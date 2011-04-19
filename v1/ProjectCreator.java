/*
 * ProjectCreator: Handles the dirty work of creating new projects; setting up variables and copying files.
 */

package v1;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
//import java.nio.*;
import java.nio.channels.FileChannel;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectCreator {
	
	static String home;
	
	ProjectCreator() {
		home = System.getProperty("user.home");
	}
	
	public Project createProject(String dir) throws IOException {
		Project p = null;
		return createProject(p, dir);
	}
	
	/**
	 * Creates a new project
	 * @param parent
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	public Project createProject(Project parent, String dir) throws IOException {
		Project p;
		if (parent != null) {
			p = new Project(parent);
			p.setMasterID(parent.getID());
		}
		else {
			p = new Project();
			p.setName("moo"); // change this
		}
		p.setID(ProjectHandler.getNextID());
		p.setDirectory(dir);
		p.updateFiles(); // necessary to change files to point to new directory
		if (createProjectDirectories(p)) {
			if (parent != null)
				createProjectFiles(parent.getDirectory(), p); // if we're making a child
			else
				createProjectFiles(p.getDirectory(), p); // otherwise
		}
		return p;
	}
	
	/**
	 * Creates the directory structure used for a project
	 * @param p
	 * @return
	 */
	private boolean createProjectDirectories(Project p) {
		String dirstr = p.getDirectory();
		
		File dir = new File(dirstr);
		if (dir.exists() && dir.isDirectory()) {
			Main.logger.warning("Directory exists!");
			dir = new File(dirstr + "/dss");
			dir.mkdirs();
			return false;
		}
		else if (dir.exists() && dir.isFile()) {
			Main.logger.warning("That's a file!");
			return false;
		}
		else {
			dir = new File(dirstr + "/dss");
			dir.mkdirs();
		}
		return true;
	}
	
	
	/**
	 * Copies project files from the parent to the new child project.
	 * @param parent_dir
	 * @param p the new project
	 * @throws IOException
	 */
	private void createProjectFiles(String parent_dir, Project p) throws IOException {
		ArrayList<DSFile> files;
		files =  p.getFiles();
		for (int i = 0; i < files.size(); i++) { // files in project
			File s = new File(parent_dir + "/" + p.getFiles().get(i).getFileString());
			File f = new File(p.getDirectory() + "/" + files.get(i).getFileString());
			if (!f.exists()) {
				f.createNewFile();
				FileChannel source = null;
				FileChannel dest = null;
				try {
					source = new FileInputStream(s).getChannel();
					dest = new FileOutputStream(f).getChannel();
					dest.transferFrom(source, 0, source.size());
				}
				finally {
					if (source != null) {
						source.close();
					}
					if (dest != null) {
						dest.close();
					}
				}
			}
			else {
				Main.logger.warning("File already exists");
			}
		}
		createSettingsFile(p);

	}
	
	/**
	 * Creates the proper settings file for the new project.
	 * @param p the project.
	 */
	private void createSettingsFile(Project p) {
		File f = new File(p.getDirectory() + "/dss/projset.dss");
		//f.mkdirs();
		FileWriter fw;
		PrintWriter pw = null;
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fw = new FileWriter(f);
			pw = new PrintWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.println("#p");
		pw.println("#n:" + p.getName());
		pw.println("#i:" + p.getID());
		pw.println("#m:" + p.getMasterID());
		pw.println("#d:" + p.getDirectory());
		for (int i = 0; i < p.files.size(); i++) {
			pw.println("#f:" + p.files.get(i).getFileString());
		}
		pw.close();
		
	}
}

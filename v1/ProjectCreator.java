/*
 * ProjectCreator: Handles the dirty work of creating new projects; setting up variables and copying files.
 */

package v1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
		}
		p.setID(ProjectHandler.getNextID());
		p.setDirectory(dir);
		p.updateFiles(); // necessary to change files to point to new directory
                int copyStatus = (parent == null) ? 1 : 0; // if making a new one, ignore existing dir and use it. 
		if (createProjectDirectories(p,copyStatus)) {
			if (parent != null)
				createProjectFiles(parent.getDirectory(), p); // if we're making a child
			else
				createProjectFiles(p.getDirectory(), p); // otherwise
		}
                else {
                    p = null;
                }
		return p;
	}
	
	/**
	 * Creates the directory structure used for a project
	 * @param p
	 * @return
	 */
	private boolean createProjectDirectories(Project p, int copyStatus) {
		String dirstr = p.getDirectory();
		
		File dir = new File(dirstr);
		if (dir.exists() && dir.isDirectory() && copyStatus == 0) {
			GUI.logger.warning("Directory exists!");
			return false;
		}
		else if (dir.exists() && dir.isFile()) {
			GUI.logger.warning("That's a file!");
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
			File s = new File(parent_dir + "/" + files.get(i).getFileString());
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
				GUI.logger.warning("File already exists");
			}
		}
        p.updateFiles();

	}
}

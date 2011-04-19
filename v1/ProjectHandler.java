/*
 * ProjectHandler: Handles the list of projects, adding new ones and cloning old ones.
 */

package v1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectHandler {
	
	private ArrayList<Project> projects;
	private static int idmax;
	private ProjectCreator pc;
	SettingsWriter sw = new SettingsWriter();
	
	public ProjectHandler() {
		importProjects();
		findMaxID();
		pc = new ProjectCreator();
	}
	
	/**
	 * Imports projects from disk. Imports dropbox projects and ones elsewhere
	 */
	private void importProjects() {
		SettingsReader sr = new SettingsReader();
		projects = sr.getProjects();
	}
	
	public static int getNextID() {
		return ++idmax;
	}
	
	/**
	 * Sets the maximum ID we've got in our projects
	 */
	private void findMaxID() {
		for (int f = 0; f < projects.size(); f++) {
			if (projects.get(f).getID() > idmax)
				idmax = projects.get(f).getID();
		}
	}
	
	public void showProjects() {
		for (int p = 0; p < projects.size(); p++) {
			projects.get(p).showProject();
		}
	}
	
	/**
	 * Creates a new child of an existing project.
	 * 
	 * @param ID the id of the project to be copied.
	 */
	public void createNewChild(int ID, String dir) {
		Project proj = null;
		Project temp = null;
		for (int p = 0; p < projects.size(); p++) {
			temp = projects.get(p);
			if (temp.getID() == ID) { // finding parent to add child to
				try {
					proj = pc.createProject(temp, dir);
				} catch (IOException e) {
					e.printStackTrace();
				}
				temp.addChild(proj);
				break;
			}
		}
		if (proj == null) {
			Main.logger.warning("Failed to copy correctly");
		}
		else {
			projects.add(proj); // add our new project to projects list
			Main.logger.info("Created child " + proj.getID() + " of " + temp.getID());
		}
		sw.createSettingsFile(proj);
		sw.updateGlobalSettings(proj);
	}
	
	/**
	 * Creates a brand new project
	 * @param dir
	 */
	public void createNewProject(String dir) {
		Project proj = new Project();
		try {
			proj = pc.createProject(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (proj == null) {
			Main.logger.warning("Failed to create correctly");
		}
		else {
			projects.add(proj); // add our new project to projects list
			Main.logger.info("Created new project" + proj.getID());
		}
		addDirectoryToProject(proj.ID, dir);
		sw.updateProjectSettings(proj);
		sw.updateGlobalSettings(proj);
	}
	
	/**
	 * Adds all given files to the project
	 * @param ID
	 * @param files
	 */
	public void addFilesToProject(int ID, ArrayList<String> files) {
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).getID() == ID) {
				for (int j = 0; j < files.size(); j++) {
					projects.get(i).addFile(files.get(j));
				}
				break;
			}
		}
	}
	
	/**
	 * Add contents of entire directory and subdirectories to project
	 * @param ID
	 * @param dir
	 */
	public void addDirectoryToProject(int ID, String dir) {
		addFilesToProject(ID,scanDirectory(dir));
	}
	

	/**
	 * Recursively scans a directory for files
	 * @param dir
	 * @return an ArrayList of filenames
	 */
	private ArrayList<String> scanDirectory(String dir) {
		ArrayList<String> files = new ArrayList<String>();
		File directory = new File(dir);
		File[] children = directory.listFiles();
		if (children != null) {
			for (int i = 0; i < children.length; i++) {
				if (children[i].isFile()) {
					files.add(children[i].getName());
				}
				else if (children[i].isDirectory()) {
					files.addAll(scanDirectory(children[i].getPath()));
				}
			}
		}
		return files;
	}
	
	public void addProject(Project tempProject) {
		projects.add(tempProject);
	}
	

}

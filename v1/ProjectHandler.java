package v1;

import java.util.ArrayList;

public class ProjectHandler {
	
	private ArrayList<Project> projects;
	private int idmax;
	
	public ProjectHandler() {
		importProjects();
		findMaxID();
	}
	
	/**
	 * Imports projects from disk. Imports dropbox projects and ones elsewhere
	 */
	private void importProjects() {
		SettingsReader sr = new SettingsReader();
		projects = sr.getProjects();
		for (int f = 0; f < projects.size(); f++) {
			projects.get(f).showProject();
		}
	}
	
	public int getNextID() {
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
	 * @return a 0 indicates child successfully created.
	 */
	public int createChild(int ID) {
		Project proj = null;
		Project temp = null;
		for (int p = 0; p < projects.size(); p++) {
			temp = projects.get(p);
			if (temp.getID() == ID) {
				proj = new Project(temp);
				proj.setMasterID(temp.getID());
				proj.setID(getNextID());
				temp.addChild(proj);
				break;
			}
		}
		if (proj == null) {
			Main.logger.warning("Failed to copy correctly");
			return 1;
		}
		else {
			projects.add(proj);
			Main.logger.info("Created child " + proj.getID() + " of " + temp.getID());
		}
		return 0;
	}
	
	public void addProject(Project tempProject) {
		projects.add(tempProject);
	}

}

package v1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SettingsReader {
	
	private String dbSettingsFile = "/home/jamie/Dropbox/dsync/settings.dss";
	private String fsSettingsFile = "/home/jamie/dsyncfs/settings.dss";
	private String projectName;
	private String projectDirectory;
	private Project tempProject = null;
	private ArrayList<Project> projects;
	private ArrayList<String> projectsToRead;
	
	public SettingsReader() {
		projects = new ArrayList<Project>();
		projectsToRead = new ArrayList<String>();
	}
	
	/**
	 * Constructor with custom settings files.
	 * 
	 * @param dbSettingsfile dropbox settings file location.
	 * @param fsSettingsfile filesystem settings file location.
	 */
	public SettingsReader(String dbSettingsfile, String fsSettingsfile) {
		this.dbSettingsFile = dbSettingsfile;
		this.fsSettingsFile = fsSettingsfile;
		
		projects = new ArrayList<Project>();
		projectsToRead = new ArrayList<String>();
	}
	
	/**
	 * Ensures dropbox and filesystem settings are read and projects
	 * generated.
	 * 
	 * @return the list of projects.
	 */
	public ArrayList<Project> getProjects() {
		readFile(dbSettingsFile);
		readFile(fsSettingsFile);
		for (int d = 0; d < projectsToRead.size(); d++) {
			tempProject = null;
			readFile(projectsToRead.get(d) + "/dss/projset.dss");
			genProject();
		}
		return projects;
	}
	
	/**
	 * Reads a file and parses it properly.
	 * @param filename the file to be read.
	 */
	private void readFile(String filename) {
		File f = new File(filename);
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			Main.logger.warning("Error opening file...");
		}
		try {
			while (scanner.hasNextLine()) {
				parseLine(scanner.nextLine());
			}
				
		} finally {
			scanner.close();
		}
	}
	
	/**
	 * Parse a line of the settings file.
	 * @param str the line to be parsed.
	 */
	private void parseLine(String str) {
		if(str.indexOf("#") == 0) {
			switch (str.charAt(1)) {
				case 'l':
					projectsToRead.add(str.substring(3));
					break;
				case 'p':
					if (tempProject != null) {
						genProject();
					}
					tempProject = new Project();
					break;
				case 'n':
					projectName = str.substring(3).trim();
					tempProject.setName(projectName);
					break;
				case 'd':
					projectDirectory = str.substring(3).trim();
					tempProject.setDirectory(projectDirectory);
					break;
				case 'i':
					String s = (str.substring(3).trim());
					tempProject.setID(Integer.parseInt(s));
					break;
				case 'm':
					String s1 = (str.substring(3).trim());
					tempProject.setMasterID(Integer.parseInt(s1));
					break;
				case 'f':
					tempProject.addFile(str.substring(3).trim());
					break;
				default:
					break;
			}
		}
		else {
			// read files and blank lines or something.
		}
	}
	
	/**
	 * Generates a project as read from the settings files.
	 */
	private void genProject() {
		if (tempProject.getMasterID() != 0) {
			for (int i = 0; i < projects.size(); i++) {
				if (tempProject.getMasterID() == projects.get(i).getID()) {
					Main.logger.info("Generated child " + tempProject.getID() +" of " + projects.get(i).getID() + " from disk");
					projects.get(i).addChild(tempProject);
					break;
				}
			}
		}
		else
			Main.logger.info("Generated new project from disk: " + tempProject.getID());
		
		projects.add(tempProject);
	}
	
	
}

/*
 * ProjectHandler: Handles the list of projects, adding new ones and cloning old ones.
 */
package v1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectHandler {

    private ArrayList<Project> projects;
    private static int idmax;
    private ProjectCreator pc;
    public Project lastProject;
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
        String dbloc = null;
        String fsloc = null;
        String cwd = System.getProperty("user.dir"); // where program running from

        Scanner scanner = null;
        try {
            File envSetting = new File(cwd+"/globalset.dss");
			scanner = new Scanner(new FileInputStream(envSetting));
		} catch (Exception e) {
			GUI.logger.warning("Error opening file...");
		}
        try {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if(str.indexOf("#") == 0) {
                    switch (str.charAt(1)) {
                        case 'd':
                            dbloc = str.substring(3);
                            break;
                        case 'f':
                            fsloc = str.substring(3);
                            break;
                        case 'w':
                            Environment.setWarnings(Integer.parseInt(str.substring(3)));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        catch(Exception e) {
            
        }

        finally {
            scanner.close();
        }
        System.out.println(dbloc + " / " + fsloc);
        SettingsReader sr = null;
        if (dbloc != null && fsloc != null) {      
            Environment.setupEnvironmentPath("dropbox", dbloc);
            Environment.setupEnvironmentPath("filesystem", fsloc);
            sr = new SettingsReader();
        } else {
            return;
        }
        
        
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
            if (projects.get(f).getID() > idmax) {
                idmax = projects.get(f).getID();
            }
        }
    }

    public void showProjects() {
        for (int p = 0; p < projects.size(); p++) {
            projects.get(p).showProject();
        }
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    /**
     * Creates a new child of an existing project.
     * 
     * @param ID the id of the project to be copied.
     */
    public boolean createNewChild(int ID, String dir, String name) {
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
  
                if (proj != null) {              
                    temp.addChild(proj);
                }
                break;
            }
        }
        if (proj == null) {
            GUI.logger.warning("Failed to copy correctly");
            return false;
        } else {
            proj.name = name;
            projects.add(proj); // add our new project to projects list
            GUI.logger.info("Created child " + proj.getID() + " of " + temp.getID());
            sw.createSettingsFile(proj);
            
            sw.updateGlobalSettings(proj);
            lastProject = proj;
            return true;
        }

    }

    /**
     * Creates a brand new project
     * @param dir
     * @return whether successful or not
     */
    public boolean createNewProject(String dir, String name) {
        Project proj = new Project();
        try {
            proj = pc.createProject(dir);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (proj == null) {
            GUI.logger.warning("Failed to create correctly");
            return false;
        } else {
            proj.name = name;
            projects.add(proj); // add our new project to projects list
            GUI.logger.info("Created new project" + proj.getID());
            addDirectoryToProject(proj.ID, dir);
            sw.updateProjectSettings(proj);
            sw.updateGlobalSettings(proj);
            lastProject = proj;
            return true;
        }

    }

    public void deleteProject(int ID) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getID() == ID) {
                Project temp = projects.get(i);

                if (temp.getMasterID() != 0) { // if it's a child
                    for (int j = 0; j < projects.size(); j++) {
                        if (projects.get(j).getID() == temp.getMasterID()) {
                            projects.get(j).deleteChild(temp); // deleting from
                            break;
                        }
                    }
                }

                projects.remove(i);
                File delf = new File(temp.getDirectory());
                boolean isdone = deleteDirectory(delf);
                if (!isdone) {
                    GUI.logger.warning("failed to delete");
                }
                
                try {
                    if (temp.getDirectory().indexOf(Environment.DROPBOX_PATH) != -1) {
                        sw.editSettingsFile(Environment.DSDP_SETTINGS_PATH, "#l:" + temp.getDirectory());
                    }
                    else {
                        sw.editSettingsFile(Environment.DSFS_SETTINGS_PATH, "#l:" + temp.getDirectory());
                    }
                } catch (IOException ex) {
                    
                }
                GUI.logger.info("Deleting project " + temp.getID());
                break;
            }
        }
    }
    
    public void moveProject(int ID) {
        Project original = lookupProject(ID);
        String name = original.getName();
        createNewChild(ID, Environment.DROPBOX_PATH+name, name);
        deleteProject(ID);
        lookupProject(name).setMasterID(0);
    }

    public boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
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
                    projects.get(i).addFile(files.get(j),1);
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
        addFilesToProject(ID, scanDirectory(dir));
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
                } else if (children[i].isDirectory()) {
                    files.addAll(scanDirectory(children[i].getPath()));
                }
            }
        }
        return files;
    }

    public void addProject(Project tempProject) {
        projects.add(tempProject);
    }
    
    public Project getParent(String name) {
        Project child = lookupProject(name);
        return(lookupProject(child.getMasterID()));
    }

    public Project lookupProject(int ID) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getID() == ID) {
                return projects.get(i);
            }
        }
        return null;
    }

    public Project lookupProject(String name) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getName().equals(name)) {
                return projects.get(i);
            }
        }
        return null;
    }
}

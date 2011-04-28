package v1;

import java.util.ArrayList;

public class Project {
	
	protected String name;
	protected String directory;
	protected int ID;
	protected int masterID;
	protected ArrayList<DSFile> files;
	private ArrayList<Project> childProjects;
	
	/**
	 * Default constructor.
	 */
	public Project() {
		files = new ArrayList<DSFile>();
		childProjects = new ArrayList<Project>();
		masterID = 0;
	}
	
	
	/**
	 * Copy constructor.
	 * 
	 * @param another	the Project object to be copied.
	 */
	public Project(Project another) {
		this.name = another.name;
		this.directory = another.directory;
		this.ID = another.ID;
		this.masterID = another.masterID;
		this.files = another.files;
		this.childProjects = new ArrayList<Project>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getDirectory() {
		return directory;
	}

	public void addFile(String file) {
		DSFile t = new DSFile(file,directory);
		files.add(t);
	}
	
	public void updateFiles() {
		for (int f = 0; f < files.size(); f++)
			files.get(f).setDirectory(directory);
	}

	public ArrayList<DSFile> getFiles() {
		return files;
	}
	
	/**
	 * Displays the contents of the project to stdout.
	 */
	public void showProject() {
		System.out.println(name + "(" + ID + ") : " + directory);
		System.out.print("Files: ");
		for (int f = 0; f < files.size(); f++)
			System.out.print(files.get(f).getFileString() + " ");
		System.out.print("\nChild projects: ");
		for (int f = 0; f < childProjects.size(); f++)
			System.out.print(childProjects.get(f).getName() + "(" + childProjects.get(f).getID() + ")");
		System.out.println("\n");
	}
        

	public void setID(int iD) {
		ID = iD;
	}

        @Override
        public String toString() {
            return getName();
        }

	public int getID() {
		return ID;
	}

	public void setMasterID(int masterID) {
		this.masterID = masterID;
	}

	public int getMasterID() {
		return masterID;
	}
	
	void addChild(Project p) {
		childProjects.add(p);
	}
        
        void deleteChild(Project p) {
                childProjects.remove(p);
        }
	
	/*void makeChild() {
		Project p = new Project();
		p.setName(this.name);
		p.setDirectory(this.directory);
		p.setMasterID(this.ID);
		childProjects.add(p);
	}*/
	
}

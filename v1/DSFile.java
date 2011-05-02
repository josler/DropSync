package v1;

public class DSFile {
	
	private String name;
	private String directory;
	private String extension;
	private int version;
	
	public DSFile(String name, String directory) {
		setName(name);
		setDirectory(directory);
		version = 1;
	}
	
	public String getExtension() {
		return extension;
	}

	public void setName(String name) {
		String[] temp = name.split("\\.");
        this.name = "";
        for (int i = 0; i < temp.length-1; i++) {
            this.name += ("." + temp[i]);
        }
        this.name = this.name.substring(1);
		this.extension = temp[temp.length-1];
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
	
	public String getFileString() {
		return (name + "." + extension);
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
}

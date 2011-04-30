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
		this.name = temp[0];
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

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
            if(name.indexOf(".") == -1) { // no .'s, simple
                this.name = name;
                this.extension = "";
            }
            else if (name.startsWith(".")) { // check if hidden
                String tail = name.substring(1);
                if (tail.indexOf(".") != -1) { // if rest has .'s in it, split
                    String[] temp = tail.split("\\.");             
                    this.name = "";
                    for (int i = 0; i < temp.length-1; i++) {
                        this.name += ("." + temp[i]);
                    }
                    this.extension = temp[temp.length - 1];
                }
                else { // if no more .'s, done
                    this.name = name;
                    this.extension = "";
                }
            }
            else { // at least 1 . , not at the start. Split
                String[] temp = name.split("\\.");
                this.name = "";
                for (int i = 0; i < temp.length-1; i++) {
                    this.name += ("." + temp[i]);
                }
                this.name = this.name.substring(1); // no . at start
                this.extension = temp[temp.length-1];
            }
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
            if (extension != "")
                return (name + "." + extension);
            else // no extension, no .
                return name;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
}

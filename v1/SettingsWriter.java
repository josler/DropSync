package v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SettingsWriter {
    
	public void updateProjectSettings(Project p) {
		createSettingsFile(p);
	}
	
	public void updateGlobalSettings(Project p) {
        String setDir = (p.getDirectory().toLowerCase().indexOf("dropbox") == -1) ? 
                        Environment.FS_PATH : Environment.DROPBOX_PATH;
		String setFile = (p.getDirectory().toLowerCase().indexOf("dropbox") == -1) ? 
                        Environment.DSFS_SETTINGS_PATH : Environment.DSDP_SETTINGS_PATH;
        
		File settings = new File(setFile);
        if(!settings.exists()) {
            File t = new File(setDir);
            t.mkdirs();
        }
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(settings, true);
			pw = new PrintWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.append("#l:" + p.getDirectory() + "\n");
		pw.close();
	}
	
	/**
	 * Creates the proper settings file for the new project.
	 * @param p the project.
	 */
	public void createSettingsFile(Project p) {
		File f = new File(p.getDirectory() + "/dss/projset.dss");
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
			pw.println("#v:" + p.files.get(i).getVersion());
		}
		pw.close();
		
	}
    
    public void editSettingsFile(String file, String lineToRemove) throws IOException {
      File inFile = new File(file);
      
      if (!inFile.isFile()) {
        System.out.println("Parameter is not an existing file");
        return;
      }
       
      //Construct the new file that will later be renamed to the original filename. 
      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
      
      BufferedReader br = new BufferedReader(new FileReader(file));
      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
      
      String line = null;

      //Read from the original file and write to the new 
      //unless content matches data to be removed.
      while ((line = br.readLine()) != null) {
        if (!line.trim().equals(lineToRemove)) {
          pw.println(line);
          pw.flush();
        }
      }
      pw.close();
      br.close();
      
      //Delete the original file
      if (!inFile.delete()) {
        System.out.println("Could not delete file");
        return;
      } 
      
      //Rename the new file to the filename the original file had.
      if (!tempFile.renameTo(inFile))
        System.out.println("Could not rename file");
    }
}

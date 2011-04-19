package v1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SettingsWriter {
	
	//private String dbSettingsFile = "/home/jamie/Dropbox/dsync/settings.dss";
	//private String fsSettingsFile = "/home/jamie/dsyncfs/settings.dss";

	public void updateProjectSettings(Project p) {
		createSettingsFile(p);
			/*String setFile = p.getDirectory() + "/dss/projset.dss";
			ArrayList<DSFile> files = p.getFiles();
			File settings = new File(setFile);
			FileWriter fw = null;
			PrintWriter pw = null;
			try {
				fw = new FileWriter(settings, true);
				pw = new PrintWriter(fw);
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int j = 0; j < files.size(); j++) {
				pw.append("#f:" + files.get(j).getName() + "\n");
			}
			pw.close(); */
	}
	
	public void updateGlobalSettings(Project p) {
		String setFile = (p.getDirectory().indexOf("Dropbox") == -1) ?
				"/home/jamie/dsyncfs/settings.dss" : "/home/jamie/Dropbox/dsync/settings.dss";

		File settings = new File(setFile);
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
		}
		pw.close();
		
	}
}

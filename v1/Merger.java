/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package v1;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 *
 * @author jamie
 */
public class Merger extends Thread {
    
    private Project parent = null;
    private Project child = null;
    
    
    public boolean run(Project child, Project parent, String mode) {
    	this.child = child;
    	this.parent = parent;
    	if (child == null) {
    		GUI.logger.info("Can't compare files");
    		return false;
    	}
    	if (mode == "up") {
    		mergeUpProjects();
    	}
    	else if (mode == "down") {
    		mergeDownProjects();
    	}
		return true;
		
    }
    
    
    /*
     * Merges files down (updating them).
     * Uses the clone as 'parent file', and master as 'child'
     */
    private void mergeDownProjects() {
    	ArrayList<DSFile> parent_files, child_files;
    	parent_files = parent.getFiles();
    	child_files = child.getFiles();
    	
    	for (int i = 0; i < parent_files.size(); i++) {
    		for (int j = 0; j < child_files.size(); j++) {
				DSFile parentFile = parent_files.get(i);
    			DSFile childFile = child_files.get(j);
    			
    			if (parentFile.getFileString().equals(childFile.getFileString())) {
               		String ffullnamep = parentFile.getDirectory() + "/" + parentFile.getFileString();
                	String ffullnamec = childFile.getDirectory() + "/" + childFile.getFileString();
					
					if(compareFiles(ffullnamep, ffullnamec)) { // if both files the same, do nothing!
                        GUI.logger.info("Trying merging " + ffullnamec + " into " + ffullnamep + ": No changes = pass");
					}
					else { // if not, copy file down.
                        GUI.logger.info("Trying merging " + ffullnamec + " into " + ffullnamep + ": Changes made = copy");
						copyFile(ffullnamep,ffullnamec);
                        // updating version
                        int version = childFile.getVersion();
                        parentFile.setVersion(version);
					}
					

                }
    		}
    	}
    	GUI.ph.sw.updateProjectSettings(parent);
    }
    
    
    /*
     * Merges up project, copying files as need and merging with diff.
     */
    private void mergeUpProjects() {       
    	ArrayList<DSFile> parent_files, child_files;
    	parent_files = parent.getFiles();
    	child_files = child.getFiles();
    	for (int i = 0; i < parent_files.size(); i++) {
    		for (int j = 0; j < child_files.size(); j++) {
    			DSFile parentFile = parent_files.get(i);
    			DSFile childFile = child_files.get(j);

    			if (parentFile.getFileString().equals(childFile.getFileString())) {
                    String ffullnamep = parentFile.getDirectory() + "/" + parentFile.getFileString();
                    String ffullnamec = childFile.getDirectory() + "/" + childFile.getFileString();

                    if (parentFile.getVersion() == childFile.getVersion()) {
                        if (compareFiles(ffullnamep, ffullnamec)) { // same
                            GUI.logger.info("Trying merging " + ffullnamec + " into " + ffullnamep + ": Same version, no changes = pass");
                        }
                        else {
                            GUI.logger.info("Trying merging " + ffullnamec + " into " + ffullnamep + ": Same version, changes made = copy");
                            copyFile(ffullnamep, ffullnamec);
                            // update version
                            int version = parentFile.getVersion();
                            version++;
                            parentFile.setVersion(version);
                            childFile.setVersion(version);
                        }
                    }
                    else { // someone else merged, compare files
                        
                        if (compareFiles(ffullnamep, ffullnamec)) { // if same
                            GUI.logger.info("Trying merging " + ffullnamec + " into " + ffullnamep + ": Different version, no changes = pass");
                        }
                        else {
                            GUI.logger.info("Trying merging " + ffullnamec + " into " + ffullnamep + ": Different version, no changes = merge");
                            mergeFiles(ffullnamep, ffullnamec);
                            // update version
                            int version = parentFile.getVersion();
                            version++;
                            parentFile.setVersion(version);
                            childFile.setVersion(version);
                        }
                    }
    			}
    		}
    	}
        // UPDATE PROJSETTINGS
        GUI.ph.sw.updateProjectSettings(parent);
        GUI.ph.sw.updateProjectSettings(child);
    }
    
    /*
     * Merging sfileb into sfilea.
     */
    private void mergeFiles(String sfilea, String sfileb) {
        File filea = new File(sfilea);
    	File fileb = new File(sfileb);
        GUI.logger.info("Merging " + sfilea + " and " + sfileb + ", please check for edits");
        String s;
        try {
            File temp = new File(Environment.FS_PATH + "temp.txt");
            Process p = null;
            p = Runtime.getRuntime().exec("diff -b -DChange " + filea + " " + fileb);
            FileWriter fw = new FileWriter(temp, true);
            PrintWriter pw = new PrintWriter(fw);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = stdInput.readLine()) != null) {
                //System.out.println(s);
                pw.println(s);
            }
            //p.waitFor();
            pw.close();
            
            /*
            GeditThread gt = new GeditThread(temp.getPath(),this);
            //Thread t = new Thread(gt);
            gt.start();
            
            // WAIT HERE
            synchronized(this) {
              try {
                wait();
              } catch(InterruptedException e) {
                throw new RuntimeException(e);
              }
            }*/
            
            
            try {
                //filea.delete();
                temp.renameTo(new File(sfilea + ".diff"));
            }
            catch (Exception e) {
                
            }
            
            
        } catch (Exception e) {

        }
    }
    
    /*
     * Compares to see if two files' contents are equal.
     */
    private boolean compareFiles(String sfilea, String sfileb) {
        File filea = new File(sfilea);
        File fileb = new File(sfileb);
		Scanner scannera = null;
        Scanner scannerb = null;
		try {
			scannera = new Scanner(new FileInputStream(filea));
            scannerb = new Scanner(new FileInputStream(fileb));
			while (scannera.hasNextLine() && scannerb.hasNextLine()) {
				if(scannera.nextLine().equals(scannerb.nextLine()))
                {
                    // do nothing
                }
                else
                    return false; // if a different line found
			}
            // if the two files different lengths!
            if (!scannera.hasNextLine() && scannerb.hasNextLine())
                return false;
            if (scannera.hasNextLine() && !scannerb.hasNextLine())
                return false;
        } catch (Exception e) {
            GUI.logger.warning("Error opening file...");
            //return false;
		} finally {
			scannera.close();
            scannerb.close();
		}
        return true;
    }
    
    /*
     * Copy fileb into filea
     */
    private void copyFile(String sfilea, String sfileb) {
        File filea = new File(sfilea);
        File fileb = new File(sfileb);
        
        FileChannel source = null;
        FileChannel dest = null;
        try {
            source = new FileInputStream(fileb).getChannel();
            dest = new FileOutputStream(filea).getChannel();
            dest.transferFrom(source, 0, source.size());
        }
        catch (Exception e) {
            
        }
        finally {
            try {
                if (source != null) {
                    source.close();
                }
                if (dest != null) {
                    dest.close();
                }
            } catch (Exception e) {
                
            }
        }
    }
}


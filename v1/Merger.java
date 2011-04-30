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
    
    
    public boolean run(Project p1, Project p2) {
    	child = (p1.getMasterID() == p2.getID()) ? p1 : p2;
    	parent = (p1.getMasterID() == p2.getID()) ? p2 : p1;
    	if (child == null) {
    		GUI.logger.info("Can't compare files");
    		return false;
    	}
		mergeProjects();
		return true;
		
    }
    
    private void mergeProjects() {
        
        
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
                    //System.out.println(parentFile.getVersion() + " " + childFile.getVersion());
                    if (parentFile.getVersion() == childFile.getVersion()) {
                        if (compareFiles(ffullnamep, ffullnamec)) { // same
                            System.out.println("same version, no change");
                        }
                        else {
                            System.out.println("same version, copying files");
                            copyFile(ffullnamep, ffullnamec); // BUG! Can't copy blindly
                        }
                    }
                    else { // someone else merged, compare files
                        
                        if (compareFiles(ffullnamep, ffullnamec)) { // if same
                            System.out.println("different version but no change");
                            //copyFile(ffullnamep, ffullnamec);
                        }
                        else {
                            System.out.println("different version, merging files");
                            mergeFiles(ffullnamep, ffullnamec);
                        }
                    }
                    
                    // update version
                    int version = parentFile.getVersion();
                    version++;
                    parentFile.setVersion(version);
                    childFile.setVersion(version);
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
                filea.delete();
                temp.renameTo(filea);
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


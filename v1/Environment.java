/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package v1;

/**
 *
 * @author jamie
 */
public class Environment {
    
    
    static String DROPBOX_PATH = "/home/jamie/Dropbox/dsync/";
    static String FS_PATH = "/home/jamie/dsyncfs/";
    static String DSDP_SETTINGS_PATH = "/home/jamie/Dropbox/dsync/settings.dss";
    static String DSFS_SETTINGS_PATH = "/home/jamie/dsyncfs/settings.dss";
    
    public static void setupEnvironmentPath(String what, String path) {
        if (what.toLowerCase().equals("dropbox")) {
            DROPBOX_PATH =  path;
            DSDP_SETTINGS_PATH = path + "/settings.dss";
        }
        else if (what.toLowerCase().equals("filesystem")) {
            FS_PATH =  path;
            DSFS_SETTINGS_PATH = path + "/settings.dss";
        }
        
    }
    
}

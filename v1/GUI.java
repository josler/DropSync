/*
 * GUI.java
 *
 * Created on 19-Apr-2011, 18:01:35
 */

package v1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.Position.Bias;
import javax.swing.tree.*;

/**
 *
 * @author jamie
 */
public class GUI extends javax.swing.JFrame {

    /** Creates new form GUI */
    public GUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightPanel = new javax.swing.JPanel();
        NewButton = new javax.swing.JButton();
        CloneButton = new javax.swing.JButton();
        MergeUpButton = new javax.swing.JButton();
        MergeDownButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        AddFileButton = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        leftPanel = new javax.swing.JScrollPane();
        Tree = new javax.swing.JTree();
        inputText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        NewButton.setText("New");
        NewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewButtonActionPerformed(evt);
            }
        });

        CloneButton.setText("Clone");
        CloneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloneButtonActionPerformed(evt);
            }
        });

        MergeUpButton.setText("Push");
        MergeUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MergeUpButtonActionPerformed(evt);
            }
        });

        MergeDownButton.setText("Pull");
        MergeDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MergeDownButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        AddButton.setText("Add Existing");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Project Options");

        AddFileButton.setText("Add File(s)");
        AddFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddFileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(AddButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(MergeDownButton, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(MergeUpButton, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(DeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(CloneButton, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(AddFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NewButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddButton)
                .addGap(18, 18, 18)
                .addComponent(AddFileButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(CloneButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DeleteButton)
                .addGap(18, 18, 18)
                .addComponent(MergeUpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MergeDownButton))
        );

        statusLabel.setFont(new java.awt.Font("DejaVu Sans", 2, 12)); // NOI18N
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        statusLabel.setText("Status");

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tree.setModel(model);
        leftPanel.setViewportView(Tree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bottomPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputText)
                            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewButtonActionPerformed
        if ("".equals(inputText.getText())) {
            statusLabel.setText("No name entered!");
        }
        else if (ph.lookupProject(inputText.getText()) != null) {
                GUI.logger.warning("File with same name exists!");
                statusLabel.setText("File with that name exists, choose another.");
            }
        else {
            if (ph.createNewProject(Environment.DROPBOX_PATH + inputText.getText(),inputText.getText())) {
                addNode("Dropbox",ph.lastProject);
                statusLabel.setText(inputText.getText() + " created.");
            }
        }
        
    }//GEN-LAST:event_NewButtonActionPerformed

    private void MergeUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MergeUpButtonActionPerformed
        /* PUSH */
        if(ph.getParent(getSelected()) != null) {
            if(Environment.Warnings) {
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(this,
                    "This will attempt to push your changes up to the master,\n if conflicts found "
                    + "diff files will be put in the folder. \nCheck for and edit these, renaming properly"
                    + "\nIs that ok?",
                    "Warning...",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
                if (n == JOptionPane.YES_OPTION) {
                    pushUp();
                }
                else {
                    statusLabel.setText("Did not merge anything.");
                }
            }
            else {
                pushUp();
            }
        }
        else
            statusLabel.setText("Please select a child to push up from.");
    }//GEN-LAST:event_MergeUpButtonActionPerformed

    private void CloneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloneButtonActionPerformed
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showSaveDialog(this);// showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Project temp = ph.lookupProject(getSelected());
            if (temp != null) {
                System.out.println(ph.lookupProject(fc.getSelectedFile().getName()+":c"));
                if (ph.lookupProject(fc.getSelectedFile().getName()+":c") != null) {
                    GUI.logger.warning("File with same name exists!");
                    statusLabel.setText("File with that name exists, please rename.");
                }
                else if (ph.createNewChild(temp.getID(),fc.getSelectedFile().getPath(),fc.getSelectedFile().getName()+":c")) {
                    addNode("FileSystem",ph.lastProject);
                    statusLabel.setText(getSelected() + " cloned.");
                }
            }
        }
    }//GEN-LAST:event_CloneButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        if(Environment.Warnings) {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(this,
                "This will permanantly delete this project on-disk. "
                + "\nIs that ok?",
                "Warning...",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
            if (n == JOptionPane.YES_OPTION) {
                Project temp = ph.lookupProject(getSelected());
                ph.deleteProject(temp.getID());
                model.removeNodeFromParent((MutableTreeNode)Tree.getSelectionPath().getLastPathComponent());
                statusLabel.setText("Deleted " + temp.getName());
            }
            else {
                statusLabel.setText("Did not delete anything.");
            }
        }
        else {
            Project temp = ph.lookupProject(getSelected());
            ph.deleteProject(temp.getID());
            model.removeNodeFromParent((MutableTreeNode)Tree.getSelectionPath().getLastPathComponent());
            statusLabel.setText("Deleted " + temp.getName());
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
         if(Environment.Warnings) {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(this,
                "This will move the project to dropbox/dsync area,\n before "
                + ", no copy will remain in previous location."
                + "\nIs that ok?",
                "Warning...",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
            if (n == JOptionPane.YES_OPTION) {    
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showOpenDialog(this);        
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    if (ph.lookupProject(fc.getSelectedFile().getName()) != null) {
                        GUI.logger.warning("File with same name exists!");
                        statusLabel.setText("File with that name exists, please rename.");
                    }
                    else if (ph.createNewProject(fc.getSelectedFile().getPath(),fc.getSelectedFile().getName())) { // make a new project
                        ph.moveProject(ph.lookupProject(fc.getSelectedFile().getName()).getID()); // MOVE to dropbox, delete old files
                        addNode("Dropbox",ph.lastProject);
                        statusLabel.setText(fc.getSelectedFile() + " created.");
                    }
                }
            }
            else {
                statusLabel.setText("Did not add anything.");
            }
         }
         else { // no warnings
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showOpenDialog(this);        
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                if (ph.lookupProject(fc.getSelectedFile().getName()) != null) {
                    GUI.logger.warning("File with same name exists!");
                    statusLabel.setText("File with that name exists, please rename.");
                }
                else if (ph.createNewProject(fc.getSelectedFile().getPath(),fc.getSelectedFile().getName())) { // make a new project
                    ph.moveProject(ph.lookupProject(fc.getSelectedFile().getName()).getID()); // MOVE to dropbox, delete old files
                    addNode("Dropbox",ph.lastProject);
                    statusLabel.setText(fc.getSelectedFile() + " created.");
                }
            }
         }
    }//GEN-LAST:event_AddButtonActionPerformed

    private void MergeDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MergeDownButtonActionPerformed
        /* PULL */
        if (ph.getParent(getSelected()) != null) {
            if (Environment.Warnings) {        
                Object[] options = {"Yes", "No"};
                int n = JOptionPane.showOptionDialog(this,
                    "This will pull down any changes made on the master."
                    + " This will overwrite any changes you have made to the clone."
                    + "\nIs that ok?",
                    "Warning...",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
                if (n == JOptionPane.YES_OPTION) {
                    pullDown();
                }
                else {
                    statusLabel.setText("Did not merge anything.");
                }
            }
            else {
                pullDown();
            }
        }
        else
            statusLabel.setText("Please select a clone to pull down to.");
    }//GEN-LAST:event_MergeDownButtonActionPerformed

    private void AddFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddFileButtonActionPerformed
        fc.setCurrentDirectory(new File(ph.lookupProject(getSelected()).getDirectory()));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(true);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
           Project proj = ph.lookupProject(getSelected());
           File[] selected = fc.getSelectedFiles();
           for(int i = 0; i < selected.length; i++) {
               proj.addFile(selected[i].getName(),1);
           }  
           ph.sw.updateProjectSettings(proj);
           statusLabel.setText("Added files to " + getSelected());
        }
        fc.setCurrentDirectory(new File(Environment.FS_PATH));
        fc.setMultiSelectionEnabled(false);
        
    }//GEN-LAST:event_AddFileButtonActionPerformed

    public static DefaultTreeModel model;
    public static MutableTreeNode root;
    public static Logger logger;
    public static TreePath tp;
    public static final JFileChooser fc = new JFileChooser();
    public static ProjectHandler ph;
    public static Merger merger;

    static { // logging bits
        try {
          int limit = 1000000; // how big our log file will be
          FileHandler fh = new FileHandler("DSLog.log", limit, 1, true);
          fh.setFormatter(new SimpleFormatter());
          logger = Logger.getLogger("DSLog");
          logger.addHandler(fh);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
    }

    /**GUIGUI
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
                GUI.logger.info("Starting program");
                ph = new ProjectHandler();
                merger = new Merger();
                setupTree();
                new GUI().setVisible(true);
                expandTree();
                
            }
        });
    }

    public static void setupTree() {
        root = new DefaultMutableTreeNode("Projects");
        MutableTreeNode dropbox = new DefaultMutableTreeNode("Dropbox");
        MutableTreeNode harddrive = new DefaultMutableTreeNode("FileSystem");
        root.insert(dropbox, 0);
        root.insert(harddrive, 1);

        
        int i = 0,j = 0;
        ArrayList<Project> curr_projects = ph.getProjects();
        while(i+j < curr_projects.size()) {
            if (curr_projects.get(i).getMasterID() == 0) {
                MutableTreeNode temp = new DefaultMutableTreeNode(curr_projects.get(i+j));
                dropbox.insert(temp, i);
                i++;
            }
            else {
                MutableTreeNode temp = new DefaultMutableTreeNode(curr_projects.get(i+j));
                harddrive.insert(temp, j);
                j++;
            }
        }
        
        
        model = new DefaultTreeModel(root);
    }
    
    
    public static void expandTree() {
        Tree.expandPath(Tree.getNextMatch("Dropbox",0,Bias.Forward));
        Tree.expandPath(Tree.getNextMatch("FileSystem",0,Bias.Forward));
    }

    public static void addNode(String loc, Project p) {
        TreePath tt = Tree.getNextMatch(loc, 0, Bias.Forward);
        MutableTreeNode node = new DefaultMutableTreeNode(p);
        MutableTreeNode nin = (MutableTreeNode)tt.getLastPathComponent();
        model.insertNodeInto(node, nin, nin.getChildCount());
    }
    
    public static String getSelected() {
        tp = Tree.getSelectionPath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tp.getLastPathComponent();
        return node.toString();
    }
    
    public static void pushUp() {
        merger.run(ph.lookupProject(getSelected()), ph.getParent(getSelected()), "up");
        statusLabel.setText(getSelected()+ " pushed up to " + ph.getParent(getSelected()));
    }
    
    public static void pullDown() {
        merger.run(ph.getParent(getSelected()), ph.lookupProject(getSelected()), "down");
        statusLabel.setText(ph.getParent(getSelected())+ " pulled down to " + getSelected());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton AddFileButton;
    private javax.swing.JButton CloneButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton MergeDownButton;
    private javax.swing.JButton MergeUpButton;
    private javax.swing.JButton NewButton;
    private static javax.swing.JTree Tree;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JTextField inputText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane leftPanel;
    private javax.swing.JPanel rightPanel;
    private static javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

}

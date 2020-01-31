package master;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class backuprestore extends javax.swing.JFrame {

    public backuprestore() {
        initComponents();
    }
    
    String path=null;
    String filename;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnBackup = new javax.swing.JButton();
        lblKetBackup = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        txtDatabaseName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnRestore = new javax.swing.JButton();
        txtPathRestore = new javax.swing.JTextField();
        btnBrowseRestore = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 0, 51));

        btnBackup.setText("Backup");
        btnBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackupActionPerformed(evt);
            }
        });

        txtDatabaseName.setEditable(false);
        txtDatabaseName.setText("laundry1.0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Database");

        btnRestore.setText("Restore");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });

        btnBrowseRestore.setText("Browse");
        btnBrowseRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseRestoreActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPathRestore)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBrowseRestore))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRestore)
                                .addGap(0, 253, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtDatabaseName)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnBackup)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblKetBackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGap(83, 83, 83))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(178, Short.MAX_VALUE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPathRestore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseRestore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRestore)
                .addGap(22, 22, 22)
                .addComponent(btnExit)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDatabaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(lblKetBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(103, 103, 103))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnBackup)
                            .addContainerGap(156, Short.MAX_VALUE)))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackupActionPerformed
        Progress();

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        try{
                String dbname = txtDatabaseName.getText();
                String dbuser = "root";
                String dbpass = "";
                

                String folderpath = "src" + "\\Backup Database";
                
                File fl = new File(folderpath);
                fl.mkdir();
                
                String savepath = "\"" + folderpath + "\\" + "" + dbname + "_" + date +".sql\"";
                //---------------------------------------------------------------
                
                // JIKA DB TIDAK MENGGUNAKAN PASSWORD//
                String execudecmd = "C:/xampp/mysql/bin/mysqldump.exe -u" + dbuser  + " --add-drop-database -B " + dbname + " -r " + savepath  ;
                
                // JIKA DB MENGGUNAKAN PASSWORD//
                // String execudecmd = "C:/xampp/mysql/bin/mysqldump.exe -u" + dbuser+  " -P" + dbpass + " --add-drop-database -B " + dbname + " -r " + savepath  ;
                
                Process runtimeprocess = Runtime.getRuntime().exec(execudecmd);
                int processcomplete = runtimeprocess.waitFor();
                
                
                if (processcomplete == 0){
                    JOptionPane.showMessageDialog(rootPane,"Backup Successfully");
//                    txt_namaDB.setText(null);
                }
                else{
                     JOptionPane.showMessageDialog(rootPane,"Backup Failed");
                     File f2 = new File("src\\Backup Database\\"+ dbname + "_" + date +".sql");
                     f2.delete();
                }
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(rootPane,e);
            }
    }//GEN-LAST:event_btnBackupActionPerformed

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        Progress();
        try{
                String dbname = txtDatabaseName.getText();
                String dbuser = "root";
                String dbpass = "";
                
                
                //String folderpath = "src" + "\\Backup Database";
                String restorepath = "src\\Backup Database\\"+ dbname+ ".sql";
                
                // JIKA DB  TIDAK MENGGUNAKAN PASSWORD//
                String[] execudecmd = new String[] {"C:/xampp/mysql/bin/mysql", "--user=" + dbuser, "--password=" +dbpass, dbname ,"-e"," source " + path };
                
                
                // JIKA DB MENGGUNAKAN PASSWORD//
                //String[] execudecmd = new String[] {"mysql", "--user=" +dbuser, "--password=" +dbpass, dbname,"-e"," source " + restorepath}; 
                
                Process runtimeprocess = Runtime.getRuntime().exec(execudecmd);
                int processcomplete = runtimeprocess.waitFor();
                
                if (processcomplete == 0){
                    JOptionPane.showMessageDialog(rootPane,"Restore Successfully");
//                    txt_namaDB.setText(null);
                }
                else{
                     JOptionPane.showMessageDialog(rootPane,"Restore Failed");
                    
                }
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(rootPane,e);
            }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void btnBrowseRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseRestoreActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        try {
            File f = fc.getSelectedFile();
            path = f.getAbsolutePath();
            path = path.replace('\\', '/');
            txtPathRestore.setText(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnBrowseRestoreActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        txtPathRestore.setText("");
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

      //--------------------- Coding Progres Bar ---------------------------//
    
public void Progress() {
        for (int i = 0; i <= 100; i++) {
            try {
                jProgressBar1.setValue(i);
                Thread.sleep(1);
            } catch (Exception e) {

            }
        }
    }

    //----------------------------------------------------------------------//
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(backuprestore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(backuprestore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(backuprestore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(backuprestore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new backuprestore().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackup;
    private javax.swing.JButton btnBrowseRestore;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRestore;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lblKetBackup;
    private javax.swing.JTextField txtDatabaseName;
    private javax.swing.JTextField txtPathRestore;
    // End of variables declaration//GEN-END:variables
}

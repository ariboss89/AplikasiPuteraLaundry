/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import koneksi.Database;
import static master.FormLogin.txt_user;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Red Dragonize
 */
public class FormMenu extends javax.swing.JFrame {

    Database dbsetting;//---1
    String driver, database, user, pass, userlogin;//--2

    /**
     * Creates new form FORM2_MENUAPLIKASI
     */
    public FormMenu() {

        Database kn = new Database();
        driver = kn.DBDriver;
        database = kn.DBDatabase;
        user = kn.DBUsername;
        pass = kn.DBPassword;

        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Authent();
        //-------------------------- Form Posisi Tengah -------------------------//   
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;
        int y = (dim.height - getHeight()) / 2;
        setLocation(x, y);
        //-----------------------------------------------------------------------//   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu_Item_S = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Pelanggan = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        Jenis = new javax.swing.JMenu();
        Pelanggan1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        Transaksi = new javax.swing.JMenu();
        Transaksi1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        MenuUtility = new javax.swing.JMenu();
        Menu_Item_KB = new javax.swing.JMenuItem();
        Menu_Item_BR = new javax.swing.JMenuItem();
        Menu_Item_UM = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        Menu_Item_S.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Menu_Item_S.setForeground(new java.awt.Color(148, 134, 134));
        Menu_Item_S.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/stock.png"))); // NOI18N
        Menu_Item_S.setText("Stok Produk");
        Menu_Item_S.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Item_SActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Utama");

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));

        lbl_user.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        lbl_user.setForeground(new java.awt.Color(255, 255, 255));
        lbl_user.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        logo_admin.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        logo_admin.setForeground(new java.awt.Color(255, 255, 255));
        logo_admin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        logo_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PUTRA LAUNDRY");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_user, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logo_admin))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logo_admin))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lbl_user, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(210, 210, 210)
                .addComponent(jLabel1)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(166, 136, 219));
        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenuBar1.setForeground(new java.awt.Color(166, 136, 219));
        jMenuBar1.setBorderPainted(false);
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jMenuBar1.setOpaque(false);

        Pelanggan.setForeground(new java.awt.Color(148, 134, 134));
        Pelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hierarchical-structure.png"))); // NOI18N
        Pelanggan.setText("Pelanggan");
        Pelanggan.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        Pelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PelangganMouseClicked(evt);
            }
        });

        jMenuItem2.setText("Pelanggan");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Pelanggan.add(jMenuItem2);

        jMenuItem1.setText("Update Member Pelanggan");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Pelanggan.add(jMenuItem1);

        jMenuBar1.add(Pelanggan);

        Jenis.setForeground(new java.awt.Color(148, 134, 134));
        Jenis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/laundry.png"))); // NOI18N
        Jenis.setText("Jenis Cuci");
        Jenis.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        Jenis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JenisMouseClicked(evt);
            }
        });
        jMenuBar1.add(Jenis);

        Pelanggan1.setForeground(new java.awt.Color(148, 134, 134));
        Pelanggan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/choices.png"))); // NOI18N
        Pelanggan1.setText("Belanja");
        Pelanggan1.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        Pelanggan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pelanggan1MouseClicked(evt);
            }
        });

        jMenuItem3.setText("Daftar Belanja");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        Pelanggan1.add(jMenuItem3);

        jMenuItem4.setText("Daftar Stok Barang");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        Pelanggan1.add(jMenuItem4);

        jMenuBar1.add(Pelanggan1);

        Transaksi.setForeground(new java.awt.Color(148, 134, 134));
        Transaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cashier.png"))); // NOI18N
        Transaksi.setText("Transaksi");
        Transaksi.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        Transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TransaksiMouseClicked(evt);
            }
        });
        jMenuBar1.add(Transaksi);

        Transaksi1.setForeground(new java.awt.Color(148, 134, 134));
        Transaksi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/report.png"))); // NOI18N
        Transaksi1.setText("Laporan");
        Transaksi1.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        Transaksi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Transaksi1MouseClicked(evt);
            }
        });

        jMenuItem5.setText("LAPORAN STOK BARANG");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        Transaksi1.add(jMenuItem5);

        jMenu3.setText("LAPORAN PENDAPATAN");

        jMenuItem6.setText("LAPORAN BULANAN/ TAHUNAN");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem10.setText("LAPORAN PERIODE");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        Transaksi1.add(jMenu3);

        jMenu2.setText("LAPORAN PENGELUARAN");

        jMenuItem8.setText("LAPORAN BULANAN/ TAHUNAN");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("LAPORAN PERIODE");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        Transaksi1.add(jMenu2);

        jMenu4.setText("LAPORAN KEUNTUNGAN");

        jMenuItem11.setText("LAPORAN BULANAN / TAHUNAN");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem12.setText("LAPORAN PERIODE");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        Transaksi1.add(jMenu4);

        jMenuBar1.add(Transaksi1);

        MenuUtility.setForeground(new java.awt.Color(148, 134, 134));
        MenuUtility.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/utility.png"))); // NOI18N
        MenuUtility.setText("Utility");
        MenuUtility.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N

        Menu_Item_KB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/detail2.png"))); // NOI18N
        Menu_Item_KB.setText("Kategori Belanja");
        Menu_Item_KB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Item_KBActionPerformed(evt);
            }
        });
        MenuUtility.add(Menu_Item_KB);

        Menu_Item_BR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/storing.png"))); // NOI18N
        Menu_Item_BR.setText("Backup & Restore");
        Menu_Item_BR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Item_BRActionPerformed(evt);
            }
        });
        MenuUtility.add(Menu_Item_BR);

        Menu_Item_UM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/users.png"))); // NOI18N
        Menu_Item_UM.setText("User Manage");
        Menu_Item_UM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Item_UMActionPerformed(evt);
            }
        });
        MenuUtility.add(Menu_Item_UM);

        jMenuBar1.add(MenuUtility);

        jMenu1.setForeground(new java.awt.Color(148, 134, 134));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit.png"))); // NOI18N
        jMenu1.setText("Exit Apps");
        jMenu1.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        if (JOptionPane.showConfirmDialog(null, "Apakan anda ingin keluar ?", ".:: Laundry1.0 ::.", 0, 3) == 0) {
            dispose();
            System.exit(0);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void Menu_Item_BRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Item_BRActionPerformed
        backuprestore data = null;
        data = new backuprestore();
        data.setLocationRelativeTo(null);
        data.setVisible(true);
    }//GEN-LAST:event_Menu_Item_BRActionPerformed

    private void Menu_Item_UMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Item_UMActionPerformed
        user_manage data = null;
        data = new user_manage();
        data.setLocationRelativeTo(null);
        data.setVisible(true);
    }//GEN-LAST:event_Menu_Item_UMActionPerformed

    private void Menu_Item_SActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Item_SActionPerformed
//        FORM6_STOK data = null;
//        data = new FORM6_STOK();
//        data.setLocationRelativeTo(null);
//        data.setVisible(true);
    }//GEN-LAST:event_Menu_Item_SActionPerformed

    private void PelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PelangganMouseClicked

    }//GEN-LAST:event_PelangganMouseClicked

    private void JenisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JenisMouseClicked
        FormJenisCuci data = null;
        data = new FormJenisCuci();
        data.setLocationRelativeTo(null);
        data.setVisible(true);
    }//GEN-LAST:event_JenisMouseClicked

    private void TransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TransaksiMouseClicked
        new FormTransaksi().show();
    }//GEN-LAST:event_TransaksiMouseClicked

    private void Transaksi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Transaksi1MouseClicked
//        cetak data = null;
//        data = new cetak();
//        data.setLocationRelativeTo(null);
//        data.setVisible(true);
    }//GEN-LAST:event_Transaksi1MouseClicked

    private void Pelanggan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pelanggan1MouseClicked

    }//GEN-LAST:event_Pelanggan1MouseClicked

    private void Menu_Item_KBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Item_KBActionPerformed
        FormJenisBelanja data = null;
        data = new FormJenisBelanja();
        data.setLocationRelativeTo(null);
        data.setVisible(true);
    }//GEN-LAST:event_Menu_Item_KBActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new FormUpdateStatusMember().show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        FormPelanggan data = new FormPelanggan();
        data.setLocationRelativeTo(null);
        data.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
//        // TODO add your handling code here:
//        belanja_new data = new belanja_new();
//        data.setLocationRelativeTo(null);
//        data.setVisible(true);
//        belanja_new.id_user.setText(txt_user.getText());
        new FormBelanja().show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new FormStokBarang().show();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        java.sql.Connection conn= new Database().getConnection();
            try{
                File file = new File("src/report/LaporanStok.jrxml");
                JasperDesign jasperDesign = JRXmlLoader.load(file);
                String sql="select *from stok";
                JRDesignQuery newQuery=new JRDesignQuery();
                newQuery.setText(sql);
                jasperDesign.setQuery(newQuery);
                JasperReport jasperReport=JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, null, conn);
                JasperViewer.viewReport(jasperPrint,false);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        new FormLaporanPengeluaran().setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        new FormLaporanPengeluaranPeriode().setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        new FormLaporanPendapatanPeriode().setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        new FormLaporanPendapatan().setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        new FormLaporanKeuntungan().show();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        new FormLaporanKeuntunganPeriode().show();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void Authent() {
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "select * from user_manage Where username='" + FormLogin.txt_user.getText() + "' ";
            ResultSet rs1 = stt.executeQuery(SQL);
            if (rs1.next()) {
                if (rs1.getString("pelanggan").equals("1")) {
                    Pelanggan.setVisible(true);
                } else {
                    Pelanggan.setVisible(false);
                }

                if (rs1.getString("jenis").equals("1")) {
                    Jenis.setVisible(true);
                } else {
                    Jenis.setVisible(false);
                }

                if (rs1.getString("belanja").equals("1")) {
                    Pelanggan1.setVisible(true);
                } else {
                    Pelanggan1.setVisible(false);
                }

                if (rs1.getString("transaksi").equals("1")) {
                    Transaksi.setVisible(true);
                } else {
                    Transaksi.setVisible(false);
                }

                if (rs1.getString("laporan").equals("1")) {
                    Transaksi1.setVisible(true);
                } else {
                    Transaksi1.setVisible(false);
                }

                if (rs1.getString("utility").equals("1")) {
                    MenuUtility.setVisible(true);
                } else {
                    MenuUtility.setVisible(false);
                }

            }
            stt.close();
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FormMenu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Jenis;
    private javax.swing.JMenu MenuUtility;
    private javax.swing.JMenuItem Menu_Item_BR;
    private javax.swing.JMenuItem Menu_Item_KB;
    private javax.swing.JMenuItem Menu_Item_S;
    private javax.swing.JMenuItem Menu_Item_UM;
    private javax.swing.JMenu Pelanggan;
    private javax.swing.JMenu Pelanggan1;
    private javax.swing.JMenu Transaksi;
    private javax.swing.JMenu Transaksi1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    public static final transient javax.swing.JLabel lbl_user = new javax.swing.JLabel();
    public static final transient javax.swing.JLabel logo_admin = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}

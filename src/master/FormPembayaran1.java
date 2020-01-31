/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import koneksi.Database;
import model.Stok;
import model.TransaksiFunction;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Red Dragonize
 */
public class FormPembayaran1 extends javax.swing.JFrame {

    private DefaultTableModel DftTblModel;
    Database dbsetting;//---1
    String driver, database, user, pass, userlogin;//--2

    public TableCellRenderer kanan = new RenderingKanan();
    public TableCellRenderer tengah = new RenderingTengah();

    public FormPembayaran1() {
        initComponents();
        Database kn = new Database();
        driver = kn.DBDriver;
        database = kn.DBDatabase;
        user = kn.DBUsername;
        pass = kn.DBPassword;
        IdTransaksi();
        String a = TransaksiFunction.getIdDetailTransaksi();
        cbIdTransaksi.addItem(a);
        //------------------------- Form Posisi Tengah --------------------------//   
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;
        int y = (dim.height - getHeight()) / 2;
        setLocation(x, y);
        //-----------------------------------------------------------------------//

        Refresh();
        lblIdPembayaran.setVisible(false);
    }

    public void TampilData() {
        DftTblModel = new DefaultTableModel();
        DftTblModel.addColumn("Id Riwayat Transaksi");
        DftTblModel.addColumn("Tanggal");
        DftTblModel.addColumn("Total");
        DftTblModel.addColumn("Bayar");
        DftTblModel.addColumn("Kembalian");
        jTable1.setModel(DftTblModel);
        java.sql.Connection conn = new Database().getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            String SQL = " select*from pembayaranku";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel.addRow(new Object[]{
                    res.getString("idriwayattransaksi"),
                    res.getString("tanggal"),
                    res.getString("total"),
                    res.getString("bayar"),
                    res.getString("kembalian")
                });
            }
        } catch (Exception e) {
        }

    }

    private void Refresh() {
        TampilData();
        IdTransaksi();
        TampilData();
    }

    private void IdTransaksi() {
        int byk_data;
        byk_data = 0;
        try {
            java.sql.Connection conn = new Database().getConnection();

            String sql = "select * from pembayaran order by Id";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            rs.last();
            byk_data = rs.getRow();
            if (byk_data == 0) {
                this.lblIdPembayaran.setText("P000001");
            } else {
                String id = rs.getString(1);
                int n = Integer.parseInt(id.substring(1, 7)) + 1;
                String nomor = "";
                for (int i = 1; i <= 2 - String.valueOf(n).length(); i++) {
                    nomor = nomor + "00000";
                }
                nomor = "P" + nomor + String.valueOf(n);
                this.lblIdPembayaran.setText(nomor);
            }
        } catch (Exception e) {
            System.out.println("Error auto increment userID : " + e + "\n");
        }
    }

    private void HitungKembalian() {
        int a = Integer.parseInt(txtTotal.getText());
        int b = Integer.parseInt(txtBayar.getText());
        int kembali = b - a;
        txtKembalian.setText(String.valueOf(kembali));
    }

    private void UpdateStatus() {
        java.sql.Connection conn = new Database().getConnection();
        try {
            java.sql.PreparedStatement stmt = conn.prepareStatement("update transaksiku set statuspembayaran='SELESAI' where Iddetail=? ");
            try {
//                stmt.setString(1,cbStatusPembayaran.getSelectedItem().toString());
                stmt.setString(1, cbIdTransaksi.getSelectedItem().toString());
                stmt.executeUpdate();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "GAGAL DI UPDATE !!!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {

        }
    }
    
    private void UpdateStatus2() {
        java.sql.Connection conn = new Database().getConnection();
        try {
            java.sql.PreparedStatement stmt = conn.prepareStatement("update transaksi set status='SELESAI' where Id=? ");
            try {
//                stmt.setString(1,cbStatusPembayaran.getSelectedItem().toString());
                stmt.setString(1, cbIdTransaksi.getSelectedItem().toString());
                stmt.executeUpdate();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "GAGAL DI UPDATE !!!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {

        }
    }
    
    private void Cetak() {
        java.sql.Connection conn = new Database().getConnection();

        try {
            HashMap parameter = new HashMap();
            File file = new File("src/report/NotaPembayaranPutraLaundry.jasper");
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
            String idDetail = cbIdTransaksi.getSelectedItem().toString();
            String total = txtTotal.getText();
            String bayar = txtBayar.getText();
            String kembalian = txtKembalian.getText();
            
            parameter.put("Iddetail", idDetail);
            parameter.put("total", total);
            parameter.put("bayar", bayar);
            parameter.put("kembalian", kembalian);

            JasperReport jp = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jp, parameter, conn);
            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
            Refresh();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_jam = new javax.swing.JLabel();
        kapasitas_meja = new javax.swing.JTextField();
        n_pajak_service = new javax.swing.JTextField();
        t_pajak_service = new javax.swing.JTextField();
        n_pajak_resto = new javax.swing.JTextField();
        txt_akhir_s = new javax.swing.JTextField();
        n_diskon = new javax.swing.JTextField();
        compare = new javax.swing.JTextField();
        total_kas_masuk = new javax.swing.JTextField();
        total_kas = new javax.swing.JTextField();
        status_ubah = new javax.swing.JLabel();
        lbl_tgl1 = new javax.swing.JLabel();
        txt_status = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblIdPembayaran = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBayar = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbStatusPembayaran = new javax.swing.JComboBox();
        cbIdTransaksi = new javax.swing.JComboBox();
        txtJumlah = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        lbl_iduser.setText("jLabel16");

        txt_idjenis.setText("jTextField1");

        kapasitas_meja.setText("jTextField1");

        txt_total_update.setText("jTextField1");

        n_pajak_service.setText("0");

        t_pajak_service.setText("0");

        n_pajak_resto.setText("0");

        txt_akhir_s.setText("0");

        n_diskon.setText("0");

        total_kas_masuk.setText("0");

        total_kas.setText("0");

        txt_status.setText("Proses");

        txt_id_trans.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FORM PESANAN");
        setUndecorated(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setPreferredSize(new java.awt.Dimension(1315, 699));

        jPanel1.setBackground(new java.awt.Color(204, 0, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TRANSAKSI LAUNDRY");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIdPembayaran)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblIdPembayaran))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(204, 0, 51));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btnRefresh.setBackground(new java.awt.Color(162, 138, 138));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(162, 138, 138));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnBayar.setBackground(new java.awt.Color(162, 138, 138));
        btnBayar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pay.png"))); // NOI18N
        btnBayar.setText("BAYAR");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(162, 138, 138));
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit (1).png"))); // NOI18N
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addGap(202, 202, 202))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Cari Transaksi");

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 0, 51));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("No Transaksi");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Jumlah");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Status");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Bayar");

        txtBayar.setText("0");
        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });
        txtBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBayarKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Kembalian");

        txtKembalian.setEditable(false);
        txtKembalian.setText("0");

        cbStatusPembayaran.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbStatusPembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELESAI" }));
        cbStatusPembayaran.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbStatusPembayaranItemStateChanged(evt);
            }
        });

        cbIdTransaksi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbIdTransaksi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbIdTransaksiItemStateChanged(evt);
            }
        });

        txtJumlah.setEnabled(false);
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });

        txtTotal.setEnabled(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        txtTanggal.setEnabled(false);
        txtTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTanggalActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tanggal");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(txtKembalian, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBayar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbIdTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbStatusPembayaran, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1357, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyReleased
        HitungKembalian();
    }//GEN-LAST:event_txtBayarKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed

    }//GEN-LAST:event_txtBayarActionPerformed

    private void btnrefresh1() {

        if (btnBayar.isEnabled()) {
            btnRefresh.setEnabled(true);
        } else {
            btnRefresh.setEnabled(false);
        }

    }

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed

        if (cbIdTransaksi.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Id Transaksi Anda di Form Transaksi!");
            new FormTransaksi().setVisible(true);
            dispose();
        } else {
            java.sql.Connection conn = new Database().getConnection();
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("insert into pembayaranku (idriwayattransaksi, tanggal, total, bayar, kembalian, status) values (?,?,?,?,?,?)");
                try {
                    stmt.setString(1, cbIdTransaksi.getSelectedItem().toString());
                    stmt.setString(2, txtTanggal.getText());
                    stmt.setString(3, txtTotal.getText());
                    stmt.setString(4, txtBayar.getText());
                    stmt.setString(5, txtKembalian.getText());
                    stmt.setString(6, cbStatusPembayaran.getSelectedItem().toString());
                    stmt.executeUpdate();
                    UpdateStatus();
                    UpdateStatus2();
                    JOptionPane.showMessageDialog(null, "MENYIMPAN DATA", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    Cetak();
//Refresh();
                    new FormTransaksi1().setVisible(true);
                    dispose();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "GAGAL MENYIMPAN DATA !!!", "Pesan", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_btnBayarActionPerformed

    private void txtBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar))
                || (vchar == KeyEvent.VK_BACK_SPACE)
                || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
//        JOptionPane.showMessageDialog(null, "Hanya Diperbolehkan Numerik");
        }

    }//GEN-LAST:event_txtBayarKeyTyped

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
        DftTblModel = new DefaultTableModel();
        DftTblModel.addColumn("Id");
        DftTblModel.addColumn("Id Transaksi");
        DftTblModel.addColumn("Nama Pelanggan");
        DftTblModel.addColumn("Jenis Laundry");
        DftTblModel.addColumn("Jumlah");
        DftTblModel.addColumn("Total");
        DftTblModel.addColumn("Tanggal Masuk");
        DftTblModel.addColumn("Tanggal Selesai");
        DftTblModel.addColumn("Keterangan");
        DftTblModel.addColumn("Pengerjaan");
        DftTblModel.addColumn("Biaya Total");
        DftTblModel.addColumn("Status Pembayaran");
        jTable1.setModel(DftTblModel);
        java.sql.Connection conn = new Database().getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            String SQL = " select*from pembayaran where Id like '%" + txtCari.getText() + "%'";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel.addRow(new Object[]{
                    res.getString("Id"),
                    res.getString("idtransaksi"),
                    res.getString("nama"),
                    res.getString("jenis"),
                    res.getString("jumlah"),
                    res.getString("totalharga"),
                    res.getString("tglmasuk"),
                    res.getString("tglselesai"),
                    res.getString("ket"),
                    res.getString("pengerjaan"),
                    res.getString("biayalaundry"),
                    res.getString("statuspembayaran")
                });
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCariKeyReleased

    private void cbStatusPembayaranItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbStatusPembayaranItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusPembayaranItemStateChanged

    private void cbIdTransaksiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbIdTransaksiItemStateChanged
        // TODO add your handling code here:
        java.sql.Connection conn = new Database().getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select *from transaksi where Id = '" + cbIdTransaksi.getSelectedItem().toString() + "'");
            while (res.next()) {
                txtJumlah.setText(res.getString("jumlahtransaksi"));
                txtTotal.setText(res.getString("totaltransaksi"));  
                txtTanggal.setText(res.getString("tanggal"));
            }
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_cbIdTransaksiItemStateChanged

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTanggalActionPerformed

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
            java.util.logging.Logger.getLogger(FormPembayaran1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FormPembayaran1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox cbIdTransaksi;
    private javax.swing.JComboBox cbStatusPembayaran;
    private javax.swing.JTextField compare;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kapasitas_meja;
    private javax.swing.JLabel lblIdPembayaran;
    public static final transient javax.swing.JLabel lbl_iduser = new javax.swing.JLabel();
    private javax.swing.JLabel lbl_jam;
    private javax.swing.JLabel lbl_tgl1;
    private javax.swing.JTextField n_diskon;
    private javax.swing.JTextField n_pajak_resto;
    private javax.swing.JTextField n_pajak_service;
    private javax.swing.JLabel status_ubah;
    private javax.swing.JTextField t_pajak_service;
    private javax.swing.JTextField total_kas;
    private javax.swing.JTextField total_kas_masuk;
    public static final transient javax.swing.JTextField txtBayar = new javax.swing.JTextField();
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtJumlah;
    public static final transient javax.swing.JTextField txtKembalian = new javax.swing.JTextField();
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txt_akhir_s;
    public static final transient javax.swing.JTextField txt_id_trans = new javax.swing.JTextField();
    public static final transient javax.swing.JTextField txt_idjenis = new javax.swing.JTextField();
    private javax.swing.JTextField txt_status;
    public static final transient javax.swing.JTextField txt_total_update = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}

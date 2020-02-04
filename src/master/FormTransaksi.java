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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.codehaus.groovy.ast.ClassHelper;

/**
 *
 * @author Red Dragonize
 */
public class FormTransaksi extends javax.swing.JFrame {

    private DefaultTableModel DftTblModel;
    Database dbsetting;//---1
    String driver, database, user, pass, userlogin;//--2

    public TableCellRenderer kanan = new RenderingKanan();
    public TableCellRenderer tengah = new RenderingTengah();

    Stok stok = new Stok();
    TransaksiFunction transaksi = new TransaksiFunction();

    public FormTransaksi() {
        initComponents();
        Database kn = new Database();
        driver = kn.DBDriver;
        database = kn.DBDatabase;
        user = kn.DBUsername;
        pass = kn.DBPassword;

        setTgl();
        setJam();
        ShowPelanggan();
        ShowKategori();
        Idtransaksi();
        IdDetailTransaski();
        TampilData();
        ShowPewangi();
        ShowDeterjen();
        txtIdTransaksi.setEditable(false);
        btn_bayar.setEnabled(false);
        btn_simpan.setEnabled(true);
        btnSelesai.setEnabled(true);
        txtBiayalaundry.setText("0");

        //------------------------- Form Posisi Tengah --------------------------//   
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;
        int y = (dim.height - getHeight()) / 2;
        setLocation(x, y);
        //-----------------------------------------------------------------------//

    }

    private void Cetak() {
        java.sql.Connection conn = new Database().getConnection();

        try {
            HashMap parameter = new HashMap();
            File file = new File("src/report/NotaPutraLaundry.jasper");
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
            String Id = TransaksiFunction.getIdDetailTransaksi();
            parameter.put("Iddetail", Id);

            JasperReport jp = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jp, parameter, conn);
            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
            Refresh();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void TampilData() {
        DftTblModel = new DefaultTableModel();
        DftTblModel.addColumn("Id");
        DftTblModel.addColumn("Id Detail");
        DftTblModel.addColumn("Nama");
        DftTblModel.addColumn("Jenis");
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
            String SQL = " select *from transaksiku where Iddetail = '" + transaksi.getIdDetailTransaksi() + "'";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel.addRow(new Object[]{
                    res.getString("Id"),
                    res.getString("Iddetail"),
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
            int a = jTable1.getRowCount();
            transaksi.setJumlahData(a);
        } catch (Exception e) {
        }

    }

    private void Refresh() {

        setTgl();
        setJam();
        TampilData();
        ShowPelanggan();
        Idtransaksi();
        txtIdTransaksi.setEditable(false);
        txtTotalHarga.setText("");
        txtTanggalSelesai.toString().equals("");
        txtJumlah.setText("");
        txtJumlah.setEditable(true);
        cbPengerjaan.setEnabled(true);
        cbPengerjaan.setSelectedItem("PILIH");
        txtBiayalaundry.setText("0");
    }

    private void ShowPelanggan() {
        java.sql.Connection conn = new Database().getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select *from data_pelanggan");
            while (res.next()) {
                cbNamaPelangan.addItem(res.getString("nama"));
            }
        } catch (SQLException ex) {

        }
    }

    private void ShowKategori() {
        java.sql.Connection conn = new Database().getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select *from jenis");
            while (res.next()) {
                cbJenisLaundry.addItem(res.getString("jenis"));
            }
        } catch (SQLException ex) {

        }
    }

    private void ShowDeterjen() {
        java.sql.Connection conn = new Database().getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select *from stok where kategori = 'Sabun' order by qty asc");
            while (rs.next()) {
                String nama = rs.getString("nama");
                stok.setNamaDeterjen(nama);
                String satuan = rs.getString("satuan");
                stok.setSatuanDeterjen(satuan);
                String qty = rs.getString("qty");
                stok.setStokDeterjen(qty);
                int minStok = rs.getInt("minqty");
                stok.setMinStokDeterjen(minStok);
            }
        } catch (SQLException ex) {

        }
    }

    private void ShowPewangi() {
        java.sql.Connection conn = new Database().getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select *from stok where kategori = 'Pewangi' order by qty asc");
            while (rs.next()) {
                String nama = rs.getString("nama");
                stok.setNamaPewangi(nama);
                cbKet.addItem(nama);
                String satuan = rs.getString("satuan");
                stok.setSatuanPewangi(satuan);
                String qty = rs.getString("qty");
                stok.setStokPewangi(qty);
                int minStok = rs.getInt("minqty");
                stok.setMinStokPewangi(minStok);
            }
        } catch (SQLException ex) {

        }
    }

    private void UpdateStokDeterjen() {
        String namaDeterjen = stok.getNamaDeterjen();
        String stokDeterjen = stok.getStokDeterjen();
        int aStokDeterjen = Integer.parseInt(String.valueOf(stokDeterjen));
        String jumlahLaundry = transaksi.getJumlahLaundry();
        int aJumlahLaundry = Integer.parseInt(String.valueOf(jumlahLaundry));

        String namaPewangi = stok.getNamaPewangi();
        String stokPewangi = stok.getStokPewangi();
        int aStokPewangi = Integer.parseInt(String.valueOf(stokPewangi));

        int minStokPewangi = aStokPewangi - aJumlahLaundry;
        stok.setTotalStokPewangi(minStokPewangi);

        int realStokPewangi = aStokPewangi - stok.getMinStokPewangi();

        int realStok = aStokDeterjen - stok.getMinStokDeterjen();

        int minStok = realStok - aJumlahLaundry;
        stok.setTotalStokDeterjen(minStok);

        int minStokDeterjen = stok.getMinStokDeterjen();

        if (aJumlahLaundry > realStok) {
            JOptionPane.showMessageDialog(null, "Tidak Bisa di Proses Karena Stok Kurang !!!");
            txtJumlah.setText("");
            txtJumlah.requestFocus();
        } else if (aJumlahLaundry > realStokPewangi) {
            JOptionPane.showMessageDialog(null, "Tidak Bisa di Proses Karena Stok Kurang !!!");
            txtJumlah.setText("");
            txtJumlah.requestFocus();
        } else {
            java.sql.Connection conn = new Database().getConnection();
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("update stok set qty ='" + stok.getTotalStokDeterjen() + "' where nama='" + stok.getNamaDeterjen() + "'");
                try {
                    stmt.executeUpdate();
                    UpdateStokPewangi();
                    Save();
                } catch (Exception e) {

                }
            } catch (Exception e) {

            }
        }
    }

    private void UpdateStokPewangi() {
//        String namaPewangi = stok.getNamaPewangi();
//        String stokPewangi = stok.getStokPewangi();
//        int aStokPewangi = Integer.parseInt(String.valueOf(stokPewangi));
//        String jumlahLaundry = transaksi.getJumlahLaundry();
//        int aJumlahLaundry = Integer.parseInt(String.valueOf(jumlahLaundry));
//
//        int minStokPewangi = aStokPewangi - aJumlahLaundry;
//        stok.setTotalStokPewangi(minStokPewangi);
//
//        int realStokPewangi = aStokPewangi - stok.getMinStokPewangi();
//  
//        if (aJumlahLaundry > realStok) {
//            JOptionPane.showMessageDialog(null, "Tidak Bisa di Proses Karena Stok Kurang !!!");
//            txtJumlah.setText("");
//            txtJumlah.requestFocus();
//        } else {
        java.sql.Connection conn = new Database().getConnection();
        try {
            java.sql.PreparedStatement stmt = conn.prepareStatement("update stok set qty ='" + stok.getTotalStokPewangi() + "' where nama='" + cbKet.getSelectedItem() + "'");
            try {
                stmt.executeUpdate();
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
        //}
    }

    private void IdDetailTransaski() {
        int byk_data;
        byk_data = 0;
        try {
            Connection kon = DriverManager.getConnection(database, user, pass);
            String sql = "select * from transaksi order by Id";
            Statement stat = kon.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            rs.last();
            byk_data = rs.getRow();
            if (byk_data == 0) {
                transaksi.setIdDetailTransaksi("DT-01");
            } else {
                String id = rs.getString(1);
                int n = Integer.parseInt(id.substring(3, 5)) + 1;
                String nomor = "";
                for (int i = 1; i <= 2 - String.valueOf(n).length(); i++) {
                    nomor = nomor + "0";
                }
                nomor = "DT-" + nomor + String.valueOf(n);
                transaksi.setIdDetailTransaksi(nomor);
            }
        } catch (Exception e) {
            System.out.println("Error auto increment userID : " + e + "\n");
        }
    }

    private void SaveDetailTransaksi() {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = spf.format(txtTanggalSelesai.getDate());
        java.sql.Connection conn = new Database().getConnection();
        try {
            java.sql.PreparedStatement stmt = conn.prepareStatement("insert into transaksi (Id, jumlahtransaksi, totaltransaksi, tanggal, status) values (?,?,?,?,?)");
            try {
                stmt.setString(1, transaksi.getIdDetailTransaksi());
                stmt.setInt(2, transaksi.getJumlahData());
                stmt.setInt(3, transaksi.getTotalBiaya());
                stmt.setString(4, tgl);
                stmt.setString(5, cbStatusPembayaran.getSelectedItem().toString());
                stmt.executeUpdate();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "GAGAL MENYIMPAN DATA !!!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {

        }
    }

    public void SumPembayaran() {
        int rowsCount = jTable1.getRowCount();
        int sum = 0;
        for (int i = 0; i < rowsCount; i++) {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 10).toString());
        }
        transaksi.setTotalBiaya(sum);
    }

    private void Save() {
        String stokDeterjen = stok.getStokPewangi();
        int aStokDeterjen = Integer.parseInt(String.valueOf(stokDeterjen));
        int minStokDeterjen = stok.getMinStokDeterjen();

        String stokPewangi = stok.getStokPewangi();
        int aStokPewangi = Integer.parseInt(String.valueOf(stokPewangi));
        int minStokPewangi = stok.getMinStokPewangi();

        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = spf.format(txtTanggalSelesai.getDate());

        if (minStokDeterjen == aStokDeterjen) {
            JOptionPane.showMessageDialog(null, "Stok Deterjen Kurang, Silahkan Tambahkan Dahulu !");
            new FormStokBarang().setVisible(true);
            dispose();
        } else if (minStokPewangi == aStokPewangi) {
            JOptionPane.showMessageDialog(null, "Stok Pewangi Kurang, Silahkan Tambahkan Dahulu !");
            new FormStokBarang().setVisible(true);
            dispose();
        } else if (txtJumlah.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jumlah Laundry Kosong !");
            txtJumlah.requestFocus();
        } else if (txtBiayalaundry.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Total Biaya Laundry Kosong !");
            txtBiayalaundry.requestFocus();
        } else {
            java.sql.Connection conn = new Database().getConnection();
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("insert into transaksiku (Id,Iddetail, nama,jenis,jumlah,totalharga,tglmasuk,tglselesai,ket,pengerjaan,biayalaundry,statuspembayaran) values (?,?,?,?,?,?,?,?,?,?,?,?)");
                try {
                    stmt.setString(1, txtIdTransaksi.getText());
                    stmt.setString(2, transaksi.getIdDetailTransaksi());
                    stmt.setString(3, cbNamaPelangan.getSelectedItem().toString());
                    stmt.setString(4, cbJenisLaundry.getSelectedItem().toString());
                    stmt.setString(5, txtJumlah.getText());
                    stmt.setString(6, txtTotalHarga.getText());
                    stmt.setString(7, txtTanggalMasuk.getText());
                    stmt.setString(8, tgl);
                    stmt.setString(9, cbKet.getSelectedItem().toString());
                    stmt.setString(10, cbPengerjaan.getSelectedItem().toString());
                    stmt.setString(11, txtBiayalaundry.getText());
                    stmt.setString(12, cbStatusPembayaran.getSelectedItem().toString());

                    String jumlah = txtJumlah.getText();
                    transaksi.setJumlahLaundry(jumlah);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "MENYIMPAN DATA", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    cbPengerjaan.setSelectedItem("PILIH");
                    txtBiayalaundry.setText("0");
                    Refresh();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "GAGAL MENYIMPAN DATA !!!", "Pesan", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {

            }
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
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btn_simpan = new javax.swing.JButton();
        btnSelesai = new javax.swing.JButton();
        btn_bayar = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtIdTransaksi = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cbNamaPelangan = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        txtStatusMember = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbJenisLaundry = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbPengerjaan = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbStatusPembayaran = new javax.swing.JComboBox();
        cbKet = new javax.swing.JComboBox();
        txtTanggalSelesai = new com.toedter.calendar.JDateChooser();

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
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
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
                .addGap(102, 102, 102)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel1)
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
        jTable1.setEnabled(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btn_simpan.setBackground(new java.awt.Color(162, 138, 138));
        btn_simpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btnSelesai.setBackground(new java.awt.Color(162, 138, 138));
        btnSelesai.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSelesai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cancel.png"))); // NOI18N
        btnSelesai.setText("SELESAI");
        btnSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesaiActionPerformed(evt);
            }
        });

        btn_bayar.setBackground(new java.awt.Color(162, 138, 138));
        btn_bayar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_bayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pay.png"))); // NOI18N
        btn_bayar.setText("BAYAR");
        btn_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bayarActionPerformed(evt);
            }
        });

        btn_exit.setBackground(new java.awt.Color(162, 138, 138));
        btn_exit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit (1).png"))); // NOI18N
        btn_exit.setText("EXIT");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(btn_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSelesai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(168, 168, 168))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelesai)
                    .addComponent(btn_bayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel7.setText("Nama Pelanggan");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pengerjaan");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Biaya Laundry");

        txtBiayalaundry.setEditable(false);
        txtBiayalaundry.setText("0");

        txtIdTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTransaksiActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tgl Selesai");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Tgl Masuk");

        txtTanggalMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTanggalMasukActionPerformed(evt);
            }
        });
        txtTanggalMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTanggalMasukKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTanggalMasukKeyTyped(evt);
            }
        });

        cbNamaPelangan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbNamaPelangan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbNamaPelanganItemStateChanged(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Status Member");

        txtStatusMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStatusMemberActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Jenis Laundry");

        cbJenisLaundry.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbJenisLaundry.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbJenisLaundryItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Harga ");

        txtHargaLaundry.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Jml (Kg)");

        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total Harga");

        txtTotalHarga.setEditable(false);

        cbPengerjaan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbPengerjaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PILIH", "Diantar", "Diambil" }));
        cbPengerjaan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPengerjaanItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Keterangan");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Status Pembayaran");

        cbStatusPembayaran.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbStatusPembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BELUM SELESAI" }));
        cbStatusPembayaran.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbStatusPembayaranItemStateChanged(evt);
            }
        });

        txtTanggalSelesai.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTanggalSelesaiPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(128, 128, 128)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbNamaPelangan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdTransaksi)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel19))
                        .addGap(110, 110, 110)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBiayalaundry)
                            .addComponent(cbStatusPembayaran, 0, 248, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(153, 153, 153)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbPengerjaan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbKet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(128, 128, 128)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStatusMember)
                            .addComponent(cbJenisLaundry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTotalHarga, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTanggalMasuk)
                            .addComponent(txtHargaLaundry)
                            .addComponent(txtTanggalSelesai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNamaPelangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStatusMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbJenisLaundry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtHargaLaundry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggalMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggalSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cbKet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPengerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBiayalaundry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1357, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        bersih();
        dispose();
    }//GEN-LAST:event_btn_exitActionPerformed

    private void txtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyReleased
        datakosong();
    }//GEN-LAST:event_txtJumlahKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        TransaksiFunction.setIdDetailTransaksi(jTable1.getValueAt(row, 0).toString());
        btn_bayar.setEnabled(true);
        btn_simpan.setEnabled(false);
        btnSelesai.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed

    }//GEN-LAST:event_txtJumlahActionPerformed

    private void btn_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bayarActionPerformed
        TransaksiFunction.getIdTransaksi();
        new FormPembayaran().setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_bayarActionPerformed

    private void txtJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyTyped
        char vchar = evt.getKeyChar();
        if (!(Character.isDigit(vchar))
                || (vchar == KeyEvent.VK_BACK_SPACE)
                || (vchar == KeyEvent.VK_DELETE)) {
            evt.consume();
//        JOptionPane.showMessageDialog(null, "Hanya Diperbolehkan Numerik");
        }

    }//GEN-LAST:event_txtJumlahKeyTyped

    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaiActionPerformed
        SumPembayaran();
        SaveDetailTransaksi();
        Cetak();
        IdDetailTransaski();
        Refresh();
    }//GEN-LAST:event_btnSelesaiActionPerformed

    private void txtIdTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTransaksiActionPerformed

    }//GEN-LAST:event_txtIdTransaksiActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed

        String jumlah = txtJumlah.getText();
        transaksi.setJumlahLaundry(jumlah);
        UpdateStokDeterjen();

    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txtTanggalMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTanggalMasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTanggalMasukActionPerformed

    private void txtTanggalMasukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTanggalMasukKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTanggalMasukKeyReleased

    private void txtTanggalMasukKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTanggalMasukKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTanggalMasukKeyTyped

    private void cbNamaPelanganItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNamaPelanganItemStateChanged
        // TODO add your handling code here:
        java.sql.Connection conn = new Database().getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select *from data_pelanggan where nama = '" + cbNamaPelangan.getSelectedItem() + "'");
            while (res.next()) {
                TransaksiFunction trs = new TransaksiFunction();
                trs.setIdPelanggan(res.getString("id_pelanggan"));
                trs.setNamaPelanggan(res.getString("nama"));
                trs.setStatus(res.getString("status"));
                String statusplgn = trs.getStatus();
                txtStatusMember.setText(statusplgn);
            }
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_cbNamaPelanganItemStateChanged

    private void txtStatusMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStatusMemberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStatusMemberActionPerformed

    private void cbJenisLaundryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbJenisLaundryItemStateChanged
        // TODO add your handling code here:
        java.sql.Connection conn = new Database().getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select *from jenis where jenis = '" + cbJenisLaundry.getSelectedItem() + "'");
            while (res.next()) {
                txtHargaLaundry.setText(res.getString("harga"));

                if (cbJenisLaundry.getSelectedItem().equals("Paket Hemat 1 25 Kg")) {
                    txtHargaLaundry.setText(res.getString("harga"));
                    txtJumlah.setText("25");
                    txtJumlah.setEditable(false);
                    txtTotalHarga.setText(res.getString("harga"));
                } else if (cbJenisLaundry.getSelectedItem().equals("Paket Hemat 2 45 Kg")) {
                    txtHargaLaundry.setText(res.getString("harga"));
                    txtJumlah.setText("45");
                    txtJumlah.setEditable(false);
                    txtTotalHarga.setText(res.getString("harga"));
                } else if (cbJenisLaundry.getSelectedItem().equals("Paket Hemat 1 65 Kg")) {
                    txtHargaLaundry.setText(res.getString("harga"));
                    txtJumlah.setText("65");
                    txtJumlah.setEditable(false);
                    txtTotalHarga.setText(res.getString("harga"));
                }
            }
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_cbJenisLaundryItemStateChanged

    private void cbPengerjaanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPengerjaanItemStateChanged
        // TODO add your handling code here:
        int total = Integer.parseInt(txtTotalHarga.getText());
        String status = txtStatusMember.getText().trim();
        int a = Integer.parseInt(txtJumlah.getText());
        int b;
        int c = Integer.parseInt(txtTotalHarga.getText());
        int diskon = ((c * 10) / 100);
        int totale = c - diskon;

        if (cbPengerjaan.getSelectedItem().equals("PILIH")) {
            cbPengerjaan.requestFocus();
        } else if (cbPengerjaan.getSelectedItem().equals("Diantar") && status.equals("Member")) {

            if (a < 5) {
                b = totale + 6000;
                txtBiayalaundry.setText(String.valueOf(b));
            } else {
                txtBiayalaundry.setText(String.valueOf(totale));
            }
        } else if (cbPengerjaan.getSelectedItem().equals("Diambil") && status.equals("Member")) {

            txtBiayalaundry.setText(String.valueOf(totale));
        }
         else if (cbPengerjaan.getSelectedItem().equals("Diantar") && status.equals("Non Member")) {

            if (a < 5) {
                b = c + 6000;
                txtBiayalaundry.setText(String.valueOf(b));
            } else {
                txtBiayalaundry.setText(String.valueOf(c));
            }
        } else if (cbPengerjaan.getSelectedItem().equals("Diambil") && status.equals("Non Member")) {

            txtBiayalaundry.setText(String.valueOf(c));
        }
    }//GEN-LAST:event_cbPengerjaanItemStateChanged

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
        if (!txtCari.getText().equals("")) {
            DftTblModel = new DefaultTableModel();
            DftTblModel.addColumn("Id");
            DftTblModel.addColumn("Jumlah Transaksi");
            DftTblModel.addColumn("Total");
            jTable1.setModel(DftTblModel);
            java.sql.Connection conn = new Database().getConnection();
            try {
                java.sql.Statement stmt = conn.createStatement();
                String SQL = " select *from transaksi where Id like '%" + txtCari.getText() + "%' And status ='Belum Selesai'";
                java.sql.ResultSet res = stmt.executeQuery(SQL);
                while (res.next()) {
                    DftTblModel.addRow(new Object[]{
                        res.getString("Id"),
                        res.getString("jumlahtransaksi"),
                        res.getString("totaltransaksi")
                    });
                }
                int a = jTable1.getRowCount();
                transaksi.setJumlahData(a);
                jTable1.setEnabled(true);
            } catch (Exception e) {
            }
        } else {
            TampilData();
        }

    }//GEN-LAST:event_txtCariKeyReleased

    private void cbStatusPembayaranItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbStatusPembayaranItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusPembayaranItemStateChanged

    private void txtTanggalSelesaiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTanggalSelesaiPropertyChange
        // TODO add your handling code here:
        if (txtTanggalSelesai.getDate() != null) {
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = spf.format(txtTanggalSelesai.getDate());

        }
    }//GEN-LAST:event_txtTanggalSelesaiPropertyChange

    public void CANCELDATA() {
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "update transaksi set status='CANCEL' where no_trans='" + txtIdTransaksi.getText() + "'";
            stt.executeUpdate(SQL);
            stt.close();
            kon.close();
        } catch (Exception exc) {
            System.err.println(exc.getMessage());

        }
    }

    public static Date getTanggalFromTable(JTable table, int kolom) {
        JTable tabel = table;
        String str_tgl = String.valueOf(tabel.getValueAt(tabel.getSelectedRow(), kolom));
        Date tanggal = null;
        try {
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(str_tgl);

        } catch (ParseException ex) {
            Logger.getLogger(FormTransaksi.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return tanggal;
    }

    public void edit_nilaistruk() {
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "update transaksi set biaya_laundry='" + txtBiayalaundry.getText()
                    + "' where no_trans='" + txtIdTransaksi.getText() + "'";
            stt.executeUpdate(SQL);
            stt.close();
            kon.close();
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }

    private void btn_order_off() {

        String faktur = compare.getText();
        if (faktur.equals("")) {
            this.btn_simpan.setEnabled(true);
        } else {
            this.btn_simpan.setEnabled(false);
        }
    }

    // Refresh Tabel & kolom - kolom -----------------------------------------//
    public void RefreshTabel() {
        int brs = tblModel.getRowCount();
        for (int i = 0; i < brs; i++) {
            tblModel.removeRow(0);
        }
    }
    //------------------------------------------------------------------------//

    // Refresh Tabel & kolom - kolom -----------------------------------------//
    public void RefreshTabel1() {
        int brs = tblModel1.getRowCount();
        for (int i = 0; i < brs; i++) {
            tblModel1.removeRow(0);
        }
    }

    // Refresh Tabel & kolom - kolom -----------------------------------------//
    public void RefreshTabel2() {
        int brs = tblModel2.getRowCount();
        for (int i = 0; i < brs; i++) {
            tblModel2.removeRow(0);
        }
    }

    //------------------------------------------------------------------------//
    private boolean datakosong() {
        String nilai1 = txtJumlah.getText();

        boolean result;
        if ((nilai1.equals(""))) {
            this.txtTotalHarga.setText("");
            {
            }
            result = false;
        } else {
            total_harga();
        }
        result = true;
        return result;
    }

    //------------------------------------------------------------------------//
    int row = 0;

    private void pindahdatapesan() {
        row = jTable1.getSelectedRow();
        String cr1 = (jTable1.getValueAt(row, 0).toString());
        master.FormTransaksi.txt_id_trans.setText(cr1);

        row = jTable1.getSelectedRow();
        String cr2 = (jTable1.getValueAt(row, 1).toString());
        master.FormTransaksi.txt_idjenis.setText(cr2);

        row = jTable1.getSelectedRow();
        String cr3 = (jTable1.getValueAt(row, 2).toString());

        row = jTable1.getSelectedRow();
        String cr4 = (jTable1.getValueAt(row, 3).toString());
        master.FormTransaksi.txtHargaLaundry.setText(cr4);

        row = jTable1.getSelectedRow();
        String cr5 = (jTable1.getValueAt(row, 4).toString());
        master.FormTransaksi.txtJumlah.setText(cr5);

        row = jTable1.getSelectedRow();
        String cr6 = (jTable1.getValueAt(row, 5).toString());
        master.FormTransaksi.txtTotalHarga.setText(cr6);
        master.FormTransaksi.txt_total_update.setText(cr6);

    }

    private void Tabel(javax.swing.JTable tb, int lebar[]) {
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int kolom = tb.getColumnCount();
        for (int i = 0; i < kolom; i++) {
            javax.swing.table.TableColumn tbc = tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
            tb.setRowHeight(17);
        }
    }

    private void total_harga() {
        int a = Integer.parseInt(FormTransaksi.txtHargaLaundry.getText());
        int b = Integer.parseInt(FormTransaksi.txtJumlah.getText());
        int c = a * b;

        FormTransaksi.txtTotalHarga.setText(Integer.toString((int) (c)));

    }

    private void total_pesanan() {
        int d = Integer.parseInt(FormTransaksi.txtBiayalaundry.getText());
        int e = Integer.parseInt(FormTransaksi.txtTotalHarga.getText());
        int f = d + e;
        int potmember = f * 10 * 100;
        int total = f - potmember;

        if (txtStatusMember.getText().equals("Member")) {
            FormTransaksi.txtBiayalaundry.setText(Integer.toString((int) (total)));
        } else if (txtStatusMember.getText().equals(("Non Member"))) {
            FormTransaksi.txtBiayalaundry.setText(Integer.toString((int) (f)));
        }

    }

    private void hapus_pesanan() {
        int d = Integer.parseInt(FormTransaksi.txtBiayalaundry.getText());
        int e = Integer.parseInt(FormTransaksi.txtTotalHarga.getText());
        int f = d - e;

        FormTransaksi.txtBiayalaundry.setText(Integer.toString((int) (f)));

    }

    private void update_pesanan() {
//        int j = Integer.parseInt(Transaksi.txt_biayalaundry.getText());
        int k = Integer.parseInt(FormTransaksi.txtTotalHarga.getText());
        int l = Integer.parseInt(FormTransaksi.txt_total_update.getText());
//        int m = (j-l)+k;
        int m = l + k;
        FormTransaksi.txtBiayalaundry.setText(Integer.toString((int) (m)));

    }

    private void pajak_service() {
        int n = Integer.parseInt(FormTransaksi.txtBiayalaundry.getText());
        int o = (n * 3) / 100;
        n_pajak_service.setText(Integer.toString((int) (o)));

    }

    private void n_pajak_service() {
        int p = Integer.parseInt(n_pajak_service.getText());
        int q = Integer.parseInt(FormTransaksi.txtBiayalaundry.getText());
        int r = p + q;
        t_pajak_service.setText(Integer.toString((int) (r)));

    }

    private void pajak_resto() {
        int s = Integer.parseInt(t_pajak_service.getText());
        int t = (s * 10) / 100;
        n_pajak_resto.setText(Integer.toString((int) (t)));

    }

    //-------------------------- Default Tabel -------------------------------//
    private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Jenis", "Harga"}) {
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return false;
                    }
                };
    }

    //--------------------------- Database Showtabel -------------------------//
    public void ShowTabel() {
        jTable1.setModel(tblModel1);
        String data[] = new String[7];
        String stat = "";
        try {
            Class.forName(driver);
            try (Connection kon = DriverManager.getConnection(database, user, pass);
                    Statement stt = kon.createStatement()) {
                String SQL = "Select * From transaksiku order by Id asc";
                ResultSet res = stt.executeQuery(SQL);
                while (res.next()) {
                    data[0] = res.getString(1);
                    data[1] = res.getString(3);
                    data[2] = res.getString(4);
                    data[3] = res.getString(5);
                    data[4] = res.getString(6);
                    data[5] = res.getString(7);
                    data[6] = res.getString(8);
                    tblModel1.addRow(data);
                }
                res.close();
                stt.close();
            }
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
    //------------------------------------------------------------------------//

    //-------------------------- Default Tabel -------------------------------//
    private javax.swing.table.DefaultTableModel getDefaultTabelModel1() {
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"IDTrans", "IDJenis", "Jenis", "Harga", "Jml", "Total", "Keterangan"}) {
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return false;
                    }
                };
    }

    //-------------------------- Default Tabel -------------------------------//
    private javax.swing.table.DefaultTableModel getDefaultTabelModel2() {
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"No Trans", "Tgl_M", "Nama", "Pengerjaan", "Tgl_S", "Status", "Total"}) {
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return false;
                    }
                };
    }

    private boolean datavalid() {
        boolean result;
        if ((txtIdTransaksi.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Isi data yang dibutuhkan");
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public void setJam() {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "", nol_menit = "", nol_detik = "";

                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();

                if (nilai_jam <= 9) {
                    nol_jam = "0";
                }
                if (nilai_menit <= 9) {
                    nol_menit = "0";
                }
                if (nilai_detik <= 9) {
                    nol_detik = "0";
                }

                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                lbl_jam.setText(jam + ":" + menit + ":" + detik + " ");
                // KosongkanTabel();
                // ShowTable();
            }
        };
        new Timer(1000, taskPerformer).start();
    }
  //--------------------------------------------------------------------------//

    //---------------------------- NO FAKTUR OTOMATIS --------------------------//  
    private void Idtransaksi() {
        int byk_data;
        byk_data = 0;
        try {
            Connection kon = DriverManager.getConnection(database, user, pass);
            String sql = "select * from transaksiku order by Id";
            Statement stat = kon.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            rs.last();
            byk_data = rs.getRow();
            if (byk_data == 0) {
                this.txtIdTransaksi.setText("T0000001");
            } else {
                String id = rs.getString(1);
                int n = Integer.parseInt(id.substring(1, 8)) + 1;
                String nomor = "";
                for (int i = 1; i <= 7 - String.valueOf(n).length(); i++) {
                    nomor = nomor + "0";
                }
                nomor = "T" + nomor + String.valueOf(n);
                this.txtIdTransaksi.setText(nomor);
            }
        } catch (Exception e) {
            System.out.println("Error auto increment faktur : " + e + "\n");
        }
    }

    //------------------------------------------------------------------------//
    //---------------------- Coding Tanggal Automatic -------------------------// 
    private void setTgl() {
        java.util.Date tanggal = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = format.format(tanggal);
        txtTanggalMasuk.setText(format.format(tanggal));

    }

    // Membersihkan Data ke posisi kosong ------------------------------------//
    private void bersih() {
        this.compare.setText("");
        this.txtIdTransaksi.setText("");
        this.txtIdTransaksi.setText("");
        this.total_kas.setText("0");
        this.total_kas_masuk.setText("0");
        this.txtBiayalaundry.setText("0");
        this.n_pajak_service.setText("0");
        this.t_pajak_service.setText("0");
        this.n_pajak_resto.setText("0");
        this.txt_akhir_s.setText("0");
        this.txtBiayalaundry.setText("0");
        this.n_diskon.setText("0");
        this.txt_total_update.setText("0");
        txtTanggalSelesai.setDate(null);
    }

    //-----------------------------------------------------------------------//
    // Membersihkan Data ke posisi kosong ------------------------------------//
    private void bersih_pesanan() {
        this.txt_idjenis.setText("");
        this.txtHargaLaundry.setText("");
        this.txtJumlah.setText("");
        this.txtTotalHarga.setText("");
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
            java.util.logging.Logger.getLogger(FormTransaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FormTransaksi().setVisible(true);
            }
        });
    }
    private javax.swing.table.DefaultTableModel tblModel = getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel tblModel1 = getDefaultTabelModel1();
    private javax.swing.table.DefaultTableModel tblModel2 = getDefaultTabelModel2();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelesai;
    private javax.swing.JButton btn_bayar;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox cbJenisLaundry;
    private javax.swing.JComboBox cbKet;
    private javax.swing.JComboBox cbNamaPelangan;
    private javax.swing.JComboBox cbPengerjaan;
    private javax.swing.JComboBox cbStatusPembayaran;
    private javax.swing.JTextField compare;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kapasitas_meja;
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
    public static final transient javax.swing.JTextField txtBiayalaundry = new javax.swing.JTextField();
    private javax.swing.JTextField txtCari;
    public static final transient javax.swing.JTextField txtHargaLaundry = new javax.swing.JTextField();
    private javax.swing.JTextField txtIdTransaksi;
    public static final transient javax.swing.JTextField txtJumlah = new javax.swing.JTextField();
    private javax.swing.JTextField txtStatusMember;
    public static final transient javax.swing.JTextField txtTanggalMasuk = new javax.swing.JTextField();
    private com.toedter.calendar.JDateChooser txtTanggalSelesai;
    public static final transient javax.swing.JTextField txtTotalHarga = new javax.swing.JTextField();
    private javax.swing.JTextField txt_akhir_s;
    public static final transient javax.swing.JTextField txt_id_trans = new javax.swing.JTextField();
    public static final transient javax.swing.JTextField txt_idjenis = new javax.swing.JTextField();
    private javax.swing.JTextField txt_status;
    public static final transient javax.swing.JTextField txt_total_update = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}

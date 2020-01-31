/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Database;
import model.Login;

/**
 *
 * @author User
 */
public class FormBelanja extends javax.swing.JFrame {

    private DefaultTableModel DftTblModel;

    /**
     * Creates new form FormStokBarang
     */
    public FormBelanja() {
        initComponents();
        Refresh();
        setLocationRelativeTo(this);
        String a = Login.getUsername();
        IdBelanja();
    }

    private void Refresh() {
        ShowKategori();
        txtJumlahBarang.setText("0");
        TampilData();
        ShowNama();
    }

    private void ShowKategori() {
        java.sql.Connection conn = new Database().getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select *from kategori");
            while (res.next()) {
                cbKategori.addItem(res.getString("kategori"));
            }
        } catch (SQLException ex) {

        }
    }

    private void ShowNama() {
        java.sql.Connection conn = new Database().getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select *from stok");
            while (res.next()) {
                cbNamaBarang.addItem(res.getString("nama"));
            }
        } catch (SQLException ex) {

        }
    }

    private void IdBelanja() {
        int byk_data;
        byk_data = 0;
        try {
            java.sql.Connection conn = new Database().getConnection();

            String sql = "select * from belanja order by Idbelanja";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            rs.last();
            byk_data = rs.getRow();
            if (byk_data == 0) {
                this.txtIdBelanja.setText("S001");
            } else {
                String id = rs.getString(1);
                int n = Integer.parseInt(id.substring(1, 4)) + 1;
                String nomor = "";
                for (int i = 1; i <= 2 - String.valueOf(n).length(); i++) {
                    nomor = nomor + "00";
                }
                nomor = "S" + nomor + String.valueOf(n);
                this.txtIdBelanja.setText(nomor);
            }
        } catch (Exception e) {
            System.out.println("Error auto increment userID : " + e + "\n");
        }
    }

    public void TampilData() {
        DftTblModel = new DefaultTableModel();
        DftTblModel.addColumn("ID BELANJA");
        DftTblModel.addColumn("TANGGAL");
        DftTblModel.addColumn("NAMA");
        DftTblModel.addColumn("KATEGORI");
        DftTblModel.addColumn("JUMLAH");
        DftTblModel.addColumn("HARGA");
        DftTblModel.addColumn("TOTAL");
        jTable1.setModel(DftTblModel);
        java.sql.Connection conn = new Database().getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            String SQL = " select *from belanja";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel.addRow(new Object[]{
                    res.getString("Idbelanja"),
                    res.getString("tanggal"),
                    res.getString("nama"),
                    res.getString("kategori"),
                    res.getString("jumlah"),
                    res.getString("harga"),
                    res.getString("total")
                });
            }
        } catch (Exception e) {
        }

    }

    private void TambahStok() {
        int stokawal = Integer.parseInt(lblStok.getText());
        int stoktambah = Integer.parseInt(txtJumlahBarang.getText());
        int totalSemua = stokawal + stoktambah;
        lblStok.setText(String.valueOf(totalSemua));
    }

    private void UpdateSTok() {
        TambahStok();
        java.sql.Connection conn = new Database().getConnection();
        try {
            java.sql.PreparedStatement stmt = conn.prepareStatement("update stok set qty=? where nama=? ");
            try {
                stmt.setString(1, lblStok.getText());
                stmt.setString(2, cbNamaBarang.getSelectedItem().toString());
                stmt.executeUpdate();
                Refresh();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "GAGAL DI UPDATE !!!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {

        }
    }

    private void Hitung() {
        int jumlah = Integer.parseInt(txtJumlahBarang.getText());
        int harga = Integer.parseInt(txtHargaBarang.getText());
        int total = jumlah * harga;
        txtTotal.setText(String.valueOf(total));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblStok = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbKategori = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbSatuan = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtJumlahBarang = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtHargaBarang = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtIdBelanja = new javax.swing.JTextField();
        cbNamaBarang = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        txtTanggalBelanja = new com.toedter.calendar.JDateChooser();
        btnSave2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 0, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM BELANJA");

        lblStok.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblStok))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 0, 51));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NAMA BARANG");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("KATEGORI BARANG");

        cbKategori.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SATUAN");

        cbSatuan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbSatuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SACHET", "LITER" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("JUMLAH BARANG");

        txtJumlahBarang.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtJumlahBarang.setText("0");
        txtJumlahBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahBarangActionPerformed(evt);
            }
        });
        txtJumlahBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahBarangKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahBarangKeyTyped(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CARI BARANG");

        txtCari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("HARGA BARANG");

        txtHargaBarang.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtHargaBarang.setText("0");
        txtHargaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHargaBarangKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHargaBarangKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("TOTAL");

        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");
        txtTotal.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ID BELANJA");

        txtIdBelanja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdBelanja.setEnabled(false);

        cbNamaBarang.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cbNamaBarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbNamaBarangItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TANGGAL");

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        txtTanggalBelanja.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTanggalBelanjaPropertyChange(evt);
            }
        });

        btnSave2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSave2.setText("EXIT");
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel11)
                                        .addGap(218, 218, 218))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdBelanja)
                                    .addComponent(cbNamaBarang, 0, 187, Short.MAX_VALUE)
                                    .addComponent(txtTanggalBelanja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtHargaBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                                .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel6))
                                                .addGap(157, 157, 157)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtJumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cbSatuan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(btnSave2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(txtTanggalBelanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtIdBelanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtJumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnUpdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        java.sql.Connection conn = new Database().getConnection();
        try {
            java.sql.PreparedStatement stmt = conn.prepareStatement("update barang set nama=?,kategori=?,satuan=?,qty=? where Id=? ");
            try {
                //stmt.setString(1,txtNamaBarang.getText());
                stmt.setString(2, cbKategori.getSelectedItem().toString());
                stmt.setString(3, cbSatuan.getSelectedItem().toString());
                stmt.setString(4, txtJumlahBarang.getText());
                //stmt.setString(5,txtIdBarang.getText());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "DI UPDATE", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                Refresh();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "GAGAL DI UPDATE !!!", "Pesan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();
        //txtIdBarang.setText(DftTblModel.getValueAt(baris,0).toString());
        //txtNamaBarang.setText(DftTblModel.getValueAt(baris,1).toString());
        cbKategori.setSelectedItem(DftTblModel.getValueAt(baris, 2).toString());
        cbSatuan.setSelectedItem(DftTblModel.getValueAt(baris, 3).toString());
        txtJumlahBarang.setText(DftTblModel.getValueAt(baris, 4).toString());
        btnUpdate.setEnabled(true);
        btnSave.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            java.sql.Connection conn = new Database().getConnection();
            int ok = JOptionPane.showConfirmDialog(null, "Are You Sure ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (ok == 0) {
                try {
                    String SQL = "delete from belanja where Id ='" + txtIdBelanja.getText() + "'";
                    java.sql.PreparedStatement stmt = conn.prepareStatement(SQL);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Delete Successfull");
                    Refresh();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Delete Failed ");
                }
            } else {
                Refresh();
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
        DftTblModel = new DefaultTableModel();
        DftTblModel.addColumn("ID BARANG");
        DftTblModel.addColumn("NAMA BARANG");
        DftTblModel.addColumn("KATEGORI");
        DftTblModel.addColumn("SATUAN");
        DftTblModel.addColumn("JUMLAH");
        jTable1.setModel(DftTblModel);
        java.sql.Connection conn = new Database().getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            String SQL = " select*from stok where nama like '%" + txtCari.getText() + "%'";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel.addRow(new Object[]{
                    res.getString("Id"),
                    res.getString("nama"),
                    res.getString("kategori"),
                    res.getString("satuan"),
                    res.getString("qty")
                });
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_txtCariKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here: 

        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = spf.format(txtTanggalBelanja.getDate());

        if (txtTanggalBelanja.getDate().equals("")) {
            JOptionPane.showMessageDialog(null, "Isi Field Tanggal !");
            txtTanggalBelanja.requestFocus();
        } else if (txtJumlahBarang.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Jumlah Barang Kosong !");
            txtJumlahBarang.requestFocus();
        } else {
            java.sql.Connection conn = new Database().getConnection();
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("insert into belanja (Idbelanja, tanggal , nama, kategori, jumlah, harga, total) values (?,?,?,?,?,?,?)");
                try {
                    stmt.setString(1, txtIdBelanja.getText());
                    stmt.setString(2, tgl);
                    stmt.setString(3, cbNamaBarang.getSelectedItem().toString());
                    stmt.setString(4, cbKategori.getSelectedItem().toString());
                    stmt.setString(5, txtJumlahBarang.getText());
                    stmt.setString(6, txtHargaBarang.getText());
                    stmt.setString(7, txtTotal.getText());
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "MENYIMPAN DATA", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                    UpdateSTok();
                    Refresh();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "GAGAL MENYIMPAN DATA !!!", "Pesan", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cbNamaBarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNamaBarangItemStateChanged
        // TODO add your handling code here:
        int jumlah = Integer.parseInt(txtJumlahBarang.getText());

        java.sql.Connection conn = new Database().getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select *from stok where nama = '" + cbNamaBarang.getSelectedItem() + "'");
            while (res.next()) {
                int jumlahStok = res.getInt("qty");
                lblStok.setText(String.valueOf(jumlahStok));
            }
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_cbNamaBarangItemStateChanged

    private void txtHargaBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaBarangKeyReleased
        // TODO add your handling code here:
        Hitung();
    }//GEN-LAST:event_txtHargaBarangKeyReleased

    private void txtTanggalBelanjaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTanggalBelanjaPropertyChange
        // TODO add your handling code here:
        if (txtTanggalBelanja.getDate() != null) {
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = spf.format(txtTanggalBelanja.getDate());
        }
    }//GEN-LAST:event_txtTanggalBelanjaPropertyChange

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void txtJumlahBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahBarangKeyReleased
        // TODO add your handling code here:
        char Digit = evt.getKeyChar();
        if (!Character.isDigit(Digit)) {
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_txtJumlahBarangKeyReleased

    private void txtJumlahBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahBarangKeyTyped
        // TODO add your handling code here:
        char Digit = evt.getKeyChar();
        if (!Character.isDigit(Digit)) {
            getToolkit().beep();
            evt.consume();
        }


    }//GEN-LAST:event_txtJumlahBarangKeyTyped

    private void txtHargaBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaBarangKeyTyped
        // TODO add your handling code here:
        char Digit = evt.getKeyChar();
        if (!Character.isDigit(Digit)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtHargaBarangKeyTyped

    private void txtJumlahBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahBarangActionPerformed

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
            java.util.logging.Logger.getLogger(FormBelanja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBelanja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBelanja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBelanja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBelanja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cbKategori;
    private javax.swing.JComboBox cbNamaBarang;
    private javax.swing.JComboBox cbSatuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblStok;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHargaBarang;
    private javax.swing.JTextField txtIdBelanja;
    private javax.swing.JTextField txtJumlahBarang;
    private com.toedter.calendar.JDateChooser txtTanggalBelanja;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

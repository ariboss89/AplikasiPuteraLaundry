/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import koneksi.Database;

/**
 *
 * @author Red Dragonize
 */
public class FormJenisCuci extends javax.swing.JFrame {

Database dbsetting;//---1
String driver, database, user, pass, userlogin;//--2
    /**
     * Creates new form FORM5_MENURESTO
     */
//    public TableCellRenderer kanan = new RenderingKanan();
//    public TableCellRenderer tengah = new RenderingTengah();    

    public FormJenisCuci() {
        initComponents();
        
        Database kn = new Database();
        driver= kn.DBDriver;
        database= kn.DBDatabase;
        user = kn.DBUsername;
        pass = kn.DBPassword;
       
        SetEditOff();
        showTable();
        
//        Tabel(table, new int[]{70,300,90,90});
//        table.getColumnModel().getColumn(0).setCellRenderer(tengah);
//        table.getColumnModel().getColumn(2).setCellRenderer(tengah);
//        table.getColumnModel().getColumn(3).setCellRenderer(kanan);
        
        //------------------------- Form Posisi Tengah --------------------------//   
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width-getWidth())/2;
        int y = (dim.height-getHeight())/2;
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

        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_jenis = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        btn_new = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jenis Cuci");
        setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 0, 51));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("ID Jenis");

        txt_id.setEditable(false);
        txt_id.setBackground(new java.awt.Color(204, 204, 204));
        txt_id.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txt_id.setNextFocusableComponent(txt_jenis);

        txt_jenis.setBackground(new java.awt.Color(204, 204, 204));
        txt_jenis.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txt_jenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jenisKeyTyped(evt);
            }
        });

        txt_harga.setBackground(new java.awt.Color(204, 204, 204));
        txt_harga.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txt_harga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_hargaKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Jenis Cucian");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Harga");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("INPUT DATA");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("/Kg atau Satuan");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_jenis, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(txt_harga))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(204, 0, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("JENIS CUCIAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 0, 51));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Cari");

        txt_cari.setBackground(new java.awt.Color(204, 204, 204));
        txt_cari.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txt_cari.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_cariCaretUpdate(evt);
            }
        });

        btn_new.setBackground(new java.awt.Color(162, 138, 138));
        btn_new.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new.png"))); // NOI18N
        btn_new.setText("NEW");
        btn_new.setOpaque(false);
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        btn_save.setBackground(new java.awt.Color(162, 138, 138));
        btn_save.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        btn_save.setText("SAVE");
        btn_save.setOpaque(false);
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(162, 138, 138));
        btn_edit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        btn_edit.setText("EDIT");
        btn_edit.setOpaque(false);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(162, 138, 138));
        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.setOpaque(false);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_refresh.setBackground(new java.awt.Color(162, 138, 138));
        btn_refresh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        btn_refresh.setText("REFRESH");
        btn_refresh.setOpaque(false);
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        btn_exit.setBackground(new java.awt.Color(162, 138, 138));
        btn_exit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit (1).png"))); // NOI18N
        btn_exit.setText("EXIT");
        btn_exit.setOpaque(false);
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_new)
                        .addGap(24, 24, 24)
                        .addComponent(btn_save)
                        .addGap(18, 18, 18)
                        .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete)
                        .addGap(18, 18, 18)
                        .addComponent(btn_refresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(btn_exit)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_new)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_save)
                        .addComponent(btn_edit)
                        .addComponent(btn_delete)
                        .addComponent(btn_refresh)
                        .addComponent(btn_exit)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Tabel(javax.swing.JTable tb, int lebar[] ) {
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int kolom=tb.getColumnCount();
        for(int i=0;i < kolom;i++) {
        javax.swing.table.TableColumn tbc=tb.getColumnModel().getColumn(i);
        tbc.setPreferredWidth(lebar[i]);
        tb.setRowHeight(17);
        }
    }
    
    //--------------------------- Database Showtabel -------------------------//
    public  void showTable(){
       table.setModel(tblModel); 
       String data[]= new String [3];
       String stat = "";
       try {
           Class.forName(driver);
           try(Connection kon = DriverManager.getConnection(database,user,pass);
              Statement stt = kon.createStatement()){
              String SQL ="Select * From jenis where id_jenis like '%"+txt_cari.getText()+"%' or jenis like '%"+txt_cari.getText()+"%' or harga like '%"+txt_cari.getText()+"%'  order by id_jenis desc";
              ResultSet res = stt.executeQuery(SQL);
              while(res.next()){
                  data[0]= res.getString(1);
                  data[1]= res.getString(2);
                  data[2]= res.getString(3);
                  tblModel.addRow(data);
             }
              res.close();
              stt.close();
             }
       } catch(Exception exc){
          System.err.println(exc.getMessage());
       }
 }
    //------------------------------------------------------------------------//
    
    //-------------------------- Default Tabel -------------------------------//
    private javax.swing.table.DefaultTableModel getDefaultTabelModel(){
        return new javax.swing.table.DefaultTableModel(
                new Object [][]{},
                new String [] {"ID","Jenis","Harga"}){
                public boolean isCellEditable(int rowIndex, int columnIndex){
                    return false;
            }
                };
    }
    
    
    //---------------------------- NO USER OTOMATIS --------------------------//  
    private void id_jenis()
    {
        int byk_data;
        byk_data = 0;
        try
        {
            Connection kon = DriverManager.getConnection(database,user,pass);
            String sql = "select * from jenis order by id_jenis";
            Statement stat = kon.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            rs.last();
            byk_data = rs.getRow();
            if (byk_data == 0)
                {
                this.txt_id.setText("C-01");
                }
            else
                {
                String id = rs.getString(1);
                int n = Integer.parseInt(id.substring(2, 4)) + 1;
                String nomor = "";
                for(int i = 1; i <= 2 - String.valueOf(n).length(); i++)
                {
                    nomor = nomor + "0";
                }
                nomor = "C-" + nomor + String.valueOf(n);
                this.txt_id.setText(nomor);
                }
        }
        catch (Exception e)
        {
        System.out.println("Error auto increment userID : " + e + "\n");
        }
        }
    
    //------------------------------------------------------------------------//
    
    // Membersihkan Data ke posisi kosong ------------------------------------//
      private void bersih(){
      this.txt_id.setText(""); 
      this.txt_jenis.setText("");
      this.txt_harga.setText("");
      this.txt_cari.setText("");
       }
    
     //-----------------------------------------------------------------------//
      
      //------------------------------ Set Edit OFF ---------------------------//
        public void SetEditOff(){
        txt_id.setEnabled(false);
        txt_jenis.setEnabled(false);
        txt_harga.setEnabled(false);
        btn_save.setEnabled(false);
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
    }
    //------------------------------------------------------------------------//
    
    //------------------------------ Set Edit ON -----------------------------//
        public void SetEditOn(){
        txt_id.setEnabled(true);
        txt_jenis.setEnabled(true);
        txt_harga.setEnabled(true);
        btn_save.setEnabled(true);
        btn_edit.setEnabled(true);
        btn_delete.setEnabled(true);
    }
    //------------------------------------------------------------------------//
        
    //--------------------------- Input Valid --------------------------------//
    private boolean inputValid() {
        boolean result;
        if ((txt_id.getText().equals("")) || (txt_jenis.getText().equals(""))|| (txt_harga.getText().equals("")) ) {
            JOptionPane.showMessageDialog(null, "Isi data yang dibutuhkan");
            result = false;
        } else {
            result = true;
        }
        return result;

    }
    //------------------------------------------------------------------------//
    
        
    // Refresh Tabel & kolom - kolom -----------------------------------------//
    public void RefreshTabel(){
    int brs = tblModel.getRowCount();
    for (int i=0;i<brs;i++){
    tblModel.removeRow(0);
  }
  }
    //------------------------------------------------------------------------//
    
    int row = 0;
    private void Tampil (){
      row=table.getSelectedRow();
      txt_id.setText(table.getValueAt(row,0).toString());
      txt_jenis.setText(table.getValueAt(row,1).toString());
      txt_harga.setText(table.getValueAt(row,2).toString());
          }
    
    
    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        bersih();
        dispose();
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        bersih();
        SetEditOff();
        btn_new.setEnabled(true);
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 2) {
            Tampil();
            txt_id.setEnabled(false);
            txt_jenis.setEnabled(true);
            txt_harga.setEnabled(true);
            btn_edit.setEnabled(true);
            btn_delete.setEnabled(true);
            btn_new.setEnabled(false);
            btn_save.setEnabled(false);
        }
        
    }//GEN-LAST:event_tableMouseClicked

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
        bersih();
        id_jenis();
        SetEditOn();
        txt_jenis.requestFocus();
        btn_edit.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_save.setEnabled(true);
    }//GEN-LAST:event_btn_newActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        if (inputValid() == true) {
        try{
        Class.forName(driver);
        Connection kon=DriverManager.getConnection(database,user,pass);
        Statement stt=kon.createStatement();
        String SQL = "insert into jenis values('" + txt_id.getText()
                        + "','" + txt_jenis.getText()
                        + "','" + txt_harga.getText() +"')";
                stt.executeUpdate(SQL);
                stt.close();
                kon.close();
//                JOptionPane.showMessageDialog(null, "Data Tersimpan ");
    }catch(Exception exc) {
        JOptionPane.showMessageDialog(null, "Peringatan !!! Terjadi Duplikasi Data ");
        System.err.println(exc.getMessage());
    }
        }
        
        RefreshTabel();
        bersih();
        showTable();
        SetEditOff();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        if (inputValid() == true) {

            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "update jenis set jenis='" + txt_jenis.getText()
                        + "',harga='" + txt_harga.getText()
                        + "' where id_jenis='" + txt_id.getText() + "'";
                stt.executeUpdate(SQL);
                stt.close();
                kon.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null, "Peringatan !!! Cek Kembali Data");
                System.err.println(exc.getMessage());
            }
        }
        RefreshTabel();
        bersih();
        showTable();
        SetEditOff();
        btn_new.setEnabled(true);
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "Delete from jenis where id_jenis ='" + txt_id.getText() + "' ";
                stt.executeUpdate(SQL);
                stt.close();
                kon.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            } catch (Exception exc) {
                System.err.println(exc.getMessage());
            }
            RefreshTabel();
            bersih();
            showTable();
            SetEditOff();
            btn_new.setEnabled(true);
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void txt_cariCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_cariCaretUpdate
        RefreshTabel();
        showTable();
    }//GEN-LAST:event_txt_cariCaretUpdate

    private void txt_hargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hargaKeyTyped
        char vchar = evt.getKeyChar();
        if(!(Character.isDigit(vchar))
            || (vchar == KeyEvent.VK_BACK_SPACE)
            || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
        }
    }//GEN-LAST:event_txt_hargaKeyTyped

    private void txt_jenisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jenisKeyTyped
        char vchar = evt.getKeyChar();
        if(!(Character.isAlphabetic(vchar))
            || (vchar == KeyEvent.VK_BACK_SPACE)
            || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
        }
    }//GEN-LAST:event_txt_jenisKeyTyped

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
            java.util.logging.Logger.getLogger(FormJenisCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormJenisCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormJenisCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormJenisCuci.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FormJenisCuci().setVisible(true);
            }
        });
    }
    private javax.swing.table.DefaultTableModel tblModel = getDefaultTabelModel();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_jenis;
    // End of variables declaration//GEN-END:variables
}
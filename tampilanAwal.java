/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author ASUS ROG
 */
public class tampilanAwal extends javax.swing.JFrame {

    /**
     * Creates new form tampilanAwal
     */
        DefaultTableModel tabel=new DefaultTableModel();
        String nama,jabatan;
        int nip,gajipokok,potongan,tunjangan,bonus,gajibersih;
        
    public tampilanAwal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        view_table();
        txtJabatan.setSelectedIndex(0);
    }
    
    private void view_table()
        {
        tabel.addColumn("Nama");
        tabel.addColumn("NIP");
        tabel.addColumn("Jabatan");
        tabel.addColumn("Gaji Pokok");
        tabel.addColumn("Potongan");
        tabel.addColumn("Tunjangan");
        tabel.addColumn("Bonus");
        tabel.addColumn("Gaji Bersih");
        jTable1.setModel(tabel);
        }
     private void simpan(){
        nama=txtNama.getText();
        jabatan=(String)txtJabatan.getSelectedItem();
        nip=Integer.parseInt(txtNIP.getText());
        gajipokok=Integer.parseInt(txtGajiPokok.getText());
        potongan=Integer.parseInt(txtPotongan.getText());
        tunjangan=Integer.parseInt(txtTunjangan.getText());
        bonus=Integer.parseInt(txtBonus.getText());
        gajibersih=(gajipokok-potongan)+tunjangan+bonus;
        txtGajiBersih.setText(Integer.toString(gajibersih));
    }
     
     private void write_database(){
        try{
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_gaji", "root", "");
            Statement statement = (Statement) koneksi.createStatement();
            statement.executeUpdate("insert into pegawai(NIP,Nama,Jabatan,Gaji_Pokok,Potongan,Tunjangan,Bonus,Gaji_Bersih) VALUES('"
                    +nip+"','"+nama+"','"+jabatan+"','"+gajipokok+"','"+potongan+"','"+tunjangan+"','"+bonus+"','"+gajibersih+"');");
            statement.close();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
        }
    }
     
     public void view(){
        try{
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_gaji","root","");
            PreparedStatement st =koneksi.prepareStatement("select * from data_gaji.pegawai;");
            ResultSet rs = st.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            koneksi.close();
        }
        catch(Exception t){
            JOptionPane.showMessageDialog(null,"Data Base Connection Failed!!");
        }
    }
     
     private void quasi_view(){
        int row;
        row=jTable1.getSelectedRow();
        if(row>=0){
            txtNama.setText((String) jTable1.getValueAt(row, 1));
            txtNIP.setText(Integer.toString((int) jTable1.getValueAt(row, 0)));
            txtJabatan.setSelectedItem((String) jTable1.getValueAt(row, 2));
            txtPotongan.setText(Long.toString((long) jTable1.getValueAt(row, 4)));
            txtTunjangan.setText(Long.toString((long) jTable1.getValueAt(row, 5)));
            txtBonus.setText(Long.toString((long) jTable1.getValueAt(row, 6)));
            txtGajiBersih.setText(Long.toString((long) jTable1.getValueAt(row, 7)));
        }
    }
     
     private void delete(){
        simpan();
        String sql="DELETE FROM Pegawai WHERE NIP='"+nip+"'";
        try{
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_gaji","root","");
            Statement statement =(Statement)koneksi.createStatement();
            statement.executeUpdate(sql);
            view();
            statement.close();
            JOptionPane.showMessageDialog(null,"Record Deleted !");
        }
        catch(Exception t){
            JOptionPane.showMessageDialog(null,"Deleted Record Failed !!!!");
        }
    }
    private void jabatanselected(){
           if(txtJabatan.getSelectedItem().equals("Programer")){
              txtGajiPokok.setText("5000000");
           }
           else if(txtJabatan.getSelectedItem().equals("System Analist")){
               txtGajiPokok.setText("6000000");
           }
           else if(txtJabatan.getSelectedItem().equals("designer")){
               txtGajiPokok.setText("3000000");
           }
           else if(txtJabatan.getSelectedItem().equals("DB Design")){
               txtGajiPokok.setText("4000000");
           }
           else{
               txtGajiPokok.setText("4500000");
           }
       }
    
        private void update(){
            simpan();
            String sql="UPDATE table_pegawai SET Nama= "+nama+",Jabatan= "+jabatan
                    +",Gaji_Pokok="+gajipokok+",Potongan="+potongan+",Tunjangan="+
                    tunjangan+",Bonus="+bonus+",Gaji_Bersih="+gajibersih+" WHERE NIP ="+nip+" ";
            try{
                Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_gaji","root","");
                Statement statement =(Statement)koneksi.createStatement();
                statement.executeUpdate(sql);
                view();
                statement.close();
                JOptionPane.showMessageDialog(null,"Data Base Updated !!");
            }
            catch(Exception t){
                JOptionPane.showMessageDialog(null,"Data Base Update failed !!!!");
            }
        }
        
        private void clear(){
        txtNama.setText("");
        txtNIP.setText("");
        txtPotongan.setText("");
        txtTunjangan.setText("");
        txtBonus.setText("");
        txtGajiBersih.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtNIP = new javax.swing.JTextField();
        txtGajiPokok = new javax.swing.JTextField();
        txtJabatan = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPotongan = new javax.swing.JTextField();
        txtTunjangan = new javax.swing.JTextField();
        txtBonus = new javax.swing.JTextField();
        txtGajiBersih = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        simpan = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        clear = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Gaji", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 51, 255));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jPanel2.setForeground(new java.awt.Color(51, 51, 255));

        jLabel1.setText("Nama");

        jLabel2.setText("NIP");

        jLabel3.setText("Jabatan");

        jLabel4.setText("Gaji Pokok");

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        txtNIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNIPActionPerformed(evt);
            }
        });

        txtJabatan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Programer", "System Analyst", "designer", "DB designer" }));
        txtJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJabatanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGajiPokok))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNIP, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtGajiPokok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));

        jLabel5.setText("Potongan");

        jLabel6.setText("Tunjangan");

        jLabel7.setText("Bonus");

        jLabel8.setText("Gaji Bersih");

        txtPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPotonganActionPerformed(evt);
            }
        });

        txtTunjangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTunjanganActionPerformed(evt);
            }
        });

        txtBonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBonusActionPerformed(evt);
            }
        });

        txtGajiBersih.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtGajiBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGajiBersihActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGajiBersih))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBonus, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(txtTunjangan))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTunjangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtGajiBersih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

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
        jScrollPane1.setViewportView(jTable1);

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        Delete.setText("Hapus");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        jButton4.setText("View");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(simpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Delete)
                        .addGap(18, 18, 18)
                        .addComponent(Update)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clear))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJabatanActionPerformed
        // Jabatan
        jabatanselected();
        if(txtNama.getText().equals("") || txtNIP.getText().equals("")
                || txtGajiPokok.getText().equals("") || txtPotongan.getText().equals("")
                || txtTunjangan.getText().equals("") || txtBonus.getText().equals("")){
        }
        else{
            simpan();
        }
    }//GEN-LAST:event_txtJabatanActionPerformed

    private void txtPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPotonganActionPerformed
        // Potongan
           if(txtNama.getText().equals("") || txtNIP.getText().equals("")
                || txtGajiPokok.getText().equals("") || txtPotongan.getText().equals("")
                || txtTunjangan.getText().equals("") || txtBonus.getText().equals("")){
        }
        else{
            simpan();
        
        }
    }//GEN-LAST:event_txtPotonganActionPerformed

    private void txtGajiBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGajiBersihActionPerformed
        // Gaji Bersih
        
    }//GEN-LAST:event_txtGajiBersihActionPerformed

    private void txtBonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBonusActionPerformed
        // Bonus
        if(txtNama.getText().equals("")||txtNIP.getText().equals("")
                ||txtGajiPokok.getText().equals("")||txtPotongan.getText().equals("")
                ||txtTunjangan.getText().equals("")||txtBonus.getText().equals(""))
        {}
        else{
            simpan();
        }
    }//GEN-LAST:event_txtBonusActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // Simpan
        if(txtNama.getText().equals("") || txtNIP.getText().equals("")
                || txtGajiPokok.getText().equals("") || txtPotongan.getText().equals("")
                || txtTunjangan.getText().equals("") || txtBonus.getText().equals("")
                || txtGajiBersih.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Form data tidak boleh kosong");
        }
        else{
            write_database();
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        //Delete
        int cek = jTable1.getSelectedRow();
        if(cek<0){
            JOptionPane.showMessageDialog(null,"Tidak ada data di select");
        }
        else{
            delete();
            clear();
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        //Update
        int cek = jTable1.getSelectedRow();
        if(cek<0){
            JOptionPane.showMessageDialog(null,"Tidak ada data di select");
        }
        else{
            update();
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        view ();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        quasi_view();
    }//GEN-LAST:event_jTable1MouseClicked

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // Clear
        clear ();
    }//GEN-LAST:event_clearActionPerformed

    private void txtTunjanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTunjanganActionPerformed
        // Tunjangan
         if(txtNama.getText().equals("")||txtNIP.getText().equals("")
                ||txtGajiPokok.getText().equals("")||txtPotongan.getText().equals("")
                ||txtTunjangan.getText().equals("")||txtBonus.getText().equals(""))
        {}
        else{
            simpan();
        }
    }//GEN-LAST:event_txtTunjanganActionPerformed

    private void txtNIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNIPActionPerformed
        //NIP
        if(txtNama.getText().equals("")||txtNIP.getText().equals("")
                ||txtGajiPokok.getText().equals("")||txtPotongan.getText().equals("")
                ||txtTunjangan.getText().equals("")||txtBonus.getText().equals(""))
        {}
        else{
            simpan();
        }
    }//GEN-LAST:event_txtNIPActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        //Nama
        if(txtNama.getText().equals("")||txtNIP.getText().equals("")
                ||txtGajiPokok.getText().equals("")||txtPotongan.getText().equals("")
                ||txtTunjangan.getText().equals("")||txtBonus.getText().equals(""))
        {}
        else{
            simpan();
        }
    }//GEN-LAST:event_txtNamaActionPerformed

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
            java.util.logging.Logger.getLogger(tampilanAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tampilanAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tampilanAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tampilanAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tampilanAwal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton Update;
    private javax.swing.JButton clear;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField txtBonus;
    private javax.swing.JTextField txtGajiBersih;
    private javax.swing.JTextField txtGajiPokok;
    private javax.swing.JComboBox<String> txtJabatan;
    private javax.swing.JTextField txtNIP;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtPotongan;
    private javax.swing.JTextField txtTunjangan;
    // End of variables declaration//GEN-END:variables
}

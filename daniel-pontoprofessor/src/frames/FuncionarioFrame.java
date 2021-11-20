/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import dao.AuditoriaDao;
import dao.CargoDao;
import dao.FuncionarioDao;
import entidades.Cargos;
import entidades.Funcionarios;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carleandro
 */
public class FuncionarioFrame extends javax.swing.JInternalFrame {
    
    /**
     * Creates new form ProfessorFrame
     */
    public FuncionarioFrame() {
        initComponents();
         addProfessorTabela();
          jTable1.getColumnModel().getColumn(0).setMinWidth(80);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(0).setWidth(80);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            desabilitar(true, true, false, false, false, false);
            preecherCargos();
            jTable1.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                  @Override public void valueChanged(ListSelectionEvent e) {
                    eventoTabela();
                  }
             });
    }
    
    private void preecherCargos(){
      List<Cargos> listCargos = new CargoDao().lista();
      Vector<Cargos> lista = new Vector<Cargos>();
      for (Cargos c : listCargos) {     
        jComboCargos.addItem(c.getNome());
      }
    }
     private void eventoTabela(){
         int linha = jTable1.getSelectedRow();
         if(linha != -1){
            desabilitar(true, true, false, true, true, false);
            jTextCod.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            jTextNome.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            jTextEmail.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            jTextCpf.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
             jComboCargos.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
            jComboTipo.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
             jComboStatus.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString());
            
         }else{
             limparCampus();
         }
    }
    
    private boolean verificarCampus(){
        if(jTextNome.getText().equals("")){
            jTextNome.requestFocus();
            return false;
        }else if(jTextEmail.getText().equals("")){
            jTextEmail.requestFocus();
            return false;
        }else if(jTextCpf.getText().equals("")){
            jTextCpf.requestFocus();
            return false;
        }
        return true;
    }
    
    private Funcionarios verificarEmailCpf(){
        return new FuncionarioDao().getFuncionario(jTextEmail.getText(), jTextCpf.getText());
    }
    
    private void desabilitar(boolean pesquisar, boolean novo,boolean salvar, 
            boolean alterar, boolean excluir, boolean cancelar){
        jBtnPesquisar1.setEnabled(pesquisar);
        jBtnNovo1.setEnabled(novo);
        jBtnSalvar1.setEnabled(salvar);
        jBtnAlterar1.setEnabled(alterar);
        jBtnExcluir1.setEnabled(excluir);
        jBtnCancelar1.setEnabled(cancelar);
        
    }
    
    private void limparCampus(){
        jTextNome.setText("");
        jTextEmail.setText("");
        jTextCod.setText("");
        jTextCpf.setText("");
    }
    
    private void addProfessorTabela(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0);
        List<Object[]> lista =  new FuncionarioDao().listaObject();
        for (Object[] object : lista) {
            Funcionarios u =(Funcionarios) object[0];
            Cargos c =(Cargos) object[1];
            Object[] row = { u.getId(), u.getNome(), u.getEmail(), u.getCpf(), c.getNome(), u.getTipo(), u.getStatus()};
             model.addRow(row);
        }
        jTable1.setModel(model);
    }
    
    private void pesquisar(){
        String texto = jTextNome.getText();
       DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0);
        List<Object[]> lista =  new FuncionarioDao().listaObject(texto);
        for (Object[] object : lista) {
            Funcionarios u =(Funcionarios) object[0];
            Cargos c =(Cargos) object[1];
            Object[] row = { u.getId(), u.getNome(), u.getEmail(), u.getCpf(), c.getNome(), u.getTipo(), u.getStatus()};
             model.addRow(row);
        }
        jTable1.setModel(model);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextCod = new javax.swing.JTextField();
        jTextNome = new javax.swing.JTextField();
        jTextEmail = new javax.swing.JTextField();
        jTextCpf = new javax.swing.JFormattedTextField();
        jComboTipo = new javax.swing.JComboBox<>();
        jComboStatus = new javax.swing.JComboBox<>();
        jComboCargos = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jBtnSalvar1 = new javax.swing.JButton();
        jBtnPesquisar1 = new javax.swing.JButton();
        jBtnNovo1 = new javax.swing.JButton();
        jBtnAlterar1 = new javax.swing.JButton();
        jBtnExcluir1 = new javax.swing.JButton();
        jBtnCancelar1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setTitle("Funcionário");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("COD");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Nome");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Tipo");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Status");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("CPF");

        jTextCod.setEnabled(false);

        try {
            jTextCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jComboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efetivo", "Contratado" }));

        jComboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Desativado" }));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Cargo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextCod, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel3)
                                .addGap(141, 141, 141)
                                .addComponent(jLabel7))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboCargos, 0, 351, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(217, 217, 217)
                                    .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnSalvar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/save.png"))); // NOI18N
        jBtnSalvar1.setText("Salvar");
        jBtnSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalvar1ActionPerformed(evt);
            }
        });

        jBtnPesquisar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/pesquisar.png"))); // NOI18N
        jBtnPesquisar1.setText("Pesquisar");
        jBtnPesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisar1ActionPerformed(evt);
            }
        });

        jBtnNovo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/new.png"))); // NOI18N
        jBtnNovo1.setText("Novo");
        jBtnNovo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNovo1ActionPerformed(evt);
            }
        });

        jBtnAlterar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/update.png"))); // NOI18N
        jBtnAlterar1.setText("Alterar");
        jBtnAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAlterar1ActionPerformed(evt);
            }
        });

        jBtnExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/delete.png"))); // NOI18N
        jBtnExcluir1.setText("Excluir");
        jBtnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluir1ActionPerformed(evt);
            }
        });

        jBtnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/cancelar.png"))); // NOI18N
        jBtnCancelar1.setText("Cancelar");
        jBtnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jBtnPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnNovo1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnAlterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jBtnSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnPesquisar1, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addComponent(jBtnAlterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnNovo1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTable1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "Nome", "Email", "CPF", "Cargo", "Tipo", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 214, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(224, 224, 224)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelar1ActionPerformed
        desabilitar(true, true, false, false, false, false);
        limparCampus();
    }//GEN-LAST:event_jBtnCancelar1ActionPerformed

    private void jBtnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluir1ActionPerformed
        desabilitar(true, true, false, false, false, false);
        if(!jTextCod.getText().equals("")){
            Funcionarios professors = new Funcionarios();
            professors.setId(Integer.valueOf(jTextCod.getText()));
            new FuncionarioDao().delete(professors);
            new AuditoriaDao().save(professors, "delete");
        }
        limparCampus();
        addProfessorTabela();
        JOptionPane.showMessageDialog(this, "Excluido com Sucesso!");
    }//GEN-LAST:event_jBtnExcluir1ActionPerformed

    private void jBtnAlterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterar1ActionPerformed
        desabilitar(false, false, true, false, false, true);
    }//GEN-LAST:event_jBtnAlterar1ActionPerformed

    private void jBtnNovo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNovo1ActionPerformed
        desabilitar(false, false, true, false, false, true);
        limparCampus();
        jTextNome.requestFocus();
    }//GEN-LAST:event_jBtnNovo1ActionPerformed

    private void jBtnPesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisar1ActionPerformed
        pesquisar();
        desabilitar(true, true, false, false, false, false);
    }//GEN-LAST:event_jBtnPesquisar1ActionPerformed

    private void jBtnSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalvar1ActionPerformed
        if(verificarCampus()){
            Funcionarios paux = verificarEmailCpf() ;
            if(paux == null || jTextCod.getText().equals((String.valueOf(paux.getId())))){
                Cargos cargos = new CargoDao().getCargo(jComboCargos.getSelectedItem().toString());
                Funcionarios funcionario = new Funcionarios(jTextNome.getText(),jTextCpf.getText(), jTextEmail.getText(),
                        jComboTipo.getSelectedItem().toString(), jComboStatus.getSelectedItem().toString(), cargos);
                if(jTextCod.getText().equals("")){
                    new FuncionarioDao().save(funcionario);
                    new AuditoriaDao().save(funcionario, "save");
                }else{
                    funcionario.setId(Integer.valueOf(jTextCod.getText()));
                    new FuncionarioDao().update(funcionario);
                    new AuditoriaDao().save(funcionario, "update");
                }
                desabilitar(true, true, false, false, false, false);
                limparCampus();
                addProfessorTabela();
                JOptionPane.showMessageDialog(this, "Salvo com Sucesso!");
            }else{
                JOptionPane.showMessageDialog(this, "Email ou CPF já cadastrado!");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Preecha todos os Campos");
        }
    }//GEN-LAST:event_jBtnSalvar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAlterar1;
    private javax.swing.JButton jBtnCancelar1;
    private javax.swing.JButton jBtnExcluir1;
    private javax.swing.JButton jBtnNovo1;
    private javax.swing.JButton jBtnPesquisar1;
    private javax.swing.JButton jBtnSalvar1;
    private javax.swing.JComboBox<String> jComboCargos;
    private javax.swing.JComboBox<String> jComboStatus;
    private javax.swing.JComboBox<String> jComboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextCod;
    private javax.swing.JFormattedTextField jTextCpf;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextNome;
    // End of variables declaration//GEN-END:variables
}

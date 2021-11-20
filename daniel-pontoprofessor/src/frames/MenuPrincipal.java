/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import dao.AuditoriaDao;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.Util;


/**
 *
 * @author carleandro
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    
    private UserFrame userFrame;
    private FuncionarioFrame professorFrame;
    private PontoFrame pontoFrame;
    private PontoCadastroFrame pontoCadastroFrame;
    private RelatorioFrame relatorioFrame;
    private CargoFrame cargoFrame;
    public MenuPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);  
        criardiretorio();
    }
    
    private void criardiretorio(){
        try {
            File dir1 = new File (".");
            String diretorioPdfs= dir1.getCanonicalPath()+Util.pastaPdfs;
            File diretorio = new File(diretorioPdfs);
            excluirDiretorio(diretorio);
            diretorio.mkdirs();
        } catch (IOException ex) {
            System.out.println("Erro:"+ex.getMessage());
        }
    }
    
    private static void excluirDiretorio(File arq){
        if(arq.isDirectory()){
            File[] arquivos = arq.listFiles();
            for(int i=0;i<arquivos.length;i++){
               excluirDiretorio(arquivos[0]);
            }
        }
        arq.delete();
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jBtnProfessor = new javax.swing.JButton();
        jBtnCadastrarPonto = new javax.swing.JButton();
        jBtnUsers = new javax.swing.JButton();
        jBtnPonto = new javax.swing.JButton();
        jBtnRelatorio = new javax.swing.JButton();
        jBtnCargos = new javax.swing.JButton();
        jBtnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ponto do Professor");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(18, 158, 131));

        jBtnProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/professor.png"))); // NOI18N
        jBtnProfessor.setText("Funcionários");
        jBtnProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnProfessorActionPerformed(evt);
            }
        });

        jBtnCadastrarPonto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/ponto.png"))); // NOI18N
        jBtnCadastrarPonto.setText("Cadastrar Ponto");
        jBtnCadastrarPonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCadastrarPontoActionPerformed(evt);
            }
        });

        jBtnUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/user.png"))); // NOI18N
        jBtnUsers.setText("Usuário");
        jBtnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUsersActionPerformed(evt);
            }
        });

        jBtnPonto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/pontomenu.png"))); // NOI18N
        jBtnPonto.setText("Ponto");
        jBtnPonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPontoActionPerformed(evt);
            }
        });

        jBtnRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/pdf.png"))); // NOI18N
        jBtnRelatorio.setText("Relatório");
        jBtnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRelatorioActionPerformed(evt);
            }
        });

        jBtnCargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/cargo.png"))); // NOI18N
        jBtnCargos.setText("Cargos");
        jBtnCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCargosActionPerformed(evt);
            }
        });

        jBtnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/sair.png"))); // NOI18N
        jBtnSair.setText("Sair");
        jBtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnPonto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCadastrarPonto, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jBtnProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnCadastrarPonto, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnPonto, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSairActionPerformed
        new AuditoriaDao().save(null, "logout");
        System.exit(0); 
    }//GEN-LAST:event_jBtnSairActionPerformed

    private void jBtnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUsersActionPerformed
        if(Util.usuarioLogado.getPermissaoroot().equals("Sim")){
            if(userFrame != null){
               userFrame.dispose();
               userFrame = null;
            }
           userFrame = new UserFrame();
           userFrame.setVisible(true);
           jDesktopPane1.add(userFrame);
           userFrame.toFront();
        }else{
            JOptionPane.showMessageDialog(this, "Não tem permissão de Acesso a esse recurso!","Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnUsersActionPerformed

    private void jBtnProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnProfessorActionPerformed
        if(professorFrame != null){
           professorFrame.dispose();
           professorFrame = null;
        }
       professorFrame = new FuncionarioFrame();
       professorFrame.setVisible(true);
       jDesktopPane1.add(professorFrame);
       professorFrame.toFront();
       
    }//GEN-LAST:event_jBtnProfessorActionPerformed

    private void jBtnPontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPontoActionPerformed
        if(pontoFrame != null){
           pontoFrame.dispose();
           pontoFrame = null;
        }
       pontoFrame = new PontoFrame();
       pontoFrame.setVisible(true);
       jDesktopPane1.add(pontoFrame);
       pontoFrame.toFront();
       
    }//GEN-LAST:event_jBtnPontoActionPerformed

    private void jBtnCadastrarPontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCadastrarPontoActionPerformed
        if(pontoCadastroFrame != null){
           pontoCadastroFrame.dispose();
           pontoCadastroFrame = null;
        }
       pontoCadastroFrame = new PontoCadastroFrame();
       pontoCadastroFrame.setVisible(true);
       jDesktopPane1.add(pontoCadastroFrame);
       pontoCadastroFrame.toFront();
     
    }//GEN-LAST:event_jBtnCadastrarPontoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       new AuditoriaDao().save(null, "logout");
    }//GEN-LAST:event_formWindowClosing

    private void jBtnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRelatorioActionPerformed

        if(relatorioFrame != null){
           relatorioFrame.dispose();
           relatorioFrame = null;
        }
       relatorioFrame = new RelatorioFrame();
       relatorioFrame.setVisible(true);
       jDesktopPane1.add(relatorioFrame);
       relatorioFrame.toFront();
    }//GEN-LAST:event_jBtnRelatorioActionPerformed

    private void jBtnCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCargosActionPerformed
        
        if(cargoFrame != null){
           cargoFrame.dispose();
           cargoFrame = null;
        }
       cargoFrame = new CargoFrame();
       cargoFrame.setVisible(true);
       jDesktopPane1.add(cargoFrame);
       cargoFrame.toFront();
    }//GEN-LAST:event_jBtnCargosActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCadastrarPonto;
    private javax.swing.JButton jBtnCargos;
    private javax.swing.JButton jBtnPonto;
    private javax.swing.JButton jBtnProfessor;
    private javax.swing.JButton jBtnRelatorio;
    private javax.swing.JButton jBtnSair;
    private javax.swing.JButton jBtnUsers;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
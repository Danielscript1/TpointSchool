/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import dao.AuditoriaDao;
import dao.PontoDao;
import dao.FuncionarioDao;
import entidades.Pontos;
import entidades.Funcionarios;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.Util;

/**
 *
 * @author carleandro
 */
public class FuncionarioPontoFrame extends javax.swing.JFrame {

    private Dimension dimensao_default;
    private Webcam WEBCAM;
    private boolean executando = true;

    /**
     * /**
     * Creates new form ProfessorPontoFrame
     */
    public FuncionarioPontoFrame() {
        initComponents();
        // TODO add your handling code here:
        INICIALIZA();
        INICIALIZA_VIDEO();

        new Thread() {
            public void run() {
                try {
                    executando = true;
                    FotoWebcam.setText("Iniciando...");//aparece o nome na tela  antes de aparece a imagem na tela
                    WEBCAM.open();//para executar o comando de ligar
                    FotoWebcam.setText("");
                   
                } catch (Exception e) {
                    executando = false;
                    FotoWebcam.setText("WebCam não conectada!");

                }
            }
        }.start();
    }

    private void INICIALIZA() {
        try {
            dimensao_default = WebcamResolution.VGA.getSize();//variavel pegar a resolucao padrao da camera
            WEBCAM = Webcam.getDefault();//pgar a webcam padrao do computador
            WEBCAM.setViewSize(dimensao_default);
//resoluçoes que webcam suporta 
            for (Dimension dimensao : WEBCAM.getViewSizes()) {
                System.out.println("Largura: " + dimensao.getWidth() + " Altuta: " + dimensao.getHeight());

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void INICIALIZA_VIDEO() {

        new Thread() {
            @Override

            public void run() {
                while (true && executando) {
                    try {

                        Image imagem = WEBCAM.getImage();//buscando a image padrao da webcam
                        if(imagem != null){
                        ImageIcon icon = new ImageIcon(imagem);//criando o objeto
                        icon.setImage(icon.getImage().getScaledInstance(FotoWebcam.getWidth(), FotoWebcam.getHeight(), 100));//dimensao do video
                        FotoWebcam.setIcon(icon);
                        }
                        Thread.sleep(50);//velocidade com qual vai mostra a iamgem na label
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }

                }

            }
        }.start();
    }
    
      private String getSql(Funcionarios f){
        DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        Date data = new Date();
        String sql = " funcionario.id=" + f.getId() + " AND u.datacadastro  BETWEEN '" + format.format(data) + " 00:00:00' AND '" + format.format(data) + " 23:59:59'";
            
        return sql;
    }
    
    private int getQtdPonto(Funcionarios f){
        List<Object[]> lista = new PontoDao().listaData(getSql(f));
        int qtd = 0;
        for (Object[] object : lista) {
            qtd++;
        }
        return qtd;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextCpf = new javax.swing.JFormattedTextField();
        jBtnCadastrar = new javax.swing.JButton();
        FotoWebcam = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastrar Ponto");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("CPF");

        try {
            jTextCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextCpf.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N

        jBtnCadastrar.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jBtnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/new.png"))); // NOI18N
        jBtnCadastrar.setText("Salvar");
        jBtnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCadastrarActionPerformed(evt);
            }
        });

        FotoWebcam.setBackground(new java.awt.Color(0, 0, 0));
        FotoWebcam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/webcam.png"))); // NOI18N
        FotoWebcam.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jBtnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(FotoWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jBtnCadastrar))
                    .addComponent(FotoWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCadastrarActionPerformed

        try {
            String nomeimagem = "";
            Funcionarios p = new FuncionarioDao().getFuncionario(jTextCpf.getText());
            if (p != null) {
                if (p.getStatus().equals("Ativo")) {
                    if (executando) {
                        //convertendo Bytes para jpg a imagem que capturou da webcam
                        ByteArrayOutputStream buff = new ByteArrayOutputStream();
                        ImageIO.write(WEBCAM.getImage(), "JPG", buff);
                        byte[] bytes = buff.toByteArray();
                        //codigo para transforma nossa em imagem em arquivos para ser salvo
                        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
                        BufferedImage imagem = ImageIO.read(is);
                        //reduzir ate um certo tamanho da imagem ,para coverter a imagem para um tamanho menor
                        int Nova_Largura = 500, Nova_Altura = 500;//aqui eu ecolho qual sera a altura e largura do px da imagem a ser salvo
                        BufferedImage new_img = new BufferedImage(Nova_Largura, Nova_Altura, BufferedImage.TYPE_INT_RGB);
                        Graphics2D g = new_img.createGraphics();
                        g.drawImage(imagem, 0, 0, Nova_Largura, Nova_Altura, null);
                        Random gerador = new Random();
                        nomeimagem = String.valueOf(gerador.nextInt(1000) + System.currentTimeMillis()) + ".jpg";
                        File dir1 = new File(".");
                        String diretorioImagens = dir1.getCanonicalPath() + Util.pastaImagem;
                        File diretorio = new File(diretorioImagens);
                        if (!diretorio.exists()) {
                            diretorio.mkdirs();
                        }
                        ImageIO.write(new_img, "JPG", new File(diretorioImagens + nomeimagem));
                    }
                    Date data = new Date();
                    String tipoPonto="";
                    if(getQtdPonto(p)%2==0){
                      tipoPonto = "Saída";  
                    }else{
                        tipoPonto="Entrada";
                    }
                    Pontos ponto = new Pontos(p, data, data, nomeimagem, tipoPonto);
                    new PontoDao().save(ponto);
                    new AuditoriaDao().save(ponto, "save");
                    JOptionPane.showMessageDialog(this, "Salvo Com Sucesso!\n" + p.getNome());
                    jTextCpf.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Professor com status desativado!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "CPF não encontrado!");
            }
            jTextCpf.requestFocus();

        } catch (IOException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Error:" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jBtnCadastrarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        new Thread() {
            public void run() {
                executando = false;
                WEBCAM.close();//para finalizar a execucao

            }
        }.start();
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(FuncionarioPontoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FuncionarioPontoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FuncionarioPontoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FuncionarioPontoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FuncionarioPontoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FotoWebcam;
    private javax.swing.JButton jBtnCadastrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JFormattedTextField jTextCpf;
    // End of variables declaration//GEN-END:variables
}
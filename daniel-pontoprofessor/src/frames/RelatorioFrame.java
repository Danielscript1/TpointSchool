/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.CargoDao;
import dao.PontoDao;
import entidades.Cargos;
import entidades.Pontos;
import entidades.Funcionarios;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.Util;

/**
 *
 * @author carleandro
 */
public class RelatorioFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form RelatorioFrame
     */
    public RelatorioFrame() {
        initComponents();
        jTable1.getColumnModel().getColumn(0).setMinWidth(80);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(80);
        jTable1.getColumnModel().getColumn(0).setWidth(80);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
        
        jTable1.getColumnModel().getColumn(1).setMinWidth(80);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(80);
        jTable1.getColumnModel().getColumn(1).setWidth(80);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
        jDateInicial.setDate(new Date());
        jDateFinal.setDate(new Date());
        
        preecherCargos();
    }
    
     private void preecherCargos(){
      List<Cargos> listCargos = new CargoDao().lista();
      Vector<Cargos> lista = new Vector<Cargos>();
      jComboCargos.addItem("Todos os cargos");
      for (Cargos c : listCargos) {     
        jComboCargos.addItem(c.getNome());
      }
    }

    private String getSql(){
         String sql = "";
         DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            if (!jTextCod.getText().equals("")) {
                int cod = Integer.valueOf(jTextCod.getText());
                sql += "funcionarios.id=" + cod;
            }
            if(!jComboCargos.getSelectedItem().toString().equals("Todos os cargos")){
                if(!sql.equals(""))
                    sql+=" AND ";
                sql += "cargos.nome = '"+jComboCargos.getSelectedItem().toString()+"'";
            }
            if (!jDateInicial.getDate().toString().equals("") && !jDateFinal.getDate().toString().equals("")) {
                if(!sql.equals(""))
                    sql+=" AND ";
                sql += " u.datacadastro BETWEEN '" + format.format(jDateInicial.getDate()) + " 00:00:00' AND '" + format.format(jDateFinal.getDate()) + " 23:59:59'";
            }
            return sql;
    }
    private void pesquisar() {
        try {
            DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setNumRows(0);
            List<Object[]> lista = new PontoDao().listaData(getSql());
            for (Object[] object : lista) {
                Pontos ponto = (Pontos) object[0];
                Funcionarios professor = (Funcionarios) object[1];
                Cargos cargo = (Cargos) object[2];
                Object[] row = {ponto.getId(), professor.getId(), professor.getNome(),cargo.getNome(), format.format(ponto.getDatacadastro()), ponto.getHora()};
                model.addRow(row);
            }
            jTable1.setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro:" + ex.getMessage());
        }
    }
    
    private PdfPTable getTabela(List<Object[]> lista){
            DateFormat formatData = new SimpleDateFormat("dd/MM/YYYY");
            PdfPTable tabela = new PdfPTable(new float[]{72, 72, 220, 100, 80});
            Font fontTabela = new Font(FontFamily.COURIER, 10.0f, Font.NORMAL, BaseColor.BLACK);
            Font fontTabelaCabecalho = new Font(FontFamily.COURIER, 10.0f, Font.BOLD, BaseColor.BLACK);
            tabela.addCell(new Paragraph("COD", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("COD FUNC", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Nome do Funcionário", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Data do Cadastro", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Hora", fontTabelaCabecalho));
            
            GregorianCalendar gc = new GregorianCalendar();
            for (Object[] object : lista) {
                Pontos ponto = (Pontos) object[0];
                Funcionarios professor = (Funcionarios) object[1];
                gc.setTime(ponto.getDatacadastro());
                String datacadastro = formatData.format(ponto.getDatacadastro()) + " " + diaSemana(gc);
                PdfPCell pcell1 = new PdfPCell(new Paragraph(ponto.getId() + "", fontTabela));
                PdfPCell pcell2 = new PdfPCell(new Paragraph(professor.getId() + "", fontTabela));
                PdfPCell pcell3 = new PdfPCell(new Paragraph(professor.getNome(), fontTabela));
                PdfPCell pcell4 = new PdfPCell(new Paragraph(datacadastro, fontTabela));
                PdfPCell pcell5 = new PdfPCell(new Paragraph(ponto.getHora() + "", fontTabela));

                tabela.addCell(pcell1);
                tabela.addCell(pcell2);
                tabela.addCell(pcell3);
                tabela.addCell(pcell4);
                tabela.addCell(pcell5);
            }
            return tabela;
    }
    
    private PdfPTable getTabelaCargo(List<Object[]> lista){
            DateFormat formatData = new SimpleDateFormat("dd/MM/YYYY");
            PdfPTable tabela = new PdfPTable(new float[]{72, 220,100, 100, 80});
            Font fontTabela = new Font(FontFamily.COURIER, 10.0f, Font.NORMAL, BaseColor.BLACK);
            Font fontTabelaCabecalho = new Font(FontFamily.COURIER, 10.0f, Font.BOLD, BaseColor.BLACK);
            tabela.addCell(new Paragraph("COD FUNC", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Nome do Funcionário", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Cargo", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Data do Cadastro", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Hora", fontTabelaCabecalho));
 
            GregorianCalendar gc = new GregorianCalendar();
            for (Object[] object : lista) {
                Pontos ponto = (Pontos) object[0];
                Funcionarios professor = (Funcionarios) object[1];
                Cargos cargo = (Cargos) object[2];
                gc.setTime(ponto.getDatacadastro());
                String datacadastro = formatData.format(ponto.getDatacadastro()) + " " + diaSemana(gc);
                PdfPCell pcell2 = new PdfPCell(new Paragraph(professor.getId() + "", fontTabela));
                PdfPCell pcell3 = new PdfPCell(new Paragraph(professor.getNome(), fontTabela));
                PdfPCell pcell4 = new PdfPCell(new Paragraph(cargo.getNome(), fontTabela));
                PdfPCell pcell5 = new PdfPCell(new Paragraph(datacadastro, fontTabela));
                PdfPCell pcell6 = new PdfPCell(new Paragraph(ponto.getHora() + "", fontTabela));

                tabela.addCell(pcell2);
                tabela.addCell(pcell3);
                tabela.addCell(pcell4);
                tabela.addCell(pcell5);
                tabela.addCell(pcell6);
            }
            return tabela;
    }

     private PdfPTable getTabelaEntradaSaida(List<Object[]> lista){
            DateFormat formatData = new SimpleDateFormat("dd/MM/YYYY");
            PdfPTable tabela = new PdfPTable(new float[]{72, 270,100, 65, 65});
            Font fontTabela = new Font(FontFamily.COURIER, 10.0f, Font.NORMAL, BaseColor.BLACK);
            Font fontTabelaCabecalho = new Font(FontFamily.COURIER, 10.0f, Font.BOLD, BaseColor.BLACK);
            tabela.addCell(new Paragraph("COD FUNC", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Nome do Funcionário", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Data do Cadastro", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Hora", fontTabelaCabecalho));
            tabela.addCell(new Paragraph("Tipo", fontTabelaCabecalho));
            
            GregorianCalendar gc = new GregorianCalendar();
            for (Object[] object : lista) {
                Pontos ponto = (Pontos) object[0];
                Funcionarios professor = (Funcionarios) object[1];
                Cargos cargo = (Cargos) object[2];
                gc.setTime(ponto.getDatacadastro());
               
                String datacadastro = formatData.format(ponto.getDatacadastro()) + " " + diaSemana(gc);
                PdfPCell pcell2 = new PdfPCell(new Paragraph(professor.getId() + "", fontTabela));
                PdfPCell pcell3 = new PdfPCell(new Paragraph(professor.getNome(), fontTabela));
                PdfPCell pcell4 = new PdfPCell(new Paragraph(datacadastro, fontTabela));
                PdfPCell pcell5 = new PdfPCell(new Paragraph(ponto.getHora() + "", fontTabela));
                PdfPCell pcell6 = new PdfPCell(new Paragraph(ponto.getTipo() + "", fontTabela));
                
                tabela.addCell(pcell2);
                tabela.addCell(pcell3);
                tabela.addCell(pcell4);
                tabela.addCell(pcell5);
                tabela.addCell(pcell6);
            }
            return tabela;
    }
     
    private void relatoriopdf(int tipo) {
        Document document = new Document(PageSize.A4);
        document.setMargins(1f, 1f, 35f, 35f);
        String caminhoPdf="";
        try {
            DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            DateFormat formatHora = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
             DateFormat formatData = new SimpleDateFormat("dd/MM/YYYY");
             
            String nomePdf = format.format(new Date())+"-"+System.currentTimeMillis()+".pdf";
            File dir1 = new File (".");
            String diretorioPdfs= dir1.getCanonicalPath()+Util.pastaPdfs+nomePdf;
            caminhoPdf = diretorioPdfs;
            PdfWriter.getInstance(document, new FileOutputStream(diretorioPdfs));
            
            
            document.open();

            Font fontCabecalho = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD, BaseColor.BLACK);
            Font fontTabela = new Font(FontFamily.COURIER, 10.0f, Font.NORMAL, BaseColor.BLACK);
            Font fontTabelaCabecalho = new Font(FontFamily.COURIER, 10.0f, Font.BOLD, BaseColor.BLACK);

            Paragraph paragrafoCabecalho = new Paragraph("Relatório de Ponto dos Funcionários", fontCabecalho);
            paragrafoCabecalho.setAlignment(Paragraph.ALIGN_CENTER);

            Paragraph paragrafoData = new Paragraph("Gerado: " + formatHora.format(new Date()), fontTabela);
            paragrafoData.setAlignment(Paragraph.ALIGN_CENTER);
            
            Paragraph paragrafoDataPesq = new Paragraph(formatData.format(jDateInicial.getDate())+" - " + formatData.format(jDateFinal.getDate()), fontTabela);
            paragrafoDataPesq.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(paragrafoCabecalho);
            document.add(paragrafoData);
            document.add(paragrafoDataPesq);
            document.add(new Paragraph(" ", fontCabecalho));
            
            List<Object[]> lista = new PontoDao().listaData(getSql());
            if(tipo == 1){
                document.add(getTabela(lista));
            }else if(tipo ==2){
                document.add(getTabelaCargo(lista));
            }else if(tipo ==3){
                document.add(getTabelaEntradaSaida(lista));
            }

        } catch (DocumentException ex) {
            System.out.println("Error:" + ex);
        } catch (FileNotFoundException ex) {
            System.out.println("Error:" + ex);
        } catch (IOException ex) {
             System.out.println("Error:" + ex);
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File(caminhoPdf));
        } catch (IOException ex) {
            System.out.println("Error:" + ex);
        }
    }

    private String diaSemana(GregorianCalendar gc) {
       String dia="";
        switch (gc.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
               dia="Domingo";
                break;
            case Calendar.MONDAY:
                dia = "Segunda";
                break;
            case Calendar.TUESDAY:
                dia = "Terça";
                break;
            case Calendar.WEDNESDAY:
                dia = "Quarta";
                break;
            case Calendar.THURSDAY:
                dia = "Quinta";
                break;
            case Calendar.FRIDAY:
                dia = "Sexta";
                break;
            case Calendar.SATURDAY:
                dia = "Sábado";
                break;
        }
        return dia;
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
        jTextCod = new javax.swing.JTextField();
        jDateInicial = new com.toedter.calendar.JDateChooser();
        jDateFinal = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboCargos = new javax.swing.JComboBox<>();
        jBtnCompleto = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setTitle("Relatório");

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Cod Func");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Data Inicio");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Data Final");

        jDateFinal.setToolTipText("");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/pesquisar.png"))); // NOI18N
        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/pdf.png"))); // NOI18N
        jButton2.setText("Relatório Simples");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jBtnCompleto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/pdf.png"))); // NOI18N
        jBtnCompleto.setText("Relatório Completo");
        jBtnCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCompletoActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/pdf.png"))); // NOI18N
        jButton3.setText("Relatório Entrada e Saída");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Cargos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextCod, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jDateInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 328, Short.MAX_VALUE))
                            .addComponent(jComboCargos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "COD FUNC", "Nome", "Cargo", "Data Cadastro", "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pesquisar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        relatoriopdf(1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jBtnCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCompletoActionPerformed
        relatoriopdf(2);
    }//GEN-LAST:event_jBtnCompletoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       relatoriopdf(3);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCompleto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboCargos;
    private com.toedter.calendar.JDateChooser jDateFinal;
    private com.toedter.calendar.JDateChooser jDateInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextCod;
    // End of variables declaration//GEN-END:variables
}

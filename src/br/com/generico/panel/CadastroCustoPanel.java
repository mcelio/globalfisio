/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.panel;

import br.com.generico.Main;
import br.com.generico.dao.CustoDao;
import br.com.generico.to.Custo;
import br.com.generico.utils.DatePicker;
import java.text.SimpleDateFormat;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcos
 */
public class CadastroCustoPanel extends javax.swing.JPanel {

    private SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    public static boolean atualizaCusto = false;
    public static Long idAtualizaCusto = -1L;
    private static Logger logger = Logger.getLogger(CadastroCustoPanel.class.getName());

    /**
     * Creates new form CadastroCustoPanel
     */
    public CadastroCustoPanel() {
        initComponents();
        Icon icone = new ImageIcon(getClass().getResource("/br/com/generico/utils/icon_calendar.png"));
        dataBtn.setIcon(icone);
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
        nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricao = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        valor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        data = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        dataBtn = new javax.swing.JButton();

        jLabel1.setText("Nome:");

        jLabel2.setText("Descrição:");

        descricao.setColumns(20);
        descricao.setRows(5);
        jScrollPane1.setViewportView(descricao);

        jLabel3.setText("Valor:");

        jLabel4.setText("Data:");

        data.setEditable(false);
        data.setMinimumSize(new java.awt.Dimension(6, 30));

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Gravar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Pesquisar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        dataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dataBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dataBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(152, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try {
        Custo custo = new Custo();
        String dataStr = data.getText();
        String descricaoStr = descricao.getText();
        String nomeStr = nome.getText();
        String valorStr = valor.getText();

        if (dataStr != null && !dataStr.trim().equals("")) {
            custo.setData(formataData.parse(dataStr));
        }
        try {
            String test = valorStr.trim().replace(",", ".");
            custo.setValor(Double.parseDouble(test));
        } catch (Exception e) {
            // faz nada e deixa valor nulo
        }
        custo.setDescricao(descricaoStr);
        custo.setNome(nomeStr);

        CustoDao custoDao = new CustoDao();

        if (atualizaCusto) {
            custo.setId(idAtualizaCusto);
            custoDao.atualiza(custo);
        } else {
            custoDao.inserir(custo);
        }
        JOptionPane.showMessageDialog(this, "Registro gravado com sucesso.");
        limpaFormulario();
    } catch (Exception e) {
        logger.error("Erro neste ponto:", e);
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}//GEN-LAST:event_jButton2ActionPerformed

    private void limpaFormulario() {
        try {
            data.setText("");
            nome.setText("");
            descricao.setText("");
            valor.setText("");
            atualizaCusto = false;
        } catch (Exception e) {
            logger.error("Erro neste ponto:", e);
        }
    }

private void dataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataBtnActionPerformed
    final JFrame f = new JFrame();
    JPanel p = new JPanel();
    Main.frame.getContentPane().add(p);
    Main.frame.pack();
    Main.frame.setVisible(true);
    data.setText(new DatePicker(Main.frame).setPickedDate());
}//GEN-LAST:event_dataBtnActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    limpaFormulario();
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    JFrame pesquisaCustoFrame = new JFrame("Pesquisar custo");
    PesquisarCustoPanel pesquisaCustoPanel = new PesquisarCustoPanel();
    pesquisaCustoFrame.getContentPane().add(pesquisaCustoPanel);
    pesquisaCustoFrame.pack();
    pesquisaCustoFrame.setVisible(true);
    pesquisaCustoFrame.setLocationRelativeTo(null);
    pesquisaCustoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField data;
    private javax.swing.JButton dataBtn;
    private javax.swing.JTextArea descricao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField valor;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.panel;

import br.com.generico.Main;
import br.com.generico.dao.LembreteDao;
import br.com.generico.to.Lembrete;
import br.com.generico.utils.DatePicker;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcos
 */
public class LembretePanel extends javax.swing.JPanel {

    private static SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    private static PesquisaLembreteTableModel model;    
    private static Logger logger = Logger.getLogger(LembretePanel.class.getName());
    
    /**
     * Creates new form LembretePanel
     */
    public LembretePanel() {
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
        jLabel2 = new javax.swing.JLabel();
        data = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        mes = new javax.swing.JComboBox();
        ano = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        avisar = new javax.swing.JCheckBox();
        dataBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        descricao = new javax.swing.JTextArea();

        jLabel1.setText("Descrição:");

        jLabel2.setText("Data:");

        jButton1.setText("Gravar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Pesquisar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Mês:");

        mes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        ano.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016" }));

        jLabel4.setText("Ano:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setText("dd/mm/yyyy");

        avisar.setText("Avisar");

        dataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataBtnActionPerformed(evt);
            }
        });

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setWheelScrollingEnabled(false);

        descricao.setColumns(20);
        descricao.setRows(5);
        descricao.setAutoscrolls(false);
        jScrollPane2.setViewportView(descricao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dataBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(avisar))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jButton2)))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(avisar))
                    .addComponent(dataBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel4)
                    .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {            
            Lembrete lembretePesquisa = new Lembrete();
            int valorMes = mes.getSelectedIndex();
            int valorAno = Integer.parseInt(ano.getSelectedItem().toString());
            Calendar dataDe = Calendar.getInstance();
            dataDe.set(Calendar.DAY_OF_MONTH, 1);
            dataDe.set(Calendar.MONTH, valorMes);
            dataDe.set(Calendar.YEAR, valorAno);

            Calendar dataAte = Calendar.getInstance();
            dataAte.set(Calendar.DAY_OF_MONTH, 31);
            dataAte.set(Calendar.MONTH, valorMes);
            dataAte.set(Calendar.YEAR, valorAno);

            LembreteDao lembreteDao = new LembreteDao();

            List<Lembrete> resultado = lembreteDao.pesquisa(lembretePesquisa, formataData.format(dataDe.getTime()), formataData.format(dataAte.getTime()));

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "A pesquisa não retornou registros.");
                return;
            }

            model = new PesquisaLembreteTableModel();

            if (resultado != null) {
                Lembrete lembrete = new Lembrete();                
                
                lembrete.setDescricao("");
                lembrete.setData(null);
                resultado.add(lembrete);
            }

            model.setRows(resultado);
            jTable1.setModel(model);
            jScrollPane1.setViewportView(jTable1);
            jTable1.setRowHeight(25);
            TableColumn hdr0 = jTable1.getTableHeader().getColumnModel().getColumn(0);
            hdr0.setPreferredWidth(490);
            hdr0.setMaxWidth(490);
            hdr0.setMinWidth(490);
            hdr0.setWidth(490);

            TableColumn hdr1 = jTable1.getTableHeader().getColumnModel().getColumn(1);
            hdr1.setPreferredWidth(90);
            hdr1.setMaxWidth(90);
            hdr1.setMinWidth(90);
            hdr1.setWidth(90);
            
            model.fireTableDataChanged();
        } catch (Exception e) {
            logger.error("Erro neste ponto:", e);
            JOptionPane.showMessageDialog(null, "Ocorreu um erro, por favor contate o desenvolvedor (Tel: 19-9354-5207).");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {            
            Lembrete lembrete = new Lembrete();            
            lembrete.setDescricao(descricao.getText());           
            try{
                lembrete.setData(formataData.parse(data.getText()));
            }catch(Exception e){
                lembrete.setData(null);
            }            
            lembrete.setAvisar((avisar.isSelected() ? Lembrete.AVISAR_SIM : Lembrete.AVISAR_NAO));            
            new LembreteDao().inserir(lembrete);
            JOptionPane.showMessageDialog(null, "Registro gravado com sucesso.");
            limparFormulario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataBtnActionPerformed
        JPanel p = new JPanel();
        Main.frame.getContentPane().add(p);
        Main.frame.pack();
        Main.frame.setVisible(true);
        data.setText(new DatePicker(Main.frame).setPickedDate());
    }//GEN-LAST:event_dataBtnActionPerformed

    private void limparFormulario() {
        data.setText("");
        descricao.setText("");
        avisar.setSelected(false);
    }

    class PesquisaLembreteTableModel extends AbstractTableModel {
        //private Object[][] data = {};

        List<Lembrete> data = new ArrayList<Lembrete>();
        private String[] columns = {"Descrição", "Data"};

        public String getColumnName(int column) {
            return columns[column];
        }

        public int getRowCount() {
            return data.size();
        }

        public int getColumnCount() {
            return columns.length;
        }

        public Object getValueAt(int rowNum, int colNum) {
            Lembrete custo = data.get(rowNum);

            if (colNum == 0) {
                return (custo.getDescricao() != null ? custo.getDescricao().trim() : "");
            } else if (colNum == 1) {
                return (custo.getData() != null ? formataData.format(custo.getData()) : "");
            }
            return null;
        }

        public boolean isCellEditable(int row, int column) {
            return false;
        }

        public Class getColumnClass(int column) {
            return String.class;
        }

        public void setRows(List<Lembrete> data) {
            this.data = data;
        }

        public List<Lembrete> getData() {
            return data;
        }

        public void setValueAt(Object o, int row, int column) {
            try {
                Lembrete rowObj = data.get(row);
                if (column == 0) {
                    rowObj.setDescricao(o.toString());
                } else if (column == 1) {
                    rowObj.setData(formataData.parse(o.toString()));
                }
            } catch (Exception e) {
                logger.error("Erro neste ponto:", e);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ano;
    private javax.swing.JCheckBox avisar;
    private javax.swing.JTextField data;
    private javax.swing.JButton dataBtn;
    private javax.swing.JTextArea descricao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox mes;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.AgendamentoDAO;
import dao.ServicoDAO;
import dao.ClienteDAO;
import java.awt.event.ActionEvent;
import java.util.List;
import model.Cliente;
import model.Servico;
import javax.swing.JOptionPane;

/**
 *
 * @author IgorP
 */
public class AgendamentosCadastrados extends javax.swing.JFrame {

    private List<Servico> listaServicos;
    private List<Cliente> listaClientesPorServico;

    /**
     * Creates new form AgendamentosCadastrados
     */
    public AgendamentosCadastrados() {
        initComponents();

        // Configuração inicial: combos de cliente e data/hora começam desabilitados
        cmbCliente.setEnabled(false);
        cmbDataHora.setEnabled(false);

        // Carrega todos os serviços no combo
        carregarServicos();

        // Adiciona os listeners para reação em cadeia
        cmbServico.addActionListener(this::aoSelecionarServico);
        cmbCliente.addActionListener(this::aoSelecionarCliente);
    }

    // Carrega todos os serviços cadastrados no cmbServico
    private void carregarServicos() {
        ServicoDAO dao = new ServicoDAO();
        listaServicos = dao.listarTodos();

        cmbServico.removeAllItems();
        cmbServico.addItem("Selecionar Serviço");

        if (listaServicos != null && !listaServicos.isEmpty()) {
            for (Servico s : listaServicos) {
                cmbServico.addItem(s.getNome());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum serviço cadastrado ainda.");
        }
    }

    // Executado quando o usuário seleciona um serviço
    private void aoSelecionarServico(ActionEvent e) {
        Object item = cmbServico.getSelectedItem();
        if (item == null || item.toString().equals("Selecionar Serviço")) {
            cmbCliente.setEnabled(false);
            cmbCliente.removeAllItems();
            cmbDataHora.setEnabled(false);
            cmbDataHora.removeAllItems();
            return;
        }

        String nomeServico = item.toString();

        // Encontra o ID do serviço selecionado
        int idServico = 0;
        for (Servico s : listaServicos) {
            if (s.getNome().equals(nomeServico)) {
                idServico = s.getIdServico();
                break;
            }
        }

        if (idServico == 0) return;

        // Busca os clientes que têm agendamento com esse serviço
        AgendamentoDAO dao = new AgendamentoDAO();
        listaClientesPorServico = dao.listarClientesPorServico(idServico);

        cmbCliente.removeAllItems();
        cmbCliente.addItem("Selecionar Cliente");

        if (!listaClientesPorServico.isEmpty()) {
            for (Cliente c : listaClientesPorServico) {
                cmbCliente.addItem(c.getNome());
            }
            cmbCliente.setEnabled(true);
        } else {
            cmbCliente.addItem("Nenhum cliente agendado para este serviço");
            cmbCliente.setEnabled(false);
        }

        // Limpa e desabilita data/hora até selecionar cliente
        cmbDataHora.setEnabled(false);
        cmbDataHora.removeAllItems();
    }

    // Executado quando o usuário seleciona um cliente
    private void aoSelecionarCliente(ActionEvent e) {
        Object itemCliente = cmbCliente.getSelectedItem();
        Object itemServico = cmbServico.getSelectedItem();

        if (itemCliente == null || itemCliente.toString().equals("Selecionar Cliente")) {
            cmbDataHora.setEnabled(false);
            cmbDataHora.removeAllItems();
            return;
        }

        String nomeCliente = itemCliente.toString();
        String nomeServico = itemServico.toString();

        // Encontra IDs
        int idServico = 0;
        for (Servico s : listaServicos) {
            if (s.getNome().equals(nomeServico)) {
                idServico = s.getIdServico();
                break;
            }
        }

        int idCliente = 0;
        for (Cliente c : listaClientesPorServico) {
            if (c.getNome().equals(nomeCliente)) {
                idCliente = c.getIdCliente();
                break;
            }
        }

        if (idServico == 0 || idCliente == 0) return;

        // Busca as datas/horas
        AgendamentoDAO dao = new AgendamentoDAO();
        List<String> horarios = dao.listarDataHoraPorServicoECliente(idServico, idCliente);

        cmbDataHora.removeAllItems();
        cmbDataHora.setEnabled(true);

        if (!horarios.isEmpty()) {
            for (String horario : horarios) {
                cmbDataHora.addItem(horario);
            }
        } else {
            cmbDataHora.addItem("Nenhum agendamento encontrado");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbServico = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbDataHora = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("DialogInput", 1, 28)); // NOI18N
        jLabel1.setText("Serviços Cadastrados");

        jLabel2.setFont(new java.awt.Font("DialogInput", 2, 20)); // NOI18N
        jLabel2.setText("Serviço:");

        cmbServico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("DialogInput", 2, 20)); // NOI18N
        jLabel3.setText("Data/Hora:");

        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("DialogInput", 2, 20)); // NOI18N
        jLabel4.setText("Cliente:");

        cmbDataHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbCliente, 0, 365, Short.MAX_VALUE)
                    .addComponent(cmbServico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbDataHora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(200, 200, 200))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AgendamentosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendamentosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendamentosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendamentosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendamentosCadastrados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbDataHora;
    private javax.swing.JComboBox<String> cmbServico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

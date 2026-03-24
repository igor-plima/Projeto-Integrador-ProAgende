package dao;

import br.com.proagende.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class AgendamentoDAO {

    //Método responsável por salvar um agendamento no banco (CREATE DO CRUD)
    public void salvarAgendamento(int idServico, int idCLiente, String dataHora, String status) {

        //Variável SQL (Comando INSERT)
        String sql = "INSERT INTO Agendamento (id_Servico, id_Cliente, data_hora, status) VALUES (?, ?, ?, ?)";

        //Criação das variáveis de conexão com o banco e comando SQL
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //Abre a conexão com o banco
            conn = Conexao.conectar();
            // Prepara o comando SQL
            stmt = conn.prepareStatement(sql);
            //Troca os valores dos "?" para os valores recebidos no parametro do metodo
            stmt.setInt(1, idServico);
            stmt.setInt(2, idCLiente);
            stmt.setString(3, dataHora);
            stmt.setString(4, status);

            //Executa o comando INSERT
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar agendamento: " + e.getMessage());

        } finally {
            //Fecha o comando SQL e a conexão com o banco
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    // Método para preencher o comboBox de Clientres na "AgendamentosCadastrados", retorna todos os clientes que possuem pelo menos um agendamento para o serviço selecionado
public List<Cliente> listarClientesPorServico(int idServico) {
    // Lista que vai guardar os clientes encontrados
    List<Cliente> lista = new ArrayList<>();
    
    // Comando SQL que junta Cliente + Agendamento, filtra pelo serviço e usa DISTINCT para não repetir cliente
    String sql = "SELECT DISTINCT c.id_Cliente, c.nome FROM Cliente c INNER JOIN Agendamento a ON c.id_Cliente = a.Id_Cliente WHERE a.id_Servico = ? ORDER BY c.nome";
    
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        // Abre conexão com o banco
        conn = Conexao.conectar();
        // Prepara o SQL 
        stmt = conn.prepareStatement(sql);
        
        // Coloca o idServico no lugar do ?
        stmt.setInt(1, idServico);
        
        // Executa a consulta
        rs = stmt.executeQuery();
        
        // Enquanto houver resultados
        while (rs.next()) {
            // Cria um objeto Cliente temporário
            Cliente cliente = new Cliente();
            
            // Pega o ID e o nome do resultado da consulta
            cliente.setIdCliente(rs.getInt("id_Cliente"));
            cliente.setNome(rs.getString("nome"));
            
            // Adiciona na lista
            lista.add(cliente);
        }
    } catch (Exception e) {
        
        e.printStackTrace();
        
    } finally {
        //Fechando os recursos para não deixar conexão aberta
        try { if (rs != null) rs.close(); } catch (Exception ignored) {}
        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }
    
    // Retorna a lista
    return lista;
}

// Método para preencher o comboBox de data/hora da tela AgendamentopsCadastrados, retorna todas as datas e horas de agendamentos para o serviço e cliente selecionados.
public List<String> listarDataHoraPorServicoECliente(int idServico, int idCliente) {
    // Lista que vai guardar as datas/horas formatadas
    List<String> lista = new ArrayList<>();
    
    //Comando sql que busca apenas a coluna data_hora, filtra pelos dois IDs, ordena por data
    String sql = "SELECT DATE_FORMAT(data_hora, '%d/%m/%Y %H:%i') AS datahora FROM Agendamento WHERE id_Servico = ? AND Id_Cliente = ? ORDER BY data_hora";

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Conexao.conectar();
        stmt = conn.prepareStatement(sql);
        
        // Coloca os dois parâmetros
        stmt.setInt(1, idServico);
        stmt.setInt(2, idCliente);
        
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            // Pega a data/hora já formatada (dd/MM/yyyy HH:mm)
            String horarioFormatado = rs.getString("datahora");
            lista.add(horarioFormatado);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception ignored) {}
        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }
    
    return lista;
}


}

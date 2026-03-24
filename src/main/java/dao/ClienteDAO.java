package dao;

import br.com.proagende.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Cliente;

public class ClienteDAO {

    //Método responsável por salvar um cliente no banco de dados (CREATE do CRUD)
    public void salvarCliente(String nome, String email, String telefone) {

        String sql = "INSERT INTO cliente (nome,email,telefone) VALUES (?,?,?)";

        //Criação das variáveis de conexão e comando SQL
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Abre a conexão com o banco de dados usando a classe Conexao
            conn = Conexao.conectar();

            // Prepara o comando SQL para execução
            stmt = conn.prepareStatement(sql);

            // Substitui os "?" pelos valores recebidos no método
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);

            // Executa o comando INSERT no banco
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar cliente " + e.getMessage());

        } finally {
            // Fecha os recursos do banco para evitar vazamento de memória
            try {
                if (stmt != null) {
                    stmt.close(); // fecha o comando SQL
                }

                if (conn != null) {
                    conn.close(); // fecha a conexão com o banco
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Método responsável por buscar clientes, para preencher o comboBox com os dados do banco (READ DO CRUD)
    public java.util.List<String> listarClientes() {

        // Lista que vai armazenar os nomes dos clientes
        java.util.List<String> lista = new java.util.ArrayList<>();

        // Comando SQL para buscar os nomes dos clientes
        String sql = "SELECT nome FROM cliente";

        // Variáveis de conexão
        Connection conn = null;
        PreparedStatement stmt = null;
        java.sql.ResultSet rs = null;

        try {
            // Abre conexão com o banco
            conn = Conexao.conectar();

            // Prepara o comando SQL
            stmt = conn.prepareStatement(sql);

            // Executa a consulta
            rs = stmt.executeQuery();

            // Percorre os resultados retornados pelo banco
            while (rs.next()) {

                // Adiciona cada nome na lista
                lista.add(rs.getString("nome"));
            }

        } catch (SQLException e) {

            // Exibe erro no console
            System.out.println("Erro ao listar clientes: " + e.getMessage());

        } finally {
            try {
                // Fecha ResultSet
                if (rs != null) {
                    rs.close();
                }

                // Fecha PreparedStatement
                if (stmt != null) {
                    stmt.close();
                }

                // Fecha conexão
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // Retorna a lista de clientes
        return lista;
    }
    
    //Método quase igual o ListarClientes, mas seleciona o id_Cliente + nome e cria objetos CLiente
    public java.util.List<Cliente> listarTodos() {
        java.util.List<Cliente> lista = new java.util.ArrayList<>();
        String sql = "SELECT id_Cliente, nome FROM cliente ORDER BY nome";  // ORDER BY para ficar organizado

        Connection conn = null;
        PreparedStatement stmt = null;
        java.sql.ResultSet rs = null;

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_Cliente"));
                cliente.setNome(rs.getString("nome"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar todos clientes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return lista;
    }

}

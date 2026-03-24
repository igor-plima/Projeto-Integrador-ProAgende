package dao;

import br.com.proagende.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Servico;

public class ServicoDAO {

    // Método responsável por salvar um serviço no banco (CREATE DO CRUD)
    public void salvarServico(int idProfissional, String nome, String descricao, double preco, int duracao) {

        // Comando SQL
        String sql = "INSERT INTO servico (id_Profissional, nome, descricao, preco, duracao_minutos) VALUES (?, ?, ?, ?, ?)";

        java.sql.Connection conn = null;
        java.sql.PreparedStatement stmt = null;

        try {
            // Abrir conexão
            conn = Conexao.conectar();

            // Preparar SQL
            stmt = conn.prepareStatement(sql);

            // Substituir os ?
            stmt.setInt(1, idProfissional);
            stmt.setString(2, nome);
            stmt.setString(3, descricao);
            stmt.setDouble(4, preco);
            stmt.setInt(5, duracao);

            // Executar
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao salvar serviço: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Método responsável por buscar servicos, para preencher o comboBox com os dados do banco (READ DO CRUD)
    public java.util.List<String> listarServicos() {

        // Lista para armazenar os nomes dos serviços
        java.util.List<String> lista = new java.util.ArrayList<>();

        // Comando SQL
        String sql = "SELECT nome FROM servico";

        Connection conn = null;
        PreparedStatement stmt = null;
        java.sql.ResultSet rs = null;

        try {
            // Conectar ao banco
            conn = Conexao.conectar();

            // Preparar SQL
            stmt = conn.prepareStatement(sql);

            // Executar consulta
            rs = stmt.executeQuery();

            // Percorrer resultados
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar serviços: " + e.getMessage());

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

    public List<Servico> listarTodos() {
        List<Servico> lista = new ArrayList<>();
        String sql = "SELECT id_Servico, nome FROM servico ORDER BY nome";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setIdServico(rs.getInt("id_Servico"));
                servico.setNome(rs.getString("nome"));
                lista.add(servico);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar todos serviços: " + e.getMessage());
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

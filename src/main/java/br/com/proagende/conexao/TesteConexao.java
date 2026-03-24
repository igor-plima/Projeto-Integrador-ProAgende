package br.com.proagende.conexao;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        Connection conn = Conexao.conectar();

        if (conn != null) {
            System.out.println("Conexão realizada com sucesso!");
        } else {
            System.out.println("Erro na conexão.");

        }

    }
}

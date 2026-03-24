
package br.com.proagende.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static final String URL = "jdbc:mysql://localhost:3306/ProAgende";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar " + e.getMessage() );
            return null;
        }
    }
    
}

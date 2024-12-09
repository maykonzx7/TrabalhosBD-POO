package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql {
    public static Connection conexao() {
        String URL = "jdbc:mysql://localhost:3306/conexao";
        String usuario = "root";
        String senha = "";

        try {
            Connection conexao = DriverManager.getConnection(URL, usuario, senha);
            System.out.println("Conexão realizada com sucesso!");

            return conexao;
        }  catch (SQLException e) {
            System.err.println("Erro ao conectar ao Banco de Dados: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

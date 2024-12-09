package org.example.DAO;

import org.example.context.GlobalVariables;
import org.example.repository.Produto;
import org.example.repository.Restaurante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    private final Connection conexao;
    private final GlobalVariables globalVariables;
    public ProdutosDAO(Connection conexao, GlobalVariables globalVariables) {
        this.conexao = conexao;
        this.globalVariables = globalVariables;
    }

    public void listarProdutos(int idRestaurante) throws SQLException {
        String consulta = "SELECT * FROM produto WHERE id_restaurante = ?";
        try (PreparedStatement prepararConsulta = conexao.prepareStatement(consulta)) {
            prepararConsulta.setInt(1, idRestaurante);
            try (ResultSet resposta = prepararConsulta.executeQuery()) {
                while (resposta.next()) {
                    int id = resposta.getInt("id");
                    String nome = resposta.getString("nome");
                    String descricao = resposta.getString("descricao");
                    float preco = resposta.getFloat("preco");

                    Restaurante restaurante = globalVariables.buscarPorId(idRestaurante);
                    if (restaurante == null) {
                        // Se o restaurante não for encontrado, pode tratar o erro aqui
                        System.out.println("Restaurante não encontrado!");
                        return;
                    }

                    Produto produto = new Produto(id, nome, descricao, preco, restaurante);
                    System.out.println("Produto: " + produto);
                }
            }
        }
    }

    public ArrayList<Produto> listarProdutosPorRestaurante(int idRestaurante) throws SQLException {
        ArrayList<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM produto WHERE id_restaurante = ?";
        try (PreparedStatement statement = conexao.prepareStatement(query)) {
            statement.setInt(1, idRestaurante);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String descricao = resultSet.getString("descricao");
                    float preco = resultSet.getFloat("preco");
                    int id_restaurante = resultSet.getInt("id_restaurante");

                    Restaurante restaurante = globalVariables.buscarPorId(id_restaurante);
                    if (restaurante == null) {
                        // Se o restaurante não for encontrado, pode tratar o erro aqui
                        System.out.println("Restaurante não encontrado!");
                        continue;
                    }

                    Produto produto = new Produto(id, nome, descricao, preco, restaurante);
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
}

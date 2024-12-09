package org.example.DAO;

import org.example.context.GlobalVariables;
import org.example.repository.Produto;
import org.example.repository.Restaurante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestauranteDAO {
    private final Connection conexao;
    private final GlobalVariables globalVariables;

    public RestauranteDAO(Connection conexao, GlobalVariables globalVariables) {
        this.conexao = conexao;
        this.globalVariables = globalVariables;
    }

    public void puxarRestaurante() throws SQLException {
        PreparedStatement prepararConsulta = null;
        ResultSet resposta;

        String consulta = "SELECT * FROM restaurante";
        prepararConsulta = conexao.prepareStatement(consulta);

        resposta = prepararConsulta.executeQuery();

        while (resposta.next()) {
            int id = resposta.getInt("id");
            String nome = resposta.getString("nome");
            String telefone = resposta.getString("telefone");
            int endereco = resposta.getInt("id_endereco");

            Restaurante restaurante = new Restaurante(id, nome, telefone);
            globalVariables.adicionarRestaurante(restaurante);
        }
    }

    // Método para buscar um restaurante pelo ID
    private Restaurante buscarRestaurantePorId(int idRestaurante) throws SQLException {
        String query = "SELECT * FROM restaurante WHERE id = ?";
        PreparedStatement statement = conexao.prepareStatement(query);
        statement.setInt(1, idRestaurante);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String telefone = resultSet.getString("telefone");
            return new Restaurante(id, nome, telefone);
        }
        return null;
    }
}

package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FormaPagamentoDAO {
    private final Connection conexao;

    public FormaPagamentoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public ArrayList<String> listarFormasDePagamento() throws SQLException {
        ArrayList<String> formasPagamento = new ArrayList<>();
        String query = "SELECT descricao FROM formas_pagamento";
        PreparedStatement statement = conexao.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            formasPagamento.add(resultSet.getString("descricao"));
        }
        return formasPagamento;
    }
}

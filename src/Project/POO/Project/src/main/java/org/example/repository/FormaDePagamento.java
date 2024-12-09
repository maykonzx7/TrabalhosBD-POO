package org.example.repository;

import java.util.List;

public class FormaDePagamento {
    private String descricao;

    public FormaDePagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

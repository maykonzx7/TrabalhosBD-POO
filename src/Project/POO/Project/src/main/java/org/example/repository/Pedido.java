package org.example.repository;

import java.util.List;

public class Pedido {
    private int id;
    private Restaurante restaurante;
    private List<Produto> produtos;
    private Endereco endereco;
    private String formaDePagamento;
    private String status;

    public Pedido(Restaurante restaurante, List<Produto> produtos, Endereco endereco, String formaDePagamento) {
        this.restaurante = restaurante;
        this.produtos = produtos;
        this.endereco = endereco;
        this.formaDePagamento = formaDePagamento;
        this.status = "Em Produção";
    }

    public void atualizarStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

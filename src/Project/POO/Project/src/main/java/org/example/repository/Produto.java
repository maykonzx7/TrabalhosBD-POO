package org.example.repository;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private float preco;
    private Restaurante restaurante;

    // Construtor com Restaurante
    public Produto(int id, String nome, String descricao, float preco, Restaurante restaurante) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.restaurante = restaurante;
    }

    // Construtor sem Restaurante (para permitir criação sem o objeto Restaurante)
    public Produto(int id, String nome, String descricao, float preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Descrição: " + descricao + ", Preço: R$" + preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}

package org.example.repository;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private int id;
    private String nome;
    private String telefone;
    private Endereco endereco; // Atributo de endereço do tipo Endereco
    private List<Produto> produtos; // A lista de produtos associados ao restaurante

    // Construtor
    public Restaurante(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.produtos = new ArrayList<>(); // Inicializando a lista de produtos
    }

    // Getters e setters
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public int getIdEndereco() {
        return endereco != null ? endereco.getId() : id;
    }
}

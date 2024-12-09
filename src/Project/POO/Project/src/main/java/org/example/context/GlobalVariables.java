package org.example.context;

import org.example.repository.Endereco;
import org.example.repository.Pedido;
import org.example.repository.Produto;
import org.example.repository.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariables {
    private static ArrayList<Restaurante> restaurantes = new ArrayList<>();
    private final ArrayList<Pedido> pedidos = new ArrayList<>();
    private int restauranteSelecionado;
    private Endereco endereco;
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getRestauranteSelecionado() {
        return restauranteSelecionado;
    }

    public void setRestauranteSelecionado(int restauranteSelecionado) {
        this.restauranteSelecionado = restauranteSelecionado;
    }

    // Método para obter todos os produtos de todos os restaurantes
    public static List<Produto> getProdutos() {
        List<Produto> produtos = new ArrayList<>();
        for (Restaurante restaurante : restaurantes) {
            produtos.addAll(restaurante.getProdutos());
        }
        return produtos;
    }


    // Métodos para restaurantes
    public ArrayList<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void adicionarRestaurante(Restaurante restaurante) {
        this.restaurantes.add(restaurante);
    }

    public void removerRestaurante(int index) {
        this.restaurantes.remove(index);
    }

    public Restaurante buscarPorId(int id) {
        return restaurantes.stream()
                .filter(restaurante -> restaurante.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public Restaurante puxarRestaurantePorId(int id){
        for(Restaurante restaurante : restaurantes){
            if(restaurante.getId() == id){
                return restaurante;
            }
        }
        return null;
    }
}

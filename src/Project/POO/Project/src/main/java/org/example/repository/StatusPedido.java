package org.example.repository;

public enum StatusPedido {
    EM_PRODUCAO("Em Produção"),
    SAIR_PARA_ENTREGA("Saiu para Entrega"),
    ENTREGUE("Entregue");

    private final String status;

    StatusPedido(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

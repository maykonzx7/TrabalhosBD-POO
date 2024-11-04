public class Pedido extends Entidade {
    private String descricao;
    private Cliente cliente;

    public Pedido(int id, String descricao, String status, Cliente cliente) {
        super(id, status);
        this.descricao = descricao;
        this.cliente = cliente;
        cliente.adicionarPedido(this);
    }

    public String getDescricao() {
        return descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Pedido ID: " + getId() + ", Descrição: " + descricao + ", Status: " + getStatus());
    }
}

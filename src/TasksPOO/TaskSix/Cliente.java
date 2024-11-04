import java.util.ArrayList;
import java.util.List;

public class Cliente extends Entidade {
    private String nome;
    private List<Pedido> pedidos;

    public Cliente(int id, String nome, String status) {
        super(id, status);
        this.nome = nome;
        this.pedidos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Cliente ID: " + getId() + ", Nome: " + nome + ", Status: " + getStatus());
    }
}

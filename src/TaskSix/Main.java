public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente(1, "Matias do Bras", Entidade.ATIVO);
        Pedido pedido1 = new Pedido(101, "Compra de um Processador R5 5500X", Entidade.PENDENTE, cliente);
        Pedido pedido2 = new Pedido(102, "Compra de Gabinete Rise Mode", Entidade.ATIVO, cliente);

        cliente.exibirDetalhes();
        for (Pedido pedido : cliente.getPedidos()) {
            pedido.exibirDetalhes();
        }
    }
}

package org.example;

import org.example.DAO.ProdutosDAO;
import org.example.DAO.RestauranteDAO;
import org.example.context.GlobalVariables;
import org.example.repository.*;
import org.example.service.ConexaoMySql;
import org.example.service.ViaCep;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static List<Pedido> pedidosRealizados = new ArrayList<>();

    public static void main(String[] args) throws SQLException {
        Connection conexao = ConexaoMySql.conexao();
        GlobalVariables globalVariables = new GlobalVariables();
        RestauranteDAO restauranteDAO = new RestauranteDAO(conexao, globalVariables);
        ProdutosDAO produtosDAO = new ProdutosDAO(conexao, globalVariables);

        Scanner scanner = new Scanner(System.in);
        List<Produto> produtosNoPedido = new ArrayList<>();
        Pedido pedido = new Pedido(null, null, null, null);

        restauranteDAO.puxarRestaurante();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        int opcao;
        do {
            System.out.println("\n==== Painel de Controle ====");
            System.out.println("1. Listar Restaurantes");
            System.out.println("2. Selecionar Restaurante");
            System.out.println("3. Listar Produtos do Restaurante");
            System.out.println("4. Adicionar Produto ao Pedido");
            System.out.println("5. Visualizar Pedido");
            System.out.println("6. Cadastrar Endereço");
            System.out.println("7. Selecionar Forma de Pagamento");
            System.out.println("8. Finalizar Pedido");
            System.out.println("9. Listar Pedidos Realizados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\nRestaurantes Disponíveis:");
                    ArrayList<Restaurante> restaurantes = globalVariables.getRestaurantes();
                    for (Restaurante restaurante : restaurantes) {
                        System.out.println("ID: " + restaurante.getId() + ", Nome: " + restaurante.getNome() + ", Endereço ID: " + restaurante.getIdEndereco());
                    }
                }

                case 2 -> {
                    System.out.print("Digite o ID do Restaurante: ");
                    int idRestaurante = scanner.nextInt();
                    Restaurante restaurante = globalVariables.buscarPorId(idRestaurante);
                    if (restaurante != null) {
                        System.out.println("Restaurante Selecionado: " + restaurante.getNome());
                        globalVariables.setRestauranteSelecionado(idRestaurante);
                    } else {
                        System.out.println("Restaurante não encontrado!");
                    }
                }

                case 3 -> {
                    System.out.print("Digite o ID do Restaurante para listar os produtos: ");
                    int idRestaurante = scanner.nextInt();
                    List<Produto> produtos = produtosDAO.listarProdutosPorRestaurante(idRestaurante);
                    for (Produto produto : produtos) {
                        System.out.println(produto);
                    }
                }

                case 4 -> {
                    System.out.print("Digite o ID do Produto que deseja adicionar: ");
                    int idProduto = scanner.nextInt();
                    Produto produto = buscarProdutoPorId(idProduto, produtosDAO);
                    if (produto != null) {
                        produtosNoPedido.add(produto);
                    } else {
                        System.out.println("Produto não encontrado!");
                    }
                }

                case 5 -> {
                    System.out.println("\nSeu Pedido:");
                    for (int i = 0; i < produtosNoPedido.size(); i++) {
                        Produto produto = produtosNoPedido.get(i);
                        System.out.printf("%d. %s - R$ %.2f\n", i + 1, produto.getNome(), produto.getPreco());
                    }
                    if (produtosNoPedido.isEmpty()) {
                        System.out.println("Nenhum produto adicionado ao pedido.");
                    }
                }

                case 6 -> {
                    System.out.print("Digite o CEP: ");
                    String cep = scanner.nextLine();
                    Endereco endereco = ViaCep.buscarCEP(cep);
                    if (endereco != null) {
                        System.out.println("Endereço encontrado:");
                        System.out.println("Logradouro: " + endereco.getLogradouro());
                        System.out.println("Bairro: " + endereco.getBairro());
                        System.out.println("Cidade: " + endereco.getLocalidade());
                        System.out.println("Estado: " + endereco.getUf());
                        System.out.println("Região: " + endereco.getRegiao());
                        System.out.println("Numero: " + endereco.getNumero());
                        System.out.println("Complemento: " + endereco.getComplemento());
                        System.out.println("UF: " + endereco.getUf());

                        if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
                            System.out.println("Bairro não encontrado. Digite o Bairro:");
                            endereco.setBairro(scanner.nextLine());
                        }
                        if (endereco.getUf() == null || endereco.getUf().isEmpty()) {
                            System.out.println("UF não encontrado. Digite o UF:");
                            endereco.setUf(scanner.nextLine());
                        }
                        if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty()) {
                            System.out.println("Logradouro não encontrado. Digite o Logradouro:");
                            endereco.setLogradouro(scanner.nextLine());
                        }
                        if (endereco.getLocalidade() == null || endereco.getLocalidade().isEmpty()) {
                            System.out.println("Cidade não encontrada. Digite a Cidade:");
                            endereco.setLocalidade(scanner.nextLine());
                        }
                        if (endereco.getNumero() == null || endereco.getNumero().isEmpty()) {
                            System.out.println("Número não encontrado. Digite o Número:");
                            endereco.setNumero(scanner.nextLine());
                        }
                        if (endereco.getComplemento() == null || endereco.getComplemento().isEmpty()) {
                            System.out.println("Complemento não encontrado. Digite o Complemento:");
                            endereco.setComplemento(scanner.nextLine());
                        }
                        if (endereco.getRegiao() == null || endereco.getRegiao().isEmpty()) {
                            System.out.println("Região não encontrada. Digite a Região:");
                            endereco.setRegiao(scanner.nextLine());
                        }

                        globalVariables.setEndereco(endereco);
                    }
                }

                case 7 -> {
                    System.out.println("Escolha a forma de pagamento: Cartão, Dinheiro, Pix");
                    String formaDePagamento = scanner.nextLine();
                    pedido.setFormaDePagamento(formaDePagamento);
                }

                case 8 -> {
                    System.out.println("\nPedido Finalizado!");
                    Pedido finalPedido = new Pedido(
                            globalVariables.puxarRestaurantePorId(globalVariables.getRestauranteSelecionado()),
                            produtosNoPedido,
                            pedido.getEndereco(),
                            pedido.getFormaDePagamento()
                    );
                    executorService.submit(() -> {
                        try {
                            System.out.println("Pedido em produção...");
                            Thread.sleep(5000);
                            finalPedido.atualizarStatus(StatusPedido.SAIR_PARA_ENTREGA.getStatus());
                            System.out.println("Pedido saiu para entrega.");
                            Thread.sleep(5000);
                            finalPedido.atualizarStatus(StatusPedido.ENTREGUE.getStatus());
                            System.out.println("Pedido entregue.");
                            produtosNoPedido.clear();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    pedidosRealizados.add(finalPedido);
                    pedido = new Pedido(null, null, null, null);

                }

                case 9 -> {
                    System.out.println("Pedidos Realizados:");
                    if (pedidosRealizados.isEmpty()) {
                        System.out.println("Nenhum pedido realizado.");
                    } else {
                        for (int i = 0; i < pedidosRealizados.size(); i++) {
                            Pedido p = pedidosRealizados.get(i);
                            System.out.println("Pedido #" + (i + 1) + ":");
                            System.out.println("Status: " + p.getStatus());
                            System.out.println("Forma de Pagamento: " + p.getFormaDePagamento());
                            Endereco end = globalVariables.getEndereco();
                            if (end != null) {
                                System.out.printf("Endereço: %s, %s, %s - %s - %s\n",
                                        end.getLogradouro(), end.getNumero(),
                                        end.getBairro(), end.getLocalidade(),
                                        end.getUf());
                            } else {
                                System.out.println("Endereço não informado.");
                            }
                        }
                    }
                }

                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        executorService.shutdown();
    }

    private static Produto buscarProdutoPorId(int idProduto, ProdutosDAO produtosDAO) {
        try {
            return produtosDAO.listarProdutosPorRestaurante(idProduto).stream().filter(produto -> produto.getId() == idProduto).findFirst().orElse(null);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
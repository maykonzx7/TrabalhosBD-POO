import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> restaurantesProdutos = new HashMap<>();
        restaurantesProdutos.put("Sabores da Praça", Arrays.asList("Pizza", "Hambúrguer", "Salada"));
        restaurantesProdutos.put("Cantinho Oriental", Arrays.asList("Sushi", "Tempurá", "Yakissoba"));
        restaurantesProdutos.put("Brasileiríssimo Grill", Arrays.asList("Churrasco", "Feijoada", "Picanha"));

        Map<String, List<String>> produtosAdicionais = new HashMap<>();
        produtosAdicionais.put("Pizza", Arrays.asList("Extra Queijo", "Calabresa", "Borda Recheada"));
        produtosAdicionais.put("Hambúrguer", Arrays.asList("Bacon", "Ovo", "Molho Especial"));
        produtosAdicionais.put("Sushi", Arrays.asList("Shoyu", "Wasabi", "Gengibre"));
        produtosAdicionais.put("Churrasco", Arrays.asList("Farofa", "Vinagrete", "Pão de Alho"));

        List<String> carrinho = new ArrayList<>();
        String restauranteSelecionado = null;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Listar Restaurantes");
            System.out.println("2. Listar Produtos do Restaurante Selecionado");
            System.out.println("3. Listar Adicionais do Produto Selecionado");
            System.out.println("4. Visualizar Carrinho");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.println("\nRestaurantes Disponíveis:");
                        int index = 1;
                        for (String restaurante : restaurantesProdutos.keySet()) {
                            System.out.println(index++ + ". " + restaurante);
                        }
                        System.out.print("Selecione um restaurante: ");
                        int escolhaRestaurante = Integer.parseInt(scanner.nextLine()) - 1;

                        if (escolhaRestaurante < 0 || escolhaRestaurante >= restaurantesProdutos.size()) {
                            throw new IllegalArgumentException("Restaurante inválido.");
                        }

                        restauranteSelecionado = new ArrayList<>(restaurantesProdutos.keySet()).get(escolhaRestaurante);
                        System.out.println("Você selecionou: " + restauranteSelecionado);
                        break;

                    case 2:
                        if (restauranteSelecionado == null) {
                            System.out.println("Por favor, selecione um restaurante primeiro (opção 1).");
                            break;
                        }
                        System.out.println("\nProdutos Disponíveis no " + restauranteSelecionado + ":");
                        List<String> produtos = restaurantesProdutos.get(restauranteSelecionado);
                        for (int i = 0; i < produtos.size(); i++) {
                            System.out.println((i + 1) + ". " + produtos.get(i));
                        }
                        System.out.print("Selecione um produto: ");
                        int escolhaProduto = Integer.parseInt(scanner.nextLine()) - 1;

                        if (escolhaProduto < 0 || escolhaProduto >= produtos.size()) {
                            throw new IllegalArgumentException("Produto inválido.");
                        }

                        String produtoSelecionado = produtos.get(escolhaProduto);
                        carrinho.add(produtoSelecionado);
                        System.out.println("Produto '" + produtoSelecionado + "' adicionado ao carrinho.");
                        break;

                    case 3:
                        if (carrinho.isEmpty()) {
                            System.out.println("Adicione um produto ao carrinho antes de selecionar adicionais.");
                            break;
                        }
                        System.out.println("\nÚltimo produto adicionado: " + carrinho.get(carrinho.size() - 1));
                        String produtoParaAdicional = carrinho.get(carrinho.size() - 1);
                        List<String> adicionais = produtosAdicionais.getOrDefault(produtoParaAdicional, null);

                        if (adicionais == null) {
                            System.out.println("Este produto não possui adicionais disponíveis.");
                            break;
                        }

                        System.out.println("Adicionais Disponíveis:");
                        for (int i = 0; i < adicionais.size(); i++) {
                            System.out.println((i + 1) + ". " + adicionais.get(i));
                        }
                        System.out.print("Selecione um adicional: ");
                        int escolhaAdicional = Integer.parseInt(scanner.nextLine()) - 1;

                        if (escolhaAdicional < 0 || escolhaAdicional >= adicionais.size()) {
                            throw new IllegalArgumentException("Adicional inválido.");
                        }

                        String adicionalSelecionado = adicionais.get(escolhaAdicional);
                        carrinho.add("  + " + adicionalSelecionado); // Adicional com indentação
                        System.out.println("Adicional '" + adicionalSelecionado + "' adicionado ao carrinho.");
                        break;

                    case 4:
                        System.out.println("\nCarrinho:");
                        if (carrinho.isEmpty()) {
                            System.out.println("Seu carrinho está vazio.");
                        } else {
                            carrinho.forEach(System.out::println);
                        }
                        break;

                    case 5:
                        System.out.println("Saindo...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número válido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}

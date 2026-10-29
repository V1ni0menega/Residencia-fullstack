import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GerenciadorPedidos gerenciador = new GerenciadorPedidos();

    public static void main(String[] args) {
        popularDadosIniciais();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> cadastrarCliente();
                case 3 -> criarPedido();
                case 4 -> listarProdutos();
                case 5 -> listarClientes();
                case 6 -> listarPedidos();
                case 7 -> visualizarPedido();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("===== SISTEMA DE GERENCIAMENTO DE PEDIDOS =====");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Cadastrar cliente");
        System.out.println("3. Criar pedido");
        System.out.println("4. Listar produtos");
        System.out.println("5. Listar clientes");
        System.out.println("6. Listar pedidos");
        System.out.println("7. Visualizar pedido");
        System.out.println("0. Sair");
        System.out.println("================================================");
    }

    private static void cadastrarProduto() {
        System.out.println("--- Cadastro de Produto ---");
        String nome = lerTexto("Nome do produto: ");
        double preco = lerDouble("Preço: ");
        int estoque = lerInteiro("Quantidade em estoque: ");

        Produto produto = gerenciador.cadastrarProduto(nome, preco, estoque);
        System.out.println("Produto cadastrado com sucesso! " + produto);
    }

    private static void cadastrarCliente() {
        System.out.println("--- Cadastro de Cliente ---");
        String nome = lerTexto("Nome: ");
        String email = lerTexto("Email: ");
        String telefone = lerTexto("Telefone: ");
        String endereco = lerTexto("Endereço: ");

        String resposta = lerTexto("Cliente é VIP? (s/n): ");
        if (resposta.equalsIgnoreCase("s")) {
            double desconto = lerDouble("Percentual de desconto (%): ");
            ClienteVip cliente = gerenciador.cadastrarClienteVip(nome, email, telefone, endereco, desconto);
            System.out.println("Cliente VIP cadastrado com sucesso! " + cliente);
        } else {
            Cliente cliente = gerenciador.cadastrarCliente(nome, email, telefone, endereco);
            System.out.println("Cliente cadastrado com sucesso! " + cliente);
        }
    }

    private static void criarPedido() {
        System.out.println("--- Novo Pedido ---");
        listarClientes();
        int idCliente = lerInteiro("ID do cliente: ");

        Pedido pedido;
        try {
            pedido = gerenciador.criarPedido(idCliente);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        boolean adicionando = true;
        while (adicionando) {
            listarProdutos();
            int idProduto = lerInteiro("ID do produto (0 para finalizar): ");

            if (idProduto == 0) {
                adicionando = false;
                continue;
            }

            Produto produto = gerenciador.buscarProdutoPorId(idProduto);
            if (produto == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            int quantidade = lerInteiro("Quantidade: ");

            try {
                pedido.adicionarItem(produto, quantidade);
                System.out.println("Item adicionado ao pedido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        try {
            pedido.confirmarPedido();
            System.out.println("Pedido confirmado com sucesso!");
            System.out.println(pedido);
        } catch (IllegalStateException e) {
            System.out.println("Erro ao confirmar pedido: " + e.getMessage());
        }
    }

    private static void listarProdutos() {
        System.out.println("--- Produtos Disponíveis ---");
        List<Produto> produtos = gerenciador.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p);
            }
        }
    }

    private static void listarClientes() {
        System.out.println("--- Clientes Cadastrados ---");
        List<Cliente> clientes = gerenciador.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    private static void listarPedidos() {
        System.out.println("--- Pedidos Realizados ---");
        List<Pedido> pedidos = gerenciador.listarPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido realizado.");
        } else {
            for (Pedido p : pedidos) {
                System.out.printf("Pedido #%d | Cliente: %s | Status: %s | Total: R$ %.2f%n",
                        p.getId(), p.getCliente().getNome(), p.getStatus(), p.calcularTotal());
            }
        }
    }

    private static void visualizarPedido() {
        int id = lerInteiro("ID do pedido: ");
        Pedido pedido = gerenciador.buscarPedidoPorId(id);

        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
        } else {
            System.out.println(pedido);
        }
    }

    private static void popularDadosIniciais() {
        gerenciador.cadastrarProduto("Notebook", 3500.00, 10);
        gerenciador.cadastrarProduto("Mouse", 50.00, 50);
        gerenciador.cadastrarProduto("Teclado", 120.00, 30);

        gerenciador.cadastrarCliente("João Silva", "joao@email.com", "11999990000", "Rua A, 123");
        gerenciador.cadastrarClienteVip("Maria Souza", "maria@email.com", "11988880000", "Rua B, 456", 10.0);
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Valor inválido. " + mensagem);
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Valor inválido. " + mensagem);
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}

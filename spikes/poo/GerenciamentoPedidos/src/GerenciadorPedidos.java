import java.util.ArrayList;
import java.util.List;

public class GerenciadorPedidos {
    private List<Produto> produtos;
    private List<Cliente> clientes;
    private List<Pedido> pedidos;
    private int proximoIdProduto;
    private int proximoIdCliente;
    private int proximoIdPedido;

    public GerenciadorPedidos() {
        this.produtos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.proximoIdProduto = 1;
        this.proximoIdCliente = 1;
        this.proximoIdPedido = 1;
    }

    public Produto cadastrarProduto(String nome, double preco, int estoque) {
        Produto produto = new Produto(proximoIdProduto++, nome, preco, estoque);
        produtos.add(produto);
        return produto;
    }

    public Cliente cadastrarCliente(String nome, String email, String telefone, String endereco) {
        Cliente cliente = new Cliente(proximoIdCliente++, nome, email, telefone, endereco);
        clientes.add(cliente);
        return cliente;
    }

    public ClienteVip cadastrarClienteVip(String nome, String email, String telefone, String endereco, double desconto) {
        ClienteVip cliente = new ClienteVip(proximoIdCliente++, nome, email, telefone, endereco, desconto);
        clientes.add(cliente);
        return cliente;
    }

    public Pedido criarPedido(int idCliente) {
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        Pedido pedido = new Pedido(proximoIdPedido++, cliente);
        pedidos.add(pedido);
        return pedido;
    }

    public Produto buscarProdutoPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Cliente buscarClientePorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public Pedido buscarPedidoPorId(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);
    }
}

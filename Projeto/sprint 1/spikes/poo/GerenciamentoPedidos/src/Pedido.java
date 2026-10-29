import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private LocalDateTime dataPedido;
    private String status;
    private static final double TAXA_ENTREGA = 10.0;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.dataPedido = LocalDateTime.now();
        this.status = "Pendente";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return new ArrayList<>(itens);
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null && !status.isEmpty()) {
            this.status = status;
        }
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (produto.temEstoque(quantidade)) {
            for (ItemPedido item : itens) {
                if (item.getProduto().getId() == produto.getId()) {
                    item.setQuantidade(item.getQuantidade() + quantidade);
                    return;
                }
            }
            itens.add(new ItemPedido(produto, quantidade));
        } else {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque para " + produto.getNome());
        }
    }

    public void removerItem(int idProduto) {
        itens.removeIf(item -> item.getProduto().getId() == idProduto);
    }

    public double calcularSubtotal() {
        return itens.stream()
                .mapToDouble(ItemPedido::calcularSubtotal)
                .sum();
    }

    public double calcularTotal() {
        double total = calcularSubtotal() + TAXA_ENTREGA;

        if (cliente instanceof ClienteVip) {
            ClienteVip vip = (ClienteVip) cliente;
            total = vip.aplicarDesconto(total);
        }

        return total;
    }

    public int obterQuantidadeItens() {
        return itens.size();
    }

    public void confirmarPedido() {
        if (itens.isEmpty()) {
            throw new IllegalStateException("Não é possível confirmar um pedido sem itens");
        }

        for (ItemPedido item : itens) {
            item.getProduto().diminuirEstoque(item.getQuantidade());
        }
        this.status = "Confirmado";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== PEDIDO ==========\n");
        sb.append(String.format("ID: %d | Status: %s\n", id, status));
        sb.append(String.format("Data: %s\n", dataPedido.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append("\n--- CLIENTE ---\n");
        sb.append(cliente).append("\n");
        sb.append("\n--- ITENS ---\n");

        if (itens.isEmpty()) {
            sb.append("Nenhum item no pedido\n");
        } else {
            for (int i = 0; i < itens.size(); i++) {
                sb.append((i + 1)).append(". ").append(itens.get(i)).append("\n");
            }
        }

        sb.append("\n--- TOTAIS ---\n");
        sb.append(String.format("Subtotal: R$ %.2f\n", calcularSubtotal()));
        sb.append(String.format("Taxa de Entrega: R$ %.2f\n", TAXA_ENTREGA));
        sb.append(String.format("TOTAL: R$ %.2f\n", calcularTotal()));
        sb.append("============================\n");

        return sb.toString();
    }
}

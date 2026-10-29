public class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        if (quantidade > 0) {
            this.produto = produto;
            this.quantidade = quantidade;
        } else {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade > 0) {
            this.quantidade = quantidade;
        } else {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
    }

    public double calcularSubtotal() {
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return String.format("%s | Quantidade: %d | Subtotal: R$ %.2f",
                produto.getNome(), quantidade, calcularSubtotal());
    }
}

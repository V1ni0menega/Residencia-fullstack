public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(int id, String nome, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco > 0) {
            this.preco = preco;
        } else {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        if (estoque >= 0) {
            this.estoque = estoque;
        } else {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
    }

    public boolean temEstoque(int quantidade) {
        return this.estoque >= quantidade;
    }

    public void diminuirEstoque(int quantidade) {
        if (temEstoque(quantidade)) {
            this.estoque -= quantidade;
        } else {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }
    }

    @Override
    public String toString() {
        return String.format("Produto [ID: %d, Nome: %s, Preço: R$ %.2f, Estoque: %d]",
                id, nome, preco, estoque);
    }
}

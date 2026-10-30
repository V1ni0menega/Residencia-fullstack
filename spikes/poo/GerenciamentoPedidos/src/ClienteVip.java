public class ClienteVip extends Cliente {
    private double percentualDesconto;

    public ClienteVip(int id, String nome, String email, String telefone, String endereco, double percentualDesconto) {
        super(id, nome, email, telefone, endereco);
        setPercentualDesconto(percentualDesconto);
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(double percentualDesconto) {
        if (percentualDesconto >= 0 && percentualDesconto <= 100) {
            this.percentualDesconto = percentualDesconto;
        } else {
            throw new IllegalArgumentException("Percentual de desconto deve estar entre 0 e 100");
        }
    }

    public double aplicarDesconto(double valor) {
        return valor - (valor * percentualDesconto / 100);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [VIP - Desconto: %.0f%%]", percentualDesconto);
    }
}

/**
 * CLASSE CALCULADORA
 * ------------------
 * Representa uma calculadora com as 4 operações básicas.
 * Aplica os conceitos de POO: encapsulamento, atributos, métodos e construtor.
 *
 * Residência Fullstack — Exercício Terça-feira
 */
public class Calculadora {

    // ── ATRIBUTOS ─────────────────────────────────────────────
    // 'private' = encapsulamento (ninguém acessa diretamente de fora)

    private int totalOperacoes;     // Conta quantas operações foram feitas
    private double ultimoResultado; // Guarda o último resultado calculado

    // ── CONSTRUTOR ────────────────────────────────────────────
    // É chamado quando fazemos 'new Calculadora()'
    // Inicializa os atributos com valores padrão

    public Calculadora() {
        this.totalOperacoes = 0;
        this.ultimoResultado = 0;
    }

    // ── MÉTODOS DAS OPERAÇÕES ─────────────────────────────────

    /** SOMA: retorna a adição de dois números. */
    public double somar(double a, double b) {
        double resultado = a + b;
        this.ultimoResultado = resultado;
        this.totalOperacoes++;
        return resultado;
    }

    /** SUBTRAÇÃO: retorna a diferença entre dois números. */
    public double subtrair(double a, double b) {
        double resultado = a - b;
        this.ultimoResultado = resultado;
        this.totalOperacoes++;
        return resultado;
    }

    /** MULTIPLICAÇÃO: retorna o produto de dois números. */
    public double multiplicar(double a, double b) {
        double resultado = a * b;
        this.ultimoResultado = resultado;
        this.totalOperacoes++;
        return resultado;
    }

    /**
     * DIVISÃO: retorna o quociente.
     * Lança ArithmeticException se o divisor for zero.
     */
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Erro: Divisão por zero não é permitida!");
        }
        double resultado = a / b;
        this.ultimoResultado = resultado;
        this.totalOperacoes++;
        return resultado;
    }

    // ── GETTERS ───────────────────────────────────────────────

    public int getTotalOperacoes() {
        return totalOperacoes;
    }

    public double getUltimoResultado() {
        return ultimoResultado;
    }

    // ── MÉTODO DE APRESENTAÇÃO ────────────────────────────────

    /** Exibe o resumo da sessão. */
    public void exibirHistorico() {
        System.out.println("\n📊 Histórico da sessão:");
        System.out.println("   Total de operações: " + totalOperacoes);
        System.out.printf("   Último resultado:   %.2f%n", ultimoResultado);
    }
}

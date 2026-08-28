import java.util.Scanner;

/**
 * CALCULADORAAPP — Programa Principal
 * ------------------------------------
 * Interface com o usuário. Cria e usa um objeto Calculadora.
 * Aplica: loop while, switch, try-catch, printf.
 *
 * Residência Fullstack — Exercício Terça-feira
 *
 * Para compilar e executar:
 *   javac Calculadora.java CalculadoraApp.java
 *   java CalculadoraApp
 */
public class CalculadoraApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Criando o OBJETO da classe Calculadora
        Calculadora calc = new Calculadora();

        // Banner
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║        🧮 CALCULADORA COM POO 🧮       ║");
        System.out.println("║     Residência Fullstack — Java        ║");
        System.out.println("╚═══════════════════════════════════════╝");

        boolean continuar = true;

        while (continuar) {

            System.out.println("\n─────────────────────────────────────");
            System.out.println("Escolha a operação:");
            System.out.println("  1. ➕ Soma");
            System.out.println("  2. ➖ Subtração");
            System.out.println("  3. ✖️  Multiplicação");
            System.out.println("  4. ➗ Divisão");
            System.out.println("  5. 📊 Ver histórico");
            System.out.println("  0. 🚪 Sair");
            System.out.print("\nOpção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    double[] n1 = pedirDoisNumeros(scanner);
                    double soma = calc.somar(n1[0], n1[1]);
                    System.out.printf("✅ Resultado: %.2f + %.2f = %.2f%n", n1[0], n1[1], soma);
                    break;

                case 2:
                    double[] n2 = pedirDoisNumeros(scanner);
                    double sub = calc.subtrair(n2[0], n2[1]);
                    System.out.printf("✅ Resultado: %.2f - %.2f = %.2f%n", n2[0], n2[1], sub);
                    break;

                case 3:
                    double[] n3 = pedirDoisNumeros(scanner);
                    double mult = calc.multiplicar(n3[0], n3[1]);
                    System.out.printf("✅ Resultado: %.2f × %.2f = %.2f%n", n3[0], n3[1], mult);
                    break;

                case 4:
                    double[] n4 = pedirDoisNumeros(scanner);
                    try {
                        double div = calc.dividir(n4[0], n4[1]);
                        System.out.printf("✅ Resultado: %.2f ÷ %.2f = %.2f%n", n4[0], n4[1], div);
                    } catch (ArithmeticException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    break;

                case 5:
                    calc.exibirHistorico();
                    break;

                case 0:
                    continuar = false;
                    calc.exibirHistorico();
                    System.out.println("\n👋 Até logo! Obrigado por usar a calculadora!");
                    break;

                default:
                    System.out.println("⚠️  Opção inválida! Digite um número de 0 a 5.");
            }
        }

        scanner.close();
    }

    /**
     * Método auxiliar: pede dois números e retorna como array.
     */
    private static double[] pedirDoisNumeros(Scanner scanner) {
        System.out.print("📥 Digite o primeiro número: ");
        double num1 = scanner.nextDouble();
        System.out.print("📥 Digite o segundo número: ");
        double num2 = scanner.nextDouble();
        scanner.nextLine();
        return new double[]{num1, num2};
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ============================================================
 *  DETECTOR DE GOLPES DIGITAIS
 *  Residência Fullstack — Semana 2 | Challenge Backend Java
 *  Big Idea: Segurança Digital
 * ============================================================
 *
 *  O programa analisa uma mensagem de texto e detecta padrões
 *  comuns em golpes digitais (phishing, scam, fraude).
 *
 *  Regras de negócio:
 *  - RN-01: Urgência       → +20 pontos
 *  - RN-02: Prêmio/sorteio → +25 pontos
 *  - RN-03: Pedido de dados → +30 pontos
 *  - RN-04: Link suspeito  → +25 pontos
 *  - RN-05: Ameaça         → +20 pontos
 *  - RN-06: Promessa $     → +20 pontos
 *
 *  Classificação:
 *  - Score  < 20  → ✅ SEGURO
 *  - Score 20–60  → ⚠️  SUSPEITO
 *  - Score  > 60  → 🚨 PERIGO (provável golpe)
 */
public class DetectorGolpes {

    // ─── Palavras-chave por categoria ─────────────────────────────────────────

    // RN-01: Urgência — cria pressão para agir rápido
    private static final String[] PALAVRAS_URGENCIA = {
        "urgente", "urgência", "agora", "imediato", "imediatamente",
        "24 horas", "24h", "hoje", "rápido", "prazo", "expira",
        "último dia", "não perca", "corra", "atenção"
    };

    // RN-02: Prêmio / Sorteio — promessas de ganhos fáceis
    private static final String[] PALAVRAS_PREMIO = {
        "parabéns", "ganhou", "ganhador", "prêmio", "sorteado",
        "contemplado", "selecionado", "benefício", "recompensa",
        "você foi escolhido", "feliz ganhador"
    };

    // RN-03: Pedido de dados pessoais — o maior sinal de alerta
    private static final String[] PALAVRAS_DADOS = {
        "cpf", "senha", "código", "token", "dados bancários",
        "confirme seus dados", "data de nascimento", "número do cartão",
        "cvv", "conta bancária", "chave pix", "rg", "informações pessoais",
        "autenticação", "verificação"
    };

    // RN-04: Links suspeitos — domínios estranhos e encurtadores
    private static final String[] PALAVRAS_LINK = {
        "bit.ly", "tinyurl", "shorturl", "goo.gl",
        ".xyz", ".space", ".click", ".online", ".shop",
        "http://", "clique aqui", "acesse o link", "clique no link",
        "acesse agora"
    };

    // RN-05: Ameaças — indução pelo medo
    private static final String[] PALAVRAS_AMEACA = {
        "bloqueado", "suspenso", "cancelado", "encerrado",
        "irregular", "problema", "pendência", "negativado",
        "será cancelada", "será bloqueada", "perderá", "expirou"
    };

    // RN-06: Promessas financeiras — dinheiro fácil
    private static final String[] PALAVRAS_DINHEIRO = {
        "r$", "reais", "transferência", "depósito", "pix",
        "dinheiro", "saque", "crédito aprovado", "empréstimo",
        "bolsa", "auxílio", "restituição"
    };

    // ─── Método principal ─────────────────────────────────────────────────────

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Banner de boas-vindas
        exibirBanner();

        // Loop principal: o usuário pode analisar várias mensagens
        String continuar = "s";

        while (continuar.equalsIgnoreCase("s")) {

            // 1. Entrada: receber a mensagem do usuário
            System.out.println("\n📩 Cole ou digite a mensagem que deseja analisar:");
            System.out.println("   (pressione Enter duas vezes para finalizar)");
            System.out.println("─".repeat(60));

            String mensagem = lerMensagemMultilinha(scanner);

            if (mensagem.isBlank()) {
                System.out.println("⚠️  Mensagem vazia. Por favor, digite algo.");
                continue;
            }

            // 2. Processamento: analisar a mensagem
            ResultadoAnalise resultado = analisarMensagem(mensagem);

            // 3. Saída: exibir o resultado
            exibirResultado(resultado, mensagem);

            // Perguntar se quer analisar outra
            System.out.println("\n🔄 Deseja analisar outra mensagem? (s/n): ");
            continuar = scanner.nextLine().trim();
        }

        System.out.println("\n👋 Obrigado por usar o Detector de Golpes Digitais!");
        System.out.println("   Fique seguro online! 🛡️\n");

        scanner.close();
    }

    // ─── Análise da Mensagem ──────────────────────────────────────────────────

    /**
     * Analisa a mensagem e retorna um ResultadoAnalise com score e sinais.
     * Converte para minúsculas para busca case-insensitive.
     */
    private static ResultadoAnalise analisarMensagem(String mensagem) {

        // Converte para minúsculas para não diferenciar "URGENTE" de "urgente"
        String mensagemLower = mensagem.toLowerCase();

        int score = 0;
        List<String> sinaisEncontrados = new ArrayList<>();

        // Verifica cada categoria e acumula pontos (RN-01 a RN-06)
        if (contemAlguma(mensagemLower, PALAVRAS_URGENCIA)) {
            score += 20;
            sinaisEncontrados.add("⏰ Urgência detectada (+20 pts)");
        }

        if (contemAlguma(mensagemLower, PALAVRAS_PREMIO)) {
            score += 25;
            sinaisEncontrados.add("🎁 Promessa de prêmio/sorteio (+25 pts)");
        }

        if (contemAlguma(mensagemLower, PALAVRAS_DADOS)) {
            score += 30;
            sinaisEncontrados.add("🔑 Pedido de dados pessoais (+30 pts) — ALTO RISCO");
        }

        if (contemAlguma(mensagemLower, PALAVRAS_LINK)) {
            score += 25;
            sinaisEncontrados.add("🔗 Link ou redirecionamento suspeito (+25 pts)");
        }

        if (contemAlguma(mensagemLower, PALAVRAS_AMEACA)) {
            score += 20;
            sinaisEncontrados.add("😨 Ameaça ou intimidação detectada (+20 pts)");
        }

        if (contemAlguma(mensagemLower, PALAVRAS_DINHEIRO)) {
            score += 20;
            sinaisEncontrados.add("💰 Promessa financeira (+20 pts)");
        }

        // Determina o nível de risco
        NivelRisco nivel = calcularNivel(score);

        return new ResultadoAnalise(score, nivel, sinaisEncontrados);
    }

    /**
     * Verifica se a mensagem contém pelo menos uma das palavras do array.
     *
     * @param mensagem  texto em minúsculas
     * @param palavras  array de palavras-chave
     * @return          true se encontrou alguma
     */
    private static boolean contemAlguma(String mensagem, String[] palavras) {
        for (String palavra : palavras) {
            if (mensagem.contains(palavra)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula o nível de risco com base no score.
     * RN-07, RN-08 e RN-09.
     */
    private static NivelRisco calcularNivel(int score) {
        if (score > 60) {
            return NivelRisco.PERIGO;
        } else if (score >= 20) {
            return NivelRisco.SUSPEITO;
        } else {
            return NivelRisco.SEGURO;
        }
    }

    // ─── Exibição do Resultado ────────────────────────────────────────────────

    private static void exibirResultado(ResultadoAnalise resultado, String mensagem) {

        System.out.println("\n" + "═".repeat(60));
        System.out.println("           📊 RESULTADO DA ANÁLISE");
        System.out.println("═".repeat(60));

        // Prévia da mensagem analisada (truncada se muito longa)
        String previa = mensagem.length() > 80
                ? mensagem.substring(0, 80) + "..."
                : mensagem;
        System.out.println("\n📩 Mensagem analisada:");
        System.out.println("   \"" + previa + "\"");

        System.out.println("\n" + "─".repeat(60));

        // Exibe sinais encontrados
        if (resultado.sinais.isEmpty()) {
            System.out.println("   ✅ Nenhum sinal suspeito encontrado.");
        } else {
            System.out.println("   🔍 Sinais encontrados:");
            for (String sinal : resultado.sinais) {
                System.out.println("      → " + sinal);
            }
        }

        System.out.println("\n" + "─".repeat(60));

        // Barra de progresso do score
        System.out.println("   📈 Score de risco: " + resultado.score + " / 140 pts");
        System.out.println("   " + gerarBarraProgresso(resultado.score, 140));

        System.out.println("\n" + "─".repeat(60));

        // Veredicto final
        switch (resultado.nivel) {
            case SEGURO:
                System.out.println("   ✅ VEREDICTO: SEGURO");
                System.out.println("   Esta mensagem parece legítima.");
                System.out.println("   Mesmo assim, sempre desconfie de pedidos inesperados.");
                break;

            case SUSPEITO:
                System.out.println("   ⚠️  VEREDICTO: SUSPEITO");
                System.out.println("   Esta mensagem tem alguns sinais de alerta.");
                System.out.println("   Não clique em links e verifique a fonte antes de agir.");
                break;

            case PERIGO:
                System.out.println("   🚨 VEREDICTO: PERIGO — PROVÁVEL GOLPE!");
                System.out.println("   Múltiplos padrões de fraude detectados.");
                System.out.println("   NÃO forneça dados, NÃO clique em links,");
                System.out.println("   NÃO faça transferências. Bloqueie e denuncie!");
                break;
        }

        System.out.println("═".repeat(60));
    }

    /**
     * Gera uma barra visual de progresso para o score.
     * Ex: [████████░░░░░░░░░░░░] 40%
     */
    private static String gerarBarraProgresso(int score, int maximo) {
        int porcentagem = Math.min((score * 100) / maximo, 100);
        int blocosCheios = porcentagem / 5; // 20 blocos total
        int blocosVazios = 20 - blocosCheios;

        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < blocosCheios; i++) barra.append("█");
        for (int i = 0; i < blocosVazios; i++) barra.append("░");
        barra.append("] ").append(porcentagem).append("%");

        return barra.toString();
    }

    // ─── Utilitários ──────────────────────────────────────────────────────────

    /**
     * Lê uma mensagem multilinhas. O usuário termina pressionando Enter em
     * uma linha vazia.
     */
    private static String lerMensagemMultilinha(Scanner scanner) {
        StringBuilder sb = new StringBuilder();
        String linha;

        // Consome qualquer quebra de linha pendente
        if (scanner.hasNextLine()) {
            linha = scanner.nextLine();
            // Se a primeira linha já estiver vazia, aguarda input real
            if (linha.isBlank()) {
                linha = scanner.nextLine();
            }
            sb.append(linha);
        }

        // Continua lendo até linha em branco
        while (scanner.hasNextLine()) {
            linha = scanner.nextLine();
            if (linha.isBlank()) break;
            sb.append(" ").append(linha);
        }

        return sb.toString().trim();
    }

    private static void exibirBanner() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║        🛡️  DETECTOR DE GOLPES DIGITAIS  🛡️              ║");
        System.out.println("║        Residência Fullstack — Backend com Java           ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  Este programa analisa mensagens de texto e identifica");
        System.out.println("  padrões comuns em golpes digitais (phishing e fraudes).");
        System.out.println();
        System.out.println("  Categorias analisadas:");
        System.out.println("  ⏰ Urgência  🎁 Prêmio  🔑 Pedido de dados");
        System.out.println("  🔗 Links     😨 Ameaças  💰 Promessas financeiras");
    }

    // ─── Classes internas ─────────────────────────────────────────────────────

    /**
     * Enum com os três níveis de risco possíveis.
     * Enums são tipos especiais com valores fixos e predefinidos.
     */
    enum NivelRisco {
        SEGURO,
        SUSPEITO,
        PERIGO
    }

    /**
     * Agrupa o resultado da análise: score, nível e lista de sinais.
     * É como uma "caixa" que carrega todas as informações do resultado.
     */
    static class ResultadoAnalise {
        int score;
        NivelRisco nivel;
        List<String> sinais;

        ResultadoAnalise(int score, NivelRisco nivel, List<String> sinais) {
            this.score = score;
            this.nivel = nivel;
            this.sinais = sinais;
        }
    }
}

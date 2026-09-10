# 📋 Semana 2 — Challenge: Detector de Golpes Digitais

> **Disciplina:** Residência Fullstack | **Semana:** 2 | **Tema:** Segurança Digital — Backend com Java

---

## 🌐 Big Idea

**Segurança Digital**

Em um mundo cada vez mais conectado, ameaças digitais como phishing, fraudes por WhatsApp e e-mails falsos se tornaram parte do cotidiano. Identificar essas ameaças de forma rápida e automatizada é um desafio real do mercado de tecnologia — e é exatamente isso que vamos resolver com código.

---

## ❓ Essential Question

> **Como a tecnologia pode nos ajudar a identificar e nos proteger de golpes digitais de forma automática?**

---

## 🎯 Challenge

**Criar um programa em Java capaz de analisar uma mensagem de texto e detectar se ela é um golpe digital (phishing, fraude, scam), apresentando o resultado no terminal com uma pontuação de risco.**

---

## 🔍 Discovery — Semana 1 (Revisão)

### O que investigamos?

Na Semana 1 fizemos o **Engage + Investigate**, onde discutimos sobre golpes digitais e levantamos os principais sinais de risco em mensagens fraudulentas.

#### Exemplos de mensagens fraudulentas analisadas:
- *"Parabéns! Você ganhou um prêmio de R$ 5.000,00. Clique aqui para resgatar: bit.ly/premio-falso"*
- *"Seu CPF foi bloqueado. Acesse urgentemente: www.banco-seguro.xyz para regularizar"*
- *"Oi, sou do suporte do Bradesco. Preciso do seu código de verificação agora!"*
- *"ATENÇÃO: Sua conta será cancelada em 24h. Clique: http://atualizar-dados.net"*

#### Sinais de risco levantados:
| Sinal | Descrição |
|---|---|
| Urgência | Palavras como "urgente", "agora", "24h", "imediato" |
| Prêmio falso | "ganhou", "prêmio", "sorteado", "contemplado" |
| Pedido de dados | "CPF", "senha", "código", "confirmação", "dados bancários" |
| Link suspeito | URLs encurtadas (bit.ly, tinyurl) ou domínios estranhos (.xyz, .space) |
| Ameaça | "bloqueado", "cancelado", "suspenso", "irregular" |
| Promessa financeira | "transferência", "R$", "dinheiro", "depósito", "Pix" |

---

## 💡 Guiding Questions

> Perguntas que guiaram nossa pesquisa e desenvolvimento da solução.

### Sobre o Problema
1. **O que é phishing e como ele funciona?**
   - Phishing é uma técnica onde criminosos se passam por entidades legítimas (bancos, governo, empresas) para enganar a vítima e obter dados pessoais ou dinheiro.

2. **Quais são os padrões mais comuns em mensagens de golpe?**
   - Criação de urgência, promessas irrealistas, pedido de informações sensíveis, links falsos e erros gramaticais.

3. **Como um programa pode "ler" uma mensagem e identificar padrões?**
   - Usando processamento de Strings em Java: busca por palavras-chave, comparação de padrões e sistema de pontuação (score).

4. **É possível ter falsos positivos? Como minimizá-los?**
   - Sim. Um sistema de pontuação ajuda: em vez de bloquear com 1 palavra, exige acúmulo de sinais para classificar como golpe.

5. **Quais dados o programa precisa receber como entrada?**
   - A mensagem de texto que o usuário quer verificar.

6. **Como apresentar o resultado de forma clara para o usuário?**
   - Com um score de risco (0–100), nível de alerta (SEGURO, SUSPEITO, PERIGO) e lista dos sinais encontrados.

### Sobre a Tecnologia
7. **O que é Java e por que é usado em backend?**
   - Java é uma linguagem fortemente tipada, robusta e multiplataforma, muito usada em sistemas bancários, governamentais e enterprise.

8. **Como funciona a leitura de dados pelo terminal em Java?**
   - Usando a classe `Scanner` do pacote `java.util`.

9. **Como buscar palavras dentro de uma String em Java?**
   - Usando os métodos `.toLowerCase()`, `.contains()` e `.split()` da classe `String`.

---

## 🔬 Guiding Activities

> O que fizemos para responder as perguntas acima.

| Atividade | Descrição | Resultado |
|---|---|---|
| Análise de casos reais | Coletamos mensagens reais de golpe (WhatsApp, SMS, e-mail) | Lista de 30+ palavras-chave suspeitas |
| Mapeamento de padrões | Categorizamos os sinais em 6 grupos de risco | Tabela de critérios de detecção |
| Pesquisa sobre Java | Estudamos fundamentos de Java: Scanner, String, if/else | Código funcional no terminal |
| Fluxo da solução | Desenhamos o fluxograma do algoritmo | Fluxograma abaixo |
| Testes | Criamos mensagens-teste (golpe e legítima) para validar | 4 cenários de teste documentados |

---

## 🗺️ Fluxograma da Solução

```
┌─────────────────────────────────────────────────────────────┐
│                    DETECTOR DE GOLPES                       │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │  Usuário digita │
                    │  a mensagem     │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │  Converter para │
                    │  letras minúsc. │
                    └────────┬────────┘
                             │
                             ▼
              ┌──────────────────────────────┐
              │   Verificar cada categoria:  │
              │                              │
              │  [1] Urgência?      +20 pts  │
              │  [2] Prêmio falso?  +25 pts  │
              │  [3] Pedido dados?  +30 pts  │
              │  [4] Link suspeito? +25 pts  │
              │  [5] Ameaça?        +20 pts  │
              │  [6] Promessa $?    +20 pts  │
              └──────────────┬───────────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │  Calcular score │
                    │  total de risco │
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              │              │              │
              ▼              ▼              ▼
         score < 20    20 ≤ score ≤ 60   score > 60
              │              │              │
              ▼              ▼              ▼
         ✅ SEGURO      ⚠️ SUSPEITO    🚨 PERIGO
```

---

## 💡 Ideia — Nossa Solução

### Descrição da Solução

Desenvolvemos um **analisador de mensagens** em Java que funciona como um "detetive digital". O programa:

1. **Recebe** uma mensagem de texto pelo terminal
2. **Processa** a mensagem convertendo para minúsculas (case-insensitive)
3. **Analisa** a mensagem em 6 categorias de risco, atribuindo pontos para cada sinal encontrado
4. **Calcula** um score total de 0 a 100+
5. **Classifica** a mensagem em 3 níveis: SEGURO, SUSPEITO ou PERIGO
6. **Exibe** o resultado com todos os sinais encontrados

### Regras de Negócio

| Regra | Descrição | Score |
|---|---|---|
| RN-01 | Se a mensagem contiver palavras de urgência → pontua | +20 pts |
| RN-02 | Se a mensagem contiver palavras de prêmio/sorteio → pontua | +25 pts |
| RN-03 | Se a mensagem solicitar dados pessoais → pontua | +30 pts |
| RN-04 | Se a mensagem contiver links suspeitos → pontua | +25 pts |
| RN-05 | Se a mensagem contiver ameaças → pontua | +20 pts |
| RN-06 | Se a mensagem contiver promessas financeiras → pontua | +20 pts |
| RN-07 | Score < 20 → classificação SEGURO | — |
| RN-08 | Score entre 20 e 60 → classificação SUSPEITO | — |
| RN-09 | Score > 60 → classificação PERIGO (provável golpe) | — |

---

## 🧪 Cenários de Teste

### Cenário 1 — Mensagem de Golpe Óbvio (score esperado: PERIGO 🚨)
```
Entrada:
"URGENTE! Seu CPF foi bloqueado. Acesse agora bit.ly/regularize e informe 
sua senha e código do banco para evitar o cancelamento da sua conta. 
Você também foi sorteado para receber R$1.000,00!"

Sinais esperados:
✅ Urgência detectada (URGENTE, agora)
✅ Pedido de dados (CPF, senha, código)
✅ Link suspeito (bit.ly)
✅ Ameaça (bloqueado, cancelamento)
✅ Prêmio falso (sorteado)
✅ Promessa financeira (R$1.000,00)

Score esperado: 140 → PERIGO
```

### Cenário 2 — Mensagem Legítima (score esperado: SEGURO ✅)
```
Entrada:
"Oi! Tudo bem? Vai poder aparecer no churrasquinho de sábado? 
Começa às 14h na casa do João. Leva aquela farofa que você faz!"

Sinais esperados: nenhum
Score esperado: 0 → SEGURO
```

### Cenário 3 — Mensagem Suspeita (score esperado: SUSPEITO ⚠️)
```
Entrada:
"Você foi selecionado para receber um benefício especial. 
Entre em contato urgente pelo link: www.beneficio-especial.com"

Sinais esperados:
✅ Prêmio/benefício detectado
✅ Urgência detectada
✅ Link suspeito (.com diferente do oficial)

Score esperado: 65 → PERIGO (borda)
```

### Cenário 4 — Mensagem de Suporte Falso (score esperado: PERIGO 🚨)
```
Entrada:
"Sou do suporte da Caixa Econômica. Sua conta será suspensa em 24 horas. 
Para evitar o bloqueio, confirme seus dados: CPF, data de nascimento e 
token do aplicativo. Clique: http://caixa-atualiza.net"

Sinais esperados:
✅ Urgência (24 horas)
✅ Pedido de dados (CPF, token)
✅ Ameaça (suspensa, bloqueio)
✅ Link suspeito (caixa-atualiza.net)

Score esperado: 95 → PERIGO
```

---

## 📁 Estrutura do Projeto

```
semana-2/
└── Challenge/
    ├── README.md              ← Descrição do challenge (original)
    ├── SEMANA2_DOCS.md        ← Este arquivo (documentação CBL)
    └── src/
        └── DetectorGolpes.java ← Código-fonte da solução
```

---

## 👥 Grupo

- Residência Fullstack — Turma [sua turma]
- Semana 2 / Aula 03 e 04

---

## 📅 Cronograma de Entrega

| Etapa | Status | Descrição |
|---|---|---|
| Semana 1 — Aula 01 | ✅ Concluído | Engage + Investigate: discussão sobre golpes |
| Semana 1 — Aula 02 | ✅ Concluído | Definição das regras + fluxograma |
| Semana 2 — Aula 03 | 🔄 Em andamento | Desenvolvimento em Java |
| Semana 2 — Aula 04 | 🔄 Pendente | Checkpoint 2 + validação |
| Semana 3 — Aula 05 | ⏳ Futuro | Finalização e ajustes |
| Semana 3 — Aula 06 | ⏳ Futuro | Apresentação (5 min + 5 min feedback) |

---

*Documentação gerada na Semana 2 — Residência Fullstack*

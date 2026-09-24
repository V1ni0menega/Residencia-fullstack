---
description: Sincroniza a documentação da sessão (checkpoint diário, diário de bordo técnico e documentação de estudo) e gera comandos de commit
---

# 📚 Workflow: Doc-Sync — Checkpoint & Aprendizados da Residência

Disparado por `/doc-sync`, `/checkpoint` ou `/fim-de-sessao`.  
O "Registrador Técnico e Tech Lead".

Ao ser acionado, o agente executa rigorosamente as etapas abaixo:

---

## 1. Inspecionar o que foi feito na sessão
- Executar `git status` e `git diff HEAD` para mapear exatamente os arquivos criados ou modificados.
- Identificar em qual módulo da Residência Fullstack estamos atuando (`frontend/`, `backend/` ou `db/`).
- Recuperar da conversa os conceitos teóricos discutidos, bugs resolvidos e decisões de arquitetura tomadas.

---

## 2. Atualizar o Checkpoint de Sessão
**Arquivo:** `docs/checkpoint.md`

Adicionar uma nova entrada no topo do histórico com o formato:

```markdown
## 📅 [DATA] — [Módulo da Residência] | Sessão [N]

### ✅ O que foi trabalhado
- Lista objetiva das tarefas e componentes implementados
- Módulo/Challenge de referência (ex: *Módulo Frontend — Challenge Portfólio*)
- Arquivos criados ou alterados

### 📍 Onde paramos (Estado Atual)
- Descrição clara do ponto exato em que a sessão foi interrompida
- O que está funcionando e o que ficou pela metade

### 🔜 Próximos passos para a próxima sessão
- Lista priorizada do que deve ser atacado assim que abrir o projeto
```

---

## 3. Atualizar o Diário de Aprendizados Geral
**Arquivo:** `docs/aprendizados.md`

Adicionar uma nova entrada com a síntese conceitual do dia:

```markdown
## 📅 [DATA] — [Tema Técnico da Sessão]

### 💡 Conceitos dominados
- **[Conceito]**: Explicação simples, prática e direta do que foi aprendido e por que é relevante no mercado.

### 🚧 Desafios & Soluções (Troubleshooting)
- **Problema encontrado** ➔ Causa-raiz e como foi solucionado no código.

### 🏆 Decisões de Arquitetura & Boas Práticas
- **Decisão técnica**: Motivação e por que escolhemos essa abordagem em vez de outra.
```

---

## 4. Criar ou Atualizar a Documentação Específica da Atividade / Challenge
**Diretório:** `docs/atividades/[modulo]-[nome-da-atividade].md`

> [!IMPORTANT]
> Além do resumo macro em `docs/aprendizados.md`, cada grande funcionalidade ou challenge trabalhado deve ter um guia técnico aprofundado para estudo posterior do desenvolvedor, contendo:
> - **Objetivo do desafio/feature**
> - **Conceitos de Engenharia de Software e Frontend/Backend/DB** aplicados
> - **Snippets de código finais e comentados**
> - **Tópicos para estudo individual e aprofundamento**

---

## 5. Gerar os Comandos de Commit (NÃO EXECUTAR)
Seguir rigorosamente o padrão do workflow `/commit` (Conventional Commits):
- Agrupar por escopo/assunto atômico.
- Exibir os blocos `git add` e `git commit` formatados como texto pronto para o desenvolvedor copiar e rodar no terminal.

> [!CAUTION]
> O agente **NUNCA DEVE** executar `git commit` ou `git push` diretamente. Apenas fornece os comandos para o usuário executar.

---

## 6. Encerramento Tech Lead
Finalizar com um resumo motivacional da evolução do desenvolvedor na sessão e uma prévia empolgante do próximo passo prático.

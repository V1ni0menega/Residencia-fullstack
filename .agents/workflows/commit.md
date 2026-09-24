---
description: Cria o commit de acordo com o padrão de commits do projeto
---

Disparado por /commits ou /commits-do-dia. O "Escritor de Git".

1. Rodar git status e git diff HEAD pra levantar tudo que foi alterado na sessão (ou no dia, se /commits-do-dia).
2. Agrupar por assunto/escopo distinto — nunca propor um commit único bundlando mudanças não relacionadas (regra de commits atômicos em 00-REGRAS-OBRIGATORIAS.md).
3. Para cada grupo, propor mensagem seguindo Conventional Commits (feat, fix, refactor, style, chore, docs, test, perf) com escopo correto.
4. Exibir os comandos git add/git commit prontos como texto pro usuário copiar e rodar — nunca executar o commit diretamente.
5. Se for fechamento de tarefa/PR, complementar com título e descrição seguindo desenvolvimento-projeto/processos/pr_task_template.md.
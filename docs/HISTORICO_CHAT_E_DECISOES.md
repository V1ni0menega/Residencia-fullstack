# 📝 Registro de Histórico de Conversas e Decisões

> **Data:** 27 de Agosto de 2026  
> **Workspace:** Residência Fullstack / TechPulse  
> **Objetivo:** Registro permanente das conversas, ideias, decisões técnicas e planejamento para prevenir perda de informações por reinicialização do sistema.

---

## 📌 1. Contexto e Ocorrência

- **O que aconteceu:** O computador desligou repentinamente durante o desenvolvimento e planejamento dos projetos.
- **Sessão recuperada:** Foi recuperada a especificação completa do projeto **TechPulse** (Portal de Notícias Inteligente para Profissionais de Tecnologia).
- **Ação preventiva:** Todos os registros e documentos foram movidos para dentro do diretório do projeto no disco local (`/home/vinicius/Projetos/Residencia-fullstack/`) e com cópias em `techpulse/` e `docs/`.

---

## 📰 2. Resumo das Decisões do Projeto TechPulse

### 🎯 Proposta do Projeto
Portal de agregação de notícias técnicas com curadoria inteligente por IA, newsletter diária automatizada e três modos de consumo de conteúdo.

### 🛠️ Stack Tecnológica Definida
- **Frontend:** React 18, Vite, TypeScript, Zustand (gerenciamento de estado), TanStack Query (cache e sync), React Router v6.
- **Backend:** Node.js 20 LTS, Express 4, TypeScript, MongoDB + Mongoose, JWT + bcrypt, `rss-parser`, `node-cron`, `p-queue` (fila com rate limiting), `nodemailer`, Zod (validação).
- **AI Engine:** Google Gemini (modelo `gemini-3.7-flash`), geração de resumos bilíngues (PT-BR / EN), extração de tags e score de relevância.
- **Infra / Deploy:** Docker + Docker Compose (local), MongoDB Atlas (M0 Free), Vercel (Frontend), Railway (Backend), GitHub Actions (CI/CD).

### 🤖 Modos de Visualização de Artigos
1. **Resumo IA (🤖):** Resumo conciso de 5 a 7 linhas gerado pelo Gemini.
2. **Link Externo (🔗):** Redirecionamento direto para a fonte original.
3. **Preview Interno (📄):** Modo de leitura limpo integrado ao portal.

### 📧 Sistema de Newsletter
- **Briefing Matinal:** 06:00 BRT
- **Atualização da Tarde:** 17:00 BRT
- Implementação com Nodemailer + Gmail SMTP e agendamento via `node-cron`.

### 🛡️ Estratégia Anti-Cota do Gemini
- Cache permanente de resumos no MongoDB (cada artigo é resumido apenas 1 vez).
- Fila com delay (`p-queue`: 1 requisição a cada 4 segundos).
- Pré-filtro por palavras-chave antes de chamar a IA.
- Lazy summarization (resumo gerado sob demanda quando o usuário acessa o artigo).

---

## 🗺️ 3. Cronograma Scrum (6 Meses / 12 Sprints)

| Sprint | Período | Foco Principal |
|---|---|---|
| **Sprint 1** | Semanas 1–2 | Setup & Fundação (Docker, Node, Express, Vite, TS) |
| **Sprint 2** | Semanas 3–4 | Autenticação & Gestão de Usuários (JWT, bcrypt) |
| **Sprint 3** | Semanas 5–6 | News Aggregator (RSS Feeds & CRON) |
| **Sprint 4** | Semanas 7–8 | AI Engine (Gemini 3.7 Flash & Queue) |
| **Sprint 5** | Semanas 9–10 | Frontend — Dashboard & Feed Personalizado |
| **Sprint 6** | Semanas 11–12 | Modos de Visualização (Resumo, Link, Preview) |
| **Sprint 7** | Semanas 13–14 | Email Service (Newsletter 06h / 17h) |
| **Sprint 8** | Semanas 15–16 | Frontend — Artigo, Favoritos & Configurações |
| **Sprint 9** | Semanas 17–18 | Deploy & Infraestrutura (Vercel, Railway, Atlas) |
| **Sprint 10** | Semanas 19–20 | Testes Automatizados (Unitários e E2E) |
| **Sprint 11** | Semanas 21–22 | Busca Avançada e Filtros Full-Text |
| **Sprint 12** | Semanas 23–24 | Otimizações, OWASP Security & Lançamento |

---

## 📁 4. Arquivos Salvos neste Diretório

1. [`techpulse/README.md`](file:///home/vinicius/Projetos/Residencia-fullstack/techpulse/README.md) — Documentação técnica completa do TechPulse.
2. [`docs/TECHPULSE_PLANEJAMENTO_COMPLETO.md`](file:///home/vinicius/Projetos/Residencia-fullstack/docs/TECHPULSE_PLANEJAMENTO_COMPLETO.md) — Cópia de segurança da especificação técnica.
3. [`docs/HISTORICO_CHAT_E_DECISOES.md`](file:///home/vinicius/Projetos/Residencia-fullstack/docs/HISTORICO_CHAT_E_DECISOES.md) — Este documento de histórico de decisões.

---
*Documento registrado em 27/08/2026.*

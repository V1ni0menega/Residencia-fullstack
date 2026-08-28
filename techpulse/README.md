<div align="center">

<img src="https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=for-the-badge" />
<img src="https://img.shields.io/badge/versão-0.1.0--alpha-blue?style=for-the-badge" />
<img src="https://img.shields.io/badge/licença-MIT-green?style=for-the-badge" />
<img src="https://img.shields.io/badge/metodologia-Scrum-orange?style=for-the-badge" />

# 📰 TechPulse

### Portal de Notícias Inteligente para Profissionais de Tecnologia

*Agregação de conteúdo tech com curadoria por IA, newsletter automatizada e experiência de leitura personalizada.*

</div>

---

## 📋 Índice

- [Visão Geral](#-visão-geral)
- [Funcionalidades](#-funcionalidades)
- [Stack Tecnológica](#-stack-tecnológica)
- [Arquitetura do Sistema](#-arquitetura-do-sistema)
- [Modelos de Dados](#-modelos-de-dados)
- [API REST — Endpoints](#-api-rest--endpoints)
- [Modos de Visualização de Artigos](#-modos-de-visualização-de-artigos)
- [Pipeline de IA (Gemini)](#-pipeline-de-ia-gemini)
- [Fontes de Notícias](#-fontes-de-notícias)
- [Serviço de E-mail](#-serviço-de-e-mail)
- [Configuração do Ambiente](#-configuração-do-ambiente)
- [Como Rodar Localmente](#-como-rodar-localmente)
- [Deploy em Produção](#-deploy-em-produção)
- [Cronograma Scrum — 6 Meses](#-cronograma-scrum--6-meses)
- [Segurança](#-segurança)
- [Notas sobre a Gemini API](#-notas-sobre-a-gemini-api)
- [Contribuindo](#-contribuindo)
- [Licença](#-licença)

---

## 🎯 Visão Geral

O **TechPulse** é um portal de notícias personalizado voltado para profissionais e entusiastas de tecnologia. O projeto resolve um problema real: a dificuldade de se manter atualizado com a grande quantidade de informação técnica disponível na internet (frameworks, linguagens, bancos de dados, cybersecurity, hardware, IA, etc.).

### O que o TechPulse faz

- **Agrega** automaticamente artigos de dezenas de fontes RSS e APIs de notícias tech
- **Cuida** do conteúdo usando IA (Google Gemini) para gerar resumos, categorizar e pontuar relevância
- **Personaliza** o feed de cada usuário com base nos seus interesses selecionados
- **Envia** newsletters automáticas às **06:00** e **17:00** (BRT) com os destaques do dia
- **Oferece** três modos de consumo por artigo: resumo IA, link externo ou leitura completa no portal

### Sistema Multi-Usuário

O TechPulse suporta múltiplos usuários com autenticação JWT, cada um com seu próprio perfil, categorias favoritas, artigos salvos e configurações de e-mail.

---

## ✨ Funcionalidades

### Portal Web (Frontend)
- [ ] Dashboard com visão geral e destaques do dia
- [ ] Feed personalizado por categorias de interesse
- [ ] Três modos de leitura por artigo: **Resumo IA** 🤖 | **Link Externo** 🔗 | **Preview Interno** 📄
- [ ] Sistema de favoritos e histórico de lidos
- [ ] Busca full-text com filtros por data, categoria e fonte
- [ ] Configurações de perfil, idioma e preferências de e-mail
- [ ] Modo escuro / claro
- [ ] Design responsivo (mobile-first)

### Backend & API
- [ ] Autenticação com JWT + refresh token
- [ ] Aggregator automático de feeds RSS (a cada 2 horas)
- [ ] Pipeline de processamento com IA (categorização + resumo bilíngue PT-BR/EN)
- [ ] Cache de resumos para não repetir chamadas à IA
- [ ] Rate limiter interno para respeitar cotas da Gemini API
- [ ] CRON jobs para envio de e-mail (06:00 e 17:00 BRT)
- [ ] Histórico de newsletters enviadas

### AI Engine
- [ ] Geração de resumo em PT-BR e EN via Google Gemini
- [ ] Score de relevância por perfil de usuário
- [ ] Categorização automática de artigos
- [ ] Extração de tags e palavras-chave
- [ ] Fila de processamento com p-queue (rate-limiting consciente)

---

## 🛠️ Stack Tecnológica

### Frontend

| Tecnologia | Versão | Papel |
|---|---|---|
| React | 18+ | UI Framework |
| Vite | 5+ | Build tool / Dev server |
| TypeScript | 5+ | Tipagem estática |
| React Router | v6 | Navegação SPA |
| Zustand | 4+ | Estado global |
| TanStack Query | v5 | Cache e sincronização de dados |
| Axios | — | Requisições HTTP |
| date-fns | — | Formatação de datas |

### Backend

| Tecnologia | Versão | Papel |
|---|---|---|
| Node.js | 20 LTS | Runtime |
| Express | 4 | Framework HTTP |
| TypeScript | 5+ | Tipagem estática |
| MongoDB + Mongoose | — | Banco de dados |
| JWT + bcrypt | — | Autenticação segura |
| node-cron | — | Scheduler de CRON jobs |
| Nodemailer | — | Envio de e-mails |
| rss-parser | — | Agregação de feeds RSS |
| p-queue | — | Fila para rate-limiting da IA |
| Zod | — | Validação de dados e schemas |
| winston | — | Logs estruturados |

### AI Engine

| Tecnologia | Papel |
|---|---|
| Google Generative AI SDK | Integração com Gemini |
| Modelo: gemini-3.7-flash | Resumos bilíngues e categorização |

### DevOps / Infra

| Ferramenta | Papel |
|---|---|
| Docker + Docker Compose | Ambiente local de desenvolvimento |
| Vercel | Deploy do frontend |
| Railway | Deploy do backend |
| MongoDB Atlas | Banco de dados em produção (Free M0) |
| GitHub Actions | CI/CD pipeline |

---

## 🏗️ Arquitetura do Sistema

```
┌──────────────────────────────────────────────────────────┐
│                   FRONTEND (Vercel)                      │
│              React + Vite + TypeScript                   │
│  Dashboard | Feed | Leitor | Favoritos | Configurações   │
└────────────────────────┬─────────────────────────────────┘
                         │ HTTPS / REST API
┌────────────────────────▼─────────────────────────────────┐
│                  BACKEND API (Railway)                   │
│             Node.js + Express + TypeScript               │
│   Auth | News | User | Email | Scheduler | AI Queue      │
└──────────────┬──────────────────┬────────────────────────┘
               │                  │
    ┌──────────▼──────┐  ┌────────▼───────────┐
    │  MongoDB Atlas  │  │   AI Queue Service  │
    │  (Free M0)      │  │  p-queue + Gemini   │
    │  Users          │  │  (rate: 1 req/4s)   │
    │  Articles       │  └────────────────────┘
    │  Sources        │
    │  Digests        │
    └─────────────────┘
               │
    ┌──────────▼──────────────────────┐
    │      News Aggregator Service    │
    │    RSS Feeds + APIs externas    │
    │    CRON: a cada 2 horas         │
    └─────────────────────────────────┘
               │
    ┌──────────▼──────────────────────┐
    │        Email Service            │
    │   Nodemailer + Gmail SMTP       │
    │   CRON: 06:00h e 17:00h BRT     │
    └─────────────────────────────────┘
```

---

## 📂 Estrutura de Pastas

```
techpulse/
├── .github/
│   └── workflows/
│       └── ci.yml
├── frontend/
│   └── src/
│       ├── components/
│       │   ├── ui/
│       │   ├── layout/
│       │   └── articles/
│       ├── pages/
│       │   ├── Dashboard.tsx
│       │   ├── Feed.tsx
│       │   ├── Article.tsx
│       │   ├── Favorites.tsx
│       │   ├── Settings.tsx
│       │   ├── Login.tsx
│       │   └── Register.tsx
│       ├── hooks/
│       ├── store/
│       ├── services/
│       ├── styles/
│       └── types/
├── backend/
│   └── src/
│       ├── config/
│       ├── controllers/
│       ├── models/
│       ├── routes/
│       ├── services/
│       │   ├── aggregator.service.ts
│       │   ├── ai.service.ts
│       │   ├── email.service.ts
│       │   └── relevance.service.ts
│       ├── scheduler/
│       │   ├── newsAggregator.cron.ts
│       │   └── emailDigest.cron.ts
│       ├── middleware/
│       ├── templates/
│       │   └── emailDigest.html
│       └── utils/
├── docker-compose.yml
├── .env.example
└── README.md
```

---

## 🗄️ Modelos de Dados (MongoDB)

### User

```typescript
{
  _id: ObjectId,
  name: string,
  email: string,
  password: string,              // bcrypt hash (salt: 12)
  language: 'pt-BR' | 'en',
  preferences: {
    categories: string[],
    articleViewMode: 'summary' | 'link' | 'preview',
    emailEnabled: boolean,
    emailSchedule: ('morning' | 'afternoon')[],
    digestSize: number,
  },
  savedArticles: ObjectId[],
  readArticles: ObjectId[],
  createdAt: Date,
  updatedAt: Date
}
```

### Article

```typescript
{
  _id: ObjectId,
  externalId: string,            // hash da URL — evita duplicatas
  title: string,
  url: string,
  source: string,
  categories: string[],
  publishedAt: Date,
  rawContent: string,
  summaryPtBr: string,           // gerado on-demand pelo Gemini
  summaryEn: string,
  aiProcessed: boolean,
  relevanceTags: string[],
  imageUrl?: string,
  fetchedAt: Date
}
```

### NewsSource

```typescript
{
  _id: ObjectId,
  name: string,
  rssUrl: string,
  category: string,
  isActive: boolean,
  supportsFullContent: boolean,
  lastFetched: Date
}
```

### EmailDigest

```typescript
{
  _id: ObjectId,
  userId: ObjectId,
  sentAt: Date,
  period: 'morning' | 'afternoon',
  articleIds: ObjectId[],
  status: 'sent' | 'failed',
  openedAt?: Date
}
```

---

## 📡 API REST — Endpoints

### Auth (`/api/auth`)

| Método | Rota | Auth | Descrição |
|---|---|---|---|
| POST | /register | ❌ | Cadastro de novo usuário |
| POST | /login | ❌ | Login — retorna JWT + refresh token |
| GET | /me | ✅ | Dados do usuário autenticado |
| POST | /refresh | ✅ | Renova o access token |

### Notícias (`/api/news`)

| Método | Rota | Auth | Descrição |
|---|---|---|---|
| GET | /feed | ✅ | Feed personalizado paginado |
| GET | /trending | ✅ | Artigos mais acessados |
| GET | /:id | ✅ | Artigo completo |
| GET | /:id/summary | ✅ | Resumo IA (on-demand, com cache) |
| GET | /:id/preview | ✅ | Conteúdo para o reader mode |
| GET | /categories | ❌ | Lista de categorias |
| GET | /search | ✅ | Busca full-text com filtros |

### Usuário (`/api/user`)

| Método | Rota | Auth | Descrição |
|---|---|---|---|
| PUT | /preferences | ✅ | Atualiza categorias e configurações |
| POST | /save/:id | ✅ | Salva um artigo |
| DELETE | /save/:id | ✅ | Remove dos salvos |
| GET | /saved | ✅ | Lista de artigos salvos |
| POST | /read/:id | ✅ | Marca artigo como lido |

### E-mail (`/api/email`)

| Método | Rota | Auth | Descrição |
|---|---|---|---|
| POST | /test | ✅ | Disparo manual de digest |
| GET | /history | ✅ | Histórico de digests enviados |

---

## 📖 Modos de Visualização de Artigos

| Modo | Ícone | Descrição |
|---|---|---|
| **Resumo IA** | 🤖 | Resumo de 5-7 linhas gerado pelo Gemini (PT-BR ou EN) |
| **Link Externo** | 🔗 | Redireciona para o artigo original (nova aba) |
| **Preview Interno** | 📄 | Renderiza o conteúdo completo dentro do TechPulse (reader mode) |

> Fallback automático: fontes sem conteúdo completo exibem Resumo IA ou Link Externo.

---

## 🤖 Pipeline de IA (Gemini)

```
[STEP 1] RSS Aggregator (CRON: a cada 2h)
         Busca artigos novos de todas as fontes ativas
                ↓
[STEP 2] Filtro Pré-IA (sem custo de API — keywords)
         • Verifica duplicatas por externalId
         • Score inicial por keyword match (0–10)
         • Score < 3 → descartado
                ↓
[STEP 3] AI Queue (p-queue | concurrency: 1 | delay: 4s)
         Chamada ao Gemini retorna:
           → categories, summaryPtBr, summaryEn, tags
         Resultado salvo no MongoDB (cache permanente)
                ↓
[STEP 4] Digest Builder (CRON: 06:00 e 17:00 BRT)
         → Busca artigos já processados (sem chamar IA novamente)
         → Ordena por relevância para o usuário
         → Monta e envia e-mail HTML
```

---

## 📡 Fontes de Notícias

| Categoria | Fonte |
|---|---|
| AI / ML | The Decoder, AI News, ArXiv CS.AI |
| Cybersecurity | Krebs on Security, The Hacker News, BleepingComputer |
| Dev / Frameworks | Dev.to, CSS-Tricks, Smashing Magazine |
| Hardware | AnandTech, Tom's Hardware |
| Databases | Planet PostgreSQL, MongoDB Blog |
| Linguagens | Hacker News (filtrado), InfoQ |
| Cloud / DevOps | The New Stack, Last Week in AWS |
| Geral Tech | TechCrunch, The Verge, Wired |

---

## 📧 Serviço de E-mail

- ☀️ **Manhã (06:00 BRT):** "Seu Briefing Matinal — {data}"
- ☕ **Tarde (17:00 BRT):** "Atualização da Tarde — {data}"

Template HTML responsivo com: destaque do dia, artigos por categoria com resumo IA, links para o portal e opções de configuração.

---

## ⚙️ Configuração do Ambiente

```bash
cp .env.example .env
```

### Variáveis principais

```env
# App
NODE_ENV=development
PORT=3001

# Database
MONGODB_URI=mongodb://localhost:27017/techpulse

# Auth
JWT_SECRET=seu_jwt_secret_super_seguro
JWT_EXPIRES_IN=7d

# Gemini AI (projeto exclusivo — ver seção abaixo)
GEMINI_API_KEY=sua_chave_gemini_aqui
GEMINI_MODEL=gemini-3.7-flash

# Email (Gmail App Password)
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
EMAIL_USER=seu_email@gmail.com
EMAIL_PASS=sua_app_password_gmail

# Schedulers
EMAIL_MORNING_CRON=0 6 * * *
EMAIL_AFTERNOON_CRON=0 17 * * *
NEWS_AGGREGATOR_CRON=0 */2 * * *

# Frontend
FRONTEND_URL=http://localhost:5173
```

---

## 🚀 Como Rodar Localmente

```bash
# 1. Clone
git clone https://github.com/SEU_USUARIO/techpulse.git
cd techpulse

# 2. Suba o MongoDB
docker compose up -d

# 3. Backend
cd backend && npm install && npm run dev
# API em: http://localhost:3001

# 4. Frontend (novo terminal)
cd frontend && npm install && npm run dev
# App em: http://localhost:5173
```

---

## 🌐 Deploy em Produção

| Serviço | Plataforma | Tier |
|---|---|---|
| Frontend (React) | Vercel | Hobby (Free) |
| Backend API | Railway | Free |
| Banco de Dados | MongoDB Atlas | Free M0 (512 MB) |

**Passos:** MongoDB Atlas → Railway (backend) → Vercel (frontend) → GitHub Actions (CI/CD)

---

## 📅 Cronograma Scrum — 6 Meses

> Sprints de 2 semanas | 12 sprints | 80 itens | ~245 story points

| Sprint | Semanas | Foco |
|---|---|---|
| Sprint 1 | 1–2 | Setup & Fundação |
| Sprint 2 | 3–4 | Autenticação & Usuários |
| Sprint 3 | 5–6 | News Aggregator |
| Sprint 4 | 7–8 | AI Engine (Gemini) |
| Sprint 5 | 9–10 | Frontend — Dashboard & Feed |
| Sprint 6 | 11–12 | Modos de Visualização |
| Sprint 7 | 13–14 | Email Service |
| Sprint 8 | 15–16 | Frontend — Artigo, Favoritos & Settings |
| Sprint 9 | 17–18 | Deploy & DevOps |
| Sprint 10 | 19–20 | Testes Automatizados |
| Sprint 11 | 21–22 | Busca e Filtros Avançados |
| Sprint 12 | 23–24 | Refinamentos & Lançamento |

### Epics

| ID | Epic |
|---|---|
| EP-01 | Fundação do Projeto |
| EP-02 | Autenticação e Usuários |
| EP-03 | Agregação de Notícias |
| EP-04 | AI Engine |
| EP-05 | Portal Web — Frontend |
| EP-06 | Serviço de E-mail |
| EP-07 | Modos de Visualização |
| EP-08 | Deploy e DevOps |
| EP-09 | Qualidade e Testes |
| EP-10 | Melhorias e Refinamentos |

---

## 🔒 Segurança

- bcrypt (salt: 12) para senhas
- JWT com expiração + refresh token
- Rate limiting com express-rate-limit
- Sem segredos hardcoded — apenas via .env
- CORS configurado por domínio em produção
- Gmail com App Password (não senha direta)
- Validação de inputs com Zod em todos os endpoints
- OWASP Top 10 checklist no Sprint 12

---

## 🔑 Notas sobre a Gemini API

### Cotas do Free Tier

As cotas são **por projeto**, não por conta Google.

| Situação | Resultado |
|---|---|
| Nova chave no mesmo projeto | NÃO resolve — compartilham a mesma cota |
| Nova chave em projeto novo | ✅ Resolve — cotas independentes |

**Crie um projeto exclusivo em:** https://aistudio.google.com/apikey

### Modelos Disponíveis no Free Tier

| Modelo | ID | RPD |
|---|---|---|
| Gemini 3.7 Flash ✅ | gemini-3.7-flash | ~1.500 |
| Gemini 3.6 Flash | gemini-3.6-flash | ~1.500 |
| Gemini 3.5 Flash | gemini-3.5-flash | ~1.500 |
| Gemini 3.5 Flash-Lite | gemini-3.5-flash-lite | Compartilhado |
| Gemini 2.5 Flash | gemini-2.5-flash | ~1.500 |

> Gemini Pro **não está disponível** no free tier (removido em abril/2026)

### Estratégia Anti-Quota

| Técnica | Detalhe |
|---|---|
| Cache permanente | Resumo gerado 1x e salvo no MongoDB |
| Fila com delay | p-queue: 1 req/4s |
| Pré-filtro keyword | Score < 3 → descartado sem chamar a IA |
| Lazy summarization | Resumo só gerado quando o artigo é aberto |
| Estimativa | ~96 req/dia — bem abaixo do limite de 1.500 |

---

## 🎓 Filosofia do Projeto

Este projeto é um **aprendizado orientado a produto real**.

| Mês | Tecnologias que você vai aprender |
|---|---|
| 1 | Git avançado, TypeScript, Docker, JWT, bcrypt |
| 2 | MongoDB, RSS parsing, APIs externas, CRON jobs |
| 3 | LLMs na prática, rate limiting, React 18, TanStack Query |
| 4 | SMTP, templates HTML, CSS avançado, Zustand |
| 5 | Deploy profissional, CI/CD, testes automatizados |
| 6 | Full-text search, PWA, performance web, OWASP |

---

## 🤝 Contribuindo

1. Fork o repositório
2. `git checkout -b feature/minha-feature`
3. `git commit -m 'feat: adiciona minha feature'`
4. `git push origin feature/minha-feature`
5. Abra um Pull Request

### Conventional Commits

```
feat:     nova funcionalidade
fix:      correção de bug
docs:     documentação
style:    formatação (sem lógica)
refactor: refatoração
test:     testes
chore:    manutenção
```

---

## 📄 Licença

MIT License — veja o arquivo [LICENSE](LICENSE) para detalhes.

---

<div align="center">

Feito com ☕ e muita vontade de aprender

**⬆ Voltar ao topo**

</div>

# 💡 Insights — Jornada Frontend

> Tudo que aprendi durante a mentoria de desenvolvimento frontend.  
> Não é só sintaxe — é o **jeito de pensar** como dev.

---

## 🗓️ Sessão 1 — 15 de Setembro de 2026

---

## 1. A Trindade do Frontend

Antes de codar, entendi que existem 3 camadas separadas e com responsabilidades diferentes:

| Camada | Tecnologia | Responsabilidade |
|--------|-----------|-----------------|
| **Estrutura** | HTML | O esqueleto — o que existe na página |
| **Visual** | CSS | A aparência — como as coisas parecem |
| **Comportamento** | JavaScript | A interação — o que as coisas fazem |

> 🔑 **Insight chave:** Nunca misture responsabilidades. HTML não é lugar de estilo (`style=""`), CSS não é lugar de lógica.

---

## 2. HTML Semântico × "Divsoup"

Aprendi que existe uma diferença enorme entre um HTML que **funciona** e um HTML que tem **significado**.

```
❌ "Divsoup" — div para tudo, sem significado
✅ HTML Semântico — cada tag tem um propósito
```

**Por que importa?**
- O Google lê as tags semânticas para entender o conteúdo (SEO)
- Leitores de tela para deficientes visuais navegam pelas tags semânticas
- Outros devs entendem o código sem precisar de comentários

> 🔑 **Insight chave:** Se eu remover todo o CSS e a página ainda fizer sentido em texto puro → o HTML está bem estruturado.

---

## 3. O Modelo Mental antes de escrever qualquer tag

Aprendi a fazer 3 perguntas antes de digitar qualquer tag HTML:

### ❓ Pergunta 1 — Qual o PROPÓSITO?
```
Navegação?          → <nav>
Cabeçalho?          → <header>
Conteúdo central?   → <main>  (só 1 por página!)
Grupo temático?     → <section>
Conteúdo sozinho?   → <article>
Só layout visual?   → <div>
```

### ❓ Pergunta 2 — Qual a HIERARQUIA?
Pensar de fora para dentro, como uma árvore:
```
html → body → header/main/footer → section → article → p
```

### ❓ Pergunta 3 — Precisa de SEMÂNTICA ou é só container?
```
Tem significado?  → tag semântica (<section>, <nav>...)
Só agrupa?        → <div> ou <span>
```

---

## 4. Regras de Ouro do HTML

Coisas que não podem errar nunca:

- **1 `<h1>` por página** — o título principal, único. O Google penaliza duplicados.
- **Hierarquia de headings** — nunca pular nível (h1 → h2 → h3, nunca h1 → h4)
- **`alt` em toda imagem** — obrigatório para acessibilidade
- **`<script>` no final do body** — para não bloquear o carregamento da página
- **`lang` correto** — `<html lang="pt-BR">` em página brasileira
- **Links externos com `rel="noopener noreferrer"`** — segurança contra reverse tabnapping

---

## 5. Prototipagem — O que aprendi que não sabia que existia

**Insight:** antes de codar qualquer coisa, devs profissionais prototipam. Eu não sabia que isso tinha nome, nem que havia 3 níveis formais.

### Os 3 Níveis de Prototipagem

```
Low-fi ──────► Mid-fi ──────► Hi-fi
(esboço)      (wireframe)    (protótipo visual)
```

| Nível | O que é | Como fazer | Quando usar |
|-------|---------|-----------|------------|
| **Low-fi** | Esboço em blocos cinzas, sem cor | Papel, Excalidraw, Balsamiq | Ideia inicial, validação rápida |
| **Mid-fi** | Wireframe digital com estrutura e hierarquia | Figma, draw.io | Antes de começar a codar |
| **Hi-fi** | Protótipo com cores, fontes e imagens reais | Figma com design system | Para apresentar ao cliente/equipe |

### Por que prototipar antes de codar?

1. **Evita retrabalho** — mudar um wireframe leva 5 minutos, mudar código leva horas
2. **Força pensar no layout** — onde vai cada seção, qual a hierarquia visual
3. **Documenta a decisão** — outros devs (e você no futuro) entendem o raciocínio
4. **É um entregável do challenge** — o README pede "prototipação de baixa e média fidelidade"

### Ferramentas gratuitas para prototipar

| Ferramenta | Nível | Link |
|-----------|-------|------|
| Papel + caneta | Low-fi | — |
| Excalidraw | Low-fi | excalidraw.com |
| draw.io | Mid-fi | draw.io |
| Figma | Mid-fi / Hi-fi | figma.com |
| Adobe XD | Hi-fi | adobe.com/xd |

> 🔑 **Insight chave:** Prototipar não é perder tempo — é **economizar** tempo de código. Todo minuto de wireframe poupa 10 minutos de refatoração.

---

## 6. O Fluxo Correto de Desenvolvimento Frontend

Aprendi que existe uma ordem certa para construir qualquer interface:

```
1. Entender o problema / requisitos
        ↓
2. Pesquisar referências (benchmark)
        ↓
3. Prototipar (low-fi → mid-fi)
        ↓
4. Estrutura HTML (semântica primeiro)
        ↓
5. Estilos CSS (mobile-first)
        ↓
6. Interações JavaScript
        ↓
7. Testar e ajustar
```

> 🔑 **Insight chave:** A maioria dos devs júnior pula os passos 2 e 3 e vai direto para o 4. Isso gera retrabalho e código mal estruturado.

---

## 7. Checklist antes de passar HTML → CSS

Antes de abrir o CSS, validar:

- [ ] Tem `<!DOCTYPE html>`?
- [ ] `<html lang="pt-BR">`?
- [ ] Somente **1 `<h1>`**?
- [ ] Todas as `<section>` têm `id`?
- [ ] Todos os links do nav apontam para os `id`s corretos?
- [ ] Todas as `<img>` têm `alt`?
- [ ] `<script>` no final do body?
- [ ] A página faz sentido **sem CSS**?

---

## 8. Vocabulário de Dev que aprendi

| Termo | Significado |
|-------|------------|
| **HTML Semântico** | HTML com tags que têm significado próprio |
| **DOM** | Document Object Model — a árvore que o browser constrói do HTML |
| **SEO** | Search Engine Optimization — otimização para o Google |
| **Acessibilidade (a11y)** | Fazer interfaces usáveis por pessoas com deficiência |
| **WCAG** | Web Content Accessibility Guidelines — padrão internacional de acessibilidade |
| **ARIA** | Accessible Rich Internet Applications — atributos extras de acessibilidade |
| **Low-fi / Mid-fi / Hi-fi** | Níveis de fidelidade de um protótipo |
| **Wireframe** | Esboço estrutural de uma interface (sem cor, sem detalhe visual) |
| **Layout shift** | Quando a página "pula" enquanto carrega — causado por img sem width/height |
| **Reverse tabnapping** | Vulnerabilidade em links `target="_blank"` sem `rel="noopener"` |
| **Defer** | Atributo do `<script>` que faz ele executar só após o HTML carregar |
| **Lazy loading** | Carregar imagens só quando entrarem na tela (melhora performance) |
| **Open Graph** | Metadados que definem como a página aparece ao ser compartilhada no LinkedIn/WhatsApp |

---

*Insights gerados durante a Residência TIC Fullstack — ResTIC55 · Setembro/2026*  
*Mentoria: Antigravity AI*

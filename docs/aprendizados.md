# 💡 Diário de Bordo & Aprendizados Técnicos

Registro contínuo de conceitos de Computação, boas práticas, desafios superados e decisões de arquitetura na Residência Fullstack.

---

## 📅 24/09/2026 — Design System, Slider Avançado e Interatividade com JS Puro

### 💡 Conceitos dominados
- **Design System com CSS Custom Properties (`--var`):**  
  Aprendemos a centralizar toda a identidade visual (cores, espaçamentos, bordas, tipografia) em variáveis `:root`. A mudança de cor accent de cyan para violeta foi feita em **1 linha** de código — isso é o poder de um Design System real.
- **`clamp()` para tipografia responsiva:**  
  Função CSS nativa que substitui media queries em tamanhos de fonte: `clamp(min, preferido, max)`. O H1 do hero usa `clamp(2.8rem, 7vw, 5rem)` — cresce com a viewport sem quebrar em telas pequenas.
- **Slider/Carousel sem bibliotecas externas:**  
  Implementamos um slider fullscreen com `overflow: hidden` no viewport + `display: flex` no track + `transform: translateX(-N * 100%)` via JS. Mais leve e mais rápido que qualquer lib de carousel.
- **Transição de conteúdo com `opacity` + `translateX`:**  
  O efeito "deslizar para o lado e aparecer" foi feito com CSS puro: o slide inativo tem `opacity: 0.3; transform: translateX(20px)` e ao ativar a classe `.active`, transiciona para `opacity: 1; transform: translateX(0)` com `cubic-bezier(0.16, 1, 0.3, 1)` (curva "spring").
- **`IntersectionObserver` para múltiplos propósitos:**  
  Usamos 3 observers na mesma página: (1) scroll-spy da sidebar, (2) detecção de seção ativa para o indicador inferior, (3) detecção se `#projetos` está visível para ativar os atalhos de teclado. API nativa, zero dependências.
- **Devicons CDN para ícones de tecnologias:**  
  Biblioteca de ícones SVG/fonte para stacks de desenvolvimento. Basta importar o CSS via CDN e usar classes como `devicon-javascript-plain colored` em qualquer `<i>` — os ícones são vetoriais e responsivos.
- **Event Listeners avançados no DOM:**  
  Implementamos 5 formas de interação em um único componente: clique nos botões, clique nos dots, scroll da roda do mouse (`wheel` com `passive: false`), arrastar com mouse (`mousedown/mousemove/mouseup`), e swipe touch (`touchstart/touchmove/touchend`).

### 🚧 Desafios & Soluções (Troubleshooting)
- **Problema:** `replace_file_content` falhava em arquivo com CRLF (`\r\n`).  
  *Causa-raiz:* O arquivo CSS foi criado com quebras de linha do Windows, e a ferramenta de edição não normalizava. *Solução:* Usar `python3` inline para ler, normalizar e substituir o bloco via string Python.
- **Problema:** Sidebar com `position: fixed` e `sidebar-track` com `position: absolute` sem referência.  
  *Causa-raiz:* `position: absolute` é relativo ao ancestral positioned mais próximo — mas o `fixed` não serve de âncora para filhos `absolute`. *Solução:* Usar `padding-right` no aside para criar espaço visual de trilho, posicionando o `::after` do item ativo com `right: -14px`.
- **Problema:** Scroll do mouse na seção de projetos disparava múltiplos slides de uma vez.  
  *Causa-raiz:* O evento `wheel` dispara dezenas de vezes por segundo. *Solução:* Implementar um `scrollCooldown` com `setTimeout` de 800ms.

### 🏆 Decisões de Arquitetura & Boas Práticas
- **JS Puro vs. Biblioteca:** Optamos por não usar jQuery, Swiper.js ou GSAP. Para um portfólio estático com 2 projetos, JS nativo é mais performático, sem overhead de dependências.
- **Separação de responsabilidades no JS:** Cada funcionalidade tem seu bloco comentado (`// ─── 1. Sidebar`, `// ─── 2. Slider`, `// ─── 3. Teclado`), mantendo o script legível e expansível.
- **Molde para futuros projetos:** A seção de projetos foi arquitetada como template — adicionar um novo projeto = duplicar um `<article>` e incrementar o contador. Sem refatorar a lógica JS.

---

## 📅 24/09/2026 — Frontend Moderno: Glassmorphism, Microinterações e Hero Tech

### 💡 Conceitos dominados
- **Glassmorphism no CSS (`backdrop-filter`):**  
  Como criar o efeito de vidro fosco no header combinando `background: rgba(...)` semitransparente com `backdrop-filter: blur(12px)`. O desfoque atua nos elementos que passam *por trás* do componente, criando sensação de profundidade tridimensional.
- **Microinterações com Pseudo-elementos (`::after` e `scaleX`):**  
  Técnica de criar uma linha decorativa animada sob links sem poluir o HTML com divs extras. A linha nasce oculta com `transform: scaleX(0)` ancorada à esquerda (`transform-origin: left`) e se expande suavemente no `:hover` com `transform: scaleX(1)`.
- **Animações Contínuas de Status (`@keyframes`):**  
  Criação de indicadores de pulso com `box-shadow` e `@keyframes pulsar`, controlando `transform: scale()` e `opacity` para simular um radar de disponibilidade.
- **Radial Glow Background:**  
  Uso de `radial-gradient` pontual para dar foco ao conteúdo principal do Hero sem usar imagens pesadas.

### 🚧 Desafios & Soluções (Troubleshooting)
- **Problema encontrado:** Ao editar os links da navegação, a regra base `header nav ul li a` foi substituída acidentalmente por `a::after`.  
  *Causa-raiz:* O pseudo-elemento `::after` perdeu sua referência de posicionamento (`position: relative`), o que quebra a âncora da linha absoluta.  
  *Solução:* Restaurada a regra base com `position: relative`, `text-decoration: none` e `padding-bottom: 4px`.
- **Problema de versionamento:** Arquivos `index.html` e `style.css` haviam subido com 0 bytes da máquina Windows.  
  *Solução:* Resgate cirúrgico dos códigos históricos a partir da transcrição de sessão `transcript_full.jsonl`.

### 🏆 Decisões de Arquitetura & Boas Práticas
- **Design Tokens no `:root`:** Variáveis de cor, tipografia e espaçamento centralizadas no topo do CSS para facilitar manutenção e consistência.
- **Workflow & Rules no `.agents/`:** Configuração de governança de IA versionável para manter a postura de mentoria e histórico entre diferentes computadores.

# 📖 Estudo de Caso: Portfólio Pessoal (Benchmark dvlpr.pro)

**Módulo:** Frontend — Residência TIC Fullstack (ResTIC55)  
**Desenvolvedor:** Vinicius Menegussi Ramos  
**Referência Visual:** [dvlpr.pro/#portfolio](https://dvlpr.pro/#portfolio)

---

## 🎯 1. Objetivo do Desafio
Construir uma interface moderna, acessível e performática para o portfólio profissional de desenvolvedor Fullstack (Java, Spring Boot, Angular), aplicando estética contemporânea de desenvolvedor sênior (dark theme, glassmorphism, microinterações e design tokens).

---

## 🔬 2. Conceitos Técnicos Aplicados

### A. Anatomia do Glassmorphism
Para que o efeito funcione perfeitamente:
1. O elemento pai deve ter `position: fixed` ou `position: sticky`.
2. A cor de fundo **não** pode ser opaca; deve ter canal alfa (`rgba(17, 17, 19, 0.75)`).
3. A propriedade `backdrop-filter: blur(12px)` desfoca o conteúdo inferior.
4. Adiciona-se `-webkit-backdrop-filter: blur(12px)` para compatibilidade com o motor WebKit (Safari).

```css
header {
    position: fixed;
    width: 100%;
    background: rgba(17, 17, 19, 0.75);
    backdrop-filter: blur(12px);
    -webkit-backdrop-filter: blur(12px);
    border-bottom: 1px solid rgba(255, 255, 255, 0.08);
}
```

---

### B. Linha Deslizante com Pseudo-Elemento `::after`
Evita inserir tags HTML extras (`<span>` ou `<div>`) apenas para fins estéticos.

```css
/* Âncora */
header nav ul li a {
    position: relative;
    padding-bottom: 4px;
}

/* Linha recolhida */
header nav ul li a::after {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 2px;
    background-color: var(--cor-accent);
    transform: scaleX(0);
    transform-origin: left;
    transition: transform 0.3s ease;
}

/* Expansão no hover */
header nav ul li a:hover::after {
    transform: scaleX(1);
}
```

---

### C. Status Badge com Indicador Vivo (Pulsar)
Indica disponibilidade para o mercado com animação em loop.

```html
<div class="status-badge">
    <span class="status-ponto"></span>
    Disponível para novos projetos · ResTIC55
</div>
```

```css
.status-ponto {
    width: 8px;
    height: 8px;
    border-radius: 50%;
    background-color: #10b981;
    box-shadow: 0 0 8px rgba(16, 185, 129, 0.6);
    animation: pulsar 2s infinite ease-in-out;
}

@keyframes pulsar {
    0%, 100% { transform: scale(1); opacity: 1; }
    50% { transform: scale(1.35); opacity: 0.5; }
}
```

---

## 📚 3. Tópicos para Estudo Individual Quando Voltar
1. Como o `backdrop-filter` impacta o *compositing* da GPU do navegador.
2. Como funciona o contexto de empilhamento (*stacking context*) ao usar `position: relative` e `position: absolute`.
3. O uso de `transform-origin` para criar variações de animação (ex: linha surgindo do centro vs. surgindo da esquerda).

---

## 🔬 4. Sessão 03 — Showcase Slider, Interatividade Avançada e Ícones

### A. Arquitetura do Slider Fullscreen

O slider foi construído com **CSS puro + JS mínimo** — sem bibliotecas:

```css
/* Viewport: esconde o overflow, age como "janela" */
.slider-viewport {
    width: 100%;
    overflow: hidden;
}

/* Track: todos os slides lado a lado */
.slider-track {
    display: flex;
    transition: transform 0.65s cubic-bezier(0.16, 1, 0.3, 1); /* curva spring */
}

/* Cada slide ocupa 100% da largura do viewport */
.slide-projeto {
    flex: 0 0 100%;
    width: 100%;
}
```

```js
// Para ir ao slide N:
track.style.transform = `translateX(-${N * 100}%)`;
```

### B. Efeito "Deslizar e Aparecer" no Conteúdo

O efeito visual de cada slide entrando pela lateral é feito com CSS transitions no conteúdo interno:

```css
/* Estado inativo: deslocado e transparente */
.slide-conteudo, .slide-preview {
    opacity: 0.3;
    transform: translateX(20px);
    transition: opacity 0.55s ease, transform 0.55s cubic-bezier(0.16, 1, 0.3, 1);
}

/* Estado ativo: visível na posição original */
.slide-projeto.active .slide-conteudo,
.slide-projeto.active .slide-preview {
    opacity: 1;
    transform: translateX(0);
}
```

### C. `IntersectionObserver` — o Motor do Scroll Spy

```js
// Observa quando cada seção entra na viewport (40% visível)
const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            // Atualiza sidebar, indicador de scroll, índice de seção
        }
    });
}, { threshold: 0.4 });

sections.forEach(sec => observer.observe(sec));
```

### D. Navegação por Teclado — Estilo dvlpr.pro

```js
document.addEventListener('keydown', (e) => {
    switch (e.key) {
        case 'ArrowDown': case 'PageDown':
            e.preventDefault();
            navegarSecao(+1);  // ↓ = próxima seção
            break;
        case 'ArrowUp': case 'PageUp':
            e.preventDefault();
            navegarSecao(-1);  // ↑ = seção anterior
            break;
        case 'ArrowRight':
            if (projetosVisivel) updateSlider(currentSlide + 1);  // → = próximo projeto
            break;
        case 'ArrowLeft':
            if (projetosVisivel) updateSlider(currentSlide - 1);  // ← = projeto anterior
            break;
    }
});
```

### E. Devicons — Ícones de Stack

Basta um link CDN no `<head>` e classes HTML:

```html
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/devicon.min.css">
...
<i class="devicon-javascript-plain colored"></i>
```

Padrão de nomenclatura: `devicon-{tecnologia}-{estilo}`. Estilos: `plain`, `original`, `line`. Sufixo `colored` aplica as cores originais da tecnologia.

---

## 📚 5. Tópicos para Estudo Avançado
1. **`cubic-bezier()`** — Como as curvas Bézier controlam a "física" das animações. Ferramenta: [cubic-bezier.com](https://cubic-bezier.com)
2. **`IntersectionObserver` avançado** — `rootMargin`, múltiplos thresholds, lazy loading de imagens.
3. **`wheel` event** — diferença entre `deltaY`, `deltaX`, `deltaMode` e como normalizar o scroll entre dispositivos.
4. **`scrollIntoView()`** — opções `behavior: 'smooth'`, `block: 'start'` e quando usar vs. `window.scrollTo()`.
5. **Accessibility (a11y) em sliders** — uso de `aria-live`, `role="region"`, `aria-label` e suporte a leitores de tela em carousels.

# Gerenciamento de Pedidos — Estudo de POO em Java

Aplicação de console em Java para gerenciar produtos, clientes e pedidos, criada para estudar os principais conceitos de Programação Orientada a Objetos.

## Objetivo

Estudar os principais conceitos de POO em Java, incluindo classes, objetos, encapsulamento, herança e polimorfismo.

## Estrutura do projeto

```
src/
├── Produto.java            // dados e regras de um produto (preço, estoque)
├── Cliente.java             // dados de um cliente
├── ClienteVip.java          // herança de Cliente, adiciona desconto
├── ItemPedido.java          // associa um produto a uma quantidade dentro de um pedido
├── Pedido.java               // composição: um pedido tem um Cliente e uma lista de ItemPedido
├── GerenciadorPedidos.java  // centraliza o cadastro e a busca de produtos, clientes e pedidos
└── Main.java                 // menu interativo no console
```

## Conceitos de POO aplicados

### Encapsulamento
Todos os atributos das classes (`Produto`, `Cliente`, `Pedido`, `ItemPedido`) são `private`, acessados apenas por getters/setters. Vários setters validam o valor antes de aceitar, por exemplo:
```java
public void setPreco(double preco) {
    if (preco > 0) {
        this.preco = preco;
    } else {
        throw new IllegalArgumentException("Preço deve ser maior que zero");
    }
}
```

### Herança
`ClienteVip extends Cliente` — reaproveita todos os atributos/comportamentos de `Cliente` e adiciona `percentualDesconto` e o método `aplicarDesconto()`:
```java
public class ClienteVip extends Cliente {
    private double percentualDesconto;
    ...
}
```

### Composição
`Pedido` **tem um** `Cliente` e **tem uma lista de** `ItemPedido` — esses objetos não existem fora do contexto de um pedido, mas também não são herdados, são "montados" dentro da classe:
```java
public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens;
    ...
}
```

### Polimorfismo
Em `Pedido.calcularTotal()`, o código verifica em tempo de execução se o cliente é um `ClienteVip` e, se for, aplica o comportamento específico dessa subclasse:
```java
if (cliente instanceof ClienteVip) {
    ClienteVip vip = (ClienteVip) cliente;
    total = vip.aplicarDesconto(total);
}
```
Além disso, `ClienteVip` sobrescreve (`@Override`) o método `toString()` de `Cliente`, estendendo a saída da classe pai.

## Responsabilidades de cada classe

| Classe | Responsabilidade |
|---|---|
| `Produto` | Representar um produto e controlar seu próprio estoque |
| `Cliente` | Representar os dados de um cliente |
| `ClienteVip` | Especializar `Cliente` com regra de desconto |
| `ItemPedido` | Associar um produto a uma quantidade e calcular seu subtotal |
| `Pedido` | Agregar cliente + itens, calcular totais e controlar o status do pedido |
| `GerenciadorPedidos` | Cadastrar e buscar produtos, clientes e pedidos (camada de "repositório" em memória) |
| `Main` | Interface de console (menu) que interage com o usuário |

## Requisitos atendidos

- ✅ Mais de 3 classes relacionadas (7 classes)
- ✅ Encapsulamento (atributos `private` + getters/setters com validação)
- ✅ Herança (`ClienteVip extends Cliente`) e composição (`Pedido` com `Cliente` + `List<ItemPedido>`)
- ✅ Responsabilidades bem definidas (cada classe tem um propósito único e claro)

## Como executar

Compilar:
```bash
cd src
javac *.java
```

Executar:
```bash
java Main
```

## Exemplo de uso

Ao iniciar, o sistema já vem com produtos e clientes de exemplo cadastrados. Pelo menu é possível:
1. Cadastrar novos produtos
2. Cadastrar clientes (comuns ou VIP)
3. Criar um pedido, adicionar itens e confirmá-lo (o que reduz o estoque automaticamente)
4. Listar produtos, clientes e pedidos
5. Visualizar o detalhamento de um pedido específico (com subtotal, taxa de entrega e total, já considerando desconto VIP quando aplicável)

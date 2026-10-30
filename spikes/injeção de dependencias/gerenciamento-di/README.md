# Gerenciamento DI — Injeção de Dependência com Spring

Aplicação Spring Boot criada para estudar **Inversão de Controle (IoC)** e **Injeção de Dependência (DI)**, usando o Spring para gerenciar e injetar as dependências da aplicação automaticamente.

## Objetivo

Compreender o conceito de inversão de controle e implementar injeção de dependência utilizando Spring, estruturando a aplicação em camadas de **Repository** e **Service**.

## Estrutura do projeto

```
src/main/java/br/com/V1ni0menega/gerenciamento_di/
├── GerenciamentoDiApplication.java   // classe principal, implementa CommandLineRunner
├── model/
│   └── Produto.java                  // entidade simples (POJO)
├── repository/
│   ├── ProdutoRepository.java        // interface do repositório
│   └── ProdutoRepositoryImpl.java    // implementação (armazenamento em memória)
└── service/
    └── ProdutoService.java           // regra de negócio, depende da interface do repositório
```

## Como a Injeção de Dependência funciona aqui

1. **`ProdutoRepository`** é uma interface que declara o contrato (`salvar`, `listarTodos`, `buscarPorId`, `deletarPorId`).
2. **`ProdutoRepositoryImpl`** é a implementação concreta, anotada com `@Repository` — isso registra a classe como um *bean* gerenciado pelo Spring.
3. **`ProdutoService`**, anotado com `@Service`, **depende da interface** `ProdutoRepository`, não da implementação. A dependência é recebida via **injeção por construtor**:
   ```java
   public ProdutoService(ProdutoRepository produtoRepository) {
       this.produtoRepository = produtoRepository;
   }
   ```
4. **`GerenciamentoDiApplication`** (classe principal) também recebe `ProdutoService` injetado pelo construtor, e implementa `CommandLineRunner` para executar uma demonstração ao iniciar a aplicação.

Em nenhum momento o código escreve `new ProdutoRepositoryImpl()` ou `new ProdutoService(...)` — é o **container do Spring** (`ApplicationContext`) que cria essas instâncias e as entrega (injeta) onde são necessárias. Isso é a Inversão de Controle: quem controla a criação e o ciclo de vida dos objetos é o framework, não o próprio código cliente.

## Requisitos atendidos

- ✅ Serviço (`ProdutoService`) e repositório (`ProdutoRepository`) com injeção de dependência
- ✅ Uma interface (`ProdutoRepository`) e uma implementação (`ProdutoRepositoryImpl`)
- ✅ Dependência injetada automaticamente pelo Spring (via construtor, sem `@Autowired` explícito — o Spring usa injeção por construtor implícita quando há um único construtor)
- ✅ Nenhuma criação manual (`new`) das dependências principais (`ProdutoRepository`/`ProdutoService`)
- ✅ Aplicação executa corretamente (testado com `./mvnw spring-boot:run`)

## Como executar

```bash
./mvnw spring-boot:run
```

### Saída esperada

```
=== Demonstração de Injeção de Dependência ===
Produtos cadastrados via ProdutoService (injetado pelo Spring):
 - Produto [id=1, nome=Notebook, preco=R$ 3500.00]
 - Produto [id=2, nome=Mouse, preco=R$ 50.00]
 - Produto [id=3, nome=Teclado, preco=R$ 120.00]
```

## Tecnologias

- Java 25
- Spring Boot 4.1.2 (Maven)

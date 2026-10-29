# Gerenciamento MVC — API Spring Boot com arquitetura em camadas

API REST em Spring Boot para gerenciar produtos, estruturada em camadas **Controller → Service → Repository**, criada para estudar a separação de responsabilidades da arquitetura MVC.

## Objetivo

Compreender a separação de responsabilidades da arquitetura MVC e sua aplicação em projetos Spring Boot.

## Estrutura do projeto

```
src/main/java/br/com/V1ni0menega/gerenciamento_mvc/
├── GerenciamentoMvcApplication.java
├── model/
│   └── Produto.java                  // entidade de domínio
├── dto/
│   ├── ProdutoRequest.java           // dados recebidos na criação/atualização
│   └── ProdutoResponse.java          // dados devolvidos ao cliente
├── repository/
│   ├── ProdutoRepository.java        // interface (contrato de persistência)
│   └── ProdutoRepositoryImpl.java    // implementação em memória
├── service/
│   └── ProdutoService.java           // validações e regras de negócio
├── controller/
│   └── ProdutoController.java        // endpoints HTTP, sem lógica de negócio
└── exception/
    ├── ProdutoNaoEncontradoException.java
    └── ApiExceptionHandler.java      // tratamento centralizado de erros
```

## Responsabilidade de cada camada

| Camada | Responsabilidade | O que NÃO faz |
|---|---|---|
| **Controller** | Receber a requisição HTTP, extrair dados (`@RequestBody`/`@PathVariable`), chamar o Service e devolver o `ResponseEntity` com o status correto | Não valida regras de negócio, não acessa o repositório diretamente, não decide o que é "inválido" |
| **Service** | Validar dados, aplicar regras de negócio (ex: preço > 0), calcular valores derivados (`valorTotalEmEstoque`), orquestrar chamadas ao repositório e lançar exceções de domínio | Não sabe que existe HTTP (não usa `ResponseEntity`, `@RequestBody`, etc.) |
| **Repository** | Persistir e recuperar dados (aqui, em memória) | Não valida regras de negócio, só executa operações de dados |

O `ProdutoController` é só uma "ponte": pega o que veio na requisição, entrega para o `ProdutoService`, e devolve o que ele retornar. Toda decisão (`nome` vazio é inválido? `preço` negativo é erro?) está no `ProdutoService`.

## Fluxo de comunicação entre camadas

```
Cliente HTTP
    │
    ▼
ProdutoController  ──(injeta)──▶  ProdutoService  ──(injeta)──▶  ProdutoRepository (interface)
                                                                        ▲
                                                                        │ implementa
                                                                ProdutoRepositoryImpl
```

Todas as dependências são injetadas pelo Spring via construtor — nenhuma camada cria `new` da camada seguinte.

## Tratamento de erros

Centralizado em `ApiExceptionHandler` (`@RestControllerAdvice`):
- `ProdutoNaoEncontradoException` → HTTP 404
- `IllegalArgumentException` (validações do Service) → HTTP 400

Isso evita que o Controller precise de blocos `try/catch` — ele só propaga a exceção e o handler global decide a resposta.

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/api/produtos` | Cadastra um novo produto |
| `GET` | `/api/produtos` | Lista todos os produtos |
| `GET` | `/api/produtos/{id}` | Busca um produto pelo id |
| `PUT` | `/api/produtos/{id}` | Atualiza um produto existente |
| `DELETE` | `/api/produtos/{id}` | Remove um produto |

### Exemplo de requisição (POST/PUT)

```json
{
  "nome": "Notebook",
  "preco": 3500.00,
  "quantidade": 10
}
```

### Exemplo de resposta

```json
{
  "id": 1,
  "nome": "Notebook",
  "preco": 3500.0,
  "quantidade": 10,
  "valorTotalEmEstoque": 35000.0
}
```

## Requisitos atendidos

- ✅ Camadas separadas: Controller, Service e Repository
- ✅ Cada camada com responsabilidade clara e única
- ✅ Controller sem lógica de negócio (validações e cálculos ficam no Service)
- ✅ Comunicação entre camadas via injeção de dependência (Controller → Service → Repository, por interface)
- ✅ Aplicação testada e funcionando (todos os endpoints validados via `curl`, incluindo cenários de erro 400/404)

## Como executar

```bash
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

### Testando com curl

```bash
# Criar produto
curl -X POST localhost:8080/api/produtos -H "Content-Type: application/json" \
  -d '{"nome":"Notebook","preco":3500.00,"quantidade":10}'

# Listar produtos
curl localhost:8080/api/produtos

# Buscar por id
curl localhost:8080/api/produtos/1

# Atualizar
curl -X PUT localhost:8080/api/produtos/1 -H "Content-Type: application/json" \
  -d '{"nome":"Notebook Gamer","preco":4200.00,"quantidade":5}'

# Remover
curl -X DELETE localhost:8080/api/produtos/1
```

## Tecnologias

- Java 25
- Spring Boot 4.1.2 (Maven) com Spring Web

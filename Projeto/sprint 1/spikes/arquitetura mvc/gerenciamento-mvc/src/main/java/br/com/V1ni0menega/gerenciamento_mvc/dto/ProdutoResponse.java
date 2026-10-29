package br.com.V1ni0menega.gerenciamento_mvc.dto;

public record ProdutoResponse(Long id, String nome, double preco, int quantidade, double valorTotalEmEstoque) {
}

package br.com.V1ni0menega.gerenciamento_mvc.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(Long id) {
        super("Produto não encontrado com o id: " + id);
    }
}

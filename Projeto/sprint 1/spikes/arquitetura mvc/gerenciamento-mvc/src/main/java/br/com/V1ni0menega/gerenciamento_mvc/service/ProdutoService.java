package br.com.V1ni0menega.gerenciamento_mvc.service;

import br.com.V1ni0menega.gerenciamento_mvc.dto.ProdutoRequest;
import br.com.V1ni0menega.gerenciamento_mvc.dto.ProdutoResponse;
import br.com.V1ni0menega.gerenciamento_mvc.exception.ProdutoNaoEncontradoException;
import br.com.V1ni0menega.gerenciamento_mvc.model.Produto;
import br.com.V1ni0menega.gerenciamento_mvc.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponse cadastrar(ProdutoRequest request) {
        validarDados(request.nome(), request.preco(), request.quantidade());

        Produto produto = new Produto(null, request.nome(), request.preco(), request.quantidade());
        Produto salvo = produtoRepository.salvar(produto);
        return toResponse(salvo);
    }

    public List<ProdutoResponse> listarTodos() {
        return produtoRepository.listarTodos().stream()
                .map(this::toResponse)
                .toList();
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        return toResponse(produto);
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        if (!produtoRepository.existePorId(id)) {
            throw new ProdutoNaoEncontradoException(id);
        }

        validarDados(request.nome(), request.preco(), request.quantidade());

        Produto produto = new Produto(id, request.nome(), request.preco(), request.quantidade());
        Produto atualizado = produtoRepository.salvar(produto);
        return toResponse(atualizado);
    }

    public void remover(Long id) {
        if (!produtoRepository.existePorId(id)) {
            throw new ProdutoNaoEncontradoException(id);
        }
        produtoRepository.deletarPorId(id);
    }

    private void validarDados(String nome, double preco, int quantidade) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
    }

    private ProdutoResponse toResponse(Produto produto) {
        double valorTotalEmEstoque = produto.getPreco() * produto.getQuantidade();
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade(),
                valorTotalEmEstoque
        );
    }
}

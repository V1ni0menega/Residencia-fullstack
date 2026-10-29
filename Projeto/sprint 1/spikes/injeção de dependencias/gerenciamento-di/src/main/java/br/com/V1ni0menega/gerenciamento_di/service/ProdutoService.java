package br.com.V1ni0menega.gerenciamento_di.service;

import br.com.V1ni0menega.gerenciamento_di.model.Produto;
import br.com.V1ni0menega.gerenciamento_di.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(String nome, double preco) {
        Produto produto = new Produto(null, nome, preco);
        return produtoRepository.salvar(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.listarTodos();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.buscarPorId(id);
    }

    public void remover(Long id) {
        produtoRepository.deletarPorId(id);
    }
}

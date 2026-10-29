package br.com.V1ni0menega.gerenciamento_di.repository;

import br.com.V1ni0menega.gerenciamento_di.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    Produto salvar(Produto produto);

    List<Produto> listarTodos();

    Optional<Produto> buscarPorId(Long id);

    void deletarPorId(Long id);
}

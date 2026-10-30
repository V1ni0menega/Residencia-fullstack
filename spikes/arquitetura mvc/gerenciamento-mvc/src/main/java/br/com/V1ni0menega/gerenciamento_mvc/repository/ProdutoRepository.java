package br.com.V1ni0menega.gerenciamento_mvc.repository;

import br.com.V1ni0menega.gerenciamento_mvc.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    Produto salvar(Produto produto);

    List<Produto> listarTodos();

    Optional<Produto> buscarPorId(Long id);

    boolean existePorId(Long id);

    void deletarPorId(Long id);
}

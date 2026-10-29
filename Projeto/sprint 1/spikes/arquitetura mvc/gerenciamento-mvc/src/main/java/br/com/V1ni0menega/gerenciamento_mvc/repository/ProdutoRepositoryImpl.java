package br.com.V1ni0menega.gerenciamento_mvc.repository;

import br.com.V1ni0menega.gerenciamento_mvc.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final Map<Long, Produto> produtos = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    @Override
    public Produto salvar(Produto produto) {
        if (produto.getId() == null) {
            produto.setId(proximoId.getAndIncrement());
        }
        produtos.put(produto.getId(), produto);
        return produto;
    }

    @Override
    public List<Produto> listarTodos() {
        return List.copyOf(produtos.values());
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return Optional.ofNullable(produtos.get(id));
    }

    @Override
    public boolean existePorId(Long id) {
        return produtos.containsKey(id);
    }

    @Override
    public void deletarPorId(Long id) {
        produtos.remove(id);
    }
}

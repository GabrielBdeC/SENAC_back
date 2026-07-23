package br.com.nonna.service;

import br.com.nonna.model.Produto;
import br.com.nonna.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listar() {
        return repository.buscarTodos();
    }

    public void criar(Produto p) {
        repository.inserir(p);
    }

    public void atualizar(String id, Produto p) {
        repository.atualizar(id, p);
    }

    public void deletar(String id) {
        repository.deletar(id);
    }
}

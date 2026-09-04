package br.com.nonna_back.services;

import br.com.nonna_back.models.Produto;
import br.com.nonna_back.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

    public List<Produto> getTodosProdutos(){
        return this.repository.getTodosProdutos();
    }
}

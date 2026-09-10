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

    public void criarProduto(Produto produto) {
        produto.setId("");

        if (produto.getNome() == null || (produto.getNome().trim()).isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser vazio!");
        }
        produto.setNome(produto.getNome().trim());

        if (produto.getDescricao() == null || (produto.getDescricao().trim()).isEmpty()){
            throw new IllegalArgumentException("Descrição não pode ser vazia!");
        }
        produto.setDescricao(produto.getDescricao().trim());

        if (produto.getPreco() == null || produto.getPreco().intValue() <= 0){
            throw new IllegalArgumentException("Preço deve ser maior que 0!");
        }

        if (produto.getCategoria() == null || (produto.getCategoria().trim()).isEmpty()){
            throw new IllegalArgumentException("Categoria não pode ser vazia!");
        }
        produto.setCategoria(produto.getCategoria().trim());

        this.repository.criarProduto(produto);
    }
}

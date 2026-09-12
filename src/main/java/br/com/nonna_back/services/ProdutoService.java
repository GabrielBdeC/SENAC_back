package br.com.nonna_back.services;

import br.com.nonna_back.models.Produto;
import br.com.nonna_back.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

    public List<Produto> getTodosProdutos(){
        return this.repository.getTodosProdutos();
    }

    public Produto getProduto(String id){
        validarId(id);

        return this.repository.getProduto(id);
    }

    public void criarProduto(Produto produto) {
        produto.setId("");
        produto = validarProduto(produto);
        this.repository.criarProduto(produto);
    }

    public void atualizarProduto(String id, Produto produto){
        validarId(id);
        produto = validarProduto(produto);
        this.repository.getProduto(id);
        this.repository.atualizarProduto(id, produto);
    }

    public void deleteProduto(String id){
        validarId(id);
        this.repository.getProduto(id);
        this.repository.deleteProduto(id);
    }

    private void validarId(String id){
        if (id == null || (id.trim()).isEmpty()){
            throw new IllegalArgumentException("Id não pode ser vazio!");
        }

        boolean idValido = false;
        try {
            if (UUID.fromString(id).toString().equals(id)){
                idValido = true;
            }
        } catch (Exception ex){
            idValido = false;
        }

        if (!idValido){
            throw new IllegalArgumentException("Id inválido");
        }
    }

    private Produto validarProduto(Produto produto){
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

        return produto;
    }
}

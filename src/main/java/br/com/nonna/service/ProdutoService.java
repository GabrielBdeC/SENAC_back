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

    // --- PARTE 1: validação manual (if + throw) ---
    // As anotações no modelo substituem este método inteiro.
    // Fica aqui para comparar a diferença de abordagem.
    //
    // private void validar(Produto p) {
    //     if (p.getNome() == null || p.getNome().isBlank())
    //         throw new IllegalArgumentException("O nome é obrigatório");
    //     if (p.getPreco() == null
    //             || new BigDecimal(p.getPreco()).compareTo(BigDecimal.ZERO) <= 0)
    //         throw new IllegalArgumentException("O preço deve ser maior que zero");
    // }
}

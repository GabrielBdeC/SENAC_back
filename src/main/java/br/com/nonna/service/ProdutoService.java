package br.com.nonna.service;

import br.com.nonna.model.Produto;
import br.com.nonna.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        validar(p);
        repository.inserir(p);
    }

    public void atualizar(String id, Produto p) {
        validar(p);
        repository.atualizar(id, p);
    }

    public void deletar(String id) {
        repository.deletar(id);
    }

    // Validação manual: if + throw, escrita à mão no service.
    // O throw interrompe o método na hora — o INSERT/UPDATE só roda se passar por tudo.
    private void validar(Produto p) {
        // isBlank() rejeita tanto o vazio ("") quanto texto só com espaços ("   ")
        if (p.getNome() == null || p.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório");
        }
        // getPreco() devolve String; convertemos para BigDecimal para usar compareTo
        // BigDecimal não aceita < ou >, por isso usamos compareTo(ZERO) <= 0
        if (p.getPreco() == null
                || new BigDecimal(p.getPreco()).compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero");
        }
    }
}

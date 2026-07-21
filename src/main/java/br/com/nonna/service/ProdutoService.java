package br.com.nonna.service;

import br.com.nonna.model.Produto;
import br.com.nonna.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// SERVICE — camada de regras de negócio.
// É Java puro: não sabe o que é HTTP e não sabe o que é SQL.
// Exemplos do que ficaria aqui: desconto de terça-feira, validação de
// preço mínimo, cálculo de frete. Por enquanto só repassa a lista,
// mas é aqui que essas regras entrariam no futuro.
@Service
public class ProdutoService {

    // O Service depende do Repository (a camada abaixo).
    // A seta sempre desce: Controller → Service → Repository.
    // Um Service jamais chama um Controller.
    private final ProdutoRepository repository;

    // Injeção de dependência via construtor.
    // O Spring cria o ProdutoRepository e o entrega aqui automaticamente.
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Método público que o Controller vai chamar.
    // Devolve a lista de produtos vinda do banco.
    public List<Produto> listar() {
        return repository.buscarTodos();
    }
}

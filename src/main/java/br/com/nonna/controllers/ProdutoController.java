package br.com.nonna.controllers;

import br.com.nonna.model.Produto;
import br.com.nonna.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// CONTROLLER — porta de entrada da aplicação.
// Recebe a requisição HTTP, chama o Service e devolve a resposta.
// Só isso. Nenhuma regra de negócio e nenhum SQL aqui dentro.
//
// @RestController = @Controller + @ResponseBody:
// diz ao Spring que os métodos desta classe respondem requisições HTTP
// e que o valor retornado vira o corpo da resposta (em JSON).
@RestController
public class ProdutoController {

    // O Controller depende do Service (a camada abaixo).
    // Não conhece o Repository nem o banco de dados.
    private final ProdutoService service;

    // Injeção de dependência via construtor.
    // O Spring cria o ProdutoService e o entrega aqui automaticamente.
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // @GetMapping("/produtos") mapeia requisições GET para a URL /produtos.
    // O Spring lê os getters do Produto e monta o JSON automaticamente.
    // Resposta esperada: [{"id":"...","nome":"Margherita","preco":"39.00",...}, ...]
    @GetMapping("/produtos")
    public List<Produto> listar() {
        return service.listar();
    }
}

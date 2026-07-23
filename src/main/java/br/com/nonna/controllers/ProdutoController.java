package br.com.nonna.controllers;

import br.com.nonna.model.Produto;
import br.com.nonna.service.ProdutoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RequestMapping define o prefixo comum — todos os endpoints ficam em /produtos.
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    // POST /produtos — cria um novo produto.
    // @RequestBody: o Spring lê o JSON do corpo da requisição e monta um objeto Produto.
    // Exemplo de corpo: {"nome":"Tiramisu","preco":18.50,"categoria":"Sobremesa"}
    @PostMapping
    public void criar(@RequestBody Produto p) {
        service.criar(p);
    }

    // PUT /produtos/{id} — atualiza um produto inteiro pelo id.
    // @PathVariable: pega o {id} da URL e injeta na variável local id.
    // Exemplo: PUT /produtos/abc-123 com o JSON novo no corpo.
    @PutMapping("/{id}")
    public void atualizar(@PathVariable String id, @RequestBody Produto p) {
        service.atualizar(id, p);
    }

    // DELETE /produtos/{id} — remove o produto com o id informado.
    // Não precisa de corpo: só o id na URL já basta para saber quem apagar.
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}

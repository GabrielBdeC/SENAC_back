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
import jakarta.validation.Valid;
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

    // --- PARTE 1: try/catch manual ---
    // Com @Valid o Spring valida antes de entrar no método;
    // o ValidacaoHandler trata o erro — sem try/catch aqui.
    //
    // @PostMapping
    // public ResponseEntity<String> criar(@RequestBody Produto p) {
    //     try {
    //         service.criar(p);
    //         return ResponseEntity.ok("Produto salvo");
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    // POST /produtos — @Valid dispara as anotações do modelo antes de entrar no método.
    // Dado inválido nem chega no service: o ValidacaoHandler devolve o 400.
    @PostMapping
    public void criar(@Valid @RequestBody Produto p) {
        service.criar(p);
    }

    // PUT /produtos/{id} — @Valid também vale na atualização: toda entrada de dados valida.
    @PutMapping("/{id}")
    public void atualizar(@PathVariable String id, @Valid @RequestBody Produto p) {
        service.atualizar(id, p);
    }

    // DELETE /produtos/{id} — remove o produto com o id informado.
    // Não precisa de corpo: só o id na URL já basta para saber quem apagar.
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}

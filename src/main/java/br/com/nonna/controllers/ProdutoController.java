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
import org.springframework.http.ResponseEntity;
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
    // ResponseEntity permite montar a resposta na mão: o status HTTP e o corpo.
    @PostMapping
    public ResponseEntity<String> criar(@RequestBody Produto p) {
        // try: tenta criar; se o service lançar throw, cai no catch
        try {
            service.criar(p);
            return ResponseEntity.ok("Produto salvo");
        } catch (IllegalArgumentException e) {
            // badRequest() = 400; e.getMessage() traz a mensagem do throw
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /produtos/{id} — atualiza um produto inteiro pelo id.
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable String id, @RequestBody Produto p) {
        try {
            service.atualizar(id, p);
            return ResponseEntity.ok("Produto atualizado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /produtos/{id} — remove o produto com o id informado.
    // Não precisa de corpo: só o id na URL já basta para saber quem apagar.
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}

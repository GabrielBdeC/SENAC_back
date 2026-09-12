package br.com.nonna_back.controllers;

import br.com.nonna_back.models.Produto;
import br.com.nonna_back.services.ProdutoService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
    private final ProdutoService service;

    ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping()
    List<Produto> getTodosProdutos() {
        return this.service.getTodosProdutos();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getProduto(@PathVariable String id) {
        try {
            Produto produto = this.service.getProduto(id);
            return ResponseEntity.status(HttpStatus.OK).body(produto);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", exception.getMessage()));
        } catch (EmptyResultDataAccessException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", "Produto não encontrado!"));
        }
    }

    @PostMapping()
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {
        try {
            this.service.criarProduto(produto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", exception.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable String id, @RequestBody Produto produto){
        try {
            this.service.atualizarProduto(id, produto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", exception.getMessage()));
        } catch (EmptyResultDataAccessException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", "Produto não encontrado!"));
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduto(@PathVariable String id) {
        try {
            this.service.deleteProduto(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", exception.getMessage()));
        } catch (EmptyResultDataAccessException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", "Produto não encontrado!"));
        }
    }

}

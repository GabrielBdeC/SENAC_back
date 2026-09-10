package br.com.nonna_back.controllers;

import br.com.nonna_back.models.Produto;
import br.com.nonna_back.services.ProdutoService;
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

    ProdutoController(ProdutoService service){
        this.service = service;
    }

    @GetMapping()
    List<Produto> getTodosProdutos(){
        return this.service.getTodosProdutos();
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
}

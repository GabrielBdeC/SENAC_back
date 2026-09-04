package br.com.nonna_back.controllers;

import br.com.nonna_back.models.Produto;
import br.com.nonna_back.services.ProdutoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoController {
    private final ProdutoService service;

    ProdutoController(ProdutoService service){
        this.service = service;
    }

    @GetMapping("/produtos")
    List<Produto> getTodosProdutos(){
        return this.service.getTodosProdutos();
    }
}

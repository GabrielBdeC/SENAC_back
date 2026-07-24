package br.com.nonna.controllers;

import br.com.nonna.dto.CadastroUsuario;
import br.com.nonna.dto.UsuarioResposta;
import br.com.nonna.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// O controller nunca recebe nem devolve a entidade Usuario — só DTOs.
@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioResposta> listar() {
        return service.listar();
    }

    @GetMapping("/por-email")
    public UsuarioResposta porEmail(@RequestParam String email) {
        return service.buscarPorEmail(email);
    }

    // @Valid: dispara a validação do record antes de entrar no método.
    // ResponseEntity permite escolher o status: 201 Created, não o 200 genérico.
    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResposta> cadastrar(
            @Valid @RequestBody CadastroUsuario dados) {
        UsuarioResposta criado = service.cadastrar(dados);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criado);

    }
}

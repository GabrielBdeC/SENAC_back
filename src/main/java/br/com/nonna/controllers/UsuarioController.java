package br.com.nonna.controllers;

import br.com.nonna.model.Usuario;
import br.com.nonna.service.UsuarioService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// ATENÇÃO — este controller tem três problemas intencionais (didáticos):
//   1. Senha em texto puro no banco.
//   2. Senha retornada na resposta JSON.
//   3. Sem controle de acesso: qualquer pessoa cadastra e lista usuários.
// Esses problemas têm solução e serão corrigidos em aulas futuras.
@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // GET /usuarios — lista todos os usuários cadastrados.
    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    // GET /usuarios/por-email?email=ana@cantina.com
    // @RequestParam lê o valor que vem depois da interrogação na URL.
    // É a peça central do login: encontrar o usuário pelo que ele digitou.
    @GetMapping("/por-email")
    public Usuario porEmail(@RequestParam String email) {
        return service.buscarPorEmail(email);
    }

    // POST /usuarios — cadastra um novo administrador.
    // Se o e-mail já existir, o UNIQUE da tabela barra e o Spring devolve 500.
    // Em produção esse erro seria tratado com uma mensagem amigável.
    @PostMapping
    public void criar(@RequestBody Usuario u) {
        service.criar(u);
    }
}

package br.com.nonna.conversor;

import br.com.nonna.dto.CadastroUsuario;
import br.com.nonna.dto.UsuarioResposta;
import br.com.nonna.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.UUID;

// Conversor: traduz entre os DTOs e a entidade.
// Centraliza a conversão — mudar o que a API aceita ou devolve é mexer aqui, só.
// Alguns times chamam esta classe de UsuarioMapper; o nome muda, o papel não.
@Component
public class UsuarioConversor {

    // Do DTO de entrada para a entidade. O id é gerado aqui:
    // o cliente manda três campos e sai uma entidade completa para o INSERT.
    public Usuario paraEntidade(CadastroUsuario dados) {
        Usuario u = new Usuario();
        u.setId(UUID.randomUUID());
        u.setNome(dados.nome());
        u.setEmail(dados.email());
        u.setSenha(dados.senha());
        return u;
    }

    // Da entidade para o DTO de saída — a senha fica para trás.
    public UsuarioResposta paraResposta(Usuario u) {
        return new UsuarioResposta(u.getId(), u.getNome(), u.getEmail());
    }
}

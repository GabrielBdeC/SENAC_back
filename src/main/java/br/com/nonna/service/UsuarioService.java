package br.com.nonna.service;

import br.com.nonna.conversor.UsuarioConversor;
import br.com.nonna.dto.CadastroUsuario;
import br.com.nonna.dto.UsuarioResposta;
import br.com.nonna.model.Usuario;
import br.com.nonna.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioConversor conversor;

    public UsuarioService(UsuarioRepository repository, UsuarioConversor conversor) {
        this.repository = repository;
        this.conversor = conversor;
    }

    public List<UsuarioResposta> listar() {
        List<Usuario> usuarios = repository.buscarTodos();
        List<UsuarioResposta> resposta = new ArrayList<>();
        for (Usuario u : usuarios) {
            resposta.add(conversor.paraResposta(u));
        }
        return resposta;
    }

    public UsuarioResposta buscarPorEmail(String email) {
        Usuario u = repository.buscarPorEmail(email);
        return u != null ? conversor.paraResposta(u) : null;
    }

    // Confere o e-mail antes do INSERT; lança exceção se já existir.
    // O UNIQUE no banco continua como última defesa para cadastros simultâneos.
    public UsuarioResposta cadastrar(CadastroUsuario dados) {
        Integer jaExiste = repository.contarPorEmail(dados.email());
        if (jaExiste > 0) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        Usuario u = conversor.paraEntidade(dados);
        repository.inserir(u);
        return conversor.paraResposta(u);
    }
}

package br.com.nonna.service;

import br.com.nonna.model.Usuario;
import br.com.nonna.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listar() {
        return repository.buscarTodos();
    }

    public Usuario buscarPorEmail(String email) {
        return repository.buscarPorEmail(email);
    }

    public void criar(Usuario u) {
        repository.inserir(u);
    }
}

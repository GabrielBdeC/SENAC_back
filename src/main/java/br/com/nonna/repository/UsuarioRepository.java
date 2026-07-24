package br.com.nonna.repository;

import br.com.nonna.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class UsuarioRepository {

    private final JdbcTemplate jdbc;

    public UsuarioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getString("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));

        // getTimestamp devolve java.sql.Timestamp; toLocalDateTime converte
        // para o tipo moderno que o modelo usa.
        if (rs.getTimestamp("criado_em") != null) {
            u.setCriadoEm(rs.getTimestamp("criado_em").toLocalDateTime());
        }
        return u;
    }

    public List<Usuario> buscarTodos() {
        return jdbc.query("SELECT * FROM usuario", (rs, linha) -> mapear(rs));
    }

    // Busca por e-mail: peça central do sistema de login.
    // @RequestParam na URL: /usuarios/por-email?email=ana@cantina.com
    public Usuario buscarPorEmail(String email) {
        return jdbc.query(
            "SELECT * FROM usuario WHERE email = ?",
            (rs, linha) -> mapear(rs),
            email)
            .stream()
            .findFirst()
            .orElse(null);
    }

    public void inserir(Usuario u) {
        // O id é gerado pela aplicação, assim como nas reservas.
        // O campo criado_em não é enviado: o banco preenche com DEFAULT CURRENT_TIMESTAMP.
        jdbc.update(
            "INSERT INTO usuario (id, nome, email, senha) VALUES (?, ?, ?, ?)",
            UUID.randomUUID().toString(),
            u.getNome(), u.getEmail(), u.getSenha());
    }
}

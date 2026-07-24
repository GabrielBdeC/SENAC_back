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
        // A coluna é CHAR(36); UUID.fromString converte o texto de volta para UUID.
        u.setId(UUID.fromString(rs.getString("id")));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));

        if (rs.getTimestamp("criado_em") != null) {
            u.setCriadoEm(rs.getTimestamp("criado_em").toLocalDateTime());
        }
        return u;
    }

    public List<Usuario> buscarTodos() {
        return jdbc.query("SELECT * FROM usuario", (rs, linha) -> mapear(rs));
    }

    public Usuario buscarPorEmail(String email) {
        List<Usuario> resultado = jdbc.query(
            "SELECT * FROM usuario WHERE email = ?",
            (rs, linha) -> mapear(rs),
            email);
        if (resultado.isEmpty()) {
            return null;
        }
        return resultado.get(0);
    }

    // COUNT(*) sempre devolve um número — nunca vazio —, por isso queryForObject é seguro aqui.
    public Integer contarPorEmail(String email) {
        return jdbc.queryForObject(
            "SELECT COUNT(*) FROM usuario WHERE email = ?",
            Integer.class, email);
    }

    public void inserir(Usuario u) {
        // O id foi gerado pelo UsuarioConversor antes de chegar aqui.
        // O campo criado_em não é enviado: o banco preenche com DEFAULT CURRENT_TIMESTAMP.
        jdbc.update(
            "INSERT INTO usuario (id, nome, email, senha) VALUES (?, ?, ?, ?)",
            u.getId().toString(),
            u.getNome(), u.getEmail(), u.getSenha());
    }
}

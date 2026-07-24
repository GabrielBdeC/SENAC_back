package br.com.nonna.repository;

import br.com.nonna.model.Reserva;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class ReservaRepository {

    private final JdbcTemplate jdbc;

    public ReservaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // RowMapper: traduz uma linha do banco em um objeto Reserva.
    // Escrito uma vez, reutilizado por todos os métodos de consulta.
    private Reserva mapear(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();

        // A coluna é CHAR(36); UUID.fromString converte o texto de volta para UUID.
        r.setId(UUID.fromString(rs.getString("id")));

        r.setNome(rs.getString("nome"));
        r.setTelefone(rs.getString("telefone"));
        r.setData(rs.getDate("data_reserva").toLocalDate());
        r.setHora(rs.getTime("hora").toLocalTime());
        r.setPessoas(rs.getInt("pessoas"));
        r.setObservacao(rs.getString("observacao"));
        return r;
    }

    // jdbc.query devolve lista — vazia quando não há resultado, nunca nula.
    public List<Reserva> buscarTodas() {
        return jdbc.query(
            "SELECT * FROM reserva ORDER BY data_reserva, hora",
            (rs, linha) -> mapear(rs));
    }

    // Para buscar por id usamos query (lista) em vez de queryForObject:
    // queryForObject lança exceção quando não encontra nada — um id inexistente derrubaria a requisição.
    public Reserva buscarPorId(UUID id) {
        List<Reserva> resultado = jdbc.query(
            "SELECT * FROM reserva WHERE id = ?",
            (rs, linha) -> mapear(rs),
            id.toString());
        if (resultado.isEmpty()) {
            return null;
        }
        return resultado.get(0);
    }

    public void inserir(Reserva r) {
        // O id é gerado aqui, antes do INSERT — a aplicação define o UUID, não o banco.
        UUID id = UUID.randomUUID();

        // Os ? evitam SQL Injection: nunca concatene valores diretamente no SQL.
        jdbc.update(
            "INSERT INTO reserva (id, nome, telefone, data_reserva, hora, pessoas, observacao)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)",
            id.toString(), r.getNome(), r.getTelefone(),
            r.getData(), r.getHora(), r.getPessoas(), r.getObservacao());
    }

    // WHERE é obrigatório: sem ele o UPDATE muda a tabela inteira.
    public void atualizar(UUID id, Reserva r) {
        jdbc.update(
            "UPDATE reserva SET data_reserva = ?, hora = ?, pessoas = ? WHERE id = ?",
            r.getData(), r.getHora(), r.getPessoas(), id.toString());
    }

    // WHERE é obrigatório: sem ele o DELETE apaga a tabela inteira.
    public void cancelar(UUID id) {
        jdbc.update("DELETE FROM reserva WHERE id = ?", id.toString());
    }
}

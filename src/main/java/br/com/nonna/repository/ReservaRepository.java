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

    // RowMapper: converte uma linha do ResultSet em um objeto Reserva.
    // Escrito uma só vez e reutilizado por todos os métodos de consulta.
    // Se uma coluna mudar, há um único lugar para alterar.
    private Reserva mapear(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();

        // A coluna guarda texto (CHAR 36); UUID.fromString converte de volta
        // para o tipo que a classe Java espera.
        r.setId(UUID.fromString(rs.getString("id")));

        r.setNome(rs.getString("nome"));
        r.setTelefone(rs.getString("telefone"));

        // getDate/getTime devolvem os tipos antigos (java.sql.Date / java.sql.Time).
        // toLocalDate() e toLocalTime() entregam os tipos modernos que o modelo usa.
        r.setData(rs.getDate("data_reserva").toLocalDate());
        r.setHora(rs.getTime("hora").toLocalTime());

        r.setPessoas(rs.getInt("pessoas"));
        r.setObservacao(rs.getString("observacao"));
        return r;
    }

    // jdbc.query: usado quando a resposta traz linhas.
    // Devolve lista — vazia quando não há resultado, nunca nula.
    public List<Reserva> buscarTodas() {
        return jdbc.query(
            "SELECT * FROM reserva ORDER BY data_reserva, hora",
            (rs, linha) -> mapear(rs));
    }

    // Para buscar uma reserva por id usamos query (que devolve lista) em vez de
    // queryForObject, porque queryForObject lança exceção quando não encontra nada
    // — um id inexistente derrubaria a requisição com erro 500.
    // Com query + stream, se não houver resultado simplesmente retorna null.
    public Reserva buscarPorId(UUID id) {
        return jdbc.query(
            "SELECT * FROM reserva WHERE id = ?",
            (rs, linha) -> mapear(rs),
            id.toString())
            .stream()
            .findFirst()
            .orElse(null);
    }

    public void inserir(Reserva r) {
        // O id é gerado aqui, antes do INSERT.
        // Como ele já existe antes de chegar no banco, podemos devolvê-lo
        // ao cliente sem precisar de outra consulta.
        UUID id = UUID.randomUUID();

        // Os ? são substituídos pelos argumentos na ordem — NUNCA concatene
        // valores diretamente no SQL, pois isso abre brecha para SQL Injection.
        jdbc.update(
            "INSERT INTO reserva (id, nome, telefone, data_reserva, hora, pessoas, observacao)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)",
            id.toString(), r.getNome(), r.getTelefone(),
            r.getData(), r.getHora(), r.getPessoas(), r.getObservacao());
    }

    // PUT: só data, hora e número de pessoas são atualizáveis.
    // O WHERE garante que apenas a reserva com aquele id é alterada.
    // Esquecer o WHERE atualiza a tabela inteira — sem aviso e sem volta.
    public void atualizar(UUID id, Reserva r) {
        jdbc.update(
            "UPDATE reserva SET data_reserva = ?, hora = ?, pessoas = ? WHERE id = ?",
            r.getData(), r.getHora(), r.getPessoas(), id.toString());
    }

    // O WHERE é indispensável: sem ele, o DELETE apaga a tabela inteira.
    public void cancelar(UUID id) {
        jdbc.update("DELETE FROM reserva WHERE id = ?", id.toString());
    }
}

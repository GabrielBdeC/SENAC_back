package br.com.nonna.repository;

import br.com.nonna.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Produto> buscarTodos() {
        return jdbcTemplate.query(
            "SELECT id, nome, preco, categoria FROM produto",
            (rs, linha) -> new Produto(
                rs.getString("id"),
                rs.getString("nome"),
                rs.getBigDecimal("preco"),
                rs.getString("categoria")
            ));
    }

    // jdbc.update executa comandos que mudam o banco: INSERT, UPDATE, DELETE.
    // Os ? são substituídos pelos argumentos na ordem — nunca concatene texto
    // direto no SQL, pois isso abre brecha para SQL Injection.
    // O id NÃO é passado no INSERT: o MySQL gera o UUID automaticamente.
    public void inserir(Produto p) {
        jdbcTemplate.update(
            "INSERT INTO produto (nome, preco, categoria) VALUES (?, ?, ?)",
            p.getNome(), p.getPreco(), p.getCategoria()
        );
    }

    // O WHERE garante que só o produto certo é alterado.
    // Sem ele, o UPDATE mudaria a tabela inteira.
    public void atualizar(String id, Produto p) {
        jdbcTemplate.update(
            "UPDATE produto SET nome = ?, preco = ?, categoria = ? WHERE id = ?",
            p.getNome(), p.getPreco(), p.getCategoria(), id
        );
    }

    // O WHERE é indispensável: sem ele, o DELETE apaga a tabela inteira.
    public void deletar(String id) {
        jdbcTemplate.update(
            "DELETE FROM produto WHERE id = ?",
            id
        );
    }
}

package br.com.nonna_back.repositories;

import br.com.nonna_back.models.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository {
    private final JdbcTemplate jdbcTemplate;

    ProdutoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Produto> getTodosProdutos(){
        return jdbcTemplate.query(
                "SELECT id, nome, descricao, preco, categoria FROM produto",
                (resultado, numLinha) ->  new Produto(
                        resultado.getString("id"),
                        resultado.getString("nome"),
                        resultado.getString("descricao"),
                        resultado.getBigDecimal("preco"),
                        resultado.getString("categoria")
                )
        );
    }
}

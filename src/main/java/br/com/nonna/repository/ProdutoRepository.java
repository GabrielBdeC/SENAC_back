package br.com.nonna.repository;

import br.com.nonna.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// REPOSITORY — única camada que conhece o banco de dados.
// Todo o SQL fica aqui. Service e Controller não sabem que existe SQL;
// eles apenas pedem uma List<Produto> e recebem de volta.
// Se um dia trocarmos MySQL por outro banco, só este arquivo muda.
@Repository
public class ProdutoRepository {

    // JdbcTemplate é a ferramenta do Spring para executar SQL.
    // O Spring cria e gerencia a conexão automaticamente com base
    // nas configurações do application.properties.
    private final JdbcTemplate jdbcTemplate;

    // Injeção de dependência via construtor: o Spring vê que o construtor
    // precisa de um JdbcTemplate, cria um e entrega aqui.
    // Não precisamos chamar "new JdbcTemplate()" em nenhum lugar.
    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Executa um SELECT e transforma cada linha do resultado em um objeto Produto.
    public List<Produto> buscarTodos() {
        return jdbcTemplate.query(
            // O SQL que será enviado ao MySQL.
            "SELECT id, nome, preco, categoria FROM produto",

            // RowMapper: uma função (lambda) chamada uma vez para cada linha.
            // rs  = ResultSet — representa a linha atual da tabela.
            // linha = número da linha (raramente usado, mas obrigatório na assinatura).
            (rs, linha) -> new Produto(
                rs.getString("id"),        // lê a coluna "id" como String
                rs.getString("nome"),       // lê a coluna "nome" como String
                rs.getBigDecimal("preco"),  // lê "preco" como BigDecimal (exato)
                rs.getString("categoria")   // lê "categoria" como String
            ));
    }
}

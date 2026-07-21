package br.com.nonna.model;

import java.math.BigDecimal;

// MODEL — representa um dado do sistema.
// Não tem @Component, @Service nem nenhuma anotação do Spring:
// é só Java puro. A única responsabilidade desta classe é carregar
// os campos de um produto de um lado ao outro da aplicação.
public class Produto {

    // UUID: texto de 36 caracteres gerado aleatoriamente (ex.: "a1b2c3d4-...").
    // Usamos String porque o banco armazena como VARCHAR(36).
    private String id;

    private String nome;

    // BigDecimal guarda o número EXATO — nunca use double ou float para dinheiro.
    // double/float usam ponto flutuante binário: 0.1 + 0.2 pode virar 0.30000000000000004.
    // BigDecimal resolve isso e o caixa fecha certinho.
    private BigDecimal preco;

    private String categoria;

    // Construtor com todos os campos: usado pelo Repository para montar o
    // objeto a partir de cada linha que o banco retorna.
    public Produto(String id, String nome, BigDecimal preco, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    // Getters e setters: o Spring usa os getters para montar o JSON.
    // Se um getter não existir, o campo some da resposta.

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    // getPreco devolve String em vez de BigDecimal.
    // No JSON o preço vai aparecer como "39.00" (texto), não como número.
    // O front-end faz Number(preco) na hora de somar — igual ao .value dos inputs.
    public String getPreco() { return preco.toString(); }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}

package br.com.nonna.model;

import java.math.BigDecimal;

public class Produto {

    private String id;
    private String nome;
    private BigDecimal preco;
    private String categoria;

    // Construtor vazio: o Jackson precisa dele para desserializar o JSON
    // que chega no corpo das requisições POST e PUT (@RequestBody).
    public Produto() {}

    public Produto(String id, String nome, BigDecimal preco, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPreco() { return preco.toString(); }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}

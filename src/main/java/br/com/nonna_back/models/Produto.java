package br.com.nonna_back.models;

import java.math.BigDecimal;

public class Produto {
    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;

    public Produto(String id, String nome, String descricao, BigDecimal preco, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }
}

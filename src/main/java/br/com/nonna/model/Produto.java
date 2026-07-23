package br.com.nonna.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class Produto {

    private String id;

    // @NotBlank: texto não pode ser vazio ("") nem só espaços ("   ")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    // @NotNull: o campo tem que vir no JSON
    // @Positive: o número tem que ser maior que zero
    @NotNull
    @Positive(message = "O preço deve ser maior que zero")
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

    public String getPreco() { return preco != null ? preco.toString() : null; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}

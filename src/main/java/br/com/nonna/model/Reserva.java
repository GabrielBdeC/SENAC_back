package br.com.nonna.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

// Espelho da tabela reserva — cada campo corresponde a uma coluna.
public class Reserva {

    // UUID: identificador de 36 caracteres gerado pela aplicação antes do INSERT.
    private UUID id;

    private String nome;
    private String telefone;

    // LocalDate e LocalTime: tipos do Java para DATE e TIME do MySQL.
    private LocalDate data;
    private LocalTime hora;

    private Integer pessoas;

    // observacao pode ser null — campo opcional na tabela.
    private String observacao;

    public Reserva() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public Integer getPessoas() { return pessoas; }
    public void setPessoas(Integer pessoas) { this.pessoas = pessoas; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}

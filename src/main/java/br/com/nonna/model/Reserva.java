package br.com.nonna.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

// Reserva representa uma linha da tabela reserva.
// Cada campo corresponde a uma coluna — mesmos tipos, mesmos nomes (em camelCase).
public class Reserva {

    // UUID: tipo do Java para o identificador de 36 caracteres.
    // O valor é criado pela aplicação antes do INSERT, então
    // uma reserva nunca existe sem id.
    private UUID id;

    private String nome;
    private String telefone;

    // LocalDate e LocalTime: os tipos modernos do Java para data e hora.
    // Combinam diretamente com DATE e TIME do MySQL.
    private LocalDate data;
    private LocalTime hora;

    private Integer pessoas;

    // observacao pode ser null: o campo é opcional na tabela.
    private String observacao;

    // Construtor vazio: necessário para o Jackson montar o objeto
    // a partir do JSON que chega no @RequestBody.
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

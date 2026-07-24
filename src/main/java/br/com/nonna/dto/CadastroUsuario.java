package br.com.nonna.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO de entrada: só os campos que o cliente digita no formulário.
// Sem id, sem criado_em — o cliente não pode mandar o que não está declarado aqui.
//
// record: o Java gera o construtor e os acessores (dados.nome(), dados.email()...)
// automaticamente. É uma classe de dados sem comportamento.
public record CadastroUsuario(

    @NotBlank(message = "Informe o nome")
    String nome,

    @NotBlank
    @Email(message = "E-mail inválido")
    String email,

    @NotBlank
    @Size(min = 8, message = "A senha precisa de 8 caracteres")
    String senha

) {}

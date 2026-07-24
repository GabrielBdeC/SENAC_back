package br.com.nonna.dto;

import java.util.UUID;

// DTO de saída: define exatamente o que o cliente recebe.
// A senha não existe neste tipo — não é uma regra para lembrar, é uma ausência estrutural.
public record UsuarioResposta(UUID id, String nome, String email) {}

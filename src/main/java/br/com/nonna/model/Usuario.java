package br.com.nonna.model;

import java.time.LocalDateTime;
import java.util.UUID;

// Usuario é a entidade: espelho fiel da tabela usuario no banco.
// Ela não conversa diretamente com o mundo externo — para isso existem os DTOs.
public class Usuario {

    // UUID: mesmo tipo usado na tabela (CHAR 36).
    // O valor é gerado pelo UsuarioConversor antes do INSERT.
    private UUID id;
    private String nome;
    private String email;
    private String senha;

    // criado_em é preenchido pelo banco (DEFAULT CURRENT_TIMESTAMP);
    // a aplicação não envia este campo no INSERT.
    private LocalDateTime criadoEm;

    public Usuario() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}

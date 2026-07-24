package br.com.nonna.model;

import java.time.LocalDateTime;

// Usuario representa um administrador do restaurante.
// ATENÇÃO — três problemas intencionais para fins didáticos:
//   1. A senha é armazenada em texto puro (nunca faça isso em produção).
//   2. A senha sai na resposta JSON (o campo deve ser omitido na resposta).
//   3. Não há controle de permissão (qualquer um cadastra e lista usuários).
// Esses pontos têm solução — eles serão corrigidos em aulas futuras.
public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;

    // criado_em é preenchido pelo banco (DEFAULT CURRENT_TIMESTAMP);
    // a aplicação não envia este campo no INSERT.
    private LocalDateTime criadoEm;

    public Usuario() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}

-- migration/3-usuario.sql
-- Cria a tabela de administradores do restaurante.
-- Execute DEPOIS de rodar o 2-reserva.sql.

USE `nonna-db`;

CREATE TABLE usuario (
  id         CHAR(36)      PRIMARY KEY,
  nome       VARCHAR(120)  NOT NULL,

  -- UNIQUE: o banco recusa um segundo cadastro com o mesmo endereço.
  -- É a garantia que não depende de ninguém lembrar de conferir no código.
  email      VARCHAR(160)  NOT NULL UNIQUE,

  -- VARCHAR(255) de propósito: quando a senha parar de ser texto puro
  -- e virar um hash criptográfico, o valor vai ocupar mais espaço.
  senha      VARCHAR(255)  NOT NULL,

  -- Único campo preenchido pelo banco: registra quando o cadastro foi feito.
  criado_em  TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);

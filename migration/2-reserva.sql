-- migration/2-reserva.sql
-- Cria a tabela que guarda os pedidos de mesa feitos pelos clientes.
-- Execute DEPOIS de rodar o 1-produto.sql.

USE `nonna-db`;

CREATE TABLE reserva (
  -- CHAR(36): UUID sempre tem exatamente 36 caracteres com traços.
  -- Tamanho fixo → CHAR, não VARCHAR.
  -- O valor chega pronto da aplicação; o banco apenas armazena.
  id            CHAR(36)      PRIMARY KEY,

  nome          VARCHAR(120)  NOT NULL,
  telefone      VARCHAR(20)   NOT NULL,

  -- "data_reserva" em vez de "data": evita colidir com palavras
  -- reservadas do próprio SQL.
  data_reserva  DATE          NOT NULL,
  hora          TIME          NOT NULL,
  pessoas       INT           NOT NULL,

  -- observacao não tem NOT NULL: campo opcional.
  -- A ausência da restrição é o que comunica isso ao banco.
  observacao    VARCHAR(255)
);

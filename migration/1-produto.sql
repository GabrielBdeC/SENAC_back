-- migration/1-produto.sql
-- Passo 1: cria a tabela produto e insere os dados iniciais.
-- Execute DEPOIS de rodar o 0-create_db.sql.

-- Ativa o banco criado no passo anterior.
USE `nonna-db`;

-- Cria a tabela que guarda os pratos do cardápio.
CREATE TABLE produto (
  -- UUID: código aleatório de 36 caracteres (ex.: "a1b2c3d4-...").
  -- DEFAULT (UUID()) faz o MySQL gerar o valor sozinho a cada INSERT.
  -- Usamos UUID em vez de 1, 2, 3 para não expor o total de registros na URL.
  id          VARCHAR(36)     PRIMARY KEY DEFAULT (UUID()),

  -- NOT NULL: o banco recusa qualquer INSERT que omita esses campos.
  nome        VARCHAR(80)     NOT NULL,

  -- Descrição é opcional (sem NOT NULL), pode ficar vazia.
  descricao   VARCHAR(255),

  -- DECIMAL(10,2): 10 dígitos no total, 2 depois da vírgula.
  -- NUNCA use FLOAT para dinheiro: float guarda aproximações binárias
  -- e 0.1 + 0.2 pode virar 0.30000000000000004. DECIMAL é exato.
  preco       DECIMAL(10,2)   NOT NULL,

  -- Categoria permite filtrar o cardápio no front-end (ex.: pizza, bebida).
  categoria   VARCHAR(30)
);

-- Insere os pratos iniciais do cardápio.
-- O campo id NÃO é citado: o banco gera o UUID automaticamente pelo DEFAULT.
INSERT INTO produto (nome, descricao, preco, categoria)
VALUES ('Fettuccine ao molho branco', 'Massa fresca ao molho de creme de leite com parmão ralado na hora e toque de noz-moscada.', 42.00, 'massa'),
       ('Pizza margherita', 'Pizza no forno a lenha com molho de tomate artesanal, mussarela fresca e folhas de manjericão.', 55.00, 'pizza'),
       ('Polenta com fortaia', 'Polenta cremosa servida com fortaia — omelete de ovos caipiras — prato típico da cozinha ítalo-gauchesca.', 35.00, 'entrada'),
       ('Salame e queijo colonial', 'Salame artesanal fatiado acompanhado de queijo colonial da região, servido com pão caseiro.', 38.00, 'entrada'),
       ('Vinho Goethe (taça)', 'Vinho branco da uva Goethe, cultivada em Urussanga/SC. Aroma floral, sabor suave e levemente ácido.', 25.00, 'bebida');

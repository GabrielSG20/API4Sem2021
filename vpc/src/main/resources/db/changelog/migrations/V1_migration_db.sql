liquibase formatted sql
Adicionando um sequence no banco de dados e inserindo alguns valores padrões dentro da tabela Espaco
changeset Gabriel Ferraz:2

CREATE SEQUENCE id_chave_seq;

changeset Soares:3
INSERT INTO Espaco VALUES (ID_CHAVE_SEQ.nextval,50,'Open Space');
INSERT INTO Espaco VALUES (ID_CHAVE_SEQ.nextval,20,'Lounge on Hall');

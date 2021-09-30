--liquibase formatted sql
--changeset Gabriel Ferraz:1

CREATE SEQUENCE id_chave_seq;

--changeset Soares:6
INSERT INTO ESPACO VALUES (id_chave_seq.nextval, 50,'Open Space');
INSERT INTO ESPACO VALUES (id_chave_seq.nextval, 20,'Lounge on Hall');
--liquibase formatted sql
--changeset Gabriel Ferraz:6

CREATE SEQUENCE id_chave_seq;

--changeset Soares:7
INSERT INTO Espaco VALUES (ID_CHAVE_SEQ.nextval,50,'Open Space');
INSERT INTO Espaco VALUES (ID_CHAVE_SEQ.nextval,20,'Lounge on Hall');

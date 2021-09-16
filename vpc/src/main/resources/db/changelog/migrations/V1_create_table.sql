--liquibase formatted sql
--changeset Gabriel:1

CREATE TABLE Categoria (
    cat_id INTEGER CONSTRAINT pk_categoria PRIMARY KEY,
    cat_categoria Varchar2(30) NOT NULL,
    cat_descricao Varchar2(30)

) ;

CREATE TABLE Espaco (
    esp_id Integer CONSTRAINT pk_espaco PRIMARY KEY,
    esp_nome Varchar2(30)  NOT NULL,
    esp_capacidade Integer  NOT NULL
) ;

CREATE TABLE Evento (
    evt_id Integer CONSTRAINT pk_evento PRIMARY KEY,
    evt_titulo Varchar2(30)  NOT NULL,
    evt_descricao Varchar2(80),
    evt_data_inicio Date NOT NULL,
    evt_data_fim Date NOT NULL,
    evt_tipo Varchar2(10) NOT NULL,
    evt_status Integer DEFAULT NULL,
    evt_imagem Blob,
    usu_email Varchar2(80)
) ;

CREATE TABLE Fornecedor (
    for_cnpj Integer CONSTRAINT pk_fornecedor PRIMARY KEY,
    for_nome Varchar2(30) NOT NULL,
    for_telefone Integer NOT NULL
) ;

CREATE TABLE Usuario (
    usu_email Varchar2(80) CONSTRAINT pk_usuario PRIMARY KEY,
    usu_nome Varchar2(80) NOT NULL,
    usu_cpf Integer CONSTRAINT uk_usuario_cpf UNIQUE,
    usu_telefone Integer NOT NULL,
    usu_departamento Varchar2(30),
    usu_nome_empresa Varchar2(50),
    usu_id_oracle Integer,
    usu_comprovante_vacinacao Blob NOT NULL,
    usu_tipo Varchar2(10) NOT NULL
) ;

CREATE TABLE Evento_Espaco (
    esp_id Integer,
    evt_id Integer,
    CONSTRAINT pk_evento_espaco PRIMARY KEY (esp_id,evt_id)
);

CREATE TABLE Evento_Fornecedor (
    evt_id Integer,
    for_cnpj Integer,
    CONSTRAINT pk_evento_fornecedor PRIMARY KEY (evt_id,for_cnpj)
) ;

CREATE TABLE Fornecedor_Categoria (
    for_cnpj Integer,
    cat_id Integer,
    CONSTRAINT pk_fornecedor_categoria PRIMARY KEY (for_cnpj,cat_id)
) ;

CREATE TABLE Usuario_Evento (
    evt_id Integer,
    usu_email Varchar2(80),
    CONSTRAINT pk_usuario_evento PRIMARY KEY (evt_id,usu_email)
) ;

ALTER TABLE Evento_Espaco ADD CONSTRAINT fk_evento_espaco_esp_id
    FOREIGN KEY (esp_id)
    REFERENCES Espaco (esp_id);

ALTER TABLE Evento_Espaco ADD CONSTRAINT fk_evento_espaco_evt_id
    FOREIGN KEY (evt_id)
    REFERENCES Evento (evt_id);

ALTER TABLE Evento_Fornecedor ADD CONSTRAINT fk_evento_forn_evt_id
    FOREIGN KEY (evt_id)
    REFERENCES Evento (evt_id);

ALTER TABLE Evento_Fornecedor ADD CONSTRAINT fk_evento_forn_for_cnpj
    FOREIGN KEY (for_cnpj)
    REFERENCES Fornecedor (for_cnpj);

ALTER TABLE Fornecedor_Categoria ADD CONSTRAINT fk_fornecedor_cat_cat_id
    FOREIGN KEY (cat_id)
    REFERENCES Categoria (cat_id);

ALTER TABLE Fornecedor_Categoria ADD CONSTRAINT fk_fornecedor_cat_for_cnpj
    FOREIGN KEY (for_cnpj)
    REFERENCES Fornecedor (for_cnpj);

ALTER TABLE Usuario_Evento ADD CONSTRAINT fk_usuario_evento_evt_id
    FOREIGN KEY (evt_id)
    REFERENCES Evento (evt_id);

ALTER TABLE Usuario_Evento ADD CONSTRAINT fk_usuario_evento_usu_email
    FOREIGN KEY (usu_email)
    REFERENCES Usuario (usu_email);

ALTER TABLE Evento ADD CONSTRAINT fk_evento_usu_email
    FOREIGN KEY (usu_email)
    REFERENCES Usuario (usu_email);

--changeset Gabriel:2

CREATE SEQUENCE id_chave_seq;


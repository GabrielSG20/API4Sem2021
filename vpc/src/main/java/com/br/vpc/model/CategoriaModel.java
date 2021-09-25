package com.br.vpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "Categoria")
    public class CategoriaModel {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
        @SequenceGenerator(name = "id_generator", sequenceName = "id_chave_seq", allocationSize = 1)
        @Column(name = "cat_id")
        private Integer idCategoria;

        @Column(name = "cat_nome", nullable = false, length = 30)
        private String nomeCategoria;

        @Column(name = "cat_descricao", length = 30)
        private String descCategoria;
    }


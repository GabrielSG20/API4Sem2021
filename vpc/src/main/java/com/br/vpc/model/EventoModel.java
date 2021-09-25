package com.br.vpc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Evento")
public class EventoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_chave_seq", allocationSize = 1)
    @Column(name = "evt_id")
    private Integer idEvento;

    @Column(name = "evt_titulo", nullable = false, length = 30)
    private String titulo;

    @Column(name = "evt_descricao", length = 80)
    private String descricao;

    @Column(name = "evt_data_inicio", nullable = false, length = 100)
    private String dataInicio;

    @Column(name = "evt_data_fim", nullable = false, length = 100)
    private String dataEncerramento;

    @Column(name = "evt_tipo", nullable = false, length = 10)
    private String tipoEvento;

    @Column(name = "evt_status", columnDefinition = "number default NULL")
    private Integer status;//DEFAULT NULL

    @Column(name = "evt_imagem")
    private Blob imagemDivulgacao;

    @Column(name = "usu_email")
    private String email;

}

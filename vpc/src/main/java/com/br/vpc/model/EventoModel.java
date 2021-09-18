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
import java.util.Date;

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

    @NonNull
    @Column(name = "evt_titulo")
    private String titulo;

    @Size(max = 250)
    @Column(name = "evt_descricao")
    private String descricao;

    @NotBlank @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "evt_data_inicio")
    private Date dataInicio;

    @NotBlank @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "evt_data_fim")
    private Date dataEncerramento;

    @Column(name = "evt_tipo")
    private String tipoEvento;

    @Size(max = 4)
    @Column(name = "evt_status")
    private Integer status;

    @Column(name = "evt_imagem")
    private Blob imagemDivulgacao;

    @Email
    @Column(name = "usu_email")
    private String email;
}

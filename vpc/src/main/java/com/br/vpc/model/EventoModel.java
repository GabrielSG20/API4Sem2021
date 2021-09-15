package com.br.vpc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evento")
public class EventoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idEvento")
    private Integer idEvento;

    @NonNull
    @Column(name = "nome")
    private String nome;

    @Size(max = 250)
    @Column(name = "descricao")
    private String descricao;

    @NonNull
    @Column(name = "status")
    private boolean status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @Column(name = "data")
    private Date data;

    @NotBlank
    @Column(name = "hora_inicio")
    private Time hora_inicio;

    @NotBlank
    @Column(name = "hora_encerramento")
    private Time hora_encerramento;


}

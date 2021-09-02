package com.br.vpc.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Eventos")
public class EventoModel {

    @Id
    private String nome;

    private String descricao;

    private boolean status;

    private Date data;
}

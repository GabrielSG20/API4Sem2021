package com.br.vpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizador")
public class OrganizadorModel {
    @Id
    String email;

    String nomeCompleto;

    Integer cpf;

    Integer idOracle;

    Integer contato;
}

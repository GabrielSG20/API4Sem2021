package com.br.vpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizador")
public class OrganizadorModel {
   @Id
   @Email
   @Column(name = "email")
   private String email;

   @Column(name = "nomeCompleto")
   private String nomeCompleto;

   @Max(11) @Min(11)
   @Column(name = "cpf")
   private Integer cpf;

   @Column(name = "idOracle")
   private Integer idOracle;

   @Column(name = "contato")
   private Integer contato;
}

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
import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Usuario")
public class OrganizadorModel {
   @Id
   @Email
   @Column(name = "usu_email")
   private String email;

   @Column(name = "usu_nome")
   private String nomeCompleto;

   @Max(11) @Min(11)
   @Column(name = "usu_cpf")
   private Integer cpf;

   @Column(name = "usu_telefone")
   private Integer telefone;

   @Column(name = "usu_departamento")
   private String departamento;

   @Column(name = "usu_nome_empresa")
   private String nomeEmpresa;

   @Column(name = "usu_id_oracle")
   private Integer idOracle;

   @Column(name = "usu_comprovante_vacinacao")
   private Blob comprovanteVacinacao;

   @Column(name = "usu_tipo")
   private String tipoUsuario;
}

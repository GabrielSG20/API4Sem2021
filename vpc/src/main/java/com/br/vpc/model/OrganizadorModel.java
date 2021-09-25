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
    @Column(name = "usu_email", length = 80)
    private String email;

    @Column(name = "usu_nome", nullable = false, length = 80)
    private String nomeCompleto;

    @Column(name = "usu_cpf", unique = true, nullable = false)
    private Integer cpf;

    @Column(name = "usu_telefone", nullable = false)
    private Integer telefone;

    @Column(name = "usu_departamento", length = 30)
    private String departamento;

    @Column(name = "usu_nome_empresa", length = 50)
    private String nomeEmpresa;

    @Column(name = "usu_id_oracle")
    private Integer idOracle;

    @Column(name = "usu_comprovante_vacinacao", nullable = false)
    private Blob comprovanteVacinacao;

    @Column(name = "usu_tipo", nullable = false, length = 20)
    private String tipoUsuario;

    @Column(name = "usu_cargo", length = 50)
    private String cargoUsuario;

    @Column(name = "usu_senha", unique = true, nullable = false, length = 100)
    private String senhaUsuario;
}

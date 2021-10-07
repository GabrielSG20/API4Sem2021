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
@Table(name = "Usuario")
public class UsuarioModel {

    @Id
    @Email
    @Size(max = 80)
    @Column(name = "usu_email")
    private String email;

    @NonNull
    @Column(name = "usu_nome")
    @Size(max = 80)
    public String nome;

    @NonNull
    @Column(name = "usu_cpf ",unique=true)
    private String cpf;

    @NonNull
    @Column(name = "usu_telefone")
    private String telefone;


    @Column(name = "usu_departamento")
    @Size(max = 30)
    private String departamento;

    @Column(name = "usu_nome_empresa")
    @Size(max = 50)
    private String nome_empresa;

    @Column(name = "usu_id_oracle")
    private Integer id_oracle;

    @NonNull
    @Column(name = "usu_comprovante_vacinacao")
    private String comprovante_vacinacao;

    @NonNull
    @Column(name = "usu_tipo")
    @Size(max = 10)
    private String tipo;


    @Column(name = "usu_cargo")
    @Size(max = 50)
    private String cargo;


}

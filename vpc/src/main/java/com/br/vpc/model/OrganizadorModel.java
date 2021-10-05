package com.br.vpc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Usuario")
public class OrganizadorModel {
    @Email
    @Id
    @Column(name = "usu_email", length = 80)
    private String email;

    @Column(name = "usu_nome", nullable = false, length = 80)
    private String nomeCompleto;

    @Column(name = "usu_cpf", unique = true, nullable = false, length = 15)
    private String cpf;

    @Column(name = "usu_telefone", nullable = false)
    private String telefone;

    @Column(name = "usu_departamento", length = 30)
    private String departamento;

    @Column(name = "usu_nome_empresa", length = 50)
    private String nomeEmpresa;

    @Column(name = "usu_id_oracle")
    private Integer idOracle;

    @Column(name = "usu_comprovante_vacinacao", nullable = false)
    private String comprovanteVacinacao;

    @Column(name = "usu_tipo", nullable = false, length = 20)
    private String tipoUsuario;

    @Column(name = "usu_cargo", length = 50)
    private String cargoUsuario;

    @Column(name = "usu_senha", unique = true, nullable = false, length = 100)
    private String senhaUsuario;

    @JsonIgnore
    @OneToMany(mappedBy = "org")
    private List<EventoModel> eventos_solicitados = new ArrayList<>();
}

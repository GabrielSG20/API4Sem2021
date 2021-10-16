package com.br.vpc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Usuario")
public class UsuarioModel implements UserDetails {
    @NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    @Id
    @Column(name = "usu_email", length = 80)
    private String email;

    @NotBlank(message = "{nome.not.blank}")
    @Column(name = "usu_nome", nullable = false, length = 80)
    private String nomeCompleto;

    @NotBlank(message = "{cpf.not.blank}")
    @Column(name = "usu_cpf", unique = true, nullable = false, length = 15)
    private String cpf;

    @NotBlank(message = "{telefone.not.blank}")
    @Column(name = "usu_telefone", nullable = false)
    private String telefone;

    @Column(name = "usu_departamento", length = 30)
    private String departamento;

    @Column(name = "usu_nome_empresa", length = 50)
    private String nomeEmpresa;

    @Column(name = "usu_id_oracle")
    private Integer idOracle;

    @Column(name = "usu_comprovante_vacinacao")
    private String comprovanteVacinacao;

    @NotBlank(message = "{tipo_u.not.blank}")
    @Column(name = "usu_tipo", nullable = false, length = 20)
    private String tipoUsuario;

    @Column(name = "usu_cargo", length = 50)
    private String cargoUsuario;

    @NotBlank(message = "{senha.not.blank}")
    @Column(name = "usu_senha", unique = true, nullable = false, length = 100)
    private String senhaUsuario;

    @JsonIgnore
    @OneToMany(mappedBy = "org", fetch = FetchType.EAGER)
    private List<EventoModel> eventos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(tipoUsuario.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.senhaUsuario;
    }

    @Override
    public String getUsername() {
        return this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

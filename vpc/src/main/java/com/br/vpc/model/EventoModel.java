package com.br.vpc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

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

    @NotBlank(message = "{titulo.not.blank}")
    @Column(name = "evt_titulo", nullable = false, length = 30)
    private String titulo;

    @Column(name = "evt_descricao", length = 80)
    private String descricao;

    @NotBlank(message = "{data_i.not.blank}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "evt_data_inicio", nullable = false, length = 100)
    private String dataInicio;

    @NotBlank(message = "{data_f.not.blank}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "evt_data_fim", nullable = false, length = 100)
    private String dataEncerramento;

    @NotBlank(message = "{tipo.not.blank}")
    @Column(name = "evt_tipo", nullable = false, length = 10)
    private String tipoEvento;

    @Column(name = "evt_status", columnDefinition = "number default NULL")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "usu_email")
    private UsuarioModel org;

    @ManyToMany
    @JoinTable(name = "evento_espaco",joinColumns = @JoinColumn(name = "evt_id"),
                               inverseJoinColumns = @JoinColumn(name = "esp_id"))
    private Set<EspacoModel> nomeEspaco = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "evento_fornecedor",joinColumns = @JoinColumn(name = "evt_id"),
                                        inverseJoinColumns = @JoinColumn(name = "for_cnpj"))
    private Set<FornecedorModel> fornecedores = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "evento_usuario",joinColumns = @JoinColumn(name = "evt_id"),
            inverseJoinColumns = @JoinColumn(name = "usu_email"))
    private Set<UsuarioModel> convidados = new HashSet<>();
}

package com.br.vpc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Blob;
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

    @Column(name = "evt_titulo", nullable = false, length = 30)
    private String titulo;

    @Column(name = "evt_descricao", length = 80)
    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "evt_data_inicio", nullable = false, length = 100)
    private String dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    @Column(name = "evt_data_fim", nullable = false, length = 100)
    private String dataEncerramento;

    @Column(name = "evt_tipo", nullable = false, length = 10)
    private String tipoEvento;

    @Column(name = "evt_status", columnDefinition = "number default NULL")
    private Integer status;

    @Column(name = "evt_imagem")
    private Blob imagemDivulgacao;

    @JsonIgnoreProperties("eventos")
    @ManyToOne
    @JoinColumn(name = "usu_email")
    private OrganizadorModel org;

    @ManyToMany
    @JoinTable(name = "evento_espaco",joinColumns = @JoinColumn(name = "evt_id"),
                               inverseJoinColumns = @JoinColumn(name = "esp_id"))
    private Set<EspacoModel> nomeEspaco = new HashSet<>();

}

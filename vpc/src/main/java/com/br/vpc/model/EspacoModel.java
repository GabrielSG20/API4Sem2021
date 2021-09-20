package com.br.vpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Espaco")
public class EspacoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_chave_seq", allocationSize = 1)
    @Column(name = "esp_id")
    private Integer idEspaco;

    @Column(name = "esp_nome")
    private String nomeEspaco;

    @Column(name = "esp_capacidade")
    private Integer capEspaco;
}

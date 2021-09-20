package com.br.vpc.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EventoEspacoID implements Serializable {

    @Column(name = "esp_id")
    private Integer espacoId;

    @Column(name = "evt_id")
    private Integer eventoId;
}

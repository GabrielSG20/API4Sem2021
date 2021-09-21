package com.br.vpc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {

    private String[] nomeEspaco;

    @NonNull
    private String titulo;

    @Size(max = 250)
    private String descricao;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    private String dataInicio;

    @NotBlank @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    private String dataEncerramento;

    private String tipoEvento;

    private Integer status;

    private Blob imagemDivulgacao;

    @Email
    private String email;
}

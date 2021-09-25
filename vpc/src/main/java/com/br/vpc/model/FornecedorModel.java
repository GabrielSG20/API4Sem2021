package com.br.vpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Fornecedor")
public class FornecedorModel {
    @Id
    @Column(name = "for_cnpj")
    private Integer cnpjFornecedor;

    @Column(name = "for_nome", nullable = false, length = 30)
    private String nomeFornecedor;

    @Column(name = "for_telefone",nullable = false)
    private Integer telefoneFornecedor;
}
package com.br.vpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Fornecedor")
public class FornecedorModel {
    @Id
    @Column(name = "for_cnpj")
    private String cnpjFornecedor;

    @Column(name = "for_nome", nullable = false, length = 30)
    private String nomeFornecedor;

    @Column(name = "for_telefone",nullable = false)
    private String telefoneFornecedor;

    @ManyToMany
    @JoinTable(name = "fornecedor_categoria",joinColumns = @JoinColumn(name = "for_cnpj"),
            inverseJoinColumns = @JoinColumn(name = "cat_id"))
    private Set<CategoriaModel> categorias = new HashSet<>();
}
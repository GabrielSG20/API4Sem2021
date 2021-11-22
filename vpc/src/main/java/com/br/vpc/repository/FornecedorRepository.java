package com.br.vpc.repository;

import com.br.vpc.model.FornecedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorModel, String> {


    Optional<FornecedorModel> findByCnpj(String cnpj);

    void deleteByCnpj(String cnpj);
}

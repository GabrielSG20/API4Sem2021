package com.br.vpc.repository;

import com.br.vpc.model.EspacoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<EspacoModel, Integer> {
    @Query(" select idCategoria from CategoriaModel where nomeCategoria = ?1 ")
    Integer findCategoriaByName(String nomeCategoria);
}

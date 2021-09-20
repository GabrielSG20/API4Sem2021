package com.br.vpc.repository;

import com.br.vpc.model.EspacoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacoRepository extends JpaRepository<EspacoModel, Integer> {
    @Query(" select idEspaco from EspacoModel where nomeEspaco = ?1 ")
    Integer findEspacoByName(String nomeEspaco);
}

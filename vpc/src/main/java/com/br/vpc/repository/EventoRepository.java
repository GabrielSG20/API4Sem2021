package com.br.vpc.repository;

import com.br.vpc.model.EventoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.Date;

@Repository
public interface EventoRepository extends JpaRepository<EventoModel, Integer> {
    @Query(" select idEvento from EventoModel where titulo = ?1 ")
    Integer findEventoByTitle(String title);
}

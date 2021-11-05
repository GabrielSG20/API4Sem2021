package com.br.vpc.repository;

import com.br.vpc.model.EventoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<EventoModel, Integer> {
    @Query(" from EventoModel where idEvento = ?1 ")
    EventoModel findEventoById(Integer id);

    @Query(" from EventoModel where status = 1 ")
    List<EventoModel> findEventosAprovados();
}

package com.br.vpc.repository;

import com.br.vpc.model.EventoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<EventoModel, String> {
}

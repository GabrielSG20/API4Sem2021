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

    @Query(" from EventoModel where status = 1 and tipoEvento = 'Public'")
    List<EventoModel> findEventosAprovados();

    @Query(" select SUBSTR(TO_CHAR(e.dataInicio),1,10) from EventoModel e group by SUBSTR(TO_CHAR(e.dataInicio),1,10) having count(1) > 1 ")
    List<String> findEventosMesmaData();

    @Query(" from EventoModel where status = 1 and tipoEvento = 'Private'")
    List<EventoModel> findEventosAprovadosFechado();

    @Query(" from EventoModel where status = 1 and tipoEvento = 'Public'")
    List<EventoModel> findEventosAprovadosAberto();
}

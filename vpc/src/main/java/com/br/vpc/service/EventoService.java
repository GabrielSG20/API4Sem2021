package com.br.vpc.service;

import com.br.vpc.model.EventoModel;
import com.br.vpc.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    public void cadastrar(EventoModel eventoModel) {
        eventoRepository.save(eventoModel);
    }

    public void atualizar(EventoModel eventoModel){
        eventoRepository.save(eventoModel);
    }

    public void deletar(String titulo){
        eventoRepository.deleteByTitulo(titulo);
    }
}

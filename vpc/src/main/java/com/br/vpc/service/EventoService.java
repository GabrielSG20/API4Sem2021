package com.br.vpc.service;

import com.br.vpc.model.EventoModel;
import com.br.vpc.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    public void cadastrar(EventoModel eventoModel) {
        eventoRepository.save(eventoModel);
    }

    public void aprovarEvento(String title){
        Integer id = eventoRepository.findEventoByTitle(title);
        Optional<EventoModel> evento = eventoRepository.findById(id);
        EventoModel eventoModel = evento.get();
        eventoModel.setStatus(1);
        eventoRepository.save(eventoModel);
    }

    public void deletar(String title){
        Integer id = eventoRepository.findEventoByTitle(title);
        eventoRepository.deleteById(id);
    }

    public List<EventoModel> listar(){ return eventoRepository.findAll(); }
}

package com.br.vpc.service;

import com.br.vpc.model.EventoModel;
import com.br.vpc.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import javax.swing.text.html.Option;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    private EventoModel findById(String nome){
        Optional<EventoModel> eventoModel = eventoRepository.findById(nome);
        return eventoModel.orElse(null);
    }

    public void cadastrar(EventoModel eventoModel) {
        eventoRepository.save(eventoModel);
    }

    public void atualizar(EventoModel eventoModel){
        EventoModel evento = findById(eventoModel.getNome());
        evento.setDescricao(eventoModel.getDescricao());
        evento.setData(eventoModel.getData());
        evento.setStatus(eventoModel.isStatus());
        eventoRepository.save(evento);
    }

    public void deletar(EventoModel eventoModel){
        findById(eventoModel.getNome());
        eventoRepository.deleteById(eventoModel.getNome());
    }

}

package com.br.vpc.service;
/*
import com.br.vpc.model.EventoDTO;
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

    public void cadastrar(EventoDTO eventoDTO) {
        EventoModel event = new EventoModel();
        event.setTitulo(eventoDTO.getTitulo());
        event.setDescricao(eventoDTO.getDescricao());
        event.setDataInicio(eventoDTO.getDataInicio());
        event.setDataEncerramento(eventoDTO.getDataEncerramento());
        event.setTipoEvento(eventoDTO.getTipoEvento());
        event.setStatus(eventoDTO.getStatus());
        event.setImagemDivulgacao(eventoDTO.getImagemDivulgacao());
        event.setEmail(eventoDTO.getEmail());/*
        eventoRepository.save(event);
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

    public Integer findEventByTitle(String title){
        return eventoRepository.findEventoByTitle(title);
    }

    public List<EventoModel> listar(){ return eventoRepository.findAll(); }
}
*/
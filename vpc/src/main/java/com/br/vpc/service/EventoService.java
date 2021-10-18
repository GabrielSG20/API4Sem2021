package com.br.vpc.service;

import com.br.vpc.model.EspacoModel;
import com.br.vpc.model.EventoModel;
import com.br.vpc.model.UsuarioModel;
import com.br.vpc.repository.EspacoRepository;
import com.br.vpc.repository.EventoRepository;
import com.br.vpc.repository.UsuarioRepository;
import com.br.vpc.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    EspacoRepository espacoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public void cadastrar(EventoModel event) {
        for(EspacoModel espaco:event.getNomeEspaco()){ espaco.setIdEspaco(espacoRepository.findEspacoByName(espaco.getNomeEspaco()));}
        Set<UsuarioModel> convidados = new HashSet<>();
        for (UsuarioModel usu:event.getConvidados()){
            if (usuarioRepository.findUsuarioByEmail(usu.getEmail()) == null) {
                event.getConvidados().remove(usu);
                convidados.add(usu);
            }
            convidados.add(usu);
        }
        eventoRepository.save(event);
        event.setConvidados(convidados);
    }

    public void aprovarEvento(String title){
        try {
            Integer id = eventoRepository.findEventoByTitle(title);
            Optional<EventoModel> evento = eventoRepository.findById(id);
            EventoModel eventoModel = evento.get();
            eventoModel.setStatus(1);
            eventoRepository.save(eventoModel);
        } catch (InvalidDataAccessApiUsageException e){
            throw new ResourceNotFoundException(title);
        }
    }
    public void deletar(String title){
        Integer id = eventoRepository.findEventoByTitle(title);
        try {
            eventoRepository.deleteById(id);
        } catch (InvalidDataAccessApiUsageException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void listarId(Integer id){
        try {
            eventoRepository.findById(id);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
    public List<EventoModel> listar(){ return eventoRepository.findAll(); }
}

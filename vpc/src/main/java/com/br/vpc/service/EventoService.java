package com.br.vpc.service;

import com.br.vpc.model.EspacoModel;
import com.br.vpc.model.EventoModel;
import com.br.vpc.model.OrganizadorModel;
import com.br.vpc.repository.EspacoRepository;
import com.br.vpc.repository.EventoRepository;
import com.br.vpc.service.exceptions.DataBaseException;
import com.br.vpc.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    EspacoRepository espacoRepository;
    private List Id;

    public void cadastrar(EventoModel event) {
        OrganizadorModel org = new OrganizadorModel();
        org.setEmail("teste@gmail.com");
        event.setOrg(org);

        for(EspacoModel espaco:event.getNomeEspaco()){ espaco.setIdEspaco(espacoRepository.findEspacoByName(espaco.getNomeEspaco()));}

        eventoRepository.save(event);
    }

    public void aprovarEvento(String title){
        try {
            Integer id = eventoRepository.findEventoByTitle(title);
            Optional<EventoModel> evento = eventoRepository.findById(id);
            EventoModel eventoModel = evento.get();
            eventoModel.setStatus(1);
            eventoRepository.save(eventoModel);
        } catch (NoSuchElementException e){
            throw new ResourceNotFoundException(title);
        }
    }

    public void deletar(String title){
        Integer id = eventoRepository.findEventoByTitle(title);
        try {
            eventoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public List<EventoModel> listar(){
        return eventoRepository.findAll();
    }

    public void listarId(Integer id){
        try {
            eventoRepository.findById(id);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

}

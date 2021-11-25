package com.br.vpc.service;

import com.br.vpc.model.EspacoModel;
import com.br.vpc.model.EventoModel;
import com.br.vpc.model.UsuarioModel;
import com.br.vpc.repository.EspacoRepository;
import com.br.vpc.repository.EventoRepository;
import com.br.vpc.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.*;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    EspacoRepository espacoRepository;

    @Autowired
    UsuarioService usuarioSerice;

    @Autowired
    EmailService emailService;

    public void cadastrar(EventoModel event) {
        for (EspacoModel espaco : event.getNomeEspaco()) {
            espaco.setIdEspaco(espacoRepository.findEspacoByName(espaco.getNomeEspaco()));
        }
        Set<UsuarioModel> convidadosCadastrados = new HashSet<>();
        Set<UsuarioModel> convidadosNaoCad = new HashSet<>();
        if (event.getConvidados() != null) {
            for (UsuarioModel usu : event.getConvidados()) {
                String email = usuarioSerice.findUsuarioByEmail(usu.getEmail());
                if (email != null) {
                    convidadosCadastrados.add(usu);
                } else {
                    convidadosNaoCad.add(usu);
                }
            }
        }

        event.setConvidados(convidadosCadastrados);
        eventoRepository.save(event);
        event.setConvidados(convidadosNaoCad);
    }

    public void aprovarEvento(Integer id) {
        try {
            EventoModel evento = eventoRepository.findEventoById(id);
            evento.setStatus(1);
            eventoRepository.save(evento);
            emailService.envioEmailEventoAprovado(evento);
        } catch (InvalidDataAccessApiUsageException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void deletar(Integer id, String comentario) {
        try {
            EventoModel evento = eventoRepository.findEventoById(id);
            emailService.envioEmailEventoReprovado(evento, comentario);
            eventoRepository.deleteById(id);
        } catch (InvalidDataAccessApiUsageException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<EventoModel> listar() {
        return eventoRepository.findAll();
    }

    public List<EventoModel> listarAprovados() {
        return eventoRepository.findEventosAprovados();
    }

}

/*package com.br.vpc.service;

import com.br.vpc.model.EventoEspacoID;
import com.br.vpc.model.EventoEspacoModel;
import com.br.vpc.repository.EventoEspacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoEspacoService {
    @Autowired
    EventoEspacoRepository eventoEspacoRepository;
    @Autowired
    EspacoService espacoService;

    public void cadastrar(String[] nomeEspaco, Integer eventoId) {
        EventoEspacoModel eventoEspacoModel = new EventoEspacoModel();
        for(String espaco:nomeEspaco) {
            Integer id = espacoService.findEspacoByName(espaco);
            eventoEspacoModel.setEventoEspacoID(new EventoEspacoID(id, eventoId));
            eventoEspacoRepository.save(eventoEspacoModel);
        }
    }
}
*/
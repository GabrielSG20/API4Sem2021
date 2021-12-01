package com.br.vpc.service;

import com.br.vpc.model.EspacoModel;
import com.br.vpc.repository.EspacoRepository;
import com.br.vpc.service.exceptions.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacoService {
    @Autowired
    EspacoRepository espacoRepository;

    public void atualizarEspaco(Integer cap, Integer cap2){
        try {
            EspacoModel esp1 = espacoRepository.findEspaco("Open Space");
            EspacoModel esp2 = espacoRepository.findEspaco("Lounge on Hall");

            esp1.setCapEspaco(cap);
            esp2.setCapEspaco(cap2);
            espacoRepository.save(esp1);
            espacoRepository.save(esp2);

        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public Integer listarEspaco(String nomeEspaco){
        try{
            EspacoModel esp = espacoRepository.findEspaco(nomeEspaco);
            return esp.getCapEspaco();
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

}

package com.br.vpc.service;

import com.br.vpc.model.OrganizadorModel;
import com.br.vpc.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizadorService {

    @Autowired
    OrganizadorRepository organizadorRepository;

    public void cadastrar(OrganizadorModel organizadorModel) {
        organizadorRepository.save(organizadorModel);
    }

    public List<OrganizadorModel> listar(){ return organizadorRepository.findAll(); }
}

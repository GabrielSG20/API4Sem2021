package com.br.vpc.service;

import com.br.vpc.model.OrganizadorModel;
import com.br.vpc.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService {

    @Autowired
    OrganizadorRepository organizadorRepository;

    public void cadastrar(OrganizadorModel organizadorModel) {
        organizadorRepository.save(organizadorModel);
    }

    public List<OrganizadorModel> listar(){ return organizadorRepository.findAll(); }

    public void aprovarOrg(String email) {
        Optional<OrganizadorModel> org = organizadorRepository.findById(email);
        OrganizadorModel organizadorModel = org.get();
        organizadorModel.setTipoUsuario("org");
        organizadorRepository.save(organizadorModel);
    }

    public void deletar(String id){
        organizadorRepository.deleteById(id);
    }
}

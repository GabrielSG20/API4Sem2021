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

    public void aprovarOrg(OrganizadorModel organizadorModel) {
        Optional<OrganizadorModel> org = organizadorRepository.findById(organizadorModel.getEmail());
        OrganizadorModel usuario = org.get();
        usuario.setCargoUsuario(organizadorModel.getCargoUsuario());
        usuario.setTipoUsuario("org");
        organizadorRepository.save(usuario);
    }

    public void deletar(String id){
        organizadorRepository.deleteById(id);
    }
}

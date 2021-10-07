package com.br.vpc.service;

import com.br.vpc.model.UsuarioModel;
import com.br.vpc.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService {

    @Autowired
    OrganizadorRepository organizadorRepository;

    public void cadastrar(UsuarioModel usuarioModel) {
        organizadorRepository.save(usuarioModel);
    }

    public List<UsuarioModel> listar(){ return organizadorRepository.findAll(); }

    public void aprovarOrg(UsuarioModel usuarioModel) {
        Optional<UsuarioModel> org = organizadorRepository.findById(usuarioModel.getEmail());
        UsuarioModel usuario = org.get();
        usuario.setCargoUsuario(usuarioModel.getCargoUsuario());
        usuario.setTipoUsuario("org");
        organizadorRepository.save(usuario);
    }

    public void deletar(String id){
        organizadorRepository.deleteById(id);
    }
}

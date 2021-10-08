package com.br.vpc.service;

import com.br.vpc.model.UsuarioModel;
import com.br.vpc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void cadastrar(UsuarioModel usuarioModel) {
        usuarioRepository.save(usuarioModel);
    }

    public List<UsuarioModel> listar(){ return usuarioRepository.findAll(); }

    public void aprovarOrg(UsuarioModel usuarioModel) {
        Optional<UsuarioModel> org = usuarioRepository.findById(usuarioModel.getEmail());
        UsuarioModel usuario = org.get();
        usuario.setCargoUsuario(usuarioModel.getCargoUsuario());
        usuario.setTipoUsuario("org");
        usuarioRepository.save(usuario);
    }

    public void deletar(String id){
        usuarioRepository.deleteById(id);
    }
}

package com.br.vpc.service;

import com.br.vpc.model.UsuarioModel;
import com.br.vpc.repository.UsuarioRepository;
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
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void cadastrar(UsuarioModel usuarioModel) {
        if (usuarioRepository.findUsuarioByEmail(usuarioModel.getEmail()) == null){
            try {
                usuarioRepository.save(usuarioModel);
            } catch (DataIntegrityViolationException e){
                throw new DataBaseException(e.getMessage());
            }
        }
    }

    public List<UsuarioModel> listar(){ return usuarioRepository.findAll(); }

    public void aprovarOrg(UsuarioModel usuarioModel) {
        try {
            Optional<UsuarioModel> org = usuarioRepository.findById(usuarioModel.getEmail());
            UsuarioModel usuario = org.get();
            usuario.setCargoUsuario(usuarioModel.getCargoUsuario());
            usuario.setTipoUsuario("org");
            usuarioRepository.save(usuario);
        } catch (NoSuchElementException e){
            throw new ResourceNotFoundException(e);
        }
    }

    public void listarId(String id){
        try {
            usuarioRepository.findById(id);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void deletar(String id){
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }
}

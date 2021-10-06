package com.br.vpc.service;

import com.br.vpc.model.OrganizadorModel;
import com.br.vpc.repository.OrganizadorRepository;
import com.br.vpc.service.exceptions.DataBaseException;
import com.br.vpc.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        try {
            Optional<OrganizadorModel> org = organizadorRepository.findById(organizadorModel.getEmail());
            OrganizadorModel usuario = org.get();
            usuario.setCargoUsuario(organizadorModel.getCargoUsuario());
            usuario.setTipoUsuario("org");
            organizadorRepository.save(usuario);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(organizadorModel);
        }
    }

    public void listarId(String id){
        try {
            organizadorRepository.findById(id);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void deletar(String id){
        try {
            organizadorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }
}

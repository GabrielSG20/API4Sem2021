package com.br.vpc.service;

import com.br.vpc.model.EventoDTO;
import com.br.vpc.model.EventoModel;
import com.br.vpc.model.OrganizadorModel;
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


    public void atualizar(UsuarioModel usuarioModel){
        Optional<UsuarioModel> atual = usuarioRepository.findById(usuarioModel.getEmail());
        UsuarioModel novo = atual.get();
        novo.setNome(usuarioModel.getNome());
        novo.setCpf(usuarioModel.getCpf());
        novo.setTelefone(usuarioModel.getCpf());
        novo.setDepartamento(usuarioModel.getDepartamento());
        novo.setNome_empresa(usuarioModel.getNome_empresa());
        novo.setId_oracle(usuarioModel.getId_oracle());
        novo.setComprovante_vacinacao(usuarioModel.getComprovante_vacinacao());
        novo.setTipo(usuarioModel.getTipo());
        novo.setCargo(usuarioModel.getCargo());

        usuarioRepository.save(novo);
    }


    public void deletar(String email){
        usuarioRepository.deleteById(email);
    }


    public List<UsuarioModel> listar(){ return usuarioRepository.findAll(); }

}

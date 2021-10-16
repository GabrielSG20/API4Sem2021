package com.br.vpc.service;

import com.br.vpc.repository.AutenticaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticaoService implements UserDetailsService {

    @Autowired
    AutenticaoRepository autenticaoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(autenticaoRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Nome de usuario não encontrado"));
    }
}

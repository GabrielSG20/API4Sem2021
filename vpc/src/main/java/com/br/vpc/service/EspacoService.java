package com.br.vpc.service;

import com.br.vpc.repository.EspacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspacoService {
    @Autowired
    EspacoRepository espacoRepository;
}

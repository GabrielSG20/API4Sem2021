package com.br.vpc.repository;

import com.br.vpc.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizadorRepository extends JpaRepository<UsuarioModel, String> {
}

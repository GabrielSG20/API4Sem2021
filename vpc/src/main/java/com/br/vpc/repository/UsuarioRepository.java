package com.br.vpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.br.vpc.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {
    @Query(" select email from UsuarioModel where email = ?1 ")
    String findUsuarioByEmail(String email);
}





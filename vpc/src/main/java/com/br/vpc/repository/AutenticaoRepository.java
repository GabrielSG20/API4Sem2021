package com.br.vpc.repository;

import com.br.vpc.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AutenticaoRepository extends JpaRepository<UsuarioModel, String> {
   @Query(" select email from UsuarioModel where email = ?1 ")
   UsuarioModel findByEmail(String email);
}

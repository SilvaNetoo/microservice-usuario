package com.microservusua.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservusua.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT obj FROM Usuario obj WHERE obj.email = :email AND obj.senha = :senha")
	public Usuario findUser(String email, String senha);
}
package br.com.mjv.petshopAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mjv.petshopAPI.entity.Usuario;

public interface UsuarioRepository   extends JpaRepository<Usuario, Long>{

}

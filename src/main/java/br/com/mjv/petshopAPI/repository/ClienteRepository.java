package br.com.mjv.petshopAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mjv.petshopAPI.entity.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>{

}

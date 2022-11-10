package br.com.mjv.petshopAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mjv.petshopAPI.dto.ClienteDto;
import br.com.mjv.petshopAPI.entity.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>{

	@Query("SELECT c FROM Cliente c")
	Page<ClienteDto> findClientes(Pageable pageable);
}

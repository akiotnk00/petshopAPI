package br.com.mjv.petshopAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.mjv.petshopAPI.dto.ClienteDto;
import br.com.mjv.petshopAPI.entity.Cliente;

public interface ClienteRepository  extends CrudRepository<Cliente, Long>{

	@Query("SELECT c FROM Cliente c")
	Page<ClienteDto> findClientes(Pageable pageable);
}

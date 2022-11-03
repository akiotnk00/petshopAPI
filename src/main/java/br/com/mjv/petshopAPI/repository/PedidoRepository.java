package br.com.mjv.petshopAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mjv.petshopAPI.entity.Pedido;

public interface PedidoRepository   extends JpaRepository<Pedido, Long>{

	@Query("SELECT p FROM Pedido p")
	Page<Pedido> findPedidos(Pageable pageable);
	
	
}

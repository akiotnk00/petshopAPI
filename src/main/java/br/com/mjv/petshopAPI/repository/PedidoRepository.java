package br.com.mjv.petshopAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mjv.petshopAPI.dto.PedidoDto;
import br.com.mjv.petshopAPI.entity.Pedido;

public interface PedidoRepository   extends JpaRepository<Pedido, Long>{

	@Query("SELECT p FROM Pedido p")
	Page<PedidoDto> findPedidos(Pageable pageable);

	@Query(
			  value = "SELECT * FROM Pedido p WHERE p.status = 'ENTREGA'", 
			  nativeQuery = true)
	Page<PedidoDto> findPedidosEntrega(Pageable pageable);
	
	
}

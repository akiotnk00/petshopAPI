package br.com.mjv.petshopAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mjv.petshopAPI.entity.Produto;

public interface ProdutoRepository   extends JpaRepository<Produto, Long>{

	@Query("SELECT p FROM Produto p")
	Page<Produto> findProdutos(Pageable pageable);
}

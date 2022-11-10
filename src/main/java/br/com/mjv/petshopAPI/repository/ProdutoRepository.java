package br.com.mjv.petshopAPI.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mjv.petshopAPI.dto.ProdutoDto;
import br.com.mjv.petshopAPI.entity.Produto;

public interface ProdutoRepository   extends JpaRepository<Produto, Long>{

	@Query("SELECT p FROM Produto p")
	Page<ProdutoDto> findProdutos(Pageable pageable);
	
	List<Produto> findByNome(String nome);
	
	List<Produto> findByCodigo(Long codigo);
	
	List<Produto> findByCategoriaNome(String nome);
	
	List<Produto> findByCategoriaCodigo(Long codigo);
}

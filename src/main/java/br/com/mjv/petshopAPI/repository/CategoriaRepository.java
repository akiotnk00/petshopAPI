package br.com.mjv.petshopAPI.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mjv.petshopAPI.entity.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {

	@Query("SELECT c FROM Categoria c")
	Page<Categoria> findCategorias(Pageable pageable);
	
	List<Categoria> findByNome(String nome);
	
	List<Categoria> findByCodigo(Long codigo);
}

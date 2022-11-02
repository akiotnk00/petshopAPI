package br.com.mjv.petshopAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mjv.petshopAPI.entity.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {

}

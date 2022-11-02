package br.com.mjv.petshopAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mjv.petshopAPI.entity.Venda;

public interface VendaRepository   extends JpaRepository<Venda, Long>{

}

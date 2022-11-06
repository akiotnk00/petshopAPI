package br.com.mjv.petshopAPI.services;

import java.util.Optional;

import br.com.mjv.petshopAPI.entity.Produto;

public class ValidarProduto {
	public static void executa(Optional<Produto> produtoBuscado) throws Exception {
		if (produtoBuscado.isPresent()) {
			throw new Exception("Não foi possivel localizar o produto.");
		}
	}
}

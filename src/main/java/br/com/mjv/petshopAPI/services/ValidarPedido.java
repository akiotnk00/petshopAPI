package br.com.mjv.petshopAPI.services;

import java.util.Optional;

import br.com.mjv.petshopAPI.entity.Pedido;

public class ValidarPedido {
	public static void executa(Optional<Pedido> pedidoBuscado) throws Exception {
		if (pedidoBuscado.isPresent()) {
			throw new Exception("Não foi possivel localizar o pedido.");
		}
	}
}

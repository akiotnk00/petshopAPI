package br.com.mjv.petshopAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mjv.petshopAPI.services.NotificacaoService;

@RestController
@RequestMapping(value = "/notificacao")
public class NotificacaoController {

	@Autowired
	private NotificacaoService notificacaoService;
	
	@PatchMapping("/{id}/pedido")
	public void enviarSMS(@PathVariable Long id) throws Exception {
		notificacaoService.enviarSMS(id);
	}
}

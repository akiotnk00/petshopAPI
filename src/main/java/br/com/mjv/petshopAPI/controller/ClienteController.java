package br.com.mjv.petshopAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mjv.petshopAPI.entity.Cliente;
import br.com.mjv.petshopAPI.services.ClienteService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	// Busca todos os clientes.
	@GetMapping
	public Page<Cliente> buscarProdutos(Pageable pageable) {
		return clienteService.findClientes(pageable);
	}

	// Cadastra um novo cliente.
	@PostMapping
	public String cadastrarCliente(@RequestBody Cliente cliente) throws Exception {
		return clienteService.cadastrarCliente(cliente);
	}

	// Deleta um cliente por ID.
	@DeleteMapping("/{codigo}")
	public String deletarCliente(@PathVariable long codigo) throws Exception {
		return clienteService.deletarCliente(codigo);
	}
	
	// Cadastra um endereço novo.
	@PutMapping("/{codigo}/endereco")
	public String cadastrarEndereco(@PathVariable Long codigo ,@RequestParam String estado,@RequestParam String cidade,@RequestParam String cep,@RequestParam String bairro,@RequestParam String logradouro,@RequestParam Integer numero) throws Exception {
		return clienteService.cadastrarEndereco(codigo,estado,cidade,bairro,cep,logradouro,numero);
		
	}
	

}

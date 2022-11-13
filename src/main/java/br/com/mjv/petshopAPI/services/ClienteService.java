package br.com.mjv.petshopAPI.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mjv.petshopAPI.dto.ClienteDto;
import br.com.mjv.petshopAPI.entity.Cliente;
import br.com.mjv.petshopAPI.entity.Endereco;
import br.com.mjv.petshopAPI.entity.Pedido;
import br.com.mjv.petshopAPI.repository.ClienteRepository;
import br.com.mjv.petshopAPI.repository.PedidoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	public Page<ClienteDto> findClientes(Pageable pageable) {
		return clienteRepository.findClientes(pageable);
	}
	
	public Cliente cadastrarCliente(Cliente cliente) {
			
		
		Pedido pedido = new Pedido(cliente);	
		
		clienteRepository.save(cliente);
		
		pedido.setCliente(cliente);	

		pedidoRepository.save(pedido);
		
		return cliente;
	}

	public String cadastrarEndereco(Long codigo, String estado, String cidade, String bairro, String cep,
			String logradouro, Integer numero) {
		
		Optional<Cliente> clienteBuscado = clienteRepository.findById(codigo);
		
		Endereco endereco = new Endereco();
		endereco.setEstado(estado);
		endereco.setCidade(cidade);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setCliente(clienteBuscado.get());
		
		clienteBuscado.get().setEndereco(endereco);
		
		clienteRepository.save(clienteBuscado.get());
		
		
		return "Endereço cadastrado com sucesso!";
	}

	public String deletarCliente(long codigo) throws Exception {
		
		Optional<Cliente> clienteBuscado = clienteRepository.findById(codigo);
		
		if(!clienteBuscado.isPresent()) {
			throw new Exception("Não foi possivel localizar o cliente.");
		}
		clienteRepository.delete(clienteBuscado.get());
		
		return "Cliente deletado com sucesso!";
	}
}

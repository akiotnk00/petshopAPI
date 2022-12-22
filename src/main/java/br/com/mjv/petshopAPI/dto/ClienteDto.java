package br.com.mjv.petshopAPI.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mjv.petshopAPI.entity.Cliente;
import lombok.Data;

@Data
public class ClienteDto {
	private Long codigo;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private LocalDate datanascimento;
	
	public ClienteDto() {
		super();
	}
	
	public ClienteDto(Cliente cliente) {
        this.codigo = cliente.getCodigo();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
		this.datanascimento = cliente.getDatanascimento();
	}
	public static List<ClienteDto> converter(List<Cliente> clientes){
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}
	
   
}

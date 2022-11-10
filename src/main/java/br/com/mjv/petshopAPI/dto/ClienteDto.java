package br.com.mjv.petshopAPI.dto;

import java.time.LocalDate;

import br.com.mjv.petshopAPI.entity.Cliente;

public class ClienteDto {
	private Long codigo;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private LocalDate datanascimento;
	
	public ClienteDto(Cliente cliente) {
		this.codigo = cliente.getCodigo();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
		this.datanascimento = cliente.getDatanascimento();
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDatanascimento() {
		return datanascimento;
	}
	public void setDatanascimento(LocalDate datanascimento) {
		this.datanascimento = datanascimento;
	}
	
   
}

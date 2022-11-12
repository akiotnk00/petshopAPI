package br.com.mjv.petshopAPI.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.mjv.petshopAPI.entity.Cliente;

public class ClienteForm {
	
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty @Length(min = 11)
	private String cpf;
	
	@NotNull @NotEmpty
	private String telefone;
	
	@NotNull @NotEmpty
	private String email;
	
	@NotNull
	private LocalDate datanascimento;

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

	public Cliente converter() {
		return new Cliente(nome, cpf, telefone, email, datanascimento);
	}

}

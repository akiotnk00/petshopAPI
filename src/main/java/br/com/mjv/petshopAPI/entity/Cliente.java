package br.com.mjv.petshopAPI.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.mjv.petshopAPI.dto.ClienteDto;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private LocalDate datanascimento;
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonBackReference
	private Endereco endereco;
	
	public Cliente() {
		
	}
	
	public Cliente(ClienteDto clienteDto) {
		this.codigo = clienteDto.getCodigo();
		this.nome = clienteDto.getNome();
		this.cpf = clienteDto.getCpf();
		this.telefone = clienteDto.getTelefone();
		this.email = clienteDto.getEmail();
		this.datanascimento = clienteDto.getDatanascimento();
	}
	

	public Cliente(String nome, String cpf, String telefone, String email, LocalDate datanascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.datanascimento = datanascimento;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
	
}

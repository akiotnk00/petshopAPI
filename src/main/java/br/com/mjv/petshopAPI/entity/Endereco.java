package br.com.mjv.petshopAPI.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	private String endereco;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}

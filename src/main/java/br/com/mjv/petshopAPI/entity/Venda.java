package br.com.mjv.petshopAPI.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private Date data;
	
	@Enumerated(EnumType.STRING)
	private StatusVenda status;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "venda")
	private List<ItemVenda> itemvenda;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemVenda> getItemvenda() {
		return itemvenda;
	}

	public void setItemvenda(List<ItemVenda> itemvenda) {
		this.itemvenda = itemvenda;
	}



}

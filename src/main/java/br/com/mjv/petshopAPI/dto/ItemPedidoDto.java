package br.com.mjv.petshopAPI.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mjv.petshopAPI.entity.ItemPedido;

public class ItemPedidoDto {
	private Long codigo;
	private Long IdProduto;
	private Integer quantidade;
	private BigDecimal valor;
		
	public ItemPedidoDto() {
		super();
	}
	public ItemPedidoDto(ItemPedido itemPedido) {
		this.codigo = itemPedido.getCodigo();
		IdProduto = itemPedido.getProduto().getCodigo();
		this.quantidade = itemPedido.getQuantidade();
		this.valor = itemPedido.getValor();
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Long getIdProduto() {
		return IdProduto;
	}
	public void setIdProduto(Long idProduto) {
		IdProduto = idProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public static List<ItemPedidoDto> converter(List<ItemPedido> itensPedido){
		return itensPedido.stream().map(ItemPedidoDto::new).collect(Collectors.toList());
	}
	
}

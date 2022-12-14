package br.com.mjv.petshopAPI.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mjv.petshopAPI.entity.Pedido;
import br.com.mjv.petshopAPI.entity.StatusPedido;

public class PedidoDto {
	private Long codigo;
	private LocalDate data;
	private StatusPedido status;
	
	
	
	public PedidoDto() {
		super();
	}
	public PedidoDto(Pedido pedido) {
		this.codigo = pedido.getCodigo();
		this.data = pedido.getData();
		this.status = pedido.getStatus();
				
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public static List<PedidoDto> converter(List<Pedido> pedidos){
		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
	}
}

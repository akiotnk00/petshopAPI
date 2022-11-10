package br.com.mjv.petshopAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mjv.petshopAPI.dto.ItemPedidoDto;
import br.com.mjv.petshopAPI.dto.PedidoDto;
import br.com.mjv.petshopAPI.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	// Busca todos os pedidos.
	@GetMapping
	public Page<PedidoDto> buscarPedidos(Pageable pageable) {
		return pedidoService.findPedidos(pageable);
	}

	// Deleta um pedido por ID.
	@DeleteMapping("/{codigo}")
	public String deletarPedido(@PathVariable long codigo) throws Exception {
		return pedidoService.deletarPedido(codigo);
	}
	
	// Adiciona um produto ao carrinho.
	@PostMapping("/{codigoPedido}/produto/{codigoProduto}/quantidade/{quantidade}")
	public String adicionarProdutoCarrinho(@PathVariable Long codigoPedido, @PathVariable Long codigoProduto, @PathVariable int quantidade)
			throws Exception {
		return pedidoService.adicionarProduto(codigoPedido, codigoProduto, quantidade);
	}
	
	// Remove um produto do carrinho
	@DeleteMapping("{codigoPedido}/item/{codigoItemPedido}")
	public String removerProdutoCarrinho(@PathVariable Long codigoPedido, @PathVariable Long codigoItemPedido)
			throws Exception {

		return pedidoService.removerProduto(codigoPedido, codigoItemPedido);
	}
	
	// Finaliza o carrinho e confirma o pedido.
	@PatchMapping("/{codigo}/confirmar")
	public String confirmarPedido(@PathVariable Long codigo) throws Exception {
		return pedidoService.confirmaPedido(codigo);
	}
	
	// Volta o pedido para o Status "Carrinho".
	@PatchMapping("/{codigo}/voltar")
	public String voltarPedido(@PathVariable Long codigo) throws Exception {
		return pedidoService.voltaCarrinhoPedido(codigo);
	}

	// Confirma o mesmo endereco do cliente.
	@PatchMapping("/{codigo}/endereco")
	public  String enderecoClientePedido(@PathVariable Long codigo) throws Exception{
		return pedidoService.enderecoClientePedido(codigo);
	}
	
	// Caso o cliente queira usar outro endereço para entrega.
	@PostMapping("/{codigo}/novoendereco")
	public String cadastrarEnderecoNovo(@PathVariable Long codigo ,@RequestParam String estado,@RequestParam String cidade,@RequestParam String cep,@RequestParam String bairro,@RequestParam String logradouro,@RequestParam Integer numero) throws Exception {
		return pedidoService.cadastrarEnderecoNovo(codigo,estado,cidade,bairro,cep,logradouro,numero);
		
	}
	
	// Confirma o pagamento de um pedido pelo ID.
	@PatchMapping("/{codigo}/pagamento")
	public String confirmarPagamento(@PathVariable Long codigo) throws Exception {
		return pedidoService.confirmaPagamento(codigo);
	}
	
	// Retorna um resumo do pedido por ID.
	@GetMapping("/{codigo}/resumo")
	public String resumoPedido(@PathVariable Long codigo) throws Exception {
		return pedidoService.resumoPedido(codigo);
	}
	
	// Retorna todos os itens do pedido por ID.
	@GetMapping("/{codigo}/itens")
	public List<ItemPedidoDto> itensPedido(@PathVariable Long codigo) throws Exception {
		return ItemPedidoDto.converter(pedidoService.itensPedido(codigo));
	}
	
	// Retorna todos os pedidos com status ENTREGA.
	@GetMapping("{codigo}/entrega")
	public Page<PedidoDto> buscarPedidosEntrega(Pageable pageable) {
		return pedidoService.findPedidosEntrega(pageable);
	}
}

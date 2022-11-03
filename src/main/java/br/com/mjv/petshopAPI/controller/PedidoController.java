package br.com.mjv.petshopAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.mjv.petshopAPI.entity.Pedido;
import br.com.mjv.petshopAPI.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public Page<Pedido> findPedidos(Pageable pageable) {
		return pedidoService.findPedidos(pageable);
	}

	// Adiciona um produto ao carrinho.
	@PatchMapping("/codigo/{codigo}/produto/{codigoProduto}/quantidade/{quantidade}")
	public String adicionaProdutoCarrinho(@PathVariable Long codigo, @PathVariable Long codigoProduto, @PathVariable int quantidade)
			throws Exception {

		return pedidoService.adicionarProduto(codigo, codigoProduto, quantidade);
	}
	
	// Finaliza o carrinho e confirma o pedido.
	@PatchMapping("/codigo/{codigo}/confirmar")
	public String confirmaPedido(@PathVariable Long codigo) throws Exception {
		return pedidoService.confirmaPedido(codigo);
	}
	
	// Volta o pedido para o Status "Carrinho".
	@PatchMapping("/codigo/{codigo}/voltar")
	public String voltarPedido(@PathVariable Long codigo) throws Exception {
		return pedidoService.voltaCarrinhoPedido(codigo);
	}

	// Confirma o mesmo endereco do cliente.
	@PatchMapping("/codigo/{codigo}/endereco")
	public  String enderecoClientePedido(@PathVariable Long codigo) throws Exception{
		return pedidoService.enderecoClientePedido(codigo);
	}
	
	@PutMapping("/{codigo}/novoendereco")
	public String cadastrarEnderecoNovo(@PathVariable Long codigo ,@RequestParam String estado,@RequestParam String cidade,@RequestParam String cep,@RequestParam String bairro,@RequestParam String logradouro,@RequestParam Integer numero) throws Exception {
		return pedidoService.cadastrarEnderecoNovo(codigo,estado,cidade,bairro,cep,logradouro,numero);
		
	}
	
	@PatchMapping("/{codigo}/pagamento")
	public String confirmaPagamento(@PathVariable Long codigo) throws Exception {
		return pedidoService.confirmaPagamento(codigo);
	}
		
	
}

package br.com.mjv.petshopAPI.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mjv.petshopAPI.entity.EnderecoEntrega;
import br.com.mjv.petshopAPI.entity.ItemPedido;
import br.com.mjv.petshopAPI.entity.Pedido;
import br.com.mjv.petshopAPI.entity.Produto;
import br.com.mjv.petshopAPI.entity.StatusPedido;
import br.com.mjv.petshopAPI.repository.ItemPedidoRepository;
import br.com.mjv.petshopAPI.repository.PedidoRepository;
import br.com.mjv.petshopAPI.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;

	public Page<Pedido> findPedidos(Pageable pageable) {
		return pedidoRepository.findPedidos(pageable);
	}

	// Adiciona um produto em um pedido.
	public String adicionarProduto(long idProduto, long idPedido, int quantidade) throws Exception {

		Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(idPedido);

		if (!produtoBuscado.isPresent()) {
			throw new Exception("Não foi possivel localizar o produto.");
		}
		if (!pedidoBuscado.isPresent()) {
			throw new Exception("Não foi possivel localizar o pedido.");
		}

		if (pedidoBuscado.get().getStatus() != StatusPedido.CARRINHO) {
			return "Carrinho fechado, abra novamente para adicionar mais itens";
		}

		produtoService.removeEstoqueProduto(idProduto, quantidade);

		ItemPedido itemPedido = new ItemPedido(pedidoBuscado.get(), produtoBuscado.get(), quantidade);

		// Salva o valor do produto no pedido.
		// O produto pode sofrer alteração no valor ao longo do tempo,
		// mas no historico do pedido ficara o valor anterior.
		itemPedido.setValor(produtoBuscado.get().getValor());

		itemPedidoRepository.save(itemPedido);

		return "Produto adicionado ao carrinho!";
	}

	// Finaliza o pedido e altera o STATUS para "ENDERECO".
	public String confirmaPedido(long idPedido) throws Exception {

		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(idPedido);

		if (pedidoBuscado.get().getStatus() == StatusPedido.CARRINHO) {
			pedidoBuscado.get().setStatus(StatusPedido.ENDERECO);
			pedidoRepository.save(pedidoBuscado.get());
		} else {
			throw new Exception("Carrinho já finalizado.");
		}

		return "Carrinho finalizado!";
	}

	// Caso o cliente queira voltar e continuar a adicionar itens ao pedido.
	public String voltaCarrinhoPedido(long idPedido) throws Exception {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(idPedido);

		if (!pedidoBuscado.isPresent()) {
			throw new Exception("Não foi possivel localizar o pedido.");
		}
		if (pedidoBuscado.get().getStatus() == StatusPedido.CARRINHO) {
			throw new Exception("O carrinho ainda não foi finalizado.");
		} else {
			pedidoBuscado.get().setStatus(StatusPedido.CARRINHO);
			pedidoRepository.save(pedidoBuscado.get());
		}

		return "Carrinho aberto novamente!";
	}

	// Caso o cliente queira usar o endereço cadastrado como entrega do pedido.
	public String enderecoClientePedido(Long codigo) throws Exception {

		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(codigo);

		if (!pedidoBuscado.isPresent()) {
			throw new Exception("Não foi possivel localizar o pedido.");
		}

		if (pedidoBuscado.get().getCliente().getEndereco().equals(null)) {
			throw new Exception("O cliente não possui endereço cadastrado!");
		}

		EnderecoEntrega endereco = new EnderecoEntrega();
		endereco.setEstado(pedidoBuscado.get().getCliente().getEndereco().getEstado());
		endereco.setCidade(pedidoBuscado.get().getCliente().getEndereco().getCidade());
		endereco.setBairro(pedidoBuscado.get().getCliente().getEndereco().getBairro());
		endereco.setCep(pedidoBuscado.get().getCliente().getEndereco().getCep());
		endereco.setLogradouro(pedidoBuscado.get().getCliente().getEndereco().getLogradouro());
		endereco.setNumero(pedidoBuscado.get().getCliente().getEndereco().getNumero());
		endereco.setPedido(pedidoBuscado.get());

		pedidoBuscado.get().setEnderecoEntrega(endereco);
		pedidoBuscado.get().setStatus(StatusPedido.PAGAMENTO);

		pedidoRepository.save(pedidoBuscado.get());

		return "Endereço de entrega confirmado!";
	}

	public String cadastrarEnderecoNovo(Long codigo, String estado, String cidade, String bairro, String cep,
			String logradouro, Integer numero) throws Exception {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(codigo);

		if (!pedidoBuscado.isPresent()) {
			throw new Exception("Não foi possivel localizar o pedido.");
		}

		EnderecoEntrega endereco = new EnderecoEntrega();
		endereco.setEstado(estado);
		endereco.setCidade(cidade);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setPedido(pedidoBuscado.get());

		pedidoBuscado.get().setEnderecoEntrega(endereco);
		pedidoBuscado.get().setStatus(StatusPedido.PAGAMENTO);

		pedidoRepository.save(pedidoBuscado.get());

		return "Endereço da entrega cadastrado com sucesso!";
	}

	// Confirma o pagamento e alterada o status do pedido para "ENTREGA"
	public String confirmaPagamento(Long codigo) throws Exception {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(codigo);
		if (!pedidoBuscado.isPresent()) {
			throw new Exception("Não foi possivel localizar o pedido.");
		}

		if(!pedidoBuscado.get().getStatus().equals(StatusPedido.ENTREGA)) {
		pedidoBuscado.get().setStatus(StatusPedido.ENTREGA);
		pedidoBuscado.get().setData(LocalDate.now());
		}
		else
		{
			throw new Exception("O pagamento do pedido já foi confirmado!");
		}
		
		pedidoRepository.save(pedidoBuscado.get());
		
		return "Pagamento confirmado!";
	}

}

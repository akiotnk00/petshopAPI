package br.com.mjv.petshopAPI.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mjv.petshopAPI.dto.PedidoDto;
import br.com.mjv.petshopAPI.entity.EmailDetails;
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

	@Autowired
	private EmailService emailService;

	public Page<PedidoDto> findPedidos(Pageable pageable) {
		return pedidoRepository.findPedidos(pageable);
	}

	// Deleta um pedido recebendo o ID como parametro.
	public String deletarPedido(long codigo) throws Exception {

		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(codigo);

		ValidarPedido.executa(pedidoBuscado);
		
		pedidoRepository.delete(pedidoBuscado.get());

		return "Pedido deletada com sucesso!";
	}

	// Adiciona um produto em um pedido.
	public String adicionarProduto(long idPedido, long idProduto, int quantidade) throws Exception {

		Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(idPedido);

		ValidarProduto.executa(produtoBuscado);	
		ValidarPedido.executa(pedidoBuscado);

		if (pedidoBuscado.get().getStatus() != StatusPedido.CARRINHO) {
			return "Carrinho fechado, abra novamente para adicionar mais itens";
		}

		produtoService.removeEstoqueProduto(idProduto, quantidade);

		ItemPedido itemPedido = new ItemPedido(pedidoBuscado.get(), produtoBuscado.get(), quantidade);

		// Salva o valor do produto no pedido.
		// O produto pode sofrer altera????o no valor ao longo do tempo,
		// mas no historico do pedido ficara o valor anterior.
		itemPedido.setValor(produtoBuscado.get().getValor());

		itemPedidoRepository.save(itemPedido);

		return "Produto adicionado ao carrinho!";
	}

	// Finaliza o pedido e altera o STATUS para "ENDERECO".
	public String confirmaPedido(long idPedido) throws Exception {

		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(idPedido);

		ValidarPedido.executa(pedidoBuscado);
		
		if (pedidoBuscado.get().getStatus() == StatusPedido.CARRINHO) {
			pedidoBuscado.get().setStatus(StatusPedido.ENDERECO);
			pedidoRepository.save(pedidoBuscado.get());
		} else {
			throw new Exception("Carrinho j?? finalizado.");
		}

		EmailDetails details = new EmailDetails();
		details.setRecipient(pedidoBuscado.get().getCliente().getEmail());
		details.setSubject("SUA COMPRA NA MJV PETSHOP FOI CONFIRMADA!");
		details.setMsgBody(
				"Ol?? " + pedidoBuscado.get().getCliente().getNome() + ", sua compra foi confirmada com a gente!"
						+ System.lineSeparator() + emailPedido(pedidoBuscado.get()));

		emailService.sendSimpleMail(details);

		Pedido pedidonovo = new Pedido(pedidoBuscado.get().getCliente());

		pedidoRepository.save(pedidonovo);

		return "Carrinho finalizado!";
	}

	// Monta o cabe??alho do email com os pedidos
	public StringBuilder emailPedido(Pedido pedido) {

		StringBuilder msg = new StringBuilder();

		for (ItemPedido item : pedido.getItemPedido()) {
			msg.append(System.lineSeparator() + "- Produto: " + item.getProduto().getNome() + " | Quantidade: "
					+ item.getQuantidade() + " | Valor Unit??rio: R$" + item.getValor().doubleValue() / 100);
		}

		msg.append(System.lineSeparator() + System.lineSeparator() + "Total da compra: R$" + valorTotalPedido(pedido));

		return msg;
	}

	// Caso o cliente queira voltar e continuar a adicionar itens ao pedido.
	public String voltaCarrinhoPedido(long idPedido) throws Exception {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(idPedido);

		ValidarPedido.executa(pedidoBuscado);
		
		if (pedidoBuscado.get().getStatus() == StatusPedido.CARRINHO) {
			throw new Exception("O carrinho ainda n??o foi finalizado.");
		} else {
			pedidoBuscado.get().setStatus(StatusPedido.CARRINHO);
			pedidoRepository.save(pedidoBuscado.get());
		}

		return "Carrinho aberto novamente!";
	}

	// Caso o cliente queira usar o endere??o cadastrado como entrega do pedido.
	public String enderecoClientePedido(Long codigo) throws Exception {

		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(codigo);

		ValidarPedido.executa(pedidoBuscado);

		if (pedidoBuscado.get().getCliente().getEndereco().equals(null)) {
			throw new Exception("O cliente n??o possui endere??o cadastrado!");
		}

		EnderecoEntrega endereco = new EnderecoEntrega();
		endereco.setEstado(pedidoBuscado.get().getCliente().getEndereco().getEstado());
		endereco.setCidade(pedidoBuscado.get().getCliente().getEndereco().getCidade());
		endereco.setBairro(pedidoBuscado.get().getCliente().getEndereco().getBairro());
		endereco.setCep(pedidoBuscado.get().getCliente().getEndereco().getCep());
		endereco.setLogradouro(pedidoBuscado.get().getCliente().getEndereco().getLogradouro());
		endereco.setNumero(pedidoBuscado.get().getCliente().getEndereco().getNumero());

		pedidoBuscado.get().setEnderecoEntrega(endereco);
		pedidoBuscado.get().setStatus(StatusPedido.PAGAMENTO);

		pedidoRepository.save(pedidoBuscado.get());

		return "Endere??o de entrega confirmado!";
	}

	public String cadastrarEnderecoNovo(Long codigo, String estado, String cidade, String bairro, String cep,
			String logradouro, Integer numero) throws Exception {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(codigo);

		ValidarPedido.executa(pedidoBuscado);

		EnderecoEntrega endereco = new EnderecoEntrega();
		endereco.setEstado(estado);
		endereco.setCidade(cidade);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);

		pedidoBuscado.get().setEnderecoEntrega(endereco);
		pedidoBuscado.get().setStatus(StatusPedido.PAGAMENTO);

		pedidoRepository.save(pedidoBuscado.get());

		return "Endere??o da entrega cadastrado com sucesso!";
	}

	// Confirma o pagamento e alterada o status do pedido para "ENTREGA"
	public String confirmaPagamento(Long codigo) throws Exception {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(codigo);
		
		ValidarPedido.executa(pedidoBuscado);

		if (!pedidoBuscado.get().getStatus().equals(StatusPedido.ENTREGA)) {
			pedidoBuscado.get().setStatus(StatusPedido.ENTREGA);
			pedidoBuscado.get().setData(LocalDate.now());
		} else {
			throw new Exception("O pagamento do pedido j?? foi confirmado!");
		}

		pedidoRepository.save(pedidoBuscado.get());

		EmailDetails details = new EmailDetails();

		details.setSubject("O PAGAMENTO DA SUA COMPRA FOI CONFIRMADO!");
		details.setMsgBody("O pagamento da sua compra foi confirmado, em breve ele ser?? enviado.");
		details.setRecipient(pedidoBuscado.get().getCliente().getEmail());

		emailService.sendSimpleMail(details);

		return "Pagamento confirmado!";
	}

	// Resumo com retorno de mensagem, codigo da compra e valor total.
	public String resumoPedido(Long codigo) throws Exception {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(codigo);
		
		ValidarPedido.executa(pedidoBuscado);
		
		BigDecimal total = BigDecimal.ZERO;

		for (ItemPedido itemPedido : pedidoBuscado.get().getItemPedido()) {
			Integer i = 0;
			while (i < itemPedido.getQuantidade()) {
				total = total.add(itemPedido.getValor());
				i++;
			}
		}

		return "O valor total ?? de R$" + total.doubleValue() / 100 + ".";
	}

	// Calcula o valor total do pedido.
	public double valorTotalPedido(Pedido pedido) {
		BigDecimal total = BigDecimal.ZERO;

		for (ItemPedido itemPedido : pedido.getItemPedido()) {
			Integer i = 0;
			while (i < itemPedido.getQuantidade()) {
				total = total.add(itemPedido.getValor());
				i++;
			}
		}

		return total.doubleValue() / 100;
	}

	public String confirmaSMSPedido(Long codigo) throws Exception {

		return "SUA COMPRA NA MJV PETSHOP FOI CONFIRMADA!, " + resumoPedido(codigo);
	}

	public Page<PedidoDto> findPedidosEntrega(Pageable pageable) {
		return pedidoRepository.findPedidosEntrega(pageable);
	}

	public String removerProduto(Long codigoPedido, Long codigoItemPedido) throws Exception {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(codigoPedido);
		Optional<ItemPedido> itemPedidoBuscado = itemPedidoRepository.findById(codigoItemPedido);

		if (!itemPedidoBuscado.isPresent()) {
			throw new Exception("N??o foi possivel localizar o item no pedido.");
		}
		
		ValidarPedido.executa(pedidoBuscado);

		if (pedidoBuscado.get().getStatus() != StatusPedido.CARRINHO) {
			return "Carrinho fechado, abra novamente para remover os itens";
		}

		produtoService.adicionaEstoqueProduto(itemPedidoBuscado.get().getProduto().getCodigo(),
				itemPedidoBuscado.get().getQuantidade());

		itemPedidoRepository.delete(itemPedidoBuscado.get());

		return "Produto removido do carrinho!";

	}

	// Retorna todos os itens do pedido.
	public List<ItemPedido> itensPedido(Long codigo) throws Exception {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(codigo);
		
		ValidarPedido.executa(pedidoBuscado);

		return pedidoBuscado.get().getItemPedido();
	}
}

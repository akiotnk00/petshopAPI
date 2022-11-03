package br.com.mjv.petshopAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mjv.petshopAPI.repository.ProdutoRepository;
import br.com.mjv.petshopAPI.entity.Produto;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Page<Produto> findProdutos(Pageable pageable) {
		return produtoRepository.findProdutos(pageable);
	}

	public List<Produto> findProdutoByNome(String nome){
		return produtoRepository.findByNome(nome);
	}
	
	public List<Produto> findProdutoByCodigo(Long codigo){
		return produtoRepository.findByCodigo(codigo);
	}
	
	public List<Produto> findProdutoByCategoriaNome(String nome){
		return produtoRepository.findByCategoriaNome(nome);
	}
	
	public List<Produto> findProdutoByCategoriaCodigo(Long codigo){
		return produtoRepository.findByCategoriaCodigo(codigo);
	}
	
	public String cadastrarProduto(Produto produto) throws Exception {
		if(produto.equals(null)) {
			throw new Exception("Produto invalido");
		}
		else
		{
			produtoRepository.save(produto);
		}
		return "Produto cadastrado com sucesso!";
	}
	
	public void removeEstoqueProduto(long idProduto, int quantidade) throws Exception {
		Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);

		if (!produtoBuscado.isPresent()) {
			throw new Exception("Produto não localizado.");
		}

		if (produtoBuscado.get().getQuantidade() >= quantidade) {
			produtoBuscado.get().setQuantidade(produtoBuscado.get().getQuantidade() - quantidade);

			produtoRepository.save(produtoBuscado.get());
		} else {
			throw new Exception("Quantidade não disponivel.");
		}

	}

	public void adicionaEstoqueProduto(long idProduto, int quantidade) throws Exception {
		Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);

		if (!produtoBuscado.isPresent()) {
			throw new Exception("Produto não localizado.");
		}

		produtoBuscado.get().setQuantidade(produtoBuscado.get().getQuantidade() + quantidade);
		produtoRepository.save(produtoBuscado.get());
	}

}

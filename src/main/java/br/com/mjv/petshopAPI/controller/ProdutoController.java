package br.com.mjv.petshopAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mjv.petshopAPI.dto.ProdutoDto;
import br.com.mjv.petshopAPI.services.ProdutoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	
	// Busca todas os produtos.
	@GetMapping
	public Page<ProdutoDto> buscarProdutos(Pageable pageable) {
		return produtoService.findProdutos(pageable);
	}

	// Cadastra um novo produto.
	@PostMapping
	public String cadastrarProduto(@RequestBody ProdutoDto produtoDto) throws Exception {
		return produtoService.cadastrarProduto(produtoDto);
	}
	
	// Deleta um produto por ID.
	@DeleteMapping("/{codigo}")
	public String deletarProduto(@PathVariable long codigo) throws Exception {
		return produtoService.deletarProduto(codigo);
	}

	// Busca um produto por ID.
	@GetMapping("/{codigo}")
	public List<ProdutoDto> buscarProdutosPorCodigo(@PathVariable Long codigo) {

		return produtoService.findProdutoByCodigo(codigo);
	}
	


	
}

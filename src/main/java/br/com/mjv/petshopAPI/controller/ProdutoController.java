package br.com.mjv.petshopAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mjv.petshopAPI.services.ProdutoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import br.com.mjv.petshopAPI.entity.Produto;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public Page<Produto> findProdutos(Pageable pageable) {
		return produtoService.findProdutos(pageable);
	}

	@GetMapping("/nome/{nome}")
	public List<Produto> findAllByNomeProdutos(@PathVariable String nome) {

		return produtoService.findProdutoByNome(nome);
	}

	@GetMapping("/codigo/{codigo}")
	public List<Produto> findAllByCodigoProdutos(@PathVariable Long codigo) {

		return produtoService.findProdutoByCodigo(codigo);
	}
	
	@GetMapping("/categoria/nome/{nome}")
	public List<Produto> findAllByCategoriaNome(@PathVariable String nome){
		return produtoService.findProdutoByCategoriaNome(nome);
	}
	
	@GetMapping("/categoria/codigo/{codigo}")
	public List<Produto> findAllByCategoriaCodigo(@PathVariable Long codigo){
		return produtoService.findProdutoByCategoriaCodigo(codigo);
	}

	@PostMapping
	public String cadastrarProduto(@RequestBody Produto produto) throws Exception {
		return produtoService.cadastrarProduto(produto);
	}
}

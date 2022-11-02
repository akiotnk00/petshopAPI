package br.com.mjv.petshopAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mjv.petshopAPI.services.ProdutoService;
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
}

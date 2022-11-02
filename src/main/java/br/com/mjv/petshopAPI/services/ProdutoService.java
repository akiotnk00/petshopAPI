package br.com.mjv.petshopAPI.services;

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
}

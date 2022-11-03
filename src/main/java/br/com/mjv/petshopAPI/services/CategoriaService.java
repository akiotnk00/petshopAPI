package br.com.mjv.petshopAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mjv.petshopAPI.entity.Categoria;
import br.com.mjv.petshopAPI.entity.Produto;
import br.com.mjv.petshopAPI.repository.CategoriaRepository;
import br.com.mjv.petshopAPI.repository.ProdutoRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public Page<Categoria> findCategorias(Pageable pageable) {
		return categoriaRepository.findCategorias(pageable);
	}

	public List<Categoria> findCategoriaByNome(String nome) {
		return categoriaRepository.findByNome(nome);
	}

	public List<Categoria> findCategoriaByCodigo(Long codigo) {
		return categoriaRepository.findByCodigo(codigo);
	}

	public String cadastrarCategoria(Categoria categoria) throws Exception {
		if (categoria.equals(null)) {
			throw new Exception("Categoria invalida");
		} else {
			categoriaRepository.save(categoria);
		}
		return "Categoria cadastrada com sucesso!";
	}

	public String adicionaProdutoCategoria(Long codigoCategoria, Long codigoProduto) throws Exception {
		Optional<Categoria> categoriaBuscada = categoriaRepository.findById(codigoCategoria);
		Optional<Produto> produtoBuscado = produtoRepository.findById(codigoProduto);

		if (!categoriaBuscada.isPresent()) {
			throw new Exception("Categoria invalida");
		}
		if (!produtoBuscado.isPresent()) {
			throw new Exception("Produto invalido");
		}

		produtoBuscado.get().setCategoria(categoriaBuscada.get());
		
		produtoRepository.save(produtoBuscado.get());
		
		return "Produto adicionado a categoria com sucesso!";

	}
}

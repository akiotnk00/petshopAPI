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
import org.springframework.web.bind.annotation.RestController;

import br.com.mjv.petshopAPI.entity.Categoria;
import br.com.mjv.petshopAPI.services.CategoriaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	// Busca todas as categorias.
	@GetMapping
	public Page<Categoria> buscarCategorias(Pageable pageable) {
		return categoriaService.findCategorias(pageable);
	}

	// Cadastra uma nova categoria.
	@PostMapping
	public String cadastrarCategoria(@RequestBody Categoria categoria) throws Exception {
		return categoriaService.cadastrarCategoria(categoria);
	}

	// Deleta uma categoria pelo ID.
	@DeleteMapping("/{codigo}")
	public String deletarCategoria(@PathVariable long codigo) throws Exception {
		return categoriaService.deletarCategoria(codigo);
	}

	// Buscar categoria por codigo.
	@GetMapping("/{codigo}")
	public List<Categoria> buscarCategoriasPorCodigo(@PathVariable Long codigo) {

		return categoriaService.findCategoriaByCodigo(codigo);
	}

	// Adiciona um produto a categoria.
	@PatchMapping("/{codigo}/produto/{codigoProduto}")
	public String adicionarProdutoCategoria(@PathVariable Long codigo, @PathVariable Long codigoProduto)
			throws Exception {

		return categoriaService.adicionaProdutoCategoria(codigo, codigoProduto);
	}

}

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

import br.com.mjv.petshopAPI.entity.Categoria;
import br.com.mjv.petshopAPI.services.CategoriaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public Page<Categoria> findCategorias(Pageable pageable) {
		return categoriaService.findCategorias(pageable);
	}
	
	// Buscar categoria por nome.
	@GetMapping("/nome/{nome}")
	public List<Categoria> 
	findAllByNomeProdutos(@PathVariable String nome) {

		return categoriaService.findCategoriaByNome(nome);
	}

	// Buscar categoria por codigo.
	@GetMapping("/codigo/{codigo}")
	public List<Categoria> findAllByCodigoCategorias(@PathVariable Long codigo) {

		return categoriaService.findCategoriaByCodigo(codigo);
	}
	
	// Adiciona um produto a categoria.
	@GetMapping("/codigo/{codigo}/produto/{codigoProduto}")
	public String adicionaProdutoCategoria(@PathVariable Long codigo,@PathVariable Long codigoProduto) throws Exception {

		return categoriaService.adicionaProdutoCategoria(codigo,codigoProduto);
	}
	

	@PostMapping
	public String cadastrarCategoria(@RequestBody Categoria categoria) throws Exception {
		return categoriaService.cadastrarCategoria(categoria);
	}
}

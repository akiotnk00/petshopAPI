package br.com.mjv.petshopAPI.services;

import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.mjv.petshopAPI.entity.Categoria;
import br.com.mjv.petshopAPI.repository.CategoriaRepository;
import br.com.mjv.petshopAPI.repository.ProdutoRepository;

class CategoriaServiceTest {

	private CategoriaService service;

	@Mock
	private CategoriaRepository categoriaRepository;

	@Mock
	private ProdutoRepository produtoRepository;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.service = new CategoriaService(categoriaRepository, produtoRepository);
	}
		
	
	// Teste para verificar se o elemento é deletado.
	@Test
	void testDeveriaDeletarCategoria() {

		Categoria categoriaBuscada = categoria();

		Mockito.when(categoriaRepository.findById((long) 1)).thenReturn(Optional.of(categoriaBuscada));
			
		service.deletarCategoria((long) 1);

		Mockito.verify(categoriaRepository, times(1)).delete(categoriaBuscada);
	}
	

	
	// Metodo para criar uma categoria "fake" para os testes.
	private Categoria categoria(){
		
		Categoria primeira = new Categoria(1,"Categoria 1");
		
		return primeira;
	}

}

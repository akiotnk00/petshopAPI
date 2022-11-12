package br.com.mjv.petshopAPI.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.mjv.petshopAPI.entity.Categoria;

public class CategoriaDto {
	private Long codigo;
	private String nome;
	
	public CategoriaDto() {
	}
	
	public CategoriaDto(Categoria categoria) {
		this.codigo = categoria.getCodigo();
		this.nome = categoria.getNome();
	}

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static List<CategoriaDto> converter(List<Categoria> categorias){
		return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
	}
}

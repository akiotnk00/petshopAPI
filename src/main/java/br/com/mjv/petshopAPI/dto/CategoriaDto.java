package br.com.mjv.petshopAPI.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.mjv.petshopAPI.entity.Categoria;

public class CategoriaDto {
	private Long codigo;
	private String nome;

	public CategoriaDto() {
	}

	public CategoriaDto convert(Categoria categoria) {
		BeanUtils.copyProperties(categoria, this, "codigo", "nome");
		return this;
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

	public static List<CategoriaDto> convertList(List<Categoria> categorias) {
		CategoriaDto categoriaDto = new CategoriaDto();
		List<CategoriaDto> categoriaDtoList = new ArrayList<>();
		categorias.forEach(c -> {
			categoriaDtoList.add(categoriaDto.convert(c));
		});

		return categoriaDtoList;
	}
}

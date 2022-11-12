package br.com.mjv.petshopAPI.form;

import br.com.mjv.petshopAPI.entity.Categoria;

public class CategoriaForm {
	private String nome;

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Categoria converter() {
		return new Categoria(nome);
	}
}

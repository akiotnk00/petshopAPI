package br.com.mjv.petshopAPI.form;

import java.math.BigDecimal;

import br.com.mjv.petshopAPI.entity.Produto;

public class ProdutoForm {
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private Integer quantidade;
	private String imagemurl;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getImagemurl() {
		return imagemurl;
	}
	public void setImagemurl(String imagemurl) {
		this.imagemurl = imagemurl;
	}
	
	public Produto converter() {
		return new Produto(nome, descricao, valor, quantidade, imagemurl);
	}
}

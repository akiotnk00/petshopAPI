package br.com.mjv.petshopAPI.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mjv.petshopAPI.entity.Produto;

public class ProdutoDto {
	private Long codigo;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private Integer quantidade;
	private String imagemurl;
	
	public ProdutoDto() {
		super();
	}

	public ProdutoDto(Produto produto) {
		this.codigo = produto.getCodigo();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.imagemurl = produto.getImagemurl();
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

	public static List<ProdutoDto> converter(List<Produto> produtos){
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
	
}

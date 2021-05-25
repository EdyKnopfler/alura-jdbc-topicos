package br.com.pip.jdbc.modelo;

public class Produto {
	
	private Integer id;
	private String nome;
	private String descricao;
	
	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Produto(int id, String nome, String descricao) {
		this(nome, descricao);
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id + " - " + nome + " - " + descricao;
	}

	public Integer getId() {
		return id;
	}
	
}

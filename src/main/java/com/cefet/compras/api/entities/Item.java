package com.cefet.compras.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "item")
public class Item implements Serializable{
	
	private static final long serialVersionUID = -4653490944095509608L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", nullable = false)
	@NotEmpty(message = "Descrição não pode ser vazia.")
	@Length(min = 3, max = 200, message = "Descrição deve conter entre 3 e 200 caracteres.")
	private String descricao;
	
	@Column(name = "observacao", nullable = true)
	private String observacao;
	
	//@NotEmpty(message = "Categoria não pode ser vazia.")
	@ManyToOne(fetch = FetchType.EAGER)
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
	
}

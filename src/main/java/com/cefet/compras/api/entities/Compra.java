package com.cefet.compras.api.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotEmpty;

import java.time.Instant;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Compra")
public class Compra implements Serializable{

	private static final long serialVersionUID = -4347892548614073481L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", nullable = false)
	@NotEmpty(message = "Descrição não pode ser vazia.")
	@Length(min = 3, max = 200, message = "Descrição deve conter entre 3 e 200 caracteres.")
	private String descricao;
	
	@Column(name = "data")
	private Instant data;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemCompra> itensCompra;

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

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public List<ItemCompra> getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(List<ItemCompra> itensCompra) {
		this.itensCompra = itensCompra;
	}
	
	
	
	
	
}

package com.cefet.compras.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 6544624391764025672L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	@NotEmpty(message = "Nome não pode ser vazia.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	private String nome;
	
	@Column(name = "email", nullable = false)
	@NotEmpty(message = "Email não pode ser vazia.")
	@Length(min = 3, max = 200, message = "Email deve conter entre 3 e 200 caracteres.")
	private String email;
	
	@Column(name = "senha", nullable = false)
	@NotEmpty(message = "Senha não pode ser vazia.")
	@Length(min = 3, max = 200, message = "Senha deve conter entre 3 e 200 caracteres.")
	/*implementação do spring security */
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}

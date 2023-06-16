package com.controlecadastro.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "procedimento")
public class Procedimento extends Persistivel implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 50)
	private String nome;
	@Column(nullable = false, length = 100)
	private String descricao;

	public Procedimento(String nome, String descricao)
	{
		this.nome = nome;
		this.descricao = descricao;
	}

	public Procedimento()
	{

	}

	public String getNome()
	{
		return this.nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getDescricao()
	{
		return this.descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
}

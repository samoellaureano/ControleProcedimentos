package com.controlecadastro.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente extends Persistivel implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Column(nullable = false, length = 14)
	private String nome;
	@Column(nullable = false, length = 11)
	private long cpf;
	@Column(nullable = false, length = 3)
	private int idade;
	@Column(nullable = false, length = 1)
	private String sexo;

	public Paciente(String nome, String cpf, int idade, String sexo)
	{
		this.nome = nome;
		this.cpf = Long.parseLong(cpf.replaceAll("[^0-9]", ""));
		this.idade = idade;
		this.sexo = sexo;
	}

	public Paciente()
	{

	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Long getCpf()
	{
		return cpf;
	}

	public void setCpf(String cpf)
	{
		this.cpf = Long.parseLong(cpf.replaceAll("[^0-9]", ""));
	}

	public int getIdade()
	{
		return idade;
	}

	public void setIdade(int idade)
	{
		this.idade = idade;
	}

	public String getSexo()
	{
		return sexo;
	}

	public void setSexo(String sexo)
	{
		this.sexo = sexo;
	}
}

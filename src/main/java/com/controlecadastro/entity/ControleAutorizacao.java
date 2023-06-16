package com.controlecadastro.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "controle_autorizacao")
public class ControleAutorizacao extends Persistivel implements Serializable
{
	private static final long serialVersionUID = 1L;
	@JoinColumn(referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Procedimento procedimento;
	@Column(nullable = false, length = 3)
	private int idade;

	@Column(nullable = false, length = 1)
	private String sexo;

	@Column(nullable = false)
	private boolean permitido;

	public ControleAutorizacao(Procedimento procedimento, int idade, String sexo, boolean permitido)
	{
		this.procedimento = procedimento;
		this.idade = idade;
		this.sexo = sexo;
		this.permitido = permitido;
	}

	public ControleAutorizacao()
	{

	}

	public Procedimento getProcedimento()
	{
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento)
	{
		this.procedimento = procedimento;
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

	public boolean getPermitido()
	{
		return permitido;
	}

	public void setPermitido(boolean permitido)
	{
		this.permitido = permitido;
	}
}

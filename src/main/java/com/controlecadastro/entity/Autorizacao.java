package com.controlecadastro.entity;

import com.controlecadastro.dao.jpa.ControleAutorizacaoJPADAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "autorizacao")
public class Autorizacao extends Persistivel implements Serializable
{
	private static final long serialVersionUID = 1L;

	@JoinColumn(referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Procedimento procedimento;

	@JoinColumn(referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Paciente paciente;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column(nullable = false)
	private boolean autorizado;

	public Autorizacao(Procedimento procedimento, Paciente paciente)
	{
		this.procedimento = procedimento;
		this.paciente = paciente;
	}

	public Autorizacao()
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

	public Paciente getPaciente()
	{
		return paciente;
	}

	public void setPaciente(Paciente paciente)
	{
		this.paciente = paciente;
	}

	public Date getData()
	{
		return data;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

	public boolean isAutorizado()
	{
		return autorizado;
	}

	public void setAutorizado(boolean autorizado)
	{
		this.autorizado = autorizado;
	}

	public boolean validar()
	{
		List<ControleAutorizacao> listaControleAutorizacao = new ControleAutorizacaoJPADAO().buscarAutorizacao(paciente, procedimento);
		if(listaControleAutorizacao.isEmpty()){
			return false;
		}
		else {
			this.autorizado = listaControleAutorizacao.get(0).getPermitido();
			this.data = new Date();
			return true;
		}
	}
}

package com.controlecadastro.dao.jpa;

import com.controlecadastro.dao.PacienteDAO;
import com.controlecadastro.entity.Paciente;

public class PacienteJPADAO extends JPAAbstract<Paciente> implements PacienteDAO
{
	@Override
	public String getEntityName()
	{
		return "Paciente";
	}

}

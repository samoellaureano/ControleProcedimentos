package com.controlecadastro.dao.jpa;

import com.controlecadastro.dao.PacienteDAO;
import com.controlecadastro.entity.Paciente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

public class PacienteJPADAO extends JPAAbstract<Paciente> implements PacienteDAO
{
	@Override
	public String getEntityName()
	{
		return "Paciente";
	}
}

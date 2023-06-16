package com.controlecadastro.dao.jpa;

import com.controlecadastro.dao.ProcedimentoDAO;
import com.controlecadastro.entity.Procedimento;

public class ProcedimentoJPADAO extends JPAAbstract<Procedimento> implements ProcedimentoDAO
{
	@Override
	public String getEntityName()
	{
		return "Procedimento";
	}
}

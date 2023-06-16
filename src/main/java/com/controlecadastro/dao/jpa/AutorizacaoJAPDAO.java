package com.controlecadastro.dao.jpa;

import com.controlecadastro.dao.AutorizacaoDAO;
import com.controlecadastro.entity.Autorizacao;

public class AutorizacaoJAPDAO extends JPAAbstract<Autorizacao> implements AutorizacaoDAO
{
	@Override
	public String getEntityName()
	{
		return "Autorizacao";
	}
}

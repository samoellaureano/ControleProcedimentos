package com.controlecadastro.dao;

import com.controlecadastro.entity.ControleAutorizacao;
import com.controlecadastro.entity.Paciente;
import com.controlecadastro.entity.Procedimento;
import java.util.List;

public interface ControleAutorizacaoDAO
{
	public List<ControleAutorizacao> buscarAutorizacao(Paciente paciente, Procedimento procedimento);
}

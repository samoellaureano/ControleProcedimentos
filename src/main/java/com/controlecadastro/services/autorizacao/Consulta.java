package com.controlecadastro.services.autorizacao;

import com.controlecadastro.dao.jpa.AutorizacaoJAPDAO;
import com.controlecadastro.dao.jpa.PacienteJPADAO;
import com.controlecadastro.entity.Autorizacao;
import com.controlecadastro.entity.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/autorizacao/consulta")
public class Consulta extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void init()
	{

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setCharacterEncoding("UTF-8");

		List<Autorizacao> autorizacoes = new AutorizacaoJAPDAO().buscar();
		ObjectMapper objectMapper = new ObjectMapper();

		String autorizacoesJson = objectMapper.writeValueAsString(autorizacoes);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(autorizacoesJson);
	}
}


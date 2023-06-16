package com.controlecadastro.services.paciente;

import com.controlecadastro.dao.jpa.PacienteJPADAO;
import com.controlecadastro.dao.jpa.ProcedimentoJPADAO;
import com.controlecadastro.entity.Paciente;
import com.controlecadastro.entity.Procedimento;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/paciente/consulta")
public class Consulta extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void init()
	{

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setCharacterEncoding("UTF-8");

		List<Paciente> pacientes = new PacienteJPADAO().buscar();
		ObjectMapper objectMapper = new ObjectMapper();

		String procedimentosJson = objectMapper.writeValueAsString(pacientes);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(procedimentosJson);
	}
}


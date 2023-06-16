package com.controlecadastro.services.procedimento;

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
import org.json.JSONObject;

@WebServlet("/procedimento/consultaPorNome")
public class ConsultaPorNome extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void init()
	{

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setCharacterEncoding("UTF-8");

		try
		{
			String jsonPayload = request.getReader().lines()
				.reduce("", (accumulator, actual) -> accumulator + actual);

			JSONObject jsonObject = new JSONObject(jsonPayload);

			String nomeProcedimento = jsonObject.getString("nomeProcedimento");
			List<Procedimento> procedimentos = new ProcedimentoJPADAO().buscarPorNome(nomeProcedimento);
			ObjectMapper objectMapper = new ObjectMapper();

			String procedimentosJson = objectMapper.writeValueAsString(procedimentos);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(procedimentosJson);
		}
		catch (Exception e)
		{
			response.getWriter().write("Erro ao realizar consulta!");
		}
	}
}


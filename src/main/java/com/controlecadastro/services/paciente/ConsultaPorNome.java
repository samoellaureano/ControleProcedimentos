package com.controlecadastro.services.paciente;

import com.controlecadastro.dao.jpa.PacienteJPADAO;
import com.controlecadastro.entity.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/paciente/consultaPorNome")
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

			String nomePaciente = jsonObject.getString("nomePaciente");
			List<Paciente> pacientes = new PacienteJPADAO().buscarPorNome(nomePaciente);
			ObjectMapper objectMapper = new ObjectMapper();

			String pacientesJson = objectMapper.writeValueAsString(pacientes);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(pacientesJson);
		}
		catch (Exception e)
		{
			response.getWriter().write("Erro ao realizar consulta!");
		}
	}
}


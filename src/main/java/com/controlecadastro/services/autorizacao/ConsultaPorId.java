package com.controlecadastro.services.autorizacao;

import com.controlecadastro.dao.jpa.AutorizacaoJAPDAO;
import com.controlecadastro.entity.Autorizacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/autorizacao/consultaPorId")
public class ConsultaPorId extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void init()
	{

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setCharacterEncoding("UTF-8");

		try
		{
			String jsonPayload = request.getReader().lines()
				.reduce("", (accumulator, actual) -> accumulator + actual);

			JSONObject jsonObject = new JSONObject(jsonPayload);

			int idAutorizacao = jsonObject.getInt("idAutorizacao");
			Autorizacao autorizacao = new AutorizacaoJAPDAO().buscarPorId(idAutorizacao);
			ObjectMapper objectMapper = new ObjectMapper();

			String autorizacaoJson = objectMapper.writeValueAsString(autorizacao);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(autorizacaoJson);
		}
		catch (Exception e)
		{
			response.getWriter().write("Erro ao realizar consulta!");
		}
	}
}


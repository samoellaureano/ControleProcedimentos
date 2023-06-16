package com.controlecadastro.services.paciente;

import com.controlecadastro.dao.jpa.PacienteJPADAO;
import com.controlecadastro.entity.Paciente;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/paciente/cadastro")
public class Cadastro extends HttpServlet
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

			String nome = jsonObject.getString("nome");
			String cpf = jsonObject.getString("cpf");
			int idade = jsonObject.getInt("idade");
			String sexo = jsonObject.getString("sexo");

			Paciente paciente = new Paciente(nome, cpf, idade, sexo);
			if (new PacienteJPADAO().salvar(paciente))
			{
				response.getWriter().write("Cadastro realizado com sucesso!");
			}
			else
			{
				response.getWriter().write("Erro ao realizar cadastro!");
			}
		}
		catch (JSONException e)
		{
			throw new RuntimeException(e);
		}
	}

}


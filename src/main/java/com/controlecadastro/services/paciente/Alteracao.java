package com.controlecadastro.services.paciente;

import com.controlecadastro.dao.jpa.PacienteJPADAO;
import com.controlecadastro.entity.Paciente;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/paciente/alteracao")
public class Alteracao extends HttpServlet
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
			int idPaciente = jsonObject.getInt("idPaciente");
			Paciente paciente = new PacienteJPADAO().buscarPorId(idPaciente);
			paciente.setNome(nome);
			paciente.setCpf(cpf);
			paciente.setIdade(idade);
			paciente.setSexo(sexo);

			if (new PacienteJPADAO().atualizar(paciente))
			{
				response.getWriter().write("Atualização realizado com sucesso!");
			}
			else
			{
				response.getWriter().write("Erro ao realizar a atualização!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

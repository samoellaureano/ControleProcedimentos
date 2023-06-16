package com.controlecadastro.services.procedimento;

import com.controlecadastro.dao.jpa.ProcedimentoJPADAO;
import com.controlecadastro.entity.Procedimento;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/procedimento/cadastro")
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
			String descricao = jsonObject.getString("descricao");
			Procedimento procedimento = new Procedimento(nome, descricao);
			if (new ProcedimentoJPADAO().salvar(procedimento))
			{
				response.getWriter().write("Cadastro realizado com sucesso!");
			}
			else
			{
				response.getWriter().write("Erro ao realizar cadastro!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

package com.controlecadastro.services.autorizacao;

import com.controlecadastro.dao.jpa.AutorizacaoJAPDAO;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/autorizacao/excluir")
public class exclusao extends HttpServlet
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

			int idAutorizacao = jsonObject.getInt("idAutorizacao");
			if (new AutorizacaoJAPDAO().excluirPorId(idAutorizacao))
			{
				response.getWriter().write("Autorização excluida com sucesso!");
			}
			else
			{
				response.getWriter().write("Erro ao excluir a autorização!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

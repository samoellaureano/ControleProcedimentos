package com.controlecadastro.services.procedimento;

import com.controlecadastro.dao.jpa.ProcedimentoJPADAO;
import com.controlecadastro.entity.Procedimento;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/procedimento/alteracao")
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
			String descricao = jsonObject.getString("descricao");
			int idProcedimento = jsonObject.getInt("idProcedimento");
			Procedimento procedimento = new ProcedimentoJPADAO().buscarPorId(idProcedimento);
			procedimento.setNome(nome);
			procedimento.setDescricao(descricao);
			if (new ProcedimentoJPADAO().atualizar(procedimento))
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

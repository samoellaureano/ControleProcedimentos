package com.controlecadastro.services.controleAutorizacao;

import com.controlecadastro.dao.jpa.ControleAutorizacaoJPADAO;
import com.controlecadastro.dao.jpa.ProcedimentoJPADAO;
import com.controlecadastro.entity.ControleAutorizacao;
import com.controlecadastro.entity.Procedimento;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/controleautorizacao/cadastro")
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

			int idade = jsonObject.getInt("idade");
			String sexo = jsonObject.getString("sexo");
			boolean permitido = jsonObject.getBoolean("permitido");
			int idProcedimento = jsonObject.getInt("idProcedimento");
			Procedimento procedimento = new ProcedimentoJPADAO().buscarPorId(idProcedimento);
			ControleAutorizacao controleAutorizacao = new ControleAutorizacao(procedimento, idade, sexo,
				permitido);

			if (new ControleAutorizacaoJPADAO().salvar(controleAutorizacao))
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

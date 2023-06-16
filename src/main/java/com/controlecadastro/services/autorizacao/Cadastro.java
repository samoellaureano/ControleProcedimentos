package com.controlecadastro.services.autorizacao;

import com.controlecadastro.dao.jpa.AutorizacaoJAPDAO;
import com.controlecadastro.dao.jpa.PacienteJPADAO;
import com.controlecadastro.dao.jpa.ProcedimentoJPADAO;
import com.controlecadastro.entity.Autorizacao;
import com.controlecadastro.entity.Paciente;
import com.controlecadastro.entity.Procedimento;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/autorizacao/cadastro")
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

			int idProcedimento = jsonObject.getInt("idProcedimento");
			Procedimento procedimento = new ProcedimentoJPADAO().buscarPorId(idProcedimento);
			int idPaciente = jsonObject.getInt("idPaciente");
			Paciente paciente = new PacienteJPADAO().buscarPorId(idPaciente);

			Autorizacao autorizacao = new Autorizacao(procedimento, paciente);
			//validar conforme o tipo de procedimento e paciente
			boolean autorizacaoCadastrada = autorizacao.validar();

			if (autorizacaoCadastrada)
			{
				if (new AutorizacaoJAPDAO().salvar(autorizacao))
				{
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("message", "Cadastro realizado com sucesso!");

					response.setStatus(200);
					response.setContentType("application/json");
					response.getWriter().write(jsonResponse.toString());
				}
				else
				{
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("message", "Erro ao realizar cadastro!");

					response.setStatus(500);
					response.setContentType("application/json");
					response.getWriter().write(jsonResponse.toString());
				}
			}
			else
			{
				response.getWriter().write(
					"Cadastro não permitido! Não existe um critério para permitir ou negar o procedimento!");
			}
		}
		catch (JSONException e)
		{
			JSONObject jsonResponse = new JSONObject();
			try
			{
				jsonResponse.put("message", "Cadastro não permitido! Não existe um critério para permitir ou negar o procedimento!");
			}
			catch (JSONException ex)
			{
				throw new RuntimeException(ex);
			}

			response.setStatus(400);
			response.setContentType("application/json");
			response.getWriter().write(jsonResponse.toString());
		}
	}

}


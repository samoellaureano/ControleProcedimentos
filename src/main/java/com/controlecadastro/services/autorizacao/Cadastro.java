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
					response.getWriter().write("Cadastro realizado com sucesso!");
				}
				else
				{
					response.getWriter().write("Erro ao realizar cadastro!");
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
			e.printStackTrace();
		}
	}

}


package com.controlecadastro.services.autorizacao;

import com.controlecadastro.dao.jpa.AutorizacaoJAPDAO;
import com.controlecadastro.dao.jpa.ControleAutorizacaoJPADAO;
import com.controlecadastro.dao.jpa.PacienteJPADAO;
import com.controlecadastro.dao.jpa.ProcedimentoJPADAO;
import com.controlecadastro.entity.Autorizacao;
import com.controlecadastro.entity.ControleAutorizacao;
import com.controlecadastro.entity.Paciente;
import com.controlecadastro.entity.Procedimento;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/autorizacao/alteracao")
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

			int idProcedimento = jsonObject.getInt("idProcedimento");
			Procedimento procedimento = new ProcedimentoJPADAO().buscarPorId(idProcedimento);
			int idPaciente = jsonObject.getInt("idPaciente");
			Paciente paciente = new PacienteJPADAO().buscarPorId(idPaciente);
			int idAutorizacao = jsonObject.getInt("idAutorizacao");
			Autorizacao autorizacao = new AutorizacaoJAPDAO().buscarPorId(idAutorizacao);
			autorizacao.setProcedimento(procedimento);
			autorizacao.setPaciente(paciente);

			if (new AutorizacaoJAPDAO().atualizar(autorizacao))
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

package br.edu.ifpb.projeto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.model.Aluno;

public class AlunoController extends ApplicationController {

	// DAO
	private AlunoDAO alunoDAO = new AlunoDAO();

	public AlunoController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public RequestDispatcher editarPerfil() throws IOException {
		super.authUserOrRedirect("usuario");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/aluno/editar_perfil.jsp");
		HttpSession session = request.getSession();

		List<String> fields = new ArrayList<>(Arrays.asList("matricula", "competencias"));
		Aluno aluno = (Aluno) session.getAttribute("usuario");

		// Se for um POST
		if (request.getMethod().equals("POST")) {

			// Valida os campos
			if (super.validaFormulario(fields)) {
				aluno.setCompetencias(request.getParameter("competencias"));
				aluno.setMatricula(Integer.parseInt(request.getParameter("matricula")));

				// Grava alterações
				alunoDAO.beginTransaction();
				alunoDAO.update(aluno);
				alunoDAO.commit();

				super.addFlashMessage("success", "Perfil atualizado com sucesso!");
			}
		}

		if (request.getMethod().equals("GET")) {
			request.setAttribute("matricula", aluno.getMatricula());
			request.setAttribute("competencias", aluno.getCompetencias());
		}

		return dispatcher;
	}

}

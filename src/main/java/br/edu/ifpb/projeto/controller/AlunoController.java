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
import br.edu.ifpb.projeto.dao.EstagioDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Vaga;

public class AlunoController extends ApplicationController {

	// DAO
	private AlunoDAO alunoDAO = new AlunoDAO();
	private VagaDAO vagaDAO = new VagaDAO();
	private EstagioDAO estagioDAO = new EstagioDAO();

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

	/*
	 * Responsável por atrelar um aluno a uma vaga
	 */
	public RequestDispatcher candidatarVaga() throws IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		HttpSession session = request.getSession();

		// Se não estiver logado, redireciona para home
		if (session.getAttribute("usuario") == null) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/usuario/login");
			return dispatcher;
		}

		// Se não for passado um id valido, redireciona
		if (this.request.getParameter("id").isEmpty() || !this.request.getParameter("id").matches("^\\d+$")) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/vagas");
		}

		Vaga vaga = vagaDAO.find(Integer.parseInt(this.request.getParameter("id")));

		// Se a vaga não existir, redirecione para vagas
		if (vaga == null) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/vagas");
			return dispatcher;
		}

		Aluno aluno = (Aluno) session.getAttribute("usuario");

		// Se o aluno já estiver concorrendo a vaga.. redirecione para vagas.
		if (vagaDAO.getByIdAndAluno(aluno, vaga) != null) {
			super.addFlashMessage("info", "Você já está concorrendo a vaga");
			response.sendRedirect(request.getServletContext().getContextPath() + "/vagas");
			return dispatcher;
		}

		// Grava o aluno na vaga
		vagaDAO.beginTransaction();
		vaga.addAluno(aluno);
		vagaDAO.update(vaga);
		vagaDAO.commit();

		super.addFlashMessage("success", "Parabéns você está concorrendo a vaga (" + vaga.getId() + ")");
		response.sendRedirect(request.getServletContext().getContextPath() + "/vagas");

		return dispatcher;
	}

}

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
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.VagaAlunoDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Vaga;
import br.edu.ifpb.projeto.model.VagaAluno;

public class AlunoController extends ApplicationController {

	// DAO
	private AlunoDAO alunoDAO = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
	private VagaDAO vagaDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());

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

		VagaAlunoDAO vagaAlunoDAO = new VagaAlunoDAO();

		// Se o aluno já estiver concorrendo a vaga.. redirecione para vagas.
		if (vagaAlunoDAO.findBy(aluno, vaga) != null) {
			super.addFlashMessage("info", "Você já está concorrendo a vaga");
			response.sendRedirect(request.getServletContext().getContextPath() + "/vagas");
			return dispatcher;
		}

		VagaAluno vagaAluno = new VagaAluno();
		vagaAluno.setAluno(aluno);
		vagaAluno.setVaga(vaga);

		aluno.addVagaAluno(vagaAluno);
		vaga.addVagaAluno(vagaAluno);

		// Commita o vaga aluno
		vagaAlunoDAO.beginTransaction();
		vagaAlunoDAO.insert(vagaAluno);
		vagaAlunoDAO.commit();

		// Commita a vaga
		vagaDAO.beginTransaction();
		vagaDAO.update(vaga);
		vagaDAO.commit();

		// Commita o aluno
		alunoDAO.beginTransaction();
		alunoDAO.update(aluno);
		alunoDAO.commit();

		super.addFlashMessage("success", "Parabéns você está concorrendo a vaga (" + vaga.getId() + ")");
		response.sendRedirect(request.getServletContext().getContextPath() + "/vagas");

		return dispatcher;
	}

	/*
	 * Método responsável por exibir o perfil público do aluno
	 */
	public RequestDispatcher perfilPublico() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/aluno/perfil_publico.jsp");

		if (!this.request.getParameter("id").matches("^\\d+$")) {
			super.addFlashMessage("error", "Aluno não localizado");
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		Aluno aluno = alunoDAO.find(Integer.parseInt(this.request.getParameter("id")));

		if (aluno == null) {
			super.addFlashMessage("error", "Aluno não localizado");
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		this.request.setAttribute("aluno", aluno);
		return dispatcher;
	}

}

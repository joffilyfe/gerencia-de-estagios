package br.edu.ifpb.projeto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.VagaAlunoDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Vaga;
import br.edu.ifpb.projeto.model.VagaAluno;

public class VagaController extends ApplicationController {

	private VagaDAO vagaDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());
	private VagaAlunoDAO vagaAlunoDAO = new VagaAlunoDAO(PersistenceUtil.getCurrentEntityManager());

	public VagaController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	/*
	 * Retorna uma lista com todas as vagas
	 */
	public RequestDispatcher index() {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/vaga/index.jsp");
		this.request.setAttribute("vagas", vagaDAO.findAll());
		return dispatcher;
	}

	/*
	 * Mostra uma página com os detalhes de uma vaga
	 */
	public RequestDispatcher show() throws IOException {
		// Valida o ID da vaga
		if (this.request.getParameter("id").isEmpty() || !this.request.getParameter("id").matches("^\\d+$")) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/vagas");
		}

		RequestDispatcher dispatch = this.request.getRequestDispatcher("/view/vaga/detalhes.jsp");

		Vaga vaga = vagaDAO.find(Integer.parseInt(this.request.getParameter("id")));
		request.setAttribute("vaga", vaga);

		return dispatch;
	}

	public RequestDispatcher listarVagas() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/vaga/listaVagas.jsp");
		HttpSession session = request.getSession();

		Empresa empresa = (Empresa) session.getAttribute("usuario");

		// Verifica se o usuário tem acesso e se a empresa existe
		if (!super.canAccess("usuario", "Empresa") || empresa == null) {
			super.addFlashMessage("error", "Você não possui acesso");
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		this.request.setAttribute("vagas", empresa.getVagas());

		return dispatcher;
	}

	/*
	 * Método responsável por listar os candidatos a uma vaga (Empresa)
	 */
	public RequestDispatcher listarCandidatos() throws IOException {
		HttpSession session = request.getSession();
		String idVaga = request.getParameter("idvaga");
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/vaga/listaCandidatos.jsp");

		// Verifica se o usuário é empresa
		if (!super.canAccess("usuario", "Empresa")) {
			super.addFlashMessage("error", "Você não possui acesso");
			response.sendRedirect(request.getServletContext().getContextPath() + "/vagas/visualizarvagas");
			return dispatcher;
		}

		Empresa empresa = (Empresa) session.getAttribute("usuario");

		if (request.getMethod().equals("POST")) {

			if (idVaga.matches("^\\d+$")) {
				Vaga vaga = vagaDAO.find(Integer.parseInt(idVaga));

				if (vaga == null) {
					super.addFlashMessage("error", "Vaga não encontrada");
					response.sendRedirect(request.getServletContext().getContextPath() + "/vagas/visualizarvagas");
					return dispatcher;
				}

				// Verifica se a vaga é da empresa
				if (vaga.getEmpresa() != empresa) {
					super.addFlashMessage("error", "Você não tem acesso a está vaga");
					response.sendRedirect(request.getServletContext().getContextPath());
					return dispatcher;
				}

				List<Aluno> candidatos = new ArrayList<Aluno>();

				// vagaAlunoDAO.beginTransaction();
				List<VagaAluno> vagaAlunos = vagaAlunoDAO.findBy(vaga);
				// vagaAlunoDAO.commit();

				// Setando os alunos como admitidos
				if (vagaAlunos != null) {
					for (VagaAluno vagaAluno : vagaAlunos) {
						Aluno aluno = vagaAluno.getAluno();

						if (vagaAluno.isAdmitido()) {
							aluno.setAdmitido(true);
						}

						candidatos.add(aluno);
					}
				} else {
					System.out.println("Sem vaga aluno");
				}

				this.request.setAttribute("vaga", vaga);
				this.request.setAttribute("candidatos", candidatos);
			}
		}

		return dispatcher;
	}

}

package br.edu.ifpb.projeto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Usuario;
import br.edu.ifpb.projeto.model.Vaga;

public class VagaController extends ApplicationController {

	private VagaDAO vagaDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());

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
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			response.sendRedirect(request.getServletContext().getContextPath());
		} else if (usuario.isCoordenador() == false && usuario.isEmpresa() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}

		if (usuario.isCoordenador()) {
			this.request.setAttribute("vagas", vagaDAO.findAll());
		} else if (usuario.isEmpresa()) {
			this.request.setAttribute("vagas", ((Empresa) usuario).getVagas());
		}

		return dispatcher;
	}

	public RequestDispatcher listarCandidatos() throws IOException {

		HttpSession session = request.getSession();
		String idVaga = request.getParameter("idvaga");
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/vaga/listaCandidatos.jsp");

		if (session.getAttribute("usuario") == null) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}

		if (((Usuario) session.getAttribute("usuario")).isCoordenador() == false
				&& ((Usuario) session.getAttribute("usuario")).isEmpresa() == false) {
		}

		if (request.getMethod().equals("POST")) {
			if (idVaga.matches("^\\d+$")) {
				Vaga vaga = vagaDAO.find(Integer.parseInt(idVaga));
				List<Aluno> candidatos = vaga.getAlunos();

				this.request.setAttribute("vaga", vaga);
				this.request.setAttribute("candidatos", candidatos);
			}
		}

		return dispatcher;
	}

}

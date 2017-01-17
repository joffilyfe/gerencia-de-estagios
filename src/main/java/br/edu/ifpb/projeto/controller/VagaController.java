package br.edu.ifpb.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.VagaDAO;
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

}

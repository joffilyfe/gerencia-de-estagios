package br.edu.ifpb.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.model.Usuario;

public class CoordenadorController extends ApplicationController{
	
	private EmpresaDAO empresaDAO = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());	
	
	public CoordenadorController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public RequestDispatcher index() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/index.jsp");
		HttpSession session = request.getSession();
		this.request.setAttribute("empresas", empresaDAO.findAll());
		
		if (session.getAttribute("usuario") == null  || ((Usuario) session.getAttribute("usuario")).isCoordenador() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}

		return dispatcher;
	}
}

package br.edu.ifpb.projeto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.UsuarioDAO;
import br.edu.ifpb.projeto.model.Empresa;


public class UsuarioController extends ApplicationController {

	// DAOS
	private UsuarioDAO usuarioDAO = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());
	private EmpresaDAO empresaDAO = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());

	public UsuarioController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public RequestDispatcher cadastro() throws IOException {
		// Determinamos quais campos queremos validar
		List<String> fields = new ArrayList<>(Arrays.asList("nome", "email", "senha", "tipo"));

		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/usuario/cadastro.jsp");

		if (request.getMethod().equals("POST")) {
			// Se o formulário for válido..
			if (super.validaFormulario(fields)) {

				// Cria um usuário do tipo empresa
				if (this.request.getParameter("tipo").equals("empresa")) {
					Empresa empresa = new Empresa(this.request.getParameter("nome"), this.request.getParameter("email"));
					empresa.setSenha(this.request.getParameter("senha"));

					// Gravando empresa no banco
					empresaDAO.beginTransaction();
					empresaDAO.insert(empresa);
					empresaDAO.commit();
				}

				if (this.request.getParameter("tipo").equals("aluno")) {
				}

				this.addFlashMessage("success", "Cadastro realizado com sucesso");

				// redireciona para a index do site
				response.sendRedirect(request.getServletContext().getContextPath());
			}
		}
		return dispatcher;
	}


}

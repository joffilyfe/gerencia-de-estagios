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
import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.UsuarioDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Usuario;

public class UsuarioController extends ApplicationController {

	// DAOS
	private EmpresaDAO empresaDAO = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
	private AlunoDAO alunoDAO = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
	private UsuarioDAO usuarioDAO = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());

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
					Empresa empresa = new Empresa(this.request.getParameter("nome"),
							this.request.getParameter("email"));
					empresa.setSenha(this.request.getParameter("senha"));

					// Gravando empresa no banco
					empresaDAO.beginTransaction();
					empresaDAO.insert(empresa);
					empresaDAO.commit();
				}

				// Cria um usuário do tipo aluno
				if (this.request.getParameter("tipo").equals("aluno")) {
					Aluno aluno = new Aluno(this.request.getParameter("nome"), this.request.getParameter("email"), 0);
					aluno.setSenha(this.request.getParameter("senha"));

					alunoDAO.beginTransaction();
					alunoDAO.insert(aluno);
					alunoDAO.commit();
				}

				this.addFlashMessage("success", "Cadastro realizado com sucesso");

				// redireciona para a index do site
				response.sendRedirect(request.getServletContext().getContextPath());
			}
		}
		return dispatcher;
	}

	public RequestDispatcher login() throws IOException {
		HttpSession session = request.getSession();
		List<String> fields = new ArrayList<>(Arrays.asList("email", "senha"));
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/usuario/login.jsp");

		// Se já estiver logado, redireciona para home
		if (session.getAttribute("usuario") != null) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}

		// Se o usuário não estiver na sessão
		if (request.getMethod().equals("POST")) {

			// Se o formulário for válido
			if (super.validaFormulario(fields)) {
				Usuario usuario = usuarioDAO.getByCredentials(request.getParameter("email"),
						request.getParameter("senha"));

				// Se o usuário não for encontrado, manda mensagem de erro.
				if (usuario == null) {
					super.addFlashMessage("error", "E-mail ou senha incorretos");
					return dispatcher;
				}

				session.setAttribute("usuario", usuario);
				super.addFlashMessage("success", "Login realizado com sucesso");
				response.sendRedirect(request.getServletContext().getContextPath());
			}
		}
		return dispatcher;
	}

	public RequestDispatcher logout() throws IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("usuario") == null) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/usuario/login");
		}

		session.removeAttribute("usuario");
		super.addFlashMessage("info", "Logout realizado com sucesso.");

		response.sendRedirect(request.getServletContext().getContextPath() + "/usuario/login");

		return null;
	}

	/*
	 * Método responsável por exibir o painel do usuário
	 */
	public RequestDispatcher painel() throws IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/usuario/painel.jsp");
		HttpSession session = request.getSession();

		if (session.getAttribute("usuario") == null) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/usuario/login");
			return dispatcher;
		}

		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario.isEmpresa()) {
			Empresa user = empresaDAO.find(usuario.getId());
			// request.setAttribute("usuario", user);
			session.setAttribute("usuario", user);
		} else if (usuario.isAluno()) {
			Aluno user = alunoDAO.find(usuario.getId());
			// request.setAttribute("usuario", user);
			session.setAttribute("usuario", user);
		}

		return dispatcher;
	}

	/*
	 * Método responsável por editar o perfil
	 */
	public RequestDispatcher editarPerfil() throws IOException {
		super.authUserOrRedirect("usuario");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/usuario/editar_perfil.jsp");
		return dispatcher;
	}
}

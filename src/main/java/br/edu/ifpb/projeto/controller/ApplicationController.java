package br.edu.ifpb.projeto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.projeto.model.Usuario;

/*
 * Essa classe é responsável por disponibilizar métodos para todas as suas classes filhas.
 * Os métodos visam facilitar o tratamento de informações vindas de uma requesição.
 *
 * ATENÇÂO: Todo controller precisa hedar do ApplicationController
 *
 */

public class ApplicationController {

	// HTTP
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	// Messages
	private ArrayList<String> errors = new ArrayList<String>();
	private ArrayList<String> success = new ArrayList<String>();
	private ArrayList<String> info = new ArrayList<String>();

	public ApplicationController(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	// Função para validar formulários
	protected boolean validaFormulario(List<String> fields) {
		// Add errors to request
		this.request.setAttribute("errors", this.errors);
		this.request.setAttribute("success", this.success);

		for (String field : fields) {
			System.out.println(field);
			boolean error = false;

			if (this.request.getParameter(field) == null || this.request.getParameter(field).length() < 1) {
				errors.add("O campo " + field + " precisa ser preenchido.");
				// return false;
				error = true;
			}

			// custom validation for dates
			if (field.matches("[\\w]*-data")) {
				if (!this.request.getParameter(field)
						.matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2,2}")) {
					errors.add("O campo " + field + " precisa de um formato válido.");
					// return false;
					error = true;
				}

			}

			// set valid attributes to request
			if (!error) {
				this.request.setAttribute(field, this.request.getParameter(field));
			}
		}

		if (!this.errors.isEmpty()) {
			return false;
		}

		return true;
	}

	// Add messages to request
	protected void addFlashMessage(String type, String message) {
		HttpSession session = request.getSession();
		if (type == "success") {
			this.success.add(message);
			session.setAttribute("success", this.success);
		}

		if (type == "info") {
			this.info.add(message);
			session.setAttribute("infos", this.info);
		}

		if (type == "error") {
			this.errors.add(message);
			session.setAttribute("errors", this.errors);
		}
	}

	protected void authUserOrRedirect(String field) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(field) == null) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/usuario/login");
		}
	}

	/*
	 * Esta função verifica se o usuário esta logado e o seu tipo
	 */
	protected boolean canAccess(String field, String cls) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(field) == null
				|| !((Usuario) session.getAttribute(field)).getTipo_usuario().equals(cls)) {
			return false;
		}

		return true;
	}
}

package br.edu.ifpb.projeto.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.project.util.Application;
import br.edu.ifpb.projeto.dao.UsuarioDAO;
import br.edu.ifpb.projeto.model.Usuario;

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean {

	private Usuario usuario;
	private String email;
	private String senha;

	/*
	 * Realiza login do usuário
	 */
	public String login() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		this.usuario = usuarioDAO.getByCredentials(this.email, this.senha);

		if (this.usuario == null) {
			Application.addMessage("E-mail ou senha incorretos", FacesMessage.SEVERITY_ERROR);
			return "/view/login?faces-redirect=true";
		}

		Application.addMessage("Login realizado com sucesso", FacesMessage.SEVERITY_INFO);

		return "/view/index?faces-redirect=true";
	}

	/*
	 * Realiza o logout do usuário
	 */
	public String logout() {
		this.usuario = null;
		Application.addMessage("Logout realizado com sucesso", FacesMessage.SEVERITY_INFO);
		return "index?faces-redirect=true";
	}

	/*
	 * Default methods (getters and setters)
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}

package br.edu.ifpb.projeto.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.project.util.Application;
import br.edu.ifpb.projeto.dao.UsuarioDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Usuario;

@ManagedBean(name = "usuarioCadastroBean")
@ViewScoped
public class UsuarioCadastroBean {
	private Usuario usuario;
	private String nome;
	private String email;
	private String senha;
	private String tipo;

	/*
	 * Realiza o cadastro do usuário
	 */
	public String cadastrar() {

		if (this.tipo.equals("aluno")) {
			this.usuario = new Aluno();
		} else if (this.tipo.equals("empresa")) {
			this.usuario = new Empresa();
		}

		this.usuario.setNome(this.nome);
		this.usuario.setEmail(this.email);
		this.usuario.setSenha(this.senha);

		UsuarioDAO dao = new UsuarioDAO();
		dao.beginTransaction();
		dao.insert(this.usuario);
		dao.commit();

		Application.addMessage("Cadastro realizado com sucesso!", FacesMessage.SEVERITY_INFO);
		return "/view/index?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

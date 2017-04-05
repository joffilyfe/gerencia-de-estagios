package br.edu.ifpb.projeto.bean;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.project.util.App;
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
			App.addMessage("E-mail ou senha incorretos", FacesMessage.SEVERITY_ERROR);
			return "/view/login?faces-redirect=true";
		}
		// Adiciona a classe usuario 
		this.setValueOf("#{sessionScope.usuarioLogado}", Usuario.class, this.usuario);
		
		App.addMessage("Login realizado com sucesso", FacesMessage.SEVERITY_INFO);

		return "/view/index?faces-redirect=true";
	}

	/*
	 * Realiza o logout do usuário
	 */
	public String logout() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
		App.addMessage("Logout realizado com sucesso", FacesMessage.SEVERITY_INFO);
		return "/view/index?faces-redirect=true";
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
	
	
	// Metodos da Classe GenericBean do professor fred
	
	/** Devolve um objeto acessivel a partir de uma expressao JSF-EL.
	 * @param elExpression A expressao EL de acesso ao objeto.
	 * @param clazz A classe deste objeto.
	 * @return O objeto acessivel pela EL.
	 */
	public Object getValueOf(String elExpression, Class<?> clazz) {
		FacesContext current = FacesContext.getCurrentInstance();
		ELContext elContext = current.getELContext();
		Application app = current.getApplication();
		ExpressionFactory fac = app.getExpressionFactory();
		ValueExpression ve = fac.createValueExpression(elContext, elExpression, clazz);
		return ve.getValue(current.getELContext());
	}

	/** Modifica um objeto acessivel a partir de uma expressao JSF-EL.
	 * @param elExpression A expressao EL de acesso ao objeto.
	 * @param clazz A classe deste objeto.
	 * @param value O novo objeto.
	 */
	public void setValueOf(String elExpression, Class<?> clazz, Object value) {
		FacesContext current = FacesContext.getCurrentInstance();
		ELContext elContext = current.getELContext();
		Application app = current.getApplication();
		ExpressionFactory fac = app.getExpressionFactory();
		ValueExpression ve = fac.createValueExpression(elContext, elExpression, clazz);
		ve.setValue(current.getELContext(), value);		
	}
}

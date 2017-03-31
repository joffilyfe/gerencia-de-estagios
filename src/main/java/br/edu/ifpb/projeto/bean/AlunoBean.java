package br.edu.ifpb.projeto.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.project.util.Application;
import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.model.Aluno;

@ManagedBean(name = "alunoBean")
@ViewScoped
public class AlunoBean {

	@ManagedProperty(value = "#{usuarioBean}")
	private UsuarioBean usuario;
	private AlunoDAO alunoDao;
	private Aluno aluno;
	private int id;

	@PostConstruct
	public void init() {
		this.aluno = (Aluno) usuario.getUsuario();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String atualizar() {
		alunoDao = new AlunoDAO();

		alunoDao.beginTransaction();
		alunoDao.update(this.aluno);
		alunoDao.commit();

		Application.addMessage("Alterações realizadas com sucesso!", FacesMessage.SEVERITY_INFO);

		return "/view/index?faces-redirect=true";
	}

	public void verAluno() {
		System.out.println("Entrou!");
		alunoDao = new AlunoDAO();
		this.aluno = alunoDao.find(this.id);
	}

}

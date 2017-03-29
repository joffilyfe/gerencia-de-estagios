package br.edu.ifpb.projeto.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.model.Aluno;

@ManagedBean(name = "alunoBean")
@ViewScoped
public class AlunoBean {
	private AlunoDAO alunoDao;
	private Aluno aluno;
	private String email;
	private String nome;
	private String competencias;

	@PostConstruct
	public void init() {
		Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		UsuarioBean usuario = (UsuarioBean) map.get("usuarioBean");
		this.aluno = (Aluno) usuario.getUsuario();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getCompetencias() {
		return competencias;
	}

	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}

	public String atualizar() {

		alunoDao = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());

		alunoDao.beginTransaction();
		alunoDao.update(this.aluno);
		alunoDao.commit();

		return "/view/index?faces-redirect=true";
	}
}

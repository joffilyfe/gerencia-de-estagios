package br.edu.ifpb.projeto.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.project.util.App;
import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.dao.VagaAlunoDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Vaga;
import br.edu.ifpb.projeto.model.VagaAluno;

@ManagedBean(name = "vagaBean")
@ViewScoped
public class VagaBean {

	@ManagedProperty(value = "#{usuarioBean}")
	private UsuarioBean ubean;
	private Vaga vaga;
	private int id;

	public String candidatarVaga(Vaga vaga) {
		Aluno aluno = (Aluno) this.ubean.getUsuario();

		VagaDAO vagaDAO = new VagaDAO();

		if (aluno == null || vaga == null) {
			System.out.println("Algo nullo");
			App.addMessage("Não foi possível candidatar-se a vaga.", FacesMessage.SEVERITY_ERROR);
			return "/view/vaga/index.xhtml?faces-redirect=true";
		}

		VagaAlunoDAO vagaAlunoDAO = new VagaAlunoDAO();

		if (vagaAlunoDAO.findBy(aluno, vaga) != null) {
			App.addMessage("Você já está concorrendo a vaga", FacesMessage.SEVERITY_INFO);
			return String.format("/view/vaga/detalhes.xhtml?id=%d&faces-redirect=true", vaga.getId());
		}

		VagaAluno vagaAluno = new VagaAluno();
		vagaAluno.setAluno(aluno);
		vagaAluno.setVaga(vaga);

		aluno.addVagaAluno(vagaAluno);
		vaga.addVagaAluno(vagaAluno);

		// Commita o vaga aluno
		vagaAlunoDAO.beginTransaction();
		vagaAlunoDAO.insert(vagaAluno);
		vagaAlunoDAO.commit();

		// Commita a vaga
		vagaDAO.beginTransaction();
		vagaDAO.update(vaga);
		vagaDAO.commit();

		// Commita o aluno
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.beginTransaction();
		alunoDAO.update(aluno);
		alunoDAO.commit();

		App.addMessage(String.format("Parabéns você está concorrendo a vaga %s.", vaga.getTitulo()),
				FacesMessage.SEVERITY_INFO);
		return String.format("/view/vaga/detalhes.xhtml?id=%d&faces-redirect=true", vaga.getId());
	}

	public void carregaVaga() {
		VagaDAO dao = new VagaDAO();
		this.vaga = dao.find(this.id);
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UsuarioBean getUbean() {
		return ubean;
	}

	public void setUbean(UsuarioBean ubean) {
		this.ubean = ubean;
	}

}

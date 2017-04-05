package br.edu.ifpb.projeto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.model.Aluno;

@ManagedBean(name = "verAlunoBean")
@ViewScoped
public class VerAlunoBean {
	private AlunoDAO alunoDao;
	private Aluno aluno;
	private int id;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void verAluno() {
		alunoDao = new AlunoDAO();
		this.aluno = alunoDao.find(this.id);
	}

}

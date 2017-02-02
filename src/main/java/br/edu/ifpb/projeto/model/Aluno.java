package br.edu.ifpb.projeto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Aluno extends Usuario {
	private Integer matricula;
	private String competencias;
	
	private boolean estagiando=false;
	@OneToOne(mappedBy="aluno", targetEntity=Estagio.class, cascade=CascadeType.ALL)
	private Estagio estagio;
	@ManyToMany(mappedBy="alunos")
	private List<Vaga> vagas = new ArrayList<Vaga>();

	public Aluno() {}

	public boolean isEstagiando() {
		return estagiando;
	}

	public void setEstagiando(boolean estagiando) {
		this.estagiando = estagiando;
	}


	public Aluno(String nome, String email, Integer matricula) {
		super(nome, email);
		this.matricula = matricula;
	}

	public List<Vaga> getVagas() {
		return this.vagas;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getCompetencias() {
		return competencias;
	}

	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}

	@Override
	public String toString() {
		return "Aluno [nome=" + super.getNome() + ", email=" + super.getEmail() + ", matricula=" + matricula + "]";
	}
}

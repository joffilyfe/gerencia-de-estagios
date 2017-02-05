package br.edu.ifpb.projeto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Aluno extends Usuario {
	private Integer matricula;
	private String competencias;
	@ManyToMany(mappedBy = "alunos")
	private List<Vaga> vagas = new ArrayList<Vaga>();
	@OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Estagio> estagios = new ArrayList<Estagio>();

	public Aluno() {
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

	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}

	public void addEstagio(Estagio estagio) {
		this.estagios.add(estagio);
	}

	public boolean isEstagiando() {
		for (Estagio estagio : estagios) {
			if (!estagio.isEncerrado()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Aluno [nome=" + super.getNome() + ", email=" + super.getEmail() + ", matricula=" + matricula + "]";
	}
}

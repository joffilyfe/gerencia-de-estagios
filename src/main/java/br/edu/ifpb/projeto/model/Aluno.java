package br.edu.ifpb.projeto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Aluno extends Usuario {
	private Integer matricula;
	private String competencias;
	@OneToMany(mappedBy = "aluno")
	private List<VagaAluno> vagaAluno = new ArrayList<VagaAluno>();
	@OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Estagio> estagios = new ArrayList<Estagio>();
	@Transient
	private boolean admitido = false;

	public Aluno() {
	}

	public Aluno(String nome, String email, Integer matricula) {
		super(nome, email);
		this.matricula = matricula;
	}

	public List<Vaga> getVagas() {
		List<Vaga> vagas = new ArrayList<Vaga>();
		for (VagaAluno vaga : vagaAluno) {
			vagas.add(vaga.getVaga());
		}

		return vagas;
	}

	public void addVagaAluno(VagaAluno vagaAluno) {
		this.vagaAluno.add(vagaAluno);
	}

	public List<VagaAluno> getVagaAluno() {
		return this.vagaAluno;
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
			if (!estagio.isEncerrado() && estagio.isEditado()) {
				return true;
			}
		}
		return false;
	}

	public boolean isAdmitido() {
		return admitido;
	}

	public void setAdmitido(boolean admitido) {
		this.admitido = admitido;
	}

	@Override
	public String toString() {
		return "Aluno [nome=" + super.getNome() + ", email=" + super.getEmail() + ", matricula=" + matricula + "]";
	}
}

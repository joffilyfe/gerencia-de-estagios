package br.edu.ifpb.projeto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aluno {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private Integer matricula;
	private String competencias;
	// É preciso criar a entidade Estagio
	//private Estagio estagio;
	//É preciso criar a entity Vaga
	//private ArrayList<Vaga> vagas;
	
	public Aluno() {}

	public Aluno(String nome, String email, Integer matricula) {
		this.nome = nome;
		this.email = email;
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "Aluno [nome=" + nome + ", email=" + email + ", matricula=" + matricula + "]";
	}
}

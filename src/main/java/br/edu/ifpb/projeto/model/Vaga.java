package br.edu.ifpb.projeto.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Vaga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private String areaDeFormacao;
	private String setor;
	private Double valorDaBolsa;
	private Integer qtdVagas;
	private String beneficios;
	private Integer qtdAlunos;
	private String preRequisitos;
	private Date dataDivulgacao;
	private Date dataEntrevista;
	@ManyToMany
	private List<Aluno> alunos = new ArrayList<Aluno>();
	@ManyToOne
	private Empresa empresa;

	public Vaga() {
	}

	public Vaga(String descricao, Empresa empresa) {
		this.descricao = descricao;
		this.empresa = empresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAreaDeFormacao() {
		return areaDeFormacao;
	}

	public void setAreaDeFormacao(String areaDeFormacao) {
		this.areaDeFormacao = areaDeFormacao;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Double getValorDaBolsa() {
		return valorDaBolsa;
	}

	public void setValorDaBolsa(Double valorDaBolsa) {
		this.valorDaBolsa = valorDaBolsa;
	}

	public Integer getQtdVagas() {
		return qtdVagas;
	}

	public void setQtdVagas(Integer qtdVagas) {
		this.qtdVagas = qtdVagas;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public Integer getQtdAlunos() {
		return qtdAlunos;
	}

	public void setQtdAlunos(Integer qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}

	public String getPreRequisitos() {
		return preRequisitos;
	}

	public void setPreRequisitos(String preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	public Date getDataDivulgacao() {
		return dataDivulgacao;
	}

	public void setDataDivulgacao(Date dataDivulgacao) {
		this.dataDivulgacao = dataDivulgacao;
	}

	public Date getDataEntrevista() {
		return dataEntrevista;
	}

	public void setDataEntrevista(Date dataEntrevista) {
		this.dataEntrevista = dataEntrevista;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void addAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Vaga [descricao=" + descricao + ", empresa=" + empresa + ", habilitada=" + "]";
	}

}

package br.edu.ifpb.projeto.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vaga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String descricao;
	private String areaDeFormacao;
	private String setor;
	private String horarioEntrada;
	private String horarioSaida;
	private Double valorDaBolsa;
	private Integer qtdVagas;
	private String beneficios;
	private String atividades;

	private Integer qtdAlunos;
	private Date dataDivulgacaoInicio;
	private Date dataDivulgacaoFim;
	private Date dataEntrevista;
	@OneToMany(mappedBy = "vaga")
	private List<VagaAluno> vagaAluno = new ArrayList<VagaAluno>();
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public String getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(String horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public String getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(String horarioSaida) {
		this.horarioSaida = horarioSaida;
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

	public Date getDataDivulgacaoInicio() {
		return dataDivulgacaoInicio;
	}

	public void setDataDivulgacaoInicio(Date dataDivulgacaoInicio) {
		this.dataDivulgacaoInicio = dataDivulgacaoInicio;
	}

	public Date getDataDivulgacaoFim() {
		return dataDivulgacaoFim;
	}

	public void setDataDivulgacaoFim(Date dataDivulgacaoFim) {
		this.dataDivulgacaoFim = dataDivulgacaoFim;
	}

	public Date getDataEntrevista() {
		return dataEntrevista;
	}

	public void setDataEntrevista(Date dataEntrevista) {
		this.dataEntrevista = dataEntrevista;
	}

	public List<Aluno> getAlunos() {
		List<Aluno> alunos = new ArrayList<Aluno>();

		for (VagaAluno va : this.vagaAluno) {
			alunos.add(va.getAluno());
		}

		return alunos;
	}

	public void addVagaAluno(VagaAluno vagaAluno) {
		this.vagaAluno.add(vagaAluno);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

	public List<VagaAluno> getVagaAluno() {
		return vagaAluno;
	}

	public void setVagaAluno(List<VagaAluno> vagaAluno) {
		this.vagaAluno = vagaAluno;
	}

	@Override
	public String toString() {
		return "Vaga [descricao=" + descricao + ", empresa=" + empresa + ", habilitada=" + "]";
	}

}

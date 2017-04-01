package br.edu.ifpb.projeto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.EstagioDAO;
import br.edu.ifpb.projeto.dao.VagaAlunoDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Estagio;
import br.edu.ifpb.projeto.model.Vaga;
import br.edu.ifpb.projeto.model.VagaAluno;

@ManagedBean(name = "coordenadorBean")
@SessionScoped
public class CoordenadorBean {

	private List<Aluno> alunos;
	private List<Empresa> empresas;
	private List<Estagio> estagios;
	private List<Vaga> vagas;

	private Aluno aluno;
	private Vaga vaga;
	private Estagio estagio;

	// getters and setters

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public String candidatos(Vaga vaga) {
		this.vaga = vaga;
		return "/view/coordenador/listaCandidatos?faces-redirect=true";
	}

	// listagens

	public void listarEmpresas() {
		EmpresaDAO empresaDao = new EmpresaDAO();
		this.empresas = empresaDao.findAll();
	}

	public void listarVagas() {
		VagaDAO vagaDao = new VagaDAO();
		this.vagas = vagaDao.findAll();
	}

	public void listarEstagios() {
		EstagioDAO estagioDao = new EstagioDAO();
		this.estagios = estagioDao.getAllActive();
	}

	// preRenderView do listar candidatos
	public void listarCandidatos() {
		this.alunos = new ArrayList<Aluno>();

		VagaAlunoDAO vagaAlunoDAO = new VagaAlunoDAO();
		List<VagaAluno> vagaAlunos = vagaAlunoDAO.findBy(this.vaga);

		if (vagaAlunos != null) {
			for (VagaAluno vagaAluno : vagaAlunos) {
				Aluno aluno = vagaAluno.getAluno();

				// Se aluno estiver estagiando e estagiando nao eh candidato
				if (!aluno.isEstagiando()) {
					this.alunos.add(aluno);
				}
			}
		}
	}

	// metodos para operações

	public String transformaEstagio(Aluno aluno) {
		EstagioDAO estagioDao = new EstagioDAO();
		AlunoDAO alunoDao = new AlunoDAO();

		Estagio estagio = estagioDao.getBy(this.vaga, aluno);
		
		if (estagio != null) {
			estagio.setEditado(true);
			estagioDao.beginTransaction();
			estagioDao.update(estagio);
			estagioDao.commit();

			alunoDao.beginTransaction();
			aluno.addEstagio(estagio);
			alunoDao.update(aluno);
			alunoDao.commit();
		}
		
		return "/view/coordenador/listaEmpresas?faces-redirect=true";
	}

	public String habilitarEmpresa(Empresa empresa) {
		EmpresaDAO empresaDao = new EmpresaDAO();

		empresa.setHabilitada(true);

		empresaDao.beginTransaction();
		empresaDao.update(empresa);
		empresaDao.commit();

		return "/view/coordenador/listaEmpresas?faces-redirect=true";
	}

	public String desabilitarEmpresa(Empresa empresa) {
		EmpresaDAO empresaDao = new EmpresaDAO();

		empresa.setHabilitada(false);

		empresaDao.beginTransaction();
		empresaDao.update(empresa);
		empresaDao.commit();

		return "/view/coordenador/listaEmpresas?faces-redirect=true";
	}

	public String editarEstagio(Estagio e) {
		this.estagio = e;

		return "/view/coordenador/editarEstagio?faces-redirect=true";
	}

	public String salvarEstagio() {
		EstagioDAO estagioDao = new EstagioDAO();

		estagioDao.beginTransaction();
		estagioDao.update(this.estagio);
		estagioDao.commit();

		return "/view/coordenador/listaEstagios?faces-redirect=true";
	}
}

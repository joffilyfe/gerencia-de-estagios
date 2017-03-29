package br.edu.ifpb.projeto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.EstagioDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.VagaAlunoDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Estagio;
import br.edu.ifpb.projeto.model.Vaga;
import br.edu.ifpb.projeto.model.VagaAluno;

@ManagedBean(name="coordenadorBean")
@SessionScoped
public class CoordenadorBean {
	
	private EmpresaDAO empresaDAO = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
	private VagaDAO vagaDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());
	private AlunoDAO alunoDAO = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
	private VagaAlunoDAO vagaAlunoDAO = new VagaAlunoDAO(PersistenceUtil.getCurrentEntityManager());
	private EstagioDAO estagioDAO = new EstagioDAO(PersistenceUtil.getCurrentEntityManager());
	
	Vaga vaga;
	Aluno aluno;
	
	private List<Aluno> alunos;
	
	private boolean obrigatorio;
	private boolean encerrado;
	

	List<Empresa> empresas = new ArrayList<Empresa>();	
	private List<Vaga>  vagas;
	private List<Estagio> estagios;

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	public boolean isObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public boolean isEncerrado() {
		return encerrado;
	}

	public void setEncerrado(boolean encerrado) {
		this.encerrado = encerrado;
	}

	
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
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
	
	
	public void listarVagas() {
		VagaDAO dao = new VagaDAO();
		this.vagas = dao.findAll();
	}

	public void listarEstagios() {
		EstagioDAO dao = new EstagioDAO();
		this.estagios = dao.getAllActive();
	}
	
	
	// ação ao clickar no titulo da vaga 
	public String detalhesVaga(Vaga vaga){
		this.vaga = new Vaga();
		
		this.vaga=vaga;
	
		
			
		
		return "/view/coordenador/listar_candidatos?faces-redirect=true";
	}
	
	//preRenderView do listar candidatos 
	public void listarCandidatos(){
		
		this.alunos=new ArrayList<Aluno>();
		
		
		List<VagaAluno> vagaAlunos = vagaAlunoDAO.findBy(this.vaga);

		// Setando os alunos como admitidos
		if (vagaAlunos != null) {
			for (VagaAluno vagaAluno : vagaAlunos) {
				Aluno aluno = vagaAluno.getAluno();

				if (vagaAluno.isAdmitido()) {
					aluno.setAdmitido(true);
				}

				this.alunos.add(aluno);
			}
		}
		
	
		
	}
	
	
	// método ao clickar no transforma em estágio na lista de candidatos
	public String transformaEstagio(Aluno aluno,Vaga vaga){
			this.aluno=new Aluno();
			this.vaga = new Vaga();
			this.aluno=aluno;
			this.vaga=vaga;
		
		
		
		return "/view/coordenador/editar_estagio?faces-redirect=true";
	}
	
	public String salvar(){
		
		Estagio estagio= estagioDAO.getBy(this.vaga,this.aluno);
			
			if(isObrigatorio()==true){
				
				estagio.setObrigatorio(true);
				
			}
			if(isObrigatorio()==false){
				
				estagio.setObrigatorio(false);
				
			}
			
			if(isEncerrado()==true){
				
				estagio.setEncerrado(true);
			}
			
			if(isEncerrado()==false){
				
				estagio.setEncerrado(false);
				
			}
			
			estagioDAO.beginTransaction();
			estagioDAO.update(estagio);
			estagioDAO.commit();
			
			alunoDAO.beginTransaction();
			aluno.addEstagio(estagio);
			alunoDAO.update(aluno);
			alunoDAO.commit();
		
		
		
		
		return "/view/coordenador/listar_estagios?faces-redirect=true";
	}

	public void listarEmpresas(){
		EmpresaDAO empresaDao = new EmpresaDAO();
		this.empresas = empresaDao.findAll();
	}
	
	public String habilitarEmpresa(Empresa e){
		EmpresaDAO empresaDao = new EmpresaDAO();
		
		Empresa empresa = empresaDao.find(e.getId());
		empresa.setHabilitada(true);
		
		empresaDao.beginTransaction();
		empresaDao.update(empresa);
		empresaDao.commit();
		
		return "/view/coordenador/listaEmpresas?faces-redirect=true";
	}
	
	public String desabilitarEmpresa(Empresa e){
		EmpresaDAO empresaDao = new EmpresaDAO();
		
		Empresa empresa = empresaDao.find(e.getId());
		empresa.setHabilitada(false);
		
		empresaDao.beginTransaction();
		empresaDao.update(empresa);
		empresaDao.commit();
		
		return "/view/coordenador/listaEmpresas?faces-redirect=true";
	}
	
}

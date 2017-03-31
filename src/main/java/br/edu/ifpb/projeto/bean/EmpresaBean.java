package br.edu.ifpb.projeto.bean;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;


import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Vaga;

@ManagedBean(name="empresaBean")
@ViewScoped
public class EmpresaBean {

	private Empresa empresa = new Empresa();
	private Vaga vaga = new Vaga();
	private List<Vaga> vagas  = new ArrayList<Vaga>();
	private List<Empresa> empresas = new ArrayList<Empresa>();
	private int id;
	
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}


	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	
	public String cadastrar()
	{
		vaga.setEmpresa(empresa);
		
		VagaDAO tDao = new VagaDAO();
		tDao.beginTransaction();
		tDao.insert(vaga);
		tDao.commit();

		
		vagas.add(vaga);
		return "/view/empresa/minhas_vagas?faces-redirect=true";

		
	}
	
	public String editar()
	{
		EmpresaDAO tDao = new EmpresaDAO();
		tDao.beginTransaction();
		tDao.update(empresa);
		tDao.commit();

		return "/view/index?faces-redirect=true";
	}
	
	public void listarVagas(ComponentSystemEvent event) {
		
		EmpresaDAO dao = new EmpresaDAO();
	    
	    empresa = dao.getById(empresa.getId());
	    this.vagas = empresa.getVagas();
	}
	
	public void listarEmpresas(ComponentSystemEvent event) {
		
		EmpresaDAO dao = new EmpresaDAO();
	    
	    this.empresas = dao.findAll();
	   
	}
	
	public String selecionar(Empresa empresa){
		this.empresa = empresa;
		loadFlash();
		return "/view/empresa/perfil_publico?faces-redirect=true&id="+empresa.getId();
	}

	public String candidatos(Vaga vaga) {
		this.vaga = vaga;
		loadFlash();
		return "/view/empresa/candidatos?faces-redirect=true";
	}
	
	private void loadFlash(){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("empresa", empresa);
		flash.put("vaga", vaga);
	}
	
	public void unloadFlash(){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		this.setEmpresa((Empresa) flash.get(empresa));
		this.setVaga((Vaga) flash.get("vaga"));
		
	}

    public void init()
	{
		Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		UsuarioBean user = (UsuarioBean) map.get("usuarioBean");
		if(user.getUsuario() != null) {
			empresa = (Empresa) user.getUsuario();
		}
		else {
			EmpresaDAO dao = new EmpresaDAO();
		    empresa = dao.getById(id);
		}
	}

	
	

}
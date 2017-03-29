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
	private int id;
	
	

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
		
		System.out.println("entrou no editar");

		return "/view/index?faces-redirect=true";
	}
	

	public void listarVagas(ComponentSystemEvent event) {
		
		EmpresaDAO dao = new EmpresaDAO();
	    
	    empresa = dao.getById(empresa.getId());
	    this.vagas = empresa.getVagas();
	}
	

	@PostConstruct
    public void init()
	{
		Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		UsuarioBean user = (UsuarioBean) map.get("usuarioBean");
		empresa = (Empresa) user.getUsuario();
	}

	
	

}

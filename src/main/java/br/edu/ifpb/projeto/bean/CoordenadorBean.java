package br.edu.ifpb.projeto.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.model.Empresa;

@ManagedBean(name = "coordenadorBean")
@ViewScoped
public class CoordenadorBean {
	List<Empresa> empresas = new ArrayList<Empresa>();
	
	public void listarEmpresas(){
		EmpresaDAO empresaDao = new EmpresaDAO();
		this.empresas = empresaDao.findAll();
	}
	
	public String habilitarEmpresa(Empresa empresa){
		EmpresaDAO empresaDao = new EmpresaDAO();
		
		empresa.setHabilitada(true);
		
		empresaDao.beginTransaction();
		empresaDao.update(empresa);
		empresaDao.commit();
		
		return "/view/admin/listaEmpresas?faces-redirect=true";
	}
	
	public String desabilitarEmpresa(Empresa empresa){
		EmpresaDAO empresaDao = new EmpresaDAO();
		
		empresa.setHabilitada(false);
		
		empresaDao.beginTransaction();
		empresaDao.update(empresa);
		empresaDao.commit();
		
		return "/view/admin/listaEmpresas?faces-redirect=true";
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
}

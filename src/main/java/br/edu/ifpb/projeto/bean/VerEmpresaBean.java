package br.edu.ifpb.projeto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.model.Empresa;

@ManagedBean(name = "verEmpresaBean")
@ViewScoped
public class VerEmpresaBean {
	private Empresa empresa;
	private int id;

	public void verPerfil() {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		this.empresa = empresaDAO.find(this.id);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

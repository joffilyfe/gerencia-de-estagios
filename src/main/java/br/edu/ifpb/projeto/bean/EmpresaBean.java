package br.edu.ifpb.projeto.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Vaga;

@ManagedBean(name="empresaBean")
@ViewScoped
public class EmpresaBean {
	private Empresa empresa;
	private Vaga vaga = new Vaga();
	private List<Vaga> vagas;

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
		VagaDAO tDao = new VagaDAO();
		tDao.beginTransaction();
		tDao.insert(vaga);
		tDao.commit();
		return "/view/index?faces-redirect=true";
	}
	
	public String editar()
	{
		EmpresaDAO tDao = new EmpresaDAO();
		tDao.beginTransaction();
		tDao.update(empresa);
		tDao.commit();

		return "/view/index?faces-redirect=true";
	}
}

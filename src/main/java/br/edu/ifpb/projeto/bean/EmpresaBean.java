package br.edu.ifpb.projeto.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Vaga;

@ManagedBean(name="empresaBean")
@ViewScoped
public class EmpresaBean {

	private Empresa empresa = new Empresa();
	private Vaga vaga = new Vaga();

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
	
	@PostConstruct
    public void init()
	{
		Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		UsuarioBean user = (UsuarioBean) map.get("usuarioBean");
		empresa = (Empresa) user.getUsuario();
	}
}

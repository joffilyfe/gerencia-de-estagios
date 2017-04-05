package br.edu.ifpb.projeto.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifpb.project.util.App;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Vaga;

@ManagedBean(name = "homeBean")
public class HomeBean {

	private List<Vaga> vagas;

	public void listarVagas() {
		VagaDAO dao = new VagaDAO();
		this.vagas = dao.latest();
		
		String menssagem = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("message");

		if (menssagem != null) {
			App.addMessage(menssagem, FacesMessage.SEVERITY_INFO);
		}
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

}

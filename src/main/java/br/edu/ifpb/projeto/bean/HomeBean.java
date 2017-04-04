package br.edu.ifpb.projeto.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Vaga;

@ManagedBean(name = "homeBean")
public class HomeBean {

	private List<Vaga> vagas;

	public void listarVagas() {
		VagaDAO dao = new VagaDAO();
		this.vagas = dao.latest();
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

}

package br.edu.ifpb.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.projeto.model.Usuario;
import br.edu.ifpb.projeto.model.Vaga;

public class VagaDAO extends GenericDAO<Vaga, Integer> {
	public VagaDAO(EntityManager em) {
		super(em);
	}

	public VagaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
	
public List<Vaga> ProcuraCandidatos(){
		
		Query q = this.getEntityManager().createQuery("SELECT u FROM Vaga u");
		
		@SuppressWarnings("unchecked")
		List<Vaga> vagas=q.getResultList();
		
		
		if(vagas.isEmpty()){return null;}
		
		return  vagas;
	}
}

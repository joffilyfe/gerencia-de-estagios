package br.edu.ifpb.projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.projeto.model.Empresa;

public class EmpresaDAO extends GenericDAO<Empresa, Integer> {

	public EmpresaDAO(EntityManager em) {
		super(em);
	}

	public EmpresaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
	
	public Empresa getById(int id) {
	    Query q = this.entityManager.createQuery("SELECT e FROM Empresa e WHERE e.id = :id");
	    q.setParameter("id",id);
	    
	    Empresa empresa = (Empresa) q.getSingleResult();

	    return empresa;
	  }
}

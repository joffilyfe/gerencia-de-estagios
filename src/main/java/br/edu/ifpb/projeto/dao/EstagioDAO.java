package br.edu.ifpb.projeto.dao;

import javax.persistence.EntityManager;

import br.edu.ifpb.projeto.model.Estagio;

public class EstagioDAO extends GenericDAO<Estagio, Integer>{
	public EstagioDAO(EntityManager em) {
		super(em);
	}

	public EstagioDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
}

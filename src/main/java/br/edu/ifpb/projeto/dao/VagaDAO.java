package br.edu.ifpb.projeto.dao;

import javax.persistence.EntityManager;

import br.edu.ifpb.projeto.model.Vaga;

public class VagaDAO extends GenericDAO<Vaga, Integer> {
	public VagaDAO(EntityManager em) {
		super(em);
	}

	public VagaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
}

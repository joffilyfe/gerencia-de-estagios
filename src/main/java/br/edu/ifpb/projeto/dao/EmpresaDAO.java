package br.edu.ifpb.projeto.dao;

import javax.persistence.EntityManager;

import br.edu.ifpb.projeto.model.Empresa;

public class EmpresaDAO extends GenericDAO<Empresa, Integer> {

	public EmpresaDAO(EntityManager em) {
		super(em);
	}

	public EmpresaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
}

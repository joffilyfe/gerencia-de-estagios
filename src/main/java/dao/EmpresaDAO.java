package dao;

import javax.persistence.EntityManager;

import model.Empresa;

public class EmpresaDAO extends GenericDAO<Empresa, Integer> {

	public EmpresaDAO(EntityManager em) {
		super(em);
	}

	public EmpresaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
}

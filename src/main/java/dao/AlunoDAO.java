package dao;

import javax.persistence.EntityManager;

import model.Aluno;

public class AlunoDAO extends GenericDAO<Aluno, Integer> {

	public AlunoDAO(EntityManager em) {
		super(em);
	}

	public AlunoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

}

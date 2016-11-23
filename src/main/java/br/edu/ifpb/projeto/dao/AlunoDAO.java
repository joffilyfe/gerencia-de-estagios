package br.edu.ifpb.projeto.dao;

import javax.persistence.EntityManager;

import br.edu.ifpb.projeto.model.Aluno;

public class AlunoDAO extends GenericDAO<Aluno, Integer> {

	public AlunoDAO(EntityManager em) {
		super(em);
	}

	public AlunoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
}

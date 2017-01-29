package br.edu.ifpb.projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.projeto.model.Aluno;

public class AlunoDAO extends GenericDAO<Aluno, Integer> {

	public AlunoDAO(EntityManager em) {
		super(em);
	}

	public AlunoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
	
	public  Aluno findById(String id) {
        Query q = this.getEntityManager().createQuery("select a from Aluno a where a.id = :id");
        q.setParameter("id", id);
        Aluno a = null;
        try {
            a = (Aluno) q.getSingleResult();
        }catch (NoResultException e) {       
        }
        return a;
    }
}

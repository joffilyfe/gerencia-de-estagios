package br.edu.ifpb.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Vaga;

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
	public List<Aluno> ProcuraAlunos(){
		
		Query q = this.getEntityManager().createQuery("SELECT u FROM Aluno u");
		
		@SuppressWarnings("unchecked")
		List<Aluno> alunos=q.getResultList();
		
		
		if(alunos.isEmpty()){return null;}
		
		return  alunos;
	}

}

package br.edu.ifpb.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.projeto.model.Usuario;

import br.edu.ifpb.projeto.model.Aluno;

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


	public List<Vaga> getByAluno(Aluno aluno) {
		Query q = this.entityManager.createQuery("SELECT v FROM Vaga v JOIN v.alunos a WHERE a.id = :id");
		q.setParameter("aluno", aluno.getId());

		@SuppressWarnings("unchecked")
		List<Vaga> vagas = q.getResultList();

		if (vagas.isEmpty()) {
			return null;
		}

		return vagas;
	}

	public Vaga getByIdAndAluno(Aluno aluno, Vaga vaga) {
		Query q = this.entityManager
				.createQuery("SELECT v FROM Vaga v JOIN v.alunos a WHERE a.id = :aluno and v.id = :vaga");
		q.setParameter("aluno", aluno.getId());
		q.setParameter("vaga", vaga.getId());

		@SuppressWarnings("unchecked")
		List<Vaga> vagas = q.getResultList();

		if (vagas.isEmpty()) {
			return null;
		}

		return vagas.get(0);

	}
}

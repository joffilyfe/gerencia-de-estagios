package br.edu.ifpb.projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Vaga;
import br.edu.ifpb.projeto.model.VagaAluno;

public class VagaAlunoDAO extends GenericDAO<VagaAluno, Integer> {

	public VagaAlunoDAO(EntityManager em) {
		super(em);
	}

	public VagaAlunoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public VagaAluno findBy(Aluno aluno, Vaga vaga) {

		Query q = this.getEntityManager()
				.createQuery("SELECT va FROM VagaAluno va WHERE aluno_id = :aluno and vaga_id = :vaga");
		q.setParameter("aluno", aluno.getId());
		q.setParameter("vaga", vaga.getId());

		if (q.getResultList().isEmpty()) {
			return null;
		}

		return (VagaAluno) q.getResultList().get(0);
	}

}
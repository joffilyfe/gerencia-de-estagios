package br.edu.ifpb.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Estagio;
import br.edu.ifpb.projeto.model.Vaga;

public class EstagioDAO extends GenericDAO<Estagio, Integer> {
	public EstagioDAO(EntityManager em) {
		super(em);
	}

	public EstagioDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public Estagio getBy(Vaga vaga, Aluno aluno) {
		Query q = this.entityManager.createQuery("SELECT e FROM Estagio e WHERE vaga_id = :vaga and aluno_id = :aluno");
		q.setParameter("aluno", aluno.getId());
		q.setParameter("vaga", vaga.getId());

		List<Estagio> estagios = q.getResultList();

		if (estagios.isEmpty()) {
			return null;
		}

		return estagios.get(0);
	}

	public List<Estagio> getAllByEncerrado(boolean status) {
		Query q = this.entityManager
				.createQuery("SELECT e FROM Estagio e WHERE e.editado = true and e.encerrado = :status");

		q.setParameter("status", status);
		List<Estagio> estagios = q.getResultList();

		if (estagios.isEmpty()) {
			return null;
		}

		return estagios;
	}
}

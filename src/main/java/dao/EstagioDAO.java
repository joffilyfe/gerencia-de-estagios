package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Aluno;
import model.Estagio;
import model.Vaga;

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

	public List<Estagio> getAllActive() {
		Query q = this.entityManager.createQuery("SELECT e FROM Estagio e WHERE e.editado = true");

		List<Estagio> estagios = q.getResultList();

		if (estagios.isEmpty()) {
			return null;
		}

		return estagios;
	}
}

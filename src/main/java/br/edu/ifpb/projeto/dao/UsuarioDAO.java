package br.edu.ifpb.projeto.dao;

import javax.persistence.EntityManager;

import br.edu.ifpb.projeto.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> {

	public UsuarioDAO(EntityManager em) {
		super(em);
	}

	public UsuarioDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
}

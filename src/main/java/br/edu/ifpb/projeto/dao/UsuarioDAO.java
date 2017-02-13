package br.edu.ifpb.projeto.dao;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;

import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> {

	public UsuarioDAO(EntityManager em) {
		super(em);
	}

	public UsuarioDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public Usuario getByCredentials(String email, String senha) {
		Query q = this.entityManager.createNamedQuery("authorize user");

		try {
			String hash = DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-256").digest(senha.getBytes("UTF-8")));
			q.setParameter("email", email);
			q.setParameter("senha", hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = q.getResultList();

		if (usuarios.isEmpty()) {
			return null;
		}

		return usuarios.get(0);
	}

	
	
	public  Usuario findById(String id) {
        Query q = this.getEntityManager().createQuery("select a from Usuario a where a.id = :id");
        q.setParameter("id", id);
        Usuario a = null;
        try {
            a = (Usuario) q.getSingleResult();
        }catch (NoResultException e) {       
        }
        return a;
    }
	
	
	
	
	
}




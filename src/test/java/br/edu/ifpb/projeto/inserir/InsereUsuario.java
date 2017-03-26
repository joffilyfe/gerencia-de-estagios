package br.edu.ifpb.projeto.inserir;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.ManagedEMContext;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.UsuarioDAO;
import model.Aluno;
import model.Empresa;
import model.Usuario;

public class InsereUsuario {
	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeClass
	public static void init() {
		PersistenceUtil.createEntityManagerFactory("estagiarios");
		emf = PersistenceUtil.getEntityManagerFactory();
		ManagedEMContext.bind(emf, emf.createEntityManager());
		System.out.println("init()");
	}

	@AfterClass
	public static void destroy() {
		if (emf != null) {
			emf.close();
			System.out.println("destroy()");
		}
	}

	@Before
	public void initEM() {
		em = emf.createEntityManager();
	}
	
	
	@Test
	public void testInsereUsuarios(){
		
		try{
			Aluno a1 = new Aluno();
			a1.setNome("Aluno");
			a1.setEmail("aluno@email.com");
			a1.setSenha("123456");
			a1.setCoordenador(false);
			
			AlunoDAO adao = new AlunoDAO(em);
			adao.beginTransaction();
			adao.insert(a1);
			adao.commit();
			
			Usuario u2 = new Usuario();
			u2.setNome("Coordenador");
			u2.setEmail("coordenador@email.com");
			u2.setSenha("123456");
			u2.setCoordenador(true);
			
			UsuarioDAO udao = new UsuarioDAO(em);
			udao.beginTransaction();
			udao.insert(u2);
			udao.commit();
			
			Empresa e1 = new Empresa();
			e1.setNome("Empresa");
			e1.setEmail("empresa@email.com");
			e1.setSenha("123456");
			e1.setCoordenador(false);
			
			EmpresaDAO edao = new EmpresaDAO(em);
			edao.beginTransaction();
			edao.insert(e1);
			edao.commit();
			
			
			
			
		}catch (Exception e) {
			Assert.fail("Erro de BD: " + e);
		}
		
	}

}

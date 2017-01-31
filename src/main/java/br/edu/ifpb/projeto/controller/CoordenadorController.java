package br.edu.ifpb.projeto.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.UsuarioDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Usuario;
import br.edu.ifpb.projeto.model.Vaga;

public class CoordenadorController extends ApplicationController {
	
	public CoordenadorController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	// DAOS
		private EmpresaDAO empresaDAO = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
		private AlunoDAO alunoDAO = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
		private UsuarioDAO usuarioDAO = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());
		private VagaDAO vagasDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());
		
		
	
	public RequestDispatcher candidatos() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/usuario/candidatos/candidatos.jsp");
		
		
		List<Vaga> vagas= vagasDAO.ProcuraCandidatos();
		
		List<Aluno> a = null;
		
		for(int i=0;i<vagas.size();i++){
			Vaga vaga=vagas.get(i);
			a=vaga.getAlunos();
			
			
		}

		request.setAttribute("alunos", a);


		return dispatcher;
	}
	public RequestDispatcher FichaAluno() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/usuario/candidatos/fichaAluno.jsp");
		
		Aluno a= alunoDAO.findById(request.getParameter("id"));
		
		request.setAttribute("aluno", a);
		
	
		
		Usuario u=usuarioDAO.findById(request.getParameter("id"));
		
		request.setAttribute("usuario",u);
		
		


		return dispatcher;
	}
	
	public RequestDispatcher FichaEmpresa() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/usuario/candidatos/fichaEmpresa.jsp");
		
	
		


		return dispatcher;
	}
	
	public RequestDispatcher Oferta() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/usuario/candidatos/oferta.jsp");
		
	
		


		return dispatcher;
	}
	

}

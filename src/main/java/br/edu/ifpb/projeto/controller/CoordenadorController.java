package br.edu.ifpb.projeto.controller;

import java.io.IOException;

import java.util.List;

import java.util.ArrayList;


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



import javax.servlet.http.HttpSession;

import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Usuario;

public class CoordenadorController extends ApplicationController{
	private EmpresaDAO empresaDAO = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
		

	public CoordenadorController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	

	// DAOS
		
		private AlunoDAO alunoDAO = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
		private UsuarioDAO usuarioDAO = new UsuarioDAO(PersistenceUtil.getCurrentEntityManager());
		private VagaDAO vagasDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());
		
		
	
		public RequestDispatcher index() throws IOException {
			RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/index.jsp");
			HttpSession session = request.getSession();
			this.request.setAttribute("empresas", empresaDAO.findAll());
			
			if (session.getAttribute("usuario") == null  || ((Usuario) session.getAttribute("usuario")).isCoordenador() == false) {
				response.sendRedirect(request.getServletContext().getContextPath());
			}
			return dispatcher;
		}	
		
		
		
	public RequestDispatcher candidatos() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/candidatos.jsp");
		
		
		List<Vaga> vagas= vagasDAO.ProcuraCandidatos();
		List<String> b= new ArrayList<String>();
		b=null;
		List<Aluno> a = null;
		
		if(vagas==null){
			request.setAttribute("alunos", a);
			return dispatcher;
		}
		else{
		
		for(int i=0;i<vagas.size();i++){
			Vaga vaga=vagas.get(i);
			a=vaga.getAlunos();
			
			
		}
		
		for(int j=0;j<a.size();j++){
			Aluno al=a.get(j);
			if(al.isEstagiando()==true){
			a.remove(j);
				
				
			}
		}
		
		request.setAttribute("alunos", a);
		return dispatcher;
		
		}
	}
	public RequestDispatcher FichaAluno() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/candidatos/fichaAluno.jsp");
		
		Aluno a= alunoDAO.findById(request.getParameter("id"));
		
		request.setAttribute("aluno", a);
		
	
		
		Usuario u=usuarioDAO.findById(request.getParameter("id"));
		
		request.setAttribute("usuario",u);
		
		


		return dispatcher;
	}
	
	public RequestDispatcher FichaEmpresa() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/candidatos/fichaEmpresa.jsp");
		
	
		


		return dispatcher;
	}
	
	public RequestDispatcher Oferta() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/candidatos/oferta.jsp");
		return dispatcher;
		
	
		

	}

	
	public RequestDispatcher habilitarempresa() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/habilitaEmpresas.jsp");
		HttpSession session = request.getSession();
		int idNumber; 
		this.request.setAttribute("empresas", empresaDAO.findAll());
		
		if (session.getAttribute("usuario") == null  || ((Usuario) session.getAttribute("usuario")).isCoordenador() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}
		if (request.getMethod().equals("POST")){
				if(request.getParameter("id").matches("^\\d+$")){
					idNumber = Integer.parseInt(request.getParameter("id"));
					Empresa empresa = empresaDAO.find(idNumber);
					
	
					
		
					if(request.getParameter("habilitar").equals("true")){
						empresa.setHabilitada(true);
					}else if(request.getParameter("habilitar").equals("false")){
						empresa.setHabilitada(false);
					}
		
					empresaDAO.beginTransaction();
					empresaDAO.update(empresa);
					empresaDAO.commit();
				} else{
					super.addFlashMessage("error", "Empresa não foi encontrada");
				}
			}
		
		
		return dispatcher;
	}
	
	
	
	
	public RequestDispatcher listarEmpresas() throws IOException{
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/listaEmpresas.jsp");
		HttpSession session = request.getSession();
		this.request.setAttribute("empresas", empresaDAO.findAll());
		

		if (session.getAttribute("usuario") == null  || ((Usuario) session.getAttribute("usuario")).isCoordenador() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}

		return dispatcher;
	}
	


}

	



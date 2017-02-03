package br.edu.ifpb.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.EstagioDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Estagio;
import br.edu.ifpb.projeto.model.Usuario;
import br.edu.ifpb.projeto.model.Vaga;

public class EmpresaController extends ApplicationController{
	private EmpresaDAO empresaDAO = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
	private VagaDAO vagaDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());
	private AlunoDAO alunoDAO = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
	private EstagioDAO estagioDAO = new EstagioDAO(PersistenceUtil.getCurrentEntityManager());
	
	public EmpresaController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public RequestDispatcher index() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/empresa/index.jsp");
		HttpSession session = request.getSession();
		this.request.setAttribute("vagas", vagaDAO.findAll());
		
		if (session.getAttribute("usuario") == null  || ((Usuario) session.getAttribute("usuario")).isEmpresa() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}

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
	
	public RequestDispatcher admitirCandidato() throws IOException{
		
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/listaCandidatos.jsp");
		HttpSession session = request.getSession();
		int idAluno, idVaga, idEmpresa;
		
		
		if (request.getMethod().equals("POST")){
			if(request.getParameter("idaluno").matches("^\\d+$")){
				idAluno = Integer.parseInt(request.getParameter("idaluno"));
				Aluno aluno = alunoDAO.find(idAluno);
				idVaga = Integer.parseInt(request.getParameter("idvaga"));
				Vaga vaga = vagaDAO.find(idVaga);
				idEmpresa = vaga.getEmpresa().getId();
				Empresa empresa = empresaDAO.find(idEmpresa);
				Estagio estagio = new Estagio(empresa,aluno,vaga);
				this.request.setAttribute("estagio", estagio);
				
				estagioDAO.beginTransaction();
				estagioDAO.insert(estagio);
				estagioDAO.commit();
				
				aluno.getVagas().remove(vaga);
				
				alunoDAO.beginTransaction();
				alunoDAO.update(aluno);
				alunoDAO.commit();
				
				vaga.getAlunos().remove(aluno);
				
				vagaDAO.beginTransaction();
				vagaDAO.update(vaga);
				vagaDAO.commit();

			}
		}
		
		if (session.getAttribute("usuario") == null  || ((Usuario) session.getAttribute("usuario")).isCoordenador() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}
		
		return dispatcher;
	}	
		
}
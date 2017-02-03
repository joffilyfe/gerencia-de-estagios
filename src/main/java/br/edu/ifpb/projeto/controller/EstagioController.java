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

public class EstagioController extends ApplicationController {

	private VagaDAO vagaDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());
	private AlunoDAO alunoDAO = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
	private EstagioDAO estagioDAO = new EstagioDAO(PersistenceUtil.getCurrentEntityManager());
	private EmpresaDAO empresaDAO = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());

	public EstagioController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public RequestDispatcher admitirCandidato() throws IOException{
		
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/vaga/listaCandidatos.jsp");
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
		
		if (session.getAttribute("usuario") == null  || ((Usuario) session.getAttribute("usuario")).isEmpresa() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}
		
		return dispatcher;
	}		
}

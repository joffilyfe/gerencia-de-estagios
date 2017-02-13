package br.edu.ifpb.projeto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.dao.EstagioDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.VagaAlunoDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Estagio;
import br.edu.ifpb.projeto.model.Vaga;
import br.edu.ifpb.projeto.model.VagaAluno;

public class EstagioController extends ApplicationController {

	private VagaDAO vagaDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());
	private AlunoDAO alunoDAO = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
	private EstagioDAO estagioDAO = new EstagioDAO(PersistenceUtil.getCurrentEntityManager());
	private VagaAlunoDAO vaDAO = new VagaAlunoDAO(PersistenceUtil.getCurrentEntityManager());

	public EstagioController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	/*
	 * Método responsável por admitir um candidato a uma vaga
	 */
	public RequestDispatcher admitirCandidato() throws IOException {

		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/vaga/listaCandidatos.jsp");
		int idAluno, idVaga;

		if (!super.canAccess("usuario", "Empresa")) {
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		if (request.getMethod().equals("POST")) {
			if (request.getParameter("idaluno").matches("^\\d+$")) {

				// Instancia aluno
				idAluno = Integer.parseInt(request.getParameter("idaluno"));
				Aluno aluno = alunoDAO.find(idAluno);

				// Instancia vaga
				idVaga = Integer.parseInt(request.getParameter("idvaga"));
				Vaga vaga = vagaDAO.find(idVaga);

				// Instancia VagaAluno
				vaDAO.beginTransaction();
				VagaAluno vagaAluno = vaDAO.findBy(aluno, vaga);
				vaDAO.commit();

				// Verifica se o aluno já foi admitido para a vaga
				if (vagaAluno != null && vagaAluno.isAdmitido()) {
					super.addFlashMessage("info", "Este aluno já foi admitido");
					response.sendRedirect(request.getServletContext().getContextPath());
					return dispatcher;
				}

				Estagio estagio = new Estagio(vaga.getEmpresa(), aluno, vaga);

				// Cria pedido de estágio
				estagioDAO.beginTransaction();
				estagioDAO.insert(estagio);
				estagioDAO.commit();

				// Seta o aluno como admitido
				vaDAO.beginTransaction();
				vagaAluno.setAdmitido(true);
				vaDAO.update(vagaAluno);
				vaDAO.commit();

				// Atualiza aluno
				alunoDAO.beginTransaction();
				alunoDAO.update(aluno);
				alunoDAO.commit();

				//Decrementa o número de vagas
				vaga.decrementarVaga();
				
				// Atualiza a vaga
				vagaDAO.beginTransaction();
				vagaDAO.update(vaga);
				vagaDAO.commit();

				this.request.setAttribute("estagio", estagio);
				super.addFlashMessage("success",
						"Parabéns, você selecionou o aluno " + aluno.getNome() + " para a vaga");

			}
		}

		return dispatcher;
	}
}

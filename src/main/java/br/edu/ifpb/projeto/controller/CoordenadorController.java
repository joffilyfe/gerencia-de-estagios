package br.edu.ifpb.projeto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.projeto.dao.AlunoDAO;
import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.EstagioDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.VagaAlunoDAO;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Aluno;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Estagio;
import br.edu.ifpb.projeto.model.Usuario;
import br.edu.ifpb.projeto.model.Vaga;
import br.edu.ifpb.projeto.model.VagaAluno;

public class CoordenadorController extends ApplicationController {
	private EmpresaDAO empresaDAO = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
	private VagaDAO vagaDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());
	private AlunoDAO alunoDAO = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
	private VagaAlunoDAO vagaAlunoDAO = new VagaAlunoDAO(PersistenceUtil.getCurrentEntityManager());
	private EstagioDAO estagioDAO = new EstagioDAO(PersistenceUtil.getCurrentEntityManager());

	public CoordenadorController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public RequestDispatcher index() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/index.jsp");
		HttpSession session = request.getSession();
		this.request.setAttribute("empresas", empresaDAO.findAll());

		if (session.getAttribute("usuario") == null
				|| ((Usuario) session.getAttribute("usuario")).isCoordenador() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}

		return dispatcher;
	}

	public RequestDispatcher habilitarempresa() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/habilitaEmpresas.jsp");
		HttpSession session = request.getSession();
		int idNumber;
		this.request.setAttribute("empresas", empresaDAO.findAll());

		if (session.getAttribute("usuario") == null
				|| ((Usuario) session.getAttribute("usuario")).isCoordenador() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}
		if (request.getMethod().equals("POST")) {
			if (request.getParameter("id").matches("^\\d+$")) {
				idNumber = Integer.parseInt(request.getParameter("id"));
				Empresa empresa = empresaDAO.find(idNumber);

				if (request.getParameter("habilitar").equals("true")) {
					empresa.setHabilitada(true);
				} else if (request.getParameter("habilitar").equals("false")) {
					empresa.setHabilitada(false);
				}

				empresaDAO.beginTransaction();
				empresaDAO.update(empresa);
				empresaDAO.commit();
			} else {
				super.addFlashMessage("error", "Empresa não foi encontrada");
			}
		}

		return dispatcher;
	}

	public RequestDispatcher listarEmpresas() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/listaEmpresas.jsp");
		HttpSession session = request.getSession();
		this.request.setAttribute("empresas", empresaDAO.findAll());

		if (session.getAttribute("usuario") == null
				|| ((Usuario) session.getAttribute("usuario")).isCoordenador() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}
		return dispatcher;
	}

	/*
	 * Método responsável por listar as vagas ativas para um coordenador
	 */
	public RequestDispatcher listarVagas() throws IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/coordenador/listar_vagas.jsp");

		if (!super.canAccess("usuario", "Usuario")) {
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		List<Vaga> vagas = vagaDAO.findAll();
		request.setAttribute("vagas", vagas);

		return dispatcher;
	}

	/*
	 * Método responsável por listar os alunos interessados em uma vaga
	 */
	public RequestDispatcher verAlunosCandidatos() throws IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/coordenador/listar_candidatos.jsp");

		// Verifica usuário
		if (!super.canAccess("usuario", "Usuario")) {
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		// Verifica id da vaga
		if (request.getParameter("id") == null || !request.getParameter("id").matches("^\\d+$")) {
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		vagaDAO.beginTransaction();
		Vaga vaga = vagaDAO.find(Integer.parseInt(request.getParameter("id")));
		vagaDAO.commit();

		// Verifica se vaga existe
		if (vaga == null) {
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		// List<Aluno> alunos = vaga.getAlunos();
		List<Aluno> candidatos = new ArrayList<Aluno>();
		List<VagaAluno> vagaAlunos = vagaAlunoDAO.findBy(vaga);

		// Setando os alunos como admitidos
		if (vagaAlunos != null) {
			for (VagaAluno vagaAluno : vagaAlunos) {
				Aluno aluno = vagaAluno.getAluno();

				if (vagaAluno.isAdmitido()) {
					aluno.setAdmitido(true);
				}

				candidatos.add(aluno);
			}
		}

		request.setAttribute("vaga", vaga);
		request.setAttribute("alunos", candidatos);

		return dispatcher;

	}

	/*
	 * Método responsável por habilitar estágios
	 */
	public RequestDispatcher habiltarEstagio() throws IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/coordenador/habilitar_estagio.jsp");
		EstagioDAO estagioDAO = new EstagioDAO(PersistenceUtil.getCurrentEntityManager());

		// Verifica usuário
		if (!super.canAccess("usuario", "Usuario")) {
			response.sendRedirect(request.getServletContext().getContextPath());
			super.addFlashMessage("error", "Você não tem permissão para acessar está área.");
			return dispatcher;
		}

		// Veririca ID da vaga e ID do aluno
		if (request.getParameter("vaga") == null || !request.getParameter("vaga").matches("^\\d+$")
				|| request.getParameter("aluno") == null || !request.getParameter("aluno").matches("^\\d+$")) {
			response.sendRedirect(request.getServletContext().getContextPath());

			super.addFlashMessage("error", "É preciso informar a vaga e o aluno");
			return dispatcher;
		}

		Aluno aluno = alunoDAO.find(Integer.parseInt(request.getParameter("aluno")));
		Vaga vaga = vagaDAO.find(Integer.parseInt(request.getParameter("vaga")));

		// Verifica se vaga e aluno existem
		if (vaga == null || aluno == null) {
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		request.setAttribute("vaga", vaga);
		request.setAttribute("aluno", aluno);

		// Se estivermos criando o estágio
		if (request.getMethod().equals("POST")) {
			List<String> fields = new ArrayList<>(Arrays.asList("obrigatorio", "encerrado", "aluno", "vaga"));

			// Verifica se o aluno já esta estagiando
			if (aluno.isEstagiando()) {
				super.addFlashMessage("info", "O aluno (" + aluno.getNome() + ") já está estagiando na empresa ("
						+ vaga.getEmpresa().getNome() + ").");
				response.sendRedirect(request.getServletContext().getContextPath() + "/coordenacao/vagas/listar");
				return dispatcher;
			}

			// Valida campos obrigatórios
			if (super.validaFormulario(fields)) {
				Estagio estagio = estagioDAO.getBy(vaga, aluno);
				estagio.setEditado(true);
				estagio.setObrigatorio(Boolean.parseBoolean(request.getParameter("obrigatorio")));
				estagio.setEncerrado(Boolean.parseBoolean(request.getParameter("encerrado")));

				estagioDAO.beginTransaction();
				estagioDAO.update(estagio);
				estagioDAO.commit();

				alunoDAO.beginTransaction();
				aluno.addEstagio(estagio);
				alunoDAO.update(aluno);
				alunoDAO.commit();

				super.addFlashMessage("success", "Estágio autorizado com sucesso");
				response.sendRedirect(request.getServletContext().getContextPath() + "/coordenacao");
			}

		}

		return dispatcher;

	}

	/*
	 * Método responsável por listar todos os estágios
	 */
	public RequestDispatcher listarEstagios() throws IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/coordenador/listar_estagios.jsp");

		// Verifica usuário
		if (!super.canAccess("usuario", "Usuario")) {
			super.addFlashMessage("error", "Você não possui acesso");
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		// boolean Status =
		// Boolean.parseBoolean(request.getParameter("status"));;

		List<Estagio> estagios = estagioDAO.getAllByEncerrado(false);
		request.setAttribute("estagios", estagios);

		return dispatcher;
	}
}
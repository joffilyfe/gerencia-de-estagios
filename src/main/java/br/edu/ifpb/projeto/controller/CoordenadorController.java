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
	 * Método responsável pela edição da empresa
	 */
	public RequestDispatcher editarEmpresa() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/coordenador/editar_empresa.jsp");

		// Verifica usuário
		if (!super.canAccess("usuario", "Usuario")) {
			response.sendRedirect(request.getServletContext().getContextPath());
			super.addFlashMessage("error", "Você não tem permissão para acessar está área.");
			return dispatcher;
		}

		if (!this.request.getParameter("id").matches("^\\d+$")) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/coordenacao/empresas/listar");
			super.addFlashMessage("error", "Empresa não encontrada");
			return dispatcher;
		}

		Empresa empresa = empresaDAO.find(Integer.parseInt(this.request.getParameter("id")));

		if (empresa == null) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/coordenacao/empresas/listar");
			super.addFlashMessage("error", "Empresa não encontrada");
			return dispatcher;
		}

		List<String> fields = new ArrayList<String>(Arrays.asList("nome", "cnpj", "endereco", "numero", "complemento",
				"bairro", "cidade", "estado", "cep", "referencia", "responsavel", "cargoResponsavel",
				"nomeContatoSelecao", "telefone", "fax", "email"));

		if (request.getMethod().equals("POST")) {
			if (super.validaFormulario(fields)) {
				empresa.setNome(request.getParameter("nome"));
				empresa.setCnpj(Integer.parseInt(request.getParameter("cnpj")));
				empresa.setEndereco(request.getParameter("endereco"));
				empresa.setNumero(Integer.parseInt(request.getParameter("numero")));
				empresa.setComplemento(request.getParameter("complemento"));
				empresa.setBairro(request.getParameter("bairro"));
				empresa.setCidade(request.getParameter("cidade"));
				empresa.setEstado(request.getParameter("estado"));
				empresa.setCep(request.getParameter("cep"));
				empresa.setPontoDeReferencia(request.getParameter("referencia"));
				empresa.setResponsavel(request.getParameter("responsavel"));
				empresa.setCargoResponsavel(request.getParameter("cargoResponsavel"));
				empresa.setNomeDoContato(request.getParameter("nomeContatoSelecao"));
				empresa.setTelefone(request.getParameter("telefone"));
				empresa.setFax(request.getParameter("fax"));
				empresa.setEmail(request.getParameter("email"));

				empresaDAO.beginTransaction();
				empresaDAO.update(empresa);
				empresaDAO.commit();

				super.addFlashMessage("success", "Perfil atualizado com sucesso!");
			}
		}

		if (request.getMethod().equals("GET")) {
			request.setAttribute("nome", empresa.getNome());
			request.setAttribute("cnpj", empresa.getCnpj());
			request.setAttribute("endereco", empresa.getEndereco());
			request.setAttribute("numero", empresa.getNumero());
			request.setAttribute("complemento", empresa.getComplemento());
			request.setAttribute("bairro", empresa.getBairro());
			request.setAttribute("cidade", empresa.getCidade());
			request.setAttribute("estado", empresa.getEstado());
			request.setAttribute("cep", empresa.getCep());
			request.setAttribute("referencia", empresa.getPontoDeReferencia());
			request.setAttribute("responsavel", empresa.getResponsavel());
			request.setAttribute("cargoResponsavel", empresa.getCargoResponsavel());
			request.setAttribute("nomeContatoSelecao", empresa.getNomeDoContato());
			request.setAttribute("telefone", empresa.getTelefone());
			request.setAttribute("fax", empresa.getFax());
			request.setAttribute("email", empresa.getEmail());
		}

		request.setAttribute("empresa", empresa);

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

		List<Estagio> estagios = estagioDAO.getAllActive();
		request.setAttribute("estagios", estagios);

		return dispatcher;
	}

	/*
	 * Método responsável por editar estágio
	 */
	public RequestDispatcher editarEstagio() throws IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/coordenador/editar_estagio.jsp");

		// Verifica usuário
		if (!super.canAccess("usuario", "Usuario")) {
			super.addFlashMessage("error", "Você não possui acesso");
			response.sendRedirect(request.getServletContext().getContextPath());
			return dispatcher;
		}

		Estagio estagio = estagioDAO.find(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("estagio", estagio);

		// Se for uma edição
		if (request.getMethod().equals("POST")) {
			List<String> fields = new ArrayList<>(Arrays.asList("obrigatorio", "encerrado", "id"));

			// Valida campos
			if (super.validaFormulario(fields)) {

				estagio.setEncerrado(Boolean.parseBoolean(request.getParameter("encerrado")));
				estagio.setObrigatorio(Boolean.parseBoolean(request.getParameter("obrigatorio")));

				estagioDAO.beginTransaction();
				estagioDAO.update(estagio);
				estagioDAO.commit();

				super.addFlashMessage("success", "Estágio editado com successo");
				response.sendRedirect(request.getServletContext().getContextPath() + "/coordenacao/estagios/listar");
			}

		}
		return dispatcher;
	}
}
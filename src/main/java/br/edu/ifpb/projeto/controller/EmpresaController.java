package br.edu.ifpb.projeto.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Usuario;
import br.edu.ifpb.projeto.model.Vaga;

public class EmpresaController extends ApplicationController {

	// DAOS
	private EmpresaDAO empresaDAO = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
	private VagaDAO vagaDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());

	public EmpresaController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public RequestDispatcher index() throws IOException {
		RequestDispatcher dispatcher = this.request.getRequestDispatcher("/view/empresa/index.jsp");
		HttpSession session = request.getSession();
		this.request.setAttribute("vagas", vagaDAO.findAll());

		if (session.getAttribute("usuario") == null
				|| ((Usuario) session.getAttribute("usuario")).isEmpresa() == false) {
			response.sendRedirect(request.getServletContext().getContextPath());
		}

		return dispatcher;
	}

	public RequestDispatcher ofertarVaga() throws IOException, ParseException {
		super.authUserOrRedirect("usuario");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/empresa/cadastro.jsp");
		HttpSession session = request.getSession();
		Empresa empresa = (Empresa) session.getAttribute("usuario");

		List<String> fields = new ArrayList<String>(
				Arrays.asList("titulo", "areaEstagio", "setor", "horarioEntrada", "horarioSaida", "valorBolsa", "vagas",
						"beneficios", "numeroAlunosSelecao", "periodoDivulgacaoInicio-data",
						"periodoDivulgacaoFim-data", "dataEntrevista-data", "principaisAtividades"));

		if (request.getMethod().equals("POST")) {
			if (super.validaFormulario(fields)) {

				Vaga vaga = new Vaga(request.getParameter("principaisAtividades"), empresa);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				sdf.setLenient(false);

				vaga.setTitulo(request.getParameter("titulo"));
				vaga.setAreaDeFormacao(request.getParameter("areaEstagio"));
				vaga.setSetor(request.getParameter("setor"));
				vaga.setHorarioEntrada(request.getParameter("horarioEntrada"));
				vaga.setHorarioSaida(request.getParameter("horarioSaida"));
				vaga.setValorDaBolsa(Double.parseDouble(request.getParameter("valorBolsa")));
				vaga.setQtdVagas(Integer.parseInt(request.getParameter("vagas")));
				vaga.setBeneficios(request.getParameter("beneficios"));
				vaga.setQtdAlunos(Integer.parseInt(request.getParameter("numeroAlunosSelecao")));
				vaga.setDataDivulgacaoInicio(sdf.parse(request.getParameter("periodoDivulgacaoInicio-data")));
				vaga.setDataDivulgacaoFim(sdf.parse(request.getParameter("periodoDivulgacaoFim-data")));
				vaga.setDataEntrevista(sdf.parse(request.getParameter("dataEntrevista-data")));

				vagaDAO.beginTransaction();
				vagaDAO.insert(vaga);
				vagaDAO.commit();

				this.addFlashMessage("success", "Cadastro realizado com sucesso");
				response.sendRedirect(request.getServletContext().getContextPath() + "/usuario/painel");
				return dispatcher;
			}
		}

		return dispatcher;
	}

	public RequestDispatcher editarPerfil() throws IOException {
		super.authUserOrRedirect("usuario");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/empresa/editar_perfil.jsp");
		HttpSession session = request.getSession();
		Empresa empresa = (Empresa) session.getAttribute("usuario");

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

		return dispatcher;
	}
}

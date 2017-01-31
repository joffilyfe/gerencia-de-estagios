package br.edu.ifpb.projeto.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.projeto.dao.EmpresaDAO;
import br.edu.ifpb.projeto.dao.PersistenceUtil;
import br.edu.ifpb.projeto.dao.VagaDAO;
import br.edu.ifpb.projeto.model.Empresa;
import br.edu.ifpb.projeto.model.Vaga;

public class EmpresaController extends ApplicationController {
	
	// DAOS
	private VagaDAO vagaDAO = new VagaDAO(PersistenceUtil.getCurrentEntityManager());

	public EmpresaController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public RequestDispatcher ofertarVaga() throws IOException, ParseException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/empresa/cadastro.jsp");
		HttpSession session = request.getSession();
		Empresa empresa = (Empresa) session.getAttribute("usuario");
		
		if (empresa != null)
		{
			
			System.out.println("empresa " + empresa.getNome());
			
			List<String> fields = new ArrayList<String>(Arrays.asList("nome", "cnpj", "endereco", "numero", "complemento", "bairro",
				    "cidade", "estado", "cep", "referencia", "responsavel", "cargoResponsavel","nomeContatoSelecao", "telefone", "fax", "email",
				    "areaEstagio", "setor", "horarioEntrada", "horarioSaida", "valorBolsa", "vagas", "beneficios","numeroAlunosSelecao", 
				    "periodoDivulgacaoInicio","periodoDivulgacaoFim", "dataEntrevista", "principaisAtividades"));
	
			if (request.getMethod().equals("POST")) {
				if (super.validaFormulario(fields)) {
					
					Vaga vaga = new Vaga(request.getParameter("principaisAtividades"), empresa);
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					sdf.setLenient(false);
					
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
					
					vaga.setAreaDeFormacao(request.getParameter("areaEstagio"));
					vaga.setSetor(request.getParameter("setor"));
					vaga.setHorarioEntrada(request.getParameter("horarioEntrada"));
					vaga.setHorarioSaida(request.getParameter("horarioSaida"));
					vaga.setValorDaBolsa(Double.parseDouble(request.getParameter("valorBolsa")));
					vaga.setQtdVagas(Integer.parseInt(request.getParameter("vagas")));
					vaga.setBeneficios(request.getParameter("beneficios"));
					vaga.setQtdAlunos(Integer.parseInt(request.getParameter("numeroAlunosSelecao")));
					vaga.setDataDivulgacaoInicio(sdf.parse(request.getParameter("periodoDivulgacaoInicio")));
					vaga.setDataDivulgacaoFim(sdf.parse(request.getParameter("periodoDivulgacaoFim")));
					vaga.setDataEntrevista(sdf.parse(request.getParameter("dataEntrevista")));
					
					vagaDAO.beginTransaction();
					vagaDAO.insert(vaga);
					vagaDAO.commit();
	
					this.addFlashMessage("success", "Cadastro realizado com sucesso" );
				}
			}
		}
		else {	
			dispatcher = request.getRequestDispatcher("/view/site/index.jsp");
		}
			
		return dispatcher;
	}

}

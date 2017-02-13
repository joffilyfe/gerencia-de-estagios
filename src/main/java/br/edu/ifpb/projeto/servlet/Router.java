package br.edu.ifpb.projeto.servlet;

import java.util.ArrayList;
import java.util.HashMap;

public class Router {
	private HashMap<String, ArrayList<String>> URL = new HashMap<String, ArrayList<String>>();

	public Router() {
		addRoute("/", "SiteController", "index");

		// Usuário
		addRoute("/usuario/login", "UsuarioController", "login");
		addRoute("/usuario/logout", "UsuarioController", "logout");
		addRoute("/usuario/cadastro", "UsuarioController", "cadastro");
		addRoute("/usuario/painel", "UsuarioController", "painel");
		addRoute("/usuario/painel/perfil/editar", "UsuarioController", "editarPerfil");

		// Aluno
		addRoute("/aluno/perfil/editar", "AlunoController", "editarPerfil");
		addRoute("/aluno/candidatar/vaga", "AlunoController", "candidatarVaga");
		addRoute("/aluno/perfil", "AlunoController", "perfilPublico");

		// Coordenacao
		addRoute("/coordenacao", "CoordenadorController", "index");
		addRoute("/coordenacao/empresas/habilitar", "CoordenadorController", "habilitarempresa");
		addRoute("/coordenacao/empresas/listar", "CoordenadorController", "listarEmpresas");
		addRoute("/coordenacao/empresa/editar", "CoordenadorController", "editarEmpresa");
		addRoute("/coordenacao/vagas/listar", "CoordenadorController", "listarVagas");
		addRoute("/coordenacao/vagas/detalhes", "CoordenadorController", "verAlunosCandidatos");
		addRoute("/coordenacao/estagio/habilitar", "CoordenadorController", "habiltarEstagio");
		addRoute("/coordenacao/estagios/listar", "CoordenadorController", "listarEstagios");
		addRoute("/coordenacao/estagios/editar", "CoordenadorController", "editarEstagio");

		// Vaga
		addRoute("/vagas", "VagaController", "index");
		addRoute("/vaga/detalhes", "VagaController", "show");
		addRoute("/vagas/visualizarvagas", "VagaController", "listarVagas");
		addRoute("/vagas/candidatos", "VagaController", "listarCandidatos");

		// Empresa
		addRoute("/empresa", "EmpresaController", "index");
		addRoute("/empresa/vagas/candidatos", "VagaController", "listarCandidatos");
		addRoute("/empresa/cadastro", "EmpresaController", "ofertarVaga");
		addRoute("/empresa/editar", "EmpresaController", "editarPerfil");
		addRoute("/empresa/perfil", "EmpresaController", "perfilPublico");

		// Estagio
		addRoute("/estagiarios/admissao", "EstagioController", "admitirCandidato");
	}

	public void addRoute(String url, String controller, String method) {
		ArrayList<String> control = new ArrayList<String>();
		control.add(controller);
		control.add(method);
		this.URL.put(url, control);
	}

	public ArrayList<String> getURL(String url) {
		return this.URL.get(url);
	}
}

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

		// Coordenacao
		addRoute("/coordenacao", "CoordenadorController", "index");
		addRoute("/coordenacao/empresas/habilitar", "CoordenadorController", "habilitarempresa");
		addRoute("/coordenacao/empresas/listar", "CoordenadorController", "listarEmpresas");
		addRoute("/coordenacao/vagas/listar", "CoordenadorController", "listarVagas");
		addRoute("/coordenacao/vagas/detalhes", "CoordenadorController", "verAlunosCandidatos");

		// Vaga
		addRoute("/vagas", "VagaController", "index");
		addRoute("/vaga/detalhes", "VagaController", "show");

		// Empresa
		addRoute("/empresa/cadastro", "EmpresaController", "ofertarVaga");
		addRoute("/empresa/editar", "EmpresaController", "editarPerfil");
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

package br.edu.ifpb.projeto.servlet;

import java.util.ArrayList;
import java.util.HashMap;

public class Router {
	private HashMap<String, ArrayList<String>> URL = new HashMap<String, ArrayList<String>>();

	public Router() {
		addRoute("/", "SiteController", "index");
		addRoute("/usuario/login", "UsuarioController", "login");
		addRoute("/usuario/logout", "UsuarioController", "logout");
		addRoute("/usuario/cadastro", "UsuarioController", "cadastro");
		
		
		// Coordenacao
		addRoute("/coordenacao", "CoordenadorController", "index");
		addRoute("/coordenacao/empresas/habilitar", "CoordenadorController", "habilitarempresa");
		addRoute("/coordenacao/empresas/listar", "CoordenadorController", "listarEmpresas");
		
		//Empresa
		addRoute("/empresa", "EmpresaController", "index");
		addRoute("/empresa/vagas/candidatos","VagaController","listarCandidatos");
		
		// Vaga
		addRoute("/vagas", "VagaController", "index");
		addRoute("/vaga/detalhes", "VagaController", "show");
		addRoute("/vagas/visualizarvagas", "VagaController", "listarVagas");
		addRoute("/vagas/candidatos","VagaController","listarCandidatos");
		
		//Estagio
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

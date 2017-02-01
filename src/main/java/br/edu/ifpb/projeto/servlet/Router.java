package br.edu.ifpb.projeto.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Router extends HashMap<String, String> {

	private static final long serialVersionUID = 6421016236362285275L;
	private HashMap<String, ArrayList<String>> URL = new HashMap<String, ArrayList<String>>();

	public Router() {
		addRoute("/", "SiteController", "index");

		// Usuário
		addRoute("/usuario/login", "UsuarioController", "login", "user_login");
		addRoute("/usuario/logout", "UsuarioController", "logout", "user_logout_route");
		addRoute("/usuario/cadastro", "UsuarioController", "cadastro");
		addRoute("/usuario/painel", "UsuarioController", "painel");
		addRoute("/usuario/painel/perfil/editar", "UsuarioController", "editarPerfil");

		// Aluno
		addRoute("/aluno/perfil/editar", "AlunoController", "editarPerfil");

		// Coordenacao
		addRoute("/coordenacao", "CoordenadorController", "index");
		addRoute("/coordenacao/empresas/habilitar", "CoordenadorController", "habilitarempresa");
		addRoute("/coordenacao/empresas/listar", "CoordenadorController", "listarEmpresas");

		// Vaga
		addRoute("/vagas", "VagaController", "index");
		addRoute("/vaga/detalhes", "VagaController", "show");

		// Empresa
		addRoute("/empresa/cadastro", "EmpresaController", "ofertarVaga");
	}

	public void addRoute(String url, String controller, String method) {
		ArrayList<String> control = new ArrayList<String>();
		control.add(controller);
		control.add(method);
		this.URL.put(url, control);
	}

	public void addRoute(String url, String controller, String method, String name) {
		ArrayList<String> control = new ArrayList<String>();
		control.add(controller);
		control.add(method);
		control.add(name);
		this.URL.put(url, control);
	}

	public ArrayList<String> getURL(String url) {
		return this.URL.get(url);
	}

	/*
	 * Retorna uma URL baseada no nome
	 */
	@Override
	public String get(Object name) {
		Iterator<Entry<String, ArrayList<String>>> it = this.URL.entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry pair = it.next();
			@SuppressWarnings("unchecked")
			ArrayList<String> route = (ArrayList<String>) pair.getValue();

			if (route.size() >= 3 && route.get(2).equals(name)) {
				return (String) pair.getKey();
			}

		}

		return "/";

	}
}

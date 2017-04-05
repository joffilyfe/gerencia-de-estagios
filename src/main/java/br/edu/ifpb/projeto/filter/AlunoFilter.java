package br.edu.ifpb.projeto.filter;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.project.util.App;
import br.edu.ifpb.projeto.model.Usuario;

@WebFilter(filterName = "AlunoFilter", urlPatterns = {"/view/aluno/*"})
public class AlunoFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession(false);

		if (httpSession != null) {
			Usuario loginUser = (Usuario) httpSession.getAttribute("usuarioLogado");
			if (loginUser == null) {
				// Se nao tiver usuario logado
				redirectIndex(httpRequest, httpResponse);
				return;
			}else if (!loginUser.isAluno()){
				// Se usuario logado nao for aluno
				httpSession.setAttribute("message", "Usuario deve ser aluno para acessar pagina!");
				redirectIndex(httpRequest, httpResponse);
				return;
			}
		} else {
			redirectIndex(httpRequest, httpResponse);
			return;
		}
		chain.doFilter(request, response);

	}

	private void redirectIndex(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		String baseUrl = httpRequest.getContextPath();
		String paginaIndex = baseUrl + "/view/index.jsf";
		httpResponse.sendRedirect(httpResponse.encodeRedirectURL(paginaIndex));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}

package br.edu.ifpb.projeto.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;

import br.edu.ifpb.projeto.dao.ManagedEMContext;
import br.edu.ifpb.projeto.dao.PersistenceUtil;

@WebFilter(urlPatterns = { "*.jsf", "/faces/*" })
public class EntityManagerConversationFilter implements Filter {
	private static Logger logger = Logger.getLogger(EntityManagerConversationFilter.class);

	private EntityManagerFactory emf;

	public static final String ENTITYMANAGER_FACTORY_KEY = "currentEntityManager";

	public static final String END_OF_CONVERSATION_FLAG = "endofconversation";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		EntityManager currentEntityManager;

		// Try to get a EntityManager from the HttpSession
		HttpSession httpSession = ((HttpServletRequest) request).getSession();
		EntityManager disconnectedEM = (EntityManager) httpSession.getAttribute(ENTITYMANAGER_FACTORY_KEY);

		// Context path
		String context = ((HttpServletRequest) request).getServletPath();
		context += ": ";
		try {

			// Start a new conversation or in the middle?
			if (disconnectedEM == null) {
				logger.debug(context + ">>> Nova conversão");
				// Define o modo manual de flush
				currentEntityManager = emf.createEntityManager();
				((org.hibernate.Session) currentEntityManager.getDelegate())
						.setFlushMode(org.hibernate.FlushMode.MANUAL);
			} else {
				logger.debug(context + "< Continuando conversão");
				currentEntityManager = disconnectedEM;
			}

			ManagedEMContext.bind(emf, currentEntityManager);
			logger.debug(context + "Associou EntityManager ao contexto");

			logger.debug(context + "Processando servlet/JSP");
			chain.doFilter(request, response);

			// End or continue the long-running conversation?
			if (request.getAttribute(END_OF_CONVERSATION_FLAG) != null
					|| request.getParameter(END_OF_CONVERSATION_FLAG) != null) {

				// currentEntityManager.flush();
				// logger.debug(context + "Executou flush do EntityManager");

				currentEntityManager.close();
				logger.debug(context + "Fechando o EntityManager");

				ManagedEMContext.unbind(emf);
				logger.debug(context + "Desassociou EntityManager do contexto");

				httpSession.removeAttribute(ENTITYMANAGER_FACTORY_KEY);
				logger.debug(context + "Retirou EntityManager da HttpSession");

				logger.debug(context + "<<< Fim da conversa��o");

			} else {
				// currentEntityManager.flush();
				// logger.debug(context + "Executou flush do EntityManager");

				ManagedEMContext.unbind(emf);
				logger.debug(context + "Desassociou EntityManager do contexto");

				httpSession.setAttribute(ENTITYMANAGER_FACTORY_KEY, currentEntityManager);
				logger.debug(context + "Associou EntityManager a HttpSession");

				logger.debug(context + "> Retornando para o usuario");
			}

		} catch (StaleObjectStateException staleEx) {
			logger.error(context + "This interceptor does not implement optimistic concurrency control!");
			logger.error(context + "Your application will not work until you add compensation actions!");
			// Rollback, close everything, possibly compensate for any permanent
			// changes
			// during the conversation, and finally restart business
			// conversation. Maybe
			// give the user of the application a chance to merge some of his
			// work with
			// fresh data... what you do here depends on your applications
			// design.
			throw staleEx;
		} catch (Throwable ex) {
			// Rollback only
			logger.debug(context + "Original exception:", ex);
			try {
				if (PersistenceUtil.getCurrentEntityManager().getTransaction().isActive()) {
					logger.debug(context + "Tentando rollback da transa��o ap�s exception");
					PersistenceUtil.getCurrentEntityManager().getTransaction().rollback();
				}
			} catch (Throwable rbEx) {
				logger.error(context + "Rollback não efetivado!", rbEx);
			} finally {
				logger.error(context + "Cleanup after exception!");

				// Cleanup
				currentEntityManager = ManagedEMContext.unbind(emf);
				logger.debug(context + "Desassociou EntityManager do contexto");

				currentEntityManager.close();
				logger.debug(context + "Fechou EntityManager ap�s exception");

				httpSession.setAttribute(ENTITYMANAGER_FACTORY_KEY, null);
				logger.debug(context + "Removeu EntityManager da HttpSession");

			}

			// Let others handle it... maybe another interceptor for exceptions?
			throw new ServletException(ex);
		} finally {

		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		emf = PersistenceUtil.getEntityManagerFactory();
		logger.info("Filtro de conversa��es longas inicializado.");
	}

	@Override
	public void destroy() {
		if (emf != null) {
			emf.close();
		}
	}

}
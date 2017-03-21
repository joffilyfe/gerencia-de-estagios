package dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.HibernateException;
import org.hibernate.context.spi.CurrentSessionContext;
import org.jboss.logging.Logger;

/**
 * Implementação de um contexto para EntityManagers nos moldes
 * do ManagedSessionContext do Hibernate.
 *
 * @author Frederico Costa
 */
public class ManagedEMContext {

	@SuppressWarnings("rawtypes")
	private static final ThreadLocal context = new ThreadLocal();
	private final EntityManagerFactory factory;
	private static Logger logger = Logger.getLogger(ManagedEMContext.class);

	public ManagedEMContext(EntityManagerFactory factory) {
		this.factory = factory;
	}

	/**
	 * @see CurrentSessionContext#currentSession
	 */
	public EntityManager currentEntityManager() {
		EntityManager current = existingEntityManager( factory );
		if ( current == null ) {
			throw new HibernateException( "No entity manager currently bound to execution context" );
		}
		return current;
	}

	/**
	 * Check to see if there is already a session associated with the current
	 * thread for the given session factory.
	 *
	 * @param factory The factory against which to check for a given session
	 * within the current thread.
	 * @return True if there is currently a session bound.
	 */
	public static boolean hasBind(EntityManagerFactory factory) {
		return existingEntityManager( factory ) != null;
	}

	/**
	 * Binds the given session to the current context for its session factory.
	 *
	 * @param session The session to be bound.
	 * @return Any previously bound session (should be null in most cases).
	 */
	@SuppressWarnings("unchecked")
	public static EntityManager bind(EntityManagerFactory emf, EntityManager em) {
		return ( EntityManager ) entityManagerMap( true ).put( emf, em );
	}

	/**
	 * Unbinds the entity manager (if one) current associated with the context for the
	 * given session.
	 *
	 * @param factory The factory for which to unbind the current session.
	 * @return The bound session if one, else null.
	 */
	@SuppressWarnings("rawtypes")
	public static EntityManager unbind(EntityManagerFactory factory) {
		EntityManager existing = null;
		Map emMap = entityManagerMap();
		if ( emMap != null ) {
			existing = ( EntityManager ) emMap.remove( factory );
			doCleanup();
		}
		return existing;
	}

	@SuppressWarnings("rawtypes")
	private static EntityManager existingEntityManager(EntityManagerFactory factory) {
		Map emMap = entityManagerMap();
		if ( emMap == null ) {
			return null;
		}
		else {
			return ( EntityManager ) emMap.get( factory );
		}
	}

	@SuppressWarnings("rawtypes")
	protected static Map entityManagerMap() {
		return entityManagerMap( false );
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static synchronized Map entityManagerMap(boolean createMap) {
		Map emMap = ( Map ) context.get();
		logger.debug("Mapa na ThreadLocal: " + emMap);
		if ( emMap == null && createMap ) {
			emMap = new HashMap();
			context.set( emMap );
			logger.debug("Criou mapa no ThreadLocal");
		}
		return emMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static synchronized void doCleanup() {
		Map emMap = entityManagerMap( false );
		if ( emMap != null ) {
			if ( emMap.isEmpty() ) {
				context.set( null );
			}
		}
	}
}


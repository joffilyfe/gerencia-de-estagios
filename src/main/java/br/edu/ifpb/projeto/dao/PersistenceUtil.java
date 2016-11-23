package br.edu.ifpb.projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class PersistenceUtil {

    private static EntityManagerFactory emf;
    private static ManagedEMContext emc;
	private static Logger logger = Logger.getLogger(PersistenceUtil.class);

	private PersistenceUtil() {
		// Singleton
	}
	
    public static EntityManagerFactory createEntityManagerFactory(String persistenceUnitName) {
        try {
    		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    		emc = new ManagedEMContext(emf);
            logger.info("Fábrica de EntityManagers construída.");
            return emf;
        } catch (Throwable ex) {
            logger.error("Falha ao criar EntityManagerFactory.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
    public static EntityManager getCurrentEntityManager() {
    	return emc.currentEntityManager();
    }
    
    public static EntityManager getEntityManager() {
    	return emf.createEntityManager();
    }
    

}
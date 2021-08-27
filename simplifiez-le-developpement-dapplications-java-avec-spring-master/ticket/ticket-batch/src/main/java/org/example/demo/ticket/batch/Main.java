package org.example.demo.ticket.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.demo.ticket.batch.bootstrap.SpringConfiguration;
import org.example.demo.ticket.business.factory.ManagerFactory;
import org.example.demo.ticket.model.exception.TechnicalException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Classe Principale de lancement des Batches.
 *
 * @author lgu
 */
public class Main {

	/** Logger pour la classe */
	private static final Log LOGGER = LogFactory.getLog(Main.class);

	/**
	 * The entry point of application.
	 *
	 * @param pArgs the input arguments
	 * @throws TechnicalException sur erreur technique
	 */
	public static void main(String[] pArgs) throws TechnicalException {

//		ApplicationContext vApplicationContext = new ClassPathXmlApplicationContext(
//				"classpath:/applicationContext.xml");

		ApplicationContext vApplicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

		// Il est possible de récupérer un bean dans ce contexte :
		ManagerFactory vManagerFactory = vApplicationContext.getBean("managerFactoryImpl", ManagerFactory.class);

		// suite de l'implémentation des batches...
	}

}

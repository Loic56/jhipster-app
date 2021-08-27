package org.example.demo.ticket.webapp.rest;

import org.example.demo.ticket.business.factory.ManagerFactory;

public class AbstractResource {

	private static ManagerFactory managerFactory;

	protected static ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public static void setManagerFactory(ManagerFactory pManagerFactory) {
		managerFactory = pManagerFactory;
	}

}

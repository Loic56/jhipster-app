package org.example.demo.ticket.business.factory;

import org.example.demo.ticket.business.contract.ProjetManager;

public class ManagerFactory {

	// Ajout d'un attribut projetManager
	private ProjetManager projetManager;

	// On renvoie désormais simplement l'attribut projetManager
	public ProjetManager getProjetManager() {
		return projetManager;
	}

	// Ajout d'un setter pour l'attribut projetManager
	public void setProjetManager(ProjetManager pProjetManager) {
		projetManager = pProjetManager;
	}
}

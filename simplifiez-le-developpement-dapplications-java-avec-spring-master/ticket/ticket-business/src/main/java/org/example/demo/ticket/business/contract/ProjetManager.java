package org.example.demo.ticket.business.contract;

import java.util.List;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.exception.NotFoundException;

public interface ProjetManager {

	Projet getProjet(Integer pId) throws NotFoundException;

	List<Projet> getListProjet();

}

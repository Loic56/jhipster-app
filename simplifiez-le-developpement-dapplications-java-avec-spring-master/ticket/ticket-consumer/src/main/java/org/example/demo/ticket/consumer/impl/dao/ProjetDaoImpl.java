package org.example.demo.ticket.consumer.impl.dao;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.example.demo.ticket.consumer.constract.dao.ProjetDao;
import org.example.demo.ticket.consumer.constract.dao.TicketDao;
import org.example.demo.ticket.consumer.constract.dao.UtilisateurDao;

@ManagedBean
public class ProjetDaoImpl implements ProjetDao {

	@Inject
	private TicketDao ticketDao;

	@Inject
	private UtilisateurDao utilisateurDao;

}
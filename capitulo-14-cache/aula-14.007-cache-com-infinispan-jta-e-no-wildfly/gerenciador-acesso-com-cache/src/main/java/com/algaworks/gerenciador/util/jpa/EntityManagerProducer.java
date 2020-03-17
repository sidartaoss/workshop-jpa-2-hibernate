package com.algaworks.gerenciador.util.jpa;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@ApplicationScoped
@RequestScoped
public class EntityManagerProducer {
	
//	private EntityManagerFactory factory;
	@PersistenceContext(unitName = "gerenciadorAcessoPU")
	private EntityManager manager;
	
	public EntityManagerProducer() {
//		this.factory = Persistence.createEntityManagerFactory("gerenciadorAcessoPU");
	}

	@Produces
	@RequestScoped
	public EntityManager create() {
//		return factory.createEntityManager();
		return manager;
	}

//	public void close(@Disposes EntityManager manager) {
//		manager.close();
//	}

}

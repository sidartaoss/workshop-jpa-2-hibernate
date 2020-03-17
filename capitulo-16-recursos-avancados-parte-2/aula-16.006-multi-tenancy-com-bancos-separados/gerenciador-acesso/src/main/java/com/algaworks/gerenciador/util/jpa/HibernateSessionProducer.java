package com.algaworks.gerenciador.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;	

@ApplicationScoped
public class HibernateSessionProducer {
	
	private SessionFactory sessionFactory;
	
	@Inject
	@TenantInject
	private Tenant tenant;
	
	public HibernateSessionProducer() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("gerenciadorAcessoPU");
		this.sessionFactory = factory.unwrap(SessionFactory.class);
	}
	
	@Produces
	@RequestScoped
	public Session create() {
//		return this.sessionFactory.openSession();
//		return this.sessionFactory
//					.withOptions()
//					.tenantIdentifier("padaria-real")
//					.openSession();
		return this.sessionFactory
					.withOptions()
					.tenantIdentifier(this.tenant.getId())
					.openSession();
	}
	
	public void close(@Disposes Session session) {
		session.close();
	}
}
/**
 * 
 */
package com.algaworks.curso.jpa2.criteria;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Carro;

/**
 * @author SEMPR
 *
 */
public class SimpleLazyLoading {

	private static EntityManagerFactory factory;

	private EntityManager manager;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}

	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();

	}

	@After
	public void tearDown() {
		this.manager.close();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void simulaLazyLoading() {
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Carro.class);
		
		criteria.setFetchMode("acessorios", FetchMode.JOIN);
		
		criteria.add(Restrictions.eq("codigo", 17L));
		Carro c = (Carro) criteria.uniqueResult();
		
		System.out.println();
		System.out.printf("Codigo: %d. Placa: %s\n", c.getCodigo(), c.getPlaca());
		System.out.println();
		
		session.close();           // Simulando o fechamento da Sessao em um Sistema Web
		
		System.out.printf("Primeiro acessorio do carro: %s", c.getAcessorios().get(0).getDescricao());
	}
	
}

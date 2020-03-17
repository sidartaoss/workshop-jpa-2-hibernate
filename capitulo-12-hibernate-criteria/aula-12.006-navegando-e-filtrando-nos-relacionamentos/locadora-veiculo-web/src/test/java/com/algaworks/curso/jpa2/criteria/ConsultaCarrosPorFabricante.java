/**
 * 
 */
package com.algaworks.curso.jpa2.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
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
public class ConsultaCarrosPorFabricante {

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

	@SuppressWarnings("unchecked")
	@Test
	public void buscarCarrosDoFabricante() {
//		String fabricante = "Chevrolet";
//		String fabricante = "CHEVROLET";
//		String fabricante = "CHEVRolet";
//		String fabricante = "CHEVR";
//		String fabricante = "Olet";
		String fabricante = "T";
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Carro.class);
		
		criteria.createAlias("modelo", "m")
				.createAlias("m.fabricante", "f")
				.add(Restrictions.ilike("f.nome", fabricante, MatchMode.ANYWHERE));
		
		List<Carro> carros = criteria.list();
		
		for (Carro carro : carros) {
			System.out.printf("Placa: %s\n", carro.getPlaca());
		}
	}
	
}

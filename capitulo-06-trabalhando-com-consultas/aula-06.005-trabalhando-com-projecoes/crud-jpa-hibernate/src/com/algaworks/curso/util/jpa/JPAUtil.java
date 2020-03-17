/**
 * 
 */
package com.algaworks.curso.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author SEMPR
 *
 */
public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	
	public EntityManager createEntityManager() {
		return emf.createEntityManager();
	}
}

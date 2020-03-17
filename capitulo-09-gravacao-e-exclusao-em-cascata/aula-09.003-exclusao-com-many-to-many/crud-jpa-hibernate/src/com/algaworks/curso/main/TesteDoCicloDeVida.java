/**
 * 
 */
package com.algaworks.curso.main;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.modelo.Cliente;
import com.algaworks.curso.util.jpa.JPAUtil;

/**
 * @author SEMPR
 *
 */
public class TesteDoCicloDeVida {

	public static void main(String[] args) {
		
		/** ==================== * ESTADO NEW * ==================== **/

		// Uma nova Entidade. Esta no Estado NEW.
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Fernando Alonso");
		cliente1.setIdade(32);
		cliente1.setProfissao("Piloto");
		cliente1.setSexo("M");
		
		/** ==================== * ESTADO NEW * ==================== **/
		
		
		/** ==================== * ESTADO MANAGED * ==================== **/
		
		EntityManager em = JPAUtil.createEntityManager();
		
		//Com persist(), fazemos o objeto passar para o Estado MANAGED. 
		em.getTransaction().begin();
		em.persist(cliente1);
		em.getTransaction().commit();
		
		/** ==================== * ESTADO MANAGED * ==================== **/
		
		
		/** ==================== * ESTADO DETACHED * ==================== **/
		
//		em.detach(cliente1);
		
		em.close();
		em = JPAUtil.createEntityManager();
		
		/** ==================== * ESTADO DETACHED * ==================== **/
		
		// Se tentarmos agora, persistir uma entidade que está desanexada, 
		// iremos receber uma exception		
		try {
			em.getTransaction().begin();
			cliente1.setIdade(33);
			em.persist(cliente1);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			System.err.println("Erro ao persistir a entidade. " + e.getMessage());
			em.getTransaction().rollback();
		}
		
		em.close();
	}
}

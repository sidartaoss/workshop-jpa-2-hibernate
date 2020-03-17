/**
 * 
 */
package com.algaworks.curso.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.curso.model.Agenda;

/**
 * @author SEMPR
 *
 */
public class Update {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("agenda-telefonica-PU");
		EntityManager em = emf.createEntityManager();
		
//		Agenda agenda = em.find(Agenda.class, 2L);
		Agenda agenda = em.find(Agenda.class, 3L);
		
		if (agenda != null) {
			
			em.getTransaction().begin();

			agenda.setNome("Jose da Silva Santos");
			agenda.setTelefone("051984759999");
			
			em.getTransaction().commit();
			
			System.out.println("Registro alterado com sucesso!");
		} else {
			System.out.println("Registro nao encontrado!");
		}
	}

}

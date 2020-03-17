/**
 * 
 */
package com.algaworks.curso.main;

import javax.persistence.Persistence;

import com.algaworks.curso.model.Agenda;

import javax.persistence.EntityManagerFactory;

import java.util.Date;

import javax.persistence.EntityManager;

/**
 * @author SEMPR
 *
 */
public class Create {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("agenda-telefonica-PU");
		EntityManager em = emf.createEntityManager();
		
		Agenda agenda = new Agenda();
//		agenda.setNome("Jose da Silva");
//		agenda.setTelefone("051999941212");
		agenda.setNome("Maria Rita da Silva");
		agenda.setTelefone("051999947945");		
		agenda.setDataRegistro(new Date());
		
		em.getTransaction().begin();
		em.persist(agenda);
		em.getTransaction().commit();
		
	}

}

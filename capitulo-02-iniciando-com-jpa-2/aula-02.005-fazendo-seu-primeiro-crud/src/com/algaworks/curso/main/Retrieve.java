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
public class Retrieve {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("agenda-telefonica-PU");
		EntityManager em = emf.createEntityManager();
		
//		Agenda agenda = em.find(Agenda.class, 2L);
		Agenda agenda = em.find(Agenda.class, 4L);
		
		if (agenda != null) {
			System.out.println("Nome: " + agenda.getNome());
			System.out.println("Telefone: " + agenda.getTelefone());
			System.out.println("Data Registro: " + agenda.getDataRegistro());
		} else {
			System.out.println("Registro nao encontrado!");
		}
	}

}

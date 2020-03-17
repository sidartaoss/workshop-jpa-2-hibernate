/**
 * 
 */
package com.algaworks.curso.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.curso.modelo.Cliente;

/**
 * @author sosilva
 *
 */
public class SalvandoPrimeiroObjeto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemploPU");
		EntityManager em = emf.createEntityManager();
		
//		Cliente cliente = new Cliente();
//		cliente.setNome("Pedro Bial");
//		cliente.setIdade(40);
//		cliente.setProfissao("Jornalista");
//		cliente.setSexo("M");

//		Cliente cliente = new Cliente();
//		cliente.setNome("José da Silva Pereira");
//		cliente.setIdade(28);
//		cliente.setProfissao("Engenheiro");
//		cliente.setSexo("M");		
		
		Cliente cliente = new Cliente();
		cliente.setNome("Maria Rita");
		cliente.setIdade(20);
		cliente.setProfissao("Médica");
		cliente.setSexo("F");		
		
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		
		System.out.println("Cliente salvo com sucesso!");
		//em.close();
	}

}

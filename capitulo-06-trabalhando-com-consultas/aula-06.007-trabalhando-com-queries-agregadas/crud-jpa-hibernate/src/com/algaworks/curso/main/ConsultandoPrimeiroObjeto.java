/**
 * 
 */
package com.algaworks.curso.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.curso.modelo.Cliente;

/**
 * @author SEMPR
 *
 */
public class ConsultandoPrimeiroObjeto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemploPU");
		EntityManager em = emf.createEntityManager();
		
		/** long codigo = 1L; **/
		/** long codigo = 5L; **/
		long codigo = 3L;
		Cliente cliente = em.find(Cliente.class, codigo);
		
		if (cliente != null) {
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("Idade: " + cliente.getIdade());
			System.out.println("Profissao: " + cliente.getProfissao());
			System.out.println("Sexo: " + cliente.getSexo());
		} else {
			System.out.println("Cliente nao encontrado.");
		}
	}

}

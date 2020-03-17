/**
 * 
 */
package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.util.JPAUtil;

/**
 * @author SEMPR
 *
 */
public class ConsultaFabricantes {

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		/** Version 1 **/
		/**
		List<Fabricante> fabricantes = em.createQuery("select f from Fabricante f", Fabricante.class)
															.getResultList();
		
		for (Fabricante fabricante : fabricantes) {
			System.out.println(fabricante.getNome());
		}
		**/
		
		/** Version 2 **/
		List<String> nomeDosFabricantes = em.createQuery("select f.nome from Fabricante f", String.class)
																.getResultList();
		
		for (String nome : nomeDosFabricantes) {
			System.out.println("Nome: " + nome);
		}
		
		em.close();
	}
	
}

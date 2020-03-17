/**
 * 
 */
package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.util.JPAUtil;

/**
 * @author sosilva
 *
 */
public class ConsultaPassandoParametros {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		String modelo = "Chevrolet";
		

		/** Version 1. **/
//		String jpql = "select mc.descricao from ModeloCarro mc "
//				+ "where mc.fabricante.nome = 'Chevrolet'";
		/** Version 2. **/
//		String jpql = "select mc.descricao from ModeloCarro mc "
//				+ "where mc.fabricante.nome = '" + modelo + "'";
		/** Version 3. **/
//		String jpql = "select mc.descricao from ModeloCarro mc "
//				+ "where mc.fabricante.nome = ?1";
		String jpql = "select mc.descricao from ModeloCarro mc "
				+ "where mc.fabricante.nome = :modelo ";				
		
//		List<String> modelos = em.createQuery(jpql, String.class)
//				.setParameter(1, modelo)
//				.getResultList();
		List<String> modelos = em.createQuery(jpql, String.class)
				.setParameter("modelo", modelo)
				.getResultList();		

		for (String m : modelos) {
			System.out.println(m);
		}
		
		em.close();
	}
}

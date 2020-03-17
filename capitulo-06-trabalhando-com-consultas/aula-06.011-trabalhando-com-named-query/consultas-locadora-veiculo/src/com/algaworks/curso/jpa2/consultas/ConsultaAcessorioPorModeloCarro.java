/**
 * 
 */
package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.util.JPAUtil;

/**
 * @author SEMPR
 *
 */
public class ConsultaAcessorioPorModeloCarro {
	
	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		/** Version 1. **/
		/** String jpql = "select c.acessorios.descricao from Carro c where c.modelo.descricao = 'Cruze'"; **/
		/** Version 2. **/
		/** String jpql = "select a.descricao from Carro c, Acessorio a where c.modelo.descricao = 'Cruze'" **/
		/** Version 3. **/
		/** String jpql = "select a.descricao from Carro c JOIN Acessorio a where c.modelo.descricao = 'Cruze'" **/
		/** Version 4. **/
		/** String jpql = "select a.descricao from Carro c JOIN c.acessorios a where c.modelo.descricao = 'Cruze'"; **/
		/** Verson 5. **/
		String jpql = "select a.descricao from Carro c JOIN c.acessorios a where c.modelo.descricao = 'Fit'";
		List<String> acessorios = em.createQuery(jpql, String.class).getResultList();
		
		for (String acessorio : acessorios) {
			System.out.println("Acessorio: " + acessorio);
		}
		
		em.close();
	}

}

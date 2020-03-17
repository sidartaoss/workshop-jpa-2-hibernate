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
public class ConsultaDescricaoECategoriaDeModeloCarro {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		String jpql = "select mc.descricao, mc.categoria from ModeloCarro mc";
		List<Object[]> resultados = em.createQuery(jpql).getResultList(); 
		
		for (Object[] obj : resultados) {
			System.out.println("Descricao: " + obj[0] + ". E categoria: " + obj[1]);
		}
		
		em.close();
	}

}

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
public class ConsultaFabricantesPeloModeloCarro {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		List<String> nomeDosFabricantes = em.createQuery("select mc.fabricante.nome from ModeloCarro mc", String.class)
									.getResultList();
		
		for (String nomeFabricante : nomeDosFabricantes) {
			System.out.println("Nome: " + nomeFabricante);
		}
		
		em.close();
	}
}

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
public class ConsultaModeloFiltroEmFabricanteECategoria {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		List<String> modelos = em.createQuery("select mc.descricao from ModeloCarro mc "
				+ "where mc.fabricante.nome = 'Honda' "
				+ "and mc.categoria in ('SEDAN_MEDIO', 'SEDAN_GRANDE') ", String.class).getResultList();

		for (String modelo : modelos) {
			System.out.println("Modelo: " + modelo);
		}
		
		em.close();
	}
}

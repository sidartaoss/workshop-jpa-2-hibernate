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
public class ConsultaModeloFiltrandoFabricante {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		/** Test 1 **/ 
		/**List<String> modelos = em.createQuery("select mc.descricao from ModeloCarro mc where mc.fabricante.nome = 'Honda'", String.class)
						.getResultList(); **/
		
		/** Test 2 **/
		List<String> modelos = em.createQuery("select mc.descricao from ModeloCarro mc where mc.fabricante.nome = 'Chevrolet'", String.class)
				.getResultList();		
		
		 for (String modelo : modelos) {
			System.out.println(modelo);
		}
		
		em.close();
	}
}

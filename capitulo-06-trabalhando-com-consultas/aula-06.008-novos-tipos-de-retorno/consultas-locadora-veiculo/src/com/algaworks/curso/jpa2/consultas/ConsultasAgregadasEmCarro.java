/**
 * 
 */
package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.util.JPAUtil;

/**
 * @author sosilva
 *
 */
public class ConsultasAgregadasEmCarro {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		/** Version 1 **/
//		String jpql = "select c, count(a), max(a.valorTotal), avg(a.valorTotal) "
//				+ "from Carro c JOIN c.alugueis a "
//				+ "group by c ";
		/** Version 2 **/
		String jpql = "select c, count(a), sum(a.valorTotal), min(a.valorTotal), max(a.valorTotal), avg(a.valorTotal) "
				+ "from Carro c JOIN c.alugueis a "
				+ "group by c "		
				+ "having count(a) > 1 ";
		
		List<Object[]> resultados = em.createQuery(jpql).getResultList();
		
		for (Object[] obj : resultados) {
			System.out.println("Modelo: " + ((Carro) obj[0]).getModelo().getDescricao());
			System.out.println("Quantidade de alugueis: " + obj[1]);
			System.out.println("Soma dos alugueis: " + obj[2]);
			System.out.println("Valor Minimo: " + obj[3]);
			System.out.println("Valor maximo: " + obj[4]);
			System.out.println("Valor medio: " + obj[5]);
			System.out.println();
		}
		
		em.close();
	}
}

/**
 * 
 */
package com.algaworks.curso.jpa2.consultas;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import com.algaworks.curso.jpa2.util.JPAUtil;

/**
 * @author sosilva
 *
 */
public class ConsultaAluguelPorDataEntrega {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		String jpql = "select count(a) "
				+ "from Aluguel a "
				+ "where a.dataEntrega >= :inicio AND a.dataDevolucao <= :fim ";
		
		/** Test 1 **/
		Calendar inicioCalendar = Calendar.getInstance();
		inicioCalendar.set(2019, 0, 21);  // 21 de Janeiro de 2019.
		Date inicio = inicioCalendar.getTime();
		
		Calendar fimCalendar = Calendar.getInstance();
		fimCalendar.set(2019, 0, 28, 20, 0);  // 28 de Janeiro de 2019 20:00 horas. 
		Date fim = fimCalendar.getTime();
		
		Number quantidadeDevolucoes = (Number) em.createQuery(jpql, Number.class)
				.setParameter("inicio", inicio, TemporalType.DATE)
				.setParameter("fim", fim, TemporalType.TIMESTAMP)
				.getSingleResult();
		
		System.out.println("Quantidade de devolucoes: " + quantidadeDevolucoes.intValue());
		
		em.close();
	}
}

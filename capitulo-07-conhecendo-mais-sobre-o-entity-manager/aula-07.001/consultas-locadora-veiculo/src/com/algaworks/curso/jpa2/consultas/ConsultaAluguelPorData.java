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
public class ConsultaAluguelPorData {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		String jpql = "select count(a) "
				+ "from Aluguel a "
				+ "where a.dataDevolucao BETWEEN :inicio AND :fim";
		
		/** Test 1 **/
//		Calendar inicioCalendar = Calendar.getInstance();
//		inicioCalendar.set(2019, 0, 25, 7, 0);  // 25 de Janeiro de 2019 7:00 horas.
//		Date inicio = inicioCalendar.getTime();
//		
//		Calendar fimCalendar = Calendar.getInstance();
//		fimCalendar.set(2019,  0, 30, 16, 0);  // 30 de Janeiro de 2019 16:00 horas. 
//		Date fim = fimCalendar.getTime();
		
		/** Test 2 **/
		Calendar inicioCalendar = Calendar.getInstance();
		inicioCalendar.set(2019, 0, 30, 16, 0);  // 30 de Janeiro de 2019 16:00 horas.
		Date inicio = inicioCalendar.getTime();
		
		Calendar fimCalendar = Calendar.getInstance();
		fimCalendar.set(2019,  0, 31, 18, 0);  // 31 de Janeiro de 2019 18:00 horas. 
		Date fim = fimCalendar.getTime();		
		
		Number quantidadeDevolucoes = (Number) em.createQuery(jpql, Number.class)
				.setParameter("inicio", inicio, TemporalType.TIMESTAMP)
				.setParameter("fim", fim, TemporalType.TIMESTAMP)
				.getSingleResult();
		
		System.out.println("Quantidade de devolucoes: " + quantidadeDevolucoes.intValue());
		
		em.close();
	}
}

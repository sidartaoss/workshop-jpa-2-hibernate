/**
 * 
 */
package com.algaworks.curso.jpa2.consultas;

import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.info.AluguelCarroInfo;
import com.algaworks.curso.jpa2.util.JPAUtil;

/**
 * @author sosilva
 *
 */
public class ConsultasAgregadasEmCarroComNovoTipoRetorno {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();

		String jpql = "" + "select NEW com.algaworks.curso.jpa2.info.AluguelCarroInfo( "
				+ "		c, count(a), max(a.valorTotal), avg(a.valorTotal) " 
				+ ") " 
				+ "from Carro c JOIN c.alugueis a "
				+ "group by c " + "having count(a) > 1 ";

		List<AluguelCarroInfo> resultados = em.createQuery(jpql, AluguelCarroInfo.class).getResultList();

		for (AluguelCarroInfo aci : resultados) {
			System.out.println("Modelo: " + aci.getCarro().getModelo().getDescricao());
			System.out.println("Quantidade de alugueis: " + aci.getQuantidadeAlugueis());
			System.out.println("Valor maximo: " + aci.getValorMaximo());
			System.out.println("Valor medio: " + aci.getValorMedio());
			System.out.println();
		}

		em.close();
	}
}

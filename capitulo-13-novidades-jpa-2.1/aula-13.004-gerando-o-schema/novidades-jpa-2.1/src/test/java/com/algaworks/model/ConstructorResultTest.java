/**
 * 
 */
package com.algaworks.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.Before;
import org.junit.Test;

import com.algaworks.relatorio.AlertasPorUsuario;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

/**
 * @author SEMPR
 *
 */
public class ConstructorResultTest {

	
	private JIntegrity helper;
	
	private EntityManager manager;
	
	@Before
	public void init() {
		this.helper = new JIntegrity();
		this.helper.useMySQL();
		this.helper.cleanAndInsert("Usuario", "Alert");
		
		this.manager = JPAHelper.currentEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deveRetornarAlertasPorUsuario() {
		List<AlertasPorUsuario> lista = this.manager.createNamedQuery("alertasPorUsuario").getResultList();
		
//		for (AlertasPorUsuario a : lista) {
//			System.out.println(a);
//		}
		
		// lista.forEach((AlertasPorUsuario a) -> System.out.println(a));
//		lista.forEach(a -> System.out.println(a));
		lista.forEach(System.out::println);
		
	}
}

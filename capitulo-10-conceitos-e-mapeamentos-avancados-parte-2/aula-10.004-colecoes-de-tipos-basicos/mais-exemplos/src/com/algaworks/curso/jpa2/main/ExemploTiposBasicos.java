/**
 * 
 */
package com.algaworks.curso.jpa2.main;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.ProprietarioColecoesTiposBasicos;
import com.algaworks.curso.jpa2.util.JPAUtil;

/**
 * @author SEMPR
 *
 */
public class ExemploTiposBasicos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		/** 1. CADASTRAR. **/
		em.getTransaction().begin();
		
		ProprietarioColecoesTiposBasicos proprietario = new ProprietarioColecoesTiposBasicos();
		
		proprietario.setNome("Joao");
		proprietario.getTelefones().add("(34) 1234-5678");
		proprietario.getTelefones().add("(11) 9876-5432");
		
		em.persist(proprietario);
		em.getTransaction().commit();
		
		em.close();
	}

}

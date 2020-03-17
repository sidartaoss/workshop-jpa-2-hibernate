/**
 * 
 */
package com.algaworks.curso.jpa2.main;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.ProprietarioColecoesTiposBasicos;
import com.algaworks.curso.jpa2.modelo.Telefone;
import com.algaworks.curso.jpa2.util.JPAUtil;

/**
 * @author SEMPR
 *
 */
public class ExemploObjetosEmbutidos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.createEntityManager();
		
		em.getTransaction().begin();
		
		ProprietarioColecoesTiposBasicos proprietario = new ProprietarioColecoesTiposBasicos();
		proprietario.setNome("Maria");
		proprietario.getTelefones().add(new Telefone("34", "1234-5678", "104"));
		proprietario.getTelefones().add(new Telefone("11", "9876-5432", null));
		
		em.persist(proprietario);
		
		em.getTransaction().commit();
		em.close();
		
	}

}

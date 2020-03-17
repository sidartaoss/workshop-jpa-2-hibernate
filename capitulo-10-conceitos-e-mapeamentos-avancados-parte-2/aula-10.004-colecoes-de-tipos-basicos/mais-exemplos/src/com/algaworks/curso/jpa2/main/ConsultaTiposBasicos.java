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
public class ConsultaTiposBasicos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		/** 2. CONSULTAR. **/
		
		ProprietarioColecoesTiposBasicos proprietario = 
				em.find(ProprietarioColecoesTiposBasicos.class, 4L);
		
		System.out.println("Nome: " + proprietario.getNome());
		
		for (String telefone : proprietario.getTelefones()) {
			System.out.println("Telefone: " + telefone);
		}
		
		em.close();
	}

}

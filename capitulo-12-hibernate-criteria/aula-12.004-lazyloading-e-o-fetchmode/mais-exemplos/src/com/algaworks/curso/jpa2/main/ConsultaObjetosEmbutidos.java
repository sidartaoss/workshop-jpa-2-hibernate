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
public class ConsultaObjetosEmbutidos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.createEntityManager();
		
		ProprietarioColecoesTiposBasicos proprietario = em.find(ProprietarioColecoesTiposBasicos.class, 5L);
		
		System.out.println();
		
		System.out.println("Nome: " + proprietario.getNome());
		
		System.out.println();
		
		for (Telefone telefone : proprietario.getTelefones()) {
			System.out.println("Telefone: (" + telefone.getPrefixo() + ") "
					+ telefone.getNumero() 
					+ (telefone.getRamal() != null ? " x" + telefone.getRamal() : "")
			);
		}
		
		em.close();
		
	}

}

/**
 * 
 */
package com.algaworks.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author SEMPR
 *
 */
public class GerarTabelas {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("novidadesJPA21PU");
	
	public static EntityManager createEntityManager() {
		return factory.createEntityManager();
	}
	
	public static void main(String[] args) {
		/** 
		 * Executando esse codigo antes dos testes, ira fazer o Hibernate gerar as tabelas 
		 * a partir do arquivo persistence.xml.
		 * **/
		EntityManager manager = GerarTabelas.createEntityManager();
		manager.close();
		System.out.println(">>>>> Tabelas geradas com sucesso! <<<<<");
	}

}

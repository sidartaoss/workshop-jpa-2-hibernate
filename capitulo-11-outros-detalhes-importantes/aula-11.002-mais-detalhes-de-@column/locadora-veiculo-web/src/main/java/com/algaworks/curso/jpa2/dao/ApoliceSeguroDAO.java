/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.ApoliceSeguro;

/**
 * @author SEMPR
 *
 */
public class ApoliceSeguroDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public void salvar(ApoliceSeguro apoliceSeguro) {
		this.em.persist(apoliceSeguro);
	}
	

	
}

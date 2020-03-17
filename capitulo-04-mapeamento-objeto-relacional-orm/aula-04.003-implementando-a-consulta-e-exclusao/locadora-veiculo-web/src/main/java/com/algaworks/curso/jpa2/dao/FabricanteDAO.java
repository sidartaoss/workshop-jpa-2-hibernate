/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

/**
 * @author sosilva
 *
 */
public class FabricanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	public void salvar(Fabricante fabricante) {
		em.persist(fabricante);
	}

	@Transactional
	public void excluir(Fabricante fabricante)  throws NegocioException {
		fabricante = em.find(Fabricante.class, fabricante.getCodigo());
		this.em.remove(fabricante);
		this.em.flush();
	}

	public List<Fabricante> buscarTodos() {
		return this.em.createQuery("from Fabricante", Fabricante.class).getResultList();
	}
	
}

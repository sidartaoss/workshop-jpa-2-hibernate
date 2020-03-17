/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

/**
 * @author SEMPR
 *
 */
public class MotoristaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public Motorista buscarPeloCodigo(Long codigo) {
		return this.em.find(Motorista.class, codigo);
	}
	
	public List<Motorista> buscarTodos() {
		return this.em.createQuery("from Motorista", Motorista.class).getResultList();
	}
	
	public void salvar(Motorista motorista) {
		this.em.merge(motorista);
	}
	
	@Transactional
	public void excluir(Motorista motorista) throws NegocioException {
		motorista = this.buscarPeloCodigo(motorista.getCodigo());
		try {
			this.em.remove(motorista);
			this.em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Motorista nao pode ser excluido.");
		}
	}

}
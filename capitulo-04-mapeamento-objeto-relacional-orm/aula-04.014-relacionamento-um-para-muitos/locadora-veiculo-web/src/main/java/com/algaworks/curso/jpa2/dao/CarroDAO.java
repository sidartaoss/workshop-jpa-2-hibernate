/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

/**
 * @author SEMPR
 *
 */
public class CarroDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public Carro buscarPeloCodigo(Long codigo) {
		return this.em.find(Carro.class, codigo);
	}
	
	public List<Carro> buscarTodos() {
		return this.em.createQuery("from Carro", Carro.class).getResultList();
	}
	
	public void salvar(Carro carro) {
		this.em.merge(carro);
	}
	
	@Transactional
	public void excluir(Carro carro) throws NegocioException {
		carro = this.buscarPeloCodigo(carro.getCodigo());
		try {
			this.em.remove(carro);
			this.em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Carro nao pode ser excluido.");
		}
	}

}

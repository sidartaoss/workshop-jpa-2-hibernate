/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

/**
 * @author SEMPR
 *
 */
public class AluguelDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private ApoliceSeguroDAO apoliceSeguroDAO;
	
	public Aluguel buscarPeloCodigo(Long codigo) {
		return this.em.find(Aluguel.class, codigo);
	}
	
	public void salvar(Aluguel aluguel) {
		/** Aula 04.013. Mapeamento  Um-Para-Um **/
		this.apoliceSeguroDAO.salvar(aluguel.getApoliceSeguro());
		/** Aula 04.013. Mapeamento  Um-Para-Um **/
		this.em.merge(aluguel);
	}

	@Transactional
	public void excluir(Aluguel aluguel) throws NegocioException {
			aluguel = this.buscarPeloCodigo(aluguel.getCodigo());
		try {
			this.em.remove(aluguel);
			this.em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Aluguel nao pode ser excluido!");
		}
	}
	
}

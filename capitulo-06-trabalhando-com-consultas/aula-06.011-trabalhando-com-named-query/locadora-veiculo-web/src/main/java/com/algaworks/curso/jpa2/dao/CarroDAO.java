/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscarTodos() {
		/** Version 1. **/
		/** return this.em.createQuery("from Carro", Carro.class).getResultList(); **/
		/** Version 2. **/
		/** return this.em.createNamedQuery("buscarTodos").getResultList(); **/
		/** Version 3. **/
		return this.em.createNamedQuery("Carro.buscarTodos").getResultList();
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

	public Carro buscarCarroComAcessorios(Long codigo) {
		/** Version 1. **/
		/** return (Carro) this.em.createQuery("from Carro c JOIN c.acessorios a where c.codigo = ?") **/
		/** Version 2. **/		
//		return (Carro) this.em.createQuery(" select c from Carro c JOIN c.acessorios a where c.codigo = ? ")
//				.setParameter(1, codigo)
//				.getSingleResult();
		/** Version 3 **/
		try {
			return this.em.createNamedQuery("Carro.buscarCarroComAcessorios", Carro.class)
					.setParameter("codigo", codigo)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}

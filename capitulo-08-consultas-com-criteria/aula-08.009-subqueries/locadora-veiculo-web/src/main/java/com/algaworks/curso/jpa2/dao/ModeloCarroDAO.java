/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

/**
 * @author SEMPR
 *
 */
public class ModeloCarroDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public ModeloCarro buscarPeloCodigo(Long codigo) {
		return this.em.find(ModeloCarro.class, codigo);
	}
	
	public void salvar(ModeloCarro modeloCarro) {
		this.em.merge(modeloCarro);
	}
	
	public List<ModeloCarro> buscarTodos() {
		return this.em.createNamedQuery("ModeloCarro.buscarTodos", ModeloCarro.class).getResultList();
	}
	
	@Transactional
	public void excluir(ModeloCarro modeloCarro) throws NegocioException {
		modeloCarro = this.buscarPeloCodigo(modeloCarro.getCodigo());
		try {
			this.em.remove(modeloCarro);
			this.em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Este modelo nao pode ser excluido.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<ModeloCarro> buscarComPaginacao(int first, int pageSize) {
		return this.em.createNamedQuery("ModeloCarro.buscarTodos")
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Number encontrarQuantidadeDeModelosCarro() {
		return this.em.createQuery("select count(mc) from ModeloCarro mc", Number.class).getSingleResult();
	}

}

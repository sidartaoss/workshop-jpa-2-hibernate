/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

/**
 * @author SEMPR
 *
 */
public class AcessorioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public Acessorio buscarPeloCodigo(Long codigo) {
		return this.em.find(Acessorio.class, codigo);
	}
	
	public void salvar(Acessorio acessorio) {
		this.em.merge(acessorio);
	}
	
	public List<Acessorio> buscarTodos() {
		return this.em.createQuery("from Acessorio", Acessorio.class).getResultList();
	}
	
	@Transactional
	public void excluir(Acessorio acessorio) throws NegocioException {
		acessorio = this.buscarPeloCodigo(acessorio.getCodigo());
		try {
			this.em.remove(acessorio);
			this.em.flush();
		} catch (Exception e) {
			throw new NegocioException("Acessorio nao pode ser excluido!");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Acessorio> buscarComPaginacao(int first, int pageSize) {
		return this.em.createNamedQuery("Acessorio.buscarTodos")
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Number encontrarQuantidadeDeAcessorios() {
		return this.em.createQuery(
				"select count(a) from Acessorio a", Number.class)
				.getSingleResult();
	}

}

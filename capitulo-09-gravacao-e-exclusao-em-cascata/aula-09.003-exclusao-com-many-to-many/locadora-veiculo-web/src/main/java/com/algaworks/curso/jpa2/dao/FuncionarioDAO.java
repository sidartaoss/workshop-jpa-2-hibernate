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
import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

/**
 * @author SEMPR
 *
 */
public class FuncionarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public Funcionario buscarPeloCodigo(Long codigo) {
		return this.em.find(Funcionario.class, codigo);
	}
	
	public List<Funcionario> buscarTodos() {
		return this.em.createQuery("from Funcionario", Funcionario.class).getResultList();
	}
	
	public void salvar(Funcionario funcionario) {
		this.em.merge(funcionario);
	}
	
	@Transactional
	public void excluir(Funcionario funcionario) throws NegocioException {
		funcionario = this.buscarPeloCodigo(funcionario.getCodigo());
		try {
			this.em.remove(funcionario);
			this.em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Funcionario nao pode ser excluido.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarComPaginacao(int first, int pageSize) {
		return this.em.createNamedQuery("Funcionario.buscarTodos")
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Number encontrarQuantidadeDeFuncionarios() {
		return this.em.createQuery("select count(f) from Funcionario f", Number.class).getSingleResult();
	}	

}

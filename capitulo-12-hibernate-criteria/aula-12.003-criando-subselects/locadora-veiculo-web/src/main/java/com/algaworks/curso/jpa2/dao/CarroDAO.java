/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.algaworks.curso.jpa2.modelo.Aluguel;
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
			throw new NegocioException("Carropu nao pode ser excluido.");
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
		return this.em.createNamedQuery("Carro.buscarCarroComAcessorios", Carro.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Carro> buscarComPaginacao(int first, int pageSize) {
		return this.em.createNamedQuery("Carro.buscarTodos")
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Number encontrarQuantidadeDeCarros() {
		return this.em.createQuery("select count(c) from Carro c", Number.class).getSingleResult();
	}

	public void setEntityManager(EntityManager currentEntityManager) {
		this.em = currentEntityManager;
	}

	@RequestScoped
	public Session getSession() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
		return (Session) factory.createEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscarCarrosNuncaAlugados() {
		/** Session session = this.em.unwrap(Session.class); **/
		Session session = this.getSession();
		
		/** select * from Carro */
		Criteria criteria = session.createCriteria(Carro.class);
		
		/** 
		 *         select * 
			          from carro
			          where codigo not in (
			              select codigo_carro from aluguel 
			                  where codigo_carro is not null
		 * **/
		
		/** select codigo_carro from Aluguel where codigo_carro is not null **/
		DetachedCriteria criteriaAluguel = DetachedCriteria.forClass(Aluguel.class)
				.setProjection(Projections.property("carro"))
				.add(Restrictions.isNotNull("carro")
		);
		
		/** where codigo not in ( select codigo_carro from Aluguel where codigo_carro is not null ) **/
		criteria.add(Property.forName("codigo").notIn(criteriaAluguel));
		
		return criteria.list();
		
	}

}

/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
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
//		this.apoliceSeguroDAO.salvar(aluguel.getApoliceSeguro());
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

	public List<Aluguel> buscarPorDataDeEntregaEModeloCarro(Date dataEntrega, ModeloCarro modelo) {
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		
		CriteriaQuery<Aluguel> criteriaQuery = builder.createQuery(Aluguel.class);
		
		// from Aluguel a
		Root<Aluguel> a = criteriaQuery.from(Aluguel.class);
		
		// select a from Aluguel a
		criteriaQuery.select(a);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (dataEntrega != null) {
			
			ParameterExpression<Date> dataEntregaInicial = builder.parameter(Date.class, "dataEntregaInicial");
			ParameterExpression<Date> dataEntregaFinal = builder.parameter(Date.class, "dataEntregaFinal");
			
			predicates.add(builder.between(a.<Date>get("dataEntrega"), dataEntregaInicial, dataEntregaFinal));
			
		}
		
		if (modelo != null) {
			
			ParameterExpression<ModeloCarro> modeloExpression = builder.parameter(ModeloCarro.class, "modelo");
			
			predicates.add(builder.equal(a.get("carro").get("modelo"), modeloExpression));
			
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Aluguel> query = this.em.createQuery(criteriaQuery);
		
		if (dataEntrega != null) {
			
			Calendar dataEntregaInicial = Calendar.getInstance();
			dataEntregaInicial.setTime(dataEntrega);
			dataEntregaInicial.set(Calendar.HOUR_OF_DAY, 0);
			dataEntregaInicial.set(Calendar.MINUTE, 0);
			dataEntregaInicial.set(Calendar.SECOND, 0);
			
			Calendar dataEntregaFinal = Calendar.getInstance();
			dataEntregaFinal.setTime(dataEntrega);
			dataEntregaFinal.set(Calendar.HOUR_OF_DAY, 23);
			dataEntregaFinal.set(Calendar.MINUTE, 59);
			dataEntregaFinal.set(Calendar.SECOND, 59);
			
			query.setParameter("dataEntregaInicial", dataEntregaInicial.getTime());
			query.setParameter("dataEntregaFinal", dataEntregaFinal.getTime());
			
		}
		
		if (modelo != null) {
			query.setParameter("modelo", modelo);
		}
		
		return query.getResultList(); 
	}
	
	/** 
	 * https://pt.stackoverflow.com/questions/158663/como-buscar-a-session-no-hibernate-5-2-3-final
	 * 
	 * Caso não tenha resolvido... Resolvi assim:

		@RequestScoped
		public Session createEntityManager() {
		    return (Session) this.factory.createEntityManager();
		}
		
		public void closeEntityManager(@Disposes Session manager) {
		    manager.close();
		}
		Se olhar o interface Session do hibernate, vais ver que agora é também implementa EntityManager, dai por baixo dos panos dá "tilt" no Weld. Na verdade o Tiago da Algaworks que foi mais a fundo nisso, apenas usei a mesma ideia dele
		
		public interface Session extends SharedSessionContract, EntityManager, HibernateEntityManager, AutoCloseable {}
	 * **/
	@RequestScoped
	public Session createHibernateSession() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	    return (Session) factory.createEntityManager();
	}	

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Aluguel> buscarPorDataDeEntregaEModeloCarroCriteria(Date dataEntrega, ModeloCarro modelo) {
//		Session session = this.em.unwrap(Session.class);
		Session session = this.createHibernateSession();
		Criteria criteria = session.createCriteria(Aluguel.class);
		
		if (dataEntrega != null) {
			criteria.add(Restrictions.between("dataEntrega", this.geraDataInicial(dataEntrega), this.geraDataFinal(dataEntrega)));
		}
		
		if (modelo != null) {
			criteria.createAlias("carro", "c");
			criteria.add(Restrictions.eq("c.modelo", modelo));
		}
		
		return criteria.list();
	}
	
	public BigDecimal calcularTotalDoMesDe(int mes) {
		Session session = this.createHibernateSession();
		
		Criteria criteria = session.createCriteria(Aluguel.class);
		
		criteria.setProjection(Projections.sum("valorTotal"));
		
		criteria.add(Restrictions.sqlRestriction("month(data_pedido) = ?", mes, StandardBasicTypes.INTEGER));
		
		return (BigDecimal) criteria.uniqueResult();
	}
	
	private Date geraDataInicial(Date dataEntrega) {
		Calendar dataEntregaInicial = Calendar.getInstance();
		dataEntregaInicial.setTime(dataEntrega);
		dataEntregaInicial.set(Calendar.HOUR_OF_DAY, 0);
		dataEntregaInicial.set(Calendar.MINUTE, 0);
	    dataEntregaInicial.set(Calendar.SECOND, 0);    
	    return dataEntregaInicial.getTime();
	}	

	private Date geraDataFinal(Date dataEntrega) {
	    Calendar dataEntregaFinal = Calendar.getInstance();
	    dataEntregaFinal.setTime(dataEntrega);
	    dataEntregaFinal.set(Calendar.HOUR_OF_DAY, 23);
	    dataEntregaFinal.set(Calendar.MINUTE, 59);
	    dataEntregaFinal.set(Calendar.SECOND, 59);    
	    return dataEntregaFinal.getTime();
	}

}

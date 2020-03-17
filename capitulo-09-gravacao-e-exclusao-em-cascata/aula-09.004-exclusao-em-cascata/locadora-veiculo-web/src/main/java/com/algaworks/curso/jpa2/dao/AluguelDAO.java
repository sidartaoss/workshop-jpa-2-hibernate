/**
 * 
 */
package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
}

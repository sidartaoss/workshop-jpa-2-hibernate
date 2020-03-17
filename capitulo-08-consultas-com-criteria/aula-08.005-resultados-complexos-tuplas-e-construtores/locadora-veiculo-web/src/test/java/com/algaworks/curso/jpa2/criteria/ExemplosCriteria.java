/**
 * 
 */
package com.algaworks.curso.jpa2.criteria;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.Carro;

/**
 * @author SEMPR
 *
 */
public class ExemplosCriteria {
	
	private static EntityManagerFactory factory;
	
	private EntityManager manager;
	
	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}
	
	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();
	}
	
	@After
	public void tearDown() {
		this.manager.close(); 
	}
	
	@Test
	public void projecoes() {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
		
		// from Carro c
		Root<Carro> c = criteriaQuery.from(Carro.class);
		
		// select c.placa from Carro c
		criteriaQuery.select(c.<String>get("placa"));
		
		TypedQuery<String> query = this.manager.createQuery(criteriaQuery);
		
		List<String> placas = query.getResultList();
		
		for (String placa : placas) {
			System.out.println(placa);
		}
		
	}
	
	@Test
	public void funcoesDeAgregacao() {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		
		// builder.max()
		
		// builder.sum()
		
		// builder.min()
		
		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);
		
		Root<Aluguel> a = criteriaQuery.from(Aluguel.class);
		
		// select sum(a.valorTotal) from Aluguel a
		
		criteriaQuery.select(builder.sum(a.<BigDecimal>get("valorTotal")));
		
		TypedQuery<BigDecimal> query = this.manager.createQuery(criteriaQuery);
		
		BigDecimal total = query.getSingleResult();
		
		System.out.println("Soma de todos os alugueis: " + total);
		
	}
	
	@Test
	public void resultadoComplexo() {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		
		// from Carro c
		Root<Carro> c = criteriaQuery.from(Carro.class);
		
		// select c.placa, c.valorDiaria from Carro c
		criteriaQuery.multiselect(c.get("placa"), c.get("valorDiaria"));
		
		TypedQuery<Object[]> query = this.manager.createQuery(criteriaQuery);
		
		List<Object[]> resultado = query.getResultList();
		
		for (Object[] valores : resultado) {
			System.out.println(valores[0] + " - " + valores[1]);
		}
		
	}
	
	@Test
	public void resultadoTupla() {
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
		
		// from Carro c
		Root<Carro> c = criteriaQuery.from(Carro.class);
		
		criteriaQuery.multiselect(c.get("placa").alias("placaCarro"), c.get("valorDiaria").alias("valorCarro"));
		
		TypedQuery<Tuple> query = this.manager.createQuery(criteriaQuery);
		
		List<Tuple> resultado = query.getResultList();
		
		for (Tuple tupla : resultado) {
			System.out.println(tupla.get("placaCarro") + " - " + tupla.get("valorCarro"));
		}
		
	}
	
	@Test
	public void resultadoConstrutores() { 
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		
		CriteriaQuery<PrecoCarro> criteriaQuery = builder.createQuery(PrecoCarro.class);
		
		Root<Carro> c = criteriaQuery.from(Carro.class);
		
		criteriaQuery.select(builder.construct(PrecoCarro.class, 
						c.get("placa"),
						c.get("valorDiaria"))
				);
		
		TypedQuery<PrecoCarro> query = this.manager.createQuery(criteriaQuery);
		
		List<PrecoCarro> resultado = query.getResultList();
		
		for (PrecoCarro precoCarro : resultado) {
			System.out.println(precoCarro.getPlaca() + " - " + precoCarro.getValor());
		}
	}
}

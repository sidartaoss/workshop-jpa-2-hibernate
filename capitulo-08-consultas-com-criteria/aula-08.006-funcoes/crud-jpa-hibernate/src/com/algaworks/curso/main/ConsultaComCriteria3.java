package com.algaworks.curso.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.algaworks.curso.modelo.Cliente;
import com.algaworks.curso.util.jpa.JPAUtil;

public class ConsultaComCriteria3 {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		// select, from, where, like, etc... -> from(), where()...
		// JPQL: from Cliente
		
		// JPQL: select c from Cliente c where c.nome like = 'Fernando%'
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);
		
		// JPQL: select c >>> from Cliente c <<< where c.nome like = 'Fernando%'
		Root<Cliente> c = criteriaQuery.from(Cliente.class);
		
		// JPQL: >>> select c from Cliente c <<< where c.nome like = 'Fernando%'
		criteriaQuery.select(c);
		
		// JPQL: select c from Cliente c >>> where c.nome like = 'Fernando%' <<<
		criteriaQuery.where(builder.like(c.<String>get("nome"), "Fernando%"));
		
		List<Cliente> clientes = em.createQuery(criteriaQuery).getResultList();
		
		
		for (Cliente cliente : clientes) {
			System.out.println("Codigo: " + cliente.getCodigo());
			System.out.println("Nome: " + cliente.getNome());
		}		
		
		em.close();
	}
}

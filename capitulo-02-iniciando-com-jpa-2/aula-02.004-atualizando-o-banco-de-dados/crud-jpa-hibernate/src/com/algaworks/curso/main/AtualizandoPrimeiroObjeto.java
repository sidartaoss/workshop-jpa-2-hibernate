package com.algaworks.curso.main;

import javax.persistence.Persistence;

import com.algaworks.curso.modelo.Cliente;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

public class AtualizandoPrimeiroObjeto {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemploPU");
		EntityManager em = emf.createEntityManager();
		
		Cliente cliente = em.find(Cliente.class, 1L);
		
		em.getTransaction().begin();
		
		cliente.setNome("Jose da Silva Pereira");
		cliente.setIdade(28);
		
		em.getTransaction().commit();
		
		System.out.println("Objeto alterado com sucesso!");
		
	}

}

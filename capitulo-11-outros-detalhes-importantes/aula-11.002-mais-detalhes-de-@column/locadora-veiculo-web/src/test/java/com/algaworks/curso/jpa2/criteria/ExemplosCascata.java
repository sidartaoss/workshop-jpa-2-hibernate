/**
 * 
 */
package com.algaworks.curso.jpa2.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Categoria;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

/**
 * @author SEMPR
 *
 */
public class ExemplosCascata {

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
	public void exemploEntidadeTransiente() {
		Carro carro  = new Carro();
		carro.setCor("Preto");
		carro.setPlaca("AAA-1111");
		
		ModeloCarro modelo = new ModeloCarro();
		modelo.setCategoria(Categoria.ESPORTIVO);
		modelo.setDescricao("Ferrari");
		carro.setModelo(modelo);
		
		this.manager.getTransaction().begin();
		this.manager.persist(carro);
		this.manager.getTransaction().commit();
		
	};
	
	@Test
	public void exemploCarroAcessorios() {
		Carro carro = new Carro();
		carro.setCor("Prata");
		carro.setPlaca("ABC-1234");
		
		Acessorio acessorio = new Acessorio();
		acessorio.setDescricao("Protetor de Porta de Carro");
		List<Acessorio> acessorios = new ArrayList<>();
		acessorios.add(acessorio);
		
		acessorio = new Acessorio();
		acessorio.setDescricao("Organizador para Assento Traseiro");
		acessorios.add(acessorio);
		
		acessorio = new Acessorio();
		acessorio.setDescricao("Hud Display Universal");
		acessorios.add(acessorio);	
		
		acessorio = new Acessorio();
		acessorio.setDescricao("Banco Esportivo Concha");
		acessorios.add(acessorio);	
		
		acessorio = new Acessorio();
		acessorio.setDescricao("Porta Moedas Automotivo");
		acessorios.add(acessorio);
		
		carro.setAcessorios(acessorios);
		
		this.manager.getTransaction().begin();
		this.manager.persist(carro);
		this.manager.getTransaction().commit();
				
	}
	
	@Test
	public void exclusaoEmCascata() {
		Carro carro = this.manager.find(Carro.class, 2L);
		
		this.manager.getTransaction().begin();
		this.manager.remove(carro);
		this.manager.getTransaction().commit();
	}
	
	@Test
	public void exclusaoDeObjetosOrfaos() {
		Carro carro = this.manager.find(Carro.class, 6L);
		
		this.manager.getTransaction().begin();
		carro.getAlugueis().remove(0);
		this.manager.getTransaction().commit();
		
	}
	
}

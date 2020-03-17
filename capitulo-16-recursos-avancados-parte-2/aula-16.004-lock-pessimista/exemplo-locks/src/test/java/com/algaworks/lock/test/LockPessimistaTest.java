package com.algaworks.lock.test;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.algaworks.lock.model.Cliente;
import com.algaworks.lock.model.Conta;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class LockPessimistaTest {

	private JIntegrity helper;
	
	private EntityManager manager;
	
	@Before
	public void init() {
		helper = new JIntegrity();
		helper.useMySQL();
		
		helper.cleanAndInsert();
		
		manager = JPAHelper.currentEntityManager();
		
	}
	
	@After
	public void end() {
		manager.getTransaction().commit();
	}
	
	@Test
	public void deveAdicionarUmClienteAConta() {
		Cliente cliente = new Cliente();
		cliente.setNome("Maria");
		
		cliente = this.manager.merge(cliente);
		this.manager.flush();
		
//		Conta conta = this.manager.find(Conta.class, 2L);
		Conta conta = this.manager.find(Conta.class, 2L, LockModeType.PESSIMISTIC_READ);
		conta.setClientes(new ArrayList<>());
		conta.getClientes().add(cliente);
		
		System.out.println("\n\nEsperando...\n\n");
		
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.manager.merge(conta);
		
	}
	
	
	
	@Test
	public void deveTransferirValor() {
//		Conta conta1 = this.manager.find(Conta.class, 1L);
//		Conta conta2 = this.manager.find(Conta.class, 2L);
		
		Conta conta1 = this.manager.find(Conta.class, 1L, LockModeType.PESSIMISTIC_WRITE);
		Conta conta2 = this.manager.find(Conta.class, 2L, LockModeType.PESSIMISTIC_WRITE);
		
		BigDecimal valor = new BigDecimal("30");
		
		conta1.setSaldo(conta1.getSaldo().subtract(valor));
		
		this.manager.flush();
		
		System.out.println("\n\n Esperando... \n\n");
		
		try {
			Thread.sleep(12000L);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		conta2.setSaldo(conta2.getSaldo().add(valor));
	}
	
}

/**
 * 
 */
package com.algaworks.curso.main;

import java.math.BigDecimal;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.curso.model.Conta;

/**
 * @author sosilva
 *
 */
public class Transferencia {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoTabajaraPU");
		EntityManager em = emf.createEntityManager();
		
//		Conta conta = new Conta();
//		conta.setAgencia("0280");
//		conta.setNumero("3522885544");
//		em.getTransaction().begin();
//		em.persist(conta);
//		em.getTransaction().commit();
		
//		Conta conta = em.find(Conta.class, 1L);
//		if (conta != null) {
//			System.out.println("Agência: " + conta.getAgencia());
//			System.out.println("Conta: " + conta.getNumero());
//		} else {
//			System.out.println("Nenhum registro encontrado!");
//		}
		
		Scanner entrada = new Scanner(System.in);
//		Conta conta1 = em.find(Conta.class, 1L);
		Conta conta1 = em.find(Conta.class, 2L);
		if (conta1 == null) {
			System.out.print("Digite o saldo inicial da conta 1: ");
			BigDecimal saldoInicialConta1 = BigDecimal.valueOf(entrada.nextDouble());
			conta1 = new Conta();
			conta1.setSaldo(saldoInicialConta1);
		}
		
//		Conta conta2 = em.find(Conta.class, 2L);
		Conta conta2 = em.find(Conta.class, 3L);
		if (conta2 == null) {
			System.out.print("Digite o saldo inicial da conta 2: ");
			BigDecimal saldoInicialConta2 = BigDecimal.valueOf(entrada.nextDouble());
			conta2 = new Conta();
			conta2.setSaldo(saldoInicialConta2);
		}		
		
		em.getTransaction().begin();
		em.persist(conta1);
		em.persist(conta2);
		em.getTransaction().commit();
		
		System.out.println("Saldo da conta1: " + conta1.getSaldo() 
					+ ". Saldo da conta2: " + conta2.getSaldo());
		
		System.out.println("--------------------------------------------------------");
		System.out.print("Digite o valor a retirar da conta 1 e depositar na conta 2: ");
		BigDecimal valorTransferencia = BigDecimal.valueOf(entrada.nextDouble());
		
		em.getTransaction().begin();
		conta1.setSaldo(conta1.getSaldo().subtract(valorTransferencia));
		conta2.setSaldo(conta2.getSaldo().add(valorTransferencia));
		
		if (conta1.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
			em.getTransaction().commit();
			System.out.println("Transferência realizada com sucesso!");
		} else {
			em.getTransaction().rollback();
			System.err.println("Tranferência não realizada, saldo insuficiente!");
		}
		
		
		
		
		
		
	}
}

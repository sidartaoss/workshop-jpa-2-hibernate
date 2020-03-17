/**
 * 
 */
package com.algaworks.curso.jpa2.main;

import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Veiculo;
import com.algaworks.curso.jpa2.modelo.VeiculoID;
import com.algaworks.curso.jpa2.util.JPAUtil;

/**
 * @author SEMPR
 *
 */
public class ExemploPropriedadeTransiente {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		
		Veiculo veiculo = em.find(Veiculo.class, new VeiculoID("AAA-1111", "Rio de Janeiro"));
		
//		System.out.println("Placa " + veiculo.getCodigo().getPlaca() + ". Fabricante " + veiculo.getFabricante() + ". Modelo " + veiculo.getModelo());
		
		System.out.println(veiculo.getDescricao());
		
		em.close();
	}

}

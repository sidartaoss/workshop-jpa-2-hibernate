///**
// * 
// */
//package com.algaworks.curso.jpa2.main;
//
//import javax.persistence.EntityManager;
//
//import com.algaworks.curso.jpa2.modelo.Proprietario;
//import com.algaworks.curso.jpa2.modelo.Veiculo;
//import com.algaworks.curso.jpa2.modelo.VeiculoID;
//import com.algaworks.curso.jpa2.util.JPAUtil;
//
///**
// * @author SEMPR
// *
// */
//public class ExemploObjetoEmbutido {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		EntityManager em = JPAUtil.createEntityManager();
//		
//		Veiculo veiculo = new Veiculo();
//		veiculo.setCodigo(new VeiculoID("AAA-1111", "Rio de Janeiro"));
//		veiculo.setFabricante("Volks");
//		veiculo.setModelo("Jeta");
//		
//		
//		Proprietario proprietario = new Proprietario();
//		proprietario.setNome("Joao da Silva");
//		proprietario.setTelefone("54991111111");
//		proprietario.setEmail("joao@silva.com");
//		veiculo.setProprietario(proprietario );
//		
//		em.getTransaction().begin();
//		em.persist(veiculo);
//		em.getTransaction().commit();
//		
//		em.close();
//	}
//
//}

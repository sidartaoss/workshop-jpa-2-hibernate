///**
// * 
// */
//package com.algaworks.curso.jpa2.main;
//
//import javax.persistence.EntityManager;
//
//import com.algaworks.curso.jpa2.modelo.Veiculo;
//import com.algaworks.curso.jpa2.modelo.VeiculoID;
//import com.algaworks.curso.jpa2.util.JPAUtil;
//
///**
// * @author SEMPR
// *
// */
//public class ExemploChaveComposta2 {
//
//	public static void main(String[] args) {
//		EntityManager manager = JPAUtil.createEntityManager();
//		
//		Veiculo veiculo = new Veiculo();
//		
//		veiculo.setCodigo(new VeiculoID("ABC-1235", "Ouro Preto"));
//		veiculo.setFabricante("Volks");
//		veiculo.setModelo("Gol");
//		
//		manager.getTransaction().begin();
//		manager.persist(veiculo);
//		manager.getTransaction().commit();
//		
////		Veiculo v = manager.find(Veiculo.class, new VeiculoID("ABC-1234", "Rio Claro")); 
//		VeiculoID codigo = new VeiculoID("ABC-1234", "Rio Claro");
//		Veiculo v = manager.find(Veiculo.class, codigo);
//		
//		System.out.println("Veiculo " + v.getCodigo().getPlaca() + " - " 
//						+ v.getCodigo().getCidade() + " - Fabricante: " + v.getFabricante());
//		
//		manager.close();
//	}
//}

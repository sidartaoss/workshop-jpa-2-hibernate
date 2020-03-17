package com.algaworks.pedidovenda.dao;

import java.util.List;

import com.algaworks.pedidovenda.model.ItemPedido;

//public class ItemPedidoDAO implements Serializable {
public interface ItemPedidoDAO extends GenericDAO<ItemPedido, Long> {

//	private static final long serialVersionUID = 1L;

//	@Inject
//	private EntityManager manager;
	
//	@SuppressWarnings("unchecked")
//	public List<ItemPedido> buscarItensPendentes() {
//	    Session session = this.manager.unwrap(Session.class);
//	    Criteria criteria = session.createCriteria(ItemPedido.class);		
//		
//	    criteria.createAlias("pedido", "p")
//	    		.add(Restrictions.eq("p.entrega", Entrega.PENDENTE));
//		
//		return criteria.list();
//	}
	List<ItemPedido> buscarItensPendentes();
}

package com.algaworks.pedidovenda.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.algaworks.pedidovenda.dao.ItemPedidoDAO;
import com.algaworks.pedidovenda.model.Entrega;
import com.algaworks.pedidovenda.model.ItemPedido;

//public class HibernateItemPedidoDAO implements Serializable {
public class HibernateItemPedidoDAO	 extends HibernateGenericDAO<ItemPedido, Long> implements ItemPedidoDAO, Serializable {

	private static final long serialVersionUID = 1L;

//	@Inject
//	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<ItemPedido> buscarItensPendentes() {
//	    Session session = this.manager.unwrap(Session.class);
//		Session session = this.getEntityManager().unwrap(Session.class);
	    Criteria criteria = this.getSession().createCriteria(ItemPedido.class);		
		
	    criteria.createAlias("pedido", "p")
	    		.add(Restrictions.eq("p.entrega", Entrega.PENDENTE));
		
		return criteria.list();
	}
	
}

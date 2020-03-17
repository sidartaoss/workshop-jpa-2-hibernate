package com.algaworks.pedidovenda.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import com.algaworks.pedidovenda.dao.PedidoDAO;
import com.algaworks.pedidovenda.dao.vo.ValorTotalVendaDoDia;
import com.algaworks.pedidovenda.model.Pedido;

//public class HibernatePedidoDAO implements Serializable {
public class HibernatePedidoDAO extends HibernateGenericDAO<Pedido, Long> implements PedidoDAO, Serializable {

	private static final long serialVersionUID = 1L;
	
//	@Inject
//	private EntityManager manager;
	
//	public void salvar(Pedido pedido) {
//		this.manager.merge(pedido);
//	}

//	@SuppressWarnings("unchecked")
//	public List<Pedido> filtrar(Pedido filtro) {
//		Session session = this.manager.unwrap(Session.class);
//		Criteria criteria = session.createCriteria(Pedido.class);
//		if (filtro.getCodigo() != null) {
//			criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
//		}
//		if (filtro.getReferencia() != null && !"".equals(filtro.getReferencia().trim())) {
//			criteria.add(Restrictions.ilike("referencia", filtro.getReferencia(), MatchMode.ANYWHERE));
//		}
//		if (filtro.getEntrega() != null) {
//			criteria.add(Restrictions.eq("entrega", filtro.getEntrega()));
//		}
//		return criteria.list();
//	  }
	
//	public Pedido buscarPeloCodigo(Long codigo) {
//		return this.manager.find(Pedido.class, codigo);
//	}

	public List<ValorTotalVendaDoDia> buscarValorTotalVendaDoDia() {
//		return this.manager.createQuery("select "
		return this.getEntityManager().createQuery("select "
				+ "NEW com.algaworks.pedidovenda.dao.vo.ValorTotalVendaDoDia(p.dataVenda, sum(p.valorTotal)) "
				+ "from Pedido p "
				+ "group by p.dataVenda "
				, ValorTotalVendaDoDia.class
		).getResultList();
	}
	
}

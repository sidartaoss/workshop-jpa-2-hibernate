/**
 * 
 */
package com.algaworks.pedidovenda.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.algaworks.pedidovenda.dao.GenericDAO;

/**
 * @author SEMPR
 *
 */
public class HibernateGenericDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

	@Inject
	private EntityManager manager;

	private Class<T> entidadeClasse;

	@SuppressWarnings("unchecked")
	public HibernateGenericDAO() {
		this.entidadeClasse = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public T buscarPeloCodigo(ID id) {
		return this.manager.find(this.entidadeClasse, id);
	}

	@Override
	public void salvar(T entidade) {
		this.manager.merge(entidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> filtrar(T entidade, String... propriedades) {
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(this.entidadeClasse);
		
		if (propriedades != null) {
			for (String propriedade : propriedades) {
				try {
					Object valor = PropertyUtils.getProperty(entidade, propriedade);
					if (valor != null) {
						if (valor instanceof String) {
							criteria.add(Restrictions.ilike(propriedade, (String) valor, MatchMode.ANYWHERE));
						} else {					
							criteria.add(Restrictions.eq(propriedade, valor));
						}
					}
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					throw new RuntimeException(e.getMessage());
				}
				
			}
		}
		
		return criteria.list();
	}
	
	protected EntityManager getEntityManager() {
		return manager;
	}
	
	protected Session getSession() {
		return this.manager.unwrap(Session.class);
	}

}

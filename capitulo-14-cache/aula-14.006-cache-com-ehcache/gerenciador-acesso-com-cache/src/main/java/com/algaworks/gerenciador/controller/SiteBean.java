/**
 * 
 */
package com.algaworks.gerenciador.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.algaworks.gerenciador.model.Status;
import com.algaworks.gerenciador.model.Usuario;

/**
 * @author SEMPR
 *
 */
@Named
@ViewScoped
public class SiteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Long getTotalUsuarios() {
		Session session = (Session) this.manager.unwrap(Session.class);
		
//		return this.manager.createQuery("select count(u) from Usuario u", Long.class).getSingleResult();
		
		return (Long) session.createQuery("select count(u) from Usuario u")
					.setCacheable(true)
					.uniqueResult();
	}
	
	public Long getUsuariosAtivos() {
		Session session = this.manager.unwrap(Session.class);
		return (Long) session.createCriteria(Usuario.class)
				.setProjection(Projections.count("codigo"))
				.add(Restrictions.eq("status", Status.ATIVO))
				.setCacheable(true)
				.uniqueResult();
	}
}
 
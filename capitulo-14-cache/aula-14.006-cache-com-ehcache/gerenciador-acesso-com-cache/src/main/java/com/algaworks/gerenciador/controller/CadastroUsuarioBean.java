/**
 * 
 */
package com.algaworks.gerenciador.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.algaworks.gerenciador.model.Status;
import com.algaworks.gerenciador.model.Usuario;

/**
 * @author SEMPR
 *
 */
@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	private Usuario usuario;
	
	public void salvar() {
		try {
			manager.getTransaction().begin();
			if (this.usuario.getNome() == null) {
				this.usuario.setNome("Pedro");
			}
			if (this.usuario.getStatus() == null) {
				this.usuario.setStatus(Status.ATIVO);
			}
			manager.merge(usuario);
			manager.getTransaction().commit();
			usuario = new Usuario();
		} catch (Exception e) {
			throw new RuntimeException("Erro salvando usuario", e);
		}
	}
	
	@PostConstruct
	public void inicializar() {
		this.usuario = new Usuario();
	}	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Status> getStatusList() {
		return Arrays.asList(Status.values());
	}
	
}
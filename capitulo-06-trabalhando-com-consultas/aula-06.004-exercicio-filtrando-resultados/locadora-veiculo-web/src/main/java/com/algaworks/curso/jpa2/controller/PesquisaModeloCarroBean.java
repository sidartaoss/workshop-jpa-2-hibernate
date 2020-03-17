/**
 * 
 */
package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

/**
 * @author SEMPR
 *
 */
@Named
@ViewScoped
public class PesquisaModeloCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<ModeloCarro> modelosCarro;
	private ModeloCarro modeloCarroSelecionado;
	
	@Inject
	private ModeloCarroDAO modeloCarroDAO;
	
	@PostConstruct
	public void inicializar() {
		this.modelosCarro = this.modeloCarroDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			this.modeloCarroDAO.excluir(this.modeloCarroSelecionado);
			this.modelosCarro.remove(this.modeloCarroSelecionado);
			FacesUtil.addSuccessMessage("Modelo " + this.modeloCarroSelecionado.getDescricao() + " excluido com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * @return the modelosCarro
	 */
	public List<ModeloCarro> getModelosCarro() {
		return modelosCarro;
	}
	
	/**
	 * @return the modeloCarroSelecionado
	 */
	public ModeloCarro getModeloCarroSelecionado() {
		return modeloCarroSelecionado;
	}
	
	public void setModeloCarroSelecionado(ModeloCarro modeloCarroSelecionado) {
		this.modeloCarroSelecionado = modeloCarroSelecionado;
	}	
	

}

/**
 * 
 */
package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.modelolazy.LazyFabricanteDataModel;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

/**
 * @author sosilva
 *
 */
@Named
@ViewScoped
public class PesquisaFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	FabricanteDAO fabricanteDAO;
	
	private List<Fabricante> fabricantes = new ArrayList<>();
	
	private LazyFabricanteDataModel lazyFabricantes;
	
	private Fabricante fabricanteSelecionado;
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public void excluir() {
		try {
			fabricanteDAO.excluir(this.fabricanteSelecionado);
			this.fabricantes.remove(this.fabricanteSelecionado);
			FacesUtil.addSuccessMessage("Fabricante " + fabricanteSelecionado.getNome() + " excluido com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}
	
	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}
	
	@PostConstruct
	public void inicializar() {
//		this.fabricantes = fabricanteDAO.buscarTodos();
		this.lazyFabricantes = new LazyFabricanteDataModel(this.fabricanteDAO);
	}
	
	public LazyFabricanteDataModel getLazyFabricantes() {
		return lazyFabricantes;
	}
}

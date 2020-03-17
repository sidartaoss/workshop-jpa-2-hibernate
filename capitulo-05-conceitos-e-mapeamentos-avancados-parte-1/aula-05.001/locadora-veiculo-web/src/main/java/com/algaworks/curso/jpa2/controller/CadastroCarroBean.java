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

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.CadastroCarroService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

/**
 * @author SEMPR
 *
 */
@Named
@ViewScoped
public class CadastroCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Carro carro;
	private List<ModeloCarro> modelosCarros;
	private List<Acessorio> acessorios;
	
	@Inject
	private CadastroCarroService cadastroCarroService;
	@Inject
	private AcessorioDAO acessorioDAO;
	@Inject
	private ModeloCarroDAO modeloCarroDAO;
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		
		this.acessorios = this.acessorioDAO.buscarTodos();
		this.modelosCarros = this.modeloCarroDAO.buscarTodos();
	}
	
	public void salvar() {
		try {
			this.cadastroCarroService.salvar(carro);
			FacesUtil.addSuccessMessage("Carro salvo com sucesso!");	
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro desconhecido. Contate o Administrador de Sistemas.");
		}
		this.limpar();
	}

	public void limpar() {
		this.carro = new Carro();
		this.carro.setAcessorios(new ArrayList<Acessorio>());
	}

	/**
	 * @return the carro
	 */
	public Carro getCarro() {
		return carro;
	}

	/**
	 * @param carro the carro to set
	 */
	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	/**
	 * @return the modelosCarros
	 */
	public List<ModeloCarro> getModelosCarros() {
		return modelosCarros;
	}

	/**
	 * @return the acessorios
	 */
	public List<Acessorio> getAcessorios() {
		return acessorios;
	}
	
	
	

}

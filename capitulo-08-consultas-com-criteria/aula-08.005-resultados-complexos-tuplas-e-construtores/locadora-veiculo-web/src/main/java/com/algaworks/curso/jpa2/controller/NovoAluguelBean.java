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

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.ApoliceSeguro;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.service.CadastroAluguelService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

/**
 * @author SEMPR
 *
 */
@Named
@ViewScoped
public class NovoAluguelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Aluguel aluguel;
	private List<Carro> carros;
	private List<Motorista> motoristas;
	@Inject
	private CadastroAluguelService cadastroAluguelService;
	@Inject
	private CarroDAO carroDAO;
	@Inject
	private MotoristaDAO motoristaDAO;
	
	public void salvar() {
		try {
			this.cadastroAluguelService.salvar(this.aluguel);
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}

   public void limpar() {
	   this.aluguel = new Aluguel();
	   this.aluguel.setApoliceSeguro(new ApoliceSeguro());
	}
   
   @PostConstruct
   public void inicializar() {
	   this.limpar();
	   this.carros = this.carroDAO.buscarTodos();
	   this.motoristas = this.motoristaDAO.buscarTodos();
   }

	/**
	 * @return the aluguel
	 */
	public Aluguel getAluguel() {
		return aluguel;
	}
	
	/**
	 * @param aluguel the aluguel to set
	 */
	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}
	
	/**
	 * @return the carros
	 */
	public List<Carro> getCarros() {
		return carros;
	}
	
	public List<Motorista> getMotoristas() {
		return motoristas;
	}

}

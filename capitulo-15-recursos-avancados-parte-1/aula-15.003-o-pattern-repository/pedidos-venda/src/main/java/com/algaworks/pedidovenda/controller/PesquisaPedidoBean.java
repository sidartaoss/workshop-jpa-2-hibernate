package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.dao.PedidoFiltro;
import com.algaworks.pedidovenda.dao.Pedidos;
import com.algaworks.pedidovenda.model.Entrega;
import com.algaworks.pedidovenda.model.Pedido;

@ViewScoped
@Named
public class PesquisaPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

//	private Pedido filtro;
	private PedidoFiltro filtro;

	private List<Pedido> listaPedidos;

	private List<Entrega> entregas;
	
	@Inject
//	private Pedidos pedidoDAO;
	private Pedidos pedidos;

	public void inicializar() {
//		this.filtro = new Pedido();
		this.filtro = new PedidoFiltro();
		this.entregas = Arrays.asList(Entrega.values());
	}

	public void pesquisar() {
//		this.listaPedidos = pedidoDAO.filtrar(this.filtro, "codigo", "referencia", "entrega");
//		this.listaPedidos = this.pedidoDAO.filtrar(this.filtro);
		this.listaPedidos = this.pedidos.filtrar(this.filtro);
	}

	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

//	public Pedido getFiltro() {
//		return filtro;
//	}
	
	public PedidoFiltro getFiltro() {
		return filtro;
	}

	public List<Entrega> getEntregas() {
		return entregas;
	}

}

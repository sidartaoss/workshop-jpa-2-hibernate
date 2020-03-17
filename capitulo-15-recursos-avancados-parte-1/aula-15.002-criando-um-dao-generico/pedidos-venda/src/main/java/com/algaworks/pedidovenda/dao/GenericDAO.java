/**
 * 
 */
package com.algaworks.pedidovenda.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author SEMPR
 *
 */
public interface GenericDAO<T, ID extends Serializable> {

	T buscarPeloCodigo(ID id); 
	
	void salvar(T entidade);
	
	List<T> filtrar(T entidade, String... propriedades);
}

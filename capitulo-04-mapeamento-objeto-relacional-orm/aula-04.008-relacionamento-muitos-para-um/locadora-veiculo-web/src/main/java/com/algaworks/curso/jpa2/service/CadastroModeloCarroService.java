/**
 * 
 */
package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

/**
 * @author SEMPR
 *
 */
public class CadastroModeloCarroService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ModeloCarroDAO modeloCarroDAO;
	
	@Transactional
	public void salvar(ModeloCarro modeloCarro) throws NegocioException {
		if (modeloCarro.getDescricao() == null || "".equals(modeloCarro.getDescricao().trim())) {
			throw new NegocioException("O nome do modelo eh obrigatorio");
		}
		
		if (modeloCarro.getFabricante() == null) {
			throw new NegocioException("");
		}
		
		this.modeloCarroDAO.salvar(modeloCarro);
	}

}

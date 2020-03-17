/**
 * 
 */
package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

/**
 * @author sosilva
 *
 */
public class CadastroFabricanteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FabricanteDAO dao;
	
	@Transactional
	public void salvar(Fabricante fabricante) throws NegocioException {
        if (fabricante.getNome() == null || "".equals(fabricante.getNome().trim())) {
            throw new NegocioException("O nome do fabricante eh obrigatorio!");
        }
        this.dao.salvar(fabricante);
	}
}

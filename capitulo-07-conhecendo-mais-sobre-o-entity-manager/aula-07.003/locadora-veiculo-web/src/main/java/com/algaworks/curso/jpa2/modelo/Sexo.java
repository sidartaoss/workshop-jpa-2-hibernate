/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

/**
 * @author SEMPR
 *
 */
public enum Sexo {

	MASCULINO,
	FEMININO;
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

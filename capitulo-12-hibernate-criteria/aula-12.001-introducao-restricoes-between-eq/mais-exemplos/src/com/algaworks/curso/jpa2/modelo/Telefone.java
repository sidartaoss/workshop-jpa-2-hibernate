/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import javax.persistence.Embeddable;

/**
 * @author SEMPR
 *
 */
@Embeddable
public class Telefone {

	private String prefixo;
	private String numero;
	private String ramal;
	
	public Telefone() {
		
	}
	
	public Telefone(String prefixo, String numero, String ramal) {
		this.prefixo = prefixo;
		this.numero = numero;
		this.ramal = ramal;
	}

	/**
	 * @return the prefixo
	 */
	public String getPrefixo() {
		return prefixo;
	}

	/**
	 * @param prefixo the prefixo to set
	 */
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the ramal
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * @param ramal the ramal to set
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
}

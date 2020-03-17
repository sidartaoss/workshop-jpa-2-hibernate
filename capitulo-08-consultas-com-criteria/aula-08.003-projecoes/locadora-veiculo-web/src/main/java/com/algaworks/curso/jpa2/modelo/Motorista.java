/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author SEMPR
 *
 */
@Entity
/** Version 1. **/
/** @DiscriminatorValue("MOTORISTA") **/
@DiscriminatorValue("1")
@NamedQueries({
	@NamedQuery(
			name = "Motorista.buscarTodos",
			query = "select m from Motorista m")
})
public class Motorista extends Pessoa {

	private String numeroCNH;
	
	@Column(name = "numero_cnh")
	public String getNumeroCNH() {
		return numeroCNH;
	}
	
	public void setNumeroCNH(String numeroCNH) {
		this.numeroCNH = numeroCNH;
	}
}

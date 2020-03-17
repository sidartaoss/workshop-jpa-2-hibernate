/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author SEMPR
 *
 */
@Entity
@DiscriminatorValue("2")
@NamedQueries({
	@NamedQuery(
			name = "Funcionario.buscarTodos", 
			query = "select f from Funcionario f")
})
public class Funcionario extends Pessoa {

	private String matricula;
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}

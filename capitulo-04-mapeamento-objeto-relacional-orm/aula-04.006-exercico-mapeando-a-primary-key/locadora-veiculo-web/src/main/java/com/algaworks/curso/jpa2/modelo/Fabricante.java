/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * @author sosilva
 *
 */
@Entity
public class Fabricante {
 
	private Long codigo;
	private String nome;

	/**
	 * @return the codigo
	 */
	@Id
	/** Test #1. @GeneratedValue(strategy = GenerationType.IDENTITY) **/
	/** Test #2. @GeneratedValue(strategy = GenerationType.TABLE) **/
	/** Test #3. **/
	@TableGenerator(name = "fabricante_generator", table = "GERADOR_CODIGO", pkColumnName = "ENTIDADE", valueColumnName = "ALOCACAO", allocationSize = 5)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "fabricante_generator")
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}

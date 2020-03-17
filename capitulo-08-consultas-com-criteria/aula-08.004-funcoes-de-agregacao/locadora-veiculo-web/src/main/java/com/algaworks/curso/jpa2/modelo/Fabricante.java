/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

/**
 * @author sosilva
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Fabricante.buscarTodos", query = "select f from Fabricante f")
})
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

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) { 
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Fabricante)) {
			return false;
		}
		Fabricante other = (Fabricante) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	

}

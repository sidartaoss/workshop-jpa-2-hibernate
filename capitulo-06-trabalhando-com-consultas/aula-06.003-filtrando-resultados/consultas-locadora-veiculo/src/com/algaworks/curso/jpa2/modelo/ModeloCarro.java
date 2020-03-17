/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author SEMPR
 *
 */
@Entity
public class ModeloCarro {

	private Long codigo;
	private String descricao;
	private Fabricante fabricante;
	private Categoria categoria;

	/**
	 * @return the codigo
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the fabricante
	 */
	/** Test 1. @ManyToOne **/
	/** Test 2. **/
	@ManyToOne
	@JoinColumn(name = "codigo_fabricante")
	public Fabricante getFabricante() {
		return fabricante;
	}

	/**
	 * @param fabricante the fabricante to set
	 */
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	/** Version 1. **/
	/** @Enumerated(EnumType.STRING) **/
	/** Version 2. **/
	/** @Enumerated(EnumType.ORDINAL) **/
	/** Version 3. **/
	@Enumerated(EnumType.STRING)
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		if (!(obj instanceof ModeloCarro)) {
			return false;
		}
		ModeloCarro other = (ModeloCarro) obj;
		return Objects.equals(codigo, other.codigo);
	}

}

/**
 * 
 */
package com.algaworks.curso.jpa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author SEMPR
 *
 */
@Entity
public class Aluno {

	private Long codigo;
	private String nome;
	private String numeroMatricula;

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

	/**
	 * @return the numeroMatricula
	 */
	@Column(name = "numero_matricula")
	public String getNumeroMatricula() {
		return numeroMatricula;
	}

	/**
	 * @param numeroMatricula the numeroMatricula to set
	 */
	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}
	
	
}

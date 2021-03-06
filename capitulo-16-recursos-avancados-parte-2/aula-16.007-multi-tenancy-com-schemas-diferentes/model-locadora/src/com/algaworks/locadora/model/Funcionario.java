package com.algaworks.locadora.model;
// Generated 17/03/2019 17:32:54 by Hibernate Tools 4.0.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Funcionario generated by hbm2java
 */
@Entity
@Table(name = "funcionario", catalog = "locadora")
public class Funcionario implements java.io.Serializable {

	private long codigo;
	private Pessoa pessoa;
	private String matricula;

	public Funcionario() {
	}

	public Funcionario(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Funcionario(Pessoa pessoa, String matricula) {
		this.pessoa = pessoa;
		this.matricula = matricula;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "pessoa"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "codigo", unique = true, nullable = false)
	public long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Column(name = "matricula")
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}

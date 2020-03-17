/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author SEMPR
 *
 */
@Entity
@Table(name = "apolice_seguro")
public class ApoliceSeguro {

	private Long codigo;
	private BigDecimal valorFranquia;
	private Boolean protecaoTerceiro;
	private Boolean protecaoCausasNaturais;
	private Boolean protecaoRoubo;

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
	 * @return the valorFranquia
	 */
	@Column(name = "valor_franquia")
	public BigDecimal getValorFranquia() {
		return valorFranquia;
	}

	/**
	 * @param valorFranquia the valorFranquia to set
	 */
	public void setValorFranquia(BigDecimal valorFranquia) {
		this.valorFranquia = valorFranquia;
	}

	/**
	 * @return the protecaoTerceiro
	 */
	@Column(name = "protecao_terceiro")
	public Boolean getProtecaoTerceiro() {
		return protecaoTerceiro;
	}

	/**
	 * @param protecaoTerceiro the protecaoTerceiro to set
	 */
	public void setProtecaoTerceiro(Boolean protecaoTerceiro) {
		this.protecaoTerceiro = protecaoTerceiro;
	}

	/**
	 * @return the protecaoCausasNaturais
	 */
	@Column(name = "protecao_causas_naturais")
	public Boolean getProtecaoCausasNaturais() {
		return protecaoCausasNaturais;
	}

	/**
	 * @param protecaoCausasNaturais the protecaoCausasNaturais to set
	 */
	public void setProtecaoCausasNaturais(Boolean protecaoCausasNaturais) {
		this.protecaoCausasNaturais = protecaoCausasNaturais;
	}

	/**
	 * @return the protecaoRoubo
	 */
	@Column(name = "protecao_roubo")
	public Boolean getProtecaoRoubo() {
		return protecaoRoubo;
	}

	/**
	 * @param protecaoRoubo the protecaoRoubo to set
	 */
	public void setProtecaoRoubo(Boolean protecaoRoubo) {
		this.protecaoRoubo = protecaoRoubo;
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
		if (!(obj instanceof ApoliceSeguro)) {
			return false;
		}
		ApoliceSeguro other = (ApoliceSeguro) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
}

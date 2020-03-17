/**
 * 
 */
package com.algaworks.curso.jpa2.info;

import java.math.BigDecimal;

import com.algaworks.curso.jpa2.modelo.Carro;

/**
 * @author sosilva
 *
 */
public class AluguelCarroInfo {

	private Carro carro;
	private Long quantidadeAlugueis;
	private BigDecimal valorMaximo;
	private BigDecimal valorMedio;

	public AluguelCarroInfo(Carro carro, Long quantidadeAlugueis, Number valorMaximo, Number valorMedio) {
		this.carro = carro;
		this.quantidadeAlugueis = quantidadeAlugueis;
		this.valorMaximo = BigDecimal.valueOf(valorMaximo.doubleValue());
		this.valorMedio = BigDecimal.valueOf(valorMedio.doubleValue());
	}

	/**
	 * @return the carro
	 */
	public Carro getCarro() {
		return carro;
	}

	/**
	 * @param carro the carro to set
	 */
	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	/**
	 * @return the quantidadeAlugueis
	 */
	public Long getQuantidadeAlugueis() {
		return quantidadeAlugueis;
	}

	/**
	 * @param quantidadeAlugueis the quantidadeAlugueis to set
	 */
	public void setQuantidadeAlugueis(Long quantidadeAlugueis) {
		this.quantidadeAlugueis = quantidadeAlugueis;
	}

	/**
	 * @return the valorMaximo
	 */
	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	/**
	 * @param valorMaximo the valorMaximo to set
	 */
	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	/**
	 * @return the valorMedio
	 */
	public BigDecimal getValorMedio() {
		return valorMedio;
	}

	/**
	 * @param valorMinimo the valorMinimo to set
	 */
	public void setValorMedio(BigDecimal valorMedio) {
		this.valorMedio = valorMedio;
	}

}

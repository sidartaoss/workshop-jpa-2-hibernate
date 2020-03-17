/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author SEMPR
 *
 */
@Entity
public class Aluguel {

	private Long codigo;
	private BigDecimal valorTotal;
	private Calendar dataPedido;
	private Date dataEntrega;
	private Date dataDevolucao;
	private ApoliceSeguro apoliceSeguro;
	private Carro carro;
	private Motorista motorista;

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
	 * @return the valorTotal
	 */
	@Column(name = "valor_total")
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the carro
	 */
	@ManyToOne
	@JoinColumn(name = "codigo_carro")
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
	 * @return the apoliceSeguro
	 */

	/** Aula 04.013. Mapeamento Um-Para-Um **/
//	@OneToOne
	/** Aula 04.013. Mapeamento Um-Para-Um **/
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_apolice_seguro")
	public ApoliceSeguro getApoliceSeguro() {
		return apoliceSeguro;
	}

	/**
	 * @param apoliceSeguro the apoliceSeguro to set
	 */
	public void setApoliceSeguro(ApoliceSeguro apoliceSeguro) {
		this.apoliceSeguro = apoliceSeguro;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_pedido")
	public Calendar getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Calendar dataPedido) {
		this.dataPedido = dataPedido;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_entrega")
	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_devolucao")
	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	@ManyToOne
	@JoinColumn(name = "codigo_motorista")
	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
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
		if (!(obj instanceof Aluguel)) {
			return false;
		}
		Aluguel other = (Aluguel) obj;
		return Objects.equals(codigo, other.codigo);
	}

}

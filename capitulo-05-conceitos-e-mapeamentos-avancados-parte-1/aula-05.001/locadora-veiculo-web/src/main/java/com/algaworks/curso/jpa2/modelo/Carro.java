/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author SEMPR
 *
 */
@Entity
public class Carro {

	private Long codigo;
	private String placa;
	private String chassi;
	private String cor;
	private BigDecimal valorDiaria;
	private ModeloCarro modelo;
	private List<Acessorio> acessorios;
	private List<Aluguel> alugueis;

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
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return the chassi
	 */
	public String getChassi() {
		return chassi;
	}

	/**
	 * @param chassi the chassi to set
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/**
	 * @return the cor
	 */
	public String getCor() {
		return cor;
	}

	/**
	 * @param cor the cor to set
	 */
	public void setCor(String cor) {
		this.cor = cor;
	}

	/**
	 * @return the valorDiaria
	 */
	@Column(name = "valor_diaria")
	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}

	/**
	 * @param valorDiaria the valorDiaria to set
	 */
	public void setValorDiaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	/**
	 * @return the acessorios
	 */
	/** Version 1. **/
	/** @ManyToMany **/
	/** Version 2. **/
	/** @ManyToMany **/
	/** Version 3. **/
	/** @ManyToMany(fetch = FetchType.EAGER) **/
	/** Version 4. **/
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "carro_acessorio", 
			joinColumns = @JoinColumn(name = "codigo_carro"),
			inverseJoinColumns = @JoinColumn(name = "codigo_acessorio")
	)
	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	/**
	 * @param acessorios the acessorios to set
	 */
	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}

	/**
	 * @return the modelo
	 */
	/** Version 1. **/ 
	/** @ManyToOne **/
	/** Version 2. **/
	/** @ManyToOne(fetch = FetchType.LAZY) **/
	/** Version 3. **/
	@ManyToOne
	@JoinColumn(name = "codigo_modelo")
	public ModeloCarro getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(ModeloCarro modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the alugueis
	 */
	@OneToMany(mappedBy = "carro")
	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	/**
	 * @param alugueis the alugueis to set
	 */
	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
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
		if (!(obj instanceof Carro)) {
			return false;
		}
		Carro other = (Carro) obj;
		return Objects.equals(codigo, other.codigo);
	}
}

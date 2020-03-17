/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
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
@Table(name = "tab_veiculo")
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	private Long codigo;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public Long getCodigo() {
//		return codigo;
//	}
//	
//	public void setCodigo(Long codigo) {
//		this.codigo = codigo;
//	}
	
	private VeiculoID codigo;
	private String fabricante;
	private String modelo;

	@EmbeddedId
	public VeiculoID getCodigo() {
		return codigo;
	}

	public void setCodigo(VeiculoID codigo) {
		this.codigo = codigo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
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
		if (!(obj instanceof Veiculo)) {
			return false;
		}
		Veiculo other = (Veiculo) obj;
		return Objects.equals(codigo, other.codigo);
	}	
}

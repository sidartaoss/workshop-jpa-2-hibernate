package com.algaworks.curso.jpa2.modelo;

import java.io.Serializable;
import java.util.Objects;

public class VeiculoID implements Serializable {

	private static final long serialVersionUID = 1L;

	private String placa;
	private String cidade;
	
	public VeiculoID() {
		
	}
	
	public VeiculoID(String placa, String cidade) {
		this.placa = placa;
		this.cidade = cidade;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cidade, placa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof VeiculoID)) {
			return false;
		}
		VeiculoID other = (VeiculoID) obj;
		return Objects.equals(cidade, other.cidade) && Objects.equals(placa, other.placa);
	}
	
	
}

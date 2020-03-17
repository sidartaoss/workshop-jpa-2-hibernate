/**
 * 
 */
package com.algaworks.gerenciador.util.jpa;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author SEMPR
 *
 */
public class Tenant implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	public Tenant() {

	}

	public Tenant(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Tenant)) {
			return false;
		}
		Tenant other = (Tenant) obj;
		return Objects.equals(id, other.id);
	}
	
}

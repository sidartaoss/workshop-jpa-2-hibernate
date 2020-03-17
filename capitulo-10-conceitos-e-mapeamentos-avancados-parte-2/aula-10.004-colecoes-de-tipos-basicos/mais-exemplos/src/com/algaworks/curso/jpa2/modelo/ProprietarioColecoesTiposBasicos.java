/**
 * 
 */
package com.algaworks.curso.jpa2.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author SEMPR
 *
 */
@Entity
@Table(name = "proprietario")
public class ProprietarioColecoesTiposBasicos {

	private Long codigo;
	private String nome;
	private List<String> telefones = new ArrayList<>();
	
	@Id
	@GeneratedValue
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@ElementCollection
	@CollectionTable(name = "proprietario_telefones",
			joinColumns = @JoinColumn(name = "cod_proprietario")	
			)
	@Column(name = "numero_telefone")
	public List<String> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}
	
}

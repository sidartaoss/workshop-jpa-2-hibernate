/**
 * 
 */
package com.algaworks.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.algaworks.model.util.LocalDateConverter;
import com.algaworks.relatorio.AlertasPorUsuario;

/**
 * @author SEMPR
 *
 */
@NamedNativeQuery(name = "alertasPorUsuario",
	query = "select u.nome as nome, count(a.codigo) as totalAlertas from Usuario u, Alert a "
			+ "where u.codigo = a.codigo_usuario "
			+ "group by u.nome",
	resultSetMapping = "alertasPorUsuarioMapping"
)
@SqlResultSetMapping(name = "alertasPorUsuarioMapping",
	classes = {
			@ConstructorResult(
					targetClass = AlertasPorUsuario.class,
					columns = {
							@ColumnResult(name = "nome"),
							@ColumnResult(name = "totalAlertas")
					}
			)
	}
)
@Entity
@Table(name = "usuario", indexes = { @Index( columnList = "nome", unique = true )
														, @Index( columnList = "nome, data_nascimento" )
})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String nome;
	private LocalDate dataNascimento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	/** 
	 * Caused by: org.hibernate.AnnotationException: @Temporal should only be set on a java.util.Date or 
	 * java.util.Calendar property: com.algaworks.model.Usuario.dataNascimento
	 * **/
//	@Temporal(TemporalType.DATE)
	@Convert(converter = LocalDateConverter.class)
	@Column(name = "data_nascimento")
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
}

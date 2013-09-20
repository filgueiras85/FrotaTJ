package dao;
// default package


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dao.Modelo;

/**
 * Marca entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "marca", catalog = "frotatj")
public class Marca implements java.io.Serializable {

	// Fields

	private Integer idmarca;
	private String nome;
	private Set<Modelo> modelos = new HashSet<Modelo>(0);

	// Constructors

	/** default constructor */
	public Marca() {
	}

	/** full constructor */
	public Marca(String nome, Set<Modelo> modelos) {
		this.nome = nome;
		this.modelos = modelos;
	}
	
	public Marca(int Id,String nome) {
		this.idmarca = Id;
		this.nome = nome;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idmarca", unique = true, nullable = false)
	public Integer getIdmarca() {
		return this.idmarca;
	}

	public void setIdmarca(Integer idmarca) {
		this.idmarca = idmarca;
	}

	@Column(name = "nome", length = 200)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "marca")
	public Set<Modelo> getModelos() {
		return this.modelos;
	}

	public void setModelos(Set<Modelo> modelos) {
		this.modelos = modelos;
	}

	public String toString()
	{
		return this.getNome();
	}
}

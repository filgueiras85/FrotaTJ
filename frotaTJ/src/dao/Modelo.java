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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.print.DocFlavor.STRING;

import dao.Marca;
import dao.TipoServicoModelo;
import dao.Veiculo;

/**
 * Modelo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "modelo", catalog = "frotatj")
public class Modelo implements java.io.Serializable {

	// Fields

	private Integer idmodelo;
	private Marca marca;
	private String nome;
	private Set<Veiculo> veiculos = new HashSet<Veiculo>(0);
	private Set<TipoServicoModelo> tipoServicoModelos = new HashSet<TipoServicoModelo>(
			0);

	// Constructors

	/** default constructor */
	public Modelo() {
	}

	/** minimal constructor */
	public Modelo(Marca marca, String nome) {
		
		this.marca = marca;
		this.nome = nome;
	}

	/** full constructor */
	public Modelo(Marca marca, String nome, Set<Veiculo> veiculos,
			Set<TipoServicoModelo> tipoServicoModelos) {
		this.marca = marca;
		this.nome = nome;
		this.veiculos = veiculos;
		this.tipoServicoModelos = tipoServicoModelos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idmodelo", unique = true, nullable = false)
	public Integer getIdmodelo() {
		return this.idmodelo;
	}

	public void setIdmodelo(Integer idmodelo) {
		this.idmodelo = idmodelo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marca_idmarca", nullable = false)
	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Column(name = "nome", length = 200)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "modelo")
	public Set<Veiculo> getVeiculos() {
		return this.veiculos;
	}

	public void setVeiculos(Set<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "modelo")
	public Set<TipoServicoModelo> getTipoServicoModelos() {
		return this.tipoServicoModelos;
	}

	public void setTipoServicoModelos(Set<TipoServicoModelo> tipoServicoModelos) {
		this.tipoServicoModelos = tipoServicoModelos;
	}

}

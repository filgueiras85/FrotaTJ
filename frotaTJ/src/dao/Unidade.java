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

import dao.Motorista;
import dao.Veiculo;

/**
 * Unidade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "unidade", catalog = "frotatj")
public class Unidade implements java.io.Serializable {

	// Fields

	private Integer idunidade;
	private String nome;
	private Set<Motorista> motoristas = new HashSet<Motorista>(0);
	private Set<Veiculo> veiculos = new HashSet<Veiculo>(0);
	private Set<Veiculo> veiculos_1 = new HashSet<Veiculo>(0);

	// Constructors

	/** default constructor */
	public Unidade() {
	}
	
	public Unidade(Integer idunidade, String nome){
		this.idunidade = idunidade;
		this.nome = nome;
	}


	/** full constructor */
	public Unidade(String nome, Set<Motorista> motoristas,
			Set<Veiculo> veiculos, Set<Veiculo> veiculos_1) {
		this.nome = nome;
		this.motoristas = motoristas;
		this.veiculos = veiculos;
		this.veiculos_1 = veiculos_1;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idunidade", unique = true, nullable = false)
	public Integer getIdunidade() {
		return this.idunidade;
	}

	public void setIdunidade(Integer idunidade) {
		this.idunidade = idunidade;
	}

	@Column(name = "nome", length = 200)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "unidade")
	public Set<Motorista> getMotoristas() {
		return this.motoristas;
	}

	public void setMotoristas(Set<Motorista> motoristas) {
		this.motoristas = motoristas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "unidade")
	public Set<Veiculo> getVeiculos() {
		return this.veiculos;
	}

	public void setVeiculos(Set<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "unidade")
	public Set<Veiculo> getVeiculos_1() {
		return this.veiculos_1;
	}

	public void setVeiculos_1(Set<Veiculo> veiculos_1) {
		this.veiculos_1 = veiculos_1;
	}

	public String toString()
	{
		return this.getNome();
	}
	
}

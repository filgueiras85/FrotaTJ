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

import dao.Servico;
import dao.Unidade;
import dao.Veiculo;

/**
 * Motorista entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "motorista", catalog = "tjsc")
public class Motorista implements java.io.Serializable {

	// Fields

	private Integer idmotorista;
	private Unidade unidade;
	private String matricula;
	private String nome;
	private Set<Servico> servicos = new HashSet<Servico>(0);
	private Set<Veiculo> veiculos = new HashSet<Veiculo>(0);

	// Constructors

	/** default constructor */
	public Motorista() {
	}

	/** minimal constructor */
	public Motorista(Unidade unidade) {
		this.unidade = unidade;
	}

	/** full constructor */
	public Motorista(Unidade unidade, String matricula, String nome,
			Set<Servico> servicos, Set<Veiculo> veiculos) {
		this.unidade = unidade;
		this.matricula = matricula;
		this.nome = nome;
		this.servicos = servicos;
		this.veiculos = veiculos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idmotorista", unique = true, nullable = false)
	public Integer getIdmotorista() {
		return this.idmotorista;
	}

	public void setIdmotorista(Integer idmotorista) {
		this.idmotorista = idmotorista;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidade_idunidade", nullable = false)
	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@Column(name = "matricula", length = 200)
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Column(name = "nome", length = 200)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "motorista")
	public Set<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(Set<Servico> servicos) {
		this.servicos = servicos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "motorista")
	public Set<Veiculo> getVeiculos() {
		return this.veiculos;
	}

	public void setVeiculos(Set<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

}

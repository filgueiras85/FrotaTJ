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

import dao.Servico;

/**
 * TipoServico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipo_servico", catalog = "frotatj")
public class TipoServico implements java.io.Serializable {

	// Fields

	private Integer idtipoServico;
	private String nome;
	private Set<Servico> servicos = new HashSet<Servico>(0);
	private Set<TipoServicoVeiculo> tipoServicoVeiculos = new HashSet<TipoServicoVeiculo>(
			0);
	private Set<TipoServicoModelo> tipoServicoModelos = new HashSet<TipoServicoModelo>(
			0);

	// Constructors

	/** default constructor */
	public TipoServico() {
	}

	/** full constructor */
	public TipoServico(String nome, Set<Servico> servicos,
			Set<TipoServicoVeiculo> tipoServicoVeiculos,
			Set<TipoServicoModelo> tipoServicoModelos) {
		this.nome = nome;
		this.servicos = servicos;
		this.tipoServicoVeiculos = tipoServicoVeiculos;
		this.tipoServicoModelos = tipoServicoModelos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idtipo_servico", unique = true, nullable = false)
	public Integer getIdtipoServico() {
		return this.idtipoServico;
	}

	public void setIdtipoServico(Integer idtipoServico) {
		this.idtipoServico = idtipoServico;
	}

	@Column(name = "nome", length = 200)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoServico")
	public Set<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(Set<Servico> servicos) {
		this.servicos = servicos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoServico")
	public Set<TipoServicoVeiculo> getTipoServicoVeiculos() {
		return this.tipoServicoVeiculos;
	}

	public void setTipoServicoVeiculos(
			Set<TipoServicoVeiculo> tipoServicoVeiculos) {
		this.tipoServicoVeiculos = tipoServicoVeiculos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoServico")
	public Set<TipoServicoModelo> getTipoServicoModelos() {
		return this.tipoServicoModelos;
	}

	public void setTipoServicoModelos(Set<TipoServicoModelo> tipoServicoModelos) {
		this.tipoServicoModelos = tipoServicoModelos;
	}
	public String toString()
	{
		return this.getNome();
	}
}

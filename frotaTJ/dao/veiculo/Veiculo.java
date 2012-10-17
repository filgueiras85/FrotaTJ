package dao.veiculo;
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

import com.fean.tjsc.dao.abastecimento.Abastecimento;
import com.fean.tjsc.dao.modelo.Modelo;
import com.fean.tjsc.dao.motorista.Motorista;
import com.fean.tjsc.dao.servico.Servico;
import com.fean.tjsc.dao.tiposervico.TipoServicoVeiculo;
import com.fean.tjsc.dao.unidade.Unidade;

/**
 * Veiculo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "veiculo", catalog = "tjsc")
public class Veiculo implements java.io.Serializable {

	// Fields

	private Integer idveiculo;
	private Modelo modelo;
	private Motorista motorista;
	private Unidade unidade;
	private String placa;
	private String revavan;
	private String chassi;
	private Integer odometro;
	private Boolean situacao;
	private Set<Abastecimento> abastecimentos = new HashSet<Abastecimento>(0);
	private Set<Servico> servicos = new HashSet<Servico>(0);
	private Set<TipoServicoVeiculo> tipoServicoVeiculos = new HashSet<TipoServicoVeiculo>(
			0);

	// Constructors

	/** default constructor */
	public Veiculo() {
	}

	/** minimal constructor */
	public Veiculo(Modelo modelo, Motorista motorista, Unidade unidade) {
		this.modelo = modelo;
		this.motorista = motorista;
		this.unidade = unidade;
	}

	/** full constructor */
	public Veiculo(Modelo modelo, Motorista motorista, Unidade unidade,
			String placa, String revavan, String chassi, Integer odometro,
			Boolean situacao, Set<Abastecimento> abastecimentos,
			Set<Servico> servicos, Set<TipoServicoVeiculo> tipoServicoVeiculos) {
		this.modelo = modelo;
		this.motorista = motorista;
		this.unidade = unidade;
		this.placa = placa;
		this.revavan = revavan;
		this.chassi = chassi;
		this.odometro = odometro;
		this.situacao = situacao;
		this.abastecimentos = abastecimentos;
		this.servicos = servicos;
		this.tipoServicoVeiculos = tipoServicoVeiculos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idveiculo", unique = true, nullable = false)
	public Integer getIdveiculo() {
		return this.idveiculo;
	}

	public void setIdveiculo(Integer idveiculo) {
		this.idveiculo = idveiculo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modelo_idmodelo", nullable = false)
	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motorista_idmotorista", nullable = false)
	public Motorista getMotorista() {
		return this.motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidade_idunidade", nullable = false)
	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@Column(name = "placa", length = 10)
	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Column(name = "revavan", length = 20)
	public String getRevavan() {
		return this.revavan;
	}

	public void setRevavan(String revavan) {
		this.revavan = revavan;
	}

	@Column(name = "chassi", length = 50)
	public String getChassi() {
		return this.chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	@Column(name = "odometro")
	public Integer getOdometro() {
		return this.odometro;
	}

	public void setOdometro(Integer odometro) {
		this.odometro = odometro;
	}

	@Column(name = "situacao")
	public Boolean getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "veiculo")
	public Set<Abastecimento> getAbastecimentos() {
		return this.abastecimentos;
	}

	public void setAbastecimentos(Set<Abastecimento> abastecimentos) {
		this.abastecimentos = abastecimentos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "veiculo")
	public Set<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(Set<Servico> servicos) {
		this.servicos = servicos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "veiculo")
	public Set<TipoServicoVeiculo> getTipoServicoVeiculos() {
		return this.tipoServicoVeiculos;
	}

	public void setTipoServicoVeiculos(
			Set<TipoServicoVeiculo> tipoServicoVeiculos) {
		this.tipoServicoVeiculos = tipoServicoVeiculos;
	}

}

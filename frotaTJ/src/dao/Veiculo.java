package dao;
// default package


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQuery;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Calendar;


import dao.Abastecimento;
import dao.Modelo;
import dao.Motorista;
import dao.Servico;
import dao.TipoServicoVeiculo;
import dao.Unidade;

/**
 * Veiculo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "veiculo", catalog = "frotatj")
@NamedQuery(
		name="VeiculoUnidade",
		query="SELECT v FROM Veiculo v WHERE unidade_idunidade = :unidade"
    	)
public class Veiculo implements java.io.Serializable {

	// Fields

	private Integer idveiculo;
	private Modelo modelo;
	private Motorista motorista;
	private Unidade unidade;
	private String placa;
	private String renavan;
	private String chassi;
	private Integer odometro;
	private String situacao;
	private Integer kmCadastro;
	private Date dataCadastro; 
	private Set<Abastecimento> abastecimentos = new HashSet<Abastecimento>(0);
	private Set<Servico> servicos = new HashSet<Servico>(0);
	private Set<TipoServicoVeiculo> tipoServicoVeiculos = new HashSet<TipoServicoVeiculo>(
			0);

	// Constructors

	/** default constructor */
	public Veiculo() {
	}
	/**constructor de cadastro */
	public Veiculo(Integer idVeiculo, Modelo modelo, Motorista motorista, Unidade unidade,
			String placa, String renavan, String chassi, Integer odometro, String situacao, Integer kmCadastro, Date dataCadastro){
		this.idveiculo = idVeiculo;
		this.modelo = modelo;
		this.motorista = motorista;
		this.unidade = unidade;
		this.placa = placa;
		this.renavan = renavan;
		this.chassi = chassi;
		this.odometro = odometro;		
		this.situacao = situacao;
		this.kmCadastro = kmCadastro;
		this.dataCadastro = dataCadastro;
	}
	public Veiculo(Integer idVeiculo, Modelo modelo, Motorista motorista, Unidade unidade,
			String placa, String renavan, String chassi, Integer odometro, String situacao){
		this.idveiculo = idVeiculo;
		this.modelo = modelo;
		this.motorista = motorista;
		this.unidade = unidade;
		this.placa = placa;
		this.renavan = renavan;
		this.chassi = chassi;
		this.odometro = odometro;		
		this.situacao = situacao;
		
	}

	/** minimal constructor */
	public Veiculo(Modelo modelo, Motorista motorista, Unidade unidade) {
		this.modelo = modelo;
		this.motorista = motorista;
		this.unidade = unidade;
	}

	/** full constructor */
	public Veiculo(Integer idVeiculo, Modelo modelo, Motorista motorista, Unidade unidade,
			String placa, String renavan, String chassi, Integer odometro,
			String situacao, Set<Abastecimento> abastecimentos,
			Set<Servico> servicos, Set<TipoServicoVeiculo> tipoServicoVeiculos) {
		this.idveiculo = idVeiculo;
		this.modelo = modelo;
		this.motorista = motorista;
		this.unidade = unidade;
		this.placa = placa;
		this.renavan = renavan;
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

	@Column(name = "renavan", length = 20)
	public String getrenavan() {
		return this.renavan;
	}

	public void setrenavan(String renavan) {
		this.renavan = renavan;
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
	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
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

	
	@Column(name = "km_cadastro")
	public Integer getKmCadastro() {
		return this.kmCadastro;
	}

	public void setKmCadastro(Integer kmCadastro) {
		this.kmCadastro = kmCadastro;
	}
	
	@Column(name = "data_cadastro")
	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String toString()
	{
		return this.getPlaca();
	}
}

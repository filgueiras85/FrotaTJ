package dao;
// default package


import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mb.MBUsuario;

import dao.Fornecedor;
import dao.Motorista;
import dao.TipoServico;
import dao.Usuario;
import dao.Veiculo;

/**
 * Servico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "servico", catalog = "frotatj")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(
		name="Servico.UltimoServico",
		query="SELECT s FROM Servico s WHERE s.veiculo = :veiculo and s.tipoServico = :tipoServico " +
				"and data2 = (select max(s.data2) from Servico s  WHERE s.veiculo = :veiculo and s.tipoServico = :tipoServico))"
<<<<<<< HEAD
    	),
    @javax.persistence.NamedQuery(
    		name="ServicoVeiculo",
    		query="SELECT s FROM Servico s WHERE veiculo = :veiculo"
    	  	),
    @javax.persistence.NamedQuery(
    		name="ServicoTipoServico",
    	    query="SELECT s FROM Servico s WHERE tipoServico = :tipoServico"
    		),
    @javax.persistence.NamedQuery(
    	    name="ServicoPorData",
    	    query="SELECT s FROM Servico s WHERE data_2 BETWEEN :data2 AND :data2"
    	    )
})


=======
    ),
    @javax.persistence.NamedQuery(
    		name="Servico.ContaServicos",
    		query="SELECT s FROM Servico s WHERE s.veiculo = :veiculo and s.tipoServico = :tipoServico "
    ),
    @javax.persistence.NamedQuery(
    		name="ServicoTipoServico",
    	    query="SELECT s FROM Servico s WHERE tipoServico = :tipoServico"
    		)
})
>>>>>>> origin/master
public class Servico implements java.io.Serializable {

	// Fields

	private Integer idServico;
	private Usuario usuario;
	private Motorista motorista;
	private Veiculo veiculo;
	private Fornecedor fornecedor;
	private TipoServico tipoServico;
	private Timestamp data2;
	private Double valor;
	private String nroOrcamento;
	private Integer nfTicket;
	private String descricao;
	private Integer km;

	// Constructors

	/** default constructor */
	public Servico() {
	}

	/** minimal constructor */
	public Servico(Integer id,Usuario usuario, Motorista motorista, Veiculo veiculo,
			Fornecedor fornecedor, TipoServico tipoServico) {
		this.idServico = id;
		this.usuario = usuario;
		this.motorista = motorista;
		this.veiculo = veiculo;
		this.fornecedor = fornecedor;
		this.tipoServico = tipoServico;
	}
	public Servico(Integer id, Motorista motorista, Veiculo veiculo,
			Fornecedor fornecedor, TipoServico tipoServico, Timestamp data,
			Double valor, String nroOrcamento, Integer nfTicket,
			String descricao, Integer km) {
		this.usuario = MBUsuario.getInstance().retornarUsuario(1);
		this.idServico = id;
		this.motorista = motorista;
		this.veiculo = veiculo;
		this.fornecedor = fornecedor;
		this.tipoServico = tipoServico;
		this.data2 = data;
		this.valor = valor;
		this.nroOrcamento = nroOrcamento;
		this.nfTicket = nfTicket;
		this.descricao = descricao;
		this.km = km;
	}

	/** full constructor */
	public Servico(Usuario usuario, Motorista motorista, Veiculo veiculo,
			Fornecedor fornecedor, TipoServico tipoServico, Timestamp data2,
			Double valor, String nroOrcamento, Integer nfTicket,
			String descricao, Integer km) {
		this.usuario = usuario;
		this.motorista = motorista;
		this.veiculo = veiculo;
		this.fornecedor = fornecedor;
		this.tipoServico = tipoServico;
		this.data2 = data2;
		this.valor = valor;
		this.nroOrcamento = nroOrcamento;
		this.nfTicket = nfTicket;
		this.descricao = descricao;
		this.km = km;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idservico", unique = true, nullable = false)
	public Integer getIdServico() {
		return this.idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_idusuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	@JoinColumn(name = "veiculo_idveiculo", nullable = false)
	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fornecedor_idfornecedor", nullable = false)
	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_servico_idtipo_servico", nullable = false)
	public TipoServico getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_2", length = 19)
	public Timestamp getData2() {
		return this.data2;
	}

	public void setData2(Timestamp data2) {
		this.data2 = data2;
	}

	@Column(name = "valor", precision = 22, scale = 0)
	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Column(name = "nro_orcamento", length = 100)
	public String getNroOrcamento() {
		return this.nroOrcamento;
	}

	public void setNroOrcamento(String nroOrcamento) {
		this.nroOrcamento = nroOrcamento;
	}

	@Column(name = "nf_ticket")
	public Integer getNfTicket() {
		return this.nfTicket;
	}

	public void setNfTicket(Integer nfTicket) {
		this.nfTicket = nfTicket;
	}

	@Column(name = "descricao")
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "km")
	public Integer getKm() {
		return this.km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}
	public String toString()
	{
		return this.getVeiculo().toString();
	}

}

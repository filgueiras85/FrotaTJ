package dao;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dao.Veiculo;

/**
 * TipoServicoVeiculo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipo_servico_veiculo", catalog = "frotatj")
public class TipoServicoVeiculo implements java.io.Serializable {

	// Fields

	private TipoServicoVeiculoId id;
	private Veiculo veiculo;
	private TipoServico tipoServico;
	private Boolean situacao;

	// Constructors

	/** default constructor */
	public TipoServicoVeiculo() {
	}

	/** minimal constructor */
	public TipoServicoVeiculo(TipoServicoVeiculoId id, Veiculo veiculo,
			TipoServico tipoServico) {
		this.id = id;
		this.veiculo = veiculo;
		this.tipoServico = tipoServico;
	}

	/** full constructor */
	public TipoServicoVeiculo(TipoServicoVeiculoId id, Veiculo veiculo,
			TipoServico tipoServico, Boolean situacao) {
		this.id = id;
		this.veiculo = veiculo;
		this.tipoServico = tipoServico;
		this.situacao = situacao;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "veiculoIdveiculo", column = @Column(name = "veiculo_idveiculo", nullable = false)),
			@AttributeOverride(name = "tipoServicoIdtipoServico", column = @Column(name = "tipo_servico_idtipo_servico", nullable = false)) })
	public TipoServicoVeiculoId getId() {
		return this.id;
	}

	public void setId(TipoServicoVeiculoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "veiculo_idveiculo", nullable = false, insertable = false, updatable = false)
	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_servico_idtipo_servico", nullable = false, insertable = false, updatable = false)
	public TipoServico getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	@Column(name = "situacao")
	public Boolean getSituacao() {
		return this.situacao;
	}
	public String Situacao() {
		if(this.situacao.equals(true)){
			return "Ok";
		}else{
			return "Atrasado";
		}
	}
	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

}

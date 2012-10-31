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

import dao.Modelo;

/**
 * TipoServicoModelo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipo_servico_modelo", catalog = "frotatj")
public class TipoServicoModelo implements java.io.Serializable {

	// Fields

	private TipoServicoModeloId id;
	private Modelo modelo;
	private TipoServico tipoServico;
	private Integer km;
	private Integer tempo;

	// Constructors

	/** default constructor */
	public TipoServicoModelo() {
	}

	/** minimal constructor */
	public TipoServicoModelo(TipoServicoModeloId id, Modelo modelo,
			TipoServico tipoServico) {
		this.id = id;
		this.modelo = modelo;
		this.tipoServico = tipoServico;
	}

	/** full constructor */
	public TipoServicoModelo(TipoServicoModeloId id, Modelo modelo,
			TipoServico tipoServico, Integer km, Integer tempo) {
		this.id = id;
		this.modelo = modelo;
		this.tipoServico = tipoServico;
		this.km = km;
		this.tempo = tempo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "tipoServicoIdtipoServico", column = @Column(name = "tipo_servico_idtipo_servico", nullable = false)),
			@AttributeOverride(name = "modeloIdmodelo", column = @Column(name = "modelo_idmodelo", nullable = false)) })
	public TipoServicoModeloId getId() {
		return this.id;
	}

	public void setId(TipoServicoModeloId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modelo_idmodelo", nullable = false, insertable = false, updatable = false)
	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_servico_idtipo_servico", nullable = false, insertable = false, updatable = false)
	public TipoServico getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	@Column(name = "km")
	public Integer getKm() {
		return this.km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	@Column(name = "tempo")
	public Integer getTempo() {
		return this.tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

}

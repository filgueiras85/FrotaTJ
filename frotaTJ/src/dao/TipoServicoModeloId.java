package dao;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TipoServicoModeloId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TipoServicoModeloId implements java.io.Serializable {

	// Fields

	private Integer tipoServicoIdtipoServico;
	private Integer modeloIdmodelo;

	// Constructors

	/** default constructor */
	public TipoServicoModeloId() {
	}

	/** full constructor */
	public TipoServicoModeloId(Integer tipoServicoIdtipoServico,
			Integer modeloIdmodelo) {
		this.tipoServicoIdtipoServico = tipoServicoIdtipoServico;
		this.modeloIdmodelo = modeloIdmodelo;
	}

	// Property accessors

	@Column(name = "tipo_servico_idtipo_servico", nullable = false)
	public Integer getTipoServicoIdtipoServico() {
		return this.tipoServicoIdtipoServico;
	}

	public void setTipoServicoIdtipoServico(Integer tipoServicoIdtipoServico) {
		this.tipoServicoIdtipoServico = tipoServicoIdtipoServico;
	}

	@Column(name = "modelo_idmodelo", nullable = false)
	public Integer getModeloIdmodelo() {
		return this.modeloIdmodelo;
	}

	public void setModeloIdmodelo(Integer modeloIdmodelo) {
		this.modeloIdmodelo = modeloIdmodelo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TipoServicoModeloId))
			return false;
		TipoServicoModeloId castOther = (TipoServicoModeloId) other;

		return ((this.getTipoServicoIdtipoServico() == castOther
				.getTipoServicoIdtipoServico()) || (this
				.getTipoServicoIdtipoServico() != null
				&& castOther.getTipoServicoIdtipoServico() != null && this
				.getTipoServicoIdtipoServico().equals(
						castOther.getTipoServicoIdtipoServico())))
				&& ((this.getModeloIdmodelo() == castOther.getModeloIdmodelo()) || (this
						.getModeloIdmodelo() != null
						&& castOther.getModeloIdmodelo() != null && this
						.getModeloIdmodelo().equals(
								castOther.getModeloIdmodelo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getTipoServicoIdtipoServico() == null ? 0 : this
						.getTipoServicoIdtipoServico().hashCode());
		result = 37
				* result
				+ (getModeloIdmodelo() == null ? 0 : this.getModeloIdmodelo()
						.hashCode());
		return result;
	}

}

package dao;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TipoServicoVeiculoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TipoServicoVeiculoId implements java.io.Serializable {

	// Fields

	private Integer veiculoIdveiculo;
	private Integer tipoServicoIdtipoServico;

	// Constructors

	/** default constructor */
	public TipoServicoVeiculoId() {
	}

	/** full constructor */
	public TipoServicoVeiculoId(Integer veiculoIdveiculo,
			Integer tipoServicoIdtipoServico) {
		this.veiculoIdveiculo = veiculoIdveiculo;
		this.tipoServicoIdtipoServico = tipoServicoIdtipoServico;
	}

	// Property accessors

	@Column(name = "veiculo_idveiculo", nullable = false)
	public Integer getVeiculoIdveiculo() {
		return this.veiculoIdveiculo;
	}

	public void setVeiculoIdveiculo(Integer veiculoIdveiculo) {
		this.veiculoIdveiculo = veiculoIdveiculo;
	}

	@Column(name = "tipo_servico_idtipo_servico", nullable = false)
	public Integer getTipoServicoIdtipoServico() {
		return this.tipoServicoIdtipoServico;
	}

	public void setTipoServicoIdtipoServico(Integer tipoServicoIdtipoServico) {
		this.tipoServicoIdtipoServico = tipoServicoIdtipoServico;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TipoServicoVeiculoId))
			return false;
		TipoServicoVeiculoId castOther = (TipoServicoVeiculoId) other;

		return ((this.getVeiculoIdveiculo() == castOther.getVeiculoIdveiculo()) || (this
				.getVeiculoIdveiculo() != null
				&& castOther.getVeiculoIdveiculo() != null && this
				.getVeiculoIdveiculo().equals(castOther.getVeiculoIdveiculo())))
				&& ((this.getTipoServicoIdtipoServico() == castOther
						.getTipoServicoIdtipoServico()) || (this
						.getTipoServicoIdtipoServico() != null
						&& castOther.getTipoServicoIdtipoServico() != null && this
						.getTipoServicoIdtipoServico().equals(
								castOther.getTipoServicoIdtipoServico())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getVeiculoIdveiculo() == null ? 0 : this
						.getVeiculoIdveiculo().hashCode());
		result = 37
				* result
				+ (getTipoServicoIdtipoServico() == null ? 0 : this
						.getTipoServicoIdtipoServico().hashCode());
		return result;
	}

}

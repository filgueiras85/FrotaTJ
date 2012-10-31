package dao;
// default package


import java.util.Date;
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

import dao.Veiculo;

/**
 * Abastecimento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "abastecimento", catalog = "frotatj")
public class Abastecimento implements java.io.Serializable {

	// Fields

	private Integer idabastecimento;
	private Veiculo veiculo;
	private Integer kmOdometro;
	private Date data2;

	// Constructors

	/** default constructor */
	public Abastecimento() {
	}

	/** minimal constructor */
	public Abastecimento(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	/** full constructor */
	public Abastecimento(Veiculo veiculo, Integer kmOdometro, Date data2) {
		this.veiculo = veiculo;
		this.kmOdometro = kmOdometro;
		this.data2 = data2;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idabastecimento", unique = true, nullable = false)
	public Integer getIdabastecimento() {
		return this.idabastecimento;
	}

	public void setIdabastecimento(Integer idabastecimento) {
		this.idabastecimento = idabastecimento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "veiculo_idveiculo", nullable = false)
	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Column(name = "km_odometro")
	public Integer getKmOdometro() {
		return this.kmOdometro;
	}

	public void setKmOdometro(Integer kmOdometro) {
		this.kmOdometro = kmOdometro;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_2", length = 10)
	public Date getData2() {
		return this.data2;
	}

	public void setData2(Date data2) {
		this.data2 = data2;
	}

}
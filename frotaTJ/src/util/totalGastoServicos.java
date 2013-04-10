package util;

public class totalGastoServicos {
	
	private String placa,odometro,fornecedor,data,valor,tipoServico,total;

	public totalGastoServicos(String placa, String odometro, String fornecedor,
			String data, String valor, String tipoServico, String total) {
		super();
		this.placa = placa;
		this.odometro = odometro;
		this.fornecedor = fornecedor;
		this.data = data;
		this.valor = valor;
		this.tipoServico = tipoServico;
		this.total = total;
	}

	public totalGastoServicos() {
		super();
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getOdometro() {
		return odometro;
	}

	public void setOdometro(String odometro) {
		this.odometro = odometro;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	

}

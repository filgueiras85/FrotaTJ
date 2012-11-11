package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.TipoServico;
import dao.TipoServicoDAO;
import dao.TipoServicoVeiculo;
import dao.TipoServicoVeiculoDAO;
import dao.TipoServicoVeiculoId;

public class MBTipoServicoVeiculo {
	private static MBTipoServicoVeiculo TipoServicoVeiculoMB = new MBTipoServicoVeiculo();
	
	private MBTipoServicoVeiculo(){
		
	}
	
	public static MBTipoServicoVeiculo getInstance(){
		return TipoServicoVeiculoMB;
		
	}
	
	public String inserir(TipoServicoVeiculo TipoServicoVeiculo) {
		
		String retorno = "ok";
		TipoServicoVeiculoDAO daoTipoServicoVeiculo = TipoServicoVeiculoDAO.getInstance();
		try {
			daoTipoServicoVeiculo.save(TipoServicoVeiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(TipoServicoVeiculo TipoServicoVeiculo) {
		String retorno = "ok";
		TipoServicoVeiculoDAO daoTipoServicoVeiculo = TipoServicoVeiculoDAO.getInstance();
		try {
			daoTipoServicoVeiculo.update(TipoServicoVeiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(TipoServicoVeiculo TipoServicoVeiculo) {
		String retorno = "ok";
		TipoServicoVeiculoDAO daoTipoServicoVeiculo = TipoServicoVeiculoDAO.getInstance();
		try {
			daoTipoServicoVeiculo.delete(TipoServicoVeiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public TipoServicoVeiculo retornarTipoServicoVeiculo(TipoServicoVeiculoId id) {
		TipoServicoVeiculoDAO daoTipoServicoVeiculo = TipoServicoVeiculoDAO.getInstance();
		return daoTipoServicoVeiculo.findById(id);
		
	}

	public List<TipoServicoVeiculo> listarTipoServicosModelos() throws ClassNotFoundException, SQLException{
		TipoServicoVeiculoDAO daoTipoServicoVeiculo = TipoServicoVeiculoDAO.getInstance();
		return daoTipoServicoVeiculo.findAll();
	}
}
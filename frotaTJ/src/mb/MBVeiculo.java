package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Veiculo;
import dao.VeiculoDAO;;

public class MBVeiculo {
	private static MBVeiculo veiculoMB = new MBVeiculo();
	
	private MBVeiculo(){
		
	}
	
	public static MBVeiculo getInstance(){
		return veiculoMB;
	}
	
	public String inserir(Veiculo veiculo) {
		
		String retorno = "ok";
		VeiculoDAO daoveiculo = VeiculoDAO.getInstance();
		try {
			daoveiculo.save(veiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Veiculo veiculo) {
		String retorno = "ok";
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		try {
			daoVeiculo.update(veiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Veiculo veiculo) {
		String retorno = "ok";
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		try {
			daoVeiculo.delete(veiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public Veiculo retornarVeiculo(int id) {
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		return daoVeiculo.findById(id);
		
	}

	public List<Veiculo> listarVeiculos() throws ClassNotFoundException, SQLException{
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		return daoVeiculo.findAll();
	}
}
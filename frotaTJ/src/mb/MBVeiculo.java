package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Modelo;
import dao.TipoServicoVeiculo;
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
	public List<Veiculo> ListarosVeiculodoModelo(Modelo modelo) {
		List<Veiculo> Lista1;
		try {
			Lista1 = listarVeiculos();
			
			List<Veiculo> lista = new ArrayList<>();
			
			for (int i = 0; i < Lista1.size(); i++) {
				System.out.println(Lista1.get(i).getModelo().getIdmodelo().equals(modelo.getIdmodelo()));
				if(Lista1.get(i).getModelo().getIdmodelo().equals(modelo.getIdmodelo())){
					lista.add(Lista1.get(i));
				}
				
			}
			
			System.out.println(lista.size()+"oi"+Lista1.size());
			return lista;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
}
package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.TipoServico;
import dao.TipoServicoDAO;
import dao.TipoServicoModelo;
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
		
		String retorno = "Cadastro inserido.";
		TipoServicoVeiculoDAO daoTipoServicoVeiculo = TipoServicoVeiculoDAO.getInstance();
		try {
			daoTipoServicoVeiculo.save(TipoServicoVeiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(TipoServicoVeiculo TipoServicoVeiculo) {
		String retorno = "Cadastro alterado.";
		TipoServicoVeiculoDAO daoTipoServicoVeiculo = TipoServicoVeiculoDAO.getInstance();
		try {
			daoTipoServicoVeiculo.update(TipoServicoVeiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(TipoServicoVeiculo TipoServicoVeiculo) {
		String retorno = "Cadastro removido.";
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
	
	public List<TipoServicoVeiculo> ListarosTipoServicoVeiculo(Integer id) {
		List<TipoServicoVeiculo> Lista1;
		try {
			Lista1 = listarTipoServicosModelos();
			
			List<TipoServicoVeiculo> lista = new ArrayList<>();
			
			for (int i = 0; i < Lista1.size(); i++) {
				System.out.println(Lista1.get(i).getVeiculo().getIdveiculo().equals(id));
				if(Lista1.get(i).getVeiculo().getIdveiculo().equals(id)){
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
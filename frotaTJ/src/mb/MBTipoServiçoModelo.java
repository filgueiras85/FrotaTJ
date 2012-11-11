package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Modelo;
import dao.TipoServico;
import dao.TipoServicoDAO;
import dao.TipoServicoModelo;
import dao.TipoServicoModeloDAO;
import dao.TipoServicoModeloId;

public class MBTipoServiçoModelo {
	private static MBTipoServiçoModelo tipoServicoModeloMB = new MBTipoServiçoModelo();
	
	private MBTipoServiçoModelo(){
		
	}
	
	public static MBTipoServiçoModelo getInstance(){
		return tipoServicoModeloMB;
		
	}
	
	public String inserir(TipoServicoModelo tipoServicoModelo) {
		
		String retorno = "ok";
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		try {
			daoTipoServicoModelo.save(tipoServicoModelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(TipoServicoModelo tipoServicoModelo) {
		String retorno = "ok";
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		try {
			daoTipoServicoModelo.update(tipoServicoModelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(TipoServicoModelo tipoServicoModelo) {
		String retorno = "ok";
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		try {
			daoTipoServicoModelo.delete(tipoServicoModelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public TipoServicoModelo retornarTipoServicoModelo(TipoServicoModeloId id) {
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		return daoTipoServicoModelo.findById(id);
		
	}
	
	public List<TipoServico> ListarosTipoServicoModelo(Integer id) {
		List<TipoServicoModelo> Lista1;
		try {
			Lista1 = listarTipoServicosModelos();
			List<TipoServico> lista = new ArrayList<>();
			
			for (int i = 0; i < Lista1.size(); i++) {
				if(Lista1.get(i).getModelo().getIdmodelo().equals(id)){
					lista.add(Lista1.get(i).getTipoServico());
				}
				
			}
			
			
			return lista;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	public List<TipoServicoModelo> listarTipoServicosModelos() throws ClassNotFoundException, SQLException{
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		return daoTipoServicoModelo.findAll();
	}
}
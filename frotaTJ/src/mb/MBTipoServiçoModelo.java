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

	public List<TipoServicoModelo> listarTipoServicosModelos() throws ClassNotFoundException, SQLException{
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		return daoTipoServicoModelo.findAll();
	}
}
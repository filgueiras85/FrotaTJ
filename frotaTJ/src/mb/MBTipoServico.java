package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.TipoServico;
import dao.TipoServicoDAO;

public class MBTipoServico {
	private static MBTipoServico tipoServicoMB = new MBTipoServico();
	
	private MBTipoServico(){
		
	}
	
	public static MBTipoServico getInstance(){
		return tipoServicoMB;
		
	}
	
	public String inserir(TipoServico tipoServico) {
		
		String retorno = "ok";
		TipoServicoDAO daoTipoServico = TipoServicoDAO.getInstance();
		try {
			daoTipoServico.save(tipoServico);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(TipoServico tipoServico) {
		String retorno = "ok";
		TipoServicoDAO daoTipoServico = TipoServicoDAO.getInstance();
		try {
			daoTipoServico.update(tipoServico);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(TipoServico tipoServico) {
		String retorno = "ok";
		TipoServicoDAO daoTipoServico = TipoServicoDAO.getInstance();
		try {
			daoTipoServico.delete(tipoServico);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public TipoServico retornarTipoServico(int id) {
		TipoServicoDAO daoTipoServico = TipoServicoDAO.getInstance();
		return daoTipoServico.findById(id);
		
	}

	public List<TipoServico> findByAll() throws ClassNotFoundException, SQLException{
		TipoServicoDAO daoTipoServico = TipoServicoDAO.getInstance();
		return daoTipoServico.findAll();
	}
}










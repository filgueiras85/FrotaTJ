package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Unidade;
import dao.UnidadeDAO;

public class MBUnidade {
	private static MBUnidade unidadeMB = new MBUnidade();
	
	private MBUnidade(){
		
	}
	
	public static MBUnidade getInstance(){
		return unidadeMB;
	}
	
	public String inserir(Unidade unidade) {
		
		String retorno = "ok";
		UnidadeDAO daoUnidade = UnidadeDAO.getInstance();
		try {
			daoUnidade.save(unidade);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Unidade unidade) {
		String retorno = "ok";
		UnidadeDAO daoUnidade = UnidadeDAO.getInstance();
		try {
			daoUnidade.update(unidade);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Unidade unidade) {
		String retorno = "ok";
		UnidadeDAO daoUnidade = UnidadeDAO.getInstance();
		try {
			daoUnidade.delete(unidade);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public Unidade retornarUnidade(int id) {
		UnidadeDAO daoUnidade = UnidadeDAO.getInstance();
		return daoUnidade.findById(id);
		
	}

	public List<Unidade> listarUnidades() throws ClassNotFoundException, SQLException{
		UnidadeDAO daoUnidade = UnidadeDAO.getInstance();
		return daoUnidade.findAll();
	}
}










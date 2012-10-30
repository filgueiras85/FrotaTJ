package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Modelo;
import dao.ModeloDAO;

public class MBModelo {
	private static MBModelo modeloMB = new MBModelo();
	
	private MBModelo(){
		
	}
	
	public static MBModelo getInstance(){
		return modeloMB;
		
	}
	
	public String inserir(Modelo modelo) {
		
		String retorno = "ok";
		ModeloDAO daoModelo = ModeloDAO.getInstance();
		try {
			daoModelo.save(modelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Modelo modelo) {
		String retorno = "ok";
		ModeloDAO daoModelo = ModeloDAO.getInstance();
		try {
			daoModelo.update(modelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Modelo modelo) {
		String retorno = "ok";
		ModeloDAO daoModelo = ModeloDAO.getInstance();
		try {
			daoModelo.delete(modelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public Modelo retornarModelo(int id) {
		ModeloDAO daoModelo = ModeloDAO.getInstance();
		return daoModelo.findById(id);
		
	}

	public List<Modelo> findByAll() throws ClassNotFoundException, SQLException{
		ModeloDAO daoModelo= ModeloDAO.getInstance();
		return daoModelo.findAll();
	}
}










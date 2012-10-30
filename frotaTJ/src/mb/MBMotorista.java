package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Motorista;
import dao.MotoristaDAO;

public class MBMotorista {
	private static MBMotorista motoristaMB = new MBMotorista();
	
	private MBMotorista(){
		
	}
	
	public static MBMotorista getInstance(){
		return motoristaMB;
		
	}
	
	public String inserir(Motorista motorista) {
		
		String retorno = "ok";
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		try {
			daoMotorista.save(motorista);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Motorista motorista) {
		String retorno = "ok";
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		try {
			daoMotorista.update(motorista);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Motorista motorista) {
		String retorno = "ok";
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		try {
			daoMotorista.delete(motorista);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public Motorista retornarMotorista(int id) {
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		return daoMotorista.findById(id);
		
	}

	public List<Motorista> findByAll() throws ClassNotFoundException, SQLException{
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		return daoMotorista.findAll();
	}
}










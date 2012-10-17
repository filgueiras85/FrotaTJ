package mb.abastecimento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.abastecimento.Abastecimento;
import dao.abastecimento.AbastecimentoDAO;

public class AbastecimentoMB {
	private static AbastecimentoMB abastecimentoMB = new AbastecimentoMB();
	
	private AbastecimentoMB(){
		
	}
	
	public static AbastecimentoMB getInstance(){
		return abastecimentoMB;
		
	}
	
	public String inserir(Abastecimento abastecimento) {
		
		String retorno = "ok";
		AbastecimentoDAO daoAbastecimento = AbastecimentoDAO.getInstance();
		try {
			daoAbastecimento.save(abastecimento);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Abastecimento abastecimento) {
		String retorno = "ok";
		AbastecimentoDAO daoAbastecimento = AbastecimentoDAO.getInstance();
		try {
			daoAbastecimento.update(abastecimento);
		} catch (Exception e) {
			retorno = "erro";
		}
		
		return retorno;
	}
	

	public String apagar(Abastecimento abastecimento) {
		String retorno = "ok";
		AbastecimentoDAO daoAbastecimento = AbastecimentoDAO.getInstance();
		try {
			daoAbastecimento.delete(abastecimento);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public Abastecimento retornarAbastecimento(int id) {
		AbastecimentoDAO daoAbastecimento = AbastecimentoDAO.getInstance();
		return daoAbastecimento.findById(id);
		
	}

	public List<Abastecimento> finbByAll() throws ClassNotFoundException, SQLException{
		AbastecimentoDAO abastecimentoDAO = AbastecimentoDAO.getInstance();
		return abastecimentoDAO.findAll();
	}
}










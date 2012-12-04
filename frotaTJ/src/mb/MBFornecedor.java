package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.Fornecedor;
import dao.FornecedorDAO;

public class MBFornecedor {
	private static MBFornecedor fornecedorMB = new MBFornecedor();
	
	private MBFornecedor(){
		
	}
	
	public static MBFornecedor getInstance(){
		return fornecedorMB;
		
	}
	
	public String inserir(Fornecedor fornecedor) {
		
		String retorno = "ok";
		FornecedorDAO daoFornecedor = FornecedorDAO.getInstance();
		try {
			daoFornecedor.save(fornecedor);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Fornecedor fornecedor) {
		String retorno = "ok";
		FornecedorDAO daoFornecedor = FornecedorDAO.getInstance();
		try {
			daoFornecedor.update(fornecedor);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Fornecedor fornecedor) {
		String retorno = "ok";
		FornecedorDAO daoFornecedor = FornecedorDAO.getInstance();
		try {
			daoFornecedor.delete(fornecedor);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	
	public String cadastrarTelefone (String tel){
		boolean validar = false;
		if (validar == true){
		return tel;
		} else {
			return null;
		}
	}

	
	public static  String validarTelefone(String tel) {  
		  
	      String formato = "\\([0-9]{2}?\\)[0-9]{4}?\\-[0-9]{4}";
	      String formato2 = "\\([0-9]{2}?\\)[0-9]{5}?\\-[0-9]{4}";
	      String formato3 = "\\([0-9]{2}?\\)[0-9]{4}?\\-[0-9]{5}";
	      String validar = "ok";  
	      if(tel == null || tel.matches(formato3) || tel.matches(formato2) || tel.matches(formato)){  
	    	  return validar;  
	      } else {  
	    	  JOptionPane.showMessageDialog(null,"Cadastro de número telefônico incorreto! /n O formato do número deve ser (DDD) 1111-1111.");
		    
		 
	      }
		return null;
	   }  
	
	  
	
	public Fornecedor retornarFornecedor(int id) {
		FornecedorDAO daoFornecedor = FornecedorDAO.getInstance();
		return daoFornecedor.findById(id);
		
	}

	public List<Fornecedor> listarFornecedores() throws ClassNotFoundException, SQLException{
		FornecedorDAO daoFornecedor = FornecedorDAO.getInstance();
		return daoFornecedor.findAll();
	}
}










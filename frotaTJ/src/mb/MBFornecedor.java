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
import dao.Servico;

public class MBFornecedor {
	private static MBFornecedor fornecedorMB = new MBFornecedor();
	
	private MBFornecedor(){
		
	}
	
	public static MBFornecedor getInstance(){
		return fornecedorMB;
		
	}
	
	public String inserir(Fornecedor fornecedor) {
		
		String retorno = "Cadastro inserido.";
		FornecedorDAO daoFornecedor = FornecedorDAO.getInstance();
		try {
			daoFornecedor.save(fornecedor);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Fornecedor fornecedor) {
		String retorno = "Cadastro alterado.";
		FornecedorDAO daoFornecedor = FornecedorDAO.getInstance();
		try {
			daoFornecedor.update(fornecedor);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Fornecedor fornecedor) {
		String retorno = "Cadastro removido.";
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

	public Fornecedor retornarFornecedor(int id) {
		FornecedorDAO daoFornecedor = FornecedorDAO.getInstance();
		return daoFornecedor.findById(id);
		
	}

	public List<Fornecedor> listarFornecedores() throws ClassNotFoundException, SQLException{
		FornecedorDAO daoFornecedor = FornecedorDAO.getInstance();
		return daoFornecedor.findAll();
	}
	public List<Fornecedor> FornecedorServico(List<Servico> listaServicos) throws ClassNotFoundException, SQLException{
		List<Fornecedor> listaFornecedor = listarFornecedores();
		List<Fornecedor> fornecedor = new ArrayList<>();
		for(int i=0;i<listaFornecedor.size();i++){
			for(int j=0;j<listaServicos.size();j++){
				if(listaFornecedor.get(i).getIdfornecedor() == listaServicos.get(j).getFornecedor().getIdfornecedor()){
					Fornecedor f = retornarFornecedor(listaFornecedor.get(i).getIdfornecedor());
					if(!fornecedor.contains(f)){
						fornecedor.add(f);
					}
				}
			}
		}
		return fornecedor;
			
	}
		
}









 
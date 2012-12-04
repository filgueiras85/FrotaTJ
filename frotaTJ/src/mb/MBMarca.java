package mb;


import java.sql.SQLException;
import java.util.List;

import dao.Marca;
import dao.MarcaDAO;

public class MBMarca {
	private static MBMarca marcaMB = new MBMarca();
	
	private MBMarca(){
		
	}
	
	public static MBMarca getInstance(){
		return marcaMB;
		
	}
	
	public String inserir(Marca marca) {
		
		String retorno = "Cadastro inserido.";
		MarcaDAO daoMarca = MarcaDAO.getInstance();
		try {
			daoMarca.save(marca);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Marca marca) {
		String retorno = "Cadastro alterado.";
		MarcaDAO daoMarca = MarcaDAO.getInstance();
		try {
			daoMarca.update(marca);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Marca marca) {
		String retorno = "Cadastro removido.";
		MarcaDAO daoMarca = MarcaDAO.getInstance();
		try {
			daoMarca.delete(marca);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public Marca retornarMarca(int id) {
		MarcaDAO daoMarca = MarcaDAO.getInstance();
		return daoMarca.findById(id);
		
	}

	public List<Marca> listarMarcas() throws ClassNotFoundException, SQLException{
		MarcaDAO daoMarca = MarcaDAO.getInstance();
		return daoMarca.findAll();
	}
}










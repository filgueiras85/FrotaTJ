package mb;

import java.sql.SQLException;
import java.util.List;

import dao.Abastecimento;
import dao.AbastecimentoDAO;

public class MBAbastecimento {
	private static MBAbastecimento abastecimentoMB = new MBAbastecimento();
	
	private MBAbastecimento(){
		
	}
	
	public static MBAbastecimento getInstance(){
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

	public List<Abastecimento> listarAbastecimentos() throws ClassNotFoundException, SQLException{
		AbastecimentoDAO abastecimentoDAO = AbastecimentoDAO.getInstance();
		return abastecimentoDAO.findAll();
	}
}










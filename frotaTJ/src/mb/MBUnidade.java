package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Unidade;
import dao.UnidadeDAO;
import dao.Veiculo;

public class MBUnidade {
	private static MBUnidade unidadeMB = new MBUnidade();
	
	private MBUnidade(){
		
	}
	
	public static MBUnidade getInstance(){
		return unidadeMB;
	}
	
	public String inserir(Unidade unidade) {
		
		String retorno = "Cadastro inserido.";
		UnidadeDAO daoUnidade = UnidadeDAO.getInstance();
		try {
			daoUnidade.save(unidade);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Unidade unidade) {
		String retorno = "Cadastro alterado.";
		UnidadeDAO daoUnidade = UnidadeDAO.getInstance();
		try {
			daoUnidade.update(unidade);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Unidade unidade) {
		String retorno = "Cadastro removido.";
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
	
	public List<Unidade> UnidadesPorVeiculos(List<Veiculo> listaVeiculo) throws ClassNotFoundException, SQLException{
		List<Unidade> unidade = new ArrayList<Unidade>();
		for(int i=0;i<listaVeiculo.size();i++){
			Unidade u = retornarUnidade(listaVeiculo.get(i).getUnidade().getIdunidade());
			if(!unidade.contains(u))
				unidade.add(u);
		}		
		
		return unidade;
		
	}
}










package mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Abastecimento;
import dao.AbastecimentoDAO;
import dao.Veiculo;

public class MBAbastecimento {
	private static MBAbastecimento abastecimentoMB = new MBAbastecimento();
	
	private MBAbastecimento(){
		
	}
	
	public static MBAbastecimento getInstance(){
		return abastecimentoMB;
		
	}
	
	public String inserir(Abastecimento abastecimento) {
		
		String retorno = "Cadastro efetuado.";
		AbastecimentoDAO daoAbastecimento = AbastecimentoDAO.getInstance();
		try {
			daoAbastecimento.save(abastecimento);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Abastecimento abastecimento) {
		String retorno = "Cadastro alterado.";
		AbastecimentoDAO daoAbastecimento = AbastecimentoDAO.getInstance();
		try {
			daoAbastecimento.update(abastecimento);
		} catch (Exception e) {
			retorno = "erro";
		}
		
		return retorno;
	}
	

	public String apagar(Abastecimento abastecimento) {
		String retorno = "Cadastro removido.";
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
	public List<Abastecimento> ListarosAbastecimentodoVeiculo(Veiculo v) {
		List<Abastecimento> Lista1;
		try {
			Lista1 = listarAbastecimentos();
			
			List<Abastecimento> lista = new ArrayList<>();
			
			for (int i = 0; i < Lista1.size(); i++) {
				if(Lista1.get(i).getVeiculo().getIdveiculo().equals(v.getIdveiculo())){
					lista.add(Lista1.get(i));
				}
				
			}
			
			System.out.println(lista.size()+"oi"+Lista1.size());
			return lista;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
}










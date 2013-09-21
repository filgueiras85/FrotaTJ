package mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Marca;
import dao.Modelo;
import dao.ModeloDAO;
import dao.Servico;
import dao.Veiculo;

public class MBModelo {
	private static MBModelo modeloMB = new MBModelo();

	private MBModelo(){

	}

	public static MBModelo getInstance(){
		return modeloMB;

	}

	public String inserir(Modelo modelo) {

		String retorno = "Cadastro inserido.";
		ModeloDAO daoModelo = ModeloDAO.getInstance();
		try {
			daoModelo.save(modelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}

	public String editar(Modelo modelo) {
		String retorno = "Cadastro alterado.";
		ModeloDAO daoModelo = ModeloDAO.getInstance();
		try {
			daoModelo.update(modelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}


	public String apagar(Modelo modelo) {
		String retorno = "Cadastro removido.";
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
	
	public Modelo retornarModelo( String modelo ){
		
		List<Modelo> listaModelo;
		try {
			listaModelo = listarModelos();
			Modelo m = new Modelo();
			for( int i = 0; i<listaModelo.size();i++){
				if ( listaModelo.get(i).getNome().equals(modelo)){
					m = listaModelo.get(i);
				}
			}
			return m;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public List<Modelo> listarModelos() throws ClassNotFoundException, SQLException{
		ModeloDAO daoModelo= ModeloDAO.getInstance();
		return daoModelo.findAll();
	}

	public List<Modelo> listaModeloServicoUnidade(int idUnidade, int idTipoServico, int idMarca) throws ClassNotFoundException, SQLException{
		ModeloDAO daoModelo = ModeloDAO.getInstance();
		return daoModelo.ModeloServicoUnidade(idUnidade, idTipoServico, idMarca);
	}

	public List<Modelo> ModeloPorVeiculo(List<Veiculo> listaVeiculo) throws ClassNotFoundException, SQLException{

		List<Modelo> modelo = new ArrayList<Modelo>();
		for(int i=0;i<listaVeiculo.size();i++){
			Modelo m = retornarModelo(listaVeiculo.get(i).getModelo().getIdmodelo());
			if(!modelo.contains(m)){
				modelo.add(m);
			}
		}
		return modelo;
	}

	public List<Modelo> ModeloVeiculo(List<Servico> listaServico) throws ClassNotFoundException, SQLException{

		List<Modelo> modelo = new ArrayList<>();
		for(int i=0;i<listaServico.size();i++){
			Modelo m = retornarModelo(listaServico.get(i).getVeiculo().getModelo().getIdmodelo());
			if(!modelo.contains(m))
				modelo.add(m);

		}
		return modelo;
	}

	public List<Modelo> ModeloMarca(Marca marca){
		ModeloDAO daoModelo = ModeloDAO.getInstance();
		return daoModelo.ModeloMarca(marca);
	}
}










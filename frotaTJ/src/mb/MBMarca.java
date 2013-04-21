package mb;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Marca;
import dao.MarcaDAO;
import dao.Modelo;
import dao.Servico;

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
	public Marca retornarMarca(String marca) throws ClassNotFoundException, SQLException{
		List<Marca> listaMarca = listarMarcas();
		for( int i=0;i<listaMarca.size();i++){
			if ( listaMarca.get(i).getNome().equals(marca)){
				return listaMarca.get(i);
			}
		}
		return null;		
	}
	
	public List<Marca> listarMarcas() throws ClassNotFoundException, SQLException{
		MarcaDAO daoMarca = MarcaDAO.getInstance();
		return daoMarca.findAll();
	}
	public List<Marca> listaMarcaModeloServicoUnidade(int idUnidade, int idTipoServico, int idModelo) throws ClassNotFoundException, SQLException{
		MarcaDAO daoMarca = MarcaDAO.getInstance();
		return daoMarca.MarcaModeloServicoUnidade(idUnidade, idTipoServico, idModelo);
	}
	public List<Marca> MarcaModelo(List<Modelo> listaModelos)  throws ClassNotFoundException, SQLException{
		List<Marca> listaMarca = listarMarcas();
		List<Marca> marca = new ArrayList<>();
		for(int i=0;i<listaMarca.size();i++){
			for(int j=0;j<listaModelos.size();j++){
				if(listaMarca.get(i).getIdmarca() == listaModelos.get(j).getMarca().getIdmarca()){
					Marca m = retornarMarca(listaMarca.get(i).getIdmarca());
					if(!marca.contains(m)){
						marca.add(m);	
					}
				}
			}
		}
		return marca;
	}
	
	public List<Marca> MarcaPorModelo(Modelo modelo) throws ClassNotFoundException, SQLException{
		List<Marca> listaMarca = listarMarcas();
		List<Marca> marca = new ArrayList<Marca>();
		for(int i=0;i<listaMarca.size();i++){
			Marca m = retornarMarca(modelo.getMarca().getIdmarca());
			if(!marca.contains(m))
				marca.add(m);
		}

		return marca;
	}

	public List<Marca> MarcaPorServico(List<Servico> listaServico)  throws ClassNotFoundException, SQLException{
		List<Marca> marca = new ArrayList<>();
		for(int i=0;i<listaServico.size();i++){
			//if(listaServico.get(i).getTipoServico().getIdtipoServico() == tipoServico.getIdtipoServico()){
				Marca m = retornarMarca(listaServico.get(i).getVeiculo().getModelo().getMarca().getIdmarca());
				if(!marca.contains(m)){
					marca.add(m);	
				}
			//}
		}
		return marca;
	}
}











package mb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Servico;
import dao.TipoServico;
import dao.TipoServicoDAO;

public class MBTipoServico {
	private static MBTipoServico tipoServicoMB = new MBTipoServico();

	private MBTipoServico(){

	}

	public static MBTipoServico getInstance(){
		return tipoServicoMB;

	}

	public String inserir(TipoServico tipoServico) {

		String retorno = "Cadastro inserido.";
		TipoServicoDAO daoTipoServico = TipoServicoDAO.getInstance();
		try {
			daoTipoServico.save(tipoServico);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}

	public String editar(TipoServico tipoServico) {
		String retorno = "Cadastro alterado.";
		TipoServicoDAO daoTipoServico = TipoServicoDAO.getInstance();
		try {
			daoTipoServico.update(tipoServico);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}


	public String apagar(TipoServico tipoServico) {
		String retorno = "Cadastro removido.";
		TipoServicoDAO daoTipoServico = TipoServicoDAO.getInstance();
		try {
			daoTipoServico.delete(tipoServico);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}

	public TipoServico retornarTipoServico(int id) {
		TipoServicoDAO daoTipoServico = TipoServicoDAO.getInstance();
		return daoTipoServico.findById(id);

	}

	public List<TipoServico> listarTipoServicos() throws ClassNotFoundException, SQLException{
		TipoServicoDAO daoTipoServico = TipoServicoDAO.getInstance();
		return daoTipoServico.findAll();
	}
<<<<<<< HEAD

	public List<TipoServico> listarTipoServicoUnidad(List<Servico> listaServico){
		List<TipoServico> listaTipo;
		try {
			listaTipo = listarTipoServicos();
			List<TipoServico> tipoServico = new ArrayList<TipoServico>();
			for(int i=0;i<listaTipo.size();i++){
				for(int j=0;j<listaServico.size();j++){
					if(listaTipo.get(i).getIdtipoServico() == listaServico.get(j).getTipoServico().getIdtipoServico()){
						TipoServico t = retornarTipoServico(listaTipo.get(i).getIdtipoServico());
						if(!tipoServico.contains(t))
							tipoServico.add(t);
					}
				}
			}
			return tipoServico;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
=======
>>>>>>> origin/master
	public List<TipoServico> TipoServicoPorServico(List<Servico> listaServico){
		List<TipoServico> tipoServico = new ArrayList<TipoServico>();
		for(int i=0;i<listaServico.size();i++){
			TipoServico t = retornarTipoServico(listaServico.get(i).getTipoServico().getIdtipoServico());
			if(!tipoServico.contains(t))
				tipoServico.add(t);
		}
		return tipoServico;
	}
<<<<<<< HEAD

=======
>>>>>>> origin/master
}
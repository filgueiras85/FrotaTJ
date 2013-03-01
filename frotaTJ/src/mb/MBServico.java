package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import dao.Servico;
import dao.ServicoDAO;
import dao.TipoServico;
import dao.TipoServicoModelo;
import dao.TipoServicoVeiculo;
import dao.Veiculo;

public class MBServico {
	private static MBServico servicoMB = new MBServico();
	
	private MBServico(){
		
	}
	
	public static MBServico getInstance(){
		return servicoMB;
		
	}
	
	public String inserir(Servico servico) {
		
		String retorno = "Cadastro inserido.";
		ServicoDAO daoServico = ServicoDAO.getInstance();
		try {
			daoServico.save(servico);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Servico servico) {
		String retorno = "Cadastro alterado.";
		ServicoDAO daoServico = ServicoDAO.getInstance();
		try {
			daoServico.update(servico);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	public List<Servico> ListarosServicodoVeiculo(Integer id, TipoServico tipoServico) {
		List<Servico> Lista1;
		try {
			Lista1 = listarServicos();
			List<Servico> lista = new ArrayList<>();
			System.out.println(Lista1.size()+"servico");
			for (int i = 0; i < Lista1.size(); i++) {
				if(Lista1.get(i).getVeiculo().getIdveiculo().equals(id)){
					if(Lista1.get(i).getTipoServico().equals(tipoServico)){
						lista.add(Lista1.get(i));

					}
				}
				System.out.println(Lista1.get(i).getVeiculo().getIdveiculo().equals(id));
				
			}
			
			System.out.println(lista);
			return lista;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	

	public String apagar(Servico servico) {
		String retorno = "Cadastro removido.";
		ServicoDAO daoServico = ServicoDAO.getInstance();
		try {
			daoServico.delete(servico);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public Servico retornarServico(int id) {
		ServicoDAO daoServico = ServicoDAO.getInstance();
		return daoServico.findById(id);
		
	}

	public List<Servico> listarServicos() throws ClassNotFoundException, SQLException{
		ServicoDAO daoServico = ServicoDAO.getInstance();
		return daoServico.findAll();
	}
	public List<Servico> ServicoPorVeiculo (List<Veiculo> listaVeiculos) throws ClassNotFoundException, SQLException{
		List<Servico> listaServico = listarServicos();
		List<Servico> servico = new ArrayList();
		for(int i=0;i<listaServico.size();i++){
			for(int j=0;j<listaVeiculos.size();j++){
				if(listaServico.get(i).getVeiculo().getIdveiculo() == listaVeiculos.get(j).getIdveiculo()){
					Servico s = retornarServico(listaServico.get(i).getIdServico());
					if(!servico.contains(s)){
						servico.add(s);
					}
				}
			}
		}
		return servico;
	}
	
	
	public List<Servico> listaServicoPorUnidade(int idUnidade) throws ClassNotFoundException, SQLException{
		ServicoDAO daoServico = ServicoDAO.getInstance();
		return daoServico.findServicoByUnidade(idUnidade);
	}
	public List<Servico> listarTipoServicoUnidade(int idUnidade) throws ClassNotFoundException, SQLException{

		ServicoDAO daoServico = ServicoDAO.getInstance();
		return daoServico.TipoServicoUnidade(idUnidade);
	}
	public List<Servico> listaMotoristaTipoServicoUnidade(int idUnidade, int tipoServico) throws ClassNotFoundException, SQLException{
		ServicoDAO daoServico = ServicoDAO.getInstance();
		return daoServico.MotoristaTipoServicoUnidade(idUnidade, tipoServico);
	}
	public List<Servico> listaServicoPlacaVeiculo() throws ClassNotFoundException, SQLException{
		ServicoDAO daoServico = ServicoDAO.getInstance();
		return daoServico.ServicoPlacaVeiculo();
	}
	public List<Servico> ServicoTipoServico(TipoServico tipoServico) throws ClassNotFoundException, SQLException{
		ServicoDAO daoServico = ServicoDAO.getInstance();
		return daoServico.ServicoTipoServico(tipoServico);
	}
	
}










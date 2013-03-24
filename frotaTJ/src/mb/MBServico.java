package mb;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.Util;

import dao.Fornecedor;
import dao.Motorista;
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
	public List<Servico> findAllServicoPorData(Date dataInicio, Date dataFinal) throws ClassNotFoundException, SQLException{
		List<Servico> listaServico = listarServicos();
		List<Servico> servico = new ArrayList<Servico>();
		Util util = Util.getInstance();
		if (dataInicio.before(dataFinal) || dataInicio.equals(dataFinal)){
			for(int i=0;i<listaServico.size();i++){
				Date d = util.RetornaData(listaServico.get(i).getData2());
				if(d.equals(dataInicio) || d.equals(dataFinal) || d.after(dataInicio) && d.before(dataFinal)){
					Servico s = retornarServico(listaServico.get(i).getIdServico());
					servico.add(s);

				}
			}
			return servico;
		}else{
			return null;
		}	
	}
	public List<Servico> ServicoPorData(Date dataInicio, Date dataFinal) throws ClassNotFoundException, SQLException{
		List<Servico> listaServico = listarServicos();
		List<Servico> servico = new ArrayList<Servico>();
		Util util = Util.getInstance();
		if (dataInicio.before(dataFinal) || dataInicio.equals(dataFinal)){
			for(int i=0;i<listaServico.size();i++){
				Date d = util.RetornaData(listaServico.get(i).getData2());
				if(d.equals(dataInicio) || d.equals(dataFinal) || d.after(dataInicio) && d.before(dataFinal)){
					Servico s = retornarServico(listaServico.get(i).getIdServico());
					if(!servico.contains(s)){
						servico.add(s);
					}
				}
			}
			return servico;
		}else{
			return null;
		}	
	}
	public List<Servico> ServicoPorVeiculos (List<Veiculo> listaVeiculos, List<Servico> listaServico) throws ClassNotFoundException, SQLException{
		List<Servico> servico = new ArrayList<Servico>();
		for(int i=0;i<listaVeiculos.size();i++){
			for(int j=0;j<listaServico.size();j++){
				if(listaServico.get(j).getVeiculo().getIdveiculo() == listaVeiculos.get(i).getIdveiculo()){
					Servico s = retornarServico(listaServico.get(j).getIdServico());
					if(!servico.contains(s)){
						servico.add(s);
					}
				}
			}
		}
		return servico;
	}
	public List<Servico> ServicosTipoServico(TipoServico tipoServico, List<Servico> listaServico) throws ClassNotFoundException, SQLException{
		ServicoDAO daoServico = ServicoDAO.getInstance();
		List<Servico> listaTipoServico = daoServico.ServicoTipoServico(tipoServico);
		List<Servico> servico = new ArrayList<Servico>();
		for(int i=0;i<listaTipoServico.size();i++){
			for(int j=0;j<listaServico.size();j++){
				if(listaTipoServico.get(i) == listaServico.get(j)){
					Servico s = retornarServico(listaTipoServico.get(i).getIdServico());
					servico.add(s);		
				}
			}
		}
		return servico;
	}
	public List<Servico> ServicosPorFornecedor(Fornecedor fornecedor, List<Servico> listaServico) throws ClassNotFoundException, SQLException{
		List<Servico> servico = new ArrayList<Servico>();
		for(int i=0;i<listaServico.size();i++){
			if(listaServico.get(i).getFornecedor().getIdfornecedor() == fornecedor.getIdfornecedor()){
				Servico s = retornarServico(listaServico.get(i).getIdServico());
				//if(!servico.contains(s))
					servico.add(s);
			}
		}
		return servico;
	}
	public List<Servico> ServicosPorMotorista(Motorista motorista, List<Servico> listaServico) throws ClassNotFoundException, SQLException{
		List<Servico> servico = new ArrayList<Servico>();
		for(int i=0;i<listaServico.size();i++){
			if(listaServico.get(i).getMotorista().getIdmotorista()== motorista.getIdmotorista()){
				Servico s = retornarServico(listaServico.get(i).getIdServico());
				if(!servico.contains(s))
					servico.add(s);
			}
		}
		return servico;		
	}
}










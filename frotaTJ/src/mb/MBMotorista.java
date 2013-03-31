package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Motorista;
import dao.MotoristaDAO;
import dao.Servico;

public class MBMotorista {
	private static MBMotorista motoristaMB = new MBMotorista();
	
	private MBMotorista(){
		
	}
	
	public static MBMotorista getInstance(){
		return motoristaMB;
		
	}
	
	public String inserir(Motorista motorista) {
		
		String retorno = "Cadastro inserido.";
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		try {
			daoMotorista.save(motorista);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Motorista motorista) {
		String retorno = "Cadastro alterado.";
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		try {
			daoMotorista.update(motorista);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Motorista motorista) {
		String retorno = "Cadastro removido.";
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		try {
			daoMotorista.delete(motorista);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public Motorista retornarMotorista(int id) {
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		return daoMotorista.findById(id);
		
	}

	public List<Motorista> listarMotoristas() throws ClassNotFoundException, SQLException{
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		return daoMotorista.findAll();
	}
<<<<<<< HEAD
	
=======

	public List<Motorista> listarMotoristasPorUnidade(int idUnidade) throws ClassNotFoundException, SQLException{
		MotoristaDAO daoMotorista = MotoristaDAO.getInstance();
		return daoMotorista.findByUnidade(MBUnidade.getInstance().retornarUnidade(idUnidade));
	}
>>>>>>> origin/master
	public List<Motorista> MotoristaServico(List<Servico> listaServicos) throws ClassNotFoundException, SQLException{
		List<Motorista> listaMotorista = listarMotoristas();
		List<Motorista> motorista = new ArrayList<>();
		for(int i=0;i<listaMotorista.size();i++){
			for(int j=0;j<listaServicos.size();j++){
				if(listaMotorista.get(i).getIdmotorista() == listaServicos.get(j).getMotorista().getIdmotorista()){
					Motorista m = retornarMotorista(listaMotorista.get(i).getIdmotorista());
					// se motorista nao foi adicionado
					if(!motorista.contains(m)){
						motorista.add(m);
					}
				}
			}
		}
		return motorista;
	}
}










package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.Modelo;
import dao.Servico;
import dao.ServicoDAO;
import dao.TipoServico;
import dao.TipoServicoDAO;
import dao.TipoServicoModelo;
import dao.TipoServicoModeloDAO;
import dao.TipoServicoModeloId;
import dao.Veiculo;

public class MBTipoServiçoModelo {
	private static MBTipoServiçoModelo tipoServicoModeloMB = new MBTipoServiçoModelo();
	
	private MBTipoServiçoModelo(){
		
	}
	
	public static MBTipoServiçoModelo getInstance(){
		return tipoServicoModeloMB;
		
	}
	
	public String inserir(TipoServicoModelo tipoServicoModelo) {
		
		String retorno = "Cadastro inserido.";
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		try {
			daoTipoServicoModelo.save(tipoServicoModelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(TipoServicoModelo tipoServicoModelo) {
		String retorno = "Cadastro alterado.";
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		try {
			daoTipoServicoModelo.update(tipoServicoModelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(TipoServicoModelo tipoServicoModelo) {
		String retorno = "Cadastro removido.";
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		try {
			daoTipoServicoModelo.delete(tipoServicoModelo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public TipoServicoModelo retornarTipoServicoModelo(TipoServicoModeloId id) {
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		return daoTipoServicoModelo.findById(id);
		
	}
	
	public List<TipoServico> ListarosTipoServicoModelo(Integer id) {
		List<TipoServicoModelo> Lista1;
		try {
			Lista1 = listarTipoServicosModelos();
			List<TipoServico> lista = new ArrayList<>();
			
			for (int i = 0; i < Lista1.size(); i++) {
				if(Lista1.get(i).getModelo().getIdmodelo().equals(id)){
					lista.add(Lista1.get(i).getTipoServico());
				}
				
			}
			
			
			return lista;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	public List<TipoServicoModelo> ListarosTipoServicodoModelo(Integer id) {
		List<TipoServicoModelo> Lista1;
		try {
			Lista1 = listarTipoServicosModelos();
			List<TipoServicoModelo> lista = new ArrayList<>();
			
			for (int i = 0; i < Lista1.size(); i++) {
				if(Lista1.get(i).getModelo().getIdmodelo().equals(id)){
					lista.add(Lista1.get(i));
				}
				
			}
			
			
			return lista;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	public List<TipoServicoModelo> listarTipoServicosModelos() throws ClassNotFoundException, SQLException{
		TipoServicoModeloDAO daoTipoServicoModelo = TipoServicoModeloDAO.getInstance();
		return daoTipoServicoModelo.findAll();
	}
	
	public List<TipoServicoModelo> atualizaStatusVeiculo (Veiculo veiculo){
		System.out.println("Aqui ohh");
		TipoServicoModeloDAO daoTiposervicomodelo = TipoServicoModeloDAO.getInstance();
		ServicoDAO servicoDAO = ServicoDAO.getInstance();
		MBVeiculo veiculoMB = MBVeiculo.getInstance();
		List<TipoServicoModelo> tsmResultado = daoTiposervicomodelo.findTipoServicoByModelo(veiculo.getModelo());
		List<TipoServicoModelo> tsm = daoTiposervicomodelo.findTipoServicoByModelo(veiculo.getModelo());		
		tsmResultado.clear();

		Calendar hoje = Calendar.getInstance();
		Calendar dataProximoServicoPercent = Calendar.getInstance();  
		Calendar dataProximoServico = Calendar.getInstance();   

		String retorno = "";

		for (int i=0;i<tsm.size();i++){
			TipoServicoModelo tsmTemp = new TipoServicoModelo();
			// para cada tipo de servico busca o ultimo servico feito
			// terminar o teste para cada servico quando != null			

			Servico ultimoServico = servicoDAO.findUltimoServico(veiculo, tsm.get(i).getTipoServico());
			tsmTemp.setTipoServico(tsm.get(i).getTipoServico());

			if ( ultimoServico != null ){
				// pega data do último serviço efetuado e adiciona o tempo - 1 mes do próximo serviço a fazer
				dataProximoServicoPercent.setTime(ultimoServico.getData2());
				dataProximoServicoPercent.add(Calendar.MONTH, ((tsm.get(i).getTempo()-1)));
				// pega data do último serviço efetuado e adiciona o tempo do próximo serviço a fazer				
				dataProximoServico.setTime(ultimoServico.getData2());
				dataProximoServico.add(Calendar.MONTH, (tsm.get(i).getTempo()));  			

				if ( (veiculo.getOdometro() > (ultimoServico.getKm() + (tsm.get(i).getKm()*0.8)))  
						|| ( hoje.after(dataProximoServicoPercent) ) ) {					
					if ( ( (veiculo.getOdometro() > ( ultimoServico.getKm() + (tsm.get(i).getKm()*0.8)) 
							&& veiculo.getOdometro() < ( ultimoServico.getKm() + (tsm.get(i).getKm())) ) )
							|| (hoje.after(dataProximoServicoPercent) && hoje.before(dataProximoServico)) ){
						
						if(hoje.after(dataProximoServico) || (veiculo.getOdometro() > (ultimoServico.getKm() + (tsm.get(i).getKm())))){
							retorno = "vermelho";
						}
						else{
							retorno = "amarelo";
						}
					}						
					else{
						retorno = "vermelho";
					}
				}
				else{
					retorno = "verde";
				}				 
				//setar temporariamente os parametros do proximo serviço pro usuario ver
				tsmTemp.setKm(ultimoServico.getKm() + tsm.get(i).getKm());
				tsmTemp.setDataProximoServico(dataProximoServico.getTime());
				tsmTemp.setSituacao(retorno);
				tsmResultado.add(i, tsmTemp);
			}
			else{
				// se não tem serviço efetuado, pega data do cadastro e adiciona tempo - 1 mes do proximo serviço a fazer
				dataProximoServicoPercent.setTime(veiculo.getDataCadastro());
				dataProximoServicoPercent.add(Calendar.MONTH, ((tsm.get(i).getTempo()-1))); 
				// se não tem serviço efetuado, pega data do cadastro e adiciona tempo do proximo serviço a fazer				
				dataProximoServico.setTime(veiculo.getDataCadastro());
				dataProximoServico.add(Calendar.MONTH, (tsm.get(i).getTempo()));  			

				if ( (veiculo.getOdometro() > (veiculo.getKmCadastro() + (tsm.get(i).getKm()*0.8)))  
						|| ( hoje.after(dataProximoServicoPercent) ) ) {					

					if ( ( (veiculo.getOdometro() > ( veiculo.getKmCadastro() + (tsm.get(i).getKm()*0.8)) 
							&& veiculo.getOdometro() < ( veiculo.getKmCadastro() + (tsm.get(i).getKm())) ) )
							|| (hoje.after(dataProximoServicoPercent) && hoje.before(dataProximoServico)) ){
						
						if( hoje.after(dataProximoServico) || (veiculo.getOdometro() > (veiculo.getKmCadastro() + (tsm.get(i).getKm())))){
							retorno = "vermelho";
						}
						else{
							retorno = "amarelo";
						}
					}				
					else{
						retorno = "vermelho";
					}
				}
				else{
					retorno = "verde";
				}

				tsmTemp.setKm(veiculo.getKmCadastro() + tsm.get(i).getKm());
				tsmTemp.setDataProximoServico(dataProximoServico.getTime());
				tsmTemp.setSituacao(retorno);
				tsmResultado.add(i, tsmTemp);			
			}								
		}
		
		int i = veiculo.getSituacao().compareToIgnoreCase("verde");

		if (i == 0 && retorno == "amarelo"){
			veiculo.setSituacao("amarelo");
		}
		if (i == 0 && retorno == "vermelho"){
			veiculo.setSituacao("vermelho");
		}
		if (i == -21 && retorno == "verde"){
			veiculo.setSituacao("amarelo");
		}
		if (i==-21 && retorno == "vermelho"){
			veiculo.setSituacao("vermelho");
		}				
		if (i == 9 && retorno != "vermelho"){
			veiculo.setSituacao("vermelho");
		}		
		veiculoMB.editar(veiculo);
		return tsmResultado;
	}
	public List<TipoServicoModelo> findTipoServicoByModelo (Veiculo veiculo){
		TipoServicoModeloDAO daoTiposervicomodelo = TipoServicoModeloDAO.getInstance();
		 ServicoDAO servicoDAO = ServicoDAO.getInstance();
		List<TipoServicoModelo> tsmResultado = daoTiposervicomodelo.findTipoServicoByModelo(veiculo.getModelo());
		List<TipoServicoModelo> tsm = daoTiposervicomodelo.findTipoServicoByModelo(veiculo.getModelo());		
		tsmResultado.clear();
		
		Calendar hoje = Calendar.getInstance();
		String retorno = "";
		
		for (int i=0;i<tsm.size();i++){
			TipoServicoModelo tsmTemp = new TipoServicoModelo();
			// para cada tipo de servico busca o ultimo servico feito
			// terminar o teste para cada servico quando != null			

			Servico ultimoServico = servicoDAO.findUltimoServico(veiculo, tsm.get(i).getTipoServico());
			tsmTemp.setTipoServico(tsm.get(i).getTipoServico());

			if ( ultimoServico != null ){
				
				Calendar dataUltimoServicoPercent = Calendar.getInstance();  
				dataUltimoServicoPercent.setTime(ultimoServico.getData2());
				dataUltimoServicoPercent.add(Calendar.MONTH, ((tsm.get(i).getTempo()-1)));  // adicionar 5 meses  
				
				Calendar dataUltimoServico = Calendar.getInstance();   
				dataUltimoServico.setTime(ultimoServico.getData2());
				dataUltimoServico.add(Calendar.MONTH, (tsm.get(i).getTempo()));  // adicionar 6 meses
				

					if ( (veiculo.getOdometro() > (ultimoServico.getKm() + (tsm.get(i).getKm()*0.8)))  
							|| ( hoje.after(dataUltimoServicoPercent) ) ) {					
						if ( ( (veiculo.getOdometro() > ( ultimoServico.getKm() + (tsm.get(i).getKm()*0.8)) 
								&& veiculo.getOdometro() < ( ultimoServico.getKm() + (tsm.get(i).getKm())) ) ) 
								|| ( hoje.after(dataUltimoServicoPercent) && hoje.before(dataUltimoServico) ) ){
							if( hoje.after(dataUltimoServico)){
								retorno = "vermelho";
							}						
							else{
								retorno = "amarelo";
							}
						}
						else{
							retorno = "vermelho";
						}
					}
					else{
						retorno = "verde";
					}				 
				//setar temporariamente os parametros do proximo serviço pro usuario ver
				tsmTemp.setKm(ultimoServico.getKm() + tsm.get(i).getKm());
				tsmTemp.setDataProximoServico(dataUltimoServico.getTime());
				tsmTemp.setSituacao(retorno);
				tsmResultado.add(i, tsmTemp);

			}
			else{
				
				Calendar dataCadastroPercent = Calendar.getInstance();  
				dataCadastroPercent.setTime(veiculo.getDataCadastro());
				dataCadastroPercent.add(Calendar.MONTH, ((tsm.get(i).getTempo()-1)));  // adicionar 5 meses  

				Calendar dataCadastro = Calendar.getInstance();   
				dataCadastro.setTime(veiculo.getDataCadastro());
				dataCadastro.add(Calendar.MONTH, (tsm.get(i).getTempo()));  // adicionar 6 meses
				

					if ( (veiculo.getOdometro() > (veiculo.getKmCadastro() + (tsm.get(i).getKm()*0.8)))  
							|| ( hoje.after(dataCadastroPercent) ) ) {					
						if ( ( (veiculo.getOdometro() < ( veiculo.getKmCadastro() + (tsm.get(i).getKm())) 
								&& veiculo.getOdometro() < ( veiculo.getKmCadastro() + (tsm.get(i).getKm())) ) ) 
								|| ( hoje.after(dataCadastroPercent) && hoje.before(dataCadastro) ) ){
							if( hoje.after(dataCadastro) ){
								retorno = "vermelho";
							}
							else{
								retorno = "amarelo";
							}						
						}
						else{
							retorno = "vermelho";
						}
					}
					else{
						retorno = "verde";
					}
				 
				tsmTemp.setKm(veiculo.getKmCadastro() + tsm.get(i).getKm());
				tsmTemp.setDataProximoServico(dataCadastro.getTime());
				tsmTemp.setSituacao(retorno);
				tsmResultado.add(i, tsmTemp);			
			}								
		}
		return tsmResultado;
	}
	
	// FUNCIONANDO!! NAO MEXER MAIS POR FAVOR!!!
		public List<TipoServicoModelo> atualizaStatusTodosVeiculos (Veiculo veiculo){
			TipoServicoModeloDAO daoTiposervicomodelo = TipoServicoModeloDAO.getInstance();
			ServicoDAO servicoDAO = ServicoDAO.getInstance();
			List<TipoServicoModelo> tsmResultado = daoTiposervicomodelo.findTipoServicoByModelo(veiculo.getModelo());
			List<TipoServicoModelo> tsm = daoTiposervicomodelo.findTipoServicoByModelo(veiculo.getModelo());		
			tsmResultado.clear();

			Calendar hoje = Calendar.getInstance();
			Calendar dataProximoServicoPercent = Calendar.getInstance();  
			Calendar dataProximoServico = Calendar.getInstance();   

			String retorno = "";

			for (int i=0;i<tsm.size();i++){
				TipoServicoModelo tsmTemp = new TipoServicoModelo();
				// para cada tipo de servico busca o ultimo servico feito
				// terminar o teste para cada servico quando != null			

				Servico ultimoServico = servicoDAO.findUltimoServico(veiculo, tsm.get(i).getTipoServico());
				tsmTemp.setTipoServico(tsm.get(i).getTipoServico());

				if ( ultimoServico != null ){
					// pega data do último serviço efetuado e adiciona o tempo - 1 mes do próximo serviço a fazer
					dataProximoServicoPercent.setTime(ultimoServico.getData2());
					dataProximoServicoPercent.add(Calendar.MONTH, ((tsm.get(i).getTempo()-1)));
					// pega data do último serviço efetuado e adiciona o tempo do próximo serviço a fazer				
					dataProximoServico.setTime(ultimoServico.getData2());
					dataProximoServico.add(Calendar.MONTH, (tsm.get(i).getTempo()));  			

					if ( (veiculo.getOdometro() > (ultimoServico.getKm() + (tsm.get(i).getKm()*0.8)))  
							|| ( hoje.after(dataProximoServicoPercent) ) ) {					
						if ( ( (veiculo.getOdometro() > ( ultimoServico.getKm() + (tsm.get(i).getKm()*0.8)) 
								&& veiculo.getOdometro() < ( ultimoServico.getKm() + (tsm.get(i).getKm())) ) )
								|| (hoje.after(dataProximoServicoPercent) && hoje.before(dataProximoServico)) ){
							
							if(hoje.after(dataProximoServico) || (veiculo.getOdometro() > (ultimoServico.getKm() + (tsm.get(i).getKm())))){
								retorno = "vermelho";
							}
							else{
								retorno = "amarelo";
							}
						}						
						else{
							retorno = "vermelho";
						}
					}
					else{
						retorno = "verde";
					}				 
					//setar temporariamente os parametros do proximo serviço pro usuario ver
					tsmTemp.setKm(ultimoServico.getKm() + tsm.get(i).getKm());
					tsmTemp.setDataProximoServico(dataProximoServico.getTime());
					tsmTemp.setSituacao(retorno);
					tsmResultado.add(i, tsmTemp);
				}
				else{
					// se não tem serviço efetuado, pega data do cadastro e adiciona tempo - 1 mes do proximo serviço a fazer
					dataProximoServicoPercent.setTime(veiculo.getDataCadastro());
					dataProximoServicoPercent.add(Calendar.MONTH, ((tsm.get(i).getTempo()-1))); 
					// se não tem serviço efetuado, pega data do cadastro e adiciona tempo do proximo serviço a fazer				
					dataProximoServico.setTime(veiculo.getDataCadastro());
					dataProximoServico.add(Calendar.MONTH, (tsm.get(i).getTempo()));  			

					if ( (veiculo.getOdometro() > (veiculo.getKmCadastro() + (tsm.get(i).getKm()*0.8)))  
							|| ( hoje.after(dataProximoServicoPercent) ) ) {					

						if ( ( (veiculo.getOdometro() > ( veiculo.getKmCadastro() + (tsm.get(i).getKm()*0.8)) 
								&& veiculo.getOdometro() < ( veiculo.getKmCadastro() + (tsm.get(i).getKm())) ) )
								|| (hoje.after(dataProximoServicoPercent) && hoje.before(dataProximoServico)) ){
							
							if( hoje.after(dataProximoServico) || (veiculo.getOdometro() > (veiculo.getKmCadastro() + (tsm.get(i).getKm())))){
								retorno = "vermelho";
							}
							else{
								retorno = "amarelo";
							}
						}				
						else{
							retorno = "vermelho";
						}
					}
					else{
						retorno = "verde";
					}

					tsmTemp.setKm(veiculo.getKmCadastro() + tsm.get(i).getKm());
					tsmTemp.setDataProximoServico(dataProximoServico.getTime());
					tsmTemp.setSituacao(retorno);
					tsmResultado.add(i, tsmTemp);			
				}								
			}
			if (veiculo.getSituacao() == "verde" && retorno == "amarelo"){
				veiculo.setSituacao("amarelo");
			}
			if (veiculo.getSituacao() == "verde" && retorno == "vermelho"){
				veiculo.setSituacao("vermelho");
			}
			if (veiculo.getSituacao() == "amarelo" && retorno == "verde"){
				veiculo.setSituacao("amarelo");
			}
			if (veiculo.getSituacao() == "amarelo" && retorno == "vermelho"){
				veiculo.setSituacao("vermelho");
			}				
			if (veiculo.getSituacao() == "vermelho" && retorno != "vermelho"){
				veiculo.setSituacao("vermelho");
			}		
			return tsmResultado;
		}
}
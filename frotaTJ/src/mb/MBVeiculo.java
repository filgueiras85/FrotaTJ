package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;


import dao.Abastecimento;
import dao.Modelo;
import dao.Servico;
import dao.ServicoDAO;
import dao.TipoServicoModelo;
import dao.TipoServicoModeloDAO;
import dao.TipoServicoVeiculo;
import dao.Unidade;
import dao.Veiculo;
import dao.VeiculoDAO;

public class MBVeiculo {
	private static MBVeiculo veiculoMB = new MBVeiculo();
	private MBTipoServicoVeiculo mbTipoServicoVeiculo = MBTipoServicoVeiculo.getInstance();
	private MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();
	private MBServico mbServico = MBServico.getInstance();
	private MBAbastecimento mbAbastecimento = MBAbastecimento.getInstance();

	private MBVeiculo(){

	}

	public static MBVeiculo getInstance(){
		return veiculoMB;
	}

	public String inserir(Veiculo veiculo) {

		String retorno = "Cadastro inserido.";
		VeiculoDAO daoveiculo = VeiculoDAO.getInstance();
		try {
			daoveiculo.save(veiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}

	public String editar(Veiculo veiculo) {
		String retorno = "Cadastro alterado.";
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		try {
			daoVeiculo.update(veiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}


	public String apagar(Veiculo veiculo) {
		String retorno = "Cadastro removido.";
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		try {
			daoVeiculo.delete(veiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public List<Veiculo> listarVeiculosPorUnidade(int idUnidade) throws ClassNotFoundException, SQLException{
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		return daoVeiculo.findByUnidade(MBUnidade.getInstance().retornarUnidade(idUnidade));
	}

	public Veiculo retornarVeiculo(int id) {
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		return daoVeiculo.findById(id);

	}

	public List<Veiculo> listarVeiculos() throws ClassNotFoundException, SQLException{
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		return daoVeiculo.findAll();
	}
	public List<Veiculo> ListarosVeiculodoModelo(Modelo modelo) {
		List<Veiculo> Lista1;
		try {
			Lista1 = listarVeiculos();

			List<Veiculo> lista = new ArrayList<>();

			for (int i = 0; i < Lista1.size(); i++) {
				System.out.println(Lista1.get(i).getModelo().getIdmodelo().equals(modelo.getIdmodelo()));
				if(Lista1.get(i).getModelo().getIdmodelo().equals(modelo.getIdmodelo())){
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
	
	public List<Veiculo> VeiculoPorModelo(List<Modelo> listaModelo) throws ClassNotFoundException, SQLException{
		List<Veiculo> veiculo = new ArrayList<Veiculo>();
		List<Veiculo> listaVeiculo = listarVeiculos();
		for(int i=0;i<listaModelo.size();i++){
			for(int j=0;j<listaVeiculo.size();j++){
				if(listaModelo.get(i).getIdmodelo() == listaVeiculo.get(j).getModelo().getIdmodelo()){
					Veiculo v = retornarVeiculo(listaVeiculo.get(j).getIdveiculo());
					if(!veiculo.contains(v)){
						veiculo.add(v);
					}
				}
			}
		}
		return veiculo;
	}
	
	public List<Veiculo> VeiculosUnidade(Unidade unidade) throws ClassNotFoundException, SQLException{
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		List<Veiculo> veiculo = daoVeiculo.VeiculoUnidade(unidade);
		return veiculo;
	}
	
	public List<Veiculo> VeiculoPorServico(List<Servico> listaServico) throws ClassNotFoundException, SQLException{
		
		List<Veiculo> veiculo = new ArrayList<Veiculo>();
		for(int i=0;i<listaServico.size();i++){
			Veiculo v = retornarVeiculo(listaServico.get(i).getVeiculo().getIdveiculo());
			if(!veiculo.contains(v)){
				veiculo.add(v);
			}
		}
		return veiculo;
	}
	
	public List<Veiculo> VeiculoPorPlaca(Object placa) throws ClassNotFoundException, SQLException{
		List<Veiculo> listaVeiculo = listarVeiculos();
		List<Veiculo> veiculo = new ArrayList<Veiculo>();
		for(int i=0;i<listaVeiculo.size();i++){
			if(listaVeiculo.get(i).getPlaca().equals(placa)){
				veiculo.add(listaVeiculo.get(i));
			}
		}
		return veiculo;
	}
	
	
	public void AtualizarSituacaoAbastecimento(Abastecimento a, int odometrodesatualizado){
		Veiculo v = retornarVeiculo(a.getVeiculo().getIdveiculo());
		List<TipoServicoModelo> listaTipoServico = mbTipoServiçoModelo.ListarosTipoServicodoModelo(v.getModelo().getIdmodelo());
		String ok = "VERDE";
		String situacao="";
		String aux = "";
		List<TipoServicoVeiculo> lista = new ArrayList<>();
		lista = mbTipoServicoVeiculo.ListarosTipoServicoVeiculo(v.getIdveiculo());
		System.out.println(lista.size());
		for(int i = 0; i<lista.size();i++){
			if(listaTipoServico.get(i).getKm()<v.getOdometro()){
				List<Servico> listaServico = mbServico.ListarosServicodoVeiculo(v.getIdveiculo(), lista.get(i).getTipoServico());
				System.out.println(lista.size()+"lista");
				System.out.println(v.getOdometro()+listaTipoServico.get(i).getKm()+"exemplo");
				if (listaServico.isEmpty()){

					List<Abastecimento> listaAbastecimento = new ArrayList<>();
					listaAbastecimento = mbAbastecimento.ListarosAbastecimentodoVeiculo(a.getVeiculo());
					System.out.println("783482853459854-----------"+listaAbastecimento.size());
					if(listaAbastecimento.size()>0){
						System.out.println(listaAbastecimento.get(0).getKmOdometro()+"OOOOII");
						Date datadoultimoabastecimento = new Date(a.getData2().getTime());
						Date datadoPrimeiroAbastecimento = new Date(listaAbastecimento.get(0).getData2().getTime());
						int data = getMonthsDifference(datadoPrimeiroAbastecimento, datadoultimoabastecimento);

						System.out.println("-----------Aquiiiii----------------"+data);
						if(v.getOdometro()<listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
							if(v.getOdometro()+200>=listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
								lista.get(i).setSituacao("AMARELO");
								System.out.println("atwrero2");

							}else{
								lista.get(i).setSituacao("VERDE");
								System.out.println("e23232");

							}

						}else{
							if(v.getOdometro()>listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
								lista.get(i).setSituacao("VERMELHO");
								System.out.println("atw22122o2"+(listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()));

							}
						}

						if(lista.get(i).getSituacao().equalsIgnoreCase("VERDE")){
							System.out.println("----------------ENTROU"+(listaTipoServico.get(i).getTempo()<data));

							System.out.println("Abastecimento-----------------------------|"+data);
							System.out.println("tempo"+listaTipoServico.get(i).getTempo());
							if(listaTipoServico.get(i).getTempo()>data){

								if(listaTipoServico.get(i).getTempo()<=data+1){
									lista.get(i).setSituacao("AMARELO");
									System.out.println("atrasadotemopo3");

									situacao = "null";

								}


							}else{
								if(listaTipoServico.get(i).getTempo()<data){
									lista.get(i).setSituacao("VERMELHO");
									System.out.println("atrasadotemoi1");
								}else{
									if(listaTipoServico.get(i).getTempo()==data){
										lista.get(i).setSituacao("AMARELO");
										System.out.println("atrasadotempo2");

									}
								}
							}

						}		
					}else{
						if(v.getOdometro()<odometrodesatualizado+listaTipoServico.get(i).getKm()){
							if(v.getOdometro()+200>odometrodesatualizado+listaTipoServico.get(i).getKm()){
								lista.get(i).setSituacao("AMARELO");
								System.out.println("lol");

							}else{
								lista.get(i).setSituacao("VERDE");
								System.out.println("at232323do2");


							}

						}else{
							if(v.getOdometro()>odometrodesatualizado+listaTipoServico.get(i).getKm()){
								lista.get(i).setSituacao("VERMELHO");
								System.out.println("1111");

							}else{
								lista.get(i).setSituacao("AMARELO");
								System.out.println("atrasa23232");


							}
						}
					}




					System.out.println("lol44");

				}else{
					if(v.getOdometro()<listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){

						if((v.getOdometro()+200)>listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("AMARELO");
							System.out.println("atrasado3");

							situacao = "null";

						}else{
							lista.get(i).setSituacao("VERDE");
							System.out.println("bom1");
						}


					}else{
						if(v.getOdometro()>listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("VERMELHO");
							System.out.println("atrasado1");
						}else{
							if(v.getOdometro()==listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
								lista.get(i).setSituacao("AMARELO");
								System.out.println("atrasado2");

							}
						}

					}
					if(lista.get(i).getSituacao().contains("VERDE")){
						Date datadoultimoservico = new Date(listaServico.get((listaServico.size()-1)).getData2().getTime());
						Date datadoAbastecimento = new Date(a.getData2().getTime());
						int data = getMonthsDifference(datadoultimoservico,datadoAbastecimento);
						System.out.println("Servico-----------------------------"+data);

						if(listaTipoServico.get(i).getTempo()>data){

							if(listaTipoServico.get(i).getTempo()<data+1||listaTipoServico.get(i).getTempo()==data+1){
								lista.get(i).setSituacao("AMARELO");
								System.out.println("atrasadotemopo3");

								situacao = "null";

							}


						}else{
							if(listaTipoServico.get(i).getTempo()<data){
								lista.get(i).setSituacao("VERMELHO");
								System.out.println("atrasadotemoi1");
							}else{
								if(listaTipoServico.get(i).getTempo()==data){
									lista.get(i).setSituacao("AMARELO");
									System.out.println("atrasadotempo2");

								}
							}
						}

					}
				}


			}else{if(listaTipoServico.get(i).getKm()<(v.getOdometro()+200)){
				lista.get(i).setSituacao("AMARELO");
				situacao = "null";

			}
			else{
				List<Abastecimento> listaAbastecimento = new ArrayList<>();
				listaAbastecimento.addAll(v.getAbastecimentos());
				if(!listaAbastecimento.isEmpty()){
					if(v.getOdometro()<listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
						if(v.getOdometro()+200>listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("AMARELO");
							System.out.println("atwrero2");

						}else{
							lista.get(i).setSituacao("VERDE");
							System.out.println("e23232");

						}

					}else{
						if(v.getOdometro()>listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("VERMELHO");
							System.out.println("atw22122o2"+(listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()));

						}else{
							lista.get(i).setSituacao("AMARELO");
							System.out.println("a22222");


						}
					}
				}else{
					if(v.getOdometro()<odometrodesatualizado+listaTipoServico.get(i).getKm()){
						if(v.getOdometro()+200>odometrodesatualizado+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("AMARELO");
							System.out.println("lol");

						}else{
							lista.get(i).setSituacao("VERDE");
							System.out.println("at232323do2");


						}

					}else{
						if(v.getOdometro()>odometrodesatualizado+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("VERMELHO");
							System.out.println("1111");

						}else{
							lista.get(i).setSituacao("AMARELO");
							System.out.println("atrasa23232");


						}
					}
				}

			}
			}
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		for(int i = 0; i<lista.size();i++){
			situacao = situacao+lista.get(i).getSituacao();
			aux = aux+ok;	
			System.out.println(situacao+"si");
			System.out.println(aux+"au");

		}
		System.out.println(situacao.contains("null")+"hh");
		if(situacao.equalsIgnoreCase(aux)){
			v.setSituacao("VERDE");
			editar(v);

		}else{
			if(situacao.contains("AMARELO")){
				if(situacao.contains("VERMELHO")){
					v.setSituacao("VERMELHO");
					editar(v);
				}
				else{
					v.setSituacao("AMARELO");
					editar(v);

				}
			}
			else{
				v.setSituacao("VERMELHO");
				editar(v);
			}

		}
		System.out.println("olamundo");

	}
	public void AtualizarSituacaoServico(Servico s, int odometrodesatualizado){
		Veiculo v = retornarVeiculo(s.getVeiculo().getIdveiculo());
		List<TipoServicoModelo> listaTipoServico = mbTipoServiçoModelo.ListarosTipoServicodoModelo(v.getModelo().getIdmodelo());
		String ok = "VERDE";
		String situacao="";
		String aux = "";
		List<TipoServicoVeiculo> lista = new ArrayList<>();
		lista = mbTipoServicoVeiculo.ListarosTipoServicoVeiculo(v.getIdveiculo());
		System.out.println(lista.size());
		for(int i = 0; i<lista.size();i++){
			List<Servico> listaServico = mbServico.ListarosServicodoVeiculo(v.getIdveiculo(), lista.get(i).getTipoServico());
			System.out.println(lista.size()+"lista");
			System.out.println(v.getOdometro()+listaTipoServico.get(i).getKm()+"exemplo");
			if(listaServico.size()>0){
				Date datadoultimoservico = new Date(listaServico.get((listaServico.size()-1)).getData2().getTime());
				Date datadoservico = new Date(s.getData2().getTime());
				int data = getMonthsDifference(datadoultimoservico, datadoservico);
				if(v.getOdometro()<listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){

					if((v.getOdometro()+200)>listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
						lista.get(i).setSituacao("AMARELO");
						System.out.println("atrasado3");

						situacao = "null";

					}else{
						lista.get(i).setSituacao("VERDE");
						System.out.println("bom2");
					}


				}else{
					if(v.getOdometro()>listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
						lista.get(i).setSituacao("VERMELHO");
						System.out.println("atrasado1");
					}else{
						if(v.getOdometro()==listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("AMARELO");
							System.out.println("atrasado2");

						}}}
				if(lista.get(i).getSituacao().equals("VERDE")){
					if(listaTipoServico.get(i).getTempo()>data){

						if(listaTipoServico.get(i).getTempo()<data+1||listaTipoServico.get(i).getTempo()==data+1){
							lista.get(i).setSituacao("AMARELO");
							System.out.println("atrasadotemopo3");

							situacao = "null";

						}


					}else{
						if(listaTipoServico.get(i).getTempo()<data){
							lista.get(i).setSituacao("VERMELHO");
							System.out.println("atrasadotemoi1");
						}else{
							if(listaTipoServico.get(i).getTempo()==data){
								lista.get(i).setSituacao("AMARELO");
								System.out.println("atrasadotempo2");

							}
						}
					}

				}
			}else{
				List<Abastecimento> listaAbastecimento = new ArrayList<>();
				listaAbastecimento.addAll(v.getAbastecimentos());
				if(listaAbastecimento.size()>0){

					System.out.println(listaAbastecimento.get(0).getKmOdometro()+"OOOOII");
					if(v.getOdometro()<listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
						if(v.getOdometro()+200>listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("AMARELO");
							System.out.println("atwrero2");

						}else{
							lista.get(i).setSituacao("VERDE");
							System.out.println("e23232");

						}

					}else{
						if(v.getOdometro()>listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("VERMELHO");
							System.out.println("atw22122o2"+(listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()));

						}else
							lista.get(i).setSituacao("AMARELO");
						System.out.println("a22222");

					} if(lista.get(i).getSituacao().equals("VERDE")){
						Date datadoprimeiro = new Date(listaAbastecimento.get(0).getData2().getTime());
						Date datadoservico = new Date(s.getData2().getTime());
						int data = getMonthsDifference(datadoprimeiro, datadoservico);
						if(listaTipoServico.get(i).getTempo()>data){

							if(listaTipoServico.get(i).getTempo()<data+1||listaTipoServico.get(i).getTempo()==data+1){
								lista.get(i).setSituacao("AMARELO");
								System.out.println("atrasadotemopo3");

								situacao = "null";

							}


						}else{
							if(listaTipoServico.get(i).getTempo()<data){
								lista.get(i).setSituacao("VERMELHO");
								System.out.println("atrasadotemoi1");
							}else{
								if(listaTipoServico.get(i).getTempo()==data){
									lista.get(i).setSituacao("AMARELO");
									System.out.println("atrasadotempo2");

								}
							}
						}

					}
				}
				else{
					if(v.getOdometro()<odometrodesatualizado+listaTipoServico.get(i).getKm()){
						if(v.getOdometro()+200>odometrodesatualizado+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("AMARELO");
							System.out.println("lol");

						}else{
							lista.get(i).setSituacao("VERDE");
							System.out.println("at232323do2");


						}

					}else{
						if(v.getOdometro()>odometrodesatualizado+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("VERMELHO");
							System.out.println("1111");

						}else{
							lista.get(i).setSituacao("AMARELO");
							System.out.println("atrasa23232");


						}
					}
				}



			}

		}
		for(int i = 0; i<lista.size();i++){
			situacao = situacao+lista.get(i).getSituacao();
			aux = aux+ok;	
			System.out.println(situacao+"si");
			System.out.println(aux+"au");

		}
		System.out.println(situacao.contains("null")+"hh");
		if(situacao.equalsIgnoreCase(aux)){
			v.setSituacao("VERDE");
			editar(v);

		}else{
			if(situacao.contains("null")){
				if(situacao.contains("VERMELHO")){
					v.setSituacao("VERMELHO");
					editar(v);
				}
				else{
					v.setSituacao("AMARELO");
					System.out.print(editar(v));

				}
			}
			else{
				v.setSituacao("VERMELHO");
				editar(v);
			}

		}
		System.out.println(v.getAbastecimentos());
		System.out.println("olamundo");
	}
	public int AtualizarOdometro(int aux, int v){
		Veiculo v2 = retornarVeiculo(v);
		int odometro = v2.getOdometro();
		if (odometro<aux){
			v2.setOdometro(aux);
			editar(v2);
		}
		
		System.out.println("entrou");
		return odometro;

	}
public List<Veiculo> statusTodosVeiculos () throws ClassNotFoundException, SQLException{
		
		List<Veiculo> veiculo = listarVeiculos();
		List<TipoServicoModelo> tiposServicosModeloVeiculo = null;
		String retorno = "";
		TipoServicoModeloDAO tiposervicomodeloDAO = TipoServicoModeloDAO.getInstance();
		ServicoDAO servicoDAO = ServicoDAO.getInstance();
		Calendar hoje = Calendar.getInstance();

		for (int i1=0;i1<veiculo.size();i1++){
			
			tiposServicosModeloVeiculo = (List<TipoServicoModelo>) mbTipoServiçoModelo.ListarosTipoServicodoModelo(veiculo.get(i1).getModelo().getIdmodelo());
			
			for (int i=0;i<tiposServicosModeloVeiculo.size();i++){
				// para cada tipo de servico busca o ultimo servico feito
				// terminar o teste para cada servico quando != null

				Servico ultimoServico = servicoDAO.findUltimoServico(veiculo.get(i1), tiposServicosModeloVeiculo.get(i).getTipoServico());

				if ( ultimoServico != null ){
					Calendar dataUltimoServicoPercent = Calendar.getInstance();  
					dataUltimoServicoPercent.setTime(ultimoServico.getData2());
					dataUltimoServicoPercent.add(Calendar.MONTH, ((tiposServicosModeloVeiculo.get(i).getTempo()-1)));  // adicionar 5 meses  

					Calendar dataUltimoServico = Calendar.getInstance();   
					dataUltimoServico.setTime(ultimoServico.getData2());
					dataUltimoServico.add(Calendar.MONTH, (tiposServicosModeloVeiculo.get(i).getTempo()));  // adicionar 6 meses

					if ( (veiculo.get(i1).getOdometro() > (ultimoServico.getKm() + (tiposServicosModeloVeiculo.get(i).getKm()*0.8)))  
							|| ( hoje.after(dataUltimoServicoPercent) ) ) {					
						if ( ( (veiculo.get(i1).getOdometro() > ( ultimoServico.getKm() + (tiposServicosModeloVeiculo.get(i).getKm()*0.8)) 
								&& veiculo.get(i1).getOdometro() < ( ultimoServico.getKm() + (tiposServicosModeloVeiculo.get(i).getKm())) ) ) 
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
				}
				else{

					Calendar dataCadastroPercent = Calendar.getInstance();  
					dataCadastroPercent.setTime(veiculo.get(i1).getDataCadastro());
					dataCadastroPercent.add(Calendar.MONTH, ((tiposServicosModeloVeiculo.get(i).getTempo()-1)));  // adicionar n-1 meses  

					Calendar dataCadastro = Calendar.getInstance();   
					dataCadastro.setTime(veiculo.get(i1).getDataCadastro());
					dataCadastro.add(Calendar.MONTH, (tiposServicosModeloVeiculo.get(i).getTempo()));  // adicionar n meses

					if ( (veiculo.get(i1).getOdometro() > (veiculo.get(i1).getKmCadastro() + (tiposServicosModeloVeiculo.get(i).getKm()*0.8)))  
							|| ( hoje.after(dataCadastroPercent) ) ) {					
						if ( ( (veiculo.get(i1).getOdometro() < ( veiculo.get(i1).getKmCadastro() + (tiposServicosModeloVeiculo.get(i).getKm())) 
								&& veiculo.get(i1).getOdometro() < ( veiculo.get(i1).getKmCadastro() + (tiposServicosModeloVeiculo.get(i).getKm())) ) ) 
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
				}
				
							
			}
			veiculo.get(i1).setSituacao(retorno);
			editar(veiculo.get(i1));								
		}
		return veiculo;
	}
	public static final int getMonthsDifference(Date date1, Date date2) {
		int m1 = date1.getYear() * 12 + date1.getMonth();
		int m2 = date2.getYear() * 12 + date2.getMonth();
		return m2 - m1 + 1;
	}
}
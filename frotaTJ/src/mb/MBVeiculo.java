package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.Abastecimento;
import dao.Modelo;
import dao.Servico;
import dao.TipoServicoModelo;
import dao.TipoServicoVeiculo;
import dao.Veiculo;
import dao.VeiculoDAO;;

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
		
		String retorno = "ok";
		VeiculoDAO daoveiculo = VeiculoDAO.getInstance();
		try {
			daoveiculo.save(veiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Veiculo veiculo) {
		String retorno = "ok";
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		try {
			daoVeiculo.update(veiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Veiculo veiculo) {
		String retorno = "ok";
		VeiculoDAO daoVeiculo = VeiculoDAO.getInstance();
		try {
			daoVeiculo.delete(veiculo);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
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
	public void AtualizarSituacaoAbastecimento(Abastecimento a, int odometrodesatualizado){
				Veiculo v = retornarVeiculo(a.getVeiculo().getIdveiculo());
		List<TipoServicoModelo> listaTipoServico = mbTipoServiçoModelo.ListarosTipoServicodoModelo(v.getModelo().getIdmodelo());
		String ok = "OK";
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
						if(v.getOdometro()+200>listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("A Fazer");
							System.out.println("atwrero2");

						}else{
							lista.get(i).setSituacao("OK");
							System.out.println("e23232");

						}

					}else{
						if(v.getOdometro()>listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("Atrasado");
							System.out.println("atw22122o2"+(listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()));

						}else{
							lista.get(i).setSituacao("A Fazer");
							System.out.println("a22222");


						}
					}
						
						if(lista.get(i).getSituacao().equalsIgnoreCase("OK")){
							System.out.println("----------------ENTROU"+(listaTipoServico.get(i).getTempo()<data));
							
							System.out.println("Abastecimento-----------------------------|"+data);
							System.out.println("tempo"+listaTipoServico.get(i).getTempo());
							if(listaTipoServico.get(i).getTempo()>data){
								
								if(listaTipoServico.get(i).getTempo()<data+1||listaTipoServico.get(i).getTempo()==data+1){
									lista.get(i).setSituacao("A Fazer");
									System.out.println("atrasadotemopo3");
									
									situacao = "null";

								}
								

							}else{
								if(listaTipoServico.get(i).getTempo()<data){
									lista.get(i).setSituacao("Atrasado");
									System.out.println("atrasadotemoi1");
								}else{
									if(listaTipoServico.get(i).getTempo()==data){
										lista.get(i).setSituacao("A Fazer");
										System.out.println("atrasadotempo2");

									}
						}
					}
					
				}		
				}else{
					if(v.getOdometro()<odometrodesatualizado+listaTipoServico.get(i).getKm()){
						if(v.getOdometro()+200>odometrodesatualizado+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("A Fazer");
							System.out.println("lol");

						}else{
							lista.get(i).setSituacao("OK");
							System.out.println("at232323do2");


						}

					}else{
						if(v.getOdometro()>odometrodesatualizado+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("Atrasado");
							System.out.println("1111");

						}else{
							lista.get(i).setSituacao("A Fazer");
							System.out.println("atrasa23232");


						}
					}
				}
				
				

			
				System.out.println("lol44");
				
			}else{
			if(v.getOdometro()<listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
				
				if((v.getOdometro()+200)>listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
					lista.get(i).setSituacao("A Fazer");
					System.out.println("atrasado3");
					
					situacao = "null";

				}else{
					lista.get(i).setSituacao("OK");
					System.out.println("bom1");
				}
				

			}else{
				if(v.getOdometro()>listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
					lista.get(i).setSituacao("Atrasado");
					System.out.println("atrasado1");
				}else{
					if(v.getOdometro()==listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
						lista.get(i).setSituacao("A Fazer");
						System.out.println("atrasado2");

					}
				}
				
			}
			if(lista.get(i).getSituacao().contains("OK")){
				Date datadoultimoservico = new Date(listaServico.get((listaServico.size()-1)).getData2().getTime());
				Date datadoAbastecimento = new Date(a.getData2().getTime());
				int data = getMonthsDifference(datadoultimoservico,datadoAbastecimento);
				System.out.println("Servico-----------------------------"+data);

				if(listaTipoServico.get(i).getTempo()>data){
					
					if(listaTipoServico.get(i).getTempo()<data+1||listaTipoServico.get(i).getTempo()==data+1){
						lista.get(i).setSituacao("A Fazer");
						System.out.println("atrasadotemopo3");
						
						situacao = "null";

					}
					

				}else{
					if(listaTipoServico.get(i).getTempo()<data){
						lista.get(i).setSituacao("Atrasado");
						System.out.println("atrasadotemoi1");
					}else{
						if(listaTipoServico.get(i).getTempo()==data){
							lista.get(i).setSituacao("A Fazer");
							System.out.println("atrasadotempo2");

						}
			}
		}
		
	}
			}
			
			
			}else{if(listaTipoServico.get(i).getKm()<(v.getOdometro()+200)){
				lista.get(i).setSituacao("A Fazer");
				situacao = "null";

			}
			else{
				List<Abastecimento> listaAbastecimento = new ArrayList<>();
				listaAbastecimento.addAll(v.getAbastecimentos());
				if(!listaAbastecimento.isEmpty()){
					if(v.getOdometro()<listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
						if(v.getOdometro()+200>listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("A Fazer");
							System.out.println("atwrero2");

						}else{
							lista.get(i).setSituacao("OK");
							System.out.println("e23232");

						}

					}else{
						if(v.getOdometro()>listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("Atrasado");
							System.out.println("atw22122o2"+(listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()));

						}else{
							lista.get(i).setSituacao("A Fazer");
							System.out.println("a22222");


						}
					}
				}else{
					if(v.getOdometro()<odometrodesatualizado+listaTipoServico.get(i).getKm()){
						if(v.getOdometro()+200>odometrodesatualizado+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("A Fazer");
							System.out.println("lol");

						}else{
							lista.get(i).setSituacao("OK");
							System.out.println("at232323do2");


						}

					}else{
						if(v.getOdometro()>odometrodesatualizado+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("Atrasado");
							System.out.println("1111");

						}else{
							lista.get(i).setSituacao("A Fazer");
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
			v.setSituacao("OK");
			editar(v);
			
		}else{
			if(situacao.contains("A Fazer")){
				if(situacao.contains("Atrasado")){
					v.setSituacao("Atrasado");
					editar(v);
				}
				else{
					v.setSituacao("A Fazer");
					editar(v);

				}
			}
			else{
				v.setSituacao("Atrasado");
				editar(v);
			}
		
	}
		System.out.println("olamundo");

	}
	public void AtualizarSituacaoServico(Servico s, int odometrodesatualizado){
		Veiculo v = retornarVeiculo(s.getVeiculo().getIdveiculo());
		List<TipoServicoModelo> listaTipoServico = mbTipoServiçoModelo.ListarosTipoServicodoModelo(v.getModelo().getIdmodelo());
		String ok = "OK";
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
					lista.get(i).setSituacao("A Fazer");
					System.out.println("atrasado3");
					
					situacao = "null";

				}else{
					lista.get(i).setSituacao("OK");
					System.out.println("bom2");
				}
				

			}else{
				if(v.getOdometro()>listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
					lista.get(i).setSituacao("Atrasado");
					System.out.println("atrasado1");
				}else{
					if(v.getOdometro()==listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
						lista.get(i).setSituacao("A Fazer");
						System.out.println("atrasado2");

					}}}
			if(lista.get(i).getSituacao().equals("OK")){
				if(listaTipoServico.get(i).getTempo()>data){
					
					if(listaTipoServico.get(i).getTempo()<data+1||listaTipoServico.get(i).getTempo()==data+1){
						lista.get(i).setSituacao("A Fazer");
						System.out.println("atrasadotemopo3");
						
						situacao = "null";

					}
					

				}else{
					if(listaTipoServico.get(i).getTempo()<data){
						lista.get(i).setSituacao("Atrasado");
						System.out.println("atrasadotemoi1");
					}else{
						if(listaTipoServico.get(i).getTempo()==data){
							lista.get(i).setSituacao("A Fazer");
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
							lista.get(i).setSituacao("A Fazer");
							System.out.println("atwrero2");

						}else{
							lista.get(i).setSituacao("OK");
							System.out.println("e23232");

						}

					}else{
						if(v.getOdometro()>listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("Atrasado");
							System.out.println("atw22122o2"+(listaAbastecimento.get(0).getKmOdometro()+listaTipoServico.get(i).getKm()));

						}else
							lista.get(i).setSituacao("A Fazer");
							System.out.println("a22222");
						
					} if(lista.get(i).getSituacao().equals("OK")){
						Date datadoprimeiro = new Date(listaAbastecimento.get(0).getData2().getTime());
						Date datadoservico = new Date(s.getData2().getTime());
						int data = getMonthsDifference(datadoprimeiro, datadoservico);
						if(listaTipoServico.get(i).getTempo()>data){
							
							if(listaTipoServico.get(i).getTempo()<data+1||listaTipoServico.get(i).getTempo()==data+1){
								lista.get(i).setSituacao("A Fazer");
								System.out.println("atrasadotemopo3");
								
								situacao = "null";

							}
							

						}else{
							if(listaTipoServico.get(i).getTempo()<data){
								lista.get(i).setSituacao("Atrasado");
								System.out.println("atrasadotemoi1");
							}else{
								if(listaTipoServico.get(i).getTempo()==data){
									lista.get(i).setSituacao("A Fazer");
									System.out.println("atrasadotempo2");

								}
					}
				}
				
			}
			}
			else{
					if(v.getOdometro()<odometrodesatualizado+listaTipoServico.get(i).getKm()){
						if(v.getOdometro()+200>odometrodesatualizado+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("A Fazer");
							System.out.println("lol");

						}else{
							lista.get(i).setSituacao("OK");
							System.out.println("at232323do2");


						}

					}else{
						if(v.getOdometro()>odometrodesatualizado+listaTipoServico.get(i).getKm()){
							lista.get(i).setSituacao("Atrasado");
							System.out.println("1111");

						}else{
							lista.get(i).setSituacao("A Fazer");
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
			v.setSituacao("OK");
			editar(v);
			
		}else{
			if(situacao.contains("null")){
				if(situacao.contains("Atrasado")){
					v.setSituacao("Atrasado");
					editar(v);
				}
				else{
					v.setSituacao("A Fazer");
					System.out.print(editar(v));

				}
			}
			else{
				v.setSituacao("Atrasado");
				editar(v);
			}
		
	}
		System.out.println(v.getAbastecimentos());
		System.out.println("olamundo");
	}
public int AtualizarOdometro(int aux, Veiculo v){
		
		int odometro = v.getOdometro();
		
			v.setOdometro(aux);
			 editar(v);
			 return odometro;
				
		}
public static final int getMonthsDifference(Date date1, Date date2) {
    int m1 = date1.getYear() * 12 + date1.getMonth();
    int m2 = date2.getYear() * 12 + date2.getMonth();
    return m2 - m1 + 1;
}
}
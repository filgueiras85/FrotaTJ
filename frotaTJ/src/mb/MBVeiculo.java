package mb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	public void AtualizarSituacaoAbastecimento(Veiculo v, int odometrodesatualizado){
				
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
				lista.get(i).setSituacao("A Fazer");
				situacao = "null";
			}else{
			if(v.getOdometro()<listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
				
				if((v.getOdometro()+200)>listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
					lista.get(i).setSituacao("A Fazer");
					System.out.println("atrasado3");
					
					situacao = "null";

				}else{
					lista.get(i).setSituacao("OK");
					System.out.println("bom");
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
			
			}}else{if(listaTipoServico.get(i).getKm()<(v.getOdometro()+200)){
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
		
	}
	public void AtualizarSituacaoServico(Veiculo v, int odometrodesatualizado){
		
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
			if(v.getOdometro()<listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
				
				if((v.getOdometro()+200)>listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
					lista.get(i).setSituacao("A Fazer");
					System.out.println("atrasado3");
					
					situacao = "null";

				}else{
					lista.get(i).setSituacao("OK");
					System.out.println("bom");
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
			}else{
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
	}
public int AtualizarOdometro(int aux, Veiculo v){
		
		int odometro = v.getOdometro();
		
			v.setOdometro(aux);
			 editar(v);
			 return odometro;
				
		}
}
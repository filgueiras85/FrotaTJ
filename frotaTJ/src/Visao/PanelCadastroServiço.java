package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;

import org.hibernate.ejb.criteria.expression.function.CurrentDateFunction;

import com.sun.jmx.snmp.Timestamp;

import mb.MBFornecedor;
import mb.MBMotorista;
import mb.MBServico;
import mb.MBTipoServico;
import mb.MBTipoServicoVeiculo;
import mb.MBTipoServiçoModelo;
import mb.MBVeiculo;

import dao.Fornecedor;
import dao.Modelo;
import dao.Motorista;
import dao.Servico;
import dao.TipoServico;
import dao.TipoServicoModelo;
import dao.TipoServicoModeloId;
import dao.TipoServicoVeiculo;
import dao.Veiculo;
import javax.swing.ImageIcon;


public class PanelCadastroServiço extends PanelExemplo {
	private JTextField textFieldData;
	private JTextField textFieldValor;
	private JTextField textFieldOrçamento;
	private JTextField textFieldCupomFiscal;
	private JTextField textFieldDescrição;
	private JTextField textFieldKm;
	private JComboBox<Motorista> comboBoxMotorista;
	private JComboBox<Fornecedor> comboBoxFornecedor;
	private JComboBox<Veiculo> comboBoxVeiculo;
	private	JComboBox<TipoServico> comboBoxTipoServico;
	private JComboBox<TipoServico> comboBoxTipoServico_1;
	private Veiculo  veiculoselecionado;

	/**
	 * Create the panel.
	 */
	
	public PanelCadastroServiço( final int idServicoSelecionado) {
		
		JLabel lblCadastroServio = new JLabel("Cadastro Servi\u00E7o");
		lblCadastroServio.setIcon(new ImageIcon("imagens\\ico-recursos-integra.png"));
		lblCadastroServio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		
		textFieldData = new JTextField();
		textFieldData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldData.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblOramento = new JLabel("Or\u00E7amento");
		lblOramento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOramento.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblCupomFiscal = new JLabel("Cupom Fiscal");
		lblCupomFiscal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCupomFiscal.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescrio.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblKm = new JLabel("Km");
		lblKm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblMotorista = new JLabel("Motorista");
		lblMotorista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblVeiculo = new JLabel("Veiculo");
		lblVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textFieldValor = new JTextField();
		textFieldValor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldValor.setColumns(10);
		
		textFieldOrçamento = new JTextField();
		textFieldOrçamento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldOrçamento.setColumns(10);
		
		textFieldCupomFiscal = new JTextField();
		textFieldCupomFiscal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldCupomFiscal.setColumns(10);
		
		textFieldDescrição = new JTextField();
		textFieldDescrição.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldDescrição.setColumns(10);
		
		MBMotorista mbMotorista= MBMotorista.getInstance();
		comboBoxMotorista = new JComboBox<Motorista>();
		DefaultComboBoxModel<Motorista> modeloComboBoxMotorista;
		try {
			modeloComboBoxMotorista = new DefaultComboBoxModel<Motorista>(new Vector(mbMotorista.listarMotoristas()));
			comboBoxMotorista.setModel(modeloComboBoxMotorista);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		comboBoxMotorista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MBFornecedor mbFornecedor= MBFornecedor.getInstance();
		comboBoxFornecedor = new JComboBox<Fornecedor>();
		DefaultComboBoxModel<Fornecedor> modeloComboBoxFornecedor;
		try {
			modeloComboBoxFornecedor = new DefaultComboBoxModel<Fornecedor>(new Vector(mbFornecedor.listarFornecedores()));
			comboBoxFornecedor.setModel(modeloComboBoxFornecedor);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		comboBoxFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		MBVeiculo mbVeiculo= MBVeiculo.getInstance();
		comboBoxVeiculo = new JComboBox<Veiculo>();
		
		DefaultComboBoxModel<Veiculo> modeloComboBoxVeiculo;
		try {
			modeloComboBoxVeiculo = new DefaultComboBoxModel<Veiculo>(new Vector(mbVeiculo.listarVeiculos()));
			comboBoxVeiculo.setModel(modeloComboBoxVeiculo);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		comboBoxVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				veiculoselecionado = (Veiculo) comboBoxVeiculo.getSelectedItem();
				comboBoxTipoServico.removeAll();
				ComboboxTipoServico(veiculoselecionado);
			}
		});
		comboBoxVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MBTipoServico mbTipoServico= MBTipoServico.getInstance();

		comboBoxTipoServico = new JComboBox<TipoServico>();
		
				MBTipoServico mbTipoServico1= MBTipoServico.getInstance();
				comboBoxTipoServico_1 = new JComboBox<TipoServico>();
				DefaultComboBoxModel<TipoServico> modeloComboBoxTipoServico;
				try {
					modeloComboBoxTipoServico = new DefaultComboBoxModel<TipoServico>(new Vector(mbTipoServico1.listarTipoServicos()));
					comboBoxTipoServico_1.setModel(modeloComboBoxTipoServico);
				} catch (Exception e) {
					// TODO: handle exception
				}		

				comboBoxTipoServico_1.setFont(new Font("Tahoma", Font.PLAIN, 15));

		
		textFieldKm = new JTextField();
		textFieldKm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldKm.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemServiço();
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("imagens\\7484_16x16.png"));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MBTipoServico mbTipoServico = MBTipoServico.getInstance();
				MBMotorista mbMotorista = MBMotorista.getInstance();
				MBVeiculo  mbVeiculo= MBVeiculo.getInstance();
				MBFornecedor mbFornecedor= MBFornecedor.getInstance();
				MBServico mbServico = MBServico.getInstance();

				java.sql.Timestamp data = new java.sql.Timestamp(transformaData(textFieldData.getText()+" 00:00:01").getTime());
				Servico s =  new Servico(new Integer(idServicoSelecionado), 
						mbMotorista.retornarMotorista(comboBoxMotorista.getItemAt(comboBoxMotorista.getSelectedIndex()).getIdmotorista()),
						mbVeiculo.retornarVeiculo(comboBoxVeiculo.getItemAt(comboBoxVeiculo.getSelectedIndex()).getIdveiculo()),
						mbFornecedor.retornarFornecedor((comboBoxFornecedor.getItemAt(comboBoxFornecedor.getSelectedIndex()).getIdfornecedor())),
						mbTipoServico.retornarTipoServico(comboBoxTipoServico_1.getItemAt(comboBoxTipoServico_1.getSelectedIndex()).getIdtipoServico()),
						data, Double.parseDouble(textFieldValor.getText()), textFieldOrçamento.getText(), 
						Integer.parseInt(textFieldCupomFiscal.getText()), textFieldDescrição.getText(), Integer.parseInt(textFieldKm.getText()));
					
					try {
						if (idServicoSelecionado==0){
							if (s.getIdServico()==0){
								s.setIdServico(null);
							}
							String retorno = mbServico.inserir(s);
							if (retorno.equals("ok")){
								if(AtualizarOdometro()){
									AtualizarSituacao(s.getVeiculo());
								}
								JOptionPane.showMessageDialog(null,"Serviço inserido!");
								PanelListagemServiço();
							}else{
								JOptionPane.showMessageDialog(null,retorno);
							}
						}else{
							
							String retorno =  mbServico.editar(s);
							if (retorno.equals("ok")){
								if(AtualizarOdometro()){
									AtualizarSituacao(s.getVeiculo());
								}
								JOptionPane.showMessageDialog(null,"Serviço alterado!");
								PanelListagemServiço();
							}else{
								JOptionPane.showMessageDialog(null,retorno);
							}
					}
						} catch (Exception e) {
						// TODO: handle exception
					}
				
			}
		});
		
		JLabel lblTipoServiço = new JLabel("Tipo Servi\u00E7o");
		lblTipoServiço.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(btnCancelar))
						.addComponent(lblCadastroServio)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblKm)
								.addComponent(lblDescrio)
								.addComponent(lblCupomFiscal)
								.addComponent(lblOramento)
								.addComponent(lblValor)
								.addComponent(lblData)
								.addComponent(lblFornecedor)
								.addComponent(lblVeiculo, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipoServiço)
								.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxMotorista, 0, 503, Short.MAX_VALUE)
								.addComponent(comboBoxTipoServico_1, 0, 640, Short.MAX_VALUE)
								.addComponent(comboBoxVeiculo, 0, 640, Short.MAX_VALUE)
								.addComponent(comboBoxFornecedor, 0, 640, Short.MAX_VALUE)
								.addComponent(textFieldOrçamento, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
								.addComponent(textFieldCupomFiscal, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
								.addComponent(textFieldValor, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
								.addComponent(textFieldData, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
								.addComponent(textFieldDescrição, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
								.addComponent(textFieldKm, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroServio)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData)
						.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValor)
						.addComponent(textFieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOramento)
						.addComponent(textFieldOrçamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCupomFiscal)
						.addComponent(textFieldCupomFiscal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescrio)
						.addComponent(textFieldDescrição, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKm)
						.addComponent(textFieldKm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVeiculo)
						.addComponent(comboBoxVeiculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoServiço)
						.addComponent(comboBoxTipoServico_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxMotorista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFornecedor)
						.addComponent(comboBoxFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(84))
		);
		setLayout(groupLayout);
		if (idServicoSelecionado>0){
			MBServico mbServico = MBServico.getInstance();
			
			try {
				Servico s = mbServico.retornarServico(idServicoSelecionado);
				String b = s.getData2().toString().substring(8, 10)+"/"+s.getData2().toString().substring(5, 7)+"/"+s.getData2().toString().substring(0, 4);
				
				textFieldCupomFiscal.setText(s.getNfTicket().toString());
				textFieldDescrição.setText(s.getDescricao());
				textFieldKm.setText(s.getKm().toString());
				textFieldValor.setText(s.getValor().toString());
				textFieldData.setText(b);
				textFieldOrçamento.setText(s.getNroOrcamento());
				// selecionar combobox fornecedor	
				boolean aux = false ;
				int  i=0; 
					
				while(aux==false){
						aux= mbFornecedor.listarFornecedores().get(i).equals(s.getFornecedor());
				   		if (aux==true) break; 
				   		i++;
						
					}
					comboBoxFornecedor.setSelectedIndex(i);
					//Selecionar combobox veiculo
					i=0;
					aux = false;
					while(aux==false){
						aux= mbVeiculo.listarVeiculos().get(i).equals(s.getVeiculo());
				   		if (aux==true) break; 
				   		i++;
					}
					comboBoxVeiculo.setSelectedIndex(i);
					//Selecionar combobox Motorista

					i=0;
					aux = false;
					while(aux==false){
						aux= mbMotorista.listarMotoristas().get(i).equals(s.getMotorista());
				   		if (aux==true) break; 
				   		i++;
					}
					comboBoxMotorista.setSelectedIndex(i);
					//Selecionar combobox TipoServiço
						veiculoselecionado = s.getVeiculo();
						
						MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();
					i=0;
					aux = false;
					System.out.println(mbTipoServiçoModelo.ListarosTipoServicoModelo(veiculoselecionado.getModelo().getIdmodelo()).size());
					while(aux==false){
						aux= mbTipoServiçoModelo.ListarosTipoServicoModelo(veiculoselecionado.getModelo().getIdmodelo()).get(i).equals(s.getTipoServico());
				   		if (aux==true) break; 
				   		i++;
					}
					comboBoxTipoServico_1.setSelectedIndex(i);
			
			} catch (ClassNotFoundException | SQLException e) {
						
						
						e.printStackTrace();
					}finally{
						
					}}

	}
	public void PanelListagemServiço(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemServiço();
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemServiço();
		}
		
	}
	public java.util.Date transformaData(String data)  
	{  
	  SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy kk:hh:ss");  
	  try  
	  {  
	    return formatador.parse(data);  
	  }  
	  catch(ParseException ex)  
	  {   
	      throw new RuntimeException(ex);  
	  }  
	}
	
	public boolean AtualizarOdometro(){
		MBVeiculo mbVeiculo = MBVeiculo.getInstance();
		Veiculo veiculo = mbVeiculo.retornarVeiculo(comboBoxVeiculo.getItemAt(comboBoxVeiculo.getSelectedIndex()).getIdveiculo());
		int aux = Integer.parseInt(textFieldKm.getText());;
		if(veiculo.getOdometro()<aux){
			veiculo.setOdometro(aux);
			 mbVeiculo.editar(veiculo);
			 return true;
		}else{
			return false;
		}

		
		}
	
	public void ComboboxTipoServico(Veiculo veiculo){
		MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();
		
		DefaultComboBoxModel<TipoServico> modeloComboBoxTipoServico;
		try {
			modeloComboBoxTipoServico = new DefaultComboBoxModel<TipoServico>(new Vector(mbTipoServiçoModelo.ListarosTipoServicoModelo(veiculo.getModelo().getIdmodelo())));
			comboBoxTipoServico_1.setModel(modeloComboBoxTipoServico);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		comboBoxTipoServico_1.updateUI();
		comboBoxTipoServico_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	}
	public void AtualizarSituacao(Veiculo v){
		MBTipoServicoVeiculo mbTipoServicoVeiculo = MBTipoServicoVeiculo.getInstance();
		MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();
		MBServico mbServico = MBServico.getInstance();		
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
				lista.get(i).setSituacao("OK");

			}
				
			}
		for(int i = 0; i<lista.size();i++){
			situacao = situacao+lista.get(i).getSituacao();
			aux = aux+ok;	
			System.out.println(situacao+"si");
			System.out.println(aux+"au");

			}
		MBVeiculo mbVeiculo = MBVeiculo.getInstance();
		System.out.println(situacao.contains("null")+"hh");
		if(situacao.equalsIgnoreCase(aux)){
			v.setSituacao("OK");
			mbVeiculo.editar(v);
			
		}else{
			if(situacao.contains("null")){
				if(situacao.contains("Atrasado")){
					v.setSituacao("Atrasado");
					mbVeiculo.editar(v);
				}
				else{
					v.setSituacao("A Fazer");
					System.out.print(mbVeiculo.editar(v));

				}
			}
			else{
				v.setSituacao("Atrasado");
				mbVeiculo.editar(v);
			}
		
	}
	}
	}
	


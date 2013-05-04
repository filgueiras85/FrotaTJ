package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.SwingConstants;


import util.IntegerDocument;
import util.JCalendar;
import util.JNumberFormatField;
import util.Util;


import mb.MBFornecedor;
import mb.MBMotorista;
import mb.MBServico;
import mb.MBTipoServico;
import mb.MBTipoServicoVeiculo;
import mb.MBTipoServiçoModelo;
import mb.MBVeiculo;

import dao.Fornecedor;
import dao.Motorista;
import dao.Servico;
import dao.TipoServico;
import dao.Veiculo;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;


public class PanelCadastroServico extends PanelExemplo {
	private JTextField textFieldValor;
	private JTextField textFieldOrcamento;
	private JTextField textFieldCupomFiscal;
	private JTextField textFieldDescricao;
	private JTextField textFieldKm;
	private JComboBox<Motorista> comboBoxMotorista;
	private JComboBox<Fornecedor> comboBoxFornecedor;
	private JComboBox<Veiculo> comboBoxVeiculo;
	private	JComboBox<TipoServico> comboBoxTipoServico;
	private JComboBox<TipoServico> comboBoxTipoServico_1;
	private Veiculo veiculoselecionado;
	private MBTipoServicoVeiculo mbTipoServicoVeiculo = MBTipoServicoVeiculo.getInstance();
	private MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();
	private MBServico mbServico = MBServico.getInstance();
	private MBTipoServico mbTipoServico = MBTipoServico.getInstance();
	private MBMotorista mbMotorista = MBMotorista.getInstance();
	private MBVeiculo  mbVeiculo= MBVeiculo.getInstance();
	private MBFornecedor mbFornecedor= MBFornecedor.getInstance();
	Util util = Util.getInstance();	

	/**
	 * Create the panel.
	 */

	public PanelCadastroServico( final int idServicoSelecionado) {

		JLabel lblCadastroServio = new JLabel("Cadastro de Servi\u00E7os");
		lblCadastroServio.setIcon(new ImageIcon("imagens\\ico-recursos-integra.png"));
		lblCadastroServio.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setHorizontalAlignment(SwingConstants.LEFT);

		MaskFormatter data = null;
		try {
			data = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


		final JCalendar cmbData = new JCalendar();

		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JLabel lblOrcamento = new JLabel("Or\u00E7amento");
		lblOrcamento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOrcamento.setHorizontalAlignment(SwingConstants.LEFT);



		final JLabel lblCupomFiscal = new JLabel("Cupom Fiscal");
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

		textFieldValor = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
		textFieldValor.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldValor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldValor.setLocale(new Locale ("pt", "BR"));
		textFieldValor.setColumns(10);

		textFieldOrcamento = new JTextField();
		textFieldOrcamento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldOrcamento.setColumns(10);

		textFieldOrcamento.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if ( textFieldOrcamento.getText().length() >= 100) {
					e.consume();
				}
			}
		});

		textFieldCupomFiscal = new JTextField();
		textFieldCupomFiscal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldCupomFiscal.setColumns(10);

		textFieldCupomFiscal.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if ( textFieldCupomFiscal.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		textFieldDescricao = new JTextField();
		textFieldDescricao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldDescricao.setColumns(10);

		textFieldDescricao.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if ( textFieldDescricao.getText().length() >= 255) {
					e.consume();
				}
			}
		});

		textFieldKm = new JTextField();
		textFieldKm.setDocument(new IntegerDocument(6));
		textFieldKm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldKm.setColumns(10);

		comboBoxMotorista = new JComboBox<Motorista>();
		DefaultComboBoxModel<Motorista> modeloComboBoxMotorista;
		try {
			modeloComboBoxMotorista = new DefaultComboBoxModel<Motorista>(new Vector(mbMotorista.listarMotoristas()));
			comboBoxMotorista.setModel(modeloComboBoxMotorista);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		comboBoxMotorista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxFornecedor = new JComboBox<Fornecedor>();
		DefaultComboBoxModel<Fornecedor> modeloComboBoxFornecedor;
		try {
			modeloComboBoxFornecedor = new DefaultComboBoxModel<Fornecedor>(new Vector(mbFornecedor.listarFornecedores()));
			comboBoxFornecedor.setModel(modeloComboBoxFornecedor);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		comboBoxFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		comboBoxVeiculo = new JComboBox<Veiculo>();

		DefaultComboBoxModel<Veiculo> modeloComboBoxVeiculo;
		try {
			List<Veiculo> lstVeiculo = mbVeiculo.listarVeiculos();
		    ArrayList veiculos = new ArrayList();
		    String veiculo = null;

		    for(int i=0;i<lstVeiculo.size();i++){
		    	veiculo = ""+lstVeiculo.get(i).getPlaca()+" - "+lstVeiculo.get(i).getModelo().getNome();
     	    	veiculos.add(veiculo);

		    }
		    // modeloComboBoxVeiculo = new DefaultComboBoxModel<Veiculo>(new Vector(mbVeiculo.listarVeiculos()));
		    modeloComboBoxVeiculo = new DefaultComboBoxModel<Veiculo>(new Vector(veiculos));
		    comboBoxVeiculo.setModel(modeloComboBoxVeiculo);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		comboBoxVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//veiculoselecionado = (Veiculo) comboBoxVeiculo.getSelectedItem();
				comboBoxTipoServico.removeAll();
				ComboboxTipoServico(retornaVeiculoSel());
			}
		});
		comboBoxVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));

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


				// java.sql.Timestamp data = new java.sql.Timestamp(transformaData(textFieldData.getText()+" 00:00:01").getTime());
				if ( cmbData.getSelectedIndex() < 0 ){
					JOptionPane.showMessageDialog(null, "Favor preencher a data.");
				}else
					if ( textFieldCupomFiscal.getText().isEmpty() ){
						JOptionPane.showMessageDialog(null, "Favor preencher campo Cupom Fiscal.");	
					}else
						if ( textFieldKm.getText().isEmpty() ){
							JOptionPane.showMessageDialog(null, "Favor preencher campo Km.");
						}else{

							java.sql.Timestamp data =  new java.sql.Timestamp(util.getCMBData(cmbData).getTime());

							String valorString = textFieldValor.getText().toString().substring(3, textFieldValor.getText().length());
							valorString = valorString.replaceAll(",", "."); 
							Servico s =  new Servico(new Integer(idServicoSelecionado), 
									mbMotorista.retornarMotorista(comboBoxMotorista.getItemAt(comboBoxMotorista.getSelectedIndex()).getIdmotorista()),
//									mbVeiculo.retornarVeiculo(comboBoxVeiculo.getItemAt(comboBoxVeiculo.getSelectedIndex()).getIdveiculo()),
									retornaVeiculoSel(),
									mbFornecedor.retornarFornecedor((comboBoxFornecedor.getItemAt(comboBoxFornecedor.getSelectedIndex()).getIdfornecedor())),
									mbTipoServico.retornarTipoServico(comboBoxTipoServico_1.getItemAt(comboBoxTipoServico_1.getSelectedIndex()).getIdtipoServico()),
									data, Double.parseDouble(valorString), textFieldOrcamento.getText(), 
									Integer.parseInt(textFieldCupomFiscal.getText().trim()), textFieldDescricao.getText(), Integer.parseInt(textFieldKm.getText().trim()));
							int confirma = 0;
							if ( valorString.equals("0.00")){
								confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja salvar o valor R$"+ textFieldValor.getText()+" ? ","", JOptionPane.YES_NO_OPTION);

							}
							if (confirma == JOptionPane.YES_OPTION){
								try {
									if (idServicoSelecionado==0){
										if (s.getIdServico()==0){
											s.setIdServico(null);
										}
										System.out.println(valorString);

										String retorno = mbServico.inserir(s);
										mbVeiculo.AtualizarOdometro(s.getKm(), s.getVeiculo().getIdveiculo());
										mbTipoServiçoModelo.atualizaStatusVeiculo(s.getVeiculo());
										if (retorno.equals("ok")){

											JOptionPane.showMessageDialog(null,"Serviço inserido!");

										}else{
											JOptionPane.showMessageDialog(null,retorno);
										}
									}else{

										String retorno =  mbServico.editar(s);
										mbVeiculo.AtualizarOdometro(s.getKm(), s.getVeiculo().getIdveiculo());
										mbTipoServiçoModelo.atualizaStatusVeiculo(s.getVeiculo());
										if (retorno.equals("ok")){

											JOptionPane.showMessageDialog(null,"Serviço alterado!");

										}else{
											JOptionPane.showMessageDialog(null,retorno);
										}
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
								PanelListagemServiço();
							}else{

							}
						}
			}
		});

		JLabel lblTipoServiço = new JLabel("Tipo Servi\u00E7o");
		lblTipoServiço.setFont(new Font("Tahoma", Font.PLAIN, 15));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblKm)
												.addComponent(lblDescrio)
												.addComponent(lblCupomFiscal)
												.addComponent(lblOrcamento)
												.addComponent(lblValor)
												.addComponent(lblData)
												.addComponent(lblFornecedor)
												.addComponent(lblVeiculo, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblTipoServiço)
												.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(comboBoxMotorista, 0, 477, Short.MAX_VALUE)
														.addComponent(comboBoxTipoServico_1, 0, 477, Short.MAX_VALUE)
														.addComponent(comboBoxVeiculo, 0, 477, Short.MAX_VALUE)
														.addComponent(comboBoxFornecedor, 0, 477, Short.MAX_VALUE)
														.addComponent(textFieldOrcamento, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
														.addComponent(textFieldCupomFiscal, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
														.addComponent(textFieldValor, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
														.addComponent(textFieldDescricao, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
														.addComponent(textFieldKm, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(cmbData, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
																.addComponent(lblCadastroServio)
																.addGap(19))))
																.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
																		.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addComponent(btnCancelar)))
																		.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblData)
								.addComponent(cmbData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCadastroServio))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblValor)
										.addComponent(textFieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblOrcamento)
												.addComponent(textFieldOrcamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblCupomFiscal)
														.addComponent(textFieldCupomFiscal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblDescrio)
																.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
																										.addGap(18)
																										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																												.addComponent(btnCancelar)
																												.addComponent(btnSalvar))
																												.addContainerGap(51, Short.MAX_VALUE))
				);
		setLayout(groupLayout);
		if (idServicoSelecionado>0){
			MBServico mbServico = MBServico.getInstance();

			try {
				Servico s = mbServico.retornarServico(idServicoSelecionado);
				String d = s.getData2().toString().substring(8, 10)+"/"+s.getData2().toString().substring(5, 7)+"/"+s.getData2().toString().substring(0, 4);
				cmbData.setSelectedItem(d);
				textFieldCupomFiscal.setText(s.getNfTicket().toString());
				textFieldDescricao.setText(s.getDescricao());
				textFieldKm.setText(s.getKm().toString());
				textFieldValor.setText(util.retornaMoeda(s.getValor()));
				//textFieldData.setText(b);
				textFieldOrcamento.setText(s.getNroOrcamento());
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
			try {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
				parent.PanelListagemServiço();
			} catch (Exception e1) {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent().getParent();
				parent.PanelListagemServiço();
			}
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

	public Veiculo retornaVeiculoSel(){
		String[] veiculo = comboBoxVeiculo.getSelectedItem().toString().split(" - ");
		try {
			veiculoselecionado = mbVeiculo.VeiculoPorPlaca( veiculo[0] );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return veiculoselecionado;
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
}
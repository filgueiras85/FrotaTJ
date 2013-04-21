package Visao;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.sql.Timestamp;

import mb.MBAbastecimento;
import mb.MBFornecedor;
import mb.MBModelo;
import mb.MBMotorista;
import mb.MBServico;
import mb.MBTipoServico;
import mb.MBTipoServicoVeiculo;
import mb.MBTipoServiçoModelo;
import mb.MBUnidade;
import mb.MBVeiculo;
import dao.Abastecimento;
import dao.Fornecedor;
import dao.Modelo;
import dao.Motorista;
import dao.Servico;
import dao.TipoServico;
import dao.TipoServicoModelo;
import dao.TipoServicoVeiculo;
import dao.TipoServicoVeiculoId;
import dao.Unidade;
import dao.Veiculo;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;

import util.IntegerDocument;
import util.JCalendar;
import util.Util;


public class PanelCadastroAbastecimento extends PanelExemplo {
	private JTextField textFieldHodometro;
	private JComboBox<Veiculo> comboBoxPlaca;
	private MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();


	/**
	 * Create the panel.
	 */

	public PanelCadastroAbastecimento( final int idAbastecimentoSelecionado) {

		JLabel lblCadastroAbastecimento = new JLabel(" Cadastro de Abastecimentos\r\n");
		lblCadastroAbastecimento.setIcon(new ImageIcon("imagens\\2895_32x32.png"));
		lblCadastroAbastecimento.setFont(new Font("Tahoma", Font.PLAIN, 20));

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

		JLabel lblHodometro = new JLabel("Hod\u00F4metro");
		lblHodometro.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textFieldHodometro = new JTextField();
		textFieldHodometro.setDocument(new IntegerDocument(6));
		textFieldHodometro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldHodometro.setColumns(10);

		MBVeiculo mbVeiculo = MBVeiculo.getInstance();
		comboBoxPlaca = new JComboBox<Veiculo>();
		DefaultComboBoxModel<Veiculo> modeloComboBox;

		JLabel lblVeiculo = new JLabel("Veiculo");
		lblVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JLabel lblModelo = new JLabel("");
		comboBoxPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Veiculo v = (Veiculo) comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex());
				lblModelo.setText(v.getModelo().getNome());
				lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			}
		});

		try {
			modeloComboBox = new DefaultComboBoxModel<Veiculo>(new Vector(mbVeiculo.listarVeiculos()));
			comboBoxPlaca.setModel(modeloComboBox);
			comboBoxPlaca.setSelectedIndex(0);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Veiculo veiculoSelecionado = (Veiculo) comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex());

		comboBoxPlaca.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemAbastecimento();
			}
		});



		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("imagens\\7484_16x16.png"));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Util util = Util.getInstance();

				java.sql.Timestamp data =  new java.sql.Timestamp(util.getCMBData(cmbData).getTime());



				MBAbastecimento mbAbastecimento = MBAbastecimento.getInstance();
				MBVeiculo mbVeiculo = MBVeiculo.getInstance();
				Abastecimento a;
				try {
					a = new Abastecimento(
							new Integer(idAbastecimentoSelecionado), 
							mbVeiculo.retornarVeiculo(comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex()).getIdveiculo()), 
							Integer.parseInt(Util.mascaraHodometro(textFieldHodometro.getText())), data
							);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				try {
					a = new Abastecimento(new Integer(idAbastecimentoSelecionado), mbVeiculo.retornarVeiculo(comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex()).getIdveiculo()), Integer.parseInt(Util.mascaraHodometro(textFieldHodometro.getText())), data);
					if (idAbastecimentoSelecionado==0){
						if (a.getIdabastecimento()==0){
							a.setIdabastecimento(null);
						}
						String retorno = mbAbastecimento.inserir(a);
						mbVeiculo.AtualizarOdometro(Integer.parseInt(textFieldHodometro.getText()), comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex()).getIdveiculo());
						mbTipoServiçoModelo.atualizaStatusVeiculo(mbVeiculo.retornarVeiculo(comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex()).getIdveiculo()));

						if (retorno.equals("ok")){
							JOptionPane.showMessageDialog(null,"Cadastro inserido!");
							//PanelListagemAbastecimento();
						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}else{

						String retorno =  mbAbastecimento.editar(a);
						mbVeiculo.AtualizarOdometro(Integer.parseInt(textFieldHodometro.getText()), comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex()).getIdveiculo());
						mbTipoServiçoModelo.atualizaStatusVeiculo(mbVeiculo.retornarVeiculo(comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex()).getIdveiculo()));
						if (retorno.equals("ok")){

							//PanelListagemAbastecimento();
						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				PanelListagemAbastecimento();
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCadastroAbastecimento)
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblHodometro)
												.addComponent(lblData)
												.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblVeiculo))
												.addGap(53)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblModelo)
														.addGroup(groupLayout.createSequentialGroup()
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
																		.addComponent(cmbData, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(comboBoxPlaca, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(textFieldHodometro, 0, 0, Short.MAX_VALUE)
																		.addComponent(btnSalvar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
																		.addGap(10)
																		.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))))
																		.addContainerGap(92, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblCadastroAbastecimento)
						.addGap(28)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblData)
								.addComponent(cmbData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblHodometro)
										.addComponent(textFieldHodometro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblPlaca)
												.addComponent(comboBoxPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblVeiculo)
														.addComponent(lblModelo))
														.addGap(57)
														.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																.addComponent(btnSalvar)
																.addComponent(btnCancelar))
																.addGap(36))
				);



		setLayout(groupLayout);
		if (idAbastecimentoSelecionado>0){
			MBAbastecimento mbAbastecimento = MBAbastecimento.getInstance();
			Util util = Util.getInstance();
			try {
				Abastecimento a = mbAbastecimento.retornarAbastecimento(idAbastecimentoSelecionado);
				String d = a.getData2().toString().substring(8, 10)+"/"+a.getData2().toString().substring(5, 7)+"/"+a.getData2().toString().substring(0, 4);
				cmbData.setSelectedItem(d);
				textFieldHodometro.setText(a.getKmOdometro().toString());


				boolean aux = false ;
				int  i=0; 

				while(aux==false){
					aux= mbVeiculo.listarVeiculos().get(i).getIdveiculo()==a.getVeiculo().getIdveiculo();
					if (aux==true) break; 
					i++;

				}
				comboBoxPlaca.setSelectedIndex(i);



			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"erro - "+e);
				// TODO: handle exception
			}

		}
	}
	public void PanelListagemAbastecimento(){


		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemAbastecimento();
		} catch (Exception e) {
			try {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
				parent.PanelListagemAbastecimento();
			} catch (Exception e1) {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent().getParent();
				parent.PanelListagemAbastecimento();
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

			JOptionPane.showMessageDialog(null,"Por favor, verifique a data do cadastro. /n     A data deve estar no formato: 15/11/2012");
		}
		return null;  
	}
}

package Visao;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
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


public class PanelCadastroAbastecimento extends PanelExemplo {
	private JTextField textFieldData;
	private JTextField textFieldHodometro;
	private JComboBox<Veiculo> comboBoxPlaca;


	/**
	 * Create the panel.
	 */

	public PanelCadastroAbastecimento( final int idAbastecimentoSelecionado) {

		JLabel lblCadastroAbastecimento = new JLabel("Cadastro Abastecimento\r\n");
		lblCadastroAbastecimento.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setHorizontalAlignment(SwingConstants.LEFT);

		textFieldData = new JTextField();
		textFieldData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldData.setColumns(10);

		JLabel lblHodometro = new JLabel("Hod\u00F4metro");
		lblHodometro.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textFieldHodometro = new JTextField();
		textFieldHodometro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldHodometro.setColumns(10);


		MBVeiculo mbVeiculo = MBVeiculo.getInstance();
		comboBoxPlaca = new JComboBox<Veiculo>();
		DefaultComboBoxModel<Veiculo> modeloComboBox;

		try {
			modeloComboBox = new DefaultComboBoxModel<Veiculo>(new Vector(mbVeiculo.listarVeiculos()));
			comboBoxPlaca.setModel(modeloComboBox);
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
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemAbastecimento();
			}
		});

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				java.sql.Timestamp data2 = new java.sql.Timestamp(transformaData(textFieldData.getText()+" 00:00:01").getTime());
				MBAbastecimento mbAbastecimento = MBAbastecimento.getInstance();
				MBVeiculo mbVeiculo = MBVeiculo.getInstance();
				Abastecimento a =  new Abastecimento(new Integer(idAbastecimentoSelecionado), mbVeiculo.retornarVeiculo(comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex()).getIdveiculo()), Integer.parseInt(textFieldHodometro.getText()), data2);


				try {
					if (idAbastecimentoSelecionado==0){
						if (a.getIdabastecimento()==0){
							a.setIdabastecimento(null);
						}
						String retorno = mbAbastecimento.inserir(a);
						if (retorno.equals("ok")){
							AtualizarOdometro();
							AtualizarSituacao(a.getVeiculo());
							JOptionPane.showMessageDialog(null,"Abastecimento inserido!");
							PanelListagemAbastecimento();
						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}else{

						String retorno =  mbAbastecimento.editar(a);
						if (retorno.equals("ok")){
							AtualizarOdometro();
							JOptionPane.showMessageDialog(null,"Abastecimento alterado!");
							PanelListagemAbastecimento();
						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblHodometro)
												.addComponent(lblData)
												.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
												.addGap(53)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(comboBoxPlaca, 0, 485, Short.MAX_VALUE)
														.addComponent(textFieldData, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
														.addComponent(textFieldHodometro, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))
														.addComponent(lblCadastroAbastecimento))
														.addContainerGap())
														.addGroup(groupLayout.createSequentialGroup()
																.addContainerGap(329, Short.MAX_VALUE)
																.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
																.addGap(19))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblCadastroAbastecimento)
						.addGap(28)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblData)
								.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblHodometro)
										.addComponent(textFieldHodometro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblPlaca)
												.addComponent(comboBoxPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(76)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnSalvar)
														.addComponent(btnCancelar))
														.addGap(284))
				);
		setLayout(groupLayout);
		if (idAbastecimentoSelecionado>0){
			MBAbastecimento mbAbastecimento = MBAbastecimento.getInstance();

			try {
				Abastecimento a = mbAbastecimento.retornarAbastecimento(idAbastecimentoSelecionado);
				textFieldData.setText(a.getData2().toString());
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
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemAbastecimento();
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
	public void AtualizarOdometro(){
		MBVeiculo mbVeiculo = MBVeiculo.getInstance();
				
		Veiculo veiculo = mbVeiculo.retornarVeiculo(comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex()).getIdveiculo());
		int aux = Integer.parseInt(textFieldHodometro.getText());;
		if(veiculo.getOdometro()<aux){
			veiculo.setOdometro(aux);
			 mbVeiculo.editar(veiculo);
		}

		
		}
	public void AtualizarSituacao(Veiculo v){
		MBTipoServicoVeiculo mbTipoServicoVeiculo = MBTipoServicoVeiculo.getInstance();
		MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();
		MBServico mbServico = MBServico.getInstance();		
		List<TipoServicoModelo> listaTipoServico = mbTipoServiçoModelo.ListarosTipoServicodoModelo(v.getModelo().getIdmodelo());
		String ok = "Ok";
		String situacao="";
		String aux = "";
		List<TipoServicoVeiculo> lista = new ArrayList<>();
		lista = mbTipoServicoVeiculo.ListarosTipoServicoVeiculo(v.getIdveiculo());
		System.out.println(lista.size());
		for(int i = 0; i<lista.size();i++){
			List<Servico> listaServico = mbServico.ListarosServicodoVeiculo(v.getIdveiculo(), lista.get(i).getTipoServico());
			System.out.println(lista.size()+"lista");
			System.out.println(v.getOdometro()+listaTipoServico.get(i).getKm()+"exemplo");
			if(v.getOdometro()<listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
				lista.get(i).setSituacao(true);
				System.out.println("bom");

			}else{
				if(v.getOdometro()>listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
					lista.get(i).setSituacao(false);
					System.out.println("atrasado1");
				}else{
					if(v.getOdometro()==listaServico.get((listaServico.size()-1)).getKm()+listaTipoServico.get(i).getKm()){
						lista.get(i).setSituacao(false);
						System.out.println("atrasado2");

					}	
				}
				
			}
			
				
			}
		for(int i = 0; i<lista.size();i++){
			situacao = situacao+lista.get(i).Situacao();
			aux = aux+ok;	
			System.out.println(situacao);
			}
		MBVeiculo mbVeiculo = MBVeiculo.getInstance();
		if(situacao.equalsIgnoreCase(aux)){
			v.setSituacao("ok");
			mbVeiculo.editar(v);
			
		}else{
		v.setSituacao("atrasado");
		mbVeiculo.editar(v);
	}
	}
}

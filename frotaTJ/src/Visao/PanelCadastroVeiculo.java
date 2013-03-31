package Visao;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.JButton;

import dao.Modelo;
import dao.Motorista;
import dao.TipoServico;
import dao.TipoServicoVeiculo;
import dao.TipoServicoVeiculoId;
import dao.Unidade;
import dao.Veiculo;

import mb.MBModelo;
import mb.MBMotorista;
import mb.MBTipoServicoVeiculo;
import mb.MBTipoServiçoModelo;
import mb.MBUnidade;
import mb.MBVeiculo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;

import util.IntegerDocument;


public class PanelCadastroVeiculo extends PanelExemplo {
	private JTextField textFieldPlaca;
	private JTextField textFieldOdometro;
	private JTextField textFieldChassi;
	private JTextField textFieldRenavan;
	private JComboBox<String> comboBoxModelo;
	private JComboBox<String> comboBoxUnidade;
	private JComboBox<Motorista> comboBoxMotorista;
	private Date dataCadastro;
	private int kmcadastro;

	/**
	 * Create the panel.
	 */

	public PanelCadastroVeiculo() {
		new PanelCadastroVeiculo(0);
	}
	public PanelCadastroVeiculo(final int idVeiculoSelecionado) {
		final MBVeiculo mbVeiculo= MBVeiculo.getInstance();
		final MBModelo mbModelo = MBModelo.getInstance();
		final MBUnidade mbUnidade = MBUnidade.getInstance();
		final MBMotorista mbMotorista = MBMotorista.getInstance();

		// ------------------------------ Labels ---------------------------\\	
		JLabel lblCadastroModelo = new JLabel("Cadastro de Veiculos\r\n");
		lblCadastroModelo.setIcon(new ImageIcon("imagens\\1519_32x32.png"));
		lblCadastroModelo.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel lblOdometro = new JLabel("Odometro");
		lblOdometro.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel lblChassi = new JLabel("Chassi");
		lblChassi.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel lblRenavan = new JLabel("Renavan");
		lblRenavan.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel lblMotorista = new JLabel("Motorista");
		lblMotorista.setFont(new Font("Tahoma", Font.PLAIN, 17));

		// ------------------------------ TextFields ---------------------------\\			
		MaskFormatter placa = null;
		try {
			placa = new MaskFormatter("UUU-####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MaskFormatter Renavam = null;
		try {
			Renavam = new MaskFormatter("########-#");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MaskFormatter chassi = null;
		try {
			chassi = new MaskFormatter(	"AAAAAAAAAAAAAAAAA");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textFieldPlaca = new JFormattedTextField(placa);
		textFieldPlaca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPlaca.setColumns(10);

		textFieldOdometro = new JTextField();
		textFieldOdometro.setDocument(new IntegerDocument(6));
		textFieldOdometro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldOdometro.setColumns(10);

		textFieldChassi = new JFormattedTextField(chassi);
		textFieldChassi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldChassi.setColumns(10);

		textFieldRenavan = new JFormattedTextField(Renavam);
		textFieldRenavan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldRenavan.setColumns(10);

		// ------------------------------ ComboBoxs ---------------------------\\		
		comboBoxModelo = new JComboBox<String>();
		comboBoxModelo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultComboBoxModel<String> modelComboBoxModelo;
		final Vector<Modelo> listaModelo = new Vector<>();
		final Vector<String> listaNomeModelo = new Vector<>();
		try {

			listaModelo.addAll(mbModelo.listarModelos());
			System.out.println(mbModelo.listarModelos());
			System.out.println("---- lista Modelo ----- ");
			for (int i = 0; i<listaModelo.size();i++){
				System.out.println(listaModelo.get(i).getNome());
				listaNomeModelo.add(listaModelo.get(i).getNome());
			}
			modelComboBoxModelo = new DefaultComboBoxModel<String>(listaNomeModelo);
			comboBoxModelo.setModel(modelComboBoxModelo);
		} catch (Exception e) {
			// TODO: handle exception
		}	

		comboBoxUnidade = new JComboBox<String>();
		comboBoxUnidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultComboBoxModel<String> modelComboBoxUnidade;
		final Vector<Unidade> listaUnidade = new Vector<>();
		final Vector<String> listaNomeUnidade = new Vector<>();

		try {
			listaUnidade.addAll(mbUnidade.listarUnidades());

			for (int i = 0; i<listaUnidade.size();i++){
				listaNomeUnidade.add(listaUnidade.get(i).getNome());
			}
			modelComboBoxUnidade = new DefaultComboBoxModel<String>(listaNomeUnidade);
			comboBoxUnidade.setModel(modelComboBoxUnidade);
		} catch (Exception e) {
			// TODO: handle exception
		}

		comboBoxMotorista = new JComboBox<Motorista>();
		comboBoxMotorista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultComboBoxModel<Motorista> modelComboBoxMotorista;
		try {
			modelComboBoxMotorista = new DefaultComboBoxModel<Motorista>(new Vector(mbMotorista.listarMotoristas()));
			comboBoxMotorista.setModel(modelComboBoxMotorista);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// ------------------------------ Botões ---------------------------\\	
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemVeiculo();
			}
		});

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("imagens\\7484_16x16.png"));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Veiculo v = new Veiculo();
				if(idVeiculoSelecionado==0){
					Date hoje = new Date(System.currentTimeMillis());
					v = new Veiculo(idVeiculoSelecionado, listaModelo.get(comboBoxModelo.getSelectedIndex()), mbMotorista.retornarMotorista(comboBoxMotorista.getItemAt(comboBoxMotorista.getSelectedIndex()).getIdmotorista()), listaUnidade.get(comboBoxUnidade.getSelectedIndex()), textFieldPlaca.getText(), textFieldRenavan.getText(), textFieldChassi.getText(), Integer.parseInt(textFieldOdometro.getText().trim()), null,Integer.parseInt(textFieldOdometro.getText().trim()), hoje );
				}else{
					v = new Veiculo(idVeiculoSelecionado, listaModelo.get(comboBoxModelo.getSelectedIndex()), mbMotorista.retornarMotorista(comboBoxMotorista.getItemAt(comboBoxMotorista.getSelectedIndex()).getIdmotorista()), listaUnidade.get(comboBoxUnidade.getSelectedIndex()), textFieldPlaca.getText(), textFieldRenavan.getText(), textFieldChassi.getText(), Integer.parseInt(textFieldOdometro.getText().trim()), null, kmcadastro, dataCadastro);

				}
				try {
					if (idVeiculoSelecionado==0){
						if (v.getIdveiculo()==0){
							v.setIdveiculo(null);
						}
						String retorno = mbVeiculo.inserir(v);
						Situação(v, idVeiculoSelecionado);
						if (retorno.equals("ok")){

							JOptionPane.showMessageDialog(null,"Cadastro efetuado!");

						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}else{
						Veiculo v2 = mbVeiculo.retornarVeiculo(v.getIdveiculo());
						v.setSituacao(v2.getSituacao());
						String retorno =  mbVeiculo.editar(v);
						if (retorno.equals("ok")){
							JOptionPane.showMessageDialog(null,"Cadastro Alterado!");

						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				PanelListagemVeiculo();
			}
		});



		// ------------------------------ Configuração do Layout ---------------------------\\	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(comboBoxMotorista, 0, 340, Short.MAX_VALUE))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
																.addComponent(lblOdometro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(lblPlaca)
																.addComponent(lblRenavan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(lblChassi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																.addComponent(lblModelo)
																.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
																.addGap(18)
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																		.addComponent(comboBoxModelo, Alignment.TRAILING, 0, 338, Short.MAX_VALUE)
																		.addComponent(textFieldRenavan, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
																		.addComponent(textFieldChassi, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
																		.addComponent(textFieldOdometro, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
																		.addComponent(comboBoxUnidade, Alignment.TRAILING, 0, 338, Short.MAX_VALUE)
																		.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
																		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
																				.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(ComponentPlacement.UNRELATED)
																				.addComponent(btnCancelar))
																				.addComponent(lblCadastroModelo))
																				.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblCadastroModelo)
						.addGap(61)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPlaca)
								.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldRenavan, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRenavan, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(textFieldChassi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblChassi, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(textFieldOdometro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblOdometro))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																.addComponent(comboBoxModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblModelo))
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																		.addComponent(comboBoxUnidade, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																				.addComponent(comboBoxMotorista, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
																				.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
																				.addGap(104)
																				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																						.addComponent(btnCancelar)
																						.addComponent(btnSalvar))
																						.addContainerGap())
				);
		setLayout(groupLayout);

		// ------------------------------ Checa o ID selecionado, para saber se é pra editar ou cadastrar ---------------------------\\	
		// ------------------------------ e Preenche os campos caso for uma edição ---------------------------------------------------\\
		if (idVeiculoSelecionado>0){

			try {
				Veiculo v = new Veiculo();
				v = mbVeiculo.retornarVeiculo(idVeiculoSelecionado);
				textFieldPlaca.setText(v.getPlaca());
				textFieldRenavan.setText(v.getrenavan());
				textFieldChassi.setText(v.getChassi());
				textFieldOdometro.setText(v.getOdometro().toString());
				comboBoxModelo.setSelectedItem(1);
				kmcadastro = v.getKmCadastro();
				dataCadastro =v.getDataCadastro();

				//-------------Seleciona os ComboBoxs-------------\\
				boolean aux = false ;
				int  i=0; 
				while(aux==false){
					aux= mbModelo.listarModelos().get(i).getIdmodelo()==v.getModelo().getIdmodelo();
					if (aux==true) break; 
					i++;						
				}
				comboBoxModelo.setSelectedIndex(i);

				i=0;
				aux = false;
				while(aux==false){
					aux= mbUnidade.listarUnidades().get(i).getIdunidade()==v.getUnidade().getIdunidade();
					if (aux==true) break; 
					i++;
				}
				comboBoxUnidade.setSelectedIndex(i);

				i=0;
				aux = false;
				while(aux==false){
					aux= mbMotorista.listarMotoristas().get(i).getIdmotorista()==v.getMotorista().getIdmotorista();
					if (aux==true) break; 
					i++;
				}
				comboBoxMotorista.setSelectedIndex(i);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"erro - "+e);
				// TODO: handle exception
			}

		}

	}
	// ------------------------------ Metodos ---------------------------\\	
	public void PanelListagemVeiculo(){ //Troca para o Panel listagemVeiculo
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemVeiculo();
		} catch (Exception e) {
			try {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
				parent.PanelListagemVeiculo();
			} catch (Exception e1) {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent().getParent();
				parent.PanelListagemVeiculo();
			}
		}
	}
	public void Situação(Veiculo v, int idVeiculoSelecionado){
		if(idVeiculoSelecionado==0){
			MBTipoServicoVeiculo mbTipoServicoVeiculo = MBTipoServicoVeiculo.getInstance();
			MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();
			List<TipoServico> listaTipoServico = mbTipoServiçoModelo.ListarosTipoServicoModelo(v.getModelo().getIdmodelo());
			String ok = "verde";
			String situacao=null;
			String aux = null;
			for(int i = 0;i<listaTipoServico.size();i++){
				TipoServicoVeiculoId tipoServicoVeiculoId = new TipoServicoVeiculoId(v.getIdveiculo(), listaTipoServico.get(i).getIdtipoServico());
				TipoServicoVeiculo tipoServicoVeiculo = new TipoServicoVeiculo(tipoServicoVeiculoId, v, listaTipoServico.get(i), "verde");
				mbTipoServicoVeiculo.inserir(tipoServicoVeiculo);
			}
			List<TipoServicoVeiculo> lista = mbTipoServicoVeiculo.ListarosTipoServicoVeiculo(v.getIdveiculo());

			for(int i = 0; i<lista.size();i++){
				situacao = situacao+lista.get(i).getSituacao();
				aux = aux+ok;	
			}
			MBVeiculo mbVeiculo = MBVeiculo.getInstance();
			if(situacao.equalsIgnoreCase(aux)){
				v.setSituacao("verde");
				mbVeiculo.editar(v);

			}

		}


	}
}
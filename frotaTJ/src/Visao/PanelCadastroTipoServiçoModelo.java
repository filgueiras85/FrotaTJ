package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;

import util.IntegerDocument;

import mb.MBMarca;
import mb.MBModelo;
import mb.MBTipoServico;
import mb.MBTipoServicoVeiculo;
import mb.MBTipoServiçoModelo;
import mb.MBVeiculo;

import dao.Marca;
import dao.Modelo;
import dao.TipoServico;
import dao.TipoServicoModelo;
import dao.TipoServicoModeloDAO;
import dao.TipoServicoModeloId;
import dao.TipoServicoVeiculo;
import dao.TipoServicoVeiculoId;
import dao.Veiculo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PanelCadastroTipoServiçoModelo extends PanelExemplo {
	private JComboBox<Modelo> comboBoxModelo;
	private JComboBox<TipoServico> comboBoxTipoServiço;
	private String winDir= ("imagens\\");
	private JTextField textFieldKM;
	private JTextField textFieldData;
	private JLabel lblModelo;
	/**
	 * Create the panel.
	 */
	public PanelCadastroTipoServiçoModelo(final int idModeloSelecionado, final int idTipoServiçoselecionado) {

		JLabel lblCadastroTipoServicoModelo = new JLabel("  Cadastro Tipo Servi\u00E7o - Modelo");
		lblCadastroTipoServicoModelo.setIcon(new ImageIcon("imagens\\11988_32x32.png"));
		lblCadastroTipoServicoModelo.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemTipoServiçoModelo();
			}
		});

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("imagens\\7484_16x16.png"));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MBTipoServico mbTipoServico = MBTipoServico.getInstance();
				TipoServicoModeloId tipoServicoModeloId = new TipoServicoModeloId(new Integer(comboBoxTipoServiço.getItemAt(comboBoxTipoServiço.getSelectedIndex()).getIdtipoServico()), 
						new Integer(comboBoxModelo.getItemAt(comboBoxModelo.getSelectedIndex()).getIdmodelo()));
				MBModelo mbModelo = MBModelo.getInstance();
				MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();
				TipoServicoModelo t = new TipoServicoModelo(tipoServicoModeloId, mbModelo.retornarModelo(comboBoxModelo.getItemAt(comboBoxModelo.getSelectedIndex()).getIdmodelo()), mbTipoServico.retornarTipoServico(comboBoxTipoServiço.getItemAt(comboBoxTipoServiço.getSelectedIndex()).getIdtipoServico()), Integer.parseInt(textFieldKM.getText()), Integer.parseInt(textFieldData.getText()));

				try {
					if (idModeloSelecionado==0 && idTipoServiçoselecionado == 0){
						if (t.getId().getModeloIdmodelo()==0 && t.getId().getTipoServicoIdtipoServico()==0){

							t.setId(tipoServicoModeloId);
						}

						String retorno = mbTipoServiçoModelo.inserir(t);

						if (retorno.equals("ok")){
							AtualizarTipoServicosdosveiculos(t);
							JOptionPane.showMessageDialog(null,"Inserido!");
							PanelListagemTipoServiçoModelo();
						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}else{

						String retorno =  mbTipoServiçoModelo.editar(t);
						if (retorno.equals("ok")){
							JOptionPane.showMessageDialog(null,"Alterado!");
							PanelListagemTipoServiçoModelo();
						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}}
				catch (Exception e) {
					// TODO: handle exception
				}




			}

		}


				);

		JLabel lblTipoServiço = new JLabel("Tipo Servi\u00E7o");
		lblTipoServiço.setFont(new Font("Tahoma", Font.PLAIN, 17));

		lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 17));

		MBModelo mbModelo = MBModelo.getInstance();
		comboBoxModelo = new JComboBox<Modelo>();
		DefaultComboBoxModel<Modelo> modeloComboBox;

		try {
			modeloComboBox = new DefaultComboBoxModel<Modelo>(new Vector(mbModelo.listarModelos()));
			comboBoxModelo.setModel(modeloComboBox);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		//DefaultComboBoxModel<Fornecedor>(mbFornecedor.listarFornecedores());
		comboBoxModelo.setFont(new Font("Tahoma", Font.PLAIN, 15));

		MBTipoServico mbTipoServico = MBTipoServico.getInstance();
		comboBoxTipoServiço = new JComboBox<TipoServico>();
		DefaultComboBoxModel<TipoServico> modeloComboBoxTipoServiço;

		try {
			modeloComboBoxTipoServiço = new DefaultComboBoxModel<TipoServico>(new Vector(mbTipoServico.listarTipoServicos()));
			comboBoxTipoServiço.setModel(modeloComboBoxTipoServiço);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxTipoServiço.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel labelKm = new JLabel("KM");
		labelKm.setFont(new Font("Tahoma", Font.PLAIN, 17));

		textFieldKM = new JTextField();
		textFieldKM.setDocument(new IntegerDocument(6));



				textFieldKM.setColumns(10);

				JLabel labelData = new JLabel("Tempo");
				labelData.setFont(new Font("Tahoma", Font.PLAIN, 17));

				
				
				textFieldData = new JTextField();
				textFieldData.setDocument(new IntegerDocument(3));
				textFieldData.setColumns(10);
				GroupLayout groupLayout = new GroupLayout(this);
				groupLayout.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(btnCancelar))
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																				.addComponent(lblTipoServiço)
																				.addComponent(lblModelo)
																				.addComponent(labelKm, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
																				.addGap(18))
																				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
																						.addComponent(labelData, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
																						.addGap(55)))
																						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																								.addComponent(textFieldKM, 429, 429, 429)
																								.addComponent(comboBoxModelo, 0, 429, Short.MAX_VALUE)
																								.addComponent(comboBoxTipoServiço, 0, 429, Short.MAX_VALUE)
																								.addComponent(textFieldData)))
																								.addComponent(lblCadastroTipoServicoModelo))
																								.addContainerGap())
						);
				groupLayout.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblCadastroTipoServicoModelo)
								.addGap(57)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblTipoServiço)
										.addComponent(comboBoxTipoServiço, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(comboBoxModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblModelo))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(labelKm, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
														.addComponent(textFieldKM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																.addComponent(labelData, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
																.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
																.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																		.addComponent(btnCancelar)
																		.addComponent(btnSalvar))
																		.addGap(31))
						);
				setLayout(groupLayout);
				if (idModeloSelecionado>0 || idTipoServiçoselecionado>0){
					MBTipoServiçoModelo mbt = MBTipoServiçoModelo.getInstance();

					try {
						TipoServicoModeloId id = new TipoServicoModeloId(new Integer(idTipoServiçoselecionado), new Integer(idModeloSelecionado));
						TipoServicoModelo t = mbt.retornarTipoServicoModelo(id);
						textFieldData.setText(t.getTempo().toString());
						textFieldKM.setText(t.getKm().toString());

						boolean aux = false ;
						int  i=0; 

						while(aux==false){
							aux= mbModelo.listarModelos().get(i).getIdmodelo()==t.getModelo().getIdmodelo();
							if (aux==true) break; 
							i++;

						}
						comboBoxModelo.setSelectedIndex(i);
						i=0;
						aux = false;
						while(aux==false){
							aux= mbTipoServico.listarTipoServicos().get(i).equals(t.getTipoServico());
							if (aux==true) break; 
							i++;

						}
						comboBoxTipoServiço.setSelectedIndex(i);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"erro - "+e);
						// TODO: handle exception
					}

				}

	}

	public void PanelListagemTipoServiçoModelo(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemTipoServiçoModelo();
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemTipoServiçoModelo();
		}
	}

	public void AtualizarTipoServicosdosveiculos( TipoServicoModelo tipoServicoModelo){

		MBTipoServicoVeiculo mbTipoServicoVeiculo = MBTipoServicoVeiculo.getInstance();
		MBVeiculo mbVeiculo = MBVeiculo.getInstance();
		List<Veiculo> listaVeiculo= mbVeiculo.ListarosVeiculodoModelo(tipoServicoModelo.getModelo());
		for(int i = 0;i<listaVeiculo.size();i++){
			Veiculo v = listaVeiculo.get(i);
			TipoServico t = tipoServicoModelo.getTipoServico();
			TipoServicoVeiculoId tipoServicoVeiculoId = new TipoServicoVeiculoId(listaVeiculo.get(i).getIdveiculo(), tipoServicoModelo.getTipoServico().getIdtipoServico());
			TipoServicoVeiculo tipoServicoVeiculo = new TipoServicoVeiculo(tipoServicoVeiculoId,v , t, "OK");
			mbTipoServicoVeiculo.inserir(tipoServicoVeiculo);
		}


	}

}



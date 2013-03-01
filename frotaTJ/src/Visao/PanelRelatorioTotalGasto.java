package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;

import util.JCalendar;
import util.UsuarioUtil;

import javax.swing.ListSelectionModel;
import javax.swing.DropMode;
import javax.swing.JComboBox;

import dao.Fornecedor;
import dao.Marca;
import dao.Modelo;
import dao.Motorista;
import dao.Servico;
import dao.TipoServico;
import dao.Unidade;
import dao.Veiculo;

import mb.MBFornecedor;
import mb.MBMarca;
import mb.MBModelo;
import mb.MBMotorista;
import mb.MBServico;
import mb.MBTipoServico;
import mb.MBUnidade;
import mb.MBVeiculo;

import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class PanelRelatorioTotalGasto extends PanelExemplo {
	private JTable table;
	final MBServico mbServico = MBServico.getInstance();

	/**

	 * Create the panel.
	 */
	public PanelRelatorioTotalGasto() {

		final UsuarioUtil usuarioLogado = UsuarioUtil.getInstance();
		final MBTipoServico mbTipoServico = MBTipoServico.getInstance();
		final MBUnidade mbUnidade = MBUnidade.getInstance();
		final MBMotorista mbMotorista = MBMotorista.getInstance();
		final MBMarca mbMarca = MBMarca.getInstance();
		final MBModelo mbModelo = MBModelo.getInstance();	
		final MBFornecedor mbFornecedor = MBFornecedor.getInstance();
		final MBVeiculo mbVeiculo = MBVeiculo.getInstance();

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNovo = new JButton("Novo");
		btnNovo.setAlignmentY(0.0f);
		btnNovo.setIcon(new ImageIcon("imagens\\8391_16x16.png"));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JButton btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}});
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon("imagens\\8427_16x16.png"));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		final JComboBox cmbDataInicio = new JCalendar();
		final JComboBox cmbDataFinal = new JCalendar();
		final JComboBox<Fornecedor> cmbFornecedor = new JComboBox<Fornecedor>();
		final JComboBox<Marca> cmbMarca = new JComboBox<Marca>();
		final JComboBox<Modelo> cmbModelo = new JComboBox<Modelo>();
		final JComboBox<Motorista> cmbMotorista = new JComboBox<Motorista>();
		final JComboBox<TipoServico> cmbTipoServico = new JComboBox<TipoServico>();
		final JComboBox cmbPlaca = new JComboBox();
		final JComboBox<Unidade> cmbUnidade = new JComboBox<Unidade>();


		cmbUnidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbUnidade.getSelectedIndex() > 0){
					Unidade unidade = mbUnidade.retornarUnidade(cmbUnidade.getSelectedIndex());

					cmbFornecedor.removeAllItems();
					cmbMarca.removeAllItems();
					cmbModelo.removeAllItems();
					cmbMotorista.removeAllItems();
					cmbTipoServico.removeAllItems();
					cmbPlaca.removeAllItems();

					try {

						List<Veiculo> listaVeiculos = mbVeiculo.VeiculosUnidade(unidade);

						List<Servico> listaServico = mbServico.ServicoPorVeiculo(listaVeiculos);

						List<TipoServico> listaTipoServico = mbTipoServico.listarTipoServicoUnidade(listaServico);
						Vector<TipoServico> tipoServico = new Vector<TipoServico>(listaTipoServico);
						cmbTipoServico.setModel(new DefaultComboBoxModel<TipoServico>(tipoServico));
						
	
						List<Modelo> listaModelo = mbModelo.ModeloVeiculo(listaVeiculos);
						Vector<Modelo> modelo = new Vector<Modelo>(listaModelo);
						cmbModelo.setModel(new DefaultComboBoxModel<Modelo>(modelo));

						
						List<Marca> listaMarca = mbMarca.MarcaModelo(listaModelo);
						Vector<Marca> marca = new Vector<Marca>(listaMarca);
						cmbMarca.setModel(new DefaultComboBoxModel<Marca>(marca));


						List<Fornecedor> listaFornecedor = mbFornecedor.FornecedorServico(listaServico);
						Vector<Fornecedor> fornecedor = new Vector<Fornecedor>(listaFornecedor);
						cmbFornecedor.setModel(new DefaultComboBoxModel<Fornecedor>(fornecedor));


						List<Motorista> listaMotorista = mbMotorista.MotoristaServico(listaServico);
						Vector<Motorista> motorista = new Vector<Motorista>(listaMotorista);
						cmbMotorista.setModel(new DefaultComboBoxModel<Motorista>(motorista));



						List<Veiculo> veiculoPlaca = mbVeiculo.VeiculoPlaca(listaVeiculos);
						for(int i=0;i<veiculoPlaca.size();i++){
							cmbPlaca.addItem(veiculoPlaca.get(i).getPlaca());
						}

					} catch (ClassNotFoundException | SQLException e) {

						e.printStackTrace();
					}
					AtualizaCombo(cmbMarca);
					AtualizaCombo(cmbMotorista);
					AtualizaCombo(cmbTipoServico);
					AtualizaCombo(cmbModelo);
					AtualizaCombo(cmbFornecedor);

				}
			}
		});
		cmbTipoServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbTipoServico.getSelectedIndex() > 0){


					cmbFornecedor.removeAllItems();
					cmbMarca.removeAllItems();
					cmbModelo.removeAllItems();
					cmbMotorista.removeAllItems();
					cmbPlaca.removeAllItems();

					try {
						TipoServico tipoServico = mbTipoServico.retornarTipoServico(cmbTipoServico.getSelectedIndex());
						
						
						List<Veiculo> listaVeiculos = ArrayList();
						if(cmbUnidade.getSelectedIndex() > 0){
							Unidade unidade = mbUnidade.retornarUnidade(cmbUnidade.getSelectedIndex());
							listaVeiculos = mbVeiculo.VeiculosUnidade(unidade);	
							
						}else{
							listaVeiculos =  mbVeiculo.listarVeiculos();		
						}
						List<Servico> listaServico = mbServico.ServicoTipoServico(tipoServico);
						listaVeiculos = mbVeiculo.VeiculoTipoServico(listaVeiculos, listaServico);
						
						List<Marca> listaMarca = mbMarca.MarcaModelo(listaServico, tipoServico);
						Vector<Marca> marca = new Vector<Marca>(listaMarca);
						cmbMarca.setModel(new DefaultComboBoxModel<Marca>(marca));					
					
						List<Modelo> listaModelo = mbModelo.ModeloVeiculo(listaVeiculos);
						Vector<Modelo> modelo = new Vector<Modelo>(listaModelo);
						cmbModelo.setModel(new DefaultComboBoxModel<Modelo>(modelo));


						List<Fornecedor> listaFornecedor = mbFornecedor.FornecedorServico(listaServico);
						Vector<Fornecedor> fornecedor = new Vector<Fornecedor>(listaFornecedor);
						cmbFornecedor.setModel(new DefaultComboBoxModel<Fornecedor>(fornecedor));


						List<Motorista> listaMotorista = mbMotorista.MotoristaServico(listaServico);
						Vector<Motorista> motorista = new Vector<Motorista>(listaMotorista);
						cmbMotorista.setModel(new DefaultComboBoxModel<Motorista>(motorista));


						List<Veiculo> veiculoPlaca = mbVeiculo.VeiculoPlaca(listaVeiculos);
						for(int i=0;i<veiculoPlaca.size();i++){
							cmbPlaca.addItem(veiculoPlaca.get(i).getPlaca());
	
						
						}
						AtualizaCombo(cmbMarca);
						AtualizaCombo(cmbMotorista);
		
						AtualizaCombo(cmbModelo);
						AtualizaCombo(cmbFornecedor);

					
					}	 catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}					
				}
			}

			private List<Veiculo> ArrayList() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		cmbMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbMarca.getSelectedIndex() > 0){
					if(cmbUnidade.getSelectedIndex() == 0 && cmbTipoServico.getSelectedIndex() == 0){
						
						cmbFornecedor.removeAllItems();
						cmbModelo.removeAllItems();
						cmbMotorista.removeAllItems();
						cmbPlaca.removeAllItems();
						try {
							Marca marca = mbMarca.retornarMarca(cmbMarca.getSelectedIndex());

							List<Veiculo> listaVeiculos = mbVeiculo.listarVeiculos();		

							List<Servico> listaServico = mbServico.ServicoPorVeiculo(listaVeiculos);
														
							List<Fornecedor> listaFornecedor = mbFornecedor.FornecedorServico(listaServico);
							Vector<Fornecedor> fornecedor = new Vector<Fornecedor>(listaFornecedor);
							cmbFornecedor.setModel(new DefaultComboBoxModel<Fornecedor>(fornecedor));

							List<Modelo> listaModelo = mbModelo.ModeloMarca(marca);
							Vector<Modelo> modelo = new Vector<Modelo>(listaModelo);
							cmbModelo.setModel(new DefaultComboBoxModel<Modelo>(modelo));


							List<Motorista> listaMotorista = mbMotorista.MotoristaServico(listaServico);
							Vector<Motorista> motorista = new Vector<Motorista>(listaMotorista);
							cmbMotorista.setModel(new DefaultComboBoxModel<Motorista>(motorista));

							//List<TipoServico> listaTipoServico = mbTipoServico.listarTipoServicoUnidade(listaServico);

							List<TipoServico> listaTipoServico = mbTipoServico.listarTipoServicos();							
							Vector<TipoServico> VtipoServico = new Vector<TipoServico>(listaTipoServico);
							cmbTipoServico.setModel(new DefaultComboBoxModel<TipoServico>(VtipoServico));

							List<Veiculo> veiculoModelo = mbVeiculo.VeiculoModelo(listaVeiculos, listaModelo);
							for(int i=0;i<veiculoModelo.size();i++){
								cmbPlaca.addItem(veiculoModelo.get(i).getPlaca());
							}	
							
							AtualizaCombo(cmbTipoServico);
							AtualizaCombo(cmbUnidade);
							AtualizaCombo(cmbMotorista);
			
							AtualizaCombo(cmbModelo);
							AtualizaCombo(cmbFornecedor);

						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}					
					}
				}
			}
		});

		cmbModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbModelo.getSelectedIndex() > 0){

					Vector listaMotorista;
					try {
						listaMotorista = new Vector<Servico>(mbServico.listaMotoristaTipoServicoUnidade(cmbUnidade.getSelectedIndex(), cmbTipoServico.getSelectedIndex()));
						cmbMotorista.setModel(new DefaultComboBoxModel<Motorista>(listaMotorista));
						AtualizaCombo(cmbMotorista);
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});		

		try {

			List<Servico> listaPlaca = mbServico.listaServicoPlacaVeiculo();
			for(int i=0;i<listaPlaca.size();i++){
				cmbPlaca.addItem(""+listaPlaca.get(i));

			}
			cmbPlaca.insertItemAt("TODOS", 0);
			cmbPlaca.setSelectedIndex(0);

			cmbFornecedor.setModel(new DefaultComboBoxModel<Fornecedor>(new Vector<Fornecedor>(mbFornecedor.listarFornecedores())));
			cmbTipoServico.setModel(new DefaultComboBoxModel<TipoServico>(new Vector<TipoServico> (mbTipoServico.listarTipoServicos())));
			cmbUnidade.setModel(new DefaultComboBoxModel<Unidade>(new Vector<Unidade>(mbUnidade.listarUnidades())));
			cmbMotorista.setModel(new DefaultComboBoxModel<Motorista>(new Vector<Motorista>(mbMotorista.listarMotoristas())));
			cmbMarca.setModel(new DefaultComboBoxModel<Marca>(new Vector<Marca>(mbMarca.listarMarcas())));
			cmbModelo.setModel(new DefaultComboBoxModel<Modelo>(new Vector<Modelo>(mbModelo.listarModelos())));

			AtualizaCombo(cmbTipoServico);
			AtualizaCombo(cmbUnidade);
			AtualizaCombo(cmbMotorista);
			AtualizaCombo(cmbMarca);
			AtualizaCombo(cmbModelo);
			AtualizaCombo(cmbFornecedor);

		} catch (Exception e) {

		}	



		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idUnidade = 0;
				if (cmbUnidade.getSelectedIndex()		== 0 && 
						cmbTipoServico.getSelectedIndex() 	== 0 &&
						cmbMotorista.getSelectedIndex() 	== 0 &&
						cmbMarca.getSelectedIndex() 		== 0 &&
						cmbModelo.getSelectedIndex() 		== 0){

					try {
						MontaTabela();
					} catch (ClassNotFoundException | SQLException e) {

						e.printStackTrace();
					}
				}					
				int idTipoServico = cmbTipoServico.getItemAt(cmbTipoServico.getSelectedIndex()).getIdtipoServico();
				try {
					if (cmbModelo.getSelectedIndex() == 0 ){
						//se o combo modelo estiver selecionado TODOS
					}

					AtualizaTabela(idUnidade, idTipoServico);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				/*int idMotorista = cmbMotorista.getItemAt(cmbMotorista.getSelectedIndex()).getIdmotorista();



				JOptionPane.showMessageDialog(null, cmbMotorista.getModel().getSelectedItem());
				JOptionPane.showMessageDialog(null, cmbModelo.getModel().getSelectedItem());
				JOptionPane.showMessageDialog(null, cmbPlaca.getModel().getSelectedItem());
				JOptionPane.showMessageDialog(null, cmbUnidade.getModel().getSelectedItem());

				JOptionPane.showMessageDialog(null, (((JCalendar) cmbDataInicio).getText()));
				JOptionPane.showMessageDialog(null, (((JCalendar) cmbDataFinal).getText()));				
				 */
				JOptionPane.showMessageDialog(null, cmbTipoServico.getItemAt(cmbTipoServico.getSelectedIndex()).getIdtipoServico());
			}
		});		



		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(45)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(cmbDataInicio, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(cmbDataFinal, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(cmbUnidade, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
														.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
																.addComponent(cmbMarca, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(cmbModelo, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
																.addGap(39)
																.addComponent(cmbPlaca, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
																.addComponent(cmbMotorista, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(cmbTipoServico, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(cmbFornecedor, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))))
																		.addGroup(groupLayout.createSequentialGroup()
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE))
																				.addGroup(groupLayout.createSequentialGroup()
																						.addGap(237)
																						.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
																						.addGap(18)
																						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addComponent(btnApagar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
																						.addGap(11))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cmbDataInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbDataFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbUnidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(cmbTipoServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(cmbFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(cmbPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(cmbModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(cmbMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(cmbMotorista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(11)
														.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
														.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																.addComponent(btnNovo)
																.addComponent(btnEditar)
																.addComponent(btnApagar))))
																.addContainerGap())
				);

		if (!usuarioLogado.ehAdministrador()){
			btnNovo.setVisible(false);
			btnEditar.setVisible(false);
			btnApagar.setVisible(false);
		}else{
			btnNovo.setVisible(true);
			btnEditar.setVisible(false);
			btnApagar.setVisible(false);
		}

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Servico","Data", "Valor", "Veiculo"
				}
				));
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		setLayout(groupLayout);

			}
			public void MontaTabela() throws ClassNotFoundException, SQLException{
				((DefaultTableModel)table.getModel()).setRowCount(0);

				List<Servico> listaServico= mbServico.listarServicos();

				for (int i=0;i<listaServico.size();i++){
					((DefaultTableModel)table.getModel()).addRow(new String[]{
							listaServico.get(i).getTipoServico()+"", FormataTimestamp(listaServico.get(i).getData2())+"", 
							listaServico.get(i).getValor()+"", listaServico.get(i).getVeiculo().getModelo().getNome()+""});
				}
			}
			public void AtualizaTabela(int unidade, int tipoServico ) throws ClassNotFoundException, SQLException{
				((DefaultTableModel)table.getModel()).setRowCount(0);

				List<Servico> listaServico= mbServico.listarServicos();
				for (int i=0;i<listaServico.size();i++){
					if (listaServico.get(i).getTipoServico().getIdtipoServico() == tipoServico ){
						if (listaServico.get(i).getVeiculo().getUnidade().getIdunidade() == unidade ){

							((DefaultTableModel)table.getModel()).addRow(new String[]{
									listaServico.get(i).getTipoServico()+"", FormataTimestamp(listaServico.get(i).getData2())+"", 
									listaServico.get(i).getValor()+"", listaServico.get(i).getVeiculo().getModelo().getNome()+""});

						}
					}
				}
			}

			public String[] AtualizaCombo( int unidade, JComboBox combo ) throws ClassNotFoundException, SQLException{
				MBVeiculo mbVeiculo = MBVeiculo.getInstance();
				List<Veiculo> listaVeiculo = mbVeiculo.listarVeiculos();
				String[] veiculo = null;
				for(int i=0;i<listaVeiculo.size();i++){
					if(listaVeiculo.get(i).getUnidade().getIdunidade() == unidade){
						veiculo[i] = listaVeiculo.get(i).getModelo().getNome();
					}
				}
				return veiculo;
			}
			private void AtualizaCombo(JComboBox combo){
				((DefaultComboBoxModel)combo.getModel()).insertElementAt("TODOS",0);
				combo.setSelectedIndex(0);
			}	
			private String FormataTimestamp(Timestamp timestamp){
				//retorna String ##/##/####
				String dataServico = timestamp.toString().substring(8, 10)+"/"+timestamp.toString().substring(5, 7)+"/"+timestamp.toString().substring(0, 4);
				return dataServico;
			}

		}


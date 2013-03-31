package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
<<<<<<< HEAD
=======
import javax.swing.JOptionPane;
>>>>>>> origin/master
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;


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
import mb.MBTipoServiçoModelo;
import mb.MBUnidade;
import mb.MBVeiculo;
import util.Util;

import util.JCalendar;
import util.UsuarioUtil;
import javax.swing.JLabel;


public class PanelRelatorioTotalGasto extends PanelExemplo {
	private JTable table;
	final MBFornecedor mbFornecedor = MBFornecedor.getInstance();
	final MBMarca mbMarca = MBMarca.getInstance();
	final MBModelo mbModelo = MBModelo.getInstance();	
	final MBMotorista mbMotorista = MBMotorista.getInstance();
	final MBServico mbServico = MBServico.getInstance();
	final MBTipoServico mbTipoServico = MBTipoServico.getInstance();
	final MBUnidade mbUnidade = MBUnidade.getInstance();
	final MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	final UsuarioUtil usuarioLogado = UsuarioUtil.getInstance();
	final Util util = Util.getInstance();
	final MBTipoServiçoModelo mbTSM = MBTipoServiçoModelo.getInstance();

<<<<<<< HEAD


	boolean flagCMBModelo = false;
	boolean flagCMBMotorista = false;
	boolean flagCMBMarca = false;
	boolean flagCMBTipoServico = false;
	boolean flagCMBUnidade = false;
	boolean flagCMBFornecedor = false;
=======
>>>>>>> origin/master
	int flagCMBDataInicio = 2;
	int flagCMBDataFinal = 2;


	JComboBox<Fornecedor> cmbFornecedor = new JComboBox<Fornecedor>();
	JComboBox<Marca> cmbMarca = new JComboBox<Marca>();
	JComboBox<Modelo> cmbModelo = new JComboBox<Modelo>();
	JComboBox<Motorista> cmbMotorista = new JComboBox<Motorista>();
	JComboBox<TipoServico> cmbTipoServico = new JComboBox<TipoServico>();
	JComboBox cmbPlaca = new JComboBox();
	JComboBox<Unidade> cmbUnidade = new JComboBox<Unidade>();
	JCalendar cmbDataInicio = new JCalendar();
	JCalendar cmbDataFinal = new JCalendar();
	List<Servico> lstServicoData = new ArrayList<Servico>();
	private final JLabel lblInicio = new JLabel("Data Inicial");
	private final JLabel lblFim = new JLabel("Data Final");
	private final JLabel lblUnidade = new JLabel("Unidade");
	private final JLabel lblTipoDeServico = new JLabel("Tipo de Servico");
	private final JLabel lblFornecedor = new JLabel("Fornecedor");
	private final JLabel lblMotorista = new JLabel("Motorista");
	private final JLabel lblMarca = new JLabel("Marca");
	private final JLabel lblModelo = new JLabel("Modelo");
	private final JLabel lblPlaca = new JLabel("Placa");

	public PanelRelatorioTotalGasto() {

<<<<<<< HEAD


		JScrollPane scrollPane = new JScrollPane();

		JButton btnNovo = new JButton("Novo");
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
=======
		JScrollPane scrollPane = new JScrollPane();

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon("imagens\\8391_16x16.png"));
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));
>>>>>>> origin/master

		cmbDataInicio.addActionListener(new ActionListener() { //// nao sei pq ta chamando dois eventos
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDataInicio.getSelectedItem() != null){
					flagCMBDataInicio--; //flag decrementa quando e recebido uma acao no combo
					if(flagCMBDataInicio == 0){
						flagCMBDataInicio = 2;
						if(cmbDataFinal.getSelectedItem() != null){ 
<<<<<<< HEAD
							Date dataInicio = getCMBData(cmbDataInicio);
							Date dataFinal = getCMBData(cmbDataFinal);
=======
							Date dataInicio = util.getCMBData(cmbDataInicio);
							Date dataFinal = util.getCMBData(cmbDataFinal);
>>>>>>> origin/master
							if( dataInicio.getTime() <= dataFinal.getTime()){ //se data inicio for menor que final atualiza combo
								AtualizaComboData();
							}
						}
					}
				}
			}
		});
		cmbDataFinal.addActionListener(new ActionListener() { // nao sei pq ta chamando dois eventos
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDataFinal.getSelectedItem() != null){
					flagCMBDataFinal--; // decrementa quando recebe uma acao no combo
					if(flagCMBDataFinal == 0){
						flagCMBDataFinal = 2; // volta ao valor inicial
						if(cmbDataInicio.getSelectedItem() != null){
<<<<<<< HEAD
							Date dataInicio = getCMBData(cmbDataInicio);
							Date dataFinal = getCMBData(cmbDataFinal);
=======
							Date dataInicio = util.getCMBData(cmbDataInicio);
							Date dataFinal = util.getCMBData(cmbDataFinal);
>>>>>>> origin/master
							if (dataInicio.getTime() <= dataFinal.getTime()){ //se data inicio for menor que data final atualiza combo
								AtualizaComboData();
							}
						}
					}
				}

			}
		});

		cmbUnidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
<<<<<<< HEAD
				if(cmbUnidade.getSelectedIndex() == 0){
=======
				/*if(cmbUnidade.getSelectedIndex() == 0){
>>>>>>> origin/master
					System.out.println("unidade");
					Unidade unidade = null;
					AtualizaComboUnidade(unidade);
				}
				if(cmbUnidade.getSelectedIndex() > 0){
					Unidade unidade = mbUnidade.retornarUnidade(cmbUnidade.getItemAt(cmbUnidade.getSelectedIndex()).getIdunidade());	
					AtualizaComboUnidade(unidade);
<<<<<<< HEAD
				}
=======
				}*/
>>>>>>> origin/master
			}
		});
		cmbTipoServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
<<<<<<< HEAD
				if(cmbTipoServico.getSelectedIndex() == 0){
=======
				/*if(cmbTipoServico.getSelectedIndex() == 0){
>>>>>>> origin/master
					TipoServico tipoServico = null;
					System.out.println("tpse 0 ");
					AtualizaComboTipoServico(tipoServico);

				}
				if(cmbTipoServico.getSelectedIndex() > 0){
					int idTs = cmbTipoServico.getItemAt(cmbTipoServico.getSelectedIndex()).getIdtipoServico();
					TipoServico tipoServico = mbTipoServico.retornarTipoServico(idTs);
					AtualizaComboTipoServico(tipoServico);
<<<<<<< HEAD
				}
=======
				}*/
>>>>>>> origin/master
			}
		});

		cmbFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
<<<<<<< HEAD
				if(cmbFornecedor.getSelectedIndex() > 0){
=======
				/*if(cmbFornecedor.getSelectedIndex() > 0){
>>>>>>> origin/master
					
						Fornecedor fornecedor = mbFornecedor.retornarFornecedor(cmbFornecedor.getItemAt(cmbFornecedor.getSelectedIndex()).getIdfornecedor());
						AtualizaComboFornecedor(fornecedor);
					
<<<<<<< HEAD
				}
=======
				}*/
>>>>>>> origin/master
			}
		});

		cmbMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
<<<<<<< HEAD
				if(cmbMarca.getSelectedIndex() > 0){
					if(cmbUnidade.getSelectedIndex() == 0 
							&& cmbTipoServico.getSelectedIndex() == 0
							&& cmbFornecedor.getSelectedIndex() == 0){
						Marca marca = mbMarca.retornarMarca(cmbMarca.getItemAt(cmbMarca.getSelectedIndex()).getIdmarca());
						AtualizaComboMarca(marca);
					}
				}
=======
				/*if(cmbMarca.getSelectedIndex() > 0){
					if(cmbUnidade.getSelectedIndex() == 0 
							&& cmbTipoServico.getSelectedIndex() == 0
							&& cmbFornecedor.getSelectedIndex() == 0){
						Marca marca = cmbMarca.getItemAt(cmbMarca.getSelectedIndex());
						AtualizaComboMarca(marca);
					}
				}*/
>>>>>>> origin/master
			}
		});

		cmbModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
<<<<<<< HEAD
				if(    cmbModelo.getSelectedIndex()	>0 ) {
					Modelo modelo = mbModelo.retornarModelo(cmbModelo.getItemAt(cmbModelo.getSelectedIndex()).getIdmodelo());
					AtualizaComboModelo(modelo);
				}
=======
>>>>>>> origin/master
			}

		});

		cmbMotorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
<<<<<<< HEAD
				if(cmbMotorista.getSelectedIndex() > 0 && cmbModelo.getSelectedIndex()	== 0	&& cmbTipoServico.getSelectedIndex() == 0
						&& cmbFornecedor.getSelectedIndex() == 0 && cmbMarca.getSelectedIndex() == 0){
					Motorista motorista = mbMotorista.retornarMotorista(cmbMotorista.getItemAt(cmbMotorista.getSelectedIndex()).getIdmotorista());
					AtualizaComboMotorista(motorista);
				}	
=======
				/*if(cmbMotorista.getSelectedIndex() > 0 && cmbModelo.getSelectedIndex()	== 0	&& cmbTipoServico.getSelectedIndex() == 0
						&& cmbFornecedor.getSelectedIndex() == 0 && cmbMarca.getSelectedIndex() == 0){
					Motorista motorista = mbMotorista.retornarMotorista(cmbMotorista.getItemAt(cmbMotorista.getSelectedIndex()).getIdmotorista());
					AtualizaComboMotorista(motorista);
				}*/	
>>>>>>> origin/master
			}
		});


		cmbPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbPlaca.getSelectedIndex() > 0 ){
					AtualizaComboPlaca();
				}
			}
		});		

<<<<<<< HEAD
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					MontaTabela();
=======
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(cmbDataInicio.getSelectedIndex());
				try {
					if ( cmbDataInicio.getSelectedIndex() < 0 || cmbDataFinal.getSelectedIndex() < 0){
						JOptionPane.showMessageDialog(null, "Favor preecher o campo data");
						
					}else{
						gerarConsulta();
					}
>>>>>>> origin/master
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
<<<<<<< HEAD
		});		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
=======
		});

		if (!usuarioLogado.ehAdministrador()){
			btnPesquisar.setVisible(false);
		}else{
			btnPesquisar.setVisible(true);
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
						"Id", "Tipo Servico", "Valor"
				}
				));
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
>>>>>>> origin/master
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
<<<<<<< HEAD
							.addComponent(cmbFornecedor, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(cmbMarca, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addGap(55)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblModelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbModelo, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))))
					.addGap(713))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFornecedor, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(34)
									.addComponent(lblInicio))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(cmbDataInicio, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGap(9)
									.addComponent(cmbDataFinal, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(21)
									.addComponent(lblFim, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addGap(27)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cmbPlaca, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addComponent(cmbUnidade, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblUnidade)
											.addGap(143)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTipoDeServico, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addComponent(cmbTipoServico, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addComponent(cmbMotorista, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblPlaca, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(676, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1083, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(715, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(240)
					.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnApagar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(660, Short.MAX_VALUE))
=======
							.addComponent(lblInicio)
							.addGap(58)
							.addComponent(lblFim, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(lblUnidade)
							.addGap(143)
							.addComponent(lblTipoDeServico, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(240)
							.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cmbFornecedor, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(cmbMarca, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
									.addGap(52)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblModelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(cmbModelo, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(cmbDataInicio, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(cmbDataFinal, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(cmbUnidade, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(cmbTipoServico, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblFornecedor, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
												.addComponent(cmbPlaca, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
												.addComponent(cmbMotorista, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(39, Short.MAX_VALUE))
>>>>>>> origin/master
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
<<<<<<< HEAD
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInicio)
						.addComponent(lblFim)
						.addComponent(lblUnidade)
						.addComponent(lblTipoDeServico))
					.addPreferredGap(ComponentPlacement.RELATED)
=======
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblFim)
							.addComponent(lblInicio))
						.addComponent(lblUnidade)
						.addComponent(lblTipoDeServico))
					.addGap(6)
>>>>>>> origin/master
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbDataInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbDataFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbUnidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbTipoServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
<<<<<<< HEAD
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFornecedor)
						.addComponent(lblMotorista))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbMotorista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMarca)
						.addComponent(lblModelo)
						.addComponent(lblPlaca))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnApagar)
						.addComponent(btnEditar))
					.addGap(56))
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
						"Tipo Servico", "Valor"
				}
				));
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
	}


	@SuppressWarnings("unchecked")
	public void MontaTabela() throws ClassNotFoundException, SQLException{
		Util util = Util.getInstance();

		((DefaultTableModel)table.getModel()).setRowCount(0);

		List<Servico> listaServico = mbServico.findAllServicoPorData(getCMBData(cmbDataInicio), getCMBData(cmbDataFinal));
		List<Servico> servico = new ArrayList<Servico>();
		if(cmbUnidade.getSelectedIndex() == 0){
			totalGasto(listaServico);
		}else{
			Unidade unidade = mbUnidade.retornarUnidade(cmbUnidade.getItemAt(cmbUnidade.getSelectedIndex()).getIdunidade());
			List<Veiculo> listaVeiculos = mbVeiculo.VeiculosUnidade(unidade);
			for(int i=0;i<listaVeiculos.size();i++){
				for(int j=0;j<listaServico.size();j++){
					if(listaVeiculos.get(i).getIdveiculo() == listaServico.get(j).getVeiculo().getIdveiculo()){
						Servico s = mbServico.retornarServico(listaServico.get(j).getIdServico());
						servico.add(s);

					}
				}
			}
			totalGasto(servico);
		}
	}

	public Date getCMBData(JComboBox combo){
		return util.formataData(combo.getSelectedItem().toString());
=======
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFornecedor)
						.addComponent(lblMotorista))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbMotorista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMarca)
								.addComponent(lblPlaca)
								.addComponent(lblModelo))
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cmbMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(cmbPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnPesquisar)
					.addGap(46))
		);
		setLayout(groupLayout);
	}


	private void gerarConsulta() throws ClassNotFoundException, SQLException{
		
		Unidade unidade;
		TipoServico tipoServico;
		Fornecedor fornecedor;
		Motorista motorista;
		Marca marca;
		Modelo modelo;
		
		String placa = cmbPlaca.getSelectedItem().toString();

		List<Veiculo> veiculos;
		List<Servico> listaServico = lstServicoData;
		List<Modelo> listaModelo = mbModelo.listarModelos();	
		
		if ( cmbUnidade.getSelectedIndex() > 0 ){
		
			unidade = cmbUnidade.getItemAt(cmbUnidade.getSelectedIndex());
			veiculos = mbVeiculo.VeiculosUnidade(unidade);
			listaServico = mbServico.ServicoPorVeiculos(veiculos, listaServico);

		}
		
//		Mantem a lista se comboBox estiver selecionado TODOS
		if ( cmbTipoServico.getSelectedIndex() > 0 ){
			
			tipoServico = cmbTipoServico.getItemAt(cmbTipoServico.getSelectedIndex());
			listaServico = mbServico.ServicosTipoServico(tipoServico, listaServico);

		}
		
		if ( cmbFornecedor.getSelectedIndex() > 0 ){

			fornecedor = cmbFornecedor.getItemAt(cmbFornecedor.getSelectedIndex());
			listaServico = mbServico.ServicosPorFornecedor(fornecedor, listaServico);
			
		}
		
		if ( cmbMotorista.getSelectedIndex() > 0 ){

			motorista = cmbMotorista.getItemAt(cmbMotorista.getSelectedIndex());
			listaServico = mbServico.ServicosPorMotorista(motorista, listaServico);
			
		}
		
		if ( cmbMarca.getSelectedIndex() > 0 ){

			marca = cmbMarca.getItemAt(cmbMarca.getSelectedIndex());
			listaModelo = mbModelo.ModeloMarca(marca);
			veiculos = mbVeiculo.VeiculoPorModelos(listaModelo);
			listaServico = mbServico.ServicoPorVeiculos(veiculos, listaServico);			
		
		}
				
		if ( cmbModelo.getSelectedIndex() > 0 ){

			modelo = cmbModelo.getItemAt(cmbModelo.getSelectedIndex());
			veiculos = mbVeiculo.VeiculoPorModelo(modelo);
			listaServico = mbServico.ServicoPorVeiculos(veiculos, listaServico);
		
		}
		
		//if ( placa.toString() != "TODOS" ){
		if ( cmbPlaca.getSelectedIndex() > 0 ){
			veiculos = mbVeiculo.VeiculoPorPlaca(placa);
			listaServico = mbServico.ServicoPorVeiculos(veiculos, listaServico);
		}
		
		atualizarTabela(listaServico);
	}
	
	
	private void atualizarTabela(List<Servico> listaServico) throws ClassNotFoundException, SQLException{
		
		Double valor = 0.0;
		Double total = 0.0;
		List<TipoServico> tipoServico = mbTipoServico.listarTipoServicos();
		
		((DefaultTableModel)table.getModel()).setRowCount(0);
		for(int j=0;j<tipoServico.size();j++){
			for(int k=0;k<listaServico.size();k++){ 
				if(tipoServico.get(j).getIdtipoServico() == listaServico.get(k).getTipoServico().getIdtipoServico()){
					valor = (Double) (listaServico.get(k).getValor() + valor);
				}
			}
			
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					""+tipoServico.get(j).getIdtipoServico() ,tipoServico.get(j).getNome()+"",
					"" + util.retornaMoeda(valor)
			});
			total += valor;
			valor = 0.0;
		}
		((DefaultTableModel)table.getModel()).addRow(new String[]{
				"TOTAL",
				"" + util.retornaMoeda(total)
		});
>>>>>>> origin/master
	}

	public void AtualizaCombo(JComboBox combo, List lista ){
		Vector<Object> modelo = new Vector<Object>(lista);
		combo.setModel(new DefaultComboBoxModel<Object>(modelo));
		((DefaultComboBoxModel)combo.getModel()).insertElementAt("TODOS",0);
		combo.setSelectedIndex(0);
	}

	public void AtualizaCombo(JComboBox combo){
		((DefaultComboBoxModel)combo.getModel()).insertElementAt("Nenhum registro",0);
		combo.setSelectedIndex(0);	
	}

<<<<<<< HEAD
	public String ConverteTimestamp(Timestamp timestamp){
		//retorna String ##/##/####
		String dataServico = timestamp.toString().substring(8, 10)+"/"+timestamp.toString().substring(5, 7)+"/"+timestamp.toString().substring(0, 4);
		return dataServico;
	}

	public void totalGasto (List<Servico> listaServico){
		List<TipoServico> tipoServico = new ArrayList<TipoServico>();

		float total = 0;

		for(int i=0;i<listaServico.size();i++){
			if(cmbTipoServico.getSelectedIndex() == 0){
				TipoServico ts = mbTipoServico.retornarTipoServico(listaServico.get(i).getTipoServico().getIdtipoServico());
				if(!tipoServico.contains(ts)){
					tipoServico.add(ts);
				}
			}else{
				TipoServico ts = mbTipoServico.retornarTipoServico(cmbTipoServico.getItemAt(cmbTipoServico.getSelectedIndex()).getIdtipoServico());
				if(!tipoServico.contains(ts)){
					tipoServico.add(ts);
				}
			}
		}

		if(cmbFornecedor.getSelectedIndex() > 0){
			Fornecedor fornecedor = mbFornecedor.retornarFornecedor(cmbFornecedor.getItemAt(cmbFornecedor.getSelectedIndex()).getIdfornecedor());
			List<Servico> ls = new ArrayList<>();
			for(int i=0;i<listaServico.size();i++){
				if(listaServico.get(i).getFornecedor().getIdfornecedor() == fornecedor.getIdfornecedor()){
					ls.add(listaServico.get(i));
				}
			}
			listaServico.clear();
			for(int j=0;j<ls.size();j++){
				listaServico.add(ls.get(j));
			}
		}

		if(cmbModelo.getSelectedIndex() > 0){
			Modelo modelo = mbModelo.retornarModelo(cmbModelo.getItemAt(cmbModelo.getSelectedIndex()).getIdmodelo());
			List<Veiculo> listaVeiculo = mbVeiculo.ListarosVeiculodoModelo(modelo);
			List<Servico> ls = new ArrayList();
			for(int v=0;v<listaServico.size();v++){
				for(int x=0;x<listaVeiculo.size();x++){
					if(listaServico.get(v).getVeiculo().getIdveiculo() == listaVeiculo.get(x).getIdveiculo()){
						ls.add(listaServico.get(v));
					}
				}
			}
			listaServico.clear();
			for(int z=0;z<ls.size();z++){
				listaServico.add(ls.get(z));
			}
		}
		/*
		 *  Soma total gasto por tipo de servico
		 */

		for(int j=0;j<tipoServico.size();j++){
			for(int k=0;k<listaServico.size();k++){ 
				if(tipoServico.get(j).getIdtipoServico() == listaServico.get(k).getTipoServico().getIdtipoServico()){
					total = (float) (listaServico.get(k).getValor() + total);
				}
			}
			System.out.println(tipoServico.get(j).getNome());
			System.out.println(total);
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					tipoServico.get(j).getNome()+"",
					"R$ "+total
			});
			total = 0;
		}
	}
=======
>>>>>>> origin/master

	public void AtualizaComboData(){
		try {

<<<<<<< HEAD
			System.out.println("atualiza data");

			lstServicoData = mbServico.ServicoPorData( getCMBData(cmbDataInicio), getCMBData(cmbDataFinal));

			if (!(lstServicoData == null)){

				cmbPlaca.removeAllItems();
				for(int i=0;i<lstServicoData.size();i++){
					cmbPlaca.addItem(lstServicoData.get(i).getVeiculo().getPlaca());
=======
			lstServicoData = mbServico.ServicoPorData( util.getCMBData(cmbDataInicio), util.getCMBData(cmbDataFinal));

			if ( lstServicoData != null ){
				
				List<Veiculo> lstVeiculo = new ArrayList<>();
				cmbPlaca.removeAllItems();
				for(int i=0;i<lstServicoData.size();i++){
					if ( !lstVeiculo.contains(lstServicoData.get(i).getVeiculo()) ){
						lstVeiculo.add(lstServicoData.get(i).getVeiculo());
						cmbPlaca.addItem(lstServicoData.get(i).getVeiculo().getPlaca());
						System.out.println(lstServicoData.get(i).getVeiculo().getPlaca());
					}
>>>>>>> origin/master
				}	

				cmbPlaca.insertItemAt("TODOS", 0);
				cmbPlaca.setSelectedIndex(0);

				List<Veiculo> listaVeiculo = mbVeiculo.VeiculoPorServico(lstServicoData);
				List<Unidade> listaUnidade = mbUnidade.UnidadesPorVeiculos(listaVeiculo);
				List<Modelo> listaModelo = mbModelo.ModeloPorVeiculo(listaVeiculo);
				List<Marca> listaMarca = mbMarca.MarcaModelo(listaModelo);
				List<Motorista> listaMotorista = mbMotorista.MotoristaServico(lstServicoData);
				List<TipoServico> listaTipoServico = mbTipoServico.TipoServicoPorServico(lstServicoData);
				List<Fornecedor> listaFornecedor = mbFornecedor.FornecedorServico(lstServicoData);


				cmbUnidade.removeAllItems();
				AtualizaCombo(cmbUnidade, listaUnidade);

				cmbTipoServico.removeAllItems();
				AtualizaCombo(cmbTipoServico, listaTipoServico);

				cmbFornecedor.removeAllItems();
				AtualizaCombo(cmbFornecedor, listaFornecedor);

				cmbMarca.removeAllItems();
				AtualizaCombo(cmbMarca, listaMarca);

				cmbModelo.removeAllItems();
				AtualizaCombo(cmbModelo, listaModelo);

				cmbMotorista.removeAllItems();
				AtualizaCombo(cmbMotorista, listaMotorista);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
	public void AtualizaComboUnidade(Unidade unidade){

		System.out.println("atualiza unidade");
		try {
			List<Servico> listaServico = lstServicoData;
			if(unidade == null){
			
				List<Veiculo> listaVeiculos = mbVeiculo.VeiculoPorServico(listaServico);

			}else{
				
				List<Veiculo> listaVeiculos = mbVeiculo.VeiculosUnidade(unidade);
				listaServico = mbServico.ServicoPorVeiculos(listaVeiculos, lstServicoData);
			}

			List<TipoServico> listaTipoServico = mbTipoServico.TipoServicoPorServico(listaServico);

			List<Modelo> listaModelo = mbModelo.ModeloVeiculo(listaServico);

			List<Marca> listaMarca = mbMarca.MarcaModelo(listaModelo);

			List<Fornecedor> listaFornecedor = mbFornecedor.FornecedorServico(listaServico);

			List<Motorista> listaMotorista = mbMotorista.MotoristaServico(listaServico);

			if(cmbTipoServico.getSelectedIndex() == 0){
				cmbTipoServico.removeAllItems();
				AtualizaCombo(cmbTipoServico, listaTipoServico);
			}
			
			if(cmbFornecedor.getSelectedIndex() == 0){
				cmbFornecedor.removeAllItems();
				AtualizaCombo(cmbFornecedor, listaFornecedor);
			}
			
			if(cmbMarca.getSelectedIndex() == 0){
				cmbMarca.removeAllItems();
				AtualizaCombo(cmbMarca, listaMarca);
			}

			if(cmbModelo.getSelectedIndex() == 0){
				cmbModelo.removeAllItems();
				AtualizaCombo(cmbModelo, listaModelo);
			}

			if(cmbMotorista.getSelectedIndex() == 0){
				cmbMotorista.removeAllItems();
				AtualizaCombo(cmbMotorista, listaMotorista);
			}

			if(cmbPlaca.getSelectedIndex() == 0){
				cmbPlaca.removeAllItems();
				for(int i=0;i<listaServico.size();i++){
					cmbPlaca.addItem(listaServico.get(i).getVeiculo().getPlaca());
				}	
				cmbPlaca.insertItemAt("TODOS", 0);
				cmbPlaca.setSelectedIndex(0);
			}


		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
	public void AtualizaComboTipoServico(TipoServico tipoServico){
		try {

			System.out.println("atualiza tipose");
			List<Veiculo> listaVeiculo = new ArrayList();
			if(cmbUnidade.getSelectedIndex() > 0){
				Unidade unidade = mbUnidade.retornarUnidade(cmbUnidade.getItemAt(cmbUnidade.getSelectedIndex()).getIdunidade());
				listaVeiculo = mbVeiculo.VeiculosUnidade(unidade);	

			}else{
				listaVeiculo =  mbVeiculo.listarVeiculos();		
			}
			
			List<Servico> listaServico = new ArrayList();
			if(tipoServico == null){

				listaServico = mbServico.ServicoPorVeiculos(listaVeiculo, lstServicoData);
			}else{
				
				listaServico = mbServico.ServicosTipoServico(tipoServico, lstServicoData);
				listaServico = mbServico.ServicoPorVeiculos(listaVeiculo, listaServico);
			}
			
			if(listaServico.isEmpty()){
				cmbUnidade.removeAllItems();
				AtualizaCombo(cmbUnidade);

				cmbFornecedor.removeAllItems();
				AtualizaCombo(cmbFornecedor);

				cmbModelo.removeAllItems();
				AtualizaCombo(cmbModelo);

				cmbMarca.removeAllItems();
				AtualizaCombo(cmbMarca);

				cmbMotorista.removeAllItems();
				AtualizaCombo(cmbMotorista);

				cmbPlaca.removeAllItems();
				cmbPlaca.insertItemAt("Nenhum registro", 0);
				cmbPlaca.setSelectedIndex(0);
			}else{

				listaVeiculo  = mbVeiculo.VeiculoPorServico(listaServico);
												
				List<Unidade> listaUnidade = mbUnidade.UnidadesPorVeiculos(listaVeiculo);

				List<Modelo> listaModelo = mbModelo.ModeloPorVeiculo(listaVeiculo);

				List<Fornecedor> listaFornecedor = mbFornecedor.FornecedorServico(listaServico);

				List<Marca> listaMarca = mbMarca.MarcaModelo(listaServico, tipoServico);

				List<Motorista> listaMotorista = mbMotorista.MotoristaServico(listaServico);

				/*if(cmbUnidade.getSelectedIndex() == 0){
				cmbUnidade.removeAllItems();
				AtualizaCombo(cmbUnidade, listaUnidade);
				}*/

				if(cmbFornecedor.getSelectedIndex() == 0){
					cmbFornecedor.removeAllItems();
					AtualizaCombo(cmbFornecedor, listaFornecedor);
				}
				
				if(cmbMarca.getSelectedIndex() == 0){
					cmbMarca.removeAllItems();	
					AtualizaCombo(cmbMarca, listaMarca);

				}

				if(cmbModelo.getSelectedIndex() == 0){
					cmbModelo.removeAllItems();	
					AtualizaCombo(cmbModelo, listaModelo);
				}
				if(cmbMotorista.getSelectedIndex()== 0){
					cmbMotorista.removeAllItems();
					AtualizaCombo(cmbMotorista, listaMotorista);
				}

				if(cmbPlaca.getSelectedIndex() == 0){
					cmbPlaca.removeAllItems();
					for(int i=0;i<listaServico.size();i++){
						cmbPlaca.addItem(listaServico.get(i).getVeiculo().getPlaca());
					}
					cmbPlaca.insertItemAt("TODOS", 0);
					cmbPlaca.setSelectedIndex(0);
				}
			}

		}	 catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}					
	}
	public void AtualizaComboFornecedor(Fornecedor fornecedor){
		

		System.out.println("atualiza Fornecedor");
		try {

			//				List<Servico> listaServico = mbServico.ServicosPorFornecedor(fornecedor, getCMBData(cmbDataInicio), getCMBData(cmbDataFinal));

			List<Servico> listaServico = lstServicoData;

			if( fornecedor != null ){ 
				 listaServico = mbServico.ServicosPorFornecedor(fornecedor, lstServicoData);
			}
			List<Veiculo> listaVeiculos = mbVeiculo.VeiculoPorServico(listaServico);		

			List<Unidade> listaUnidade = mbUnidade.UnidadesPorVeiculos(listaVeiculos);

			List<TipoServico> listaTipoServico = mbTipoServico.TipoServicoPorServico(listaServico);

			List<Modelo> listaModelo = mbModelo.ModeloPorVeiculo(listaVeiculos);

			List<Marca> listaMarca = mbMarca.MarcaModelo(listaModelo);

			List<Motorista> listaMotorista = mbMotorista.MotoristaServico(listaServico);

			/*
			if(cmbUnidade.getSelectedIndex() == 0){
				cmbUnidade.removeAllItems();
				AtualizaCombo(cmbUnidade, listaUnidade);
			}*/
			if(cmbMarca.getSelectedIndex() == 0){
				cmbMarca.removeAllItems();	
				AtualizaCombo(cmbMarca, listaMarca);

			}
			/*if(cmbTipoServico.getSelectedIndex() == 0){
				cmbTipoServico.removeAllItems();
				AtualizaCombo(cmbTipoServico, listaTipoServico);
			}*/

			if(cmbModelo.getSelectedIndex() == 0){
				cmbModelo.removeAllItems();	
				AtualizaCombo(cmbModelo, listaModelo);
			}
			if(cmbMotorista.getSelectedIndex()== 0){
				cmbMotorista.removeAllItems();
				AtualizaCombo(cmbMotorista, listaMotorista);
			}

			if(cmbPlaca.getSelectedIndex() == 0){
				cmbPlaca.removeAllItems();
				for(int i=0;i<listaServico.size();i++){
					cmbPlaca.addItem(listaServico.get(i).getVeiculo().getPlaca());
				}
				cmbPlaca.insertItemAt("TODOS", 0);
				cmbPlaca.setSelectedIndex(0);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}					
	}

	public void AtualizaComboMarca(Marca marca){
		try {
			
			System.out.println("atualiza marca");

			List<Modelo> listaModelo = mbModelo.ModeloMarca(marca);				

			List<Veiculo> listaVeiculos = mbVeiculo.VeiculoPorModelo(listaModelo);		

			//				List<Servico> listaServico = mbServico.ServicoPorVeiculos(listaVeiculos, getCMBData(cmbDataInicio), getCMBData(cmbDataFinal));

			List<Servico> listaServico = mbServico.ServicoPorVeiculos(listaVeiculos, lstServicoData);

			List<Fornecedor> listaFornecedor = mbFornecedor.FornecedorServico(listaServico);

			List<Modelo> listaModeloCmb = mbModelo.ModeloVeiculo(listaServico);

			List<Unidade> listaUnidade = mbUnidade.UnidadesPorVeiculos(listaVeiculos);

			List<TipoServico> listaTipoServico = mbTipoServico.TipoServicoPorServico(listaServico);

			List<Motorista> listaMotorista = mbMotorista.MotoristaServico(listaServico);

			if(cmbModelo.getSelectedIndex() == 0){
				cmbModelo.removeAllItems();
				AtualizaCombo(cmbModelo, listaModeloCmb);	
			}

			if(cmbFornecedor.getSelectedIndex() == 0){
				cmbFornecedor.removeAllItems();
				AtualizaCombo(cmbFornecedor, listaFornecedor);	
			}

			/*
			if(cmbUnidade.getSelectedIndex() == 0){
				cmbUnidade.removeAllItems();
				AtualizaCombo(cmbUnidade, listaUnidade);
			}*/

			if(cmbTipoServico.getSelectedIndex() == 0){
				cmbTipoServico.removeAllItems();
				AtualizaCombo(cmbTipoServico, listaTipoServico);
			}

			if(cmbMotorista.getSelectedIndex() == 0){
				cmbMotorista.removeAllItems();
				AtualizaCombo(cmbMotorista, listaMotorista);
			}
			if(cmbPlaca.getSelectedIndex() == 0){
				cmbPlaca.removeAllItems();
				for(int i=0;i<listaServico.size();i++){
					cmbPlaca.addItem(listaServico.get(i).getVeiculo().getPlaca());
				}	

				cmbPlaca.insertItemAt("TODOS", 0);
				cmbPlaca.setSelectedIndex(0);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}					
	}

	public void AtualizaComboModelo(Modelo modelo){


		System.out.println("atualiza modelo");
		try {

			List<Veiculo> listaVeiculos = mbVeiculo.ListarosVeiculodoModelo(modelo);		

			//				List<Servico> listaServico = mbServico.ServicoPorVeiculos(listaVeiculos, getCMBData(cmbDataInicio), getCMBData(cmbDataFinal));

			List<Servico> listaServico = mbServico.ServicoPorVeiculos(listaVeiculos, lstServicoData);

			listaVeiculos = mbVeiculo.VeiculoPorServico(listaServico);

			List<Fornecedor> listaFornecedor = mbFornecedor.FornecedorServico(listaServico);

			List<Marca> listaMarca = mbMarca.MarcaPorModelo(modelo);

			List<Unidade> listaUnidade = mbUnidade.UnidadesPorVeiculos(listaVeiculos);

			List<TipoServico> listaTipoServico = mbTipoServico.TipoServicoPorServico(listaServico);

			List<Motorista> listaMotorista = mbMotorista.MotoristaServico(listaServico);

			/*
			if(cmbUnidade.getSelectedIndex() == 0){
				cmbUnidade.removeAllItems();
				AtualizaCombo(cmbUnidade, listaUnidade);
			}*/
			System.out.println(modelo.getMarca());
			((DefaultComboBoxModel)cmbMarca.getModel()).setSelectedItem(modelo.getMarca());


			if(cmbTipoServico.getSelectedIndex() == 0){
				cmbTipoServico.removeAllItems();
				AtualizaCombo(cmbTipoServico, listaTipoServico);
			}

			if(cmbMotorista.getSelectedIndex()== 0){
				cmbMotorista.removeAllItems();
				AtualizaCombo(cmbMotorista, listaMotorista);
			}
			if(cmbFornecedor.getSelectedIndex() == 0){
				cmbFornecedor.removeAllItems();
				AtualizaCombo(cmbFornecedor, listaFornecedor);
			}
			if(cmbPlaca.getSelectedIndex() == 0){
				cmbPlaca.removeAllItems();
				for(int i=0;i<listaServico.size();i++){
					cmbPlaca.addItem(listaServico.get(i).getVeiculo().getPlaca());
				}
				cmbPlaca.insertItemAt("TODOS", 0);
				cmbPlaca.setSelectedIndex(0);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}									
	}

	public void AtualizaComboMotorista(Motorista motorista){

		System.out.println("atualiza motorista");
		try {
			cmbUnidade.removeAllItems();
			cmbTipoServico.removeAllItems();
			cmbFornecedor.removeAllItems();
			cmbMarca.removeAllItems();
			cmbPlaca.removeAllItems();

			//				List<Servico> listaServico = mbServico.ServicosPorMotorista(motorista, getCMBData(cmbDataInicio), getCMBData(cmbDataFinal));

			List<Servico> listaServico = mbServico.ServicosPorMotorista(motorista, lstServicoData);

			List<Veiculo> listaVeiculos = mbVeiculo.VeiculoPorServico(listaServico);		

			List<Fornecedor> listaFornecedor = mbFornecedor.FornecedorServico(listaServico);

			List<Unidade> listaUnidade = mbUnidade.UnidadesPorVeiculos(listaVeiculos);

			List<TipoServico> listaTipoServico = mbTipoServico.TipoServicoPorServico(listaServico);

			List<Modelo> listaModelo = mbModelo.ModeloPorVeiculo(listaVeiculos);

			List<Marca> listaMarca = mbMarca.MarcaModelo(listaModelo);

			AtualizaCombo(cmbFornecedor, listaFornecedor);

			AtualizaCombo(cmbMarca, listaMarca);

			AtualizaCombo(cmbUnidade, listaUnidade);

			AtualizaCombo(cmbUnidade, listaUnidade);

			AtualizaCombo(cmbTipoServico, listaTipoServico);

			for(int i=0;i<listaServico.size();i++){
				cmbPlaca.addItem(listaServico.get(i).getVeiculo().getPlaca());
			}	

			cmbPlaca.insertItemAt("TODOS", 0);
			cmbPlaca.setSelectedIndex(0);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}									
	}

	public void AtualizaComboPlaca(){
		

		System.out.println("atualiza pplaca");
		try {

			List<Veiculo> listaVeiculos = mbVeiculo.VeiculoPorPlaca(cmbPlaca.getSelectedItem());

			//				List<Servico> listaServico = mbServico.ServicoPorVeiculos(listaVeiculos, getCMBData(cmbDataInicio), getCMBData(cmbDataFinal));

			List<Servico> listaServico = mbServico.ServicoPorVeiculos(listaVeiculos, lstServicoData);

			List<Fornecedor> listaFornecedor = mbFornecedor.FornecedorServico(listaServico);

			List<Unidade> listaUnidade = mbUnidade.UnidadesPorVeiculos(listaVeiculos);

			List<TipoServico> listaTipoServico = mbTipoServico.TipoServicoPorServico(listaServico);

			List<Modelo> listaModelo = mbModelo.ModeloPorVeiculo(listaVeiculos);

			List<Marca> listaMarca = mbMarca.MarcaModelo(listaModelo);

			List<Motorista> listaMotorista = mbMotorista.MotoristaServico(listaServico);

			for(int i=0;i<listaVeiculos.size();i++){
				((DefaultComboBoxModel)cmbMarca.getModel()).setSelectedItem(listaVeiculos.get(i).getModelo().getMarca());
			}

			for(int i=0;i<listaVeiculos.size();i++){
				((DefaultComboBoxModel)cmbModelo.getModel()).setSelectedItem(listaVeiculos.get(i).getModelo());
			}

			if(cmbFornecedor.getSelectedIndex() == 0){
				AtualizaCombo(cmbFornecedor, listaFornecedor);
			}

			if(cmbUnidade.getSelectedIndex() == 0){
				AtualizaCombo(cmbUnidade, listaUnidade);
			}

			if(cmbTipoServico.getSelectedIndex() == 0){
				AtualizaCombo(cmbTipoServico, listaTipoServico);
			}

			if(cmbMotorista.getSelectedIndex() == 0){
				AtualizaCombo(cmbMotorista, listaMotorista);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}

=======
	
	public void AtualizaComboPlaca(){
		
		List<Veiculo> listaVeiculos;
		try {
			listaVeiculos = mbVeiculo.VeiculoPorServico(lstServicoData);
			String placa = cmbPlaca.getSelectedItem().toString();
			for(int i=0;i<listaVeiculos.size();i++){
				if ( placa == listaVeiculos.get(i).getPlaca()){

					cmbMarca.setSelectedItem(listaVeiculos.get(i).getModelo().getMarca());
					cmbModelo.setSelectedItem(listaVeiculos.get(i).getModelo());
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}
}
>>>>>>> origin/master

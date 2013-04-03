package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
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

		JScrollPane scrollPane = new JScrollPane();

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon("imagens\\8391_16x16.png"));
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		cmbDataInicio.addActionListener(new ActionListener() { //// nao sei pq ta chamando dois eventos
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDataInicio.getSelectedItem() != null){
					flagCMBDataInicio--; //flag decrementa quando e recebido uma acao no combo
					if(flagCMBDataInicio == 0){
						flagCMBDataInicio = 2;
						if(cmbDataFinal.getSelectedItem() != null){ 
							Date dataInicio = util.getCMBData(cmbDataInicio);
							Date dataFinal = util.getCMBData(cmbDataFinal);
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
							Date dataInicio = util.getCMBData(cmbDataInicio);
							Date dataFinal = util.getCMBData(cmbDataFinal);
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
				/*if(cmbUnidade.getSelectedIndex() == 0){
					System.out.println("unidade");
					Unidade unidade = null;
					AtualizaComboUnidade(unidade);
				}
				if(cmbUnidade.getSelectedIndex() > 0){
					Unidade unidade = mbUnidade.retornarUnidade(cmbUnidade.getItemAt(cmbUnidade.getSelectedIndex()).getIdunidade());	
					AtualizaComboUnidade(unidade);
				}*/
			}
		});
		cmbTipoServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*if(cmbTipoServico.getSelectedIndex() == 0){
					TipoServico tipoServico = null;
					System.out.println("tpse 0 ");
					AtualizaComboTipoServico(tipoServico);

				}
				if(cmbTipoServico.getSelectedIndex() > 0){
					int idTs = cmbTipoServico.getItemAt(cmbTipoServico.getSelectedIndex()).getIdtipoServico();
					TipoServico tipoServico = mbTipoServico.retornarTipoServico(idTs);
					AtualizaComboTipoServico(tipoServico);
				}*/
			}
		});

		cmbFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*if(cmbFornecedor.getSelectedIndex() > 0){
					
						Fornecedor fornecedor = mbFornecedor.retornarFornecedor(cmbFornecedor.getItemAt(cmbFornecedor.getSelectedIndex()).getIdfornecedor());
						AtualizaComboFornecedor(fornecedor);
					
				}*/
			}
		});

		cmbMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*if(cmbMarca.getSelectedIndex() > 0){
					if(cmbUnidade.getSelectedIndex() == 0 
							&& cmbTipoServico.getSelectedIndex() == 0
							&& cmbFornecedor.getSelectedIndex() == 0){
						Marca marca = cmbMarca.getItemAt(cmbMarca.getSelectedIndex());
						AtualizaComboMarca(marca);
					}
				}*/
			}
		});

		cmbModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}

		});

		cmbMotorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*if(cmbMotorista.getSelectedIndex() > 0 && cmbModelo.getSelectedIndex()	== 0	&& cmbTipoServico.getSelectedIndex() == 0
						&& cmbFornecedor.getSelectedIndex() == 0 && cmbMarca.getSelectedIndex() == 0){
					Motorista motorista = mbMotorista.retornarMotorista(cmbMotorista.getItemAt(cmbMotorista.getSelectedIndex()).getIdmotorista());
					AtualizaComboMotorista(motorista);
				}*/	
			}
		});


		cmbPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbPlaca.getSelectedIndex() > 0 ){
					AtualizaComboPlaca();
				}
			}
		});		

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(cmbDataInicio.getSelectedIndex());
				try {
					if ( cmbDataInicio.getSelectedIndex() < 0 || cmbDataFinal.getSelectedIndex() < 0){
						JOptionPane.showMessageDialog(null, "Favor preecher o campo data");

					}else{
						gerarConsulta();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblInicio, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addGap(58)
					.addComponent(lblFim, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
					.addGap(37)
					.addComponent(lblUnidade, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
					.addGap(143)
					.addComponent(lblTipoDeServico, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
					.addGap(171))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(cmbDataInicio, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cmbDataFinal, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addGap(6)
					.addComponent(cmbUnidade, 0, 176, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(cmbTipoServico, 0, 229, Short.MAX_VALUE)
					.addGap(28))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblFornecedor, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(236)
					.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(cmbFornecedor, 0, 293, Short.MAX_VALUE)
					.addGap(40)
					.addComponent(cmbMotorista, 0, 290, Short.MAX_VALUE)
					.addGap(28))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblModelo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(218)
					.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(cmbMarca, 0, 159, Short.MAX_VALUE)
					.addGap(52)
					.addComponent(cmbModelo, 0, 253, Short.MAX_VALUE)
					.addGap(62)
					.addComponent(cmbPlaca, 0, 97, Short.MAX_VALUE)
					.addGap(28))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
					.addGap(68))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(240)
					.addComponent(btnPesquisar, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
					.addGap(280))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblFim, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUnidade, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTipoDeServico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(cmbDataFinal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(cmbDataInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(cmbUnidade)
						.addComponent(cmbTipoServico))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFornecedor)
						.addComponent(lblMotorista))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cmbFornecedor)
						.addComponent(cmbMotorista))
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMarca)
						.addComponent(lblModelo)
						.addComponent(lblPlaca))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cmbMarca)
						.addComponent(cmbModelo)
						.addComponent(cmbPlaca))
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnPesquisar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(62))
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


	public void AtualizaComboData(){
		try {

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
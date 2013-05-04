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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import util.Util;

import util.Filtros;
import util.JCalendar;
import util.UsuarioUtil;
import util.totalGastoServicos;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;


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
	static List listaServicos = new ArrayList();

	int flagCMBDataInicio = 2;
	int flagCMBDataFinal = 2;
	boolean flagCMBPlaca = false;
	boolean flagCMBMarca = false;


	JComboBox<Fornecedor> cmbFornecedor = new JComboBox<Fornecedor>();
	JComboBox<Marca> cmbMarca = new JComboBox<Marca>();
	JComboBox<Modelo> cmbModelo = new JComboBox<Modelo>();
	JComboBox<Motorista> cmbMotorista = new JComboBox<Motorista>();
	JComboBox<TipoServico> cmbTipoServico = new JComboBox<TipoServico>();
	JComboBox cmbPlaca = new JComboBox();
	JCalendar cmbDataInicio = new JCalendar();
	JCalendar cmbDataFinal = new JCalendar();
	List<Servico> lstServicoData = new ArrayList<Servico>();
	private final JLabel lblInicio = new JLabel("Data Inicial");
	private final JLabel lblFim = new JLabel("Data Final");
	private final JLabel lblTipoDeServico = new JLabel("Tipo de Servico");
	private final JLabel lblFornecedor = new JLabel("Fornecedor");
	private final JLabel lblMotorista = new JLabel("Motorista");
	private final JLabel lblMarca = new JLabel("Modelo");
	private final JLabel lblModelo = new JLabel("Marca");
	private final JLabel lblPlaca = new JLabel("Placa");
	private JTextField txtTotal;

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

		cmbMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( flagCMBMarca && cmbMarca.getSelectedIndex() == 0 ){
					System.out.println("igual 0 ");
					cmbModelo.setSelectedIndex(0);
					cmbPlaca.setSelectedIndex(0);
				}
				if ( cmbMarca.getSelectedIndex() > 0){
					System.out.println("igual 1");
					flagCMBMarca = true;
				}
			}
		});
		cmbPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbPlaca.getSelectedIndex() > 0 ){
					AtualizaComboPlaca( cmbPlaca.getSelectedIndex() );
					flagCMBPlaca = true;
				}
				if( cmbPlaca.getSelectedIndex() == 0 && flagCMBPlaca ){
					AtualizaComboPlaca( cmbPlaca.getSelectedIndex() );
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
					"Placa", "Od\u00F4metro", "Fornecedor", "Tipo Servico", "Data", "Valor"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(51);
			table.getColumnModel().getColumn(2).setPreferredWidth(170);
			table.getColumnModel().getColumn(3).setPreferredWidth(141);
			table.getColumnModel().getColumn(4).setPreferredWidth(54);
			table.getColumnModel().getColumn(5).setPreferredWidth(83);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		JButton btnImprimirRelatrio = new JButton("Imprimir  Relat\u00F3rio");
		btnImprimirRelatrio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				listaServicos.clear();
				
				for (int i=0;i<table.getRowCount();i++){					
					totalGastoServicos veiculoServico = new totalGastoServicos();
					veiculoServico.setPlaca(table.getValueAt(i, 0).toString());
					veiculoServico.setOdometro(table.getValueAt(i, 1).toString());
					veiculoServico.setFornecedor(table.getValueAt(i, 2).toString());
					veiculoServico.setTipoServico(table.getValueAt(i, 3).toString());
					veiculoServico.setData(table.getValueAt(i, 4).toString());
					veiculoServico.setValor(table.getValueAt(i, 5).toString());
					veiculoServico.setTotal(txtTotal.getText().toString());
					listaServicos.add(veiculoServico);					
				}	
				
				JasperReport report;
				 
				try {
					report = JasperCompileManager.compileReport("relatorios/totalGastoServicos.jrxml");					
					JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(listaServicos));
					JasperViewer jrviewer = new JasperViewer (print,false);
					jrviewer.show();					
					System.out.println("Relatório gerado.");					
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnImprimirRelatrio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtTotal = new JTextField();
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setForeground(Color.RED);
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(cmbDataInicio, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
										.addComponent(lblInicio, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(cmbDataFinal, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
										.addComponent(lblFim, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(cmbPlaca, 0, 79, Short.MAX_VALUE)
										.addComponent(lblPlaca, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(cmbTipoServico, 0, 237, Short.MAX_VALUE)
										.addComponent(lblTipoDeServico, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblModelo, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
										.addComponent(cmbMarca, 0, 67, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblMarca, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cmbModelo, 0, 90, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMotorista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cmbMotorista, 0, 104, Short.MAX_VALUE)))
								.addComponent(cmbFornecedor, 0, 273, Short.MAX_VALUE)
								.addComponent(lblFornecedor, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnImprimirRelatrio)
									.addGap(18)
									.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
									.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))))
					.addGap(1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblFim, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPlaca)
						.addComponent(lblMarca)
						.addComponent(lblModelo)
						.addComponent(lblMotorista))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbDataInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cmbDataFinal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cmbModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbMotorista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDeServico)
						.addComponent(lblFornecedor))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbTipoServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPesquisar)
						.addComponent(btnImprimirRelatrio)
						.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotal))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}


	private void gerarConsulta() throws ClassNotFoundException, SQLException{

		//Unidade unidade;
		TipoServico tipoServico;
		Fornecedor fornecedor;
		Motorista motorista;
		Marca marca;
		Modelo modelo;

		String placa = cmbPlaca.getSelectedItem().toString();

		List<Veiculo> veiculos;
		List<Servico> listaServico = lstServicoData;
		List<Modelo> listaModelo = mbModelo.listarModelos();
		/*
		if ( Filtros.getIdUnidadeSelecionada() > 0 ){

			unidade = mbUnidade.getInstance().retornarUnidade(Filtros.getIdUnidadeSelecionada());
			veiculos = mbVeiculo.VeiculosUnidade(unidade);
			listaServico = mbServico.ServicoPorVeiculos(veiculos, listaServico);

		}*/
		
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

		if ( cmbPlaca.getSelectedIndex() > 0 ){
			veiculos = (List<Veiculo>) mbVeiculo.VeiculoPorPlaca(placa);
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
				if(tipoServico.get(j).getIdtipoServico() == listaServico.get(k).getTipoServico().getIdtipoServico()
						&& listaServico.get(k).getValor() != 0){
					valor = (Double) (listaServico.get(k).getValor() + valor);
					((DefaultTableModel)table.getModel()).addRow(new String[]{
							listaServico.get(k).getVeiculo().getPlaca(),
							listaServico.get(k).getVeiculo().getOdometro()+"",
							listaServico.get(k).getFornecedor().getNome(),
							tipoServico.get(j).getNome()+"",
							listaServico.get(k).getData2().toString().substring(8, 10)+"/"+
							listaServico.get(k).getData2().toString().substring(5, 7)+"/"+
							listaServico.get(k).getData2().toString().substring(0, 4),							
							"" + util.retornaMoeda(valor),							
					});
				}
				total += valor;
				valor = 0.0;
			}			
		}
		txtTotal.setText(util.retornaMoeda(total));		
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


				//cmbUnidade.removeAllItems();
				//AtualizaCombo(cmbUnidade, listaUnidade);

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

	public void AtualizaComboPlaca(int plc){

		List<Veiculo> listaVeiculos;
		try {
			listaVeiculos = mbVeiculo.VeiculoPorServico(lstServicoData);
			String placa = cmbPlaca.getSelectedItem().toString();
			if ( plc == 0 ){
        		cmbMarca.setSelectedIndex(0);
        		cmbModelo.setSelectedIndex(0);
                
            }else{
                for(int i=0;i<listaVeiculos.size();i++){
				    if ( placa == listaVeiculos.get(i).getPlaca()){
    					cmbMarca.setSelectedItem(listaVeiculos.get(i).getModelo().getMarca());
	        			cmbModelo.setSelectedItem(listaVeiculos.get(i).getModelo());
				    }
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

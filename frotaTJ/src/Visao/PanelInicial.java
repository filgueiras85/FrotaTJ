package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;

import dao.Modelo;
import dao.TipoServicoModelo;
import dao.Unidade;
import dao.Veiculo;

import mb.MBModelo;
import mb.MBServico;
import mb.MBTipoServiçoModelo;
import mb.MBUnidade;
import mb.MBVeiculo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class PanelInicial extends PanelExemplo {
	private JTable table;
	private int idVeiculoSelecionado;
	private JTextField textFieldPlaca;
	private JComboBox<String> comboBoxSituacao;
	private JComboBox<String> comboBoxUnidade;
	final MBServico servicoMB = MBServico.getInstance();
	final MBTipoServiçoModelo tipoServicoModeloMB = MBTipoServiçoModelo.getInstance();


	final MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	final MBUnidade mbUnidade = MBUnidade.getInstance();
	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 */
	public PanelInicial(){

		// ----------------------- Lebel e txt ---------------------------\\
		JLabel lblTitulo = new JLabel("Listagem geral de ve\u00EDculos");
		lblTitulo.setIcon(new ImageIcon("imagens\\1519_32x32.png"));
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblSitucao = new JLabel("Situa\u00E7\u00E3o");
		lblSitucao.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));

		textFieldPlaca = new JTextField();
		textFieldPlaca.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();


		//--------------------------- ComboBox ---------------------------\\

		comboBoxSituacao = new JComboBox<String>();
		comboBoxSituacao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSituacao.addItem("Selecionar");
		comboBoxSituacao.addItem("ok");
		comboBoxSituacao.addItem("a fazer");
		comboBoxSituacao.addItem("atrasado");	


		comboBoxUnidade = new JComboBox<String>();
		comboBoxUnidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultComboBoxModel<String> modelComboBoxUnidade;
		final Vector<Unidade> listaUnidade = new Vector<>();
		final Vector<String> listaNomeUnidade = new Vector<>();

		try {
			listaUnidade.addAll(mbUnidade.listarUnidades());

			listaNomeUnidade.add("Selecionar");
			for (int i = 0; i<listaUnidade.size();i++){
				listaNomeUnidade.add(listaUnidade.get(i).getNome());
			}
			modelComboBoxUnidade = new DefaultComboBoxModel<String>(listaNomeUnidade);
			comboBoxUnidade.setModel(modelComboBoxUnidade);
		} catch (Exception e) {
			// TODO: handle exception
		}




		//--------------------------- Botões ---------------------------\\	
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon("imagens\\1408_16x16.png"));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel)table.getModel()).setRowCount(0);				
				ArrayList<Veiculo> listaVeiculo = new ArrayList<>();

				for (int i=0; i<table.getRowCount(); i++){
					((DefaultTableModel)table.getModel()).removeRow(i);
				}

				try {
					listaVeiculo.addAll(mbVeiculo.listarVeiculos());

					for (int i=0; i<listaVeiculo.size()-1; i++){
						if(listaVeiculo.get(i).getPlaca().equals(textFieldPlaca.getText()) &&
								listaVeiculo.get(i).getUnidade().getNome().equals(comboBoxUnidade.getSelectedItem().toString()) &&
								listaVeiculo.get(i).getSituacao().equals(comboBoxSituacao.getSelectedItem().toString())){

							((DefaultTableModel)table.getModel()).addRow(new String[]{
									listaVeiculo.get(i).getIdveiculo()+"", 
									listaVeiculo.get(i).getPlaca(), listaVeiculo.get(i).getUnidade().getNome(),	listaVeiculo.get(i).getMotorista().getNome(), listaVeiculo.get(i).getSituacao()});

							//"ID", "Placa", "Unidade", "Motorista", "Situa\u00E7\u00E3o"

						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JButton btnDetalhes = new JButton("Detalhes");
		btnDetalhes.setIcon(new ImageIcon("imagens\\8390_16x16.png"));
		btnDetalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaDetalhesVeiculo t = new TelaDetalhesVeiculo(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+""));
				t.show();
			}
		});
		btnDetalhes.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JButton btnRelatorioDePendencias = new JButton("Relat\u00F3rio de pend\u00EAncias");
		btnRelatorioDePendencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JasperPrint rel;
				rel = gerar();
				JasperViewer.viewReport(rel, true);
			}
		});
		btnRelatorioDePendencias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDetalhes.setVisible(false);
		btnRelatorioDePendencias.setVisible(false);





		//---------------------------- Codigo do Layout ----------------------------\\
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(lblPlaca)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldPlaca, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
										.addGap(18)
										.addComponent(lblUnidade)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBoxUnidade, 0, 40, Short.MAX_VALUE)
										.addGap(18)
										.addComponent(lblSitucao)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBoxSituacao, 0, 41, Short.MAX_VALUE)
										.addGap(32)
										.addComponent(btnPesquisar))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addComponent(btnRelatorioDePendencias)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnDetalhes))
												.addComponent(lblTitulo))
												.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblTitulo)
						.addGap(24)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPlaca)
								.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUnidade)
								.addComponent(comboBoxUnidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSitucao)
								.addComponent(comboBoxSituacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPesquisar))
								.addGap(18)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnDetalhes)
										.addComponent(btnRelatorioDePendencias))
										.addGap(0))
				);



		//----------------------------- Tabela ------------------------------\\
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idVeiculoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				btnDetalhes.setVisible(true);
				btnRelatorioDePendencias.setVisible(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "Placa", "Odometro", "Situa\u00E7\u00E3o"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(37);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		scrollPane.setViewportView(table);
		atualizaTabela2();
		setLayout(groupLayout);
		TableRowSorter<TableModel> sorter  = new TableRowSorter<TableModel>();	
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String s1, String s2) {
				String[] strings1 = s1.split("\\s");
				String[] strings2 = s2.split("\\s");
				return strings1[strings1.length - 1]
						.compareTo(strings2[strings2.length - 1]);
			}
		};
		table.setRowSorter(sorter);
		table.setAutoCreateRowSorter(true);
	}


	//------------------------------- Métodos -------------------------\\
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		List<Veiculo> listaVeiculo = mbVeiculo.listarVeiculos();
		for (int i=0;i<listaVeiculo.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaVeiculo.get(i).getIdveiculo()+"", listaVeiculo.get(i).getPlaca(), listaVeiculo.get(i).getUnidade().getNome(), listaVeiculo.get(i).getMotorista().getNome(), listaVeiculo.get(i).getSituacao()});
		}
	}
	public  void atualizaTabela2() {
		((DefaultTableModel)table.getModel()).setRowCount(0);

		// pega todos os serviços pendentes de cada veiculo, esse funciona

		try {
			List<Veiculo> veiculos = mbVeiculo.listarVeiculos();
			String dataBR = "";
			/*for (int i1=0;i1<veiculos.size();i1++){
				if(veiculos.get(i1).getSituacao() != "verde"){
					List<TipoServicoModelo> tiposServicosModeloVeiculo = (List<TipoServicoModelo>) tipoServicoModeloMB.atualizaStatusTodosVeiculos(veiculos.get(i1));
					for (int i=0;i<tiposServicosModeloVeiculo.size();i++){
						if (tiposServicosModeloVeiculo.get(i).getSituacao() != "verde"){							
							SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");  				  
							dataBR = out.format( tiposServicosModeloVeiculo.get(i).getDataProximoServico().getTime() );
							String cor = tiposServicosModeloVeiculo.get(i).getSituacao(); 
							((DefaultTableModel)table.getModel()).addRow(new String[] {
									veiculos.get(i1).getIdveiculo()+"",
									veiculos.get(i1).getPlaca(),								
									veiculos.get(i1).getOdometro()+"",
									tiposServicosModeloVeiculo.get(i).getKm()+"", // km do proximo servico
									dataBR, // data proximo servico
									tiposServicosModeloVeiculo.get(i).getTipoServico().getNome(), // servico a fazer
									cor,
									cor //situacao do serviço
							});							
						}						
					}				
				}
			}*/
		System.out.println("verde".compareToIgnoreCase("vermelho"));
		System.out.println("verde".compareToIgnoreCase("amarelo"));

			for (int i1=0;i1<veiculos.size();i1++){
				List<TipoServicoModelo> tiposServicosModeloVeiculo = (List<TipoServicoModelo>) tipoServicoModeloMB.atualizaStatusTodosVeiculos(veiculos.get(i1));

				int i = veiculos.get(i1).getSituacao().compareToIgnoreCase("verde");
				if(i!=0){

							((DefaultTableModel)table.getModel()).addRow(new String[] {
									veiculos.get(i1).getIdveiculo()+"",
									veiculos.get(i1).getPlaca(),								
									veiculos.get(i1).getOdometro()+"",
									veiculos.get(i1).getSituacao()
								
							});							
						}	
				}
									
				
			
			
			
		/* aqui é pra listar somente os veiculos, sem os serviços... nao sei pq mas NAO FUNCIONA DE JEITO NENHUM!!!	
		try {
			MBVeiculo veiculomb2 = MBVeiculo.getInstance();
			List<Veiculo> veiculos = veiculomb2.listarVeiculos();

			for (int i1=0;i1<veiculos.size();i1++){

				//String cor = "vermelho";
				//JOptionPane.showMessageDialog(null, veiculos.get(i1).getSituacao());

				if(veiculos.get(i1).getSituacao() != "verde"){
					String cor = veiculos.get(i1).getSituacao();
					((DefaultTableModel)table.getModel()).addRow(new String[] {
							veiculos.get(i1).getIdveiculo()+"",
							veiculos.get(i1).getPlaca(),								
							veiculos.get(i1).getOdometro()+"",
							"",//tiposServicosModeloVeiculo.get(i).getKm()+"", // km do proximo servico
							"",//dataBR, // data proximo servico
							"",//tiposServicosModeloVeiculo.get(i).getTipoServico().getNome(), // servico a fazer
							cor,
							cor //situacao do serviço
					});
				}
			}*/

			table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {  
				public Component getTableCellRendererComponent(JTable table, Object value,  
						boolean isSelected, boolean hasFocus, int row, int column) {  
					super.getTableCellRendererComponent(table, value, isSelected,  
							hasFocus, row, column);  
					// para definir cores para a linha da tabela de acordo com a situacao do servico
					String cor =  table.getValueAt(row, 3).toString();
					int ama = "verde".compareToIgnoreCase(cor);
					if (ama ==-9) {  
						setBackground(Color.RED);
						setForeground(Color.WHITE);
					} 
					else if (ama ==21) {  
						setBackground(Color.YELLOW);
						setForeground(Color.BLACK);
					} 
					else
					{  
						setBackground(null);
						setForeground(null);
					}	


					return this;  
				}  
			});
			//table.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
			//table.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 ); 

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//----------------- Gerando o Relatório -------------------\\

	public JasperPrint gerar(){
		JasperPrint rel = null;
		try {
			//Connection con = gConexao.getConexao();
			HashMap map = new HashMap();
			String arquivoJasper = "relatorio.jasper"; //Nome do relatorio
			rel = JasperFillManager.fillReport(arquivoJasper, map);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		return rel;
	}
}


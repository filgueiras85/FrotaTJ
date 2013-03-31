package Visao;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.TipoServicoModelo;
import dao.TipoServicoVeiculo;
import dao.Veiculo;

import mb.MBTipoServicoVeiculo;
import mb.MBTipoServiçoModelo;
import mb.MBVeiculo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaDetalhesVeiculo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private int idVeiculoSelecionado;
	
	final MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	final MBTipoServicoVeiculo mbTipoServicoVeiculo = MBTipoServicoVeiculo.getInstance();
	final MBTipoServiçoModelo mbTipoServicoModelo = MBTipoServiçoModelo.getInstance();	
	final MBTipoServiçoModelo tipoServicoModeloMB = MBTipoServiçoModelo.getInstance();

	/**
	 * Create the frame.
	 */
	public TelaDetalhesVeiculo(int idVeiculoSelecionado) {
		setTitle("Informa\u00E7oes do Veiculo ");

		setVisible(true);
		show();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 584);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPlacaDoVeculo = new JLabel("Informa\u00E7\u00F5es do Ve\u00EDculo Selecionado");
		lblPlacaDoVeculo.setIcon(new ImageIcon("imagens\\1519_32x32.png"));
		lblPlacaDoVeculo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
	//----------------------- Botão Relatório -----------------------\\		
		
		JButton btnHistricoDoVeculo = new JButton("Hist\u00F3rico do Ve\u00EDculo");
		btnHistricoDoVeculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JasperPrint rel;
				rel = gerar();
				JasperViewer.viewReport(rel, true);
			}
		});
		
		
	//----------------------- Labels de Informações -----------------------\\	
		Veiculo v = new Veiculo();
		v = mbVeiculo.retornarVeiculo(idVeiculoSelecionado);
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setText("Placa: "+v.getPlaca()); //preenche o label placa com a placa
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblChassi = new JLabel("Chassi:");
		lblChassi.setText("Chassi: "+v.getChassi());
		lblChassi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblRenavan = new JLabel("Renavan:");
		lblRenavan.setText("Renavan: "+v.getrenavan());
		lblRenavan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblOdometro = new JLabel("Odometro:");
		lblOdometro.setText("Odometro: "+v.getOdometro());
		lblOdometro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setText("Modelo: "+v.getModelo().getNome());
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblUnidade = new JLabel("Unidade:");
		lblUnidade.setText("Unidade: "+v.getUnidade().getNome());
		lblUnidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMotorista = new JLabel("Motorista:");
		lblMotorista.setText("Motorista: "+v.getMotorista().getNome());
		lblMotorista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSituacao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituacao.setText("Situa\u00E7\u00E3o: "+v.getSituacao());
		lblSituacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		

		btnHistricoDoVeculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblPlacaDoVeculo)
								.addContainerGap(212, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblOdometro, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
									.addComponent(lblChassi, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
									.addComponent(lblRenavan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
									.addComponent(lblPlaca, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED, 18, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblSituacao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblModelo, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
									.addComponent(lblUnidade, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblMotorista, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
								.addGap(39)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnHistricoDoVeculo)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPlacaDoVeculo)
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPlaca)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblChassi)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRenavan)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOdometro)
								.addComponent(lblSituacao)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblModelo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblUnidade)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblMotorista)))
					.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(btnHistricoDoVeculo)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Servi\u00E7o", "Situa\u00E7\u00E3o", "Km Pr\u00F3ximo servi\u00E7o", "Data Pr\u00F3ximo Servi\u00E7o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(129);
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setPreferredWidth(56);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		atualizaTabela2(idVeiculoSelecionado);
	}
	
	public void atualizarTabela(int idVeiculoSelecionado) throws ClassNotFoundException, SQLException{
		try{
			int odometro = mbVeiculo.retornarVeiculo(idVeiculoSelecionado).getOdometro();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		((DefaultTableModel)table.getModel()).setRowCount(0);
		List<TipoServicoVeiculo> listaTipoServicoVeiculo = mbTipoServicoVeiculo.ListarosTipoServicoVeiculo(idVeiculoSelecionado);
		System.out.println(listaTipoServicoVeiculo);
		System.out.println(idVeiculoSelecionado);
		for (int i=0;i<listaTipoServicoVeiculo.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{listaTipoServicoVeiculo.get(i).getTipoServico().getNome(), listaTipoServicoVeiculo.get(i).getSituacao()});
		}
	}
	public  void atualizaTabela2(int idVeiculo) {
		((DefaultTableModel)table.getModel()).setRowCount(0);
	

		//Servico s1 = new Servico();
		/* Ordem para por na tabela:
		 * id veiculo
		 * placa
		 * odometro veiculo
		 * serviço a fazer
		 * km próximo servico (a fazer)
		 * data próximo serviço (a fazer)
		 * situação (verde, amarelo, vemelho)
		 */

			String dataBR = "";
					Veiculo v = mbVeiculo.retornarVeiculo(idVeiculo);
					List<TipoServicoModelo> tiposServicosModeloVeiculo = (List<TipoServicoModelo>) tipoServicoModeloMB.findTipoServicoByModelo(v);
					for (int i=0;i<tiposServicosModeloVeiculo.size();i++){
												
							SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");  				  
							dataBR = out.format( tiposServicosModeloVeiculo.get(i).getDataProximoServico().getTime() );							
							((DefaultTableModel)table.getModel()).addRow(new String[] {
									tiposServicosModeloVeiculo.get(i).getTipoServico().getNome(), // servico a fazer
									tiposServicosModeloVeiculo.get(i).getSituacao(), //situacao do serviço

									tiposServicosModeloVeiculo.get(i).getKm()+"", // km do proximo servico
									dataBR, // data proximo servico
							});							
						}						
								
				
			
			
			table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {  
				public Component getTableCellRendererComponent(JTable table, Object value,  
						boolean isSelected, boolean hasFocus, int row, int column) {  
					super.getTableCellRendererComponent(table, value, isSelected,  
							hasFocus, row, column);  
					// para definir cores para a linha da tabela de acordo com a situacao do servico
					
						if (table.getValueAt(row, 1) =="vermelho") {  
							setBackground(Color.RED);
							setForeground(Color.WHITE);
						} 
						else if (table.getValueAt(row, 1) =="amarelo") {  
							setBackground(Color.YELLOW);
							setForeground(Color.BLACK);
						} 
						else if(table.getValueAt(row, 1)=="verde") {  
							setBackground(Color.GREEN);
							setForeground(Color.BLACK);
							}
							else{  
							setBackground(null);
							setForeground(null);
						}	
										
											
					return this;  
				}  
			});
			table.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
			table.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 ); 
			

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


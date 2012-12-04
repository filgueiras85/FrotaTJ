package Visao;

import java.awt.BorderLayout;
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
import javax.swing.table.DefaultTableModel;

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
				{null, null},
			},
			new String[] {
				"Servi\u00E7o", "Situa\u00E7\u00E3o"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		try {
			atualizarTabela(v.getIdveiculo());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


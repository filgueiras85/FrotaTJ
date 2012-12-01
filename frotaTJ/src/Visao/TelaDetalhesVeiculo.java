package Visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
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

import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.awt.Toolkit;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\frotaTJ\\imagens\\1519_32x32.png"));

		setVisible(true);
		show();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPlacaDoVeculo = new JLabel("Informa\u00E7\u00F5es do ve\u00EDculo selecionado");
		lblPlacaDoVeculo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPlacaDoVeculo)
							.addContainerGap(162, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblOdometro, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblChassi, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblRenavan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblPlaca, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 18, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblSituacao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblModelo, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
								.addComponent(lblUnidade, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblMotorista, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
							.addGap(39))))
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
					.addGap(89)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Servi\u00E7o", "Situa\u00E7\u00E3o", "Tempo para o limite", "Km para o limite"
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
}

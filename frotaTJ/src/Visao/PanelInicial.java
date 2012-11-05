package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import dao.Modelo;
import dao.Unidade;
import dao.Veiculo;

import mb.MBModelo;
import mb.MBUnidade;
import mb.MBVeiculo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;


public class PanelInicial extends PanelExemplo {
	private JTable table;
	private int idVeiculoSelecionado;
	private JTextField textFieldPlaca;
	private JComboBox<String> comboBoxSituacao;
	private JComboBox<String> comboBoxUnidade;
	
	
	final MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	final MBUnidade mbUnidade = MBUnidade.getInstance();
	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 */
	public PanelInicial(){
		
	// ----------------------- Lebel e txt ---------------------------\\
		JLabel lblTitulo = new JLabel("Listagem geral de ve\u00EDculos");
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
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		

		
		
	//---------------------------- Codigo do Layout ----------------------------\\
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblPlaca)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldPlaca, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblUnidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxUnidade, 0, 83, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblSitucao)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxSituacao, 0, 81, Short.MAX_VALUE)
							.addGap(39)
							.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
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
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		
	//----------------------------- Tabela ------------------------------\\
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idVeiculoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Placa", "Unidade", "Motorista", "Situa\u00E7\u00E3o"
			}
		));
		scrollPane.setViewportView(table);
		try {
			atualizarTabela();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(groupLayout);

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
}


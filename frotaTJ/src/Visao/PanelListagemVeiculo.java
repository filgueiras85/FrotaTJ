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

import mb.MBMotorista;
import mb.MBServico;
import mb.MBUnidade;
import mb.MBVeiculo;

import dao.Motorista;
import dao.Servico;
import dao.Unidade;
import dao.Veiculo;
import dao.VeiculoDAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JComboBox;


public class PanelListagemVeiculo extends PanelExemplo {
	private JTable table;
	private int idVeiculoSelecionado;
	private JTextField textFieldPlaca;
	private JComboBox<String> comboBoxUnidade;
	private JComboBox<String> comboBoxSituacao;
	private JComboBox<Motorista> comboBoxMotorista;

	final MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	final MBUnidade mbUnidade = MBUnidade.getInstance();
	final MBMotorista mbMotorista = MBMotorista.getInstance();
	
	/**
	 * Create the panel.
	 */
	public PanelListagemVeiculo() {		
	// ------------------- Lebel -----------------------\\
		JLabel lblListagemVeiculos = new JLabel("Listagem Veiculos\r\n");
		lblListagemVeiculos.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSituacao = new JLabel("Situa\u00E7\u00E3o");
		lblSituacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMotrista = new JLabel("Motrista");
		lblMotrista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldPlaca = new JTextField();
		textFieldPlaca.setColumns(10);
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
	//------------------------- Botões ----------------------------\\
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
						listaVeiculo.get(i).getSituacao().equals(comboBoxSituacao.getSelectedItem().toString()) && 
						listaVeiculo.get(i).getMotorista().getNome().equals(comboBoxMotorista.getSelectedItem().toString())){

						((DefaultTableModel)table.getModel()).addRow(new String[]{
									listaVeiculo.get(i).getIdveiculo()+"", 
									listaVeiculo.get(i).getPlaca(), listaVeiculo.get(i).getrenavan(), listaVeiculo.get(i).getChassi(),
									listaVeiculo.get(i).getOdometro().toString(), listaVeiculo.get(i).getSituacao(), listaVeiculo.get(i).getModelo().getNome(),
									listaVeiculo.get(i).getUnidade().getNome(),	listaVeiculo.get(i).getMotorista().getNome()});
							
						}
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroVeiculo(0);
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelInicial();
				
			}
		});
		
		
		final JButton btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Veiculo v = mbVeiculo.retornarVeiculo(idVeiculoSelecionado);
					int op = JOptionPane.showConfirmDialog(null,"Deseja realmente apagar o Veículo selecionado?");
					if (op==JOptionPane.YES_OPTION ) {
						
						
						JOptionPane.showMessageDialog(null,mbVeiculo.apagar(v));
						atualizarTabela();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"erro - "+e1);
					// TODO: handle exception
				}
			}
		});
		
		final JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Veiculo v = new Veiculo();
				idVeiculoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				v = mbVeiculo.retornarVeiculo(idVeiculoSelecionado);
				PanelCadastroVeiculo(idVeiculoSelecionado);
			}
		});
		

	//-------------------------- ComboBox -----------------------------------\\
		
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
		
		comboBoxSituacao = new JComboBox<String>();
		comboBoxSituacao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxSituacao.addItem("ok");
		comboBoxSituacao.addItem("a fazer");
		comboBoxSituacao.addItem("atrasado");
		
			
		comboBoxMotorista = new JComboBox<Motorista>();
		comboBoxMotorista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultComboBoxModel<Motorista> modelComboBoxMotorista;
		try {
			modelComboBoxMotorista = new DefaultComboBoxModel<Motorista>(new Vector(mbMotorista.listarMotoristas()));
			comboBoxMotorista.setModel(modelComboBoxMotorista);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	//----------------------------Layout do Panel ----------------------------\\
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnApagar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVoltar))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldPlaca, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxUnidade, 0, 34, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblMotrista, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxMotorista, 0, 34, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblSituacao, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxSituacao, 0, 43, Short.MAX_VALUE)
							.addGap(47)
							.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblListagemVeiculos))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListagemVeiculos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBoxUnidade, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblMotrista, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBoxMotorista, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblSituacao, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBoxSituacao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnEditar)
						.addComponent(btnApagar)
						.addComponent(btnVoltar))
					.addContainerGap())
		);
		
		btnEditar.setVisible(false);
		btnApagar.setVisible(false);
		
	//--------------------------------------Tabela ------------------------------\\
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idVeiculoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				btnEditar.setVisible(true);
				btnApagar.setVisible(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Id", "Placa", "Renavan", "Chassi", "Odometro", "Situa\u00E7\u00E3o", "Modelo", "Unidade", "Motorista"
			}
		));
		scrollPane.setViewportView(table);
		
	//--------------------------------Atualizando a Tabela ---------------------------\\	
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
	
//-----------------------------------Métodos ---------------------------------\\
	public void PanelCadastroVeiculo(int id){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroVeiculo(id);
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroVeiculo(id);
		}
	}
	
	public void PanelInicial(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelInicial();
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelInicial();
		}
	}
	
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		List<Veiculo> listaVeiculo = mbVeiculo.listarVeiculos();
		for (int i=0;i<listaVeiculo.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaVeiculo.get(i).getIdveiculo()+"", 
					listaVeiculo.get(i).getPlaca(), listaVeiculo.get(i).getrenavan(), listaVeiculo.get(i).getChassi(),
					listaVeiculo.get(i).getOdometro().toString(), listaVeiculo.get(i).getSituacao(), listaVeiculo.get(i).getModelo().getNome(),
					listaVeiculo.get(i).getUnidade().getNome(),	listaVeiculo.get(i).getMotorista().getNome()});
		}
	}
	}


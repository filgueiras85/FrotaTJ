package Visao;
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

import mb.MBServico;
import mb.MBVeiculo;

import dao.Servico;
import dao.Veiculo;
import dao.VeiculoDAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JComboBox;


public class PanelListagemVeiculo extends PanelExemplo {
	private JTable table;
	private int idVeiculoSelecionado;
	final MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelListagemVeiculo() {		
	// ------------------- Lebel -----------------------\\
		JLabel lblListagemVeiculos = new JLabel("Listagem Veiculos\r\n");
		lblListagemVeiculos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
	//------------------------- Botões ----------------------------\\
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroVeiculo(0);
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelInicial();
				
			}
		});
		
		
		final JButton btnApagar = new JButton("Apagar");
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
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Veiculo v = new Veiculo();
				idVeiculoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				v = mbVeiculo.retornarVeiculo(idVeiculoSelecionado);
				PanelCadastroVeiculo(idVeiculoSelecionado);
			}
		});
		
		JLabel label = new JLabel("Placa");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("Unidade");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_2 = new JLabel("Situa\u00E7\u00E3o");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton button = new JButton("Pesquisar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_3 = new JLabel("Unidade");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
	//----------------------------Layout do Panel ----------------------------\\
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
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
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, 0, 34, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_2, 0, 34, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, 0, 43, Short.MAX_VALUE)
							.addGap(47)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
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
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnApagar)
						.addComponent(btnNovo)
						.addComponent(btnEditar))
					.addGap(0))
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


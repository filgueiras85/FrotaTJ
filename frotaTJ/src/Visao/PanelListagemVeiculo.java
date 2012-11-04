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


public class PanelListagemVeiculo extends PanelExemplo {
	private JTable table;
	private int idVeiculoSelecionado;
	final MBVeiculo mbVeiculo = MBVeiculo.getInstance();

	/**
	 * Create the panel.
	 */
	public PanelListagemVeiculo() {		
	// ------------------- Lebel -----------------------\\
		JLabel lblListagemVeiculos = new JLabel("Listagem Veiculos\r\n");
		lblListagemVeiculos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
	//------------------------- Bot�es ----------------------------\\
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
					int op = JOptionPane.showConfirmDialog(null,"Deseja realmente apagar o Ve�culo selecionado?");
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
		
		
	//----------------------------Layout do Panel ----------------------------\\
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnApagar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVoltar))
						.addComponent(lblListagemVeiculos))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListagemVeiculos)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnApagar)
						.addComponent(btnNovo)
						.addComponent(btnEditar))
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
	
//-----------------------------------M�todos ---------------------------------\\
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


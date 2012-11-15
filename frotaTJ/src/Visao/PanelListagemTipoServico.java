package Visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

import mb.MBTipoServico;
//import mb.MBUnidade;
import dao.TipoServico;
import javax.swing.ImageIcon;
//import dao.Unidade;

public class PanelListagemTipoServico extends PanelExemplo {
	private int idTipoServicoSelecionado;
	private JTable table;
	MBTipoServico mbTipoServico = MBTipoServico.getInstance();
	
	/**
	 * Create the panel.
	 */
	public PanelListagemTipoServico() {
		JLabel lblListagemTipoServico = new JLabel("Listagem dos Tipo De Servico");
		lblListagemTipoServico.setIcon(new ImageIcon("C:\\Users\\Frankilyn\\Documents\\GitHub\\FrotaTJ\\imagens\\servicos-icone.png"));
		lblListagemTipoServico.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon("C:\\Users\\Frankilyn\\Documents\\GitHub\\FrotaTJ\\imagens\\8391_16x16.png"));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JButton btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon("C:\\Users\\Frankilyn\\Documents\\GitHub\\FrotaTJ\\imagens\\7464_32x32.png"));
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon("C:\\Users\\Frankilyn\\Documents\\GitHub\\FrotaTJ\\imagens\\8427_16x16.png"));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditar.setVisible(false);
		btnApagar.setVisible(false);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Tipo De Servico",
				}
				));
		scrollPane.setViewportView(table);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblListagemTipoServico))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(213, Short.MAX_VALUE)
					.addComponent(btnNovo)
					.addGap(6)
					.addComponent(btnEditar)
					.addGap(6)
					.addComponent(btnApagar)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblListagemTipoServico)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNovo)
						.addComponent(btnEditar)
						.addComponent(btnApagar))
					.addGap(18))
		);
		setLayout(groupLayout);
		
		try {
			atualizarTabela();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				btnEditar.setVisible(true);
				btnApagar.setVisible(true);
			}
		});

		btnNovo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				PanelCadastroTipoServico(0);	
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idTipoServicoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				PanelCadastroTipoServico(idTipoServicoSelecionado);
			}
		});
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TipoServico tipoServico = new TipoServico();
				idTipoServicoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				tipoServico = mbTipoServico.retornarTipoServico(idTipoServicoSelecionado);
				mbTipoServico.apagar(tipoServico);
				try {
					atualizarTabela();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idTipoServicoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				PanelCadastroTipoServico(idTipoServicoSelecionado);
			}
		});
	}

	public void PanelCadastroTipoServico(int idTipoServico){
		try {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroTipoServico(idTipoServico);
		} catch (Exception e) {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroTipoServico(idTipoServico);
		}
	}
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		List<TipoServico> listaTipoServico = mbTipoServico.listarTipoServicos();
		for (int i=0;i<listaTipoServico.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaTipoServico.get(i).getIdtipoServico()+"", listaTipoServico.get(i).getNome()+""
			});
		}
	}
}

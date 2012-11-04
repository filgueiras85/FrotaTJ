package Visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;

import javax.swing.table.DefaultTableModel;

import mb.MBUnidade;


import dao.Unidade;


public class PanelListagemUnidade extends PanelExemplo {
	private int idUnidadeSelecionada;
	private JTable table;
	MBUnidade mbUnidade = MBUnidade.getInstance();
	
	
	public PanelListagemUnidade() {

		JLabel lblListagemUsuario = new JLabel("Listagem das unidades");
		lblListagemUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Nome",
				}
				));
		scrollPane.setViewportView(table);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblListagemUsuario))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(130)
					.addComponent(btnNovo)
					.addGap(6)
					.addComponent(btnEditar)
					.addGap(6)
					.addComponent(btnApagar)
					.addGap(6)
					.addComponent(btnVoltar))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblListagemUsuario)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNovo)
						.addComponent(btnEditar)
						.addComponent(btnApagar)
						.addComponent(btnVoltar)))
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
		
		
		btnNovo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				PanelCadastroUnidade(0);	
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idUnidadeSelecionada = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				PanelCadastroUnidade(idUnidadeSelecionada);
			}
		});
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Unidade unidade = new Unidade();
				idUnidadeSelecionada = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				unidade = mbUnidade.retornarUnidade(idUnidadeSelecionada);
				mbUnidade.apagar(unidade);
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
				idUnidadeSelecionada = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				PanelCadastroUnidade(idUnidadeSelecionada);
			}
		});
	}

	public void PanelCadastroUnidade(int idUnidade){
		try {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroUnidade(idUnidade);
		} catch (Exception e) {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroUnidade(idUnidade);
		}
	}
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		List<Unidade> listaUnidade = mbUnidade.listarUnidades();
		for (int i=0;i<listaUnidade.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaUnidade.get(i).getIdunidade()+"", listaUnidade.get(i).getNome()+""
			});
		}
	}
}

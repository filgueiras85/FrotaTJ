package Visao;
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


import mb.MBUsuario;

import dao.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;


public class PanelListagemUsuario extends PanelExemplo {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelListagemUsuario() {

		JLabel lblListagemUsuario = new JLabel("Listagem Usuarios");
		lblListagemUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroUsuario();
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroUsuario();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addContainerGap()
												.addComponent(btnNovo)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnEditar)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnApagar)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnVoltar))
												.addGroup(groupLayout.createSequentialGroup()
														.addContainerGap()
														.addComponent(lblListagemUsuario)))
														.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblListagemUsuario)
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

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Nome", "Matricula", "Email"
				}
				));
		scrollPane.setViewportView(table);
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

	}

	public void PanelCadastroUsuario(){
		try {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroUsuario();
		} catch (Exception e) {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroUsuario();
		}
	}

	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		MBUsuario mbUsuario = MBUsuario.getInstance();
		List<Usuario> listaUsuario = mbUsuario.findByAll();
		for (int i=0;i<listaUsuario.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaUsuario.get(i).getIdUsuario()+"", listaUsuario.get(i).getNome()+"", listaUsuario.get(i).getMatricula()+"", listaUsuario.get(i).getEmail()+"",
			});
		}
	}





}

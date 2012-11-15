package Visao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import mb.MBUsuario;
import dao.Usuario;
import javax.swing.ImageIcon;



public class PanelListagemUsuario extends PanelExemplo {
	private JTable table;
	private int idUsuarioSelecionado;
	MBUsuario mbUsuario = MBUsuario.getInstance();
	/**
	 * Create the panel.
	 */
	public PanelListagemUsuario() {

		JLabel lblListagemUsuario = new JLabel("Listagem de Usuarios");
		lblListagemUsuario.setIcon(new ImageIcon("C:\\Users\\Frankilyn\\Documents\\GitHub\\FrotaTJ\\imagens\\7837_32x32.png"));
		lblListagemUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));

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

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addGap(18)
							.addComponent(btnEditar)
							.addGap(18)
							.addComponent(btnApagar)
							.addGap(17))
						.addComponent(lblListagemUsuario))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListagemUsuario)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApagar)
						.addComponent(btnEditar)
						.addComponent(btnNovo))
					.addContainerGap())
		);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Nome", "Matricula", "Email", "Tipo Usuario"
				}
				));
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

		try {
			atualizarTabela();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				btnEditar.setVisible(true);
				btnApagar.setVisible(true);
			}
		});

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroUsuario(0);
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idUsuarioSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				PanelCadastroUsuario(idUsuarioSelecionado);
			}
		});
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuario = new Usuario();
				idUsuarioSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				usuario = mbUsuario.retornarUsuario(idUsuarioSelecionado);
				mbUsuario.apagar(usuario);
				try {
					atualizarTabela();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void PanelCadastroUsuario(int idUsuario){
		try {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroUsuario(idUsuario);
		} catch (Exception e) {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroUsuario(idUsuario);
		}
	}

	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		MBUsuario mbUsuario = MBUsuario.getInstance();
		List<Usuario> listaUsuario = mbUsuario.listarUsuarios();
		for (int i=0;i<listaUsuario.size();i++){
			if ( listaUsuario.get(i).getAdministrador() == true ){
				((DefaultTableModel)table.getModel()).addRow(new String[]{
						listaUsuario.get(i).getIdUsuario()+"", listaUsuario.get(i).getNome()+"", 
						listaUsuario.get(i).getMatricula()+"", listaUsuario.get(i).getEmail()+"", "Administrador",
				});
			}else{((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaUsuario.get(i).getIdUsuario()+"", listaUsuario.get(i).getNome()+"", 
					listaUsuario.get(i).getMatricula()+"", listaUsuario.get(i).getEmail()+"", "Usuario",
			});
			}
		}
	}
}

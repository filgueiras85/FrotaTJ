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

import dao.Marca;
import dao.Servico;
import dao.Usuario;

import mb.MBMarca;
import mb.MBUsuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;

import util.SendMail;


public class PanelListagemMarca extends PanelExemplo {
	private JTable table;
	private int idMarcaSelecionado;
	/**
	 * Create the panel.
	 */
	public PanelListagemMarca() {
		
		JLabel lblListagemMarca = new JLabel("Listagem Marca");
		lblListagemMarca.setIcon(new ImageIcon("imagens\\M.jpg"));
		lblListagemMarca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon("imagens\\8391_16x16.png"));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SendMail sendmail = SendMail.getInstance();
				sendmail.EnviarEmail("manoelalbani@gmail.com", "test 2", "Test Mandar msg Sistema");
				Set<Usuario> usuarios = new HashSet<Usuario>(0);
				MBUsuario mbUsuario = MBUsuario.getInstance();
				try {
					List<Usuario> lista = mbUsuario.listarUsuarios();
					
					for(int i=0; i<lista.size();i++){
						usuarios.add(lista.get(i));
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(usuarios.size()+"usuarios");
				

				List<String> listaAnexo = new ArrayList<>();
				listaAnexo.add("imagens\\8427_16x16.png");
				sendmail.EnviarEmailAnexoRelatorio(usuarios, usuarios, usuarios, listaAnexo, "Test Anexo", "Test anexo, encaminhado via Sistema");
				
				PanelCadastroMarca();
			}
		});
		
		
		final JButton btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MBMarca mbMarca = MBMarca.getInstance();
				try {
					Marca m = mbMarca.retornarMarca(idMarcaSelecionado);
					int op = JOptionPane.showConfirmDialog(null,"Deseja realmente apagar o Marca selecionado ?");
					if (op==JOptionPane.YES_OPTION ) {
						
						
						JOptionPane.showMessageDialog(null,mbMarca.apagar(m));
						atualizarTabela();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"erro - "+e);
					// TODO: handle exception
				}
			}
		});
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		final JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon("imagens\\8427_16x16.png"));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelEditarMarca();
			}
		});
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
							.addComponent(btnApagar))
						.addComponent(lblListagemMarca))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListagemMarca)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApagar)
						.addComponent(btnNovo)
						.addComponent(btnEditar))
					.addContainerGap())
		);
		btnEditar.setVisible(false);
		btnApagar.setVisible(false);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idMarcaSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				btnEditar.setVisible(true);
				btnApagar.setVisible(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id","Nome"
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
	public void PanelCadastroMarca(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroMarca(0);
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroMarca(0);
		}
	}
	public void PanelEditarMarca(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroMarca(idMarcaSelecionado);
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroMarca(idMarcaSelecionado);
		}
	}
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		MBMarca mbMarca = MBMarca.getInstance();
		List<Marca> listaMarca = mbMarca.listarMarcas();
		for (int i=0;i<listaMarca.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaMarca.get(i).getIdmarca()+"", listaMarca.get(i).getNome()+""});
		}
	}
	
	}


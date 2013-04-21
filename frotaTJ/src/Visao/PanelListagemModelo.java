package Visao;
import javax.swing.JOptionPane;
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

import mb.MBModelo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;

import util.UsuarioUtil;


public class PanelListagemModelo extends PanelExemplo {
	private JTable table;
	private int idModeloSelecionado;
	/**
	 * Create the panel.
	 */
	public PanelListagemModelo() {
		final UsuarioUtil usuarioLogado = UsuarioUtil.getInstance();

		JLabel lblListagemModelos = new JLabel("Listagem de Modelos");
		lblListagemModelos.setIcon(new ImageIcon("imagens\\1517_32x32.png"));
		lblListagemModelos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon("imagens\\8391_16x16.png"));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroModelo();
			}
		});
		
		
		final JButton btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MBModelo mbModelo = MBModelo.getInstance();
				try {
					Modelo m = mbModelo.retornarModelo(idModeloSelecionado);
					int op = JOptionPane.showConfirmDialog(null,"Deseja realmente apagar o Modelo selecionado ?");
					if (op==JOptionPane.YES_OPTION ) {
						
						
						JOptionPane.showMessageDialog(null,mbModelo.apagar(m));
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
				PanelEditarModelo();
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
						.addComponent(lblListagemModelos))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListagemModelos)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApagar)
						.addComponent(btnNovo)
						.addComponent(btnEditar))
					.addContainerGap())
		);
		if (!usuarioLogado.ehAdministrador()){
			btnNovo.setVisible(false);
			btnEditar.setVisible(false);
			btnApagar.setVisible(false);
		}else{
			btnNovo.setVisible(true);
			btnEditar.setVisible(false);
			btnApagar.setVisible(false);
		}
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(usuarioLogado.ehAdministrador()){
					idModeloSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");

					btnEditar.setVisible(true);
					btnApagar.setVisible(true);
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Marca"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(40);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
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
	public void PanelCadastroModelo(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroModelo(0, "");
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroModelo(0, "");
		}
	}
	public void PanelEditarModelo(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroModelo(idModeloSelecionado, "");
		} catch (Exception e) {
			try {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
				parent.PanelCadastroModelo(idModeloSelecionado, "");
			} catch (Exception e1) {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent().getParent();
				parent.PanelCadastroModelo(idModeloSelecionado, "");
			}
		}
	}
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		MBModelo mbModelo= MBModelo.getInstance();
		List<Modelo> listaModelo = mbModelo.listarModelos();
		for (int i=0;i<listaModelo.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaModelo.get(i).getIdmodelo()+"", listaModelo.get(i).getNome()+"", listaModelo.get(i).getMarca()+""});
		}
	}
	
	}


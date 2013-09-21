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

import dao.Modelo;
import dao.TipoServicoModelo;
import dao.TipoServicoModeloId;

import mb.MBModelo;
import mb.MBTipoServiçoModelo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;

import util.UsuarioUtil;


public class PanelListagemTipoServiçoModelo extends PanelExemplo {
	private JTable table;
	/**
	 * Create the panel.
	 */
	public PanelListagemTipoServiçoModelo() {

		final UsuarioUtil usuarioLogado = UsuarioUtil.getInstance();

		JLabel lblListagemTipoServiçoModelo = new JLabel("Listagem de Tipos Servi\u00E7o por Modelo");
		lblListagemTipoServiçoModelo.setIcon(new ImageIcon("imagens\\11988_32x32.png"));
		lblListagemTipoServiçoModelo.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon("imagens\\8391_16x16.png"));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroTipoServiçoModelo(0,0);
			}
		});


		final JButton btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MBModelo mbModelo = MBModelo.getInstance();
				int idTipoServicoModelo =  Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
			
				String nomeModelo = table.getValueAt(table.getSelectedRow(), 1)+"";
				Modelo modelo = mbModelo.retornarModelo(nomeModelo);
				int idModelo = modelo.getIdmodelo();
				
				MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();
				try {
					TipoServicoModeloId tipo = new TipoServicoModeloId(idTipoServicoModelo, idModelo);
					TipoServicoModelo t = mbTipoServiçoModelo.retornarTipoServicoModelo(tipo);
					int op = JOptionPane.showConfirmDialog(null,"Deseja realmente apagar o Tipo Serviço Modelo selecionado ?");
					if (op==JOptionPane.YES_OPTION ) {


						JOptionPane.showMessageDialog(null,mbTipoServiçoModelo.apagar(t));
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
				MBModelo mbModelo = MBModelo.getInstance();
				int idTipoServicoModelo =  Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
			
				String nomeModelo = table.getValueAt(table.getSelectedRow(), 1)+"";
				Modelo modelo = mbModelo.retornarModelo(nomeModelo);
				int idModelo = modelo.getIdmodelo();

				PanelCadastroTipoServiçoModelo(idModelo, idTipoServicoModelo);
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
										.addComponent(lblListagemTipoServiçoModelo))
										.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblListagemTipoServiçoModelo)
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
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id", "Modelo","Tipo de Serviço", "KM", "Tempo"
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

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {


				if(usuarioLogado.ehAdministrador()){

					/*   String s[] = auxiliar.split("\\ ");  
					       idModelo= Integer.parseInt(s[0]);  
					       idTipoServiço = Integer.parseInt(s[1]);
					 */						btnEditar.setVisible(true);
					 btnApagar.setVisible(true);
				}
			}
		});


	}



	public void PanelCadastroTipoServiçoModelo(int idModelo, int idTipoServiço){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroTipoServiçoModelo(idModelo, idTipoServiço);
		} catch (Exception e) {
			try {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
				parent.PanelCadastroTipoServiçoModelo(idModelo, idTipoServiço);
			} catch (Exception e1) {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent().getParent();
				parent.PanelCadastroTipoServiçoModelo(idModelo, idTipoServiço);
			}
		}
	}/*
	public void PanelCadastroTipoServiçoModelo(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroTipoServiçoModelo(0,0);
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroTipoServiçoModelo(0,0);
			}
	}*/
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		MBTipoServiçoModelo mbTipoServiçoModelo= MBTipoServiçoModelo.getInstance();
		List<TipoServicoModelo> listaTipoServicoModelos= mbTipoServiçoModelo.listarTipoServicosModelos();
		for (int i=0;i<listaTipoServicoModelos.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaTipoServicoModelos.get(i).getId().getTipoServicoIdtipoServico()+"", 
					listaTipoServicoModelos.get(i).getModelo()+"",
					listaTipoServicoModelos.get(i).getTipoServico()+"", 
					listaTipoServicoModelos.get(i).getKm()+"", 
					listaTipoServicoModelos.get(i).getTempo()+""});
		}
	}

}


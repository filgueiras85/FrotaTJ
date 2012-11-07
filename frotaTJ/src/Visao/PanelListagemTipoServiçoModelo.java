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
import mb.MBTipoServi�oModelo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;


public class PanelListagemTipoServi�oModelo extends PanelExemplo {
	private JTable table;
	private int idTipoServi�o;
	private int idModelo;
	/**
	 * Create the panel.
	 */
	public PanelListagemTipoServi�oModelo() {
		
		JLabel lblListagemTipoServi�oModelo = new JLabel("Listagem Tipo Servi\u00E7o Modelo");
		lblListagemTipoServi�oModelo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroTipoServi�oModelo();
			}
		});
		
		
		final JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				MBTipoServi�oModelo mbTipoServi�oModelo = MBTipoServi�oModelo.getInstance();
				try {
					TipoServicoModeloId tipo = new TipoServicoModeloId(new Integer(idTipoServi�o), new Integer(idModelo));
					TipoServicoModelo t = mbTipoServi�oModelo.retornarTipoServicoModelo(tipo);
					int op = JOptionPane.showConfirmDialog(null,"Deseja realmente apagar o Tipo Servi�o Modelo selecionado ?");
					if (op==JOptionPane.YES_OPTION ) {
						
						
						JOptionPane.showMessageDialog(null,mbTipoServi�oModelo.apagar(t));
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
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelEditarTipoServi�oModelo();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(lblListagemTipoServi�oModelo, Alignment.TRAILING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNovo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnApagar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListagemTipoServi�oModelo)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
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
			public void mouseClicked(MouseEvent arg0) {
				MBModelo mbModelo = MBModelo.getInstance();
				String auxiliar = table.getValueAt(table.getSelectedRow(), 0)+"";
				String s[] = auxiliar.split("\\ ");  
			       idModelo= Integer.parseInt(s[0]);  
			       idTipoServi�o = Integer.parseInt(s[1]);
				
				btnApagar.setVisible(true);
				btnEditar.setVisible(true);
			}
		});
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "id", "Modelo","Tipo de Servi�o", "KM", "Tempo"
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
	
	public void PanelEditarTipoServi�oModelo(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroTipoServi�oModelo(idModelo, idTipoServi�o);
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroTipoServi�oModelo(idModelo, idTipoServi�o);
		}
	}
	public void PanelCadastroTipoServi�oModelo(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroTipoServi�oModelo(0,0);
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroTipoServi�oModelo(0,0);
			}
	}
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		MBTipoServi�oModelo mbTipoServi�oModelo= MBTipoServi�oModelo.getInstance();
		List<TipoServicoModelo> listaTipoServicoModelos= mbTipoServi�oModelo.listarTipoServicosModelos();
		for (int i=0;i<listaTipoServicoModelos.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{ listaTipoServicoModelos.get(i).getId().getModeloIdmodelo()+" "+
						listaTipoServicoModelos.get(i).getId().getTipoServicoIdtipoServico(), listaTipoServicoModelos.get(i).getModelo()+"",listaTipoServicoModelos.get(i).getTipoServico()+"", 
					listaTipoServicoModelos.get(i).getKm()+"", listaTipoServicoModelos.get(i).getTempo()+""});
		}
	}
	
	}


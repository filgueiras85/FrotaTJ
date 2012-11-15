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

import mb.MBModelo;
import mb.MBMotorista;
import mb.MBServico;

import dao.Modelo;
import dao.Motorista;
import dao.Servico;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;


public class PanelListagemMotorista extends PanelExemplo {
	private JTable table;
	private int idMotoristaSelecionado;

	/**
	 * Create the panel.
	 */
	public PanelListagemMotorista() {

		JLabel lblListagemMotoristas = new JLabel("Listagem Motoristas");
		lblListagemMotoristas.setIcon(new ImageIcon("C:\\Users\\Frankilyn\\Documents\\GitHub\\FrotaTJ\\imagens\\7133_32x32.png"));
		lblListagemMotoristas.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon("C:\\Users\\Frankilyn\\Documents\\GitHub\\FrotaTJ\\imagens\\8391_16x16.png"));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroMotorista();
			}
		});


		final JButton btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon("C:\\Users\\Frankilyn\\Documents\\GitHub\\FrotaTJ\\imagens\\7464_32x32.png"));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MBMotorista mbMotorista = MBMotorista.getInstance();
				try {
					Motorista m = mbMotorista.retornarMotorista(idMotoristaSelecionado);
					int op = JOptionPane.showConfirmDialog(null,"Deseja realmente apagar o Motorista selecionado?");
					if (op==JOptionPane.YES_OPTION ) {


						JOptionPane.showMessageDialog(null,mbMotorista.apagar(m));
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
		btnEditar.setIcon(new ImageIcon("C:\\Users\\Frankilyn\\Documents\\GitHub\\FrotaTJ\\imagens\\8427_16x16.png"));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelEditarMotorista();
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
										.addComponent(lblListagemMotoristas))
										.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblListagemMotoristas)
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
			public void mouseClicked(MouseEvent e) {
				idMotoristaSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				btnEditar.setVisible(true);
				btnApagar.setVisible(true);
			}
		});

		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Nome", "Matrícula" 
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
	public void PanelCadastroMotorista(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroMotorista(0);
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroMotorista(0);
		}
	}
	public void PanelEditarMotorista(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroMotorista(idMotoristaSelecionado);
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroMotorista(idMotoristaSelecionado);
		}
	}
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		MBMotorista mbMotorista= MBMotorista.getInstance();
		List<Motorista> listaMotorista = mbMotorista.listarMotoristas();
		for (int i=0;i<listaMotorista.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaMotorista.get(i).getIdmotorista()+"", listaMotorista.get(i).getNome()+"", listaMotorista.get(i).getMatricula()+""});
		}
	}
}

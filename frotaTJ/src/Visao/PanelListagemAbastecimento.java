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

import mb.MBAbastecimento;
import mb.MBServico;

import dao.Abastecimento;
import dao.Servico;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PanelListagemAbastecimento extends PanelExemplo {
	private JTable table;
	private int idAbastecimentoSelecionado;;

	/**
	 * Create the panel.
	 */
	public PanelListagemAbastecimento() {

		JLabel lblListagemAbastecimentos = new JLabel("Listagem Abastecimentos");
		lblListagemAbastecimentos.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroServiço();
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				MBAbastecimento mbAbastecimento = MBAbastecimento.getInstance();
				try {
					Abastecimento a = mbAbastecimento.retornarAbastecimento(idAbastecimentoSelecionado);
					int op = JOptionPane.showConfirmDialog(null,"Deseja realmente apagar o Abastecimento selecionado ?");
					if (op==JOptionPane.YES_OPTION ) {


						JOptionPane.showMessageDialog(null,mbAbastecimento.apagar(a));
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
				PanelEditarAbastecimento();
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
														.addComponent(lblListagemAbastecimentos)))
														.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblListagemAbastecimentos)
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
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				idAbastecimentoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1)+"");
				btnEditar.setVisible(true);
				btnApagar.setVisible(true);
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Veiculo", "Hodômetro", "Data"
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
	public void PanelCadastroServiço(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroServiço(0);
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroServiço(0);
		}
	}
	public void PanelEditarAbastecimento(){

		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelCadastroAbastecimento(idAbastecimentoSelecionado);
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelCadastroAbastecimento(idAbastecimentoSelecionado);
		}
	}
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		MBAbastecimento mbAbastecimento= MBAbastecimento.getInstance();
		List<Abastecimento> listaAbastecimento = mbAbastecimento.listarAbastecimentos();
		for (int i=0;i<listaAbastecimento.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaAbastecimento.get(i).getData2().toString().substring(8, 10)+"/"+listaAbastecimento.get(i).getData2().toString().substring(5, 7)+
					"/"+listaAbastecimento.get(i).getData2().toString().substring(0, 4), listaAbastecimento.get(i).getIdabastecimento()+"", 
					listaAbastecimento.get(i).getVeiculo().getPlaca()+""});
		
		}
	}
}

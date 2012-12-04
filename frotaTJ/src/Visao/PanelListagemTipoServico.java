package Visao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

import mb.MBTipoServico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
//import mb.MBUnidade;
import dao.TipoServico;
import javax.swing.ImageIcon;

import util.UsuarioUtil;
//import dao.Unidade;

public class PanelListagemTipoServico extends PanelExemplo {
	private int idTipoServicoSelecionado;
	private JTable table;
	MBTipoServico mbTipoServico = MBTipoServico.getInstance();
	
	/**
	 * Create the panel.
	 */
	public PanelListagemTipoServico() {
		final UsuarioUtil usuarioLogado = UsuarioUtil.getInstance();

		JLabel lblListagemTipoServico = new JLabel("Listagem dos Tipo De Servico");
		lblListagemTipoServico.setIcon(new ImageIcon("imagens\\servicos-icone.png"));
		lblListagemTipoServico.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon("imagens\\8391_16x16.png"));
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JButton btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon("imagens\\8427_16x16.png"));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		final JButton btnRelatrioDeGastos = new JButton("Relat\u00F3rio de gastos");
		btnRelatrioDeGastos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JasperPrint rel;
				rel = gerar();
				JasperViewer.viewReport(rel, true);
			}
		});
		
		if (!usuarioLogado.ehAdministrador()){
			btnNovo.setVisible(false);
			btnEditar.setVisible(false);
			btnApagar.setVisible(false);
		}else{
			btnNovo.setVisible(true);
			btnEditar.setVisible(false);
			btnApagar.setVisible(false);
			btnRelatrioDeGastos.setVisible(false);

		}
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
		

		btnRelatrioDeGastos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(179, Short.MAX_VALUE)
					.addComponent(btnRelatrioDeGastos)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnApagar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListagemTipoServico)
					.addContainerGap(387, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblListagemTipoServico)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApagar)
						.addComponent(btnEditar)
						.addComponent(btnNovo)
						.addComponent(btnRelatrioDeGastos))
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
				if(usuarioLogado.ehAdministrador()){
					btnEditar.setVisible(true);
					btnApagar.setVisible(true);
					btnRelatrioDeGastos.setVisible(true);
				}
				
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
	//----------------- Gerando o Relatório -------------------\\
	
	public JasperPrint gerar(){
		JasperPrint rel = null;
		try {
			//Connection con = gConexao.getConexao();
			HashMap map = new HashMap();
			String arquivoJasper = "relatorio.jasper"; //Nome do relatorio
			rel = JasperFillManager.fillReport(arquivoJasper, map);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		return rel;
	}
}

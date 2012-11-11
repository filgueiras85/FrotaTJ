package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;

import mb.MBMarca;
import mb.MBModelo;
import mb.MBTipoServico;
import mb.MBTipoServi�oModelo;

import dao.Marca;
import dao.Modelo;
import dao.TipoServico;
import dao.TipoServicoModelo;
import dao.TipoServicoModeloDAO;
import dao.TipoServicoModeloId;


public class PanelCadastroTipoServi�oModelo extends PanelExemplo {
	private JComboBox<Modelo> comboBoxModelo;
	private JComboBox<TipoServico> comboBoxTipoServi�o;
	private String winDir= ("c:\\frotaTJ\\imagens\\");
	private JTextField textFieldKM;
	private JTextField textFieldData;
	private JLabel lblModelo;
	/**
	 * Create the panel.
	 */
	public PanelCadastroTipoServi�oModelo(final int idModeloSelecionado, final int idTipoServi�oselecionado) {
		
		JLabel lblCadastroTipoServicoModelo = new JLabel("  Cadastro Tipo Servi\u00E7o - Modelo");
		lblCadastroTipoServicoModelo.setIcon(new ImageIcon(winDir+"1517_32x32.png"));
		lblCadastroTipoServicoModelo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(winDir+"7464_32x32.png"));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemTipoServi�oModelo();
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(winDir+"7484_32x32.png"));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MBTipoServico mbTipoServico = MBTipoServico.getInstance();
				TipoServicoModeloId tipoServicoModeloId = new TipoServicoModeloId(new Integer(comboBoxTipoServi�o.getItemAt(comboBoxTipoServi�o.getSelectedIndex()).getIdtipoServico()), 
																					new Integer(comboBoxModelo.getItemAt(comboBoxModelo.getSelectedIndex()).getIdmodelo()));
				MBModelo mbModelo = MBModelo.getInstance();
				MBTipoServi�oModelo mbTipoServi�oModelo = MBTipoServi�oModelo.getInstance();
				TipoServicoModelo t = new TipoServicoModelo(tipoServicoModeloId, mbModelo.retornarModelo(comboBoxModelo.getItemAt(comboBoxModelo.getSelectedIndex()).getIdmodelo()), mbTipoServico.retornarTipoServico(comboBoxTipoServi�o.getItemAt(comboBoxTipoServi�o.getSelectedIndex()).getIdtipoServico()), Integer.parseInt(textFieldKM.getText()), Integer.parseInt(textFieldData.getText()));

					try {
						if (idModeloSelecionado==0 || idTipoServi�oselecionado == 0){
							if (t.getId().getModeloIdmodelo()==0 || t.getId().getTipoServicoIdtipoServico()==0){
								
								t.setId(tipoServicoModeloId);
							}
							
							String retorno = mbTipoServi�oModelo.inserir(t);
							if (retorno.equals("ok")){
								
								JOptionPane.showMessageDialog(null,"Inserido!");
								PanelListagemTipoServi�oModelo();
							}else{
								JOptionPane.showMessageDialog(null,retorno);
							}
						}else{
							
							String retorno =  mbTipoServi�oModelo.editar(t);
							if (retorno.equals("ok")){
								JOptionPane.showMessageDialog(null,"Alterado!");
								PanelListagemTipoServi�oModelo();
							}else{
								JOptionPane.showMessageDialog(null,retorno);
							}
					}}
						 catch (Exception e) {
						// TODO: handle exception
					}
					
					
					
		
				}
				
				}
				
			
		);
		
		JLabel lblTipoServi�o = new JLabel("Tipo Servi\u00E7o");
		lblTipoServi�o.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		MBModelo mbModelo = MBModelo.getInstance();
		comboBoxModelo = new JComboBox<Modelo>();
		DefaultComboBoxModel<Modelo> modeloComboBox;
		
			try {
				modeloComboBox = new DefaultComboBoxModel<Modelo>(new Vector(mbModelo.listarModelos()));
				comboBoxModelo.setModel(modeloComboBox);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		
		//DefaultComboBoxModel<Fornecedor>(mbFornecedor.listarFornecedores());
		comboBoxModelo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		MBTipoServico mbTipoServico = MBTipoServico.getInstance();
		comboBoxTipoServi�o = new JComboBox<TipoServico>();
		DefaultComboBoxModel<TipoServico> modeloComboBoxTipoServi�o;
		
		try {
			modeloComboBoxTipoServi�o = new DefaultComboBoxModel<TipoServico>(new Vector(mbTipoServico.listarTipoServicos()));
			comboBoxTipoServi�o.setModel(modeloComboBoxTipoServi�o);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboBoxTipoServi�o.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel labelKm = new JLabel("KM");
		labelKm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textFieldKM = new JTextField();
		textFieldKM.setColumns(10);
		
		JLabel labelData = new JLabel("Tempo");
		labelData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textFieldData = new JTextField();
		textFieldData.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTipoServi�o)
										.addComponent(lblModelo)
										.addComponent(labelKm, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
									.addGap(18))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(labelData, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
									.addGap(55)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldKM, 429, 429, 429)
								.addComponent(comboBoxModelo, 0, 429, Short.MAX_VALUE)
								.addComponent(comboBoxTipoServi�o, 0, 429, Short.MAX_VALUE)
								.addComponent(textFieldData)))
						.addComponent(lblCadastroTipoServicoModelo))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroTipoServicoModelo)
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTipoServi�o)
						.addComponent(comboBoxTipoServi�o, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblModelo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelKm, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldKM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelData, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(31))
		);
		setLayout(groupLayout);
		if (idModeloSelecionado>0 || idTipoServi�oselecionado>0){
			MBTipoServi�oModelo mbt = MBTipoServi�oModelo.getInstance();
			
			try {
				TipoServicoModeloId id = new TipoServicoModeloId(new Integer(idTipoServi�oselecionado), new Integer(idModeloSelecionado));
				TipoServicoModelo t = mbt.retornarTipoServicoModelo(id);
				textFieldData.setText(t.getTempo().toString());
				textFieldKM.setText(t.getKm().toString());
					
				boolean aux = false ;
				int  i=0; 
					
				while(aux==false){
						aux= mbModelo.listarModelos().get(i).getIdmodelo()==t.getModelo().getIdmodelo();
				   		if (aux==true) break; 
				   		i++;
						
					}
					comboBoxModelo.setSelectedIndex(i);
				i=0;
				aux = false;
				while(aux==false){
						aux= mbTipoServico.listarTipoServicos().get(i).equals(t.getTipoServico());
				   		if (aux==true) break; 
				   		i++;
						
					}
					comboBoxTipoServi�o.setSelectedIndex(i);
								
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"erro - "+e);
				// TODO: handle exception
			}
			
		}
		
	}
	
	public void PanelListagemTipoServi�oModelo(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemTipoServi�oModelo();
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemTipoServi�oModelo();
		}
	}
}


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

import dao.Marca;
import dao.Modelo;


public class PanelCadastroModelo extends PanelExemplo {
	private JTextField textFieldNome;
	private JComboBox<Marca> comboBoxMarca;
	private String winDir= ("imagens\\");
	/**
	 * Create the panel.
	 */
	public PanelCadastroModelo(final int idModeloSelecionado) {

		JLabel lblCadastroModelo = new JLabel("  Cadastro de Modelos");
		lblCadastroModelo.setIcon(new ImageIcon(winDir+"1517_32x32.png"));
		lblCadastroModelo.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(winDir+"7464_32x32.png"));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemModelo();
			}
		});

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(winDir+"7484_16x16.png"));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MBMarca mbMarca= MBMarca.getInstance();

				MBModelo mbModelo = MBModelo.getInstance();
				Modelo m =  new Modelo(new Integer(idModeloSelecionado),mbMarca.retornarMarca(comboBoxMarca.getItemAt(comboBoxMarca.getSelectedIndex()).getIdmarca()), textFieldNome.getText());

				try {
					if (idModeloSelecionado==0){
						if (m.getIdmodelo()==0){
							m.setIdmodelo(null);
						}
						String retorno = mbModelo.inserir(m);
						if (retorno.equals("ok")){

							JOptionPane.showMessageDialog(null,"Produto inserido!");

						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}else{

						String retorno =  mbModelo.editar(m);
						if (retorno.equals("ok")){
							JOptionPane.showMessageDialog(null,"Produto alterado!");

						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				PanelListagemModelo();

			}

		});

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldNome.setColumns(10);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 17));

		MBMarca mbMarca = MBMarca.getInstance();
		comboBoxMarca = new JComboBox<Marca>();
		DefaultComboBoxModel<Marca> modeloComboBox;

		try {
			modeloComboBox = new DefaultComboBoxModel<Marca>(new Vector(mbMarca.listarMarcas()));
			comboBoxMarca.setModel(modeloComboBox);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		//DefaultComboBoxModel<Fornecedor>(mbFornecedor.listarFornecedores());
		comboBoxMarca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addComponent(lblNome)
										.addGap(18)
										.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnCancelar))
												.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
														.addComponent(lblMarca)
														.addGap(18)
														.addComponent(comboBoxMarca, 0, 367, Short.MAX_VALUE))
														.addComponent(lblCadastroModelo))
														.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblCadastroModelo)
						.addGap(61)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBoxMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMarca))
										.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnCancelar)
												.addComponent(btnSalvar))
												.addContainerGap())
				);
		setLayout(groupLayout);
		if (idModeloSelecionado>0){
			MBModelo mbModelo = MBModelo.getInstance();

			try {
				Modelo m = mbModelo.retornarModelo(idModeloSelecionado);
				textFieldNome.setText(m.getNome());

				boolean aux = false ;
				int  i=0; 

				while(aux==false){
					aux= mbMarca.listarMarcas().get(i).equals(m.getMarca());
					if (aux==true) break; 
					i++;

				}
				comboBoxMarca.setSelectedIndex(i);



			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"erro - "+e);
				// TODO: handle exception
			}

		}
	}

	public void PanelListagemModelo(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemModelo();
		} catch (Exception e) {
			try {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
				parent.PanelListagemModelo();
			} catch (Exception e1) {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent().getParent();
				parent.PanelListagemModelo();
			}
		}
	}
}


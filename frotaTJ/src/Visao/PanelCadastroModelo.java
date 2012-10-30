package Visao;
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
import javax.swing.ImageIcon;


public class PanelCadastroModelo extends PanelExemplo {
	private JTextField textFieldNome;

	/**
	 * Create the panel.
	 */
	public PanelCadastroModelo() {
		
		JLabel lblCadastroModelo = new JLabel("  Cadastro Modelo");
		lblCadastroModelo.setIcon(new ImageIcon("C:\\Program Files\\Sistema de Manuten\u00E7\u00E3o de Frota TJSC\\imagens\\1517_32x32.png"));
		lblCadastroModelo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("C:\\Program Files\\Sistema de Manuten\u00E7\u00E3o de Frota TJSC\\imagens\\7464_32x32.png"));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemModelo();
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("C:\\Program Files\\Sistema de Manuten\u00E7\u00E3o de Frota TJSC\\imagens\\7484_16x16.png"));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		JComboBox comboBoxMarca = new JComboBox();
		comboBoxMarca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCadastroModelo)
							.addGap(281))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNome)
							.addGap(18)
							.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMarca)
							.addGap(18)
							.addComponent(comboBoxMarca, 0, 367, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblCadastroModelo)
					.addGap(46)
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
					.addGap(27))
		);
		setLayout(groupLayout);

	}
	public void PanelListagemModelo(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemModelo();
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemModelo();
		}
	}
}


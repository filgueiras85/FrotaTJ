package Visao;

import java.awt.Font;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;


import mb.MBUsuario;

import dao.Usuario;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelLogin extends PanelExemplo {
	private JPasswordField senhaUsuario;

	/**
	 * Create the panel.
	 */
	public PanelLogin() {
		final MBUsuario mbUsuario = MBUsuario.getInstance();
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		senhaUsuario = new JPasswordField();
		senhaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		add(senhaUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		


		final JComboBox<String> cmbUsuario = new JComboBox<String>();
		cmbUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final Vector<Usuario> listaUsuario = new Vector<>();
		Vector<String> listaNomeUsuario = new Vector<>();
		
		try{
			listaUsuario.addAll(mbUsuario.listarUsuarios());
			for (int i=0;i<listaUsuario.size();i++){
				listaNomeUsuario.add(listaUsuario.get(i).getNome());
			}
			DefaultComboBoxModel<String> modelCmbUsuario = new DefaultComboBoxModel<String>(listaNomeUsuario);
			cmbUsuario.setModel(modelCmbUsuario);
			
		}catch (Exception e){
			
		}
		
		senhaUsuario.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if ( senhaUsuario.getPassword().length >= 6) {
					e.consume();
				}
			}
		});
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuario = mbUsuario.retornarUsuario(listaUsuario.get(cmbUsuario.getSelectedIndex()).getIdUsuario());
				String senha = new String(senhaUsuario.getPassword());
				if ( senha.equals(usuario.getSenha())){
					JOptionPane.showMessageDialog(null, "Deuboa");
				}else{
					JOptionPane.showMessageDialog(null, "Senha incorreta");
				}
			}
		});
		
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnEntrar)
							.addGap(48)
							.addComponent(btnCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblSenha))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(senhaUsuario, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
									.addGap(171))
								.addComponent(cmbUsuario, 0, 203, Short.MAX_VALUE))))
					.addGap(78))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(301))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(lblLogin)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(cmbUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(senhaUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnEntrar))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}

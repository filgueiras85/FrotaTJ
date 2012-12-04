package Visao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

import mb.MBUsuario;
import dao.Usuario;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class PanelCadastroUsuario extends PanelExemplo {
	private JTextField txtNome;
	private JTextField txtMatricula;
	private JTextField txtEmail;

	private boolean tipoUsuario;	 

	public PanelCadastroUsuario(final int idUsuarioSelecionado) {
		final MBUsuario mbUsuario = MBUsuario.getInstance();
		txtNome = new JTextField();
		txtNome.setColumns(10);

		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				 if ((txtEmail.getText().contains("@")) &&
						 (txtEmail.getText().contains(".")) &&
						 (!txtEmail.getText().contains(" "))) {
						  
						 String usuario = new String(txtEmail.getText().substring(0,
						 txtEmail.getText().lastIndexOf('@')));
						  
						             String dominio = new String(txtEmail.getText().substring(txtEmail.getText().lastIndexOf
						 ('@') + 1, txtEmail.getText().length()));
						  
						             if ((usuario.length() >=1) && (!usuario.contains("@")) &&
						 (dominio.contains(".")) && (!dominio.contains("@")) && (dominio.indexOf(".") >=
						 1) && (dominio.lastIndexOf(".") < dominio.length() - 1)) {
						  
						             
						  
						             } else {
						  
						                 txtEmail.setText("");
						  
						                 txtEmail.requestFocus();
						  
						             }
						  
						         } else {
						  
						             txtEmail.setText("");
						  
						             txtEmail.requestFocus();
						  
						         }


			}
		});
		txtEmail.setColumns(10);
		
		JLabel lblCadastroDeUsuario = new JLabel("Cadastro de Usuarios");
		lblCadastroDeUsuario.setIcon(new ImageIcon("imagens\\7818_32x32.png"));
		lblCadastroDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		final JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JRadioButton rdbtnUsuario = new JRadioButton("Usu\u00E1rio");
		rdbtnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("imagens\\7484_16x16.png"));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		add(rdbtnUsuario);
		add(rdbtnAdministrador);
		add(txtMatricula);
		add(txtNome);
		add(txtEmail);
		add(btnSalvar);
		add(btnCancelar);

		final JPasswordField senhaUsuario = new JPasswordField();

		add(senhaUsuario);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblNome);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblSenha);

		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblMatricula);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblEmail);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCadastroDeUsuario)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(senhaUsuario, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMatricula, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtMatricula, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(rdbtnAdministrador, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(rdbtnUsuario, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnSalvar)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnCancelar)
											.addGap(8))))
								.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroDeUsuario)
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(senhaUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMatricula)
						.addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnAdministrador)
						.addComponent(rdbtnUsuario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addGap(57))
		);
		setLayout(groupLayout);
		
		/*
		 * Inicia tela com o Usuario selecionado
		 */
		if (idUsuarioSelecionado == 0)
			rdbtnUsuario.setSelected(true);

		
		if (idUsuarioSelecionado>0){
			try {
				Usuario usuario = new Usuario();
				usuario = mbUsuario	.retornarUsuario(idUsuarioSelecionado);
				txtNome.setText(usuario.getNome());
				txtEmail.setText(usuario.getEmail());
				txtMatricula.setText(usuario.getMatricula());
				senhaUsuario.setText(usuario.getSenha());
				if (usuario.getAdministrador() == true){
					rdbtnAdministrador.setSelected(true);
				}else if (usuario.getAdministrador() == false){
					rdbtnUsuario.setSelected(true);
				}
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "erro -" +e);
			}
		}
		
		/*
		 * Limita o numero de caracteres da senha para 6
		 */
		senhaUsuario.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if ( senhaUsuario.getPassword().length >= 6) {
					e.consume();
				}
			}
		});
		
		rdbtnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnAdministrador.isSelected()){	
					rdbtnUsuario.setSelected(false);
					tipoUsuario = true;
				}else if(rdbtnUsuario.isSelected()){
					rdbtnAdministrador.setSelected(false);
				}
			}
		});
		rdbtnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnUsuario.isSelected() ){
					rdbtnAdministrador.setSelected(false);
					tipoUsuario = false;
				}else{
					rdbtnUsuario.setSelected(false);
				}
			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String senha = new String(senhaUsuario.getPassword());
				if (idUsuarioSelecionado == 0){	
					Usuario usuario = new Usuario(null, txtNome.getText(), txtMatricula.getText(), senha, txtEmail.getText(), tipoUsuario);
					String retorno = mbUsuario.inserir(usuario);
					if (retorno.equals("ok")){
						JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso");
						PanelListagemUsuario();
					}else{
						JOptionPane.showMessageDialog(null,retorno);
					}
				}else{
					Usuario usuario = new Usuario(idUsuarioSelecionado, txtNome.getText(), txtMatricula.getText(), senha, txtEmail.getText(), tipoUsuario);
					String retorno = mbUsuario.editar(usuario);
					if (retorno.equals("ok")){
						Usuario us = mbUsuario.retornarUsuario(idUsuarioSelecionado);	
						if (us.getSenha() != senha){ 
							JOptionPane.showMessageDialog(null, "A nova senha deverá ser utilizada a partir do próximo login" );
						}
						JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso");
						PanelListagemUsuario();				
					}else{
						JOptionPane.showMessageDialog(null,retorno);
					}
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					PanelListagemUsuario();
			}
		});
	}
	public void PanelListagemUsuario(){
		try {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemUsuario();
		} catch (Exception e) {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemUsuario();
		}
	}
}

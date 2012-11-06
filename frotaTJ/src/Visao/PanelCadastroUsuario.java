package Visao;

import javax.swing.JPanel;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;


import mb.MBUsuario;
import dao.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PanelCadastroUsuario extends PanelExemplo {
	private JTextField txtNome;
	private JTextField txtMatricula;
	private JTextField txtEmail;
	private JPasswordField senhaUsuario;
	private boolean tipoUsuario;	 

	public PanelCadastroUsuario(final int idUsuarioSelecionado) {
		final MBUsuario mbUsuario = MBUsuario.getInstance();
		txtNome = new JTextField();
		txtNome.setColumns(10);

		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);

		final JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");

		final JRadioButton rdbtnUsuario = new JRadioButton("Usu\u00E1rio");

		JButton btnSalvar = new JButton("Salvar");

		JButton btnVoltar = new JButton("Voltar");
		add(rdbtnUsuario);
		add(rdbtnAdministrador);
		add(txtMatricula);
		add(txtNome);
		add(txtEmail);
		add(btnSalvar);
		add(btnVoltar);

		final JPasswordField senhaUsuario = new JPasswordField();

		add(senhaUsuario);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNome);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSenha);

		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblMatricula);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEmail);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
					.addGap(114))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(senhaUsuario, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
					.addGap(114))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblMatricula, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtMatricula, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
					.addGap(114))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnAdministrador, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(rdbtnUsuario, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
							.addGap(61))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
							.addGap(114))))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(183, Short.MAX_VALUE)
					.addComponent(btnSalvar)
					.addGap(31)
					.addComponent(btnVoltar)
					.addGap(112))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSenha)
						.addComponent(senhaUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMatricula))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnAdministrador)
						.addComponent(rdbtnUsuario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnSalvar, Alignment.TRAILING)
						.addComponent(btnVoltar))
					.addGap(79))
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
			@Override
			public void keyTyped(KeyEvent e) {
				if ( senhaUsuario.getPassword().length >= 6) {
					e.consume();
				}
			}
		});
		
		rdbtnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnAdministrador.isSelected() && idUsuarioSelecionado == 0){	
					rdbtnUsuario.setSelected(false);
					tipoUsuario = true;
				}else{
					rdbtnAdministrador.setSelected(false);
				}
			}
		});
		rdbtnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnUsuario.isSelected() && idUsuarioSelecionado == 0){
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
					MBUsuario mbUsuario = MBUsuario.getInstance();
					mbUsuario.inserir(usuario);
				}else{
					Usuario usuario = new Usuario(idUsuarioSelecionado, txtNome.getText(), txtMatricula.getText(), senha, txtEmail.getText(), tipoUsuario);
					MBUsuario mbUsuario = MBUsuario.getInstance();
					mbUsuario.editar(usuario);					
				}
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					PaneListagemUsuario();
			}
		});
	}
	public void PaneListagemUsuario(){
		try {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemUsuario();
		} catch (Exception e) {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemUsuario();
		}
	}
}

package Visao;

import javax.swing.JPanel;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import mb.MBFornecedor;
import mb.MBUsuario;
import dao.Servico;
import dao.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class PanelCadastroUsuario extends JPanel {
	private JTextField txtNome;
	private JTextField txtMatricula;
	private JTextField txtEmail;
	private JPasswordField senhaUsuario;

	public PanelCadastroUsuario() {
		
		txtNome = new JTextField();
		txtNome.setBounds(104, 37, 232, 20);
		txtNome.setColumns(10);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(104, 99, 232, 20);
		txtMatricula.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(104, 130, 232, 20);
		txtEmail.setColumns(10);
		
		final JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.setBounds(163, 168, 136, 23);

		final JRadioButton rdbtnUsuario = new JRadioButton("Usu\u00E1rio");
		rdbtnUsuario.setBounds(163, 194, 136, 23);
		rdbtnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnAdministrador.isSelected()){	
					rdbtnUsuario.setSelected(false);
				}
			}
		});
		rdbtnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnUsuario.isSelected()){	
					rdbtnAdministrador.setSelected(false);
				}
			}
		});

		rdbtnUsuario.setSelected(true);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(93, 235, 103, 23);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(227, 235, 129, 23);
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		setLayout(null);
		add(rdbtnUsuario);
		add(rdbtnAdministrador);
		add(txtMatricula);
		add(txtNome);
		add(txtEmail);
		add(btnSalvar);
		add(btnVoltar);
		
		final JPasswordField senhaUsuario = new JPasswordField();

		senhaUsuario.setBounds(104, 68, 232, 20);
				
		add(senhaUsuario);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(48, 37, 46, 14);
		add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(48, 68, 46, 14);
		add(lblSenha);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatricula.setBounds(48, 99, 46, 14);
		add(lblMatricula);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(48, 130, 46, 14);
		add(lblEmail);
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, senhaUsuario.getPassword().length);
				if (rdbtnUsuario.isSelected()){
					Usuario usuario = new Usuario(null, txtNome.getText(), txtMatricula.getText(), senhaUsuario.getPassword(), txtEmail.getText(), false);
					MBUsuario mbUsuario = MBUsuario.getInstance();
					mbUsuario.inserir(usuario);				
				} else if (rdbtnAdministrador.isSelected()) {
					Usuario usuario = new Usuario(null, txtNome.getText(), txtMatricula.getText(), senhaUsuario.getPassword(), txtEmail.getText(), true);
					MBUsuario mbUsuario = MBUsuario.getInstance();
					mbUsuario.inserir(usuario);						
				}
			}
		});		
	}
}

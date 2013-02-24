package Visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;

import mb.MBUsuario;
import mb.MBVeiculo;
import util.UsuarioUtil;
import dao.Usuario;
import java.awt.Toolkit;



public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField senhaUsuario;

	final MBUsuario mbUsuario = MBUsuario.getInstance();
	final UsuarioUtil usuarioLogado = UsuarioUtil.getInstance();
	final MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * TODO Implementar metodo de para controle de acesso ( usuario ou administrador )
	 *  e limitar tempo de acesso ( configuravel )
     *  telaPrincipal, trocar usuario faz o logof e fica tela login
	 */
	public TelaLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagens\\7837_32x32.png"));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 402, 257);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		senhaUsuario = new JPasswordField();
		senhaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setIcon(new ImageIcon("imagens\\7484_16x16.png"));
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		final JComboBox<String> cmbUsuario = new JComboBox<String>();
		cmbUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		final Vector<Usuario> listaUsuario = new Vector<>();
		Vector<String> listaNomeUsuario = new Vector<>();
		try{
			mbVeiculo.statusTodosVeiculos();
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
		senhaUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER ){
					Usuario usuario = mbUsuario.retornarUsuario(listaUsuario.get(cmbUsuario.getSelectedIndex()).getIdUsuario());
					validaUsuario(usuario);
				}
			}
		});

		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuario = mbUsuario.retornarUsuario(listaUsuario.get(cmbUsuario.getSelectedIndex()).getIdUsuario());
				validaUsuario(usuario);
				}
		});

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sair = JOptionPane.showConfirmDialog(null, "Deseja sair?","", JOptionPane.YES_NO_OPTION);
				if (sair == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEntrar)
							.addGap(32)
							.addComponent(btnSair))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSenha)
								.addComponent(lblUsurio))
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(senhaUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
								.addComponent(cmbUsuario, 0, 223, Short.MAX_VALUE))))
					.addGap(70))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsurio))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(senhaUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSair)
						.addComponent(btnEntrar))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void TelaPrincipal(){
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		telaPrincipal.show();
	}
	public void validaUsuario(Usuario usuario){
		String senha = new String(senhaUsuario.getPassword());
		if ( senha.equals(usuario.getSenha())){
			usuarioLogado.usuarioLogado(usuario);
			setVisible(false);
			TelaPrincipal();
		}else{
			JOptionPane.showMessageDialog(null, "Senha incorreta");
		}
	}
}

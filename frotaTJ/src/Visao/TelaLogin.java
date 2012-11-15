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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import mb.MBUsuario;
import javax.swing.JButton;

import dao.Usuario;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField senhaUsuario;

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
		final MBUsuario mbUsuario = MBUsuario.getInstance();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 402, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		senhaUsuario = new JPasswordField();
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
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

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSenha)
						.addComponent(lblUsurio))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEntrar)
							.addGap(18)
							.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(senhaUsuario, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGap(185))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(cmbUsuario, 0, 243, Short.MAX_VALUE)
								.addGap(70)))))
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
						.addComponent(btnEntrar)
						.addComponent(btnSair))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

package Visao;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Font;


public class TelaPrincipal extends JFrame {

	private String winDir= ("c:\\frotaTJ\\imagens\\");

	private PanelInicial panelInicial = new PanelInicial();
	private PanelConteudo panelConteudo = new PanelConteudo();
	
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("Sistema de Manuten\u00E7\u00E3o de Frota do Tribunal de Justi\u00E7a do Estado de Santa Catarina ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 800, 600);
		setLocationRelativeTo(null);
		panelConteudo.setBackground(UIManager.getColor("Button.background"));
		panelConteudo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelConteudo);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu(" Inicio");
		mnInicio.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		mnInicio.setIcon(new ImageIcon(winDir+"7161_32x32.png"));
		menuBar.add(mnInicio);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setIcon(new ImageIcon(winDir+"8366_32x32.png"));
		mnArquivo.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		menuBar.add(mnArquivo);
		
		JMenu mnCadastrar = new JMenu("Cadastrar");
		mnCadastrar.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		mnCadastrar.setIcon(new ImageIcon(winDir+"6098_32x32.png"));
		menuBar.add(mnCadastrar);
		
		JMenuItem mntmAbastecimento = new JMenuItem("Abastecimento");
		mntmAbastecimento.setIcon(new ImageIcon(winDir+"2895_32x32.png"));
		mntmAbastecimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroAbastecimento(0);
			}
		});
		mnCadastrar.add(mntmAbastecimento);
		
		JMenuItem mntmFornecedor = new JMenuItem("Fornecedor");
		mntmFornecedor.setIcon(new ImageIcon(winDir+"1003_32x32.png"));
		mnCadastrar.add(mntmFornecedor);
		
		JMenuItem mntmMarca = new JMenuItem("Marca");
		mntmMarca.setIcon(new ImageIcon(winDir+"3303_32x32.png"));
		mnCadastrar.add(mntmMarca);
		
		// Adiciona PanelCadastroModelo() no menu
		JMenuItem mntmModelo = new JMenuItem("Modelo");
		mntmModelo.setIcon(new ImageIcon(winDir+"1517_32x32.png"));
		mntmModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroModelo(0);
			}
		});
		mnCadastrar.add(mntmModelo);
		
		JMenuItem mntmMotorista = new JMenuItem("Motorista");
		mntmMotorista.setIcon(new ImageIcon(winDir+"7133_32x32.png"));
		mnCadastrar.add(mntmMotorista);
		
		JMenuItem mntmServio = new JMenuItem("Servi\u00E7o");
		mntmServio.setIcon(new ImageIcon(winDir+"ico-recursos-integra.png"));
		mntmServio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroServiço(0);
			}
		});
		mnCadastrar.add(mntmServio);
		
		JMenuItem mntmTipoDeServio = new JMenuItem("Tipo de Servi\u00E7o");
		mntmTipoDeServio.setIcon(new ImageIcon(winDir+"servicos-icone.png"));
		mnCadastrar.add(mntmTipoDeServio);
		
		JMenuItem mntmComarca = new JMenuItem("Unidade");
		mntmComarca.setIcon(new ImageIcon(winDir+"4049_32x32.png"));
		mnCadastrar.add(mntmComarca);
		
		JMenuItem mntmUsuario = new JMenuItem("Usuario ");
		mntmUsuario.setIcon(new ImageIcon(winDir+"7818_32x32.png"));
		mntmUsuario.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroUsuario();
			}
		});		
		mnCadastrar.add(mntmUsuario);
		
		JMenuItem mntmCarro = new JMenuItem("Veiculo");
		mntmCarro.setIcon(new ImageIcon(winDir+"1519_32x32.png"));
		mnCadastrar.add(mntmCarro);
		
		JMenu mnListar = new JMenu("Listagem");
		mnListar.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		mnListar.setIcon(new ImageIcon(winDir+"6169_32x32.png"));
		menuBar.add(mnListar);
		
		JMenuItem menuItem = new JMenuItem("Abastecimento");
		mnListar.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Fornecedor");
		mnListar.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Marca");
		mnListar.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("Modelo");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemModelo();
			}
		});
		mnListar.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("Motorista");
		mnListar.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("Servi\u00E7o");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemServiço();
			}
		});
	
		mnListar.add(menuItem_5);
		
		JMenuItem menuItem_6 = new JMenuItem("Tipo de Servi\u00E7o");
		mnListar.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("Unidade");
		mnListar.add(menuItem_7);
		
		JMenuItem menuItem_8 = new JMenuItem("Usuario ");
		mnListar.add(menuItem_8);
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemUsuario();
			}
		});
		JMenuItem menuItem_9 = new JMenuItem("Veiculo");
		mnListar.add(menuItem_9);
		
		JMenu mnRelatorios = new JMenu("Relatorios");
		mnRelatorios.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		mnRelatorios.setIcon(new ImageIcon(winDir+"1588_32x32.png"));
		menuBar.add(mnRelatorios);
		
		JMenu mnNewMenu = new JMenu("Trocar Usuario");
		mnNewMenu.setIcon(new ImageIcon(winDir+"7837_32x32.png"));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		menuBar.add(mnNewMenu);
		getContentPane().setLayout(new CardLayout(0, 0));
		panelConteudo.add(panelInicial, "panelInicial");
		
		
		
		
		
		}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void PanelListagemModelo(){
		PanelListagemModelo panelListagemModelo = new PanelListagemModelo();
		panelConteudo.add(panelListagemModelo, "panelListagemModelo");
		CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
		cardLayout.show(panelConteudo, "panelListagemModelo");
	}
	
	public void PanelListagemServiço(){
		PanelListagemServiço panelListagemServiço = new PanelListagemServiço();
		panelConteudo.add(panelListagemServiço, "panelListagemServiço");
		CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
		cardLayout.show(panelConteudo, "panelListagemServiço");
	}
	public void PanelListagemUsuario(){
		PanelListagemUsuario panelListagemUsuario = new PanelListagemUsuario();
		panelConteudo.add(panelListagemUsuario, "panelListagemUsuario");
		CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
		cardLayout.show(panelConteudo,"panelListagemUsuario");
	}
	public void PanelCadastroUsuario(){
		PanelCadastroUsuario panelCadastroUsuario = new PanelCadastroUsuario();
		panelConteudo.add(panelCadastroUsuario, "panelCadastroUsuario");
		CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
		cardLayout.show(panelConteudo,"panelCadastroUsuario");
	}
	public void PanelCadastroModelo(int j){
		PanelCadastroModelo panelCadastroModelo = new PanelCadastroModelo( j);
		panelConteudo.add(panelCadastroModelo, "panelCadastroModelo");
		CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
		cardLayout.show(panelConteudo, "panelCadastroModelo");
	}
	public void PanelCadastroServiço(int i){
		PanelCadastroServiço panelCadastroServiço = new PanelCadastroServiço(i);
		panelConteudo.add(panelCadastroServiço, "panelCadastroServiço");
		CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
		cardLayout.show(panelConteudo, "panelCadastroServiço");
	}
	public void PanelCadastroAbastecimento(int j){
		PanelCadastroAbastecimento panelCadastroAbastecimento = new PanelCadastroAbastecimento( j);
		panelConteudo.add(panelCadastroAbastecimento, "panelCadastroAbastecimento");
		CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
		cardLayout.show(panelConteudo, "panelCadastroAbastecimento");
	}
}



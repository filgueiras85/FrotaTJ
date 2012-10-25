import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;


public class TelaPrincipal extends JFrame {

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
		menuBar.add(mnInicio);
		
		JMenu mnListar = new JMenu("Listar");
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
		
		JMenuItem menuItem_9 = new JMenuItem("Veiculo");
		mnListar.add(menuItem_9);
		
		JMenu mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);
		
		JMenuItem mntmAbastecimento = new JMenuItem("Abastecimento");
		mnCadastrar.add(mntmAbastecimento);
		
		JMenuItem mntmFornecedor = new JMenuItem("Fornecedor");
		mnCadastrar.add(mntmFornecedor);
		
		JMenuItem mntmMarca = new JMenuItem("Marca");
		mnCadastrar.add(mntmMarca);
		
		JMenuItem mntmModelo = new JMenuItem("Modelo");
		mntmModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroModelo();
			}
		});
		mnCadastrar.add(mntmModelo);
		
		JMenuItem mntmMotorista = new JMenuItem("Motorista");
		mnCadastrar.add(mntmMotorista);
		
		JMenuItem mntmServio = new JMenuItem("Servi\u00E7o");
		mntmServio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroServiço();
			}
		});
		mnCadastrar.add(mntmServio);
		
		JMenuItem mntmTipoDeServio = new JMenuItem("Tipo de Servi\u00E7o");
		mnCadastrar.add(mntmTipoDeServio);
		
		JMenuItem mntmComarca = new JMenuItem("Unidade");
		mnCadastrar.add(mntmComarca);
		
		JMenuItem mntmUsuario = new JMenuItem("Usuario ");
		mnCadastrar.add(mntmUsuario);
		
		JMenuItem mntmCarro = new JMenuItem("Veiculo");
		mnCadastrar.add(mntmCarro);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenu mnRelatorios = new JMenu("Relatorios");
		menuBar.add(mnRelatorios);
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
	public void PanelCadastroModelo(){
		PanelCadastroModelo panelCadastroModelo = new PanelCadastroModelo();
		panelConteudo.add(panelCadastroModelo, "panelCadastroModelo");
		CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
		cardLayout.show(panelConteudo, "panelCadastroModelo");
	}
	public void PanelCadastroServiço(){
		PanelCadastroServiço panelCadastroServiço = new PanelCadastroServiço();
		panelConteudo.add(panelCadastroServiço, "panelCadastroServiço");
		CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
		cardLayout.show(panelConteudo, "panelCadastroServiço");
	}
}


package Visao;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import util.UsuarioUtil;
import util.Util;

import mb.MBUnidade;

import dao.Marca;
import dao.Unidade;

import dao.Usuario;

import java.awt.Toolkit;


public class TelaPrincipal extends JFrame {

	private String winDir= ("imagens\\");
	private String unidadeSelecionada;
	private PanelInicial panelInicial = new PanelInicial();
	private PanelConteudo panelConteudo = new PanelConteudo();
	final UsuarioUtil usuarioLogado = UsuarioUtil.getInstance();


	public TelaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagens\\1517_32x32.png"));
		setTitle("Sistema de Manuten\u00E7\u00E3o de Frotas do Tribunal de Justi\u00E7a do Estado de Santa Catarina ");


		setTitle("Manuten\u00E7\u00E3o de Frotas do Tribunal de Justi\u00E7a do Estado de Santa Catarina ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 800, 600);
		setLocationRelativeTo(null);
		panelConteudo.setBackground(UIManager.getColor("Button.background"));
		panelConteudo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelConteudo);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);



		//-------------------------- Menu Inicio --------------------------\\
		JMenu mnInicio = new JMenu(" Inicio");
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PanelInicial();
			}
		});
		mnInicio.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		mnInicio.setIcon(new ImageIcon(winDir+"7161_32x32.png"));
		menuBar.add(mnInicio);


		//-------------------------- Menu Cadastrar --------------------------\\		
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
		mntmFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroFornecedor(0);
			}
		});
		mnCadastrar.add(mntmFornecedor);

		JMenuItem mntmMarca = new JMenuItem("Marca");
		mntmMarca.setIcon(new ImageIcon(winDir+"M.jpg"));
		mntmMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroMarca(0);
			}
		});
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
		mntmMotorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroMotorista(0);
			}
		});
		mnCadastrar.add(mntmMotorista);

		JMenuItem mntmServio = new JMenuItem("Servi\u00E7o");
		mntmServio.setIcon(new ImageIcon(winDir+"ico-recursos-integra.png"));
		mntmServio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroServiço(0);
			}
		});
		mnCadastrar.add(mntmServio);

		JMenuItem mntmTipoDeServico = new JMenuItem("Tipo de Servi\u00E7o");
		mntmTipoDeServico.setIcon(new ImageIcon(winDir+"servicos-icone.png"));
		mntmTipoDeServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroTipoServico(0);
			}
		});
		mnCadastrar.add(mntmTipoDeServico);

		JMenuItem mntmUnidade = new JMenuItem("Unidade");
		mntmUnidade.setIcon(new ImageIcon(winDir+"4049_32x32.png"));
		mntmUnidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroUnidade(0);
			}
		});

		JMenuItem mntmTipoServioModelo = new JMenuItem("Tipo Servi\u00E7o Modelo");
		mntmTipoServioModelo.setIcon(new ImageIcon("imagens\\11988_32x32.png"));
		mntmTipoServioModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroTipoServiçoModelo(0, 0);
			}
		});
		mnCadastrar.add(mntmTipoServioModelo);
		mnCadastrar.add(mntmUnidade);

		JMenuItem mntmUsuario = new JMenuItem("Usuario ");
		mntmUsuario.setIcon(new ImageIcon(winDir+"7818_32x32.png"));
		mntmUsuario.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroUsuario(0);
			}
		});		
		mnCadastrar.add(mntmUsuario);

		//Item Cadastrar Veiculo
		JMenuItem mntmCarro = new JMenuItem("Veiculo");
		mntmCarro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelCadastroVeiculo(0);
			}
		});
		mntmCarro.setIcon(new ImageIcon(winDir+"1519_32x32.png"));
		mnCadastrar.add(mntmCarro);


		//-------------------------- Menu Listar --------------------------\\	
		JMenu mnListar = new JMenu("Listagem");
		mnListar.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		mnListar.setIcon(new ImageIcon("imagens\\7674_32x32.png"));
		menuBar.add(mnListar);

		JMenuItem menuItem = new JMenuItem("Abastecimento");
		menuItem.setIcon(new ImageIcon("imagens\\2895_32x32.png"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemAbastecimento();
			}
		});
		mnListar.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("Fornecedor");
		menuItem_1.setIcon(new ImageIcon("imagens\\1003_32x32.png"));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemFornecedor();
			}
		});
		mnListar.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("Marca");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemMarca();
			}
		});
		menuItem_2.setIcon(new ImageIcon("imagens\\M.jpg"));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemMarca();
			}
		});
		mnListar.add(menuItem_2);

		JMenuItem menuItem_3 = new JMenuItem("Modelo");
		menuItem_3.setIcon(new ImageIcon("imagens\\1517_32x32.png"));
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemModelo();
			}
		});
		mnListar.add(menuItem_3);

		JMenuItem menuItem_4 = new JMenuItem("Motorista");
		menuItem_4.setIcon(new ImageIcon("imagens\\7133_32x32.png"));
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemMotorista();
			}
		});
		mnListar.add(menuItem_4);

		JMenuItem menuItem_5 = new JMenuItem("Servi\u00E7o");
		menuItem_5.setIcon(new ImageIcon("imagens\\ico-recursos-integra.png"));
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemServiço();
			}
		});
		mnListar.add(menuItem_5);

		JMenuItem menuItem_6 = new JMenuItem("Tipo de Servi\u00E7o");
		menuItem_6.setIcon(new ImageIcon("imagens\\servicos-icone.png"));
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemTipoServico();
			}
		});
		mnListar.add(menuItem_6);

		JMenuItem menuItem_7 = new JMenuItem("Unidade");
		menuItem_7.setIcon(new ImageIcon("imagens\\4049_32x32.png"));
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemUnidade();
			}
		});

		JMenuItem mntmTipoDeServio_1 = new JMenuItem("Tipo de Servi\u00E7o Modelo");
		mntmTipoDeServio_1.setIcon(new ImageIcon("imagens\\11988_32x32.png"));
		mntmTipoDeServio_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemTipoServiçoModelo();
			}
		});
		mnListar.add(mntmTipoDeServio_1);
		mnListar.add(menuItem_7);

		JMenuItem menuItem_8 = new JMenuItem("Usuario ");
		menuItem_8.setIcon(new ImageIcon("imagens\\7818_32x32.png"));
		mnListar.add(menuItem_8);
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemUsuario();
			}
		});

		//Menu Item Listar Veiculo
		JMenuItem menuItem_9 = new JMenuItem("Veiculo");
		menuItem_9.setIcon(new ImageIcon("imagens\\1519_32x32.png"));
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelListagemVeiculo();
			}
		});
		mnListar.add(menuItem_9);

		//-------------------------- Menu Trocar Usuário --------------------------\\	
		JMenu mnNewMenu = new JMenu("Trocar Usuario");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				TelaLogin();
			}
		});
		mnNewMenu.setIcon(new ImageIcon(winDir+"7837_32x32.png"));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		menuBar.add(mnNewMenu);
		mntmUnidade.setIcon(new ImageIcon(winDir+"4049_32x32.png"));
		MBUnidade mbUnidade = MBUnidade.getInstance();
		Vector<Unidade> listaUnidade = new Vector<>();
		Vector<String> listaNomeUnidade = new Vector<>();
		try {
			listaUnidade.addAll(mbUnidade.listarUnidades());

			for (int i=0; i<listaUnidade.size(); i++){
				listaNomeUnidade.add(listaUnidade.get(i).getNome());
				//comboBoxUnidade.addItem(listaUnidade.get(i).getNome());
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		getContentPane().setLayout(new CardLayout(0, 0));
		panelConteudo.add(panelInicial, "panelInicial");

	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//TelaPrincipal frame = new TelaPrincipal();
					Visao.TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*/----------------------- Método para checar a unidade selecionada no sistema -----------------\\ 

	public String retornarUnidadeSelecionada(){
		return comboBoxUnidade.getSelectedItem()+"";
	}*/

	//--------------------- Método para voltar para o Panel Inicial ----------------------\\
	public void PanelInicial(){
		PanelInicial panelInicial = new PanelInicial();
		panelConteudo.add(panelInicial, "panelInicial");
		CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
		cardLayout.show(panelConteudo, "panelInicial");
	}	

	public void TelaLogin(){
		TelaLogin telaLogin = new TelaLogin();
		telaLogin.show();
	}

	//--------------------- Métodos para troca de Panel de cadastro ---------------------\\


	public void PanelCadastroTipoServiçoModelo(int j, int i){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){			
				PanelCadastroTipoServiçoModelo panelCadastroTipoServiçoModelo = new PanelCadastroTipoServiçoModelo( j, i);
				panelConteudo.add(panelCadastroTipoServiçoModelo, "panelCadastroTipoServiçoModelo");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo, "panelCadastroTipoServiçoModelo");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");
			}
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelCadastroModelo(int j){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelCadastroModelo panelCadastroModelo = new PanelCadastroModelo( j);
				panelConteudo.add(panelCadastroModelo, "panelCadastroModelo");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo, "panelCadastroModelo");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");
			}
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelCadastroServiço(int i){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelCadastroServiço panelCadastroServiço = new PanelCadastroServiço(i);
				panelConteudo.add(panelCadastroServiço, "panelCadastroServiço");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo, "panelCadastroServiço");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");
			}
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelCadastroVeiculo(int id){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelCadastroVeiculo panelCadastroVeiculo = new PanelCadastroVeiculo(id);
				panelConteudo.add(panelCadastroVeiculo, "panelCadastroVeiculo");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo, "panelCadastroVeiculo");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");
			}
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelCadastroUnidade(int idUnidade){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelCadastroUnidade panelCadastroUnidade = new PanelCadastroUnidade(idUnidade);
				panelConteudo.add(panelCadastroUnidade, "panelCadastroUnidade");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo,"panelCadastroUnidade");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");
			}
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelCadastroTipoServico(int idTipoServico){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelCadastroTipoServico panelCadastroTipoServico = new PanelCadastroTipoServico(idTipoServico);
				panelConteudo.add(panelCadastroTipoServico, "panelCadastroTipoServico");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo,"panelCadastroTipoServico");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");
			}
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelCadastroUsuario(int idUsuario){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelCadastroUsuario panelCadastroUsuario = new PanelCadastroUsuario(idUsuario);
				panelConteudo.add(panelCadastroUsuario, "panelCadastroUsuario");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo,"panelCadastroUsuario");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");
			}
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelCadastroAbastecimento(int idAbastecimento){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelCadastroAbastecimento panelCadastroAbastecimento = new PanelCadastroAbastecimento(idAbastecimento);
				panelConteudo.add(panelCadastroAbastecimento, "panelCadastroAbastecimento");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo,"panelCadastroAbastecimento");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");
			}
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelCadastroMotorista(int idMotorista){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelCadastroMotorista panelCadastroMotorista = new PanelCadastroMotorista(idMotorista);
				panelConteudo.add(panelCadastroMotorista, "panelCadastroMotorista");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo,"panelCadastroMotorista");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");
			}
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelCadastroFornecedor(int id){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelCadastroFornecedor panelCadastroFornecedor = new PanelCadastroFornecedor(id);
				panelConteudo.add(panelCadastroFornecedor, "panelCadastroFornecedor");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo, "panelCadastroFornecedor");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");
			}
		}else{
			setVisible(false);
			TelaLogin();
		}	
	}

	public void PanelCadastroMarca(int id){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelCadastroMarca panelCadastroMarca = new PanelCadastroMarca(id);
				panelConteudo.add(panelCadastroMarca, "panelCadastroMarca");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo, "panelCadastroMarca");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");

			}
		}else{
			setVisible(false);
			TelaLogin();
		}	
	}


	//--------------------- Métodos para troca de Panel de Listagem ---------------------\\	
	public void PanelListagemModelo(){
		if (usuarioLogado.tempoLogin()){
			PanelListagemModelo panelListagemModelo = new PanelListagemModelo();
			panelConteudo.add(panelListagemModelo, "panelListagemModelo");
			CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
			cardLayout.show(panelConteudo, "panelListagemModelo");
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelListagemTipoServiçoModelo(){
		if (usuarioLogado.tempoLogin()){
			PanelListagemTipoServiçoModelo panelListagemTipoServiçoModelo = new PanelListagemTipoServiçoModelo();
			panelConteudo.add(panelListagemTipoServiçoModelo, "panelListagemTipoServiçoModelo");
			CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
			cardLayout.show(panelConteudo, "panelListagemTipoServiçoModelo");
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelListagemServiço(){
		if (usuarioLogado.tempoLogin()){
			PanelListagemServiço panelListagemServiço = new PanelListagemServiço();
			panelConteudo.add(panelListagemServiço, "panelListagemServiço");
			CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
			cardLayout.show(panelConteudo, "panelListagemServiço");
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelListagemUnidade(){
		if (usuarioLogado.tempoLogin()){
			PanelListagemUnidade panelListagemUnidade = new PanelListagemUnidade();
			panelConteudo.add(panelListagemUnidade, "panelListagemUnidade");
			CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
			cardLayout.show(panelConteudo,"panelListagemUnidade");
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelListagemTipoServico(){
		if (usuarioLogado.tempoLogin()){
			PanelListagemTipoServico panelListagemTipoServico = new PanelListagemTipoServico();
			panelConteudo.add(panelListagemTipoServico, "panelListagemTipoServico");
			CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
			cardLayout.show(panelConteudo,"panelListagemTipoServico");
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelListagemUsuario(){
		if (usuarioLogado.tempoLogin()){
			if(usuarioLogado.ehAdministrador()){
				PanelListagemUsuario panelListagemUsuario = new PanelListagemUsuario();
				panelConteudo.add(panelListagemUsuario, "panelListagemUsuario");
				CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
				cardLayout.show(panelConteudo,"panelListagemUsuario");
			}else{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão!");

			}
			
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelListagemVeiculo(){
		if (usuarioLogado.tempoLogin()){
			PanelListagemVeiculo panelListagemVeiculo = new PanelListagemVeiculo();
			panelConteudo.add(panelListagemVeiculo, "panelListagemVeiculo");
			CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
			cardLayout.show(panelConteudo, "panelListagemVeiculo");
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelListagemAbastecimento(){
		if (usuarioLogado.tempoLogin()){
			PanelListagemAbastecimento panelListagemAbastecimento = new PanelListagemAbastecimento();
			panelConteudo.add(panelListagemAbastecimento, "panelListagemAbastecimento");
			CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
			cardLayout.show(panelConteudo, "panelListagemAbastecimento");
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelListagemMotorista(){
		if (usuarioLogado.tempoLogin()){
			PanelListagemMotorista panelListagemMotorista = new PanelListagemMotorista();
			panelConteudo.add(panelListagemMotorista, "panelListagemMotorista");
			CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
			cardLayout.show(panelConteudo, "panelListagemMotorista");
		}else{
			setVisible(false);
			TelaLogin();
		}
	}

	public void PanelListagemFornecedor(){
		if (usuarioLogado.tempoLogin()){
			PanelListagemFornecedor panelListagemFornecedor = new PanelListagemFornecedor();
			panelConteudo.add(panelListagemFornecedor, "panelListagemFornecedor");
			CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
			cardLayout.show(panelConteudo,"panelListagemFornecedor");
		}else{
			setVisible(false);
			TelaLogin();
		}	
	}

	public void PanelListagemMarca(){
		if (usuarioLogado.tempoLogin()){
			PanelListagemMarca panelListagemMarca = new PanelListagemMarca();
			panelConteudo.add(panelListagemMarca, "panelListagemMarca");
			CardLayout cardLayout = (CardLayout)panelConteudo.getLayout();
			cardLayout.show(panelConteudo,"panelListagemMarca");
		}else{
			setVisible(false);
			TelaLogin();
		}			
	}
}




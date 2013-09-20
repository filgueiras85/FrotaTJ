package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;

import dao.Modelo;
import dao.TipoServicoModelo;
import dao.Unidade;
import dao.Usuario;
import dao.Veiculo;

import mb.MBModelo;
import mb.MBServico;
import mb.MBTipoServiçoModelo;
import mb.MBUnidade;
import mb.MBUsuario;
import mb.MBVeiculo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import util.SendMail;
import util.UsuarioUtil;

import com.lowagie.text.Image;
import com.sun.mail.handlers.image_gif;

import java.awt.Button;
import java.io.FileNotFoundException;
import java.io.IOException;


public class PanelInicial extends PanelExemplo {
	private JTable table;
	private int idVeiculoSelecionado;
	final MBServico servicoMB = MBServico.getInstance();
	final MBTipoServiçoModelo tipoServicoModeloMB = MBTipoServiçoModelo.getInstance();
	final PanelGrafico panelGrafico = PanelGrafico.getInstance();
	final PanelGraficoBarras panelGraficoBarras = PanelGraficoBarras.getInstance();
	final SendMail sendEmail = SendMail.getInstance();
	final UsuarioUtil usuarioLogado = UsuarioUtil.getInstance();



	final MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	final MBUnidade mbUnidade = MBUnidade.getInstance();
	/**
	 * Create the panel.
	 * @throws ClassNotFoundException 
	 */
	public PanelInicial(){

		// ----------------------- Lebel e txt ---------------------------\\
		JLabel lblTitulo = new JLabel("Listagem geral de ve\u00EDculos");
		lblTitulo.setIcon(new ImageIcon("imagens\\1519_32x32.png"));
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JScrollPane scrollPane = new JScrollPane();
		DefaultComboBoxModel<String> modelComboBoxUnidade;
		final Vector<Unidade> listaUnidade = new Vector<>();
		final Vector<String> listaNomeUnidade = new Vector<>();

		try {
			listaUnidade.addAll(mbUnidade.listarUnidades());

			listaNomeUnidade.add("Selecionar");
			for (int i = 0; i<listaUnidade.size();i++){
				listaNomeUnidade.add(listaUnidade.get(i).getNome());
			}
			modelComboBoxUnidade = new DefaultComboBoxModel<String>(listaNomeUnidade);
		} catch (Exception e) {
			// TODO: handle exception
		}

		final JButton btnDetalhes = new JButton("Detalhes");
		btnDetalhes.setIcon(new ImageIcon("imagens\\8390_16x16.png"));
		btnDetalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaDetalhesVeiculo t = new TelaDetalhesVeiculo(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+""));
				t.show();
			}
		});
		btnDetalhes.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JButton btnRelatorioDePendencias = new JButton("Relat\u00F3rio de pend\u00EAncias");
		btnRelatorioDePendencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JasperPrint rel;
				rel = gerar();
				JasperViewer.viewReport(rel, true);
			}
		});
		btnRelatorioDePendencias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDetalhes.setVisible(false);
		btnRelatorioDePendencias.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		String graficoPizza = panelGrafico.grafico();
		String graficoBarra = null;

		ImageIcon img = new ImageIcon(graficoPizza);
		JLabel label = new JLabel(img); 
		ImageIcon img2= null;
		try {
			graficoBarra = panelGraficoBarras.Grafico();
			img2 = new ImageIcon(graficoBarra);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JLabel label2 = new JLabel(img2);
		panel.add(label, BorderLayout.SOUTH);
		panel.add(label2, BorderLayout.NORTH);
		List<String> anexos = new ArrayList();
		anexos.add(graficoBarra);
		anexos.add(graficoPizza);
		SendMail sendmail = SendMail.getInstance();
		Set<Usuario> usuarios = new HashSet<Usuario>(0);
		MBUsuario mbUsuario = MBUsuario.getInstance();
		try {
			List<Usuario> lista = mbUsuario.listarUsuarios();
				usuarios.add(mbUsuario.retornarUsuario(usuarioLogado.getIdUsuario()));
			
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
					

			sendmail.EnviarEmailAnexoRelatorio(usuarios, usuarios, usuarios, anexos, "Graficos", "Grafico anexo, encaminhado via Sistema");
		
		
		






		//---------------------------- Codigo do Layout ----------------------------\\
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(btnRelatorioDePendencias)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDetalhes))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)))
						.addComponent(lblTitulo))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDetalhes)
								.addComponent(btnRelatorioDePendencias))
							.addGap(0))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
							.addContainerGap())))
		);



		//----------------------------- Tabela ------------------------------\\
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idVeiculoSelecionado = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				btnDetalhes.setVisible(true);
				btnRelatorioDePendencias.setVisible(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "Placa", "Odometro", "Situa\u00E7\u00E3o"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(37);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		scrollPane.setViewportView(table);
		atualizaTabela2();
		setLayout(groupLayout);
		TableRowSorter<TableModel> sorter  = new TableRowSorter<TableModel>();	
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String s1, String s2) {
				String[] strings1 = s1.split("\\s");
				String[] strings2 = s2.split("\\s");
				return strings1[strings1.length - 1]
						.compareTo(strings2[strings2.length - 1]);
			}
		};
		table.setRowSorter(sorter);
		table.setAutoCreateRowSorter(true);
	}


	//------------------------------- Métodos -------------------------\\
	public void atualizarTabela() throws ClassNotFoundException, SQLException{
		((DefaultTableModel)table.getModel()).setRowCount(0);
		List<Veiculo> listaVeiculo = mbVeiculo.listarVeiculos();
		for (int i=0;i<listaVeiculo.size();i++){
			((DefaultTableModel)table.getModel()).addRow(new String[]{
					listaVeiculo.get(i).getIdveiculo()+"", listaVeiculo.get(i).getPlaca(), listaVeiculo.get(i).getUnidade().getNome(), listaVeiculo.get(i).getMotorista().getNome(), listaVeiculo.get(i).getSituacao()});
		}
	}
	public  void atualizaTabela2() {
		((DefaultTableModel)table.getModel()).setRowCount(0);

		// pega todos os serviços pendentes de cada veiculo, esse funciona

		try {
			List<Veiculo> veiculos = mbVeiculo.listarVeiculos();
			String dataBR = "";
			/*for (int i1=0;i1<veiculos.size();i1++){
				if(veiculos.get(i1).getSituacao() != "verde"){
					List<TipoServicoModelo> tiposServicosModeloVeiculo = (List<TipoServicoModelo>) tipoServicoModeloMB.atualizaStatusTodosVeiculos(veiculos.get(i1));
					for (int i=0;i<tiposServicosModeloVeiculo.size();i++){
						if (tiposServicosModeloVeiculo.get(i).getSituacao() != "verde"){							
							SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");  				  
							dataBR = out.format( tiposServicosModeloVeiculo.get(i).getDataProximoServico().getTime() );
							String cor = tiposServicosModeloVeiculo.get(i).getSituacao(); 
							((DefaultTableModel)table.getModel()).addRow(new String[] {
									veiculos.get(i1).getIdveiculo()+"",
									veiculos.get(i1).getPlaca(),								
									veiculos.get(i1).getOdometro()+"",
									tiposServicosModeloVeiculo.get(i).getKm()+"", // km do proximo servico
									dataBR, // data proximo servico
									tiposServicosModeloVeiculo.get(i).getTipoServico().getNome(), // servico a fazer
									cor,
									cor //situacao do serviço
							});							
						}						
					}				
				}
			}*/
		System.out.println("verde".compareToIgnoreCase("vermelho"));
		System.out.println("verde".compareToIgnoreCase("amarelo"));

			for (int i1=0;i1<veiculos.size();i1++){
				List<TipoServicoModelo> tiposServicosModeloVeiculo = (List<TipoServicoModelo>) tipoServicoModeloMB.atualizaStatusTodosVeiculos(veiculos.get(i1));

				int i = veiculos.get(i1).getSituacao().compareToIgnoreCase("verde");
				if(i!=0){

							((DefaultTableModel)table.getModel()).addRow(new String[] {
									veiculos.get(i1).getIdveiculo()+"",
									veiculos.get(i1).getPlaca(),								
									veiculos.get(i1).getOdometro()+"",
									veiculos.get(i1).getSituacao()
								
							});							
						}	
				}
									
				
			
			
			
		/* aqui é pra listar somente os veiculos, sem os serviços... nao sei pq mas NAO FUNCIONA DE JEITO NENHUM!!!	
		try {
			MBVeiculo veiculomb2 = MBVeiculo.getInstance();
			List<Veiculo> veiculos = veiculomb2.listarVeiculos();

			for (int i1=0;i1<veiculos.size();i1++){

				//String cor = "vermelho";
				//JOptionPane.showMessageDialog(null, veiculos.get(i1).getSituacao());

				if(veiculos.get(i1).getSituacao() != "verde"){
					String cor = veiculos.get(i1).getSituacao();
					((DefaultTableModel)table.getModel()).addRow(new String[] {
							veiculos.get(i1).getIdveiculo()+"",
							veiculos.get(i1).getPlaca(),								
							veiculos.get(i1).getOdometro()+"",
							"",//tiposServicosModeloVeiculo.get(i).getKm()+"", // km do proximo servico
							"",//dataBR, // data proximo servico
							"",//tiposServicosModeloVeiculo.get(i).getTipoServico().getNome(), // servico a fazer
							cor,
							cor //situacao do serviço
					});
				}
			}*/

			table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {  
				public Component getTableCellRendererComponent(JTable table, Object value,  
						boolean isSelected, boolean hasFocus, int row, int column) {  
					super.getTableCellRendererComponent(table, value, isSelected,  
							hasFocus, row, column);  
					// para definir cores para a linha da tabela de acordo com a situacao do servico
					String cor =  table.getValueAt(row, 3).toString();
					int ama = "verde".compareToIgnoreCase(cor);
					if (ama ==-9) {  
						setBackground(Color.RED);
						setForeground(Color.WHITE);
					} 
					else if (ama ==21) {  
						setBackground(Color.YELLOW);
						setForeground(Color.BLACK);
					} 
					else
					{  
						setBackground(null);
						setForeground(null);
					}	


					return this;  
				}  
			});
			//table.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
			//table.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 ); 

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//----------------- Gerando o Relatório -------------------\\

	public JasperPrint gerar(){
		JasperPrint rel = null;
		try {
			//Connection con = gConexao.getConexao();
			HashMap map = new HashMap();
			String arquivoJasper = "relatorio.jasper"; //Nome do relatorio
			rel = JasperFillManager.fillReport(arquivoJasper, map);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		return rel;
	}
}


package Visao;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.SwingConstants;

import org.hibernate.ejb.criteria.expression.function.CurrentDateFunction;

import com.sun.jmx.snmp.Timestamp;

import mb.MBFornecedor;
import mb.MBMotorista;
import mb.MBServico;
import mb.MBTipoServico;
import mb.MBVeiculo;

import dao.Fornecedor;
import dao.Motorista;
import dao.Servico;
import dao.TipoServico;
import dao.Veiculo;


public class PanelCadastroAbastecimento extends PanelExemplo {
	private JTextField textFieldData;
	private JTextField textFieldHodometro;
	private	JComboBox<TipoServico> comboBoxTipoServiço;
	private JComboBox<Motorista> comboBoxMotorista;
	private JComboBox<Fornecedor> comboBoxFornecedor;
	private JComboBox<Veiculo> comboBoxVeiculo;


	/**
	 * Create the panel.
	 */
	
	public PanelCadastroAbastecimento( final int idServicoSelecionado) {
		
		JLabel lblCadastroAbastecimento = new JLabel("Cadastro Abastecimento\r\n");
		lblCadastroAbastecimento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		
		textFieldData = new JTextField();
		textFieldData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldData.setColumns(10);
		
		JLabel lblHodometro = new JLabel("Hod\u00F4metro");
		lblHodometro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblTipodeServio = new JLabel("Tipo de Servi\u00E7o");
		lblTipodeServio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblMotorista = new JLabel("Motorista");
		lblMotorista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblVeiculo = new JLabel("Veiculo");
		lblVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textFieldHodometro = new JTextField();
		textFieldHodometro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldHodometro.setColumns(10);
		
		MBTipoServico mbTipoServico= MBTipoServico.getInstance();
		comboBoxTipoServiço = new JComboBox<TipoServico>();
		DefaultComboBoxModel<TipoServico> modeloComboBoxTipoServico;
		try {
			modeloComboBoxTipoServico = new DefaultComboBoxModel<TipoServico>(new Vector(mbTipoServico.listarTipoServicos()));
			comboBoxTipoServiço.setModel(modeloComboBoxTipoServico);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		comboBoxTipoServiço.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MBMotorista mbMotorista= MBMotorista.getInstance();
		comboBoxMotorista = new JComboBox<Motorista>();
		DefaultComboBoxModel<Motorista> modeloComboBoxMotorista;
		try {
			modeloComboBoxMotorista = new DefaultComboBoxModel<Motorista>(new Vector(mbMotorista.listarMotoristas()));
			comboBoxMotorista.setModel(modeloComboBoxMotorista);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		comboBoxMotorista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MBFornecedor mbFornecedor= MBFornecedor.getInstance();
		comboBoxFornecedor = new JComboBox<Fornecedor>();
		DefaultComboBoxModel<Fornecedor> modeloComboBoxFornecedor;
		try {
			modeloComboBoxFornecedor = new DefaultComboBoxModel<Fornecedor>(new Vector(mbFornecedor.listarFornecedores()));
			comboBoxFornecedor.setModel(modeloComboBoxFornecedor);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		comboBoxFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		MBVeiculo mbVeiculo= MBVeiculo.getInstance();
		comboBoxVeiculo = new JComboBox<Veiculo>();
		DefaultComboBoxModel<Veiculo> modeloComboBoxVeiculo;
		try {
			modeloComboBoxVeiculo = new DefaultComboBoxModel<Veiculo>(new Vector(mbVeiculo.listarVeiculos()));
			comboBoxVeiculo.setModel(modeloComboBoxVeiculo);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
		comboBoxVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemServiço();
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MBTipoServico mbTipoServico = MBTipoServico.getInstance();
				MBMotorista mbMotorista = MBMotorista.getInstance();
				MBVeiculo  mbVeiculo= MBVeiculo.getInstance();
				MBFornecedor mbFornecedor= MBFornecedor.getInstance();
				MBServico mbServico = MBServico.getInstance();

				java.sql.Timestamp data = new java.sql.Timestamp(transformaData(textFieldData.getText()+" 00:00:01").getTime());
				Servico s =  new Servico(new Integer(idServicoSelecionado), 
						mbMotorista.retornarMotorista(comboBoxMotorista.getItemAt(comboBoxMotorista.getSelectedIndex()).getIdmotorista()),
						mbVeiculo.retornarVeiculo(comboBoxVeiculo.getItemAt(comboBoxVeiculo.getSelectedIndex()).getIdveiculo()),
						mbFornecedor.retornarFornecedor((comboBoxFornecedor.getItemAt(comboBoxFornecedor.getSelectedIndex()).getIdfornecedor())),
						mbTipoServico.retornarTipoServico((comboBoxTipoServiço.getItemAt(comboBoxTipoServiço.getSelectedIndex()).getIdtipoServico())),
						data, Double.parseDouble(textFieldHodometro.getText()), textFieldOrçamento.getText(), 
						Integer.parseInt(textFieldCupomFiscal.getText()), textFieldDescrição.getText(), Integer.parseInt(textFieldKm.getText()));

					try {
						if (idServicoSelecionado==0){
							if (s.getIdServico()==0){
								s.setIdServico(null);
							}
							String retorno = mbServico.inserir(s);
							if (retorno.equals("ok")){
								
								JOptionPane.showMessageDialog(null,"Serviço inserido!");
								PanelListagemServiço();
							}else{
								JOptionPane.showMessageDialog(null,retorno);
							}
						}else{
							
							String retorno =  mbServico.editar(s);
							if (retorno.equals("ok")){
								JOptionPane.showMessageDialog(null,"Serviço alterado!");
								PanelListagemServiço();
							}else{
								JOptionPane.showMessageDialog(null,retorno);
							}
					}
						} catch (Exception e) {
						// TODO: handle exception
					}
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblVeiculo, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHodometro)
										.addComponent(lblData)
										.addComponent(lblFornecedor)
										.addComponent(lblTipodeServio)
										.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
									.addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(comboBoxMotorista, 0, 304, Short.MAX_VALUE)
										.addComponent(textFieldData, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
										.addComponent(textFieldHodometro, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
										.addComponent(comboBoxVeiculo, Alignment.LEADING, 0, 304, Short.MAX_VALUE)
										.addComponent(comboBoxTipoServiço, Alignment.LEADING, 0, 304, Short.MAX_VALUE)
										.addComponent(comboBoxFornecedor, 0, 304, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblCadastroAbastecimento))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroAbastecimento)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData)
						.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHodometro)
						.addComponent(textFieldHodometro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxMotorista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(155)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxTipoServiço, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipodeServio))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVeiculo)
						.addComponent(comboBoxVeiculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxFornecedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFornecedor))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(83))
		);
		setLayout(groupLayout);
		if (idServicoSelecionado>0){
			MBServico mbServico = MBServico.getInstance();
			
			try {
				Servico s = mbServico.retornarServico(idServicoSelecionado);
				String b = s.getData2().toString().substring(8, 10)+"/"+s.getData2().toString().substring(5, 7)+"/"+s.getData2().toString().substring(0, 4);
				
				textFieldCupomFiscal.setText(s.getNfTicket().toString());
				textFieldDescrição.setText(s.getDescricao());
				textFieldKm.setText(s.getKm().toString());
				textFieldHodometro.setText(s.getValor().toString());
				textFieldData.setText(b);
				textFieldOrçamento.setText(s.getNroOrcamento());
				// selecionar combobox fornecedor	
				boolean aux = false ;
				int  i=0; 
					
				while(aux==false){
						aux= mbFornecedor.listarFornecedores().get(i).getIdfornecedor()==s.getFornecedor().getIdfornecedor();
				   		if (aux==true) break; 
				   		i++;
						
					}
					comboBoxFornecedor.setSelectedIndex(i);
					//Selecionar combobox veiculo
					i=0;
					aux = false;
					while(aux==false){
						aux= mbVeiculo.listarVeiculos().get(i).getIdveiculo()==s.getVeiculo().getIdveiculo();
				   		if (aux==true) break; 
				   		i++;
					}
					comboBoxVeiculo.setSelectedIndex(i);
					//Selecionar combobox Motorista

					i=0;
					aux = false;
					while(aux==false){
						aux= mbMotorista.listarMotoristas().get(i).getIdmotorista()==s.getMotorista().getIdmotorista();
				   		if (aux==true) break; 
				   		i++;
					}
					comboBoxMotorista.setSelectedIndex(i);
					//Selecionar combobox TipoServiço

					i=0;
					aux = false;
					while(aux==false){
						aux= mbTipoServico.listarTipoServicos().get(i).getIdtipoServico()==s.getTipoServico().getIdtipoServico();
				   		if (aux==true) break; 
				   		i++;
					}
					comboBoxTipoServiço.setSelectedIndex(i);
			
			} catch (ClassNotFoundException | SQLException e) {
						
						
						e.printStackTrace();
					}finally{
						
					}}

	}
	public void PanelListagemServiço(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemServiço();
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemServiço();
		}
		
	}
	public java.util.Date transformaData(String data)  
	{  
	  SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy kk:hh:ss");  
	  try  
	  {  
	    return formatador.parse(data);  
	  }  
	  catch(ParseException ex)  
	  {   
	      throw new RuntimeException(ex);  
	  }  
	}
	
	
}

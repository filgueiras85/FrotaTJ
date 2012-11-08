package Visao;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import mb.MBFornecedor;
import mb.MBMotorista;
import mb.MBServico;
import mb.MBTipoServico;
import mb.MBUnidade;
import mb.MBVeiculo;
import dao.Fornecedor;
import dao.Motorista;
import dao.Servico;
import dao.TipoServico;
import dao.Unidade;
import dao.Veiculo;


public class PanelCadastroMotorista extends PanelExemplo {
	private JTextField textFieldMatricula;
	private JTextField textFieldNome;
	private JComboBox<Unidade> comboBoxUnidade;


	/**
	 * Create the panel.
	 */
	
	public PanelCadastroMotorista( final int idServicoSelecionado) {
		
		JLabel lblCadastroMotorista = new JLabel("Cadastro Motorista");
		lblCadastroMotorista.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblMatricula = new JLabel("Matr\u00EDcula");
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldMatricula.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldNome.setColumns(10);
		
		MBTipoServico mbTipoServico= MBTipoServico.getInstance();
		DefaultComboBoxModel<TipoServico> modeloComboBoxTipoServico;
		try {
			modeloComboBoxTipoServico = new DefaultComboBoxModel<TipoServico>(new Vector(mbTipoServico.listarTipoServicos()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		MBUnidade mbUnidade= MBUnidade.getInstance();
		comboBoxUnidade = new JComboBox<Unidade>();
		DefaultComboBoxModel<Unidade> modeloComboBoxUnidade;
		try {
			modeloComboBoxUnidade = new DefaultComboBoxModel<Unidade>(new Vector(mbUnidade.listarUnidades()));
			comboBoxUnidade.setModel(modeloComboBoxUnidade);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		comboBoxUnidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MBFornecedor mbFornecedor= MBFornecedor.getInstance();
		DefaultComboBoxModel<Fornecedor> modeloComboBoxFornecedor;
		try {
			modeloComboBoxFornecedor = new DefaultComboBoxModel<Fornecedor>(new Vector(mbFornecedor.listarFornecedores()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		MBVeiculo mbVeiculo= MBVeiculo.getInstance();
		DefaultComboBoxModel<Veiculo> modeloComboBoxVeiculo;
		try {
			modeloComboBoxVeiculo = new DefaultComboBoxModel<Veiculo>(new Vector(mbVeiculo.listarVeiculos()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
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
				/*MBTipoServico mbTipoServico = MBTipoServico.getInstance();
				MBMotorista mbMotorista = MBMotorista.getInstance();
				MBVeiculo  mbVeiculo= MBVeiculo.getInstance();
				MBFornecedor mbFornecedor= MBFornecedor.getInstance();
				MBServico mbServico = MBServico.getInstance();

				java.sql.Timestamp data = new java.sql.Timestamp(transformaData(textFieldMatricula.getText()+" 00:00:01").getTime());
				Servico s =  new Servico(new Integer(idServicoSelecionado), 
						mbMotorista.retornarMotorista(comboBoxUnidade.getItemAt(comboBoxUnidade.getSelectedIndex()).getIdmotorista()),
						mbVeiculo.retornarVeiculo(comboBoxVeiculo.getItemAt(comboBoxVeiculo.getSelectedIndex()).getIdveiculo()),
						mbFornecedor.retornarFornecedor((comboBoxFornecedor.getItemAt(comboBoxFornecedor.getSelectedIndex()).getIdfornecedor())),
						mbTipoServico.retornarTipoServico((comboBoxTipoServiço.getItemAt(comboBoxTipoServiço.getSelectedIndex()).getIdtipoServico())),
						data, Double.parseDouble(textFieldNome.getText()), textFieldOrçamento.getText(), 
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
			*/	
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome)
								.addComponent(lblMatricula)
								.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxUnidade, 0, 0, Short.MAX_VALUE)
								.addComponent(textFieldMatricula, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
								.addComponent(textFieldNome, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))
						.addComponent(lblCadastroMotorista)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(427, Short.MAX_VALUE)
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroMotorista)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMatricula)
						.addComponent(textFieldMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxUnidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(298))
		);
		setLayout(groupLayout);
		if (idServicoSelecionado>0){
			MBServico mbServico = MBServico.getInstance();
			
			try {
				Servico s = mbServico.retornarServico(idServicoSelecionado);
				String b = s.getData2().toString().substring(8, 10)+"/"+s.getData2().toString().substring(5, 7)+"/"+s.getData2().toString().substring(0, 4);
				
				
				textFieldNome.setText(s.getValor().toString());
				textFieldMatricula.setText(b);
			
				// selecionar combobox fornecedor	
				boolean aux = false ;
				int  i=0; 
					
				while(aux==false){
						aux= mbFornecedor.listarFornecedores().get(i).getIdfornecedor()==s.getFornecedor().getIdfornecedor();
				   		if (aux==true) break; 
				   		i++;
						
					}
					/*comboBoxFornecedor.setSelectedIndex(i);
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
					}*/
					comboBoxUnidade.setSelectedIndex(i);
					//Selecionar combobox TipoServiço

					i=0;
					aux = false;
					while(aux==false){
						aux= mbTipoServico.listarTipoServicos().get(i).getIdtipoServico()==s.getTipoServico().getIdtipoServico();
				   		if (aux==true) break; 
				   		i++;
					}
					/*comboBoxTipoServiço.setSelectedIndex(i);*/
			
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

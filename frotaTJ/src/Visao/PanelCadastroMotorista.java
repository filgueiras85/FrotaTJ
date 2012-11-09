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
import mb.MBMarca;
import mb.MBModelo;
import mb.MBMotorista;
import mb.MBServico;
import mb.MBTipoServico;
import mb.MBUnidade;
import mb.MBVeiculo;
import dao.Fornecedor;
import dao.Marca;
import dao.Modelo;
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
	
	public PanelCadastroMotorista( final int idMotoristaSelecionado) {
		
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
		
		
		final MBUnidade mbUnidade = MBUnidade.getInstance();
		comboBoxUnidade = new JComboBox<Unidade>();
		DefaultComboBoxModel<Unidade> modeloComboBox;
		
			try {
				modeloComboBox = new DefaultComboBoxModel<Unidade>(new Vector(mbUnidade.listarUnidades()));
				comboBoxUnidade.setModel(modeloComboBox);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		
		
			
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemMotorista();
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MBUnidade mbUnidade= MBUnidade.getInstance();

				MBMotorista mbMotorista = MBMotorista.getInstance();
				Motorista m =  new Motorista(mbUnidade.retornarUnidade(comboBoxUnidade.getItemAt(comboBoxUnidade.getSelectedIndex()).getIdunidade()), textFieldMatricula.getText(), textFieldNome.getText(), null, null);

					try {
						if (idMotoristaSelecionado==0){
							if (m.getIdmotorista()==0){
								m.setIdmotorista(null);
							}
							String retorno = mbMotorista.inserir(m);
							if (retorno.equals("ok")){
								
								JOptionPane.showMessageDialog(null,"Motorista inserido!");
								PanelListagemMotorista();
							}else{
								JOptionPane.showMessageDialog(null,retorno);
							}
						}else{
							
							String retorno =  mbMotorista.editar(m);
							if (retorno.equals("ok")){
								JOptionPane.showMessageDialog(null,"Motorista alterado!");
								PanelListagemMotorista();
							}else{
								JOptionPane.showMessageDialog(null,retorno);
							}
					}
						} catch (Exception e) {
						// TODO: handle exception
					}
					
					
					
		
				}
				
				}
				
			
		);
		
		
		
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
		
		
		if (idMotoristaSelecionado>0){
			MBMotorista mbMotorista = MBMotorista.getInstance();
			
			try {
				Motorista m = mbMotorista.retornarMotorista(idMotoristaSelecionado);
				textFieldNome.setText(m.getNome());
					
				boolean aux = false ;
				int  i=0; 
					
				while(aux==false){
						aux= mbUnidade.listarUnidades().get(i).getIdunidade()==m.getUnidade().getIdunidade();
				   		if (aux==true) break; 
				   		i++;
						
					}
					comboBoxUnidade.setSelectedIndex(i);

				
								
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"erro - "+e);
				// TODO: handle exception
			}
			
		}
	}
	
	public void PanelListagemMotorista(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemMotorista();
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemMotorista();
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

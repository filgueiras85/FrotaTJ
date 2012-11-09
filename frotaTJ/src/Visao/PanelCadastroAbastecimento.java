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

import mb.MBAbastecimento;
import mb.MBFornecedor;
import mb.MBMotorista;
import mb.MBServico;
import mb.MBTipoServico;
import mb.MBVeiculo;
import dao.Abastecimento;
import dao.Fornecedor;
import dao.Motorista;
import dao.Servico;
import dao.TipoServico;
import dao.Veiculo;


public class PanelCadastroAbastecimento extends PanelExemplo {
	private JTextField textFieldData;
	private JTextField textFieldHodometro;
	private JComboBox<Veiculo> comboBoxPlaca;


	/**
	 * Create the panel.
	 */

	public PanelCadastroAbastecimento( final int idAbastecimentoSelecionado) {

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

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textFieldHodometro = new JTextField();
		textFieldHodometro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldHodometro.setColumns(10);

 
		MBVeiculo mbVeiculo = MBVeiculo.getInstance();
		comboBoxPlaca = new JComboBox<Veiculo>();
		DefaultComboBoxModel<Veiculo> modeloComboBoxVeiculo;
		try {
			modeloComboBoxVeiculo = new DefaultComboBoxModel<Veiculo>(new Vector(mbVeiculo.listarVeiculos()));
			comboBoxPlaca.setModel(modeloComboBoxVeiculo);
		} catch (Exception e) {
			// TODO: handle exception
		}	

		comboBoxPlaca.setFont(new Font("Tahoma", Font.PLAIN, 15));

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
				
				MBAbastecimento mbAbastecimento = MBAbastecimento.getInstance();
				
				java.sql.Timestamp data = new java.sql.Timestamp(transformaData(textFieldData.getText()+" 00:00:01").getTime());
				Abastecimento a =  new Abastecimento();
						mbAbastecimento.retornarAbastecimento(comboBoxPlaca.getItemAt(comboBoxPlaca.getSelectedIndex()).getIdveiculo());

					try {
						if (idAbastecimentoSelecionado==0){
							if (a.getIdabastecimento()==0){
								a.setIdabastecimento(null);
							}
							String retorno = mbAbastecimento.inserir(a);
							if (retorno.equals("ok")){

								JOptionPane.showMessageDialog(null,"Serviço inserido!");
								PanelListagemServiço();
							}else{
								JOptionPane.showMessageDialog(null,retorno);
							}
						}else{

							String retorno =  mbAbastecimento.editar(a);
							if (retorno.equals("ok")){
								JOptionPane.showMessageDialog(null,"Abastecimento alterado!");
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
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblHodometro)
												.addComponent(lblData)
												.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
												.addGap(53)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(comboBoxPlaca, 0, 485, Short.MAX_VALUE)
														.addComponent(textFieldData, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
														.addComponent(textFieldHodometro, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))
														.addComponent(lblCadastroAbastecimento))
														.addContainerGap())
														.addGroup(groupLayout.createSequentialGroup()
																.addContainerGap(329, Short.MAX_VALUE)
																.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
																.addGap(19))
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
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblPlaca)
												.addComponent(comboBoxPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(76)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnSalvar)
														.addComponent(btnCancelar))
														.addGap(284))
				);
		setLayout(groupLayout);
		/*if (idServicoSelecionado>0){
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

					}}*/

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

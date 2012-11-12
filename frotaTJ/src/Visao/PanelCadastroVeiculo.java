package Visao;

import javax.swing.ComboBoxEditor;
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

import dao.Modelo;
import dao.Motorista;
import dao.TipoServico;
import dao.TipoServicoVeiculo;
import dao.TipoServicoVeiculoId;
import dao.Unidade;
import dao.Veiculo;

import mb.MBModelo;
import mb.MBMotorista;
import mb.MBTipoServicoVeiculo;
import mb.MBTipoServiçoModelo;
import mb.MBUnidade;
import mb.MBVeiculo;
import mb.MBVeiculo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class PanelCadastroVeiculo extends PanelExemplo {
	private JTextField textFieldPlaca;
	private JTextField textFieldOdometro;
	private JTextField textFieldChassi;
	private JTextField textFieldRenavan;
	private JComboBox<String> comboBoxModelo;
	private JComboBox<String> comboBoxUnidade;
	private JComboBox<Motorista> comboBoxMotorista;

	/**
	 * Create the panel.
	 */
	public PanelCadastroVeiculo(final int idVeiculoSelecionado) {
		final MBVeiculo mbVeiculo= MBVeiculo.getInstance();
		final MBModelo mbModelo = MBModelo.getInstance();
		final MBUnidade mbUnidade = MBUnidade.getInstance();
		final MBMotorista mbMotorista = MBMotorista.getInstance();
		
	// ------------------------------ Labels ---------------------------\\	
		JLabel lblCadastroModelo = new JLabel("Cadastro Veiculo\r\n");
		lblCadastroModelo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblOdometro = new JLabel("Odometro");
		lblOdometro.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblChassi = new JLabel("Chassi");
		lblChassi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblRenavan = new JLabel("Renavan");
		lblRenavan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblMotorista = new JLabel("Motorista");
		lblMotorista.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
	// ------------------------------ TextFields ---------------------------\\			
		textFieldPlaca = new JTextField();
		textFieldPlaca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPlaca.setColumns(10);
		
		textFieldOdometro = new JTextField();
		textFieldOdometro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldOdometro.setColumns(10);
		
		textFieldChassi = new JTextField();
		textFieldChassi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldChassi.setColumns(10);
		
		textFieldRenavan = new JTextField();
		textFieldRenavan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldRenavan.setColumns(10);

	// ------------------------------ ComboBoxs ---------------------------\\		
		comboBoxModelo = new JComboBox<String>();
		comboBoxModelo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultComboBoxModel<String> modelComboBoxModelo;
		final Vector<Modelo> listaModelo = new Vector<>();
		final Vector<String> listaNomeModelo = new Vector<>();
		try {
			listaModelo.addAll(mbModelo.listarModelos());
			
			for (int i = 0; i<listaModelo.size();i++){
				listaNomeModelo.add(listaModelo.get(i).getNome());
			}
			modelComboBoxModelo = new DefaultComboBoxModel<String>(listaNomeModelo);
			comboBoxModelo.setModel(modelComboBoxModelo);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
		comboBoxUnidade = new JComboBox<String>();
		comboBoxUnidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultComboBoxModel<String> modelComboBoxUnidade;
		final Vector<Unidade> listaUnidade = new Vector<>();
		final Vector<String> listaNomeUnidade = new Vector<>();
		
		try {
			listaUnidade.addAll(mbUnidade.listarUnidades());
			
			for (int i = 0; i<listaUnidade.size();i++){
				listaNomeUnidade.add(listaUnidade.get(i).getNome());
			}
			modelComboBoxUnidade = new DefaultComboBoxModel<String>(listaNomeUnidade);
			comboBoxUnidade.setModel(modelComboBoxUnidade);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		comboBoxMotorista = new JComboBox<Motorista>();
		comboBoxMotorista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DefaultComboBoxModel<Motorista> modelComboBoxMotorista;
		try {
			modelComboBoxMotorista = new DefaultComboBoxModel<Motorista>(new Vector(mbMotorista.listarMotoristas()));
			comboBoxMotorista.setModel(modelComboBoxMotorista);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	// ------------------------------ Botões ---------------------------\\	
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelListagemVeiculo();
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Veiculo v = new Veiculo(idVeiculoSelecionado, listaModelo.get(comboBoxModelo.getSelectedIndex()), mbMotorista.retornarMotorista(comboBoxMotorista.getItemAt(comboBoxMotorista.getSelectedIndex()).getIdmotorista()), listaUnidade.get(comboBoxUnidade.getSelectedIndex()), textFieldPlaca.getText(), textFieldRenavan.getText(), textFieldChassi.getText(), Integer.parseInt(textFieldOdometro.getText()));
				try {
					if (idVeiculoSelecionado==0){
						if (v.getIdveiculo()==0){
							v.setIdveiculo(null);
						}
						String retorno = mbVeiculo.inserir(v);
						Situação(v, idVeiculoSelecionado);
						if (retorno.equals("ok")){
							
							JOptionPane.showMessageDialog(null,"Veículo cadastrado com sucesso!");
							PanelListagemVeiculo();
						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
					}else{
						
						String retorno =  mbVeiculo.editar(v);
						if (retorno.equals("ok")){
							JOptionPane.showMessageDialog(null,"Veículo alterado com sucesso!");
							PanelListagemVeiculo();
						}else{
							JOptionPane.showMessageDialog(null,retorno);
						}
				}
					} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		
		
		
		// ------------------------------ Configuração do Layout ---------------------------\\	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblOdometro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblPlaca)
								.addComponent(lblRenavan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblChassi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldPlaca, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(textFieldRenavan, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(textFieldChassi, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(textFieldOdometro, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblModelo)
									.addGap(39))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxUnidade, Alignment.TRAILING, 0, 338, Short.MAX_VALUE)
								.addComponent(comboBoxModelo, 0, 338, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBoxMotorista, 0, 340, Short.MAX_VALUE))
						.addComponent(lblCadastroModelo))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblCadastroModelo)
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlaca)
						.addComponent(textFieldPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldRenavan, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRenavan, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldChassi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblChassi, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldOdometro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOdometro))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblModelo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxUnidade, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUnidade, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxMotorista, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMotorista, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(182))
		);
		setLayout(groupLayout);
		
	// ------------------------------ Checa o ID selecionado, para saber se é pra editar ou cadastrar ---------------------------\\	
	// ------------------------------ e Preenche os campos caso for uma edição ---------------------------------------------------\\
		if (idVeiculoSelecionado>0){
			
			try {
				Veiculo v = new Veiculo();
				v = mbVeiculo.retornarVeiculo(idVeiculoSelecionado);
				textFieldPlaca.setText(v.getPlaca());
				textFieldRenavan.setText(v.getrenavan());
				textFieldChassi.setText(v.getChassi());
				textFieldOdometro.setText(v.getOdometro().toString());
				comboBoxModelo.setSelectedItem(1);
				
			//-------------Seleciona os ComboBoxs-------------\\
				boolean aux = false ;
				int  i=0; 
				while(aux==false){
						aux= mbModelo.listarModelos().get(i).getIdmodelo()==v.getModelo().getIdmodelo();
				   		if (aux==true) break; 
				   		i++;						
				}
				comboBoxModelo.setSelectedIndex(i);
				
				i=0;
				aux = false;
				while(aux==false){
					aux= mbUnidade.listarUnidades().get(i).getIdunidade()==v.getUnidade().getIdunidade();
					if (aux==true) break; 
					i++;
				}
				comboBoxUnidade.setSelectedIndex(i);

				i=0;
				aux = false;
				while(aux==false){
					aux= mbMotorista.listarMotoristas().get(i).getIdmotorista()==v.getMotorista().getIdmotorista();
					if (aux==true) break; 
					i++;
				}
				comboBoxMotorista.setSelectedIndex(i);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"erro - "+e);
				// TODO: handle exception
			}
			
		}

	}
	// ------------------------------ Metodos ---------------------------\\	
	public void PanelListagemVeiculo(){ //Troca para o Panel listagemVeiculo
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemVeiculo();
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemVeiculo();
		}
	}
	public void Situação(Veiculo v, int idVeiculoSelecionado){
		if(idVeiculoSelecionado==0){
			MBTipoServicoVeiculo mbTipoServicoVeiculo = MBTipoServicoVeiculo.getInstance();
			MBTipoServiçoModelo mbTipoServiçoModelo = MBTipoServiçoModelo.getInstance();
			List<TipoServico> listaTipoServico = mbTipoServiçoModelo.ListarosTipoServicoModelo(v.getModelo().getIdmodelo());
			String ok = "Ok";
			String situacao=null;
			String aux = null;
			for(int i = 0;i<listaTipoServico.size();i++){
			TipoServicoVeiculoId tipoServicoVeiculoId = new TipoServicoVeiculoId(v.getIdveiculo(), listaTipoServico.get(i).getIdtipoServico());
			TipoServicoVeiculo tipoServicoVeiculo = new TipoServicoVeiculo(tipoServicoVeiculoId, v, listaTipoServico.get(i), true);
			mbTipoServicoVeiculo.inserir(tipoServicoVeiculo);
			}
			List<TipoServicoVeiculo> lista = mbTipoServicoVeiculo.ListarosTipoServicoVeiculo(v.getIdveiculo());
			
			for(int i = 0; i<lista.size();i++){
				situacao = situacao+lista.get(i).Situacao();
				aux = aux+ok;	
				}
			if(situacao.equalsIgnoreCase(aux)){
				v.setSituacao("ok");
			}
			
		}
		
		
	}
}


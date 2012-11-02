package Visao;

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

import dao.Veiculo;

import mb.MBVeiculo;
import mb.MBVeiculo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelCadastroVeiculo extends PanelExemplo {
	private JTextField textFieldPlaca;
	private JTextField textFieldOdometro;
	private JTextField textFieldChassi;
	private JTextField textFieldRenavan;
	private JTextField textFieldSituacao;

	/**
	 * Create the panel.
	 */
	public PanelCadastroVeiculo(final int idVeiculoSelecionado) {
		
		
		JLabel lblCadastroModelo = new JLabel("Cadastro Veiculo\r\n");
		lblCadastroModelo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
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
				PanelListagemVeiculo();
			}
		});
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textFieldPlaca = new JTextField();
		textFieldPlaca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPlaca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JComboBox comboBoxModelo = new JComboBox();
		comboBoxModelo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblOdometro = new JLabel("Odometro");
		lblOdometro.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textFieldOdometro = new JTextField();
		textFieldOdometro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldOdometro.setColumns(10);
		
		JLabel lblChassi = new JLabel("Chassi");
		lblChassi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textFieldChassi = new JTextField();
		textFieldChassi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldChassi.setColumns(10);
		
		textFieldRenavan = new JTextField();
		textFieldRenavan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldRenavan.setColumns(10);
		
		JLabel lblRenavan = new JLabel("Renavan");
		lblRenavan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		textFieldSituacao = new JTextField();
		textFieldSituacao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldSituacao.setColumns(10);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
		lblSituao.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JComboBox comboBoxUnidade = new JComboBox();
		comboBoxUnidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JComboBox comboBoxMotorista = new JComboBox();
		comboBoxMotorista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblMotorista = new JLabel("Motorista");
		lblMotorista.setFont(new Font("Tahoma", Font.PLAIN, 17));
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
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblOdometro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblPlaca)
								.addComponent(lblModelo)
								.addComponent(lblRenavan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblChassi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblSituao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblUnidade, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblMotorista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxMotorista, 0, 338, Short.MAX_VALUE)
								.addComponent(comboBoxUnidade, 0, 338, Short.MAX_VALUE)
								.addComponent(comboBoxModelo, 0, 338, Short.MAX_VALUE)
								.addComponent(textFieldSituacao, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(textFieldPlaca, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(textFieldRenavan, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(textFieldChassi, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(textFieldOdometro, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)))
						.addComponent(lblCadastroModelo))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblCadastroModelo)
					.addGap(46)
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
						.addComponent(textFieldSituacao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSituao, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
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
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(182))
		);
		setLayout(groupLayout);
		
		if (idVeiculoSelecionado>0){
			MBVeiculo mbVeiculo = MBVeiculo.getInstance();
			
			try {
				Veiculo v = new Veiculo();
				v = mbVeiculo.retornarVeiculo(idVeiculoSelecionado);
				textFieldPlaca.setText(v.getPlaca());
				textFieldRenavan.setText(v.getrenavan());
				textFieldChassi.setText(v.getChassi());
				textFieldOdometro.setText(v.getOdometro().toString());
				textFieldSituacao.setText(v.getSituacao().toString());
				comboBoxModelo.setSelectedItem(1);
				
				/*txtNome.setText(p.getNome());
				txtEmail1.setText(p.getEmail1());
				txtEmail2.setText(p.getEmail2());
				txtFoneCel.setText(p.getFoneCel());
				txtFoneFixo.setText(p.getFoneFixo());*/
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"erro - "+e);
				// TODO: handle exception
			}
			
		}

	}
	public void PanelListagemVeiculo(){
		try {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemVeiculo();
		} catch (Exception e) {
			TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemVeiculo();
		}
	}
}


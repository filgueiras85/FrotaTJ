package Visao;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import dao.Unidade;

import mb.MBUnidade;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCadastroUnidade extends PanelExemplo {
	private JTextField txtNome;

	public PanelCadastroUnidade(final int idUnidadeSelecionada) {
		final MBUnidade mbUnidade = MBUnidade.getInstance();
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		
		JButton btnSalvar = new JButton("Salvar");
		
		JButton btnCancelar = new JButton("Cancelar");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
					.addGap(189))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
					.addGap(30)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(178))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(75)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addGap(76))
		);
		setLayout(groupLayout);
		
		if (idUnidadeSelecionada>0){
			try {
				Unidade unidade = new Unidade();
				unidade = mbUnidade	.retornarUnidade(idUnidadeSelecionada);
				txtNome.setText(unidade.getNome());
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "erro -" +e);
			}
		}
		
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( idUnidadeSelecionada == 0){
					Unidade unidade = new Unidade(null, txtNome.getText());
					mbUnidade.inserir(unidade);
				}else{
					Unidade unidade = new Unidade(idUnidadeSelecionada, txtNome.getText());
					mbUnidade.editar(unidade);
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Voltar para onde? TelaPrincipal.
				 *
				 */
			}
		});
		
	}
}

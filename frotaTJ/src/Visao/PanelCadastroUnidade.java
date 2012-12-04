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
import java.awt.Font;
import javax.swing.ImageIcon;

public class PanelCadastroUnidade extends PanelExemplo {
	private JTextField txtNome;

	public PanelCadastroUnidade(final int idUnidadeSelecionada) {
		final MBUnidade mbUnidade = MBUnidade.getInstance();
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("imagens\\7484_16x16.png"));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		
		JLabel lblCadastroDeUnidade = new JLabel("Cadastro de Unidades");
		lblCadastroDeUnidade.setIcon(new ImageIcon("imagens\\4049_32x32.png"));
		lblCadastroDeUnidade.setFont(new Font("Tahoma", Font.PLAIN, 20));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(234, Short.MAX_VALUE)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancelar)
					.addGap(29))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
							.addGap(189))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCadastroDeUnidade)
							.addContainerGap(204, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblCadastroDeUnidade)
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addContainerGap())
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
				PanelListagemUnidade();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelListagemUnidade();
			}
		});
	}
	public void PanelListagemUnidade(){
		try {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemUnidade();
		} catch (Exception e) {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
			parent.PanelListagemUnidade();
		}
	}
}

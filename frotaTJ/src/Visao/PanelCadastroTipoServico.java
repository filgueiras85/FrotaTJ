package Visao;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import dao.TipoServico;
import dao.Unidade;

import mb.MBTipoServico;
import mb.MBUnidade;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class PanelCadastroTipoServico extends PanelExemplo {
	private JTextField txtNome;


	/**
	 * Create the panel.
	 */
	public PanelCadastroTipoServico(final int idTipoServicoSelecionado) {
		final MBTipoServico mbTipoServico = MBTipoServico.getInstance();
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("imagens\\7484_16x16.png"));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("imagens\\7464_32x32.png"));
		
		JLabel lblCadastroDeTipoServico = new JLabel("Cadastro de Tipos De Servico");
		lblCadastroDeTipoServico.setIcon(new ImageIcon("imagens\\servicos-icone.png"));
		lblCadastroDeTipoServico.setFont(new Font("Tahoma", Font.PLAIN, 20));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addComponent(lblCadastroDeTipoServico)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastroDeTipoServico)
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		if (idTipoServicoSelecionado>0){
			try {
				TipoServico tipoServico = new TipoServico();
				tipoServico = mbTipoServico	.retornarTipoServico(idTipoServicoSelecionado);
				txtNome.setText(tipoServico.getNome());
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "erro -" +e);
			}
		}
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( idTipoServicoSelecionado == 0){
					TipoServico tipoServico = new TipoServico(null, txtNome.getText()); //new TipoServico(null, txtNome.getText());
					mbTipoServico.inserir(tipoServico);
				}else{
					TipoServico tipoServico = new TipoServico(idTipoServicoSelecionado, txtNome.getText());
					mbTipoServico.editar(tipoServico);
				}
				PanelListagemTipoServico();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelListagemTipoServico();
			}
		});
	}
	public void PanelListagemTipoServico(){
		try {
			TelaPrincipal parent = (TelaPrincipal)getParent().getParent().getParent();
			parent.PanelListagemTipoServico();
		} catch (Exception e) {
			try {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent();
				parent.PanelListagemTipoServico();
			} catch (Exception e1) {
				TelaPrincipal	parent = (TelaPrincipal)getParent().getParent().getParent().getParent().getParent();
				parent.PanelListagemTipoServico();
			}
		}
	}
}

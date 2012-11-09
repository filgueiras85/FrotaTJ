package Visao;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class TelaDetalhesVeiculo extends JInternalFrame {
	static int idVeiculoSelecionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDetalhesVeiculo frame = new TelaDetalhesVeiculo(idVeiculoSelecionado);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaDetalhesVeiculo(int idVeiculoSelecionado) {
		setBounds(100, 100, 450, 300);
		this.idVeiculoSelecionado = idVeiculoSelecionado;
		
	}


}

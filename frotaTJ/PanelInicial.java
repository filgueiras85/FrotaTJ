import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.CardLayout;


public class PanelInicial extends PanelExemplo {

	/**
	 * Create the panel.
	 */
	public PanelInicial() {
		setLayout(new CardLayout(0, 0));
		
		JLabel lblSistemaDeControle = new JLabel("Sistema de Controle de Frota");
		lblSistemaDeControle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaDeControle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblSistemaDeControle);

	}

}

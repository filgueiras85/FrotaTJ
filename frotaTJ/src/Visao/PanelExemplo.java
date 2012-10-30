package Visao;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class PanelExemplo extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelExemplo() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE)
		);
		setLayout(groupLayout);

	}

}

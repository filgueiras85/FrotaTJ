package Visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;


import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ListagemPrincipal1 extends JPanel {

	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListagemPrincipal1() {
				GridBagLayout gridBagLayout = new GridBagLayout();
				gridBagLayout.columnWidths = new int[]{64, 63, 81, 73, 81, 0};
				gridBagLayout.rowHeights = new int[]{22, 0, 40, 170, 23, 23, 0};
				gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				setLayout(gridBagLayout);
				
				JLabel lblListagemPessoa = new JLabel("Listagem");
				lblListagemPessoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_lblListagemPessoa = new GridBagConstraints();
				gbc_lblListagemPessoa.anchor = GridBagConstraints.WEST;
				gbc_lblListagemPessoa.fill = GridBagConstraints.VERTICAL;
				gbc_lblListagemPessoa.insets = new Insets(0, 0, 5, 5);
				gbc_lblListagemPessoa.gridx = 1;
				gbc_lblListagemPessoa.gridy = 1;
				add(lblListagemPessoa, gbc_lblListagemPessoa);
				
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.gridwidth = 4;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 3;
				add(scrollPane, gbc_scrollPane);
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
					
						//btnEditar.setVisible(true);
						//btnApagar.setVisible(true);
					}
				});
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Nome ","Nome ", "Nome", "Nome" 
					}
				));
				scrollPane.setViewportView(table);
				
				JButton btnCadastrar = new JButton("Cadastrar");
				GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
				gbc_btnCadastrar.insets = new Insets(0, 0, 5, 5);
				gbc_btnCadastrar.gridx = 1;
				gbc_btnCadastrar.gridy = 4;
				add(btnCadastrar, gbc_btnCadastrar);
				final JButton btnApagar = new JButton("  Apagar  ");
				GridBagConstraints gbc_btnApagar = new GridBagConstraints();
				gbc_btnApagar.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnApagar.insets = new Insets(0, 0, 5, 5);
				gbc_btnApagar.gridwidth = 2;
				gbc_btnApagar.gridx = 2;
				gbc_btnApagar.gridy = 4;
				add(btnApagar, gbc_btnApagar);
				btnApagar.setVisible(false);
				final JButton btnEditar = new JButton("Editar");
				GridBagConstraints gbc_btnEditar = new GridBagConstraints();
				gbc_btnEditar.anchor = GridBagConstraints.NORTH;
				gbc_btnEditar.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnEditar.insets = new Insets(0, 0, 5, 5);
				gbc_btnEditar.gridx = 3;
				gbc_btnEditar.gridy = 4;
				add(btnEditar, gbc_btnEditar);
				btnEditar.setVisible(false);
				
				JButton btnNewButton = new JButton("   Voltar   ");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
				gbc_btnNewButton.gridx = 4;
				gbc_btnNewButton.gridy = 4;
				add(btnNewButton, gbc_btnNewButton);
				
				

			}
			
				}



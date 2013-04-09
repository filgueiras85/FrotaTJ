package Visao;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;

import mb.MBTipoServiçoModelo;
import mb.MBVeiculo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.TipoServicoModelo;
import dao.Veiculo;


public class PanelGraficoBarras extends JFrame{

	final static MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	final static MBTipoServiçoModelo tipoServicoModeloMB = MBTipoServiçoModelo.getInstance();

public PanelGraficoBarras(String title) throws ClassNotFoundException, SQLException {
super(title);
CategoryDataset dataset = PanelGraficoBarras.createDataset();
JFreeChart chart = PanelGraficoBarras.createBarChart(dataset);
ChartPanel panel = new ChartPanel(chart);
panel.setPreferredSize(new Dimension(400, 300));
setContentPane(panel);
}

private static CategoryDataset createDataset() throws ClassNotFoundException, SQLException {
	List<Veiculo> veiculos = (List<Veiculo>) mbVeiculo.listarVeiculos();
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	List<TipoServicoModelo> tipoServicos = (List<TipoServicoModelo>) tipoServicoModeloMB.listarTipoServicosModelos();
	for(int j = 0; j<tipoServicos.size();j++){
		dataset.addValue(0, "verde", tipoServicos.get(j).getTipoServico().getNome());
		dataset.addValue(0, "amarelo",tipoServicos.get(j).getTipoServico().getNome());
		dataset.addValue(0, "vermelho", tipoServicos.get(j).getTipoServico().getNome());


	}

	for(int ie=0;ie<veiculos.size();ie++){
	 List<TipoServicoModelo> tiposServicosModeloVeiculo = (List<TipoServicoModelo>) tipoServicoModeloMB.findTipoServicoByModelo(veiculos.get(ie));
		for (int i=0;i<tiposServicosModeloVeiculo.size();i++){
									
				
			 dataset.incrementValue(1, tiposServicosModeloVeiculo.get(i).getSituacao(),tiposServicosModeloVeiculo.get(i).getTipoServico().getNome()) ; // servico a fazer
						
										
			}	
	}


return dataset;
}

private static JFreeChart createBarChart(CategoryDataset dataset) {
JFreeChart chart = ChartFactory.createBarChart(
"Escolha de cor por veículo", //Titulo
"Veículo", // Eixo X
"Quantidade", //Eixo Y
dataset, // Dados para o grafico
PlotOrientation.VERTICAL, //Orientacao do grafico
true, false, false); // exibir: legendas, tooltips, url
return chart;
}

public static void main( String[] args ) throws ClassNotFoundException, SQLException {
	PanelGraficoBarras chart = new PanelGraficoBarras("Teste Bar Chart");
chart.pack();
chart.setVisible(true);
}
}

package Visao;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;

import mb.MBTipoServiçoModelo;
import mb.MBVeiculo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.TipoServicoModelo;
import dao.Veiculo;


public class PanelGraficoBarras {

	final static MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	final static MBTipoServiçoModelo tipoServicoModeloMB = MBTipoServiçoModelo.getInstance();
	private static PanelGraficoBarras panelGrafico = new PanelGraficoBarras();

	
	public static PanelGraficoBarras getInstance(){
		return panelGrafico;
		
	}
	
	public String Grafico() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
		
		CategoryDataset dataset = PanelGraficoBarras.createDataset();
		
		JFreeChart chart = PanelGraficoBarras.createBarChart(dataset);
		chart.getCategoryPlot().getRenderer(0).setSeriesPaint(0, Color.GREEN);  
		chart.getCategoryPlot().getRenderer(0).setSeriesPaint(1, Color.YELLOW);  
		chart.getCategoryPlot().getRenderer(0).setSeriesPaint(2, Color.RED);  

		StringBuffer strNome = new StringBuffer();
		strNome.append("imagens\\"); 


		DateFormat formatter = new SimpleDateFormat("ddMMyyyyhhmmss");

		String Nome = null;

		Calendar calendar = Calendar.getInstance();
		String dataatual = formatter.format(calendar.getTime());
		Nome = strNome.toString();
		System.out.println(Nome+1);
		strNome.append("Barra");
		strNome.append(dataatual);
		strNome.append(".png");

		Nome = strNome.toString();
		System.out.println(Nome);






		// Gera o gráfico própriamente  
		ChartUtilities.writeChartAsPNG(new FileOutputStream(Nome, true), chart, 400, 200, null, false, 0);  
		System.out.println(Nome);
		return Nome;
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
"Serviços", //Titulo
"Serviços", // Eixo X
"Quantidade", //Eixo Y
dataset, // Dados para o grafico
PlotOrientation.VERTICAL, //Orientacao do grafico
true, false, false); // exibir: legendas, tooltips, url
return chart;
}




}

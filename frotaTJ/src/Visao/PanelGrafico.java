package Visao;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import com.sun.org.apache.xml.internal.utils.CharKey;

import util.JCalendar;

public class PanelGrafico extends PanelExemplo{

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Map<String, Integer> pieDataSet = new HashMap<String, Integer>();

		pieChart();

	}


	@SuppressWarnings("null")
	private static void pieChart() throws FileNotFoundException, IOException {
		String Nome = null, dia = null, mes = null, ano = null;
		DefaultPieDataset pieDataSet = new DefaultPieDataset();

		pieDataSet.setValue("Vermelho", new Integer(105));
		pieDataSet.setValue("Amarelo", new Integer(7));
		pieDataSet.setValue("Verde", new Integer(17));


		JFreeChart chart = ChartFactory.createPieChart3D("Pie Chart",
				pieDataSet, true, true, true);

		PiePlot parametrizacaoGrafico = (PiePlot) chart.getPlot();   

		chart.getPlot().setForegroundAlpha(0.5f);  

		
			Nome = util.JCalendar.getTime();
		
	        try {
			dia = Nome.substring(0,2);
			mes = Nome.substring(3,5);
			ano = Nome.substring(6,10); 
			
			System.out.println(dia);
			System.out.println(mes);
			System.out.println(ano);
			
			switch(mes) {
		    case "01":
		        mes = "-Janeiro-";
		        break;
		    case "02":
		    	mes = "-Fevereiro-";
		        break;
		    case "03":
		    	mes = "-Março-";
		        break;
		    case "04":
		    	mes = "-Abril-";
		        break;
		    case "05":
		    	mes = "-Maio-";
		        break;
		    case "06":
		    	mes = "-Junho-";
		        break;
		    case "07":
		    	mes = "-Julho-";
		        break;
		    case "08":
		    	mes = "-Agosto-";
		        break;
		    case "09":
		    	mes = "-Setembro-";
		        break;
		    case "10":
		    	mes = "-Outubro-";
		        break;
		    case "11":
		    	mes = "-Novembro-";
		        break;
		    case "12":
		    	mes = "-Dezembro-";
		        break;
			}
			StringBuffer strNome = new StringBuffer();
	        strNome.append("imagens\\"); 
	        strNome.append(dia);
	        strNome.append(mes);
	        strNome.append(ano);
	        strNome.append(".png");
	        
	        Nome = strNome.toString();
	        System.out.println(Nome);
		
	        
		} catch (Exception e) {
				}
		
		// Gera o gráfico própriamente  
		ChartUtilities.writeChartAsPNG(new FileOutputStream(Nome, true), chart, 1000, 400, null, false, 0);  
		System.out.println(Nome);
		//p.showForegroundAlpha(TOP_ALIGNMENT);
		ChartFrame frame = new ChartFrame("Pie Chart", chart);
		frame.setVisible(true);
		frame.setSize(450, 500);


		// Cor do fundo do label  
		parametrizacaoGrafico.setLabelBackgroundPaint(Color.white);  

		// Cor do fundo do gráfico  
		parametrizacaoGrafico.setBackgroundPaint(Color.white);        

		// Cor da borda do gráfico  
		parametrizacaoGrafico.setBaseSectionOutlinePaint(Color.BLACK);  

		// Rotação da pizza no sentido horário  
		parametrizacaoGrafico.setDirection(Rotation.CLOCKWISE);  

		parametrizacaoGrafico.setLabelShadowPaint(Color.BLACK);  

	}
}



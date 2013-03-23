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

public class PanelGrafico extends PanelExemplo{

    public static void main(String[] args) throws FileNotFoundException, IOException {
    	Map<String, Integer> pieDataSet = new HashMap<String, Integer>();
    	
        pieChart();
        
    }


    private static void pieChart() throws FileNotFoundException, IOException {

        DefaultPieDataset pieDataSet = new DefaultPieDataset();

        pieDataSet.setValue("Vermelho", new Integer(105));
        pieDataSet.setValue("Amarelo", new Integer(7));
        pieDataSet.setValue("Verde", new Integer(17));
     

        JFreeChart chart = ChartFactory.createPieChart3D("Pie Chart",
                                pieDataSet, true, true, true);

        PiePlot parametrizacaoGrafico = (PiePlot) chart.getPlot();   
        
        chart.getPlot().setForegroundAlpha(0.5f);  
        
        
        // Gera o gráfico própriamente  
        ChartUtilities.writeChartAsPNG(new FileOutputStream("imagens\\gráfico.png"), chart, 1000, 400, null, false, 0);  
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
          
        parametrizacaoGrafico.setLabelShadowPaint(Color.RED);  
        
    }
}


   
package Visao;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class PanelGrafico extends PanelExemplo{

    public static void main(String[] args) {
        
        pieChart();
        
    }


    private static void pieChart() {

        DefaultPieDataset pieDataSet = new DefaultPieDataset();

        pieDataSet.setValue("Vermelho", new Integer(105));
        pieDataSet.setValue("Amarelo", new Integer(7));
        pieDataSet.setValue("Verde", new Integer(17));
     

        JFreeChart chart = ChartFactory.createPieChart("Pie Chart",
                                pieDataSet, true, true, true);

        PiePlot p = (PiePlot) chart.getPlot();

        //p.showForegroundAlpha(TOP_ALIGNMENT);
        ChartFrame frame = new ChartFrame("Pie Chart", chart);
        frame.setVisible(true);
        frame.setSize(450, 500);
    }
}
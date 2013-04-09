package Visao;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mb.MBVeiculo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.Color;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import com.sun.org.apache.xml.internal.utils.CharKey;

import dao.TipoServicoModelo;
import dao.Veiculo;

import util.JCalendar;
import util.Util;

public class PanelGrafico {
	private static MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	private static PanelGrafico panelGrafico = new PanelGrafico();
	public static PanelGrafico getInstance(){
		return panelGrafico;
		
	}
	
	public PanelGrafico() {
	}
	public String grafico(){
		String Nome = null;
		

		DefaultPieDataset pieDataSet = new DefaultPieDataset();

		/*pieDataSet.setValue("Atualizadas", new Integer(17));
	    pieDataSet.setValue("Pendentes", new Integer(105));
		pieDataSet.setValue("Precisam de Atenção", new Integer(7));


		JFreeChart chart = ChartFactory.createPieChart3D("Situação das Manutenções",
				pieDataSet, true, false, false);*/
		int verde = 0;
		int vermelho = 0;
		int amarelo = 0;
		try {
			List<Veiculo> veiculos = mbVeiculo.listarVeiculos();
		
			for (int i1=0;i1<veiculos.size();i1++){
				String cor = veiculos.get(i1).getSituacao();
				int ama = "verde".compareToIgnoreCase(cor);
				if(ama==0){//compara se esta verde
					verde+=1;
				}else{if(ama==-9){//compara se esta vermelho
					vermelho+=1;
				}else{if(ama==21){//compara se esta amarelo
					amarelo+=1;
				}
					
				}
					
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

		JFreeChart chart = ChartFactory.createPieChart3D("Situação das Manutenções",pieDataSet,true,true,false);                
		PiePlot ConfigurarCores = (PiePlot)chart.getPlot();

		ConfigurarCores.setSectionPaint("Atualizadas", Color.GREEN);
		pieDataSet.setValue("Atualizadas", new Integer(verde));
		
		ConfigurarCores.setSectionPaint("Precisam de Atenção", Color.YELLOW);
		pieDataSet.setValue("Precisam de Atenção", new Integer(amarelo));

		ConfigurarCores.setSectionPaint("Pendentes", Color.red);
		pieDataSet.setValue("Pendentes", new Integer(vermelho));

		

		PiePlot parametrizacaoGrafico = (PiePlot) chart.getPlot();   

		chart.getPlot().setForegroundAlpha(0.8f);  


		Date data = new Date();
		Util util = Util.getInstance();
		//Nome = util.transformaData(data()).to;
		
			StringBuffer strNome = new StringBuffer();
			strNome.append("imagens\\"); 
			
			
	        DateFormat formatter = new SimpleDateFormat("ddMMyyyyhhmmss");


			Calendar calendar = Calendar.getInstance();
			String dataatual = formatter.format(calendar.getTime());
			Nome = strNome.toString();
			System.out.println(Nome+1);
			strNome.append("Pizza");
			strNome.append(dataatual);
			strNome.append(".png");

			Nome = strNome.toString();
			System.out.println(Nome);


		



		// Gera o gráfico própriamente  
		try {
			ChartUtilities.writeChartAsPNG(new FileOutputStream(Nome, true), chart, 400, 200, null, false, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println(Nome);
		//p.showForegroundAlpha(TOP_ALIGNMENT);
		

		// Cor do fundo do label  
		parametrizacaoGrafico.setLabelBackgroundPaint(Color.white);  

		// Cor do fundo do gráfico  
		parametrizacaoGrafico.setBackgroundPaint(Color.white);        

		// Cor da borda do gráfico  
		parametrizacaoGrafico.setBaseSectionOutlinePaint(Color.BLACK);  

		// Rotação da pizza no sentido anti-horário  
		parametrizacaoGrafico.setDirection(Rotation.ANTICLOCKWISE);  

		parametrizacaoGrafico.setLabelShadowPaint(Color.BLACK);  
		return Nome;
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Map<String, Integer> pieDataSet = new HashMap<String, Integer>();

		PanelGrafico();

	}


	@SuppressWarnings({ "null", "deprecation" })
	private static void PanelGrafico() throws FileNotFoundException, IOException {
		String Nome = null;
		int dia = 0, mes = 0, ano = 0;

		DefaultPieDataset pieDataSet = new DefaultPieDataset();

		/*pieDataSet.setValue("Atualizadas", new Integer(17));
	    pieDataSet.setValue("Pendentes", new Integer(105));
		pieDataSet.setValue("Precisam de Atenção", new Integer(7));


		JFreeChart chart = ChartFactory.createPieChart3D("Situação das Manutenções",
				pieDataSet, true, false, false);*/
		int verde = 0;
		int vermelho = 0;
		int amarelo = 0;
		try {
			List<Veiculo> veiculos = mbVeiculo.listarVeiculos();
		
			for (int i1=0;i1<veiculos.size();i1++){
				String cor = veiculos.get(i1).getSituacao();
				int ama = "verde".compareToIgnoreCase(cor);
				if(ama==0){//compara se esta verde
					verde+=1;
				}else{if(ama==-9){//compara se esta vermelho
					vermelho+=1;
				}else{if(ama==21){//compara se esta amarelo
					amarelo+=1;
				}
					
				}
					
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

		JFreeChart chart = ChartFactory.createPieChart3D("Situação das Manutenções",pieDataSet,true,true,false);                
		PiePlot ConfigurarCores = (PiePlot)chart.getPlot();

		ConfigurarCores.setSectionPaint("Atualizadas", Color.GREEN);
		pieDataSet.setValue("Atualizadas", new Integer(verde));
		
		ConfigurarCores.setSectionPaint("Precisam de Atenção", Color.YELLOW);
		pieDataSet.setValue("Precisam de Atenção", new Integer(amarelo));

		ConfigurarCores.setSectionPaint("Pendentes", Color.red);
		pieDataSet.setValue("Pendentes", new Integer(vermelho));

		

		PiePlot parametrizacaoGrafico = (PiePlot) chart.getPlot();   

		chart.getPlot().setForegroundAlpha(0.8f);  


		Date data = new Date();
		Util util = Util.getInstance();
		//Nome = util.transformaData(data()).to;
		
			StringBuffer strNome = new StringBuffer();
			strNome.append("imagens\\"); 
			
			
	        DateFormat formatter = new SimpleDateFormat("ddMMyyyyhhmmss");


			Calendar calendar = Calendar.getInstance();
			String dataatual = formatter.format(calendar.getTime());
			Nome = strNome.toString();
			System.out.println(Nome+1);
			strNome.append("Pizza");
			strNome.append(dataatual);
			strNome.append(".png");

			Nome = strNome.toString();
			System.out.println(Nome);


		



		// Gera o gráfico própriamente  
		ChartUtilities.writeChartAsPNG(new FileOutputStream(Nome, true), chart, 400, 200, null, false, 0);  
		System.out.println(Nome);
		//p.showForegroundAlpha(TOP_ALIGNMENT);
		ChartFrame frame = new ChartFrame("Situação das Manutenções", chart);
		frame.setVisible(true);
		frame.setSize(450, 500);


		// Cor do fundo do label  
		parametrizacaoGrafico.setLabelBackgroundPaint(Color.white);  

		// Cor do fundo do gráfico  
		parametrizacaoGrafico.setBackgroundPaint(Color.white);        

		// Cor da borda do gráfico  
		parametrizacaoGrafico.setBaseSectionOutlinePaint(Color.BLACK);  

		// Rotação da pizza no sentido anti-horário  
		parametrizacaoGrafico.setDirection(Rotation.ANTICLOCKWISE);  

		parametrizacaoGrafico.setLabelShadowPaint(Color.BLACK);  



	}
}



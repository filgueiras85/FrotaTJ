package Visao;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
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

public class PanelGrafico extends PanelExemplo{
	private static MBVeiculo mbVeiculo = MBVeiculo.getInstance();
	public PanelGrafico() {
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
		try {
			dia = data.getDay();
			mes = data.getMonth();
			ano = data.getYear();
			/*dia = Nome.substring(0,2);

			mes = Nome.substring(3,5);
			ano = Nome.substring(6,10); */

			System.out.println(dia);
			System.out.println(mes);
			System.out.println(ano);

			String mesOut = null;
			System.out.println(dia + mesOut + ano);


			switch(mes) {
			case 01:
				mesOut = "-Janeiro-";

				break;
			case 02:
				mesOut = "-Fevereiro-";

				break;
			case 03:
				mesOut = "-Março-";
				break;
				/* case "04":
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
				 */}
			StringBuffer strNome = new StringBuffer();
			strNome.append("imagens\\"); 
			/*strNome.append(dia);
			//strNome.append(mes);
			strNome.append(mesOut);
			strNome.append(ano);*/
			strNome.append(System.currentTimeMillis());
			strNome.append(".png");

			Nome = strNome.toString();
			System.out.println(Nome);


		} catch (Exception e) {
		}



		// Gera o gráfico própriamente  
		ChartUtilities.writeChartAsPNG(new FileOutputStream(Nome, true), chart, 1000, 400, null, false, 0);  
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



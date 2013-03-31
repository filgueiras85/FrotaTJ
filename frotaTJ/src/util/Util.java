package util;

<<<<<<< HEAD
import java.sql.Timestamp;
import java.text.DateFormat;
=======
import java.text.DateFormat;
import java.text.NumberFormat;
>>>>>>> origin/master
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
<<<<<<< HEAD
import javax.swing.JOptionPane;
import javax.swing.JTable;
=======
>>>>>>> origin/master
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import sun.net.www.content.image.png;

import Visao.PanelRelatorioTotalGasto;


import dao.Usuario;

public class Util {
	private static Util util = new Util();
	private MaskFormatter data;
	private Date RTGDataInicio = new Date();
	private Date RTGDataFinal = new Date();
	

	public static Util getInstance(){
		return util;		
	}
	
	public void setRTGDataInicio(Date data){
		this.RTGDataInicio = RetornaData(data);
	}
	public Date getRTGDataInicio(){
		return this.RTGDataInicio;
	}
	
	public void setRTGDataFinal(Date data){
		this.RTGDataFinal = RetornaData(data);
	}
	public Date getRTGDataFinal(){
		return this.RTGDataFinal;
	}
	
	public void setRTGData(Date dataInicio, Date datFinal){
		this.setRTGDataInicio(dataInicio);
		this.setRTGDataFinal(datFinal);
	}


	public static String mascaraHodometro (String hodometro) throws ParseException{
		String base6 = "nnnnnn";
		String base5 = "nnnnn";
		String base4 = "nnnn";
		String base3 = "nnn";
		String base2 = "nn";
		String base1 = "n";
		if (hodometro.length() == base6.length()){
			return hodometro;
		}else if (hodometro.length() == base5.length()){
			return hodometro;
		}else if (hodometro.length() == base4.length()){
			return hodometro;
		}else if (hodometro.length() == base3.length()){
			return hodometro;
		}else if (hodometro.length() == base2.length()){
			return hodometro;
		}else if (hodometro.length() == base1.length()){
			return hodometro;
			
		}
		return hodometro;
	}
	
	public Date getCMBData(JComboBox combo){
		return formataData(combo.getSelectedItem().toString());
	}

	
	
	
	public JTextField mascaraData(){
		JTextField txt;
		MaskFormatter data = null;
		try {
			data = new MaskFormatter("##/##/####");
			return txt = new JFormattedTextField(data);
			
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		return null;
	}
	
		
	public java.util.Date transformaData(String data)  
	{  
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy kk:hh:ss");  
		try  
		{  
			return formatador.parse(data);  
		}  
		catch(ParseException ex)  
		{   

		}
		return null;  
	}
<<<<<<< HEAD
=======
	
    public Date formataData(String data) {   
        if (data == null || data.equals(""))  
            return null;  
          
        Date date = null;  
  
        	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            try {
				date = (java.util.Date)(formatter.parse(data));
		        return date;
            } catch (ParseException e) {
				// TODO Auto-generated catch block
            	e.printStackTrace();
            	return null;
            }    
    }
	
	public Date retornaData(Date data){
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        
        Date d = new Date();
        d.setTime(data.getTime());
        
      	try {
			d = sd.parse(sd.format(d));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return d;
	}
	
	public String retornaMoeda(Double valor){
		//String valorStr = "R$ 00,000";
		
		NumberFormat formato = NumberFormat.getCurrencyInstance();
		return formato.format(valor);
		
		
	}
>>>>>>> origin/master
	
	public Date RetornaData(Date data){
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        
        Date d = new Date();
        d.setTime(data.getTime());
        
      	try {
			d = sd.parse(sd.format(d));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return d;
	}
	public Date RetornarData(String sData){
        int d = Integer.parseInt(sData.substring(0,2));
        int m = Integer.parseInt(sData.substring(3,5))-1;
        int y = Integer.parseInt(sData.substring(6));

        
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.DAY_OF_MONTH, d);
        gc.set(Calendar.MONTH, m);
        gc.set(Calendar.YEAR, y);
        
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = gc.getTime();
        
        
        try{
        	dt = sd.parse(sd.format(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return dt;
	}	

	public String dataBanco(Date data){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");  
		return sd.format(data);
	}
    public Date formataData(String data) {   
        if (data == null || data.equals(""))  
            return null;  
          
        Date date = null;  
  
        	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            try {
				date = (java.util.Date)(formatter.parse(data));
		        return date;
            } catch (ParseException e) {
				// TODO Auto-generated catch block
            	e.printStackTrace();
            	return null;
            }    
    }
    

}


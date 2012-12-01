package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


import dao.Usuario;

public class Util extends JTextField{
	private static Util util = new Util();
	private MaskFormatter data;

	
	public static Util getInstance(){
		return util;		
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




}


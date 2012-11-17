package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;


import dao.Usuario;

public class Util {
	private static Util util = new Util();
	
	public static Util getInstance(){
		return util;		
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

package dao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRenderer extends DefaultTableCellRenderer {
   
	public TableCellRenderer() {  
    }  
  
    @Override    
    public Component getTableCellRendererComponent(    
        JTable table,    
        Object value,    
        boolean isSelected,    
        boolean hasFocus,    
        int row,    
        int column)    
    {    
        Component result = super.getTableCellRendererComponent(    
            table,    
            value,    
            isSelected,    
            hasFocus,    
            row,    
            column    
        );    
            
        if (column == 2 && (Float.parseFloat(value.toString()) < 0)){  
            result.setFont(new Font("arial", Font.PLAIN, 12));  
            result.setForeground(Color.red);                
        }else{  
            result.setFont(new Font("arial", Font.PLAIN, 12));  
            result.setForeground(Color.black);                
        }  
         
        return result;    
    }  
}

package master;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Red Dragonize
 */
import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;

public class RenderingKanan extends DefaultTableCellRenderer{
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); setHorizontalAlignment(SwingConstants.RIGHT); return this; } 
}


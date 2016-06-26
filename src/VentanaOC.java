

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.border.*;


public class VentanaOC extends JFrame 
{
	 private SistemaIndumentaria sis;
	 private  int WIDTH = 450;
	 private  int HEIGHT = 450;
	 
	
	 // Swing components
	 private Vector<String> columns;
	
	 	
	 private JTable table;
	
	 public VentanaOC() 
	 {
		 sis = new SistemaIndumentaria();
		 setSize(WIDTH,HEIGHT);
		  setBackground(Color.blue);
		  this.setVisible(true);
		  initGUI();
		  
		  
	 }
	 
	 private void initGUI() {
			try {
				setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				getContentPane().setLayout(null);
				{
					setTitle("Listado de Ordenes de compra");
					Vector datos = sis.getOC();
					Vector columnas = new Vector();
					columnas.add("Codigo");
					columnas.add("Fecha");
					columnas.add("Total");
					columnas.add("Proveedor");
					TableModel jTable1Model = new DefaultTableModel(datos, columnas);
					table = new JTable();
					getContentPane().add(table);
					table.setModel(jTable1Model);
					table.setBounds(14, 14, 380, 290);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.setLocation(new java.awt.Point(0, -1));
					
				}

				pack();
				this.setSize(400, 328);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
	
	 public static void main(String[] args) 
	 {
		 VentanaOC app = new VentanaOC();
		 app.setVisible(true);
	 }
	 
	 
}

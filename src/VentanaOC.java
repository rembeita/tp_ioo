import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;

public class VentanaOC extends JFrame{

	private JLabel lblError = new JLabel();
	
	private JTable tblTablaOc = new JTable();
	private JScrollPane tableContainer = new JScrollPane();
	
	private JButton btnSalir = new JButton("Salir");
	
	private SistemaIndumentaria sistemaIndumentaria;
	private Vector<Integer> idsOrdenesDeCompra = new Vector<Integer>();
	private Map<String, String> datosOc = new HashMap<String, String>();
	private Object[] components = {tableContainer, btnSalir, lblError};
	
	public VentanaOC (SistemaIndumentaria sistema){
		super();
		sistemaIndumentaria = sistema;
		sistema.ControlarStockMateriales();
		idsOrdenesDeCompra = sistemaIndumentaria.getIdsOC();
		initGUI();
		addComponents(components);
		if(idsOrdenesDeCompra.size() > 0){
			updateTable();
		} else {
			tableContainer.setVisible(false);
			lblError.setText("No existen materiales para reponer");
			lblError.setBounds(180, 150, 300, 30);
			lblError.setForeground(new Color(255, 0, 0));
			btnSalir.setBounds(170, 230, 200, 30);
		}
	}
	
	private AbstractTableModel tblModel(){
		AbstractTableModel model = new AbstractTableModel() {
			private String[] columnNames = {"Codigo", "Fecha", "Proveedor", "Total"};
			private Object[][] data = new Object[idsOrdenesDeCompra.size()][4];
			
			public int getColumnCount() {
		        return columnNames.length;
		    }

		    public int getRowCount() {
		        return data.length;
		    }

		    public String getColumnName(int col) {
		        return columnNames[col];
		    }

		    public Object getValueAt(int row, int col) {
		    	return data[row][col];
		    }

		    public Class getColumnClass(int c) {
		        return getValueAt(0, c).getClass();
		    }

		    public void setValueAt(Object value, int row, int col) {
		        data[row][col] = value;
		        fireTableCellUpdated(row, col);
		    }
		};
		
		return model;
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			
			tblTablaOc.setPreferredScrollableViewportSize(new Dimension(420, 420));
			tblTablaOc.setFillsViewportHeight(true);
			tblTablaOc.setModel(tblModel());
			tblTablaOc.getColumnModel().getColumn(1).setMaxWidth(80);
			tableContainer.setViewportView(tblTablaOc);
			tableContainer.setBounds(10, 60, 480, 350);
			
			btnSalir.setBounds(170, 430, 200, 30);
			btnSalir.setText("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});

			setTitle("Listado de Ordenes de Compra");
			setSize(600, 600);
			setMaximumSize(new Dimension(600, 600));
			setMinimumSize(new Dimension(600, 600));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateTable(){
		if(idsOrdenesDeCompra.size() > 0){
			for (int i = 0; i < idsOrdenesDeCompra.size(); i++) {
				datosOc = sistemaIndumentaria.buscarOc(idsOrdenesDeCompra.elementAt(i));
				tblTablaOc.getModel().setValueAt(datosOc.get("codigo"), i, 0);
				tblTablaOc.getModel().setValueAt(datosOc.get("fecha"), i, 1);
				tblTablaOc.getModel().setValueAt(datosOc.get("proveedor"), i, 2);
				tblTablaOc.getModel().setValueAt(datosOc.get("total"), i, 3);
			}
		}
	}
	
	private void addComponents(Object[] components){
		if(components != null){
			for (int i = 0; i < components.length; i++) {
				getContentPane().add((Component) components[i]);
			}
		}
	}
	
}

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

public class VentanaVentaPrenda extends JFrame{

	private JLabel lblDatosCliente = new JLabel("Datos de Cliente", SwingConstants.CENTER);
	private JLabel lblNumCliente = new JLabel("Número:");
	private JLabel lblNombreCliente = new JLabel("Nombre:");
	private JLabel lblSeleccionarItem = new JLabel("Seleccionar Prenda:");
	private JLabel lblError = new JLabel();
	private JLabel lblCantidadItem = new JLabel("Cantidad:");
	private JLabel lblPrecioTotal = new JLabel("Total:");
	
	private JTable tblTablaPrendas = new JTable();
	private JScrollPane tableContainer = new JScrollPane();
	
	private JComboBox<String>cmbPrendas;
	
	private JButton btnSeguir = new JButton("Seguir");
	private JButton btnAgregarItem = new JButton("Agregar");
	private JButton btnImprimirFactura = new JButton("Imprimir Factura");
	private JButton btnCancelar = new JButton("Cancelar");

	private JFormattedTextField txfCantidadField = new JFormattedTextField(numberFormater());
	private JFormattedTextField txfNumCliente = new JFormattedTextField(numberFormater());
	private JTextField txfNombreCliente = new JTextField();
	
	private SistemaIndumentaria sistemaIndumentaria;
	private Vector<Integer> prendas;
	private int codfac;
	private int codprendaSeleccionada;
	private int[][] itemsFactura;
	private Object[] components = {lblSeleccionarItem, lblCantidadItem, txfCantidadField, btnAgregarItem,
			tblTablaPrendas, lblPrecioTotal, btnImprimirFactura, btnCancelar, lblNumCliente, lblNombreCliente,
			btnSeguir, lblDatosCliente, txfNumCliente, txfNombreCliente, tableContainer, lblError};
	
	public VentanaVentaPrenda (SistemaIndumentaria sistema){
		super();
		sistemaIndumentaria = sistema;
		prendas = sistemaIndumentaria.getPrendas();
		if(prendas.size() > 0){
			codprendaSeleccionada = prendas.elementAt(0);
		}
		fillPrendas();
		initGUI();
		addComponents(components);
		showGenerarFactura();
	}
	
	private AbstractTableModel tblModel(){
		AbstractTableModel model = new AbstractTableModel() {
			private String[] columnNames = {"Nombre", "Cantidad"};
			private Object[][] data = new Object[prendas.size()][2];
			
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
	
	private void fillPrendas() {
		cmbPrendas = new JComboBox<String>(getNombrePrendas());
		addComponents(new Object[]{cmbPrendas});
	}

	private Vector<String> getNombrePrendas() {
		Vector<String> nombres = new Vector<String>();
		if(prendas.size()>0){
			for (int i = 0; i < prendas.size(); i++) {
				nombres.add(sistemaIndumentaria.buscarTPrenda(prendas.elementAt(i)).get("nombre"));
			}
		}
		return nombres;
	}

	private NumberFormatter numberFormater(){
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    return formatter;
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);

			
			// Listeners
			cmbPrendas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					codprendaSeleccionada = prendas.elementAt(cmbPrendas.getSelectedIndex());
				}
			});
			btnSeguir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nomcliente = txfNombreCliente.getText();
					if(txfNumCliente.getValue() != null && nomcliente.length() > 0){
						codfac = sistemaIndumentaria.generarFactura((int)txfNumCliente.getValue(), nomcliente);
						hideGenerarFactura();
						showAgregarPrenda();
					}
				}
			});
			btnAgregarItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(txfCantidadField.getValue() != null){
						int cantidad = (int)txfCantidadField.getValue();
						if(cantidad <= sistemaIndumentaria.buscarPrenda(codprendaSeleccionada).getStockPrenda()){
							lblError.setVisible(false);
							sistemaIndumentaria.agregarPrenda(codfac, codprendaSeleccionada, cantidad);
							itemsFactura = sistemaIndumentaria.getItemfacturas(codfac);
							updateTable();
							lblPrecioTotal.setText("Total: "+sistemaIndumentaria.buscarFactura(codfac).get("total"));
						} else {
							lblError.setVisible(true);
						}
					}
				}
			});
			btnImprimirFactura.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hideAgregarPrenda();
					showFactura();
				}
			});
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(codfac != -1){
						sistemaIndumentaria.eliminarFactura(codfac);
					}
					dispose();
				}
			});
			setTitle("Venta de Prendas");
			setSize(600, 600);
			setMaximumSize(new Dimension(600, 600));
			setMinimumSize(new Dimension(600, 600));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateTable(){
		if(itemsFactura.length > 0){
			for (int i = 0; i < itemsFactura.length; i++) {
				tblTablaPrendas.getModel().setValueAt(
						sistemaIndumentaria.buscarTPrenda(itemsFactura[i][0]).get("nombre"), i, 0);
				tblTablaPrendas.getModel().setValueAt(itemsFactura[i][1], i, 1);
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
	
	private void showGenerarFactura(){
		if(prendas.size() > 0){
			lblDatosCliente.setBounds(10, 50, 480, 40);
			lblNumCliente.setBounds(140, 100, 200, 30);
			txfNumCliente.setBounds(200, 100, 150, 30);
			txfNumCliente.requestFocus();
			lblNombreCliente.setBounds(140, 150, 200, 30);
			txfNombreCliente.setBounds(200, 150, 150, 30);
			btnSeguir.setBounds(250, 220, 90, 30);
			btnCancelar.setBounds(140, 220, 90, 30);
		} else {
			lblError.setText("No hay prendas disponibles.");
			lblError.setBounds(180, 100, 200, 30);
			lblError.setForeground(new Color(255, 0, 0));
			lblError.setVisible(true);
			btnCancelar.setBounds(170, 220, 200, 30);
			btnCancelar.setText("Salir");
		}
	}
	
	private void hideGenerarFactura(){
		lblDatosCliente.setVisible(false);
		lblNumCliente.setVisible(false);
		txfNumCliente.setVisible(false);
		txfNombreCliente.setVisible(false);
		lblNombreCliente.setVisible(false);
		btnSeguir.setVisible(false);
	}
	
	private void showAgregarPrenda(){
		lblSeleccionarItem.setBounds(10, 10, 200, 20);
		cmbPrendas.setBounds(140, 5, 200, 30);
		lblCantidadItem.setBounds(10, 30, 200, 20);
		txfCantidadField.setBounds(80, 30, 40, 20);
		lblError.setText("Error: cantidad excedida");
		lblError.setBounds(140, 30, 200, 20);
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setVisible(false);
		btnAgregarItem.setBounds(360, 5, 130, 50);
		tblTablaPrendas.setPreferredScrollableViewportSize(new Dimension(420, 420));
		tblTablaPrendas.setFillsViewportHeight(true);
		tblTablaPrendas.setModel(tblModel());
		tblTablaPrendas.getColumnModel().getColumn(1).setMaxWidth(80);
		tblTablaPrendas.getModel().setValueAt("", 0, 0);
		tblTablaPrendas.getModel().setValueAt("", 0, 1);
		tableContainer.setViewportView(tblTablaPrendas);
		tableContainer.setBounds(10, 60, 480, 350);
		lblPrecioTotal.setBounds(400, 420, 200, 20);
		btnImprimirFactura.setBounds(340, 440, 150, 30);
		btnCancelar.setBounds(240, 440, 90, 30);
	}
	
	private void hideAgregarPrenda(){
		cmbPrendas.setVisible(false);
		lblCantidadItem.setVisible(false);
		txfCantidadField.setVisible(false);
		lblError.setVisible(false);
		btnAgregarItem.setVisible(false);
		btnImprimirFactura.setVisible(false);
		btnCancelar.setVisible(false);
	}
	
	private void showFactura(){
		lblSeleccionarItem.setText("Factura generada correctamente");
		lblSeleccionarItem.setBounds(150, 10, 250, 20);
		lblNombreCliente.setText("Cliente: "+txfNombreCliente.getText());
		lblNombreCliente.setBounds(10, 30, 200, 20);
		lblNombreCliente.setVisible(true);
	}
}

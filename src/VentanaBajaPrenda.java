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


public class VentanaBajaPrenda extends JFrame {
		
		private JLabel lblDatosPrenda = new JLabel("Datos de la prenda a eliminar", SwingConstants.CENTER);
		private JLabel lblCodPrenda = new JLabel("Codigo de prenda:");
		private JLabel lblPrendaEncontrada = new JLabel("¿Desea eliminar la siguiente prenda?");
		private JLabel lblNombrePrenda = new JLabel("Nombre prenda:");
		private JLabel lblError = new JLabel("No se pudo eliminar la prenda");
		
		private JButton btnBuscarPrenda = new JButton("Buscar Prenda");
		private JButton btnEliminar = new JButton("Eliminar");
		private JButton btnCancelar = new JButton("Cancelar");
		private JButton btnSalir;
		
		private Vector<Integer> prendas;
		
		private JFormattedTextField txfCodigoPrenda = new JFormattedTextField(numberFormater());
		
		private SistemaIndumentaria sistemaIndumentaria;
		
		private Object[] components = {lblDatosPrenda, lblCodPrenda, lblPrendaEncontrada, lblNombrePrenda,
				lblError, btnBuscarPrenda, btnEliminar, btnCancelar, txfCodigoPrenda};
		
	public VentanaBajaPrenda(SistemaIndumentaria sistema) {
		super();
		sistemaIndumentaria = sistema;
		prendas = sistemaIndumentaria.getPrendas();
//		inicializarComponentes();
		addComponents(components);
		showBuscarPrenda();
		initGUI();
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
			
			{
				btnSalir = new JButton();
				getContentPane().add(btnSalir);
				btnSalir.setText("Salir");
				btnSalir.setBounds(30, 337, 80, 23);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
			// Listeners
			btnBuscarPrenda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txfCodigoPrenda.getValue()!= null){
//						
						lblPrendaEncontrada.setVisible(false);
						lblNombrePrenda.setVisible(false);
						btnEliminar.setVisible(false);
						btnCancelar.setVisible(true);
						
						if (sistemaIndumentaria.buscarPrenda((int)txfCodigoPrenda.getValue()) != null){
							lblPrendaEncontrada.setVisible(true);
							lblNombrePrenda.setText(sistemaIndumentaria.buscarPrenda((int)txfCodigoPrenda.getValue()).getNombrePrenda());
							lblNombrePrenda.setVisible(true);
							lblError.setVisible(false);
							showEliminarPrenda();
						}else{
							lblError.setText("ERROR: La prenda que desea eliminar no existe");
							lblError.setVisible(true);
							lblError.setBounds(120, 230, 300, 30);
							lblError.setForeground(new Color(255, 0, 0));
							lblPrendaEncontrada.setVisible(false);
							btnCancelar.setVisible(false);
						}						
		
					}
				}
			});
			btnEliminar.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					if(txfCodigoPrenda.getValue()!= null)
						{
						    sistemaIndumentaria.bajaPrenda( (int)txfCodigoPrenda.getValue() );
							lblPrendaEncontrada.setText("La prenda ha sido eliminada");
							lblPrendaEncontrada.setVisible(true);
							lblPrendaEncontrada.setBounds(160, 220, 200, 30);
							lblNombrePrenda.setVisible(false);
						}	
				}
			
			});
			
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
						setTitle("Baja Prenda");
						setSize(500, 500);
						setMaximumSize(new Dimension(500, 500));
						setMinimumSize(new Dimension(500, 500));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addComponents(Object[] components){
		if(components != null){
			for (int i = 0; i < components.length; i++) {
				getContentPane().add((Component) components[i]);
			}
		}
	}
	
	
	private void showBuscarPrenda(){
		if(prendas.size() > 0){
		lblDatosPrenda.setBounds(0, 50, 480, 40);
		lblCodPrenda.setBounds(70, 100, 200, 30);
		txfCodigoPrenda.setBounds(200, 100, 150, 30);
		btnBuscarPrenda.setBounds(170, 170, 150, 30);
		lblDatosPrenda.setVisible(true);
		lblCodPrenda.setVisible(true);
		txfCodigoPrenda.setVisible(true);
		btnBuscarPrenda.setVisible(true);
		}
		else
		{
			lblError.setText("No hay prendas disponibles.");
			lblError.setBounds(180, 100, 200, 30);
			lblError.setForeground(new Color(255, 0, 0));
			lblError.setVisible(true);
			//btnCancelar.setBounds(170, 220, 200, 30);
			//btnCancelar.setText("Salir");
		}
	}
	
	private void showEliminarPrenda(){
		lblPrendaEncontrada.setBounds(150, 190, 250, 80);
		lblNombrePrenda.setBounds(150, 250, 200, 30);
		btnEliminar.setBounds(250, 300, 90, 30);
		btnCancelar.setBounds(140, 300, 90, 30);
		lblNombrePrenda.setVisible(true);
		btnEliminar.setVisible(true);
		btnCancelar.setVisible(true);
	}

	
	
	
}
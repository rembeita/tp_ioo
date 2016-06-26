import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class VentanaAltaPrenda extends JFrame {

	private JComboBox cmbTemporada;
	private JComboBox<String> cmbMaterial;
	private JTable tblTablaMateriales;
	private JScrollPane tblContTablaMateriales;
	
	private JLabel lblTituloPantalla;
	private JLabel lblCodigo;
	private JLabel lblTemporada;		
	private JLabel lblNombre;
	private JLabel lblStock;
	private JLabel lblMaterial;
	private JLabel lblCantMat;
	private JLabel lblError;
	
	private JButton btnAceptar;
	private JButton btnSeguir;
	private JButton btnCancelar;
	private JButton btnAgregar;
		
	private JTextField txtNombre;
	private JFormattedTextField txtCodigo;
	private JFormattedTextField txtStock;
	private JFormattedTextField txtCantMat;
	
	private boolean bPrendaDeTemporada;
	
	private SistemaIndumentaria sistemaIndumentaria;
	private Vector<Material> materiales;
	private Vector<ItemPrenda> itemPrendas;	
	private int[][] materialesAgregados;
	private int codMaterial;
	private int codPrenda;                       
	private boolean bError = false; 
	private Material matSeleccionado;

	
	public VentanaAltaPrenda(SistemaIndumentaria sistema){
		
		super();
		
		sistemaIndumentaria = sistema;
		materiales = sistemaIndumentaria.getMateriales();
		materialesAgregados = new int [materiales.size()][2];
		initGUI();
		cargaComboTemporadas();
		cargaComboMateriales();
	}	
	
	private void initGUI(){				
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			setTitle("Alta Prenda");
			
			//Visualizador de Errores
			{
				lblError = new JLabel();
				getContentPane().add(lblError);
				lblError.setBounds(10, 2, 450, 20);
				lblError.setForeground(new Color(255, 0, 0));
			}
			
			
			//Codigo
			{ 
				lblCodigo = new JLabel();
				getContentPane().add(lblCodigo);
				lblCodigo.setText("Código: ");
				lblCodigo.setBounds(10,30, 80, 20);
			}						
			{
				txtCodigo = new JFormattedTextField(numberFormater());
				getContentPane().add(txtCodigo);
				txtCodigo.setBounds(70, 28, 60, 20);
			}		
			
			{ //lbltemporada
				lblTemporada = new JLabel();
				getContentPane().add(lblTemporada);
				lblTemporada.setText("Temporada");
				lblTemporada.setBounds(180, 30, 80, 20);
			}
			
			//Nombre
			{ 
				lblNombre= new JLabel();
				getContentPane().add(lblNombre);
				lblNombre.setText("Nombre: ");
				lblNombre.setBounds(10,68, 80, 20);
			}			
			
			{
				txtNombre = new JTextField();
				getContentPane().add(txtNombre);
				txtNombre.setBounds(70, 66, 250, 20);
			}	
			
			//Stock
			{ 
				lblStock= new JLabel();
				getContentPane().add(lblStock);
				lblStock.setText("Stock: ");
				lblStock.setBounds(10,106, 80, 20);
			}			
			
			{
				txtStock = new JFormattedTextField(numberFormater());
				getContentPane().add(txtStock);
				txtStock.setBounds(70,104, 60, 20);
			}
			
			//Materiales
			{ 
				lblMaterial= new JLabel();
				getContentPane().add(lblMaterial);
				lblMaterial.setText("Material: ");
				lblMaterial.setBounds(10,144, 80, 20);				
			}			
			
			//Cantidad Materiales
			{
				lblCantMat = new JLabel();
				getContentPane().add(lblCantMat);
				lblCantMat.setText("Cantidad:");
//				lblCantMat.setForeground(Color.red);
				lblCantMat.setBounds(10, 182, 80, 20);	
			}

			{
				txtCantMat = new JFormattedTextField(numberFormater());
				getContentPane().add(txtCantMat);
				txtCantMat.setBounds(70, 180, 60, 20);
				
			}
			
			{
				btnSeguir = new JButton();
				getContentPane().add(btnSeguir);
				btnSeguir.setText("Seguir");
				btnSeguir.setBounds(150, 100, 80, 23);
				btnSeguir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Add prenda actual
						if(cmbTemporada.getSelectedItem() == "Sin Temporada"){
							if(sistemaIndumentaria.AltaPrendaSinTemporada((int)txtCodigo.getValue(), 
									txtNombre.getText(), (int)txtStock.getValue())){
								// Se pudo generar la prenda temporaria, mostrar carga de materiales
							}
						}
					}
				});
			}
			
			{	//btnAgregar
				btnAgregar  = new JButton();
				getContentPane().add(btnAgregar);
				btnAgregar.setText("Agregar");
				btnAgregar.setBounds(150, 180, 80, 23);
				btnAgregar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {						
												
						int codMaterial = materiales.elementAt(cmbMaterial.getSelectedIndex()).getCodigoMaterial();
						int cantMaterial = (int)txtCantMat.getValue();
						boolean bExiste = false;
						
						// Chequear este metodo, debe ser similar al agregar item factura
						sistemaIndumentaria.generarItemPrenda(codMaterial, cantMaterial);
						
						/*
						validaDatos();
						
						//Si tengo todos los datos cargados, busco si existe la prenda
						//if(!bError){
							
							int i;							
							codPrenda = (int)txtCodigo.getValue();						
							
							if(sistemaIndumentaria.buscarPrenda(codPrenda)== null){
								
								//Recorro el vector, si existe lo actualizo, sino lo agrego
								for(i = 0; i< materialesAgregados.length; i++){
									
									if(materialesAgregados[i][1] == codMaterial){
										materialesAgregados[i][2] = cantMaterial;
										bExiste = true; 
									}
								} //for(i = 0; i< materialesAgregados.length; i++){	
								
								if (!bExiste){
									materialesAgregados[i][1] = codMaterial;
									materialesAgregados[i][2] = cantMaterial;
								}
								
								updateTable();
							}else{
								
								lblError.setText("Error: La prenda ya existe");
							} //else if(sistemaIndumentaria.buscarPrenda(codPrenda)== null){
						//} //if(!bError)
						*/
					}
				});				
			}
			
			//Tabla de Materiales
			{

				tblTablaMateriales = new JTable();
				tblContTablaMateriales = new JScrollPane();
				getContentPane().add(tblContTablaMateriales);
				
				tblTablaMateriales.setPreferredScrollableViewportSize(new Dimension(340, 270));
				tblTablaMateriales.setFillsViewportHeight(true);
				tblTablaMateriales.setModel(tblModel());
				tblTablaMateriales.getColumnModel().getColumn(1).setMaxWidth(80);
				tblTablaMateriales.getModel().setValueAt("", 0, 0);
				tblTablaMateriales.getModel().setValueAt("", 0, 1);
				tblContTablaMateriales.setViewportView(tblTablaMateriales);
				tblContTablaMateriales.setBounds(40, 230, 400, 100);				

			}
					
			{ //btnAceptar
				
				btnAceptar = new JButton();
				getContentPane().add(btnAceptar);
				btnAceptar.setText("Aceptar");
				btnAceptar.setBounds(70, 350, 100, 30);
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						sistemaIndumentaria.finalizarAltaPrenda();
					}
				});
			}	
			
			{ //btnCancelar

				btnCancelar = new JButton();
				getContentPane().add(btnCancelar);
				btnCancelar.setText("Cancelar");
				btnCancelar.setBounds(250, 350, 100, 30);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});				
			}
			
			pack();
			setSize(500, 500);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
	private void cargaComboTemporadas() {
		// TODO Auto-generated method stub
		cmbTemporada = new JComboBox();
		getContentPane().add(cmbTemporada);
		cmbTemporada.setBounds(260, 29, 120, 20);
		cmbTemporada.addItem("Sin Temporada");
		cmbTemporada.addItem("Verano");
		cmbTemporada.addItem("Otoño");
		cmbTemporada.addItem("Invierno");
		cmbTemporada.addItem("Primavera");
	} //private void cargaComboTemporadas()
	
	private void cargaComboMateriales() {
		// TODO Auto-generated method stub
		cmbMaterial = new JComboBox<String>();
		getContentPane().add(cmbMaterial);
		cmbMaterial.setBounds(70, 143, 120, 20);
		// TODO Show something if vector is empty
		for(int i = 0; i< materiales.size();i++){
			if(i == 0){
				matSeleccionado = materiales.elementAt(0);
			}
			cmbMaterial.addItem(materiales.elementAt(i).getNombreMaterial());
		}
		//Evento que al seleccionar un material del combo, me guarda el codigo de este en una variable
		cmbMaterial.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	matSeleccionado = materiales.elementAt(cmbMaterial.getSelectedIndex());		
		    	codMaterial = materiales.elementAt(cmbMaterial.getSelectedIndex()).getCodigoMaterial();
		    }
		});;
	}

	private void generarPrendaTemporada() {
		// TODO Auto-generated method stub
		
	}

	private void generarPrendaSinTemporada() {
		
//		if(cmbTemporada.getSelectedItem() == "Sin Temporada")
//			
//			generarPrendaSinTemporada();
//		else
//			
//			generarPrendaTemporada();
//		//if(cmbTemporada.getSelectedItem() == "Sin Temporada")
//		
//		sistemaIndumentaria.AltaPrendaSinTemporada(codPrenda, nombrePrenda, stock, codMaterial, cantMaterial);
//		
	}	
	
	/*private void generarItemPrenda(){
		// TODO Check if matSeleccionado != null		
		if(txtCantMat.getValue() != null || (int)txtCantMat.getValue() == 0){
			int cantMaterial = (int)txtCantMat.getValue();
				sistemaIndumentaria.generarItemPrenda(codPrenda, codMaterial, cantMaterial);
//			if(cantidad <= sistemaIndumentaria.buscarMaterial(codMaterial).getCantStock()){
//				lblError.setText("");
////				sistemaIndumentaria.agregarPrenda(codfac, codPrenda, cantidad);Prenda(codfac, codprendaSeleccionada, cantidad);
//				sistemaIndumentaria.
//				itemsFactura = sistemaIndumentaria.buscarFactura(codfac).getItemfacturas();
//				updateTable();
//				lblPrecioTotal.setText("Total: "+sistemaIndumentaria.buscarFactura(codfac).getPrecioTotal());
//			} else {
//				lblError.setText("Error: Debe ingresar un valor en cantidad")
//			}
		}else{
			
			lblError.setText("Error: Debe ingresar una cantidad v�lida de materiales");
		} //if(txtCantMat.getValue() != null){
		
	}*/
	
	private void validaDatos() {
		// TODO Auto-generated method stub
		if(txtCodigo.getValue() == null){
			lblError.setText("Debe ingresar un c�digo de Prenda v�lido");
			bError = true;
		}else{
			if (bError == true)
				bError = false;					
		}
		
		if(txtNombre.getText() == null){
			lblError.setText("Debe ingresar un nombre de Prenda v�lido");
			bError = true;
		}else{			
			if (bError == true)
				bError = false;					
		}
		
		if(txtStock.getValue() == null){
			lblError.setText("Debe ingresar un stock de Prenda v�lido");
			bError = true;
		}else{			
			if (bError == true)
				bError = false;					
		}
	}

	private AbstractTableModel tblModel(){
		AbstractTableModel model = new AbstractTableModel() {
			private String[] columnNames = {"Material", "Cantidad"};
			private Object[][] data = new Object[materiales.size()][2];
			
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
	
	private void updateTable(){
		
		if(materialesAgregados.length > 0){
			for (int i = 0; i < itemPrendas.size(); i++) {
				int cantidad = itemPrendas.elementAt(i).getCantidad();
				Material material = itemPrendas.elementAt(i).getMaterial();
				tblTablaMateriales.getModel().setValueAt(material.getNombreMaterial(), i, 0);
				tblTablaMateriales.getModel().setValueAt(cantidad, i, 1);
			}
		}
	}
	
	//Formato para que el campo sea s�lo numerico
	private NumberFormatter numberFormater(){
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    return formatter;
	}
} //public class VentanaAltaPrenda extends JFrame
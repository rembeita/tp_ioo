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
	
	private JLabel lblCodigo;
	private JLabel lblTemporada;		
	private JLabel lblNombre;
	private JLabel lblStock;
	private JLabel lblMaterial;
	private JLabel lblCantMat;
	private JLabel lblError;
	
	private JButton btnAceptar;
	private JButton btnSeguir;
	private JButton btnSalir;
	private JButton btnCancelar;
	private JButton btnAgregar;
		
	private JTextField txtNombre;
	private JFormattedTextField txtCodigo;
	private JFormattedTextField txtStock;
	private JFormattedTextField txtCantMat;

	
	private SistemaIndumentaria sistemaIndumentaria;
	private int codMaterial;
	private int[] idsMateriales;
	private int[][] materialesAgregados;                    
	private boolean bError; 


	
	public VentanaAltaPrenda(SistemaIndumentaria sistema){
		
		super();
		
		sistemaIndumentaria = sistema;
		idsMateriales = sistemaIndumentaria.getIdsMateriales();
		materialesAgregados = new int [idsMateriales.length][2];
		cargaComboMateriales();
		cargaComboTemporadas();
		initGUI();		
	
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
				lblCodigo.setText("C�digo: ");
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
						
						validaDatos();
						// Add prenda actual
						if(!bError){
							
							if(cmbTemporada.getSelectedItem() == "Sin Temporada"){
								if(sistemaIndumentaria.AltaPrendaSinTemporada((int)txtCodigo.getValue(), 
									txtNombre.getText(), (int)txtStock.getValue())){
									lblError.setText("");
									// Se pudo generar la prenda temporaria, mostrar carga de materiales
									muestraOcultaAgregaMaterial(true);
								}else{
									lblError.setText("Error: La prenda ya existe");
								}
							}else{
								
								if(sistemaIndumentaria.AltaPrendaDeTemporada((int)txtCodigo.getValue(), 
										txtNombre.getText(), (int)txtStock.getValue(), (String) cmbTemporada.getSelectedItem())){
										lblError.setText("");
										
										// Se pudo generar la prenda temporaria, mostrar carga de materiales
										muestraOcultaAgregaMaterial(true);
								}else{
									lblError.setText("Error: La prenda ya existe");
								}
							}
							materialesAgregados = sistemaIndumentaria.getItemsPrendas();
							updateTable();
						} //if(!bError)
					}
				});
			}

			{
				btnSalir = new JButton();
				getContentPane().add(btnSalir);
				btnSalir.setText("Salir");
				btnSalir.setBounds(260, 100, 80, 23);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
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
						
						if(txtCantMat.getValue() != null && (int)txtCantMat.getValue() > 0){
							codMaterial = idsMateriales[cmbMaterial.getSelectedIndex()];
							
							int cantMaterial = (int)txtCantMat.getValue();
							sistemaIndumentaria.generarItemPrenda(codMaterial, cantMaterial);
							materialesAgregados = sistemaIndumentaria.getItemsPrendas();
							updateTable();					
						}else{
							lblError.setText("Error: Debe ingresar una cantidad v�lida de material");
						}
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
						
						if(materialesAgregados.length>0){
							sistemaIndumentaria.finalizarAltaPrenda();
							lblError.setText("Prenda generada exitosamente !!");
							muestraOcultaAgregaMaterial(false);
							
						}else{
							
							lblError.setText("Error: Debe cargar al menos un material para realizar esta operacion");
						}
												
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
			
			muestraOcultaAgregaMaterial(false);
			pack();
			setSize(500, 470);			
		} catch (Exception e) {
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
		cmbTemporada.addItem("Oto�o");
		cmbTemporada.addItem("Invierno");
		cmbTemporada.addItem("Primavera");
	} //private void cargaComboTemporadas()
	
	private void cargaComboMateriales() {
		cmbMaterial = new JComboBox<String>();
		getContentPane().add(cmbMaterial);
		cmbMaterial.setBounds(70, 143, 120, 20);
		for(int i = 0; i< idsMateriales.length;i++){
			cmbMaterial.addItem(sistemaIndumentaria.getNombreItemPrenda(idsMateriales[i]));
		}
		//Evento que al seleccionar un material del combo, me guarda el codigo de este en una variable
		cmbMaterial.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {		    				
		    	codMaterial = idsMateriales[cmbMaterial.getSelectedIndex()];
		    }
		});
	}
	
	private void validaDatos() {
		
		bError = false;
		lblError.setText("");
		
		if(txtCodigo.getValue() == null || (int)txtCodigo.getValue() == 0){
			
			lblError.setText("Debe ingresar un c�digo de Prenda v�lido");
			bError = true;
		}		
		
		if(txtNombre.getText().length() == 0 && !bError){
			
			lblError.setText("Debe ingresar un nombre de Prenda v�lido");
			bError = true;
		}
		
		if(txtStock.getValue() == null && !bError || (int)txtStock.getValue() == 0 && !bError){
			
			lblError.setText("Debe ingresar un stock de Prenda v�lido");
			bError = true;
		}
	}

	private AbstractTableModel tblModel(){
		AbstractTableModel model = new AbstractTableModel() {
			private String[] columnNames = {"Material", "Cantidad"};
			private Object[][] data = new Object[idsMateriales.length][2];
			
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
		if(idsMateriales.length > 0){
			System.out.println("Materiales agregados: "+materialesAgregados.length);
			if(materialesAgregados.length > 0){
				for (int i = 0; i < materialesAgregados.length; i++) {
					System.out.println("In: "+i);
					tblTablaMateriales.getModel().setValueAt(
						sistemaIndumentaria.getNombreItemPrenda(materialesAgregados[i][0]), i, 0);
					tblTablaMateriales.getModel().setValueAt(materialesAgregados[i][1], i, 1);
				}
			} else {
				// Clean table
				for (int i = 0; i < idsMateriales.length; i++) {
					tblTablaMateriales.getModel().setValueAt("", i, 0);
					tblTablaMateriales.getModel().setValueAt("", i, 1);
				}
			}
		}
	}
	
	//Formato para que el campo sea solo numerico
	private NumberFormatter numberFormater(){
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    return formatter;
	}
	
	private void muestraOcultaAgregaMaterial(boolean bMuestra){
		
		lblMaterial.setVisible(bMuestra);
		cmbMaterial.setVisible(bMuestra);	
		lblCantMat.setVisible(bMuestra);
		txtCantMat.setVisible(bMuestra);
		btnAgregar.setVisible(bMuestra);
		tblContTablaMateriales.setVisible(bMuestra);
		tblTablaMateriales.setVisible(bMuestra);
		
		if(!bMuestra){
			
			txtCodigo.setValue(0);
			txtNombre.setText("");
			txtStock.setValue(0);
			txtCantMat.setValue(0);				
		} //if(!bMuestra)
		

		btnSeguir.setVisible(!bMuestra);
		btnSalir.setVisible(!bMuestra);
		txtCodigo.setEnabled(!bMuestra);
		txtNombre.setEnabled(!bMuestra);
		cmbTemporada.setEnabled(!bMuestra);
		txtStock.setEnabled(!bMuestra);
		
		btnAceptar.setVisible(bMuestra);
		btnCancelar.setVisible(bMuestra);

	}
}
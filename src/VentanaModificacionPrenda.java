
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class VentanaModificacionPrenda extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel lblError;
	private JLabel mensaje = new JLabel();
	
	private JButton buscar;
	private JTextField codigoPrenda;
	private JButton modificar;
	private JTextField stock;
	private JTextField nombre_prenda;
	private JTextField codigo;
	private JTextField precio_prenda;

	
	private SistemaIndumentaria sistema;
//	private int codprenda;
//	private prendaDeTemporada prendacon;
//	private AlumnoView alumno;

	
	
	
	public VentanaModificacionPrenda(SistemaIndumentaria sistemaindu) {
		super();
		sistema = sistemaindu;
		initGUI();	
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			//Visualizador de Errores
			{
				lblError = new JLabel();
				getContentPane().add(lblError);
				lblError.setBounds(5, 0, 450, 20);
				lblError.setForeground(new Color(255, 0, 0));
			}
			
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Codigo:");
				jLabel1.setBounds(21, 52, 63, 28);
				jLabel1.setVisible(false);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Nombre:");
				jLabel2.setBounds(21, 101, 63, 28);
				jLabel2.setVisible(false);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Stock:");
				jLabel3.setBounds(21, 157, 63, 28);
				jLabel3.setVisible(false);
			}
			{
				precio_prenda = new JTextField();
				getContentPane().add(precio_prenda);
				precio_prenda.setBounds(119, 207, 210, 28);
				precio_prenda.setVisible(false);
			}
			{
				codigo = new JTextField();
				getContentPane().add(codigo);
				codigo.setBounds(119, 52, 210, 28);
				codigo.setVisible(false);
			}
			{
				nombre_prenda = new JTextField();
				getContentPane().add(nombre_prenda);
				nombre_prenda.setBounds(119, 101, 210, 28);
				nombre_prenda.setVisible(false);
			}
			{
				stock = new JTextField();
				getContentPane().add(stock);
				stock.setBounds(119, 157, 210, 28);
				stock.setVisible(false);
			}
			{
				modificar = new JButton();
				getContentPane().add(modificar);
				modificar.setText("Ok");
				modificar.setBounds(273, 277, 63, 28);
				modificar.setVisible(false);
				modificar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						boolean respuesta = sistema.modificarPrenda(Integer.parseInt(codigoPrenda.getText()), 
								(String)nombre_prenda.getText(), Integer.parseInt(stock.getText()), 
								Float.parseFloat(precio_prenda.getText()));
						
						if (respuesta == true)
						{
							getContentPane().add(mensaje);
							mensaje.setText("La prenda se modifico correctamente");
							mensaje.setBounds(119, 52, 210, 28);
							mensaje.setVisible(true);
							jLabel1.setVisible(false);
							jLabel2.setVisible(false);
							jLabel3.setVisible(false);
							jLabel4.setVisible(true);

						}
						limpiarpantalla();
					}
				});
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Ingrese Codigo:");
				jLabel4.setBounds(21, 17, 98, 28);

			}
			
			{
				codigoPrenda = new JTextField();
				getContentPane().add(codigoPrenda);
				codigoPrenda.setBounds(140, 17, 147, 28);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Precio:");
				jLabel5.setBounds(21, 200, 98, 28);
				jLabel5.setVisible(false);
			}
			{
				buscar = new JButton();
				getContentPane().add(buscar);
				buscar.setText("Buscar");
				buscar.setBounds(301, 17, 77, 28);
				buscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						if(codigoPrenda.getText().length() == 0){
						
							lblError.setText("Error: Debe ingresar un código válido");							
						}else{
							
							int codigoPrendaFormatted = Integer.parseInt(codigoPrenda.getText());

							System.out.println("Prenda: " + sistema.buscarPrenda(codigoPrendaFormatted));

							if (sistema.buscarPrenda(codigoPrendaFormatted) != null)
							{
								jLabel1.setVisible(true);
								jLabel2.setVisible(true);
								jLabel3.setVisible(true);
								jLabel5.setVisible(true);
								lblError.setText("");
								
								codigo.setVisible(true);
								codigo.setEnabled(false);
								codigo.setText(Integer.toString(sistema.buscarPrenda(codigoPrendaFormatted).getCodigoPrenda()));
								nombre_prenda.setVisible(true);
								nombre_prenda.setText(sistema.buscarPrenda(codigoPrendaFormatted).getNombrePrenda());
								stock.setVisible(true);
								stock.setText(Integer.toString(sistema.buscarPrenda(codigoPrendaFormatted).getStockPrenda()));
								modificar.setVisible(true);
								precio_prenda.setVisible(true);
								precio_prenda.setEnabled(false);
								precio_prenda.setText(Float.toString(sistema.buscarPrenda(codigoPrendaFormatted).getPrecioPrenda()));
							}
							else
							{
								lblError.setText("Error: Prenda no encontrada");
								limpiarpantalla();
							} //else - if (sistema.buscarPrenda(codigoPrendaFormatted) != null)							
						} // else - if(codigoPrenda.getText() == "" || Integer.parseInt(codigoPrenda.getText()) <= 0)
					}
				});
			}
			pack();
			setSize(400, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void limpiarpantalla() 
	{
		jLabel1.setVisible(false);
		jLabel2.setVisible(false);
		jLabel3.setVisible(false);
		jLabel5.setVisible(false);

		codigo.setVisible(false);
		nombre_prenda.setVisible(false);
		stock.setVisible(false);
		precio_prenda.setVisible(false);

		modificar.setVisible(false);
		//jLabel4.setVisible(true);
		codigoPrenda.setText("");
		
	}

}


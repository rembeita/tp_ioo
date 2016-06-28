import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;


public class VentanaMenuPrincipal extends javax.swing.JFrame
{

	private static final long serialVersionUID = 1L;
	private JMenuBar jBarradeMenues;
	private JMenu jMenuVenderPrendas;
	private JMenu jMenuPrendas;
	private JMenu jMenuPrendasAlta;
	private JMenuItem jMenuPrendasItemAlta;
	private JMenuItem jMenuPrendasItemBaja;
	private JMenuItem jMenuPrendasItemModificacion;
	private JMenu jMenuListadoOC;
	private JMenuItem jMenuListadosItemListarPrendas;	
	private JMenuItem jMenuListadosItemListarOCs;
	private JMenu jMenuChequearPtoRep;
	private JMenu jMenuSalir;	
	
	private SistemaIndumentaria sistemaIndumentaria;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		VentanaMenuPrincipal ventanaPpal = new VentanaMenuPrincipal();
		ventanaPpal.setVisible(true);
	}
	
	public VentanaMenuPrincipal() {
		super();
		initGUI();
		sistemaIndumentaria = new SistemaIndumentaria();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jBarradeMenues = new JMenuBar();
				setJMenuBar(jBarradeMenues);
				jBarradeMenues.setPreferredSize(new java.awt.Dimension(800, 22));
				{						
					{//Menu - ABM Prendas

						jMenuPrendas = new JMenu();
						jBarradeMenues.add(jMenuPrendas);
						jMenuPrendas.setText("ABM Prendas");
						//Segundo nivel de Menu	
						{	
							//Menu - Ata
							jMenuPrendasItemAlta = new JMenuItem();
							jMenuPrendas.add(jMenuPrendasItemAlta);
							jMenuPrendasItemAlta.setText("Alta Prenda");
							jMenuPrendasItemAlta.addActionListener(new ActionListener() {
								
								public void actionPerformed(ActionEvent e) {
									VentanaAltaPrenda vtnAltaprenda = new VentanaAltaPrenda(sistemaIndumentaria);
									vtnAltaprenda.setVisible(true);									
								}
							});
														
							//Menu - Baja
							jMenuPrendasItemBaja = new JMenuItem();
							jMenuPrendas.add(jMenuPrendasItemBaja);
							jMenuPrendasItemBaja.setText("Baja Prenda");
							jMenuPrendasItemBaja.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) 
								{
//										Baja Prenda
								}
							});											
						
							//Menu - Modificacion
							jMenuPrendasItemModificacion = new JMenuItem();
							jMenuPrendas.add(jMenuPrendasItemModificacion);
							jMenuPrendasItemModificacion.setText("Modificar Prenda");
							jMenuPrendasItemModificacion.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) 
								{
									VentanaModificacionPrenda vtnModificacionprenda = new VentanaModificacionPrenda(sistemaIndumentaria);
									vtnModificacionprenda.setVisible(true);
								}
							});
						}
					}//FIN Menu - 	ABM Prendas

					{//Menu - Vender Prendas
					jMenuVenderPrendas = new JMenu();
					jBarradeMenues.add(jMenuVenderPrendas);
					jMenuVenderPrendas.setText("Venta Prenda");
					jMenuVenderPrendas.addMenuListener(new MenuListener() {
							public void menuSelected(MenuEvent evt) {
								VentanaAltaPrenda vtnAltaprenda = new VentanaAltaPrenda(sistemaIndumentaria);
								vtnAltaprenda.setVisible(true);	
							}
							public void menuDeselected(MenuEvent evt) {
								
							}
							public void menuCanceled(MenuEvent evt) {
								
							}
						});
					}//FIN Menu - Vender Prendas					
					
					{//Menu - Listados

						jMenuListadoOC= new JMenu();
						jBarradeMenues.add(jMenuListadoOC);
						jMenuListadoOC.setText("Listado OC");
						jMenuListadoOC.addMenuListener(new MenuListener() {
							public void menuSelected(MenuEvent evt) {
								VentanaOC ventanaoc = new VentanaOC();
								ventanaoc.setVisible(true);
							}
							public void menuDeselected(MenuEvent evt) {
								
							}
							public void menuCanceled(MenuEvent evt) {
								
							}
						});
						
					}//FIN Menu - Listados					
					
					{//Menu - Chequear Pto. Reposicion
						
						jMenuChequearPtoRep = new JMenu();
						jBarradeMenues.add(jMenuChequearPtoRep);
						jMenuChequearPtoRep.setText("Chequear Pto. Rep.");
						jMenuChequearPtoRep.addMenuListener(new MenuListener() {
							
							public void menuSelected(MenuEvent arg0) {							
								// CHequear Pto Rep
								
							}
							public void menuDeselected(MenuEvent arg0) {
								
							}
							public void menuCanceled(MenuEvent arg0) {
								
							}
						});
					}//FIN Menu - Chequear Pto. Reposicion
					
					{//Menu - Salir
						jMenuSalir = new JMenu();
						jBarradeMenues.add(jMenuSalir);
						jMenuSalir.setText("Salir");
						jMenuSalir.addMenuListener(new MenuListener() {
							public void menuSelected(MenuEvent evt) {
								System.exit(0);
							}
							public void menuDeselected(MenuEvent evt) {
								
							}
							public void menuCanceled(MenuEvent evt) {
								
							}
						});
					}//FIN Menu - Salir															
				} //jBarradeMenues.setPreferredSize(new java.awt.Dimension(500, 22))
			} //setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
			pack();
			setSize(450, 300);
		} catch (Exception e) {
			e.printStackTrace();
		} //Try - Catch
		
	} //private void initGUI()

} //public class VentanaMenuPrincipal extends javax.swing.JFrame

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Scanner;
//import java.util.Vector;
//
//public class MainSistemaIndumentaria 
//{
//	private SistemaIndumentaria sistemaindumentaria;
//	
//	public MainSistemaIndumentaria()
//	{
//		sistemaindumentaria = new SistemaIndumentaria();
//	}
//	
//	/*public static void main(String[] args) 
//	{
//		MainSistemaIndumentaria sistema = new MainSistemaIndumentaria();
//		sistema.mostrarMenu();
//
//	}*/
//
//	public void mostrarMenu() 
//	{
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//	 	
//		//Imprimo menu de opciones
//		System.out.println("MENU DE OPCIONES");
//		System.out.println("-------------------------------------------------------");
//		System.out.println("1.- ABM Prenda");
//		System.out.println("2.- Venta de Prendas");
//		System.out.println("3.- Listar Prendas");
//		System.out.println("4.- Chequear punto de reposicion");
//		System.out.println("5.- Listar Ordenes de Compra");
//		System.out.println("9.- Salir");
//		System.out.println("-------------------------------------------------------");
//		System.out.print("Opcion:");
//		try
//		{
//			char s = (char)reader.read();
//			
//		  	switch (s)
//		  	{
//		  		case '1' : 
//		  		{
//		  			this.mostrarMenuABMPrendas();
//		  			this.mostrarMenu();
//		  			break;
//		  		}
//			  	case '2' : 
//			  	{
//			  		this.ventaPrendas();
//			  		this.mostrarMenu();
//			  		break;
//			  	}
//			  	case '3' : 
//			  	{
//			  		this.listarPrendas(true);
//			  		this.mostrarMenu();//claguirre
//			  		break;
//			  	}
//			  	
//			  	case '4' :
//			  	{
//			  		sistemaindumentaria.ControlarStockMateriales();
//			  		this.mostrarMenu();
//			  		break;
//			  	}
//			  	
//			  	case '5' :
//			  	{
//			  		sistemaindumentaria.ListarOrdenesDeCompra();
//			  		this.mostrarMenu();
//			  		break;
//			  	}
//		
//			  	case '9' : 
//			  	{
//			  		this.salir();
//			  	}
//		  	}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//	}
//	
//	public void mostrarMenuABMPrendas() 
//	{
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//	 	
//		//Imprimo menu de opciones
//		System.out.println("MENU DE OPCIONES - ABM PRENDAS");
//		System.out.println("-------------------------------------------------------");
//		System.out.println("1.- Dar de Alta una Prenda");
//		System.out.println("2.- Dar de Baja una Prenda");
//		System.out.println("3.- Modificar una prenda");
//		System.out.println("4.- Listar prendas");
//		System.out.println("9.- Salir");
//		System.out.println("-------------------------------------------------------");
//		System.out.print("Opcion:");
//		try
//		{
//			char s = (char)reader.read();
//			
//		  	switch (s)
//		  	{
//		  		case '1' : 
//		  		{
//		  			this.crearPrenda();
//		  			break;
//		  		}
//			  	case '2' : 
//			  	{
//			  		this.bajaPrenda();
//			  		break;
//			  	}
//			  	case '3' : 
//			  	{
//			  		this.modificarPrenda2();
//			  		break;
//			  	}
//			  	case '4' : 
//			  	{
//			  		this.listarPrendas(true);
//			  		this.mostrarMenuABMPrendas();//claguirre
//			  		break;
//			  	}
//			  	case '9' : 
//			  	{
//			  		this.salir();
//			  	}
//		  	}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//	}
//		
//	public void crearPrenda() 
//	{
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//	 	
//		//Imprimo menu de opciones
//		System.out.println("MENU DE OPCIONES - ABM PRENDAS - CREAR PRENDA");
//		System.out.println("-------------------------------------------------------");
//		System.out.println("1.- Prenda de Verano");
//		System.out.println("2.- Prenda de Invierno");
//		System.out.println("3.- Prenda de Oto�o");
//		System.out.println("4.- Prenda de Primavera");
//		System.out.println("5.- Prenda sin Temporada");
//		System.out.println("9.- Salir");
//		System.out.println("-------------------------------------------------------");
//		System.out.print("Opcion:");
//		try
//		{
//			char s = (char)reader.read();
//			
//		  	switch (s)
//		  	{
//		  		case '1' : 
//		  		{
//		  			if(	this.AltaPrendaDeTemporada("verano"))
//			  		{
//			  			System.out.println("###PRENDA GENERADA CORRECTAMENTE###");
//			  		}
//			  		else
//			  		{
//			  			System.out.println("Prenda no se pudo generar");
//			  		}	
//		  			break;
//		  		}
//			  	case '2' : 
//			  	{
//			  		if(	this.AltaPrendaDeTemporada("invierno"))
//			  		{
//			  			System.out.println("###PRENDA GENERADA CORRECTAMENTE###");
//			  		}
//				  	else
//				  	{
//				  		System.out.println("Prenda no se pudo generar");
//				  	}
//		  		break;			  		
//			  	}
//			  	case '3' : 
//			  	{
//		  			if(	this.AltaPrendaDeTemporada("otono"))
//			  		{
//			  			System.out.println("###PRENDA GENERADA CORRECTAMENTE###");
//			  		}
//			  		else
//			  		{
//			  			System.out.println("Prenda no se pudo generar");
//			  		}	
//		  			break;
//			  		
//			  	}
//			  	case '4' : 
//			  	{
//		  			if(	this.AltaPrendaDeTemporada("primavera"))
//			  		{
//			  			System.out.println("###PRENDA GENERADA CORRECTAMENTE###");
//			  		}
//			  		else
//			  		{
//			  			System.out.println("Prenda no se pudo generar");
//			  		}	
//		  			break;
//			  		
//			  	}
//			  	case '5' : 
//			  	{
//			  		
//			  		if(	this.AltaPrendaSinTemporada())
//			  		{
//			  			System.out.println("###PRENDA GENERADA CORRECTAMENTE###");
//			  		}
//			  		else
//			  		{
//			  			System.out.println("Prenda no se pudo generar");
//			  		}
//			  		
//			  		break;
//			  	}
//		
//			  	case '9' : 
//			  	{
//			  		this.salir();
//			  	}
//		  	}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//	}
//	
//	public void salir() 
//	{
//		System.exit(0);
//	}
//	
//
//	public boolean AltaPrendaSinTemporada()
//	{
//		int stock, codigo;
//		boolean finCargaMateriales = true;
//		Prenda prendainstanciada;
//		
//		System.out.println("##### DANDO DE ALTA PRENDA SIN TEMPORADA #####");
//		System.out.print("Ingrese el codigo:");
//		Scanner scanCodigo = new Scanner(System.in);
//		String strCodigo = scanCodigo.nextLine();
//		codigo = Integer.parseInt(strCodigo);
//		if (sistemaindumentaria.buscarPrenda(codigo) != null)
//		{
//			System.out.println("El codigo ya existe!");
//			return false;
//		}
//		
//		System.out.print("Ingrese el nombre de la prenda:");
//		Scanner scanPrenda = new Scanner(System.in);
//		String nombrePrenda = scanPrenda.nextLine();
//		
//		System.out.print("Ingrese la cantidad de stock:");
//		Scanner scanStock = new Scanner(System.in);
//		String strStock = scanStock.nextLine();
//		stock = Integer.parseInt(strStock);
//		
//		System.out.print("Ingrese el codigo del material:");
//		Scanner scanCodMaterial = new Scanner(System.in);
//		String strCodMaterial = scanCodMaterial.nextLine();
//		int codMaterial = Integer.parseInt(strCodMaterial);
//		Material materialbuscado = sistemaindumentaria.buscarMaterial(codMaterial);
//		if (materialbuscado == null)
//		{
//			System.out.println("Mensaje: No se encontro el material.");
//			return false;
//		}
//		
//		System.out.print("Ingrese la cantidad de ese material:");
//		Scanner scanCantMaterial = new Scanner(System.in);
//		String strCantMaterial = scanCantMaterial.nextLine();
//		int cantMaterial = Integer.parseInt(strCantMaterial);
//		
//		if (cantMaterial < 1)
//		{
//			System.out.println("Mensaje: La cantidad de material debe ser mayor a 0.");
//			return false;
//		}
//				
//		prendainstanciada = sistemaindumentaria.AltaPrendaSinTemporada(codigo, nombrePrenda, stock, materialbuscado, cantMaterial);
//		
//		System.out.print("Desea seguir cargando materiales?: (s/n)");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		try {
//			char s = (char)reader.read();
//			if ( s != 's')
//			{
//				finCargaMateriales = false;
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//		
//		while (finCargaMateriales)
//		{
//			System.out.print("Ingrese el codigo del material:");
//			strCodMaterial = scanCodMaterial.nextLine();
//			codMaterial = Integer.parseInt(strCodMaterial);
//			materialbuscado = sistemaindumentaria.buscarMaterial(codMaterial);
//			if (materialbuscado == null)
//			{
//				System.out.println("Mensaje: No se encontro el material.");
//				return false;
//			}
//			
//			System.out.print("Ingrese la cantidad de ese material:");
//			scanCantMaterial = new Scanner(System.in);
//			strCantMaterial = scanCantMaterial.nextLine();
//			cantMaterial = Integer.parseInt(strCantMaterial);
//			
//			if (cantMaterial < 1)
//			{
//				System.out.println("Mensaje: La cantidad de material debe ser mayor a 0.");
//				return false;
//			}
//			
//			prendainstanciada.agregarItemPrenda(cantMaterial, materialbuscado);
//			
//			System.out.print("Desea seguir cargando materiales?: (s/n)");
//			reader = new BufferedReader(new InputStreamReader(System.in));
//
//			try {
//				char s = (char)reader.read();
//				if ( s != 's')
//				{
//					finCargaMateriales = false;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		// Controlar el stock de materiales
//		sistemaindumentaria.ControlarStockMateriales();
//		
//		return true;
//	}
//	
//	
//	public boolean AltaPrendaDeTemporada(String epoca)
//	{
//		int stock, codigo;
//		boolean respuesta, finCargaMateriales=true;
//		Prenda prendainstanciada;
//		
//		System.out.println("##### DANDO DE ALTA PRENDA DE TEMPORADA DE "+epoca+" #####");
//		System.out.print("Ingrese el codigo:");
//		Scanner scanCodigo = new Scanner(System.in);
//		String strCodigo = scanCodigo.nextLine();
//		codigo = Integer.parseInt(strCodigo);
//		if (sistemaindumentaria.buscarPrenda(codigo) != null)
//		{
//			System.out.println("El codigo ya existe!");
//			return false;
//		}
//		
//		System.out.print("Ingrese el nombre de la prenda:");
//		Scanner scanPrenda = new Scanner(System.in);
//		String nombrePrenda = scanPrenda.nextLine();
//		
//		System.out.print("Ingrese la cantidad de stock:");
//		Scanner scanStock = new Scanner(System.in);
//		String strStock = scanStock.nextLine();
//		stock = Integer.parseInt(strStock);
//		
//		System.out.print("Ingrese el codigo del material:");
//		Scanner scanCodMaterial = new Scanner(System.in);
//		String strCodMaterial = scanCodMaterial.nextLine();
//		int codMaterial = Integer.parseInt(strCodMaterial);
//		Material materialbuscado = sistemaindumentaria.buscarMaterial(codMaterial);
//		if (materialbuscado == null)
//		{
//			System.out.println("Mensaje: No se encontro el material.");
//			return false;
//		}
//		
//		System.out.print("Ingrese la cantidad de ese material:");
//		Scanner scanCantMaterial = new Scanner(System.in);
//		String strCantMaterial = scanCantMaterial.nextLine();
//		int cantMaterial = Integer.parseInt(strCantMaterial);
//		
//		if (cantMaterial < 1)
//		{
//			System.out.println("Mensaje: La cantidad de material debe ser mayor a 0.");
//			return false;
//		}
//				
//		prendainstanciada = sistemaindumentaria.AltaPrendaDeTemporada(codigo, nombrePrenda, stock, materialbuscado, cantMaterial, epoca);
//		
//		System.out.print("Desea seguir cargando materiales?: (s/n)");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		try {
//			char s = (char)reader.read();
//			if ( s != 's')
//			{
//				finCargaMateriales = false;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		
//		
//		while (finCargaMateriales)
//		{
//			System.out.print("Ingrese el codigo del material:");
//			strCodMaterial = scanCodMaterial.nextLine();
//			codMaterial = Integer.parseInt(strCodMaterial);
//			materialbuscado = sistemaindumentaria.buscarMaterial(codMaterial);
//			if (materialbuscado == null)
//			{
//				System.out.println("Mensaje: No se encontro el material.");
//				return false;
//			}
//			
//			System.out.print("Ingrese la cantidad de ese material:");
//			scanCantMaterial = new Scanner(System.in);
//			strCantMaterial = scanCantMaterial.nextLine();
//			cantMaterial = Integer.parseInt(strCantMaterial);
//			
//			if (cantMaterial < 1)
//			{
//				System.out.println("Mensaje: La cantidad de material debe ser mayor a 0.");
//				return false;
//			}
//			
//			prendainstanciada.agregarItemPrenda(cantMaterial, materialbuscado);
//			
//			System.out.print("Desea seguir cargando materiales?: (s/n)");
//			reader = new BufferedReader(new InputStreamReader(System.in));
//
//			try {
//				char s = (char)reader.read();
//				if ( s != 's')
//				{
//					finCargaMateriales = false;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		// Controlar el stock de materiales
//		sistemaindumentaria.ControlarStockMateriales();
//		
//		return true;
//	}
//	
//	public void listarPrendas(boolean ventas)
//	{
//		System.out.println("LISTADO DE PRENDAS");
//		System.out.println("-------------------------------------------------------");
//		Vector<Prenda>mostrarprendas;
//		mostrarprendas = sistemaindumentaria.getPrendas();
//		
//		for (int i=0; i<mostrarprendas.size();i++)
//		{
//			//Ahora controlo que el codigo no este dado de baja
//			if(mostrarprendas.elementAt(i).getCodigoPrenda()>0){
//				if (ventas == true || mostrarprendas.elementAt(i).getStockPrenda() > 0)	
//				{
//				System.out.println("CODIGO: " + mostrarprendas.elementAt(i).getCodigoPrenda() + " NOMBRE: "
//						+ mostrarprendas.elementAt(i).getNombrePrenda() + " STOCK: " 
//						+ mostrarprendas.elementAt(i).getStockPrenda() + " PRECIO: " 
//						+ mostrarprendas.elementAt(i).getPrecioPrenda());
//				}
//				else if (ventas == false)
//				{
//					System.out.println("CODIGO: " + mostrarprendas.elementAt(i).getCodigoPrenda() + " NOMBRE: "
//							+ mostrarprendas.elementAt(i).getNombrePrenda() + " STOCK: " 
//							+ mostrarprendas.elementAt(i).getStockPrenda() + " PRECIO: " 
//							+ mostrarprendas.elementAt(i).getPrecioPrenda());
//				}
//			} //if(mostrarprendas.elementAt(i).getCodigoPrenda()>0)
//		} //for (int i=0; i<mostrarprendas.size();i++)
//		System.out.println("-------------------------------------------------------");
//	}
//
//	public void bajaPrenda()
//	{
//		//int stock, codigo; -> No usamos stock en este metodo
//		int codigo;
//		boolean respuesta;
//		
//		System.out.println("##### DANDO DE BAJA PRENDA DE TEMPORADA #####");
//		System.out.print("Ingrese el codigo:");
//		Scanner scanCodigo = new Scanner(System.in);
//		String strCodigo = scanCodigo.nextLine();
//		codigo = Integer.parseInt(strCodigo);
//		
//		respuesta = sistemaindumentaria.bajaPrenda(codigo);
//		if (respuesta == false)
//		{
//			System.out.println("Mensaje: No se encontro la prenda.");
//		}
//		else
//		{
//			System.out.println("Mensaje: Se elimino la prenda correctamente.");
//		
//		}
//	}
//
//	public boolean modificarPrenda()
//	{
//		int stock, codigo;
//		boolean respuesta, finCargaMateriales=true;
//		Prenda prendainstanciada;
//		
//		System.out.println("##### MODIFICANDO PRENDA #####");
//		System.out.print("Ingrese el codigo:");
//		Scanner scanCodigo = new Scanner(System.in);
//		String strCodigo = scanCodigo.nextLine();
//		codigo = Integer.parseInt(strCodigo);
//		prendainstanciada = sistemaindumentaria.buscarPrenda(codigo);
//		
//		if (prendainstanciada == null)
//		{
//			System.out.println("No se encontro la prenda");
//			return false;
//		}
//		
//		System.out.print("Ingrese el nombre de la prenda:");
//		Scanner scanPrenda = new Scanner(System.in);
//		String nombrePrenda = scanPrenda.nextLine();
//		prendainstanciada.setNombrePrenda(nombrePrenda);
//		
//		System.out.print("Ingrese la cantidad de stock:");
//		Scanner scanStock = new Scanner(System.in);
//		String strStock = scanStock.nextLine();
//		stock = Integer.parseInt(strStock);
//		prendainstanciada.setStockPrenda(stock);
//		
//		Scanner scanCodMaterial = null, scanCantMaterial = null;
//		String strCodMaterial, strCantMaterial;
//		int codMaterial, cantMaterial;
//		Material materialbuscado;
//				
//		
//		System.out.print("Desea cargar materiales?: (s/n)");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		try {
//			char s = (char)reader.read();
//			if ( s != 's')
//			{
//				finCargaMateriales = false;
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		while (finCargaMateriales)
//		{
//						
//			System.out.print("Ingrese el codigo del material:");
//			scanCodMaterial = new Scanner(System.in);
//			strCodMaterial = scanCodMaterial.nextLine();
//			codMaterial = Integer.parseInt(strCodMaterial);
//			materialbuscado = sistemaindumentaria.buscarMaterial(codMaterial);
//			if (materialbuscado == null)
//			{
//				System.out.println("Mensaje: No se encontro el material.");
//				return false;
//			}
//			
//			System.out.print("Ingrese la cantidad de ese material:");
//			scanCantMaterial = new Scanner(System.in);
//			strCantMaterial = scanCantMaterial.nextLine();
//			cantMaterial = Integer.parseInt(strCantMaterial);
//			
//			if (cantMaterial < 1)
//			{
//				System.out.println("Mensaje: La cantidad de material debe ser mayor a 0.");
//				return false;
//			}
//			
//			prendainstanciada.agregarItemPrenda(cantMaterial, materialbuscado);
//			
//		    System.out.print("Desea seguir cargando materiales?: (s/n)");
//			reader = new BufferedReader(new InputStreamReader(System.in));
//
//			try {
//				char s = (char)reader.read();
//				if ( s != 's')
//				{
//					finCargaMateriales = false;
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		
//		return true;
//	}
//	
//	public boolean modificarPrenda2(){
//		
//		int codigoPrenda;
//		int stockPrenda;
//		String nombrePrenda;
//		String temporadaPrenda;
//		
//		System.out.print("Ingrese el codigo de la prenda");
//		Scanner ScanCodigo = new Scanner(System.in);
//		String strCodigo = ScanCodigo.nextLine();
//		codigoPrenda = Integer.parseInt(strCodigo);
//		
//		if(sistemaindumentaria.buscarPrenda(codigoPrenda) != null){
//
//			
//		}else{
//			
//			System.out.println("Prenda no encontrada");
//			this.mostrarMenuABMPrendas();
//		}
//		
//		return true;		
//	}
//	
//	
//	public boolean ventaPrendas() 
//	{
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		boolean compraEnProceso=true;
//		//true es igual a vista cliente
//		this.listarPrendas(true);
//		Prenda prenda;
//		Scanner scanEntrada;
//		int codigo, cantidadPrenda;
//		String respuesta;
//		
//		System.out.print("Ingrese el nombre del cliente:");
//		Scanner scanCliente = new Scanner(System.in);
//		String nombreCliente = scanCliente.nextLine();
//		
//		int codFactura = sistemaindumentaria.generarFactura(10, nombreCliente);
//		Factura factura = sistemaindumentaria.buscarFactura(codFactura);
//		
//		while (compraEnProceso)
//		{
//			System.out.print("Ingrese el codigo:");
//			scanEntrada = new Scanner(System.in);
//			codigo = Integer.parseInt(scanEntrada.nextLine());
//		
//				prenda = sistemaindumentaria.buscarPrenda(codigo);
//				if ( prenda == null)
//				{
//					System.out.println("El codigo no existe!");
//					return false;
//				}
//
//				System.out.print("Ingrese cantidad:");
//				scanEntrada = new Scanner(System.in);
//				cantidadPrenda = Integer.parseInt(scanEntrada.nextLine());
//				if (cantidadPrenda > prenda.getStockPrenda())
//				{
//					System.out.println("No hay suficiente Stock");
//						
//				}
//				else
//				{
//					factura.incorporarItemFactura(cantidadPrenda, prenda);
//				}
//				
//			
//				System.out.print("Desea seguir comprando?: (s/n)");
//				reader = new BufferedReader(new InputStreamReader(System.in));
//				try {
//					char s = (char)reader.read();
//					if ( s != 's')
//					{
//						compraEnProceso = false;
//					}
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
//		this.imprimirFactura(factura);
//		return true;
//	}
//	
//	public void imprimirFactura(Factura factura)	
//	{
//		System.out.println("IMPRIMIENDO FACTURA...");
//		System.out.println("-------------------------------------------------------");
//		System.out.println("NRO FACTURA: " + factura.getNroFactura());
//		System.out.println("CLIENTE: " + factura.getNombreCliente());
//		System.out.println("LOCAL: " + factura.getNombreLocal());
//		System.out.println("FECHA: " + factura.getFecha());
//		
//		for (int i = 0; i < factura.getItemfacturas().size() ; i++)
//		{
//			System.out.println("NOMBRE ARTICULO: " + 
//					factura.getItemfacturas().elementAt(i).getPrenda().getNombrePrenda());
//			System.out.println("CANTIDAD: " + 
//					factura.getItemfacturas().elementAt(i).getCantidadComprada());
//			System.out.println("PRECIO: " + 
//					factura.getItemfacturas().elementAt(i).getPrenda().getPrecioPrenda() *
//					factura.getItemfacturas().elementAt(i).getCantidadComprada());
//			
//		}
//		
//		System.out.println("PRECIO TOTAL: " + factura.getPrecioTotal());
//		System.out.println("-------------------------------------------------------");
//		
//	}
//	
//}

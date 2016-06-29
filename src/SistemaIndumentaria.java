import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class SistemaIndumentaria 
{
	private Vector<Prenda>prendas;
	private Vector<Material>materiales;
	private Vector<Proveedor>proveedores;
	private Vector<Material>materialesAPedir;
	private Vector<ordenDeCompra>ordenesDeCompra;
	private Vector<Factura>facturas;
	private Vector<itemOC>itemsOC;
	private Prenda prendaActual;
	
	private void cargaInicial()
	{
		Proveedor proveedor1 = new Proveedor("Cachito SA", 5);
		Proveedor proveedor2 = new Proveedor("Pepito SA", 2);
		proveedores.add(proveedor1);
		proveedores.add(proveedor2);

		Material material1 = new Material(10, "botones", 80, proveedor1, (float) 5.5, 100, 20);
		Material material2 = new Material(20, "algodon", 80, proveedor2, (float) 8.5, 200, 10);
		Material material3 = new Material(30, "hilo", 80, proveedor2, (float) 2.5, 200, 10);
		Material material4 = new Material(40, "cierre", 80, proveedor1, (float) 1.5, 200, 10);
		Material material5 = new Material(50, "elastico", 80, proveedor2, (float) 11.75, 200, 10);
		Material material6 = new Material(60, "cordon", 80, proveedor1, (float) 5.5, 200, 10);
		Material material7 = new Material(70, "tachas", 80, proveedor1, (float) 1.85, 200, 10);
		
		materiales.add(material1);
		materiales.add(material2);
		materiales.add(material3);
		materiales.add(material4);
		materiales.add(material5);
		materiales.add(material6);
		materiales.add(material7);
		
		itemOC itemOC1 = new itemOC(material1, 3);
		
//		ordenDeCompra oc1 = new ordenDeCompra(100, "28/06/2016", 
		
	}
	
	public SistemaIndumentaria()
	{
		prendas = new Vector<Prenda>();
		materiales = new Vector<Material>();
		proveedores = new Vector<Proveedor>();
		materialesAPedir = new Vector<Material>();
		ordenesDeCompra = new Vector<ordenDeCompra>();
		facturas = new Vector<Factura>();
		cargaInicial();
	}

	public boolean AltaPrendaSinTemporada(int codigo, String nombrePrenda, int stock){
		if(this.buscarPrenda(codigo) == null){
			prendaActual = new PrendaSinTemporada(codigo, nombrePrenda, stock);
			return true;
		}
		return false;
	}
	
	public boolean AltaPrendaDeTemporada(int codigo, String nombrePrenda, int stock, String epoca)
	{
		if(this.buscarPrenda(codigo) == null){
			prendaActual = new prendaDeTemporada(codigo, nombrePrenda, stock, epoca);
			return true;
		}
		return false;
	}	
	
	public void ControlarStockMateriales()
	{
		for (int i = 0; i < materiales.size(); i++) {
			Material mat = materiales.elementAt(i);
			if(mat.getCantStock() <= mat.getPuntoDeReposicion())
			{
				materialesAPedir.add(mat);
			}
		}
		if(materialesAPedir.size()>0)
		{
			Vector<Material>aux;
			aux = new Vector<Material>();
			aux.add(materialesAPedir.elementAt(0));
			materialesAPedir.removeElementAt(0);
			int count = 0;
			while(aux.size() > 0)
			{
				for (int i = 0; i < materialesAPedir.size(); i++)
				{
					Material mat = materialesAPedir.elementAt(i);
					if(mat.getProveedor().getCodigoProveedor() == aux.elementAt(0).getProveedor().getCodigoProveedor())
					{
						aux.add(mat);
						materialesAPedir.removeElementAt(i);
						i--;
					}
				}
				// Crear Orden de compra
				this.generarOrdenDeCompra(aux);
				count++;
				aux.removeAllElements();
				if(materialesAPedir.size() > 0)
				{
					aux.add(materialesAPedir.elementAt(0));
					materialesAPedir.removeElementAt(0);
				}
			}
			System.out.println("Se generaron: "+count+" ordenes de compras");
		}
	}
	
	public void generarOrdenDeCompra(Vector<Material> materiales)
	{
		Vector<itemOC>itemocs;
		itemocs = new Vector<itemOC>();
		for (int i = 0; i < materiales.size(); i++) {
			Material mat = materiales.elementAt(i);
			itemOC itemOc = new itemOC(mat, mat.getCantidadAPedir());
			itemocs.add(itemOc);
		}
		// El codigo de la orden de compra (ordenesDeCompra.size()) ira aumentando 1,2,3.. secuencialmente.
		ordenDeCompra orden = new ordenDeCompra(ordenesDeCompra.size()+1, "Hoy",itemocs);
		ordenesDeCompra.add(orden);
	}
	
	public void ListarOrdenesDeCompra() {
		// Faltaria chequear si las ordenes de compra son del mismo proveedor
		if(ordenesDeCompra.size() > 0)
		{
			System.out.println("Ordenes de compras:");
			for (int i = 0; i < ordenesDeCompra.size(); i++) {
				ordenDeCompra orden = ordenesDeCompra.elementAt(i);
				Vector<itemOC> items = orden.getItemsOCs();
				System.out.println("------------------");
				System.out.println("Fecha: "+orden.getFechaOC());
				System.out.println("Codigo: "+orden.getCodigoOC());
				System.out.println("Materiales a pedir:");
				for (int j = 0; j < items.size(); j++) {
					itemOC item = items.elementAt(j);
					System.out.println("  ----------------");
					System.out.println(" - Nombre: "+item.getMaterial().getNombreMaterial());
					System.out.println(" - Cantidad: "+item.getCantPedir());
					System.out.println("  ----------------");
				}
				System.out.println("Proveedor: "+orden.getNombreProveedor());
				System.out.println("TOTAL: "+orden.getTotalOC());
				System.out.println("------------------");
			}
		}
		else
		{
			System.out.println("No hay ordenes de compra");
		}
	}
	
	public Prenda buscarPrenda(int cod)
	{
	
		for (int j=0; j < prendas.size(); j++)
		{
			if (prendas.elementAt(j).sosPrenda(cod) == true)
			{
			
				return prendas.elementAt(j);
			}
		}
	
		return null;
	}
	
	public Map<String, String> buscarTPrenda(int cod){
		Map<String, String> prenda = new HashMap<String, String>();
		if(prendas.size() > 0){
			for (int j=0; j < prendas.size(); j++){
				if (prendas.elementAt(j).sosPrenda(cod) == true){
					Prenda encontrada = prendas.elementAt(j);
					prenda.put("nombre", encontrada.getNombrePrenda());
					prenda.put("codigo", String.valueOf(encontrada.getCodigoPrenda()));
					prenda.put("stock", String.valueOf(encontrada.getStockPrenda()));
					prenda.put("precio", encontrada.getNombrePrenda());
				}
			}
		}
		return prenda;
	}
	
	public Material buscarMaterial(int cod){

		for (int i=0; i<materiales.size();i++)
		{
			if (materiales.elementAt(i).sosMaterial(cod))
				return materiales.elementAt(i);
		}

		return null;
	}

	public Vector<Integer> getPrendas() {
		Vector<Integer> idsprendas = new Vector<Integer>();
		if(prendas.size() > 0){
			for (int i = 0; i < prendas.size(); i++) {
				idsprendas.add(prendas.elementAt(i).getCodigoPrenda());
			}
		}
		return idsprendas;
	}	
	
	public void setPrendas(Vector<Prenda> prendas) {
		this.prendas = prendas;
	}
	
	public int generarFactura(int numeroCliente, String nombreCliente)
	{
		int numeroFactura = facturas.size()+1;
		Factura nuevaFactura = new Factura(numeroFactura, numeroCliente, nombreCliente);
		facturas.add(nuevaFactura);
		return nuevaFactura.getNroFactura();
	}

	public Map<String, String> buscarFactura(int numFactura){
		Map<String, String> datos = new HashMap<String, String>();
		if(facturas.size() > 0){
			for (int i = 0; i < facturas.size(); i++) {
				if(numFactura == facturas.elementAt(i).getNroFactura()){
					Factura fac = facturas.elementAt(i); 
					datos.put("nroFactura", String.valueOf(fac.getNroFactura()));
					datos.put("nombreLocal", fac.getNombreLocal());
					datos.put("nombreCliente", fac.getNombreCliente());
					datos.put("numCliente", String.valueOf(fac.getNumCliente()));
					datos.put("total", String.valueOf(fac.getPrecioTotal()));
				}
			}
		}
		return datos;
	}
	
	public void eliminarFactura(int numFactura){
		Factura factura = this.getFactura(numFactura);
		if(facturas.size()>0){
			int nroFac = factura.getNroFactura();
			for (int i = 0; i < facturas.size(); i++) {
				if(nroFac == facturas.elementAt(i).getNroFactura()){
					facturas.removeElementAt(i);
					break;
				}
			}
		}
	}
	
	private Factura getFactura(int numFactura){
		if(facturas.size() > 0){
			for (int i = 0; i < facturas.size(); i++) {
				if(numFactura == facturas.elementAt(i).getNroFactura()){
					return facturas.elementAt(i);
				}
			}
		}
		return null;
	}	
	
	public Vector<Vector> getOrdenesDeCompra() 
	{
		 Vector<Vector> filas = new Vector<Vector>();
		 Vector<String> titulo = new Vector<String>();
		 String codigot = "Codigo:";
		 String fechat = "Fecha:";
		 String totalt = "Total:";
		 String proveedort = "Proveedor:";
		titulo.add(codigot);
		titulo.add(fechat);
		titulo.add(totalt);
		titulo.add(proveedort);
		filas.add(titulo);
		 for (int i = 0; i < ordenesDeCompra.size(); i++) {
				ordenDeCompra ordenDecompra = ordenesDeCompra.elementAt(i);
				int codigo = ordenDecompra.getCodigoOC();
				String fecha = ordenDecompra.getFechaOC();
				float total = ordenDecompra.getTotalOC();
				String proveedor = ordenDecompra.getNombreProveedor();
				Vector<String> aux = new Vector<String>();
				aux.add(Integer.toString(codigo));
				aux.add(fecha);
				aux.add(Float.toString(total));
				aux.add(proveedor);
				filas.add(aux);

			}
		return filas;
	}
	
	public void agregarPrenda(int codfac, int codPrenda, int cantidad) {
		Factura fac = this.getFactura(codfac);
		Prenda prenda = buscarPrenda(codPrenda);
		fac.incorporarItemFactura(cantidad, prenda);
	}
		
	public void generarItemPrenda(int codigoMaterial, int cantMaterial){
		Material material = buscarMaterial(codigoMaterial);
		prendaActual.agregarItemPrenda(cantMaterial, material);
	}
	
	public void bajaPrenda(int codigo){
		Prenda prendainstanciada = this.buscarPrenda(codigo);
		if (prendainstanciada != null)
			prendainstanciada.baja();
	}
	
	public boolean modificarPrenda(int codigo, String nombre, int stock, float precio){
		Prenda prendatmp = this.buscarPrenda(codigo);
		if (prendatmp != null){
			prendatmp.setNombrePrenda(nombre);
			prendatmp.setStockPrenda(stock);
			prendatmp.setPrecioPrenda(precio);
			return true;
		}else{
			return false;
		}
			
	}

	public void finalizarAltaPrenda() {
		prendas.add(prendaActual);
		prendaActual = null;
		System.out.println(prendas);
	}

	public int[] getIdsMateriales() {
		int[] idsMateriales = new int[materiales.size()];
		for(int i=0; i<materiales.size(); i++){
			idsMateriales[i] = materiales.elementAt(i).getCodigoMaterial();
		}
		return idsMateriales;
	}

	public int[][] getItemsPrendas() {
		int[][] items = new int[0][2];
		
		if(prendaActual != null){
			Vector<ItemPrenda> itemsPrenda = prendaActual.getItemsprendas();
			items = new int[itemsPrenda.size()][2];
			if(itemsPrenda.size()>0){
				for (int i = 0; i < itemsPrenda.size(); i++) {
					items[i][0] = itemsPrenda.elementAt(i).getMaterial().getCodigoMaterial();
					items[i][1] = itemsPrenda.elementAt(i).getCantidad();
				}
			}
		}
		return items;
	}

	public String getNombreItemPrenda(int codigo) {
		return this.buscarMaterial(codigo).getNombreMaterial();
	}

	public int[][] getItemfacturas(int codigo){
		int[][] items = new int[0][2];
		if(getFactura(codigo) != null){
			items = getFactura(codigo).getItemfacturas();
		}
		return items;
	}		
}

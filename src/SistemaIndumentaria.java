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
	private Prenda prendaActual;
	
	private void cargaInicial()
	{
		Proveedor proveedor1 = new Proveedor("Cachito SA", 5);
		Proveedor proveedor2 = new Proveedor("Pepito SA", 2);
		proveedores.add(proveedor1);
		proveedores.add(proveedor2);

		Material material1 = new Material(10, "botones", 10, proveedor1, (float) 5.5, 100, 20);
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
	
	public Map<String, String> buscarOc(Integer codigo) {
		Map<String, String> datos = new HashMap<String, String>();
		if(ordenesDeCompra.size() > 0){
			for (int i = 0; i < ordenesDeCompra.size(); i++) {
				ordenDeCompra oc = ordenesDeCompra.elementAt(i);
				if(oc.sosOC(codigo)){
					datos.put("codigo", String.valueOf(oc.getCodigoOC()));
					datos.put("fecha", oc.getFechaOC());
					datos.put("proveedor", oc.getNombreProveedor());
					datos.put("total", String.valueOf(oc.getTotalOC()));
					break;
				}
			}
		}
		return datos;
	}
	
	public Vector<Integer> getIdsOC(){
		Vector<Integer> ids = new Vector<Integer>();
		if(ordenesDeCompra.size() > 0){
			for (int i = 0; i < ordenesDeCompra.size(); i++) {
				ids.add(ordenesDeCompra.elementAt(i).getCodigoOC());
			}
		}
		return ids;
	}
	
	public void ControlarStockMateriales(){
		for (int i = 0; i < materiales.size(); i++) {
			Material mat = materiales.elementAt(i);
			if(mat.getCantStock() <= mat.getPuntoDeReposicion()){
				materialesAPedir.add(mat);
			}
		}
		if(materialesAPedir.size()>0){
			while(materialesAPedir.size() > 0){
				Vector<Material> materialesPorProveedor = this.filtrarPorProveedor(materialesAPedir);
				String proveedor = materialesPorProveedor.elementAt(0).getProveedor().getNombreProveedor();
				ordenDeCompra oc = new ordenDeCompra(proveedor, materialesPorProveedor);
				ordenesDeCompra.add(oc);
				materialesAPedir = this.eliminarMatPorProveedor(proveedor);
			}
		}
	}
	
	private Vector<Material> filtrarPorProveedor(Vector<Material> materiales){
		Vector<Material> filtrados = new Vector<Material>();
		filtrados.addElement(materiales.elementAt(0));
		materiales.removeElementAt(0);
		for (int i = 0; i < materiales.size(); i++){
			Material mat = materiales.elementAt(i);
			if(mat.getProveedor().getCodigoProveedor() == filtrados.elementAt(0).getProveedor().getCodigoProveedor()){
				filtrados.add(mat);
			}
		}
		return filtrados;
	}
	
	private Vector<Material> eliminarMatPorProveedor(String proveedor){
		Vector<Material> materiales = new Vector<Material>();
		for (int i = 0; i < materiales.size(); i++){
			Proveedor prov = materiales.elementAt(i).getProveedor();
			if(prov.getNombreProveedor() == proveedor){
				materiales.remove(i);
			}
		}
		return materiales;
	}
	
	public Prenda buscarPrenda(int cod){
	
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
	
	//Nuevo CAGUIRRE
	public Vector<Material> getMateriales() {
		return materiales;
	}	
	
	
	public void setPrendas(Vector<Prenda> prendas) {
		this.prendas = prendas;
	}
	
	public int generarFactura(int numeroCliente, String nombreCliente){
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
	
	public int[][] getItemfacturas(int codigo){
		int[][] items = new int[0][2];
		if(getFactura(codigo) != null){
			items = getFactura(codigo).getItemfacturas();
		}
		return items;
	}
	
	public void eliminarFactura(Integer numFactura){
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

	public String getNombreItemPrenda(Integer codigo) {
		return this.buscarMaterial(codigo).getNombreMaterial();
	}

}

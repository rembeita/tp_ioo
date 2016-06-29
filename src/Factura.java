import java.util.Vector;
import java.util.Date;
public class Factura 
{
	private int nroFactura;
	private String nombreLocal="LA SALADA";
	private String nombreCliente;
	private int numCliente;

	private Date fecha;
	private float precioTotal;
	private Vector<itemFactura>itemfacturas;
	
	public int[][] getItemfacturas() {
		int[][] items = new int[0][2];
		if(itemfacturas.size() > 0){
			items = new int[itemfacturas.size()][2];
			for (int i = 0; i < itemfacturas.size(); i++) {
				items[i][0] = itemfacturas.elementAt(i).getPrenda().getCodigoPrenda();
				items[i][1] = itemfacturas.elementAt(i).getCantidadComprada();
			}
		}
		return items;
	}


	public void setItemfacturas(Vector<itemFactura> itemfacturas) {
		this.itemfacturas = itemfacturas;
	}


	public Factura(int nroFactura, int numCliente, String nombreCliente) 
	{
		super();
		this.nroFactura = nroFactura;
		this.nombreCliente = nombreCliente;
		this.fecha = new Date();
		this.numCliente = numCliente;
		itemfacturas = new Vector<itemFactura>();
		
	}
	
	
	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public int getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}
	public String getNombreLocal() {
		return nombreLocal;
	}
	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	public void incorporarItemFactura(int cantidadComprada, Prenda prenda)
	{
		if(itemfacturas.size() > 0){
			for (int i = 0; i < itemfacturas.size(); i++) {
				if(itemfacturas.elementAt(i).getPrenda().getCodigoPrenda() == prenda.getCodigoPrenda()){
					itemfacturas.elementAt(i).setCantidadComprada(cantidadComprada);
					break;
				} else if(i+1 == itemfacturas.size()){
					itemFactura itemfc = new itemFactura(cantidadComprada, prenda);
					this.itemfacturas.add(itemfc);
				}
			}
		} else {
			itemFactura itemfc = new itemFactura(cantidadComprada, prenda);
			this.itemfacturas.add(itemfc);
		}
		prenda.setStockPrenda(prenda.getStockPrenda()-cantidadComprada);
		this.setPrecioTotal(this.getPrecioTotal()+(cantidadComprada * prenda.getPrecioPrenda()));
	}

	public int getNumCliente() {
		return numCliente;
	}

}

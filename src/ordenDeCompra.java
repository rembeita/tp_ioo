import java.util.Vector;

public class ordenDeCompra {

	private int codigoOC;
	private String fechaOC;
	private float totalOC = 0;
	private Vector<itemOC> itemsOCs;
	private String nombreProveedor;

	// Faltaria agregar precio unitario
	public ordenDeCompra(int codigoOC, String fechaOC, Vector<itemOC> itemocs) {
		super();

		this.codigoOC = codigoOC;
		this.fechaOC = fechaOC;
		this.nombreProveedor = itemocs.firstElement().getMaterial().getProveedor().getNombreProveedor();
		this.itemsOCs = new Vector<itemOC>();

		for (int i = 0; i < itemocs.size(); i++) {
			itemOC item = itemocs.elementAt(i);
			Material mat = item.getMaterial();
			itemsOCs.add(item);
			this.totalOC += (float) (mat.getCosto() * item.getCantPedir());
		}
	}

	public int getCodigoOC() {
		return codigoOC;
	}

	public void setCodigoOC(int codigoOC) {
		this.codigoOC = codigoOC;
	}

	public Vector<itemOC> getItemsOCs() {
		return itemsOCs;
	}

	public void setItemsOCs(Vector<itemOC> itemsOCs) {
		this.itemsOCs = itemsOCs;
	}

	public String getFechaOC() {
		return fechaOC;
	}

	public void setFechaOC(String fechaOC) {
		this.fechaOC = fechaOC;
	}

	public float getTotalOC() {
		return totalOC;
	}

	public void setTotalOC(float totalOC) {
		this.totalOC = totalOC;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public void calcularPrecioTotal() {
		float total = 0;
		for (int i = 0; i < itemsOCs.size(); i++) {
			total = total + (itemsOCs.elementAt(i).getCantPedir() * itemsOCs.elementAt(i).getMaterial().getCosto());
		}
		System.out.println("El valor total de la OC es: " + total);
		this.totalOC = total;
	}

	public void incorporarItemOC(int cantPedir, Material material) {

		itemOC itemOC = new itemOC(material, cantPedir);
		this.itemsOCs.addElement(itemOC);

	}

	public boolean sosOC(int codigo) {
		return codigo == codigoOC;
	}

}
